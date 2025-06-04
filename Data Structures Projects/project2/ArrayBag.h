// Name: Connor Dailey
// Class: CSCI 132
// Date: 2/16/2022
// Assignmnet: HW 2
// Purpose: Initialization of member variables and methods

/** Header file for an array-based implementation of the ADT bag. Listing 3-1.
  @file ArrayBag.h */

#ifndef ARRAY_BAG_
#define ARRAY_BAG_

#include "BagInterface.h"

template<class ItemType>
class ArrayBag : public BagInterface<ItemType>
{
  private:
    static const int DEFAULT_CAPACITY = 50000; // Use 100 until we know a better size
    ItemType items[DEFAULT_CAPACITY];        // Array of bag items
    int itemCount;                           // Current count of bag items 
    int maxItems;                            // Max capacity of the bag

    // Returns either the index of the element in the array items that
    // contains the given target or -1, if the array does not contain 
    // the target.
    int getIndexOf(const ItemType& target) const;   

  public:
    ArrayBag(); // Default Constructor
    int getCurrentSize() const;
    bool isEmpty() const;
    bool isFull() const;
    bool add(const ItemType& newEntry);
    bool remove(const ItemType& anEntry);
    void clear();
    bool contains(const ItemType& anEntry) const;
    ItemType get(int pos) const;
    int getFrequencyOf(const ItemType& anEntry) const;
    std::vector<ItemType> toVector() const;
    // ItemType* toArray() const;
    void fillArray(ItemType arr[]) const;

    // pickRandom selects a random item from this Bag.
    // precondition: this Bag must not be empty
    // returns: the randomly-selected item
    ItemType pickRandom() const; // fixme: should return type be reference?
}; // end ArrayBag

#include "ArrayBag.cpp"
#endif
