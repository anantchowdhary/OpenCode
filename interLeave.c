#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/*  Here we approach the problem by finding slots for characters in str1. Then the left over slots are 
occupied by characters of str2. We're essentially solving this the same way we solve permutations
and combinations problems by hand. We're using backtracking here! */

void getPosition(char* a , char*str1,char*str2,int l , int charNumber) 
{

/*Finding a place for the charNumber'th character*/

	int ctr=0;

	

 	if(charNumber>strlen(str1)) 
 	{
 		
 		//Fill in other empty positions using str2 , print all and then return

 		for(int i=0; i<(strlen(str1)+strlen(str2)); i++) 
 		{
 			int changed =0;
 			if(*(a+i)=='0') 
 			{

 				*(a+i) = *(str2+ctr);
 				
 				ctr++;
 				changed =1;

 			}

 			printf("%c",*(a+i));
 			
 			if(changed==1)
 			{
 				*(a+i)='0'; 

 				/*If this element had changed, we've printed it out , 
 				now we need to change it back to '0'.This is so that the 
 				array retains its original values, so that values of str1 can be 
 				plugged into other places too*/

 			}

 		}

 		

 	
 	printf("\n");
 	return;

 	}

 	if( l > (strlen(str1)+strlen(str2)-1) ) return;

 	int rightLim = strlen(str2)+charNumber-1;
 	
 	for(int i=l; i<=rightLim; i++) 
 	{

 		

 		*(a+i) = *(str1+(charNumber-1));  /* We've found the test index for the charNumber'th character */
 		

 		getPosition(a,str1,str2,(i+1),(charNumber+1));

 		

 		*(a+i) = '0';   /*Resetting a[i] = '0'. Essentially backtracking */

 	}


}


int main() 
{
	

	char str1[] = "AB";
	char str2[] = "CD";

	int size1 = strlen(str1);
	int size2 = strlen(str2);

	char * a = (char*) malloc((size1+size2)*sizeof(char));

	for(int i =0; i<(size1+size2); i++) 
	{


		*(a+i) = '0';


	}


	getPosition(a,str1,str2,0,1); 


}