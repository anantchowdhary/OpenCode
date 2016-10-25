/* This is a piece of code to remove frmo the input, characters in a string called the mask string 


Author : Anant Chowdhary

*/


#include<stdio.h>
#include<stdlib.h>
#define NO_OF_CHARS 256

char* removeDirtyChars(char* input, char* mask) 
{
	char* result = NULL;
	int* countArray = (int*) calloc(NO_OF_CHARS,sizeof(int));

	int i=0;
	while(*(mask+i)!='\0') 
	{

		countArray[*(mask+i)]++;
		i++;

	}
	
	i=0; // i is the counter(overall)
	int ctr=0; // ctr is the new string's counter

	while(*(input+i)!='\0')
	{

		if(countArray[*(input+i)]==0) 

		{
			//printf("The current character is %c\n",*(input+i));
			input[ctr]=*(input+i);
			ctr++;
		}
		else 
		{

			//printf("The character being omitted is %c\n",*(input+i));

		}

		i++;

	}

	*(input+ctr) = '\0';

	return input;




}

int main() 
{
	

	char input[] = "This is a string";
	char mask[]  = "mask";

	printf("The masked string is : %s\n",removeDirtyChars(input,mask));





}