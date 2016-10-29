/* Code to determine the rank of a string amongst all its permutations in lexicographic order */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define NO_OF_CHARS 256

int factorial(n) 
{

int fact=1;
int i=1;

for(i=1;i<=n;i++) 
{

fact=fact*i;

}
return fact;

}

int rankOf (char* input, int length) 
{

	int numAhead = 0 ;
	int keyChar = *input;

	int i=0;
	while(*(input+i)!='\0') 
	{

		if(*(input+i)<keyChar) {  numAhead++; }

		i++;

	}

return numAhead;
}

int* createCountArray(char* input) 
{

	int * countArray = (int*)calloc(NO_OF_CHARS,sizeof(int));

	for(int i=0;*(input+i)!='\0';i++) 
	{

		countArray[*(input+i)]++;

	}


 	return countArray;


}



int main() 
{
	
	char input[] = "strxoing";	
	int rank=0;
	
	int * countArray = createCountArray(input);
	
	int numAheadOfFirst = rankOf(input,strlen(input));
	
	

	rank = numAheadOfFirst * factorial(strlen(input)-1);

	

	for(int i=1;i<strlen(input);i++) 
	{

		rank = rank + rankOf((input+i),(strlen(input)-i)) * factorial(strlen(input)-i-1);

	}

	rank++;

	printf("%d\n",rank);


return 0;
}