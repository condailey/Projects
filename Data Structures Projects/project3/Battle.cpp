//author: Connor Dailey
//date: 12/23/2022
//class: CSCI 132
//HW 3
//purpose: implementation of functions and client code for battle

#include "Card.h"
#include "Deck.h"
#include "ArrayStack.h"
#include <cassert>
#include <iostream>
using namespace std;
//initialization of variables
ArrayStack<Card> drawPile;
ArrayStack<Card> winPile;
ArrayStack<Card> drawPileC;
ArrayStack<Card> winPileC;
ArrayStack<Card> prizePile;
//initialization of functions and other variables
void replenish(ArrayStack<Card> & stack, ArrayStack<Card> & winStack);
bool nextRound();
string reply = "";

//method for replenishing draw pile
//precon: must have contents in to avoid assert failure
void replenish(ArrayStack<Card> & stack, ArrayStack<Card> & winStack){
    while(!winStack.isEmpty()){
        stack.push(winStack.peek());
        std::cout << "Moving ";
        winStack.peek().printCard();
        std::cout << "\n";
        winStack.pop();
    }
}

//determines next round 
bool nextRound(string response){ 
    while(response != "y" && response != "n"){
        std::cout << "Invalid answer. Do you want to continue (y/n)? ";
        std::cin >> response;
    }
    if(response == "n"){
        return false;
    }
    return true;
}
//beginning of main body 
int main(){
//keeps track of rounds
int rounds = 1;
//defines deck
Deck curDeck = Deck();

curDeck.shuffle();
std::cout << "How many cards in each hand (not more than 26)? ";
int numCards = 0;
std::cin >> numCards;
//condition check to see if number of cards is less than 26
while(numCards > 26 || numCards == 0 || numCards < 0){
    if(numCards > 26 || numCards == 0 || numCards < 0){
    std::cout << "That’s too many cards. Please enter a number less than 27: " << "\n" << "How many cards in each hand (not more than 26)? ";
    std::cin >> numCards;
    }
    break;
}
std::cout << "Dealing " << numCards << " cards to each player." << std::endl;
for(int i = 0; i < numCards; i++){
    drawPileC.push(curDeck.dealCard());
    drawPile.push(curDeck.dealCard()); 
}
    while(true){//while not breaking continue round
    
        //condition calling for replenishing player
        //precon: must have contents in to avoid assert failure
        if(drawPile.isEmpty() && !winPile.isEmpty()){ 
            std::cout << "\n";
            std::cout << "User replenishes draw pile using winnings: " << endl;
            replenish(drawPile, winPile);
        }
        //condition calling for replenishing computer
        //precon: must have contents in to avoid assert failure
        if(drawPileC.isEmpty() && !winPileC.isEmpty()){
            std::cout << "\n";
            std::cout << "Computer replenishes draw pile using winnings: " << endl;
            replenish(drawPileC, winPileC);
        }
        
        //interface messaging
        std::cout << "\n";
        std::cout << "User has " << drawPile.size() << " cards and draws ";
        drawPile.peek().printCard();
        std::cout << "\n";
        std::cout << "Computer has " << drawPileC.size() << " cards and draws ";
        drawPileC.peek().printCard();
        std::cout << "\n";


        //determines that computer wins
        if(drawPileC.peek().getValue() > drawPile.peek().getValue()){

            winPileC.push(drawPileC.peek());
            winPileC.push(drawPile.peek());

            drawPile.pop();
            drawPileC.pop();
            //precon: must have contents in to avoid assert failure
            while(!prizePile.isEmpty()){
                winPile.push(prizePile.peek());
            }
            std::cout<< "Computer wins round " << rounds << std::endl;
        //determines that User wins
        }else if(drawPileC.peek().getValue() < drawPile.peek().getValue()){
            winPile.push(drawPile.peek());
            winPileC.push(drawPileC.peek());
            drawPile.pop();
            drawPileC.pop();
            //precon: must have contents in to avoid assert failure
            while(!prizePile.isEmpty()){
                winPile.push(prizePile.peek());
            }
            std::cout<< "User wins round " << rounds << std::endl;
        }
        //determines tie
        else if(drawPileC.peek().getValue() == drawPile.peek().getValue()){
            prizePile.push(drawPile.peek());
            prizePile.push(drawPileC.peek());
            std::cout<< "It's a tie! " << rounds << std::endl;
            drawPile.pop();
            drawPileC.pop();
        }
        std::cout << "Do you want to continue (y/n)? ";
        string reply = " ";
        std::cin >> reply;
        if(reply == "y"){
            rounds++;
        }
        //condition for a loser
        if((drawPile.size() == 0 && winPile.size() == 0) || (drawPileC.size() == 0 && winPileC.size() == 0)){
            std::cout << "User has " << drawPile.size() + winPile.size() << " cards total. " << endl;
            std::cout << "Computer has " << drawPileC.size() + winPileC.size() << " cards total. " << endl;
            std::cout << "There are " << prizePile.size() << " cards in the prize pile." << endl;
            
            if(drawPile.size() + winPile.size() > drawPileC.size() + winPileC.size()){
                std::cout << "User wins!\n";
            } else if(drawPile.size() + winPile.size() > drawPileC.size() + winPileC.size()){
                std::cout << "Computer wins!\n";
            }
            break;
        }
        
        //function determining if there is a next round
        if(nextRound(reply) == false){
            std::cout << "User has " << drawPile.size() + winPile.size() << " cards total. " << endl;
            std::cout << "Computer has " << drawPileC.size() + winPileC.size() << " cards total. " << endl;
            std::cout << "There are " << prizePile.size() << " cards in the prize pile." << endl;
            if((drawPile.size() + winPile.size()) > (drawPileC.size() + winPileC.size())){
                std::cout << "User wins!" << "\n";
            }else if((drawPile.size() + winPile.size()) > (drawPileC.size() + winPileC.size())){
                std::cout << "Computer wins!" << "\n";   
            }

            break;
        }
    }

}//end