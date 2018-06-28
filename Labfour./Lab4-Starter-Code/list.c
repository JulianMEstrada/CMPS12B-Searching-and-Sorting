#include <stdlib.h>
#include "list.h"
#include <stdio.h>
#include <assert.h>

Node* make_node(void* data, void* next){ //MAKE NODE
  //Create new node and space for that node, pass data and return
  Node* newNode= calloc(1, sizeof(Node));
  newNode->data=data;
  newNode->next=next;
  return newNode;
}// END MAKE NODE

List* make_list(){//MAKE LIST
// Create new list with size zero and return
  List* newList= calloc(1, sizeof(List));
  newList->size=0;
  newList->head=NULL;

  return newList;
}

void free_node(Node* node){// FREE NODE
//Destructor meathod for a node, free the node while current is not null
  if (node->data!=NULL){
    free(node->data);
    node->data=NULL;
  }
  free(node); 
}//END FREE NODE

void free_list(List* list) {//FREE LIST 
//Destructor meathod for a list, free the list while current is not null
  Node * current = list->head;
  Node * next = current;
  while(current != NULL){
    next = current->next;
    free_node(current);
    current = next;
  }
  free(list);
}//END FREE LIST

void add(List* list, int index, void* data) {//ADD 
  assert(!(index > list->size || index < 0) && "Index out of bounds"); //KEEP
//add to our with index and data passed, keep all conditions 
    if (index==0){// if index is zero
      Node* newNode= make_node(data, NULL);
      newNode->next= list->head;
      list->head= newNode;
      list->size=list->size+1;
    }
    // if the given index matches the size of our lis t
    else if (index==list->size){
      Node* current= list->head;
    // while next of the list is not a null 
      while(current->next!=NULL){ 
        current= current->next;
      }
      Node* newNode= make_node(data, NULL);
      current->next= newNode;
      list->size=list->size+1;
    }
    // else condition head exists and given index does not match
    else {
      Node* current=list->head;

      for (int i=0; i<index; i++){
        current=current->next;
      }
      Node* newNode= make_node(data, NULL);
      newNode->next= current->next;
      current->next= newNode;
      list->size=list->size+1;
    }
}// END ADD 

void* get(List* list, int index){ // GET
  assert(!(index > list->size || index < 0) && "Index out of bounds");
  // finds given list item at given index return the data of that current node 
  Node * current = list->head;
  int count = 0;
  while(count < index){
    current = current->next;
    count= count+1;
  }
  return current->data;
}// END GET 

void set(List* list, int index, void* data) { // SET
  assert(!(index > list->size || index < 0) && "Index out of bounds");
  // finds given list item at given index return the data of that current node 
  Node * current = list->head;
  int count = 0;
  while(count < index){
    current = current->next;
    count= count+1;
  }
  current = current->data;
}// END SET
