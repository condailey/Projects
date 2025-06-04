#include "LinkedList.h"
#include "Node.h"

template<class ItemType>
void Node<ItemType>:: reverseChain(Node* p){
    if(p->getItem() == nullptr){
        p = headPtr;
    }
    while(p->getNext() != nullptr){

    }
}

