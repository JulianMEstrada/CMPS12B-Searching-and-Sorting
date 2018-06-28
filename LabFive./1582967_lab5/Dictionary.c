#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

typedef struct EntryObj{ // new EntryObject
    char* key;
    char* value;
    struct EntryObj* next;
    struct EntryObj* prev;

}EntryObj;
int holder = 101; // Holder for needed table amount value in relation to PDF requirements DO NOT CHANGE** 
typedef EntryObj* Entry;

Entry newEntry(char* key, char* value){ // Node entry initalization
    Entry curr = malloc(sizeof(EntryObj));
    curr->value=calloc(holder, sizeof(char));
    curr->key=calloc(holder, sizeof(char));
    if(curr!=NULL && curr->value!=0 && curr->key!=0){
    strcpy(curr->value, value); //copy
     strcpy(curr->key, key); //copy
 }
 curr->next = NULL;
 curr->prev = NULL;
 return curr;
}
typedef struct DictionaryObj{ // Dictionary Object
    Entry* table;
    int tableSize;
}DictionaryObj;

Dictionary newDictionary(int tableSize){ //Dictionary initalization
    Dictionary D = malloc(sizeof(DictionaryObj));
    if(D!=NULL){
      D->table = calloc(holder, sizeof(Entry));
  }
  if(D->table!=NULL){
      D->tableSize=0;
  }
  return D;
}
void freeEntry(Entry* pN){ // Free node 
    if(pN!=NULL&&*pN!=NULL){
        free(*pN);
        *pN=NULL;
    }
}
void freeDictionary(Dictionary* pD){ // Free Dictionary 
    if(pD!=NULL && *pD!=NULL){
        makeEmpty(*pD);
        free(*pD);
        *pD=NULL;
    }
    int i;
  for (i = 0; i < pD->size; i++) {
 
    if (pD->table[i] != NULL) 
        freeEntry(pD->table[i]);
  }

}
//KEEP, STARTED CODE

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
 int sizeInBits = 8*sizeof(unsigned int);
 shift = shift & (sizeInBits - 1);
 if ( shift == 0 ) {
  return value;
}
return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
 unsigned int result = 0xBAE86554;
 while (*input) { 
  result ^= *input++;
  result = rotate_left(result, 5);
}
return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(Dictionary D, char* key){
 return pre_hash(key) %holder;
}
// END STARTER CODE

int isEmpty(Dictionary D){ // Empty
  
    return D->tableSize==0;
}

int size(Dictionary D){ // Size of table 
    return D->tableSize;
}

Entry find(Dictionary D, char* key){ // Find helper for look up and to just find current without value
    Entry curr = D->table[hash(D, key)];
    Entry NodeE;
    while(curr && curr->next!=NULL){
        if( strcmp(curr->key, key)==0 ){ //compare
            return curr;
        }
        NodeE = curr;
        curr = curr->next;
        curr->prev = NodeE;
    }
          NodeE = NULL;
        return curr;
  
}
char* lookup(Dictionary D, char* key){ // lookUp driver relies on find above returns value of curr
    Entry curr;
    curr = find(D, key);
    if(curr==NULL||strcmp(curr->key, key)!=0){ //compare
        return NULL;
    }
    return curr->value;
}

void insert(Dictionary D, char* key, char* value){ // Insert into table
    int i;
    Entry curr, NodeE; // New Entries
    i = hash(D, key); // hash key
    curr = find(D, key);
    if(curr==NULL){  // current is null so create 
        curr = newEntry(key, value);
        D->table[i]=curr;
     }
     else if(curr!=NULL){ // not null continue on
         curr = D->table[i];
          NodeE = newEntry(key, value);
          NodeE->next = curr;
          curr= NodeE;
    }
    D->tableSize++;
}

void delete(Dictionary D, char* key){ // Delete value in table 
    Entry curr, NodeE; // New Entries
    curr = find(D, key); // find value 
    if(curr->prev==NULL){ 
        NodeE = curr;
        D->table[hash(D, key)]=curr->next; // next value of current will equal our hash
        free(NodeE->value); // free
        NodeE->value = NULL; // set val to null 
        free(NodeE->key); //free
        NodeE->key = NULL;
        freeEntry(&NodeE); // free
    }else{
        // case in which our prev is not null 
        NodeE = curr->prev;
        NodeE->next = curr->next;
        free(curr->value);
        curr->value=NULL;
        free(curr->key);
        curr->key=NULL;
        freeEntry(&curr);
    }
    D->tableSize--;
}

void makeEmpty(Dictionary D){ // Empty table
    int i = 0;
    Entry curr, NodeE;
    while(i<holder){
        curr = D->table[i];
        while(curr!=NULL){
            NodeE = curr->next;
            delete(D, curr->key);
            curr = NodeE;
        }
        i++;
    }
}
void printDictionary(FILE* out, Dictionary D){ // Print 
    Entry curr;
    int i = 0;
    curr = D->table[i];
    while(i<holder){
        curr = D->table[i];
        while(curr!=NULL){
            fprintf(out, "%s %s\n", curr->key, curr->value);
            curr=curr->next;
        }
        i++;
    }
}