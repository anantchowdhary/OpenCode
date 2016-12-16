#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/*Code to remove 'ac' and 'b' from a string, in just one pass.*/
int main() 
{
	
	char str[] ="ababaac";
	
	int size = strlen(str), j=0,i=0;

	while(i<size) 
	{
		
		if(str[i]=='b')
		{

			i++;
			continue;

		}

		else if(str[i]=='a') 
		{

			if(str[i+1]=='c')
			{
				
				i=i+2;
				continue;

			}

		}

		str[j]=str[i];
		i++;
		j++;



	}
	str[j]='\0';

	printf("%s\n",str);

	return 0;
}

