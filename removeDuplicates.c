#include<stdio.h>
#include<stdlib.h>
#include<string.h>

	 void removeDups(char *str,int j, int start,int flag) 
	{
		/*Check if start character is repeating or not. If yes , then loop till you find
		 * another character. Pass that characters index(new start) into a recursive call*/
		
		if(start == strlen(str)-1) 
		{
			if(flag!=1)
			{
				str[j] = str[start];
				j++;
				
			}
			if(j<=strlen(str)-1) 
			{
				
				
				str[j] = '\0';
				
				
				
			}
			
		}
		
		while(start<strlen(str)-1 && str[start]!='0') 
		{
			
			if(str[start+1]!=str[start]) 
			{
				
				str[j] = str[start];
				
				start++;
				j++;
				if(start==strlen(str)-1) {removeDups(str,j,start,flag);}
			}
			
			
			else 
			{
				char ref = str[start];
				
				while(str[start]==ref) 
				{
					
					if(start<strlen(str)-1)
					{
						start++;
					}
					else 
					{
						flag =1;
						break;
						
					}
					
				}
				
				removeDups(str,j,start,flag);
				return;
				
			}
			
		}
		

		 
		
		
	}

int main() 
{
	

	char str[] = "abccdrrfeg";


	removeDups(str,0,0,0);
	removeDups(str,0,0,0);
	
	for(int i=0;str[i]!='\0';i++) 
	{

		printf("%c",str[i]);
	}
	printf("\n");
}
	
	
	
	



