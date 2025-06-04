/*********************************************************************************
 * Name: Connor Dailey
 * Date: 3/28/2022
 * Description: Program that solves a maze via backtracking recursion.
 ********************************************************************************/

#include <iostream>
#include "Maze.h"
using namespace std;

// Try to find a path throught maze from (x, y) to the MAGENTA exit.
// precondition: x and y have to be at least greater than one.
// postcondition: returns a solved maze illustrating the path to the exit.
bool solveMaze(Maze &m, int x, int y) {
  if(m.getMarker(x, y) == MAGENTA){//base case that returns true once the exit is found.
    return true;
  }
  if(m.getMarker(x, y) == YELLOW){//case that returns false to not go back over breadcrumbs in maze.
    return false;
  }
  if(m.getMarker(x, y) == BLANK){//if the current position is blank explore options
    m.markPosition(x, y, YELLOW);
    if(!m.hasWallAbove(x, y)){//checks if there is wall above.
      if(solveMaze(m, x, y-1)){
        return true;
      }
    } if(!m.hasWallBelow(x, y)){//checks if there is wall below.
      if(solveMaze(m, x, y+1)){
        return true;
      }
    } if(!m.hasWallLeft(x, y)){//checks if there is a wall to the left.
      if(solveMaze(m, x-1, y)){
        return true;
      }
    } if(!m.hasWallRight(x, y)){//checks if there is a wall to the right.
      if(solveMaze(m, x+1, y)){
        return true;
      }
    }
    m.markPosition(x, y, BLANK);//will make a previously yellow space blank via backtracking.
  }
  return false; // can't find an option

}  // end solveMaze

int main(int argc, char **argv)  {

  // Modify the default behavior for Maze class, if desired.
  // Maze::printInColor = false;
  // Maze::animated = false;
  // Maze::animationSlowness = 30; // lower is quicker
  // Maze::allowCycles = true;
  // Maze::allowUnsolvable = true;

  int width, height, seed;

  if (argc == 4) {
    // Use command-line parameters, if provided
    width = atoi(argv[1]);
    height = atoi(argv[2]);
    seed = atoi(argv[3]);
  } else {
    cout << "Enter width and height for maze: ";
    cin >> width >> height;
    cout << "Enter a seed for the random generator (or 0 for varying): ";
    cin >> seed;
  }

  Maze m(width, height, seed);

  cout << "Maze to be solved:\n";
  m.print();

  bool success = solveMaze(m, 0, 0);

  if (success)
    cout << "Maze is solved:\n";
  else
    cout << "Maze could not be solved:\n";
  m.print();

  return 0;
}  // end main
