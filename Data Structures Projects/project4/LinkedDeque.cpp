//author: Connor Dailey
//date: 3/16/2022
//class: CSCI 132
//HW 4
//purpose: implementation of functions for LinkedDequeue


#include "LinkedDeque.h"  

template<class ItemType>
LinkedDeque<ItemType>::LinkedDeque()
{
  frontPtr = nullptr;
  backPtr = nullptr;
}  // end default constructor

template<class ItemType>
LinkedDeque<ItemType>::LinkedDeque(const LinkedDeque<ItemType>& original)
{
  // TODO: implement this method
  if(original.isEmpty()){
    throw PrecondViolatedExcept("LinkedDeque() called with empty deque");
  }
  frontPtr = nullptr;
  backPtr = nullptr;
  Node<ItemType>* copy = original.frontPtr;
  while(copy != nullptr){
    this->enqueueBack(copy->getItem());
    copy = copy->getNext();
    
  }
}  // end copy constructor

template<class ItemType>
LinkedDeque<ItemType>::~LinkedDeque()
{
  while (!isEmpty())
    dequeueFront();
}  // end destructor

template<class ItemType>
bool LinkedDeque<ItemType>::isEmpty() const
{
  return backPtr == nullptr;
}  // end isEmpty

template<class ItemType>
bool LinkedDeque<ItemType>::isFull() const
{
  return false;
}  // end isFull

template<class ItemType>
bool LinkedDeque<ItemType>::enqueueBack(const ItemType& newEntry)
{
  Node<ItemType>* toAdd = new Node<ItemType>(newEntry, nullptr);

  // Insert the new node
  if (isEmpty()) {
    // Was empty, so it becomes our only node.
    frontPtr = toAdd;
    backPtr = toAdd;
  } else {
    // Not empty, so link it into the back of our chain.
    backPtr->setNext(toAdd);
    backPtr = toAdd;
  }

  return true;
}  // end enqueueBack

template<class ItemType>
ItemType LinkedDeque<ItemType>::peekFront() const
{
  // Check precondition, just to be safe.
  if (isEmpty())
    throw PrecondViolatedExcept("peekFront() called with empty deque");

  // ArrayDeque is not empty, so return the front entry
  return frontPtr->getItem();
} // end peekFront

template<class ItemType>
bool LinkedDeque<ItemType>::dequeueFront()
{
  if (isEmpty())
    return false;

  // Deque is not empty, so unlink and delete front node.
  Node<ItemType>* toDelete = frontPtr;
  if (frontPtr == backPtr) {
    // Special case: only one node in the chain
    backPtr = nullptr;
    frontPtr = nullptr;
  } else {
    // Ordinary case: multiple nodes in chain, move front to second one
    frontPtr = frontPtr->getNext();
  }
  delete toDelete;
  return true;
}  // end dequeueFront

template<class ItemType>
bool LinkedDeque<ItemType>::enqueueFront(const ItemType& newEntry)
{
  // TODO: implement this method
  Node<ItemType>* toAdd = new Node<ItemType>(newEntry, nullptr);

  // Insert the new node
  if (isEmpty()) {
    // Was empty, so it becomes our only node.
    frontPtr = toAdd;
    backPtr = toAdd;
  } else {
    // Not empty, so link it into the front of our chain.
    toAdd->setNext(frontPtr);
    frontPtr = toAdd;
  }

  return true;
}  // end enqueueFront
  

template<class ItemType>
bool LinkedDeque<ItemType>::dequeueBack()
{
  // TODO: implement this method
  if (isEmpty())
    return false;

  // Deque is not empty, so unlink and delete back node.
  Node<ItemType>* toBack = frontPtr;
  Node<ItemType>* toDel = backPtr;
  if (frontPtr == backPtr) {
    // Special case: only one node in the chain
    backPtr = nullptr;
    frontPtr = nullptr;
  }else {
  while(toBack->getNext() != backPtr){
    toBack = toBack->getNext();
  }
  toBack->setNext(nullptr);
  delete toDel;
  backPtr = toBack;
  }
  return true;
}  // end dequeueBack

template<class ItemType>
ItemType LinkedDeque<ItemType>::peekBack() const
{
  // TODO: implement this method
  if (isEmpty())
    throw PrecondViolatedExcept("peekBack() called with empty deque");

  // ArrayDeque is not empty, so return the back entry
  return backPtr->getItem();
}  // end peekBack