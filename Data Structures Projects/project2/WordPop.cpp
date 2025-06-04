// Name: Connor Dailey
// Class: CSCI 132
// Date: 2/16/2022
// Assignmnet: HW 2
// Purpose: Implementation of member variables and methods

#include "ArrayBag.h"
#include <iostream>
#include <iomanip>
#include <fstream>

using namespace std;

// A list of common punctuation characters that might separate words in a title.
const string PUNCTUATION = " ,:;\t.()&";

// most of the papers are from 1967 - 2007, a 41-year span
const int OLDEST = 1967;
const int NEWEST = 2007;

int main()
{
  ifstream data;
  data.open("papers.lst");
  if (!data) {
    cout << "Sorry, could not open file: papers.lst\n";
    return 1;
  }

  ArrayBag<string> *years = new ArrayBag<string>[41]; //initialization of years array of array bags
  ArrayBag<string> *holder = new ArrayBag<string>; //initialization of an ArrayBag to hold the trends
  while (true) {
    int year;
    data >> year;
    if (!data)
      break;

    string authors;
    getline(data, authors, ',');
    if (!data)
      break;

    string title;
    getline(data, title);
    if (!data)
      break;

    // ignore papers outside the range OLDEST to NEWEST
    if (year < OLDEST || year > NEWEST)
      continue;

    // ignore papers if the arraybag is already full
    if (years[year -1967].isFull())
      continue;

    bool moreWords = true;
    while (moreWords) {
      size_t pos = title.find_first_of(PUNCTUATION);
      string word;
      if (pos == string::npos) {
        word = title;
        moreWords = false;
      } else {
        word = title.substr(0, pos);
        title.erase(0, pos+1);
      }

      // ignore short words, like "A", or "the", which are mostly boring
      if (word.length() < 5)
        continue;

      // process word
      if (!years[year - 1967].add(word)) {
        cout << "Oops, ArrayBag is now full.\n";
        break;
      }
    }
  }
  
  int freq = 0; // frequency
  int maxFreq = 0; // max frequency
  string curr = ""; // current word
  string mostFreqWord = ""; // most frequent word
  for(int i = 1967; i < 2008; i++){ // goes through each spot in array
    freq = 0;
    maxFreq = 0;
    for(int j = 0; j < years[i-1967].getCurrentSize(); j++){ // goes through contents in each space of array 
      curr = years[i-1967].get(j);
      freq = years[i-1967].getFrequencyOf(curr);
      if(freq > maxFreq){
        maxFreq = freq;
        mostFreqWord = curr;
      }
    }
   std::cout << "Most popular word for " << i << " is " << mostFreqWord << ", which appeared " << maxFreq << " times,\n " << " among " << years[i - 1967].getCurrentSize() << " total words for that year." << endl; // prints original output
   if(!holder->contains(mostFreqWord)){ // condition for no duplicates in keeping track of the trends
     holder->add(mostFreqWord);
   }
   
  }
  std:: cout << "YEAR "; // printing the trends
  for(int i = 0; i < holder->getCurrentSize(); i++){ //iterate through the holder
    mostFreqWord = holder->get(i);
    std:: cout << mostFreqWord << "    ";
  }
  std::cout << "\n";

  for(int i = 0; i < 41; i++){ //iterate through each year
    freq = years[i].getFrequencyOf(mostFreqWord);
    std:: cout << 1967 + i << " ";
    for(int j = 0; j < holder->getCurrentSize(); j++){ //iterate through each space in the holder for i years
      mostFreqWord = holder->get(j);
      int newNum = years[i].getFrequencyOf(mostFreqWord);
      std::cout << newNum << "             ";
    }
    std::cout << "\n";
  }  

  // Print frequency of each word, taking care to only print each word
  // (and frequency) once. That way, if "Machine" appears say 3 times
  // in the ArrayBag, we print "3 Machine", but we only print this the
  // first time that "Machine" is encountered in the ArrayBag. It
  // would be silly to print that three times!
  //ArrayBag<string> printed; // this bag keeps track of words already printed
  //int n = years->getCurrentSize();
  //int count = 0;
  //string word = " ";
  //for (int i = 0; i < n; i++) {
    //word = words->get(i);
    //count = words->getFrequencyOf(word);
      // if word was already printed, don't print again,
      // continue with next loop iteration instead
      //continue;
    //}
    //cout << setw(5) << count << " " << words << "\n";
    //printed.add(word); // add the now-printed word to the bag 
    
  }//end




