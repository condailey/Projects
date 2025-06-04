// Name: Connor Dailey
// Class: CSCI 132
// Date: 2/9/2022
// Assignmnet: Homework 1
// Purpose: Implementation of member variables and methods


#include "Player.h"
#include <cstdlib> // for rand()

//Player object that holds info for Player
Player::Player(std::string name)
{
  gamesWon = 0;
  this->name = name;
  hand =" ";

}
//clearhand method that clears hand
void Player::clearHand()
{
  hand = " ";
}
//clearPrizes method that clears prizes
void Player::clearPrizes()
{
  prizeBag.clear();
}
//method that throws
void Player::throwDice()
{
  diceBag.clear();
  for(int i = 0; i < NUM_DICE; i++){
    int num = randInRange(1, 6);
    diceBag.add(num);
    std::cout << num << " ";
    
  }
  computeHand();
}
//method that shows the hand
void Player::showHand()
{
  std::cout << hand << "";
}
//method that gets hand
std::string Player::getHand()
{
  return hand;
}
//method that counts prizes 
int Player::countPrizes()
{
  return prizeBag.getCurrentSize();
}
//method that adds prizes
bool Player::addPrize(std::string prize)
{
  if(!prizeBag.contains(prize)){
    prizeBag.add(prize);
    return true;
  }
  return false;
}
//method that increments Game wins
void Player::addGameWin()
{
  gamesWon++;
}
//method that gets the amount of games won
int Player::getGamesWon()
{
  return gamesWon;
}
//method that computes the hand
void Player::computeHand()
{
  int freq [7];
  int max = 0;
  int nextMax = 0;

  for(int i = 1; i < 7; i++){
    freq[i] = diceBag.getFrequencyOf(i);

  }
  
  for(int i = 1; i < 7; i++){
    if(freq[i] > max){
      nextMax = max;
      max = freq[i];
    }
    else if(freq[i] > nextMax){
      nextMax = freq[i];
    }
    if(max == 1){
      hand = "assortment";
    }
    if(max == 2){
      hand = "One Pair";
    }
    if(max == 2 && nextMax == 2){
      hand = "Two Pair";
    }
    if(max == 3 && nextMax == 2){
      hand = "Full House";
    }
    if(max == 3){
      hand = "Three of Kind";
    }
    if(max == 4){
      hand = "Four of a Kind";
    }
    if(max == 5){
      hand = "Five of a Kind";
    }
  }
}
//method that gets Names
std::string Player::getName()
{
  return name;
}
//method that gets a random number in a given range
int randInRange(int lo, int hi)
{
  double v = (double)rand();    // 0 <= v && v <= RAND_MAX
  double f = v / ((double)RAND_MAX+1);  // 0 <= f && f < 1.0
  return lo + (int)(f * (hi - lo + 1));
}  // end randInRange

// end