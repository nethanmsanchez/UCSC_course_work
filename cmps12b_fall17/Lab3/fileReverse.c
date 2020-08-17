//--------------------------------
// Nethaniel Sanchez  Lab3
// Negmsanc
// fileReverse.c
// a program that reads input from a file and prints each string reversed and on its own line
// -------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void stringReverse(char* s);

int main(int argc, char* argv[]) {
	FILE* in;
	FILE* out;
	char word[256];

	//checks if there are the correct number of arguments (from lab3 manual)
	if(argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// opens file for reading (from lab3 manual)
	in = fopen(argv[1], "r");
	if(in==NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	// opens file for writing (from lab3 manual)
	out = fopen(argv[2], "w");
	if(out==NULL){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	// add description prints words
	while(fscanf(in, " %s", word) != EOF ){
		stringReverse(word);
		fprintf(out, "%s\n", word);
	}

	// closes files
	fclose(in);
	fclose(out);

	return(EXIT_SUCCESS);
}

void stringReverse(char* s){
	int length = strlen(s); // subtract one to get the correct value in array
	char holder;
	int counter = 0;
	while(length>counter){
		holder = s[length-1];
		s[length-1] = s[counter];
		s[counter] = holder;
		length = length -1;
		counter = counter +1;
	}
}

