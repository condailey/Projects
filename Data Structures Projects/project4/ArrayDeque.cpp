//author: Connor Dailey
//date: 3/16/2022
//class: CSCI 132
//HW 4
//purpose: implementation of functions ArrayDeque

#include "ArrayDeque.h"
#include "PrecondViolatedExcept.h"

template<class ItemType>
ArrayDeque<ItemType>::ArrayDeque()
{
  capacity = DEFAULT_CAPACITY;
  items = new ItemType[capacity]; // dyanamically allocate array
  front = 0;
  back = capacity - 1;
  count = 0;
}  // end default constructor

template<class ItemType>
ArrayDeque<ItemType>::ArrayDeque(int maxItems)
{
  capacity = maxItems;
  items = new ItemType[capacity]; // dyanamically allocate array
  front = 0;
  back = capacity - 1;
  count = 0;
}  // end one-argument constructor

template<class ItemType>
ArrayDeque<ItemType>::ArrayDeque(const ArrayDeque<ItemType>& other)
{
  // TODO: implement this method
  if(other.isEmpty()){
    throw PrecondViolatedExcept("ArrayDeque() called with empty deque");
  }
  capacity = other.capacity;
  items = new ItemType[capacity];
  back = capacity - 1;
  front = 0;
  for(int i = 0; i < other.count; i++){
    int current = (other.front + i) % capacity;
    ItemType hold = other.items[current];
    this->enqueueBack(hold);
  }
}  // end copy constructor

template<class ItemType>
ArrayDeque<ItemType>::~ArrayDeque()
{
  delete[] items;
}  // end destructor

template<class ItemType>
bool ArrayDeque<ItemType>::isEmpty() const
{
  return count == 0;
}  // end isEmpty

template<class ItemType>
bool ArrayDeque<ItemType>::isFull() const
{
  return count == capacity;
}  // end isFull

template<class ItemType>
bool ArrayDeque<ItemType>::dequeueFront()
{
  if (isEmpty()) {
    return false;
  } else {
    // Increment front, being careful to wrap around to the start
    front = (front + 1) % capacity;
    count--;
    return true;
  }
} // end dequeueFront

template<class ItemType>
ItemType ArrayDeque<ItemType>::peekFront() const
{
  // Check precondition, just to be safe.
  if (isEmpty())
    throw PrecondViolatedExcept("peekFront() called with empty deque");

  // ArrayDeque is not empty, so return the front entry
  return items[front];
} // end peekFront

template<class ItemType>
bool ArrayDeque<ItemType>::enqueueBack(const ItemType &newEntry)
{
  if (isFull()) {
    return false;
  } else {
    // There is room for another item.
    // Increment back, being careful to wrap around to the start
    back = (back + 1) % capacity;
    items[back] = newEntry;
    count++;
    return true;
  }
} // end enqueueBack

  template<class ItemType>
bool ArrayDeque<ItemType>::enqueueFront(const ItemType &newEntry)
{
  // TODO: implement this method
  if (isFull()){
    return false; // stub
  }if(front == 0){
    front = capacity - 1;
  } else{
    front = (front - 1) % capacity;
  }
  items[front] = newEntry;
  count++;
  return true;
} // end enqueueFront

template<class ItemType>
bool ArrayDeque<ItemType>::dequeueBack()
{
  // TODO: implement this method
  if (isEmpty()) {
    return false; //stub
  } if(back == 0){
    back = capacity -1;
  }else {
    back = (back - 1) % capacity;
  }
  count--;
  return true;

} // end dequeueBack

template<class ItemType>
ItemType ArrayDeque<ItemType>::peekBack() const
{
  // TODO: implement this method
  if (isEmpty())
    throw PrecondViolatedExcept("peekBack() called with empty deque");

  // ArrayDeque is not empty, so return the front entry
  return items[back];
} // end peekBack

