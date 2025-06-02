import SwiftUI

struct ContentView: View {
    @State private var word = ""
    @State private var guessedLetters = [Character]()
    @State private var incorrectGuesses = 0
    @State private var gameOver = false
    @State private var message = ""
    
    let maxIncorrectGuesses = 6
    
    func getRandomWord() -> String? {
        guard let fileURL = Bundle.main.url(forResource: "dictionary", withExtension: "txt"),
              let content = try? String(contentsOf: fileURL, encoding: .utf8) else { return nil }
        
        let wordList = content.components(separatedBy: .newlines)
            .filter { !$0.isEmpty }
        return wordList.randomElement()
    }
    
    func generateRandomWord() {
        word = getRandomWord()?.uppercased() ?? "SWIFTUI"
        guessedLetters.removeAll()
        incorrectGuesses = 0
        gameOver = false
        updateMessage()
    }
    
    var isWordGuessed: Bool {
        return word.allSatisfy { guessedLetters.contains($0) }
    }
    
    func updateMessage() {
        if gameOver {
            if isWordGuessed {
                message = "You Won!"
            } else {
                message = "Game Over. The word was \(word)."
            }
        } else {
            message = "Guess the word!"
        }
    }
    
    func guessLetter(_ letter: Character) {
        if guessedLetters.contains(letter) || gameOver {
            return
        }
        
        guessedLetters.append(letter)
        
        if !word.contains(letter) {
            incorrectGuesses += 1
        }
        
        if incorrectGuesses >= maxIncorrectGuesses {
            gameOver = true
        }
        
        if isWordGuessed {
            gameOver = true
        }
        
        updateMessage()
    }
    
    func resetGame() {
        generateRandomWord()
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack {
                // Hangman components (based on incorrect guesses)
                VStack {
                    if incorrectGuesses < 5 {
                        Rectangle()
                            .frame(width: 35, height: 45)
                            .foregroundStyle(.white)
                    } else {
                        Rectangle()
                            .frame(width: 35, height: 45)
                            .foregroundStyle(.black)
                    }
                    
                    if incorrectGuesses < 4 {
                        Rectangle()
                            .frame(width: 50, height: 10)
                            .foregroundStyle(.white)
                    } else {
                        Rectangle()
                            .frame(width: 50, height: 10)
                            .foregroundStyle(.black)
                    }
                    
                    if incorrectGuesses < 3 {
                        Circle()
                            .stroke(.white, lineWidth: 10)
                            .frame(width: 50, height: 50)
                    } else {
                        Circle()
                            .stroke(.black, lineWidth: 10)
                            .frame(width: 50, height: 50)
                    }
                    
                    if incorrectGuesses < 2 {
                        HStack {
                            Rectangle()
                                .rotation(Angle(degrees: 180))
                                .frame(width: 100, height: 10)
                                .foregroundStyle(.white)
                        }
                    } else {
                        HStack {
                            Rectangle()
                                .rotation(Angle(degrees: 180))
                                .frame(width: 100, height: 10)
                                .foregroundStyle(.black)
                        }
                    }
                    
                    if incorrectGuesses < 1 {
                        Circle()
                            .stroke(.white, lineWidth: 10)
                            .frame(width: 100, height: 100)
                    } else {
                        Circle()
                            .stroke(.black, lineWidth: 10)
                            .frame(width: 100, height: 100)
                    }
                }

                // Game message
                Text(message)
                    .font(.title)
                    .padding(.horizontal)
                
                if !gameOver {
                    Text("Incorrect Guesses: \(incorrectGuesses)/\(maxIncorrectGuesses)")
                        .font(.title2)
                        .padding(.horizontal)
                    
                    // Dynamically adjust word display with padding
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 10) {
                            ForEach(0..<word.count, id: \.self) { index in
                                let letter = word[word.index(word.startIndex, offsetBy: index)]
                                Text(self.guessedLetters.contains(letter) ? String(letter) : "_")
                                    .font(.system(size: geometry.size.width / CGFloat(word.count + 5)))
                                    .padding(8)
                                    .frame(minWidth: 20, maxWidth: 40)
                                    .background(Color.white)
                                    .cornerRadius(5)
                                    .shadow(radius: 5)
                            }
                        }
                        .padding(.horizontal)
                    }
                }
                
                // On-screen keyboard with dynamic button sizing
                ScrollView(.horizontal, showsIndicators: false) {
                    VStack {
                        let alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ'"
                        
                        ForEach(0..<3) { row in
                            HStack(spacing: 8) {
                                ForEach(0..<9) { col in
                                    let index = row * 9 + col
                                    if index < alphabet.count {
                                        let letter = alphabet[alphabet.index(alphabet.startIndex, offsetBy: index)]
                                        KeyboardButton(letter: letter, action: {
                                            self.guessLetter(letter)
                                        }, isDisabled: self.guessedLetters.contains(letter))
                                        .frame(width: (geometry.size.width - 40) / 9, height: 40)
                                    }
                                }
                            }
                            .frame(maxWidth: .infinity)
                            .padding(.horizontal)
                        }
                    }
                    .padding(.horizontal)
                }
                
                // Reset button
                Button(action: resetGame) {
                    Text("Start New Game")
                        .font(.title2)
                        .padding()
                        .background(Color.green)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
                .padding(.top)
            }
            .padding() // Padding to prevent clipping on edges
            .onAppear(perform: generateRandomWord)
        }
    }
}

// Custom button for keyboard
struct KeyboardButton: View {
    var letter: Character
    var action: () -> Void
    var isDisabled: Bool
    
    var body: some View {
        Button(action: action) {
            Text(String(letter))
                .font(.title)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(isDisabled ? Color.gray : Color.blue)
                .foregroundColor(.white)
                .cornerRadius(5)
                .shadow(radius: 5)
        }
        .disabled(isDisabled)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .previewDevice("iPhone 12")
            .previewLayout(.sizeThatFits)
    }
}
