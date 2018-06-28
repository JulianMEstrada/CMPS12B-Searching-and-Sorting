
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "rule.h"
#include "helpers.h"

Rule* make_rule(char* key){
// make rule and expansion 
  Rule* newRule= calloc(1, sizeof(Rule));
  newRule->key=key;
  newRule->expansions=make_list();
  return newRule; 
}

void free_rule(Rule* rule){
// free our rule and list 
  if (rule != NULL) {
    free(rule->key);
    free_list(rule->expansions);
  }
}

List* read_grammar(char* filename){
// Make read grammar list 
 List* grammar=make_list();
 // END OF TODO
 FILE* input_file = fopen(filename,"r");
 char buffer[1000];

 int number_of_expansions = 0;
 int buffer_index = 0;
 int number_of_rules = 0;

 for (char current = fgetc(input_file); current != EOF; current = fgetc(input_file)){
  if (current == ':'){
    char* key = calloc(buffer_index+1,sizeof(char));
    memcpy(key,buffer,buffer_index); 
   //TODO 4 
      // Initialize rule and add to rule to list 
    Rule* newRule= make_rule(key); 
    add(grammar, number_of_rules, newRule);
    number_of_rules++;
 // END OF TODO 4 
    buffer_index = 0;
  }
  else if (current == ',' || current == '\n'){

    char* expansion = calloc(buffer_index+1,sizeof(char));      
    memcpy(expansion,buffer,buffer_index);
     //TODO 4C
      // last rule and add last rule expansion 
    Rule* lastRule=get(grammar, grammar->size-1);   
    add(lastRule->expansions, number_of_expansions, expansion);
    if(current == '\n'){
      number_of_expansions = 0;
    }

     //END TODO 4C

    buffer_index = 0;

  }
  else {
    buffer[buffer_index] = current;
    buffer_index++;
  }
}
fclose(input_file);

  return grammar; // Simply return grammar
}



char* expand(char* text, List* grammar){
   /* 
   * BONUS TODO
   */

 List* splited= split(text, "#");
 List* expanded= make_list();
 for (int i = 0; i < splited->size; i++){
  if (i%2==0){
    add(expanded,expanded->size,copy_string(get(splited,i))); 
  } else {
   for (int k = 0; k < grammar->size; k++){
    Rule* index = get(grammar,k);  
    if(strcmp(get(splited, i), index->key)==0){ 
     char* b = get_random(index->expansions);
     char* recursive = expand(b, grammar);
     add(expanded, expanded->size, recursive);
   }
 }
}
}
char* final = join(expanded);
free_list(splited);
free_list(expanded);
return final;


 //  return NULL; 


  /*
   * BONUS TODO
   */
}

void print_grammar(List* grammar){

  for (int ii = 0; ii < grammar->size; ii++){
    Rule* rule = get(grammar,ii);
    for (int jj = 0; jj < rule->expansions->size; jj++){
      printf("A potential expansion of rule '%s' is '%s'\n",rule->key, (char*) get(rule->expansions,jj));
    }
  }

}

