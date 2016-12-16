
/*Assumes wild card characters ? and *
 *  * = any character(s)
 *   ? = a single character */
public class StringMatching {
	
	
	public static void main(String[] args) 
	{
		
		
		StringBuilder s1 = new StringBuilder("*??*c*d");
		StringBuilder s2 = new StringBuilder("abxxcd");
		
		System.out.println(compareStrings(s1,s2));
		
		
		
		
	}
	public static boolean compareStrings(StringBuilder first, StringBuilder second) 
	{
		/*Compare strings character by character. However, the characters may not be compared one by one
		 * one character in the first may correspond to one or more characters in the second string*/
		
		
		char last='0';
		while(!(first.length()==0)) 
		{
			
			if(first.charAt(0)!='*' && first.charAt(0)!='?') 
			{
				if(second.length()<1) {return false;}
				if(first.charAt(0) == second.charAt(0)) 
				{
					last = first.charAt(0);
					first.deleteCharAt(0);
					
					second.deleteCharAt(0);
					
				}
				
				else {return false;}
				
			}
			
			else if (first.charAt(0)=='?') 
			{
				if(first.length()>1)
				{
					if(second.length()<1) 
					{
						return false;
				
					}
				}
				
				else //first.length()==1
				{
					if(second.length()!=1) 
					{
						
						return false;
						
					}
					
				}
				last=first.charAt(0);
				first.deleteCharAt(0);
				second.deleteCharAt(0);
				
			}
			
			else if(first.charAt(0)=='*') 
			{
				
				if(first.length()>1) 
				{
					
					if(first.charAt(1)=='?') 
					{
						/*Count the number of consecutive ?'s and then 
						 * see the substring right after that
						 * till the next wild card. Then see if the second string has at least
						 * that many characters. And then perform the usual deletions*/
						int count=0,i;
						for(i=1;i<first.length();i++) 
						{
							if(first.charAt(i)=='?')
							{
								count++;
							}
							
							if(first.charAt(i)!='?'&&first.charAt(i)!='*') {break;}
							
						}
						
						/*Now we have a count of the number of ?'s. And we know where the next
						 * non wild card character is*/
						
						int min;
						
						
						if(first.indexOf("*", i)>0&&first.indexOf("?", i)>0)
						{
							 min = Math.min(first.indexOf("*", i), first.indexOf("?", i));
						}
						
						else if(first.indexOf("*", i)>0 && first.indexOf("?", i)<0) 
						{
							min = first.indexOf("*", i);
						}
						
						else if(first.indexOf("*", i)<0 && first.indexOf("?", i)>0)
						{
							
							min = first.indexOf("?", i);
							
						}
						
						else
						{
							
						min = first.length()-1;	
						}
						
						String ref = first.substring(i, min);
						
						int index = second.indexOf(ref,count-1);// Essentially checks if there are
						//at least count characters before the next set of characters is matched
						
						if(index<0) {return false;}
						
						
						else 
						{
							last=first.charAt(i-1);
							first.delete(0, i);
							second.delete(0, index);
							
						}
						
					
						
						
						
						
						continue;
						
					}
					
					if(second.length()<1) {return false;}
					
					//If * isn't the last character of first string
					last = first.charAt(0);
					first.deleteCharAt(0);
					int max= Math.min(first.indexOf("*"), first.indexOf("?"));
					if(first.indexOf("*")>0 && first.indexOf("?")<0)
					{
						max=first.indexOf("*");
						
					}
					else if(first.indexOf("*")<0 && first.indexOf("?")>0) 
					{
						max=first.indexOf("?");
						
					}
					if(max<0) 
					{
						
					max=first.length();
						
					}
					String ref= first.substring(0,max);
					int index = second.indexOf(ref); 
					if(index<0)
					{
				
						return false;
				
					}
					
					
					second.delete(0,index);
				}
				
				else 
				{
					last=first.charAt(0);
					first.deleteCharAt(0);
				}
				
				
			
			}
			
		}
		if(first.length()==0 && second.length()!=0) 
		{
			
			if(last!='*') {return false;}
		}
		return true;
	}

}
