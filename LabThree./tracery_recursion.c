#include <stdio.h>
#include <stdlib.h>

char* make_string_from(char* from, int count) {// new make string 
	/* TODO 2 */
	char* string = calloc(count+1,sizeof(char)); 
	for (int c = 0; c<count; c++) 
		string[c]=from[c]; 
	return string;
	/* TODO 2 */
} // make string frome

int main(int argc, char** argv) { // main 
	/* TODO 1 */
	for (int c = 0; c<argc; c++) 
		printf("%s\n", argv[c]); // print given value abc def
 /* TODO 1 */
/* TODO 3 */
	char char_buffer[1000]; // max length of char 
	char* rule=NULL;  // leave as null new rule
	char* expansion=NULL; // leave as null new expanision
	int buffer_index= 0; 
	char c;  
	while(((c = getchar())!= EOF)){
		char_buffer[buffer_index] = c; 
		if(c==':'){
			rule =make_string_from(char_buffer,buffer_index); // pass rule 
			buffer_index=0; // must set to zero 
			for(int i=0; i<1000;i++){ // must clear char for consistency, change if char buffer max is ever changed
				char_buffer[i] = 0; 
			}
		}
		else if(c==','|| c=='\n'){
			expansion= make_string_from(char_buffer,buffer_index);
			printf("A possible expansion of %s is %s \n", rule, expansion);
			buffer_index=0; // must set to zero 
			for(int i=0; i<1000;i++){ // must clear char for consistency, change if char buffer max is ever changed
				char_buffer[i] = 0; 
			}
			//DO NOT DELETE, PREVENTS MEMORY LEAKS
			free(expansion); // free expansion and set to null 
			expansion = NULL;
			if( c=='\n'){
				free(rule);// free rule and set to null
				rule = NULL;
			}

		}
		else { //Otherwise add to index
			buffer_index++; 
		}
	}
 	/* TODO 3 */
}
