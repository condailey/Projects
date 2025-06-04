//author: Connor Dailey
//date: 3/23/2022
//class: CSCI 132
//HW 5
//purpose: implementation of functions ListFunctions

#include "ListInterface.h"
#include "ArrayList.h"
#include "LinkedList.h"
#include <iostream>
#include <string>

using namespace std;

/* Post: Entries at positions pos1 and pos2 of ArrayList aList
 * are interchanged.  If either entry is missing return false. */
bool interchange(int pos1, int pos2, ListInterface<int>* aList) {
    if(aList->getEntry(pos1) == 0 || aList->getEntry(pos2) == 0){
        return false;
    }else{
        int copy = aList->getEntry(pos1);
        aList->replace(pos1, aList->getEntry(pos2));
        aList->replace(pos2, copy);
        return true;
    }
}

/* Post: All entries from list1 are copied onto the end of list2.
 * Returns false if list2 is full before the copying is complete. */
bool join(ListInterface<int>* list1, ListInterface<int>* list2) {
    if(list1->isEmpty()){
        return false;
    }
    for(int i = 1; i <= list1->getLength(); i++){
        list2->insert(list2->getLength() + 1, list1->getEntry(i));
    }
    return true;
}
/* Post:  Reverses the order of all entries in aList. */
void reverse(ListInterface<int>* aList) {
    for(int i = 1; i <= (aList->getLength()) / 2; i++){    
        interchange(i, aList->getLength() - i + 1, aList);
    }

}
/* Post: Copies all entries from source so that those in odd-numbered
 * positions make up oddlist and those in even-numbered positions make up
 * Returns false if either output list fills before the copy is complete.
 * Otherwise, returns true. */
bool split(const ListInterface<int>* source, ListInterface<int>* oddlist, ListInterface<int>* evenlist) {
    if(source->isEmpty()){
        return false;
    }
    int evenCount = 1;
    int oddCount = 1;
    for(int i = 1; i <= source->getLength(); i++){
            if(i % 2 == 0){
                evenlist->insert(evenCount, source->getEntry(i));
                evenCount++;
            }
            if(i % 2 != 0){
                oddlist->insert(oddCount, source->getEntry(i));
                oddCount++;
            }
        }
    return true;
}

/* Post: list1 contains all of the entries of the source list with values less
 * than or equal to the given value x. list2 contains all of the entries of
 * the source list with values greater than the given value x. Returns false
 * if either output list fills before the copy is complete. Otherwise, returns true. */
bool partList(const int& x, const ListInterface<int>* source, ListInterface<int>* list1,
                                                              ListInterface<int>* list2) {
    int list1Val = 1;
    int list2Val = 1;
    for(int i = 1; i <= source->getLength(); i++){
        if(source->getEntry(i) <= x){
            list1->insert(list1Val, source->getEntry(i));
                list1Val++;
        }
        if(source->getEntry(i) > x){
            list2->insert(list2Val, source->getEntry(i));
                list2Val++;
        }
    }
    return true;
}

void printList(const ListInterface<int>* aList) {
    for (int i = 1; i <= aList->getLength(); i++) { 
        cout << aList->getEntry(i)  << " ";
    }
    cout << endl;
} //end printList

int main(void) {
	ListInterface<int>* myList;
	ListInterface<int>* one;
	ListInterface<int>* two;

    string choice = "";
    cout << "Please choose the list implementation to use (array/linked): ";
    cin >> choice;
    while(choice != "array" && choice != "linked") {
        cout << "Please choose the list implementation to use (array/linked): ";
        cin >> choice;
    }

    int n = 0;
    cout << "Enter a size for the list to test: ";
    cin >> n;

    while (n <= 0) {
        cout << "Please enter a number greater than 0." << endl;
        cout << "Enter a size for the list to test: ";
        cin >> n;
    }

	cout << endl << "Creating a list of " << n << " random entries." << endl;

    if (choice == "array") {
        myList = new ArrayList<int>;
        one = new ArrayList<int>;
        two = new ArrayList<int>;
    } else {
        myList = new LinkedList<int>;
        one = new LinkedList<int>;
        two = new LinkedList<int>;
    }

	
    srand(1234);
    // Generates a list of length n consisting of random
    // numbers, each chosen from the range 1 to n.
	for (int i = 1; i <= n; i++) {
		myList->insert(i, 1+(rand()%n));
	} 
	printList(myList);

	cout << endl << "Interchanging positions 1 and " << n-1 << " of list. " << endl;
	if(!interchange(1, n-1, myList)) {
		cout << "Error in interchange." << endl;
	} else {
		printList(myList);
	} //else print the interchanged list
	
	cout << endl 
	     << "Switching entries at 1 and " << n-1 << " back to original positions. " << endl;
	if(!interchange(1, n-1, myList)) {
		cout << "Error in interchange." << endl;
	} else {
		printList(myList);
	} //else print the interchanged list

	cout << endl << "Splitting list into odd list and even list. " << endl;
    if(!split(myList, one, two)) {
		cout << "Error in splitting list." << endl;
	} else {
		cout << "Printing odd list. " << endl;
		printList(one);
		cout << "Printing even list. " << endl;
		printList(two);
	} //else the list was split, print each list
	
	cout << endl << "Joining odd list to the end of the even list." << endl;
    if(!join(one,two)) {
		cout << "Error in joining lists." << endl;
	} else {
		printList(two);
	} //else print after joining
    one->clear();
    two->clear();
    
    int x = n/2;
    cout << endl << "Splitting list at value " << x << "." << endl;
    if(!partList(x, myList, one, two)) {
        cout << "Error in partitioning list!" << endl;
    } else {
        cout << "Printing first partition." << endl;
        printList(one);
        cout << "Printing second partition." << endl;
        printList(two);
    }
    delete one;
    delete two;
	
	cout << endl << "Reversing list. " << endl;
	reverse(myList);
    printList(myList);

    int value = 0;
    cout << endl << "Testing remove for a given entry." << endl;
    cout << "Enter a value to remove from the list: ";
    cin >> value;
    if(!myList->removeEntry(value)) {
        cout << "Could not remove " << value << " from the list." << endl;
    } else {
        cout << "Successfully removed the first occurrence of " << value << " from the list." << endl;
        printList(myList);
    }

    delete myList;

	return 0;
} //end main
	
