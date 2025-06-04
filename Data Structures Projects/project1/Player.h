// Name: Connor Dailey
// Class: CSCI 132
// Date: 2/9/2022
// Assignmnet: Homework 1
// Purpose: Initialization of member variables and methods

#ifndef PLAYER_H_
#define PLAYER_H_

#include "ArrayBag.h"
#include <string>
#include <iostream>

/** randInRange produces a pseudo-random integer between lo and hi,
 * inclusive. For example, randInRange(1, 100) may pick 1, 100, or
 * any number between them.
 * @precondition  lo <= hi
 * @return  A number r, such that lo <= r and r <= hi
 */
int randInRange(int lo, int hi);

class Player
{
public:
  Player(std::string name);
  void clearHand();
  void clearPrizes();
  void throwDice();
  void showHand();
  std::string getHand();
  int countPrizes();
  bool addPrize(std::string prize);
  std::string getName();
  void addGameWin();
  int getGamesWon();

private:
  void computeHand();

  static const int NUM_DICE = 5;
  ArrayBag<int> diceBag;
  ArrayBag<std::string> prizeBag;
  std::string hand;
  int gamesWon;
  std::string name;
};

#endif
