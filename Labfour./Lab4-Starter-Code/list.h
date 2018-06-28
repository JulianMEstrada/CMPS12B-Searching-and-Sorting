#ifndef CMPS12B_LIST
#define CMPS12B_LIST
#include <stdlib.h>
#include <stdio.h>

typedef struct node_type{

	void* data;
	struct node_type* next;  
	
} Node;


typedef struct {

    Node * head;// initalize a head node for our list 
	int size; // initalize a size index for our list 
} List;

Node* make_node(void* data, void* next);
List* make_list();

void free_node(Node* node);
void free_list(List* list);

void add(List* list, int index, void* data);
void* get(List* list, int index);
void set(List* list, int index, void* data);


#endif

