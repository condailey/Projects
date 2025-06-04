//Name: Connor Dailey
//Date: 5/4/2022
//Assignment: project8
//Description: implemented functions for printing various details from a family tree


#include "Tree.h"
#include "Person.h"
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;


void printFamilyTree(string prefix, TreeNode<Person*> *t)
{
  if (t == nullptr) {
    cout << "oops, subtree is nullptr\n";
    return;
  }
  int n = t->getNumChildren();
  cout << prefix << t->getItem()->toString() << ", with " << n << " children\n";
  for (int i = 0; i < n; i++) {
    TreeNode<Person*> *child = t->getChild(i);
    printFamilyTree(prefix + "| ", child);
  }
}

Person *findPersonHelper(TreeNode<Person*> *node, string target)//helper for finding a person in a tree
{
  if(node != nullptr){
    if(node->getItem()->name == target){
      return node->getItem();
    }else{
      int n = node->getNumChildren();
     for(int i = 0; i < n; i++){
        TreeNode<Person*> *child = node->getChild(i);
        Person* curPerson = findPersonHelper(child, target);
        if(curPerson != nullptr){
          return curPerson;
        }
     }
    }
  }
  return nullptr;
}

Person *findPerson(Tree<Person*> *tree, string target)//function for finding a person in a tree
{
  if (tree->isEmpty())
    return nullptr;
  else
    return findPersonHelper(tree->getRoot(), target);
}

bool isPrimeMinister(Person *p)
{
  return p->occupation.find("Prime Minister") != string::npos;
}

Tree<Person*> *parseFile(string filename)
{
  ifstream data(filename.c_str());
  if (!data.is_open()) {
    cout << "Sorry, the file 'genealogy.txt' could not be opened.\n"
      << "It is needed for this program. Please make sure it\n"
      << "can be found in the current directory and has suitable\n"
      << "file permissions.\n";
    return nullptr;
  }

  Tree<Person*> *family = new Tree<Person*>();

  string line;
  while ( getline(data, line) ) {
    Person *p = Person::parseLine(line);
    if (p == nullptr)
      continue; // skip any lines that could not be parsed
    if (family->isEmpty()) {
      family->addAsRoot(p);
    } else  {
      string parentName = "Unknown";
      if (p->lineage.rfind("son of ", 0) == 0)
        parentName = p->lineage.substr(7); // everything after "son of "
      else if (p->lineage.rfind("daughter of ", 0) == 0)
        parentName = p->lineage.substr(12); // everything after "daughter of "
      Person* parent = findPerson(family, parentName);
      if (parent == nullptr || !family->addAsChild(p, parent)) {
         cout << "Oops, this person's parent, " << parentName << " doesn't appear to be part of this family:\n"
           << "  " << p->toString() << "\n";
         delete p;
      }
    }
  }

  return family;
}

void printChildren(TreeNode<Person*> *node)
{
  int n = node->getNumChildren();
  for(int i = 0; i < n; i++){
    std::cout << node->getChild(i)->getItem()->name << "\n";
  }
  return;
}

void printFriendsOf(Tree<Person*> *tree, string target)//function for printing the close relatives of a given person
{
  Person* tar = findPerson(tree, target);
  TreeNode<Person*> *person = tree->findNode(tar);
  if(person == nullptr || person->isRoot()){
    std::cout << "Siblings and cousins of " << tar->name << " are not known." << "\n";
  }
  TreeNode<Person*> *parent = person->getParent();
  TreeNode<Person*> *grandParent = parent->getParent();

  if(grandParent == nullptr){
    std::cout<< "Siblings and cousins of " << person->getItem()->name << " include: " << "\n";
    int n = parent->getNumChildren();
    for(int i = 0; i < n; i++){
      TreeNode<Person*> *curChild = parent->getChild(i);
      std::cout<< curChild->getItem()->name << " \n";
    }
  }
  else{
    int c = grandParent->getNumChildren();
    std::cout<< "Siblings and cousins of " << person->getItem()->name << " include: " << "\n";
    for(int i = 0; i < c; i++){
      TreeNode<Person*> *auntUncle = grandParent->getChild(i);
      for(int j = 0; j < 1; j++){
        TreeNode<Person*> *cousin = auntUncle->getChild(j);
        std::cout << cousin->getItem()->name << " \n";
      }
    }
  }

}

int printPrimeHelper(TreeNode<Person*> *root)//helper for printing prime ministers
{
  int count = 0;
  if(root == nullptr){
    return count;
  }
  if(isPrimeMinister(root->getItem())){
    count++;
     std::cout << root->getItem()->name << " was a " << root->getItem()->occupation << "\n";
     int n = root->getNumChildren();
     for(int i = 0; i < n; i++){
        TreeNode<Person*> *child = root->getChild(i);
        if(child != nullptr){
          count += printPrimeHelper(child);
        }
     }
  }else{
     int n = root->getNumChildren();
     for(int i = 0; i < n; i++){
        TreeNode<Person*> *child = root->getChild(i);
        if(child != nullptr){
          count += printPrimeHelper(child);
        }
     }
  }
    return count;
  }

void printPrimeMinisters(Tree<Person*> *tree)//function for printing prime ministers
{
  std::cout << "Results of search for prime ministers: " << "\n";
  std::cout << printPrimeHelper(tree->getRoot()) << " family members found.";
}

void printAncestryOf(Tree<Person*> *family, string target)//function for printing the ancestry of a given person
{
  Person *tar = findPerson(family, target);
  TreeNode<Person*> *curPerson = family->findNode(tar);
  if(curPerson == nullptr){
    std::cout<< target << " does not appear to be a member of this family.";
    return;
  }
  std::cout<< "\n" << "Known ancestry of: " << tar->name << " \n";
  int h = family->getHeight();
  for(int i = 0; i < h; i++){
    std::cout<< curPerson->getItem()->name << "is the " << curPerson->getItem()->lineage << " \n";
    curPerson = curPerson->getParent();
  }
  return;
}

int main(void)
{
  Tree<Person*> *family = parseFile("genealogy.txt");

  cout << "The entire family tree:\n";
  printFamilyTree("", family->getRoot());
  cout << "\n";

  cout << "Portion of the family tree showing Indira Gandhi and her descendents:\n";
  TreeNode<Person*> *indiraNode = family->findNode(findPerson(family, "Indira Gandhi"));
  printFamilyTree("", indiraNode);
  cout << "\n";
  
 
  TreeNode<Person*> *vijayaNode = family->findNode(findPerson(family, "Vijaya Lakshmi Pandit"));
  cout << "Children of Vijaya Lakshmi Pandit are:\n";
  printChildren(vijayaNode);
  cout << "\n";


  printFriendsOf(family, "Balwant Kumar Nehru");
  cout << "\n";
  

  printPrimeMinisters(family);
  cout << "\n";

  printAncestryOf(family, "Anasuya Gandhi");
  printAncestryOf(family, "Prof. Fitzsimmons");
  cout << "\n";

  cout << "\n";

  cout << "\n";

  return 0;
}
