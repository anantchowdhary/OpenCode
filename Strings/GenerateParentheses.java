import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	
	
	 public static List<String> generateParenthesis(int n) 
	        
	    
	{
		List<String> toReturn = new ArrayList<String>();
		
		if(n==0) 
		{
			
			toReturn.add("");
			return toReturn;
			
		}
		if(n==1) 
		{
			
			
			toReturn.add("()");
			return toReturn;
			
		}
		
		
		

		
		/* Now varying the spacing between parenthesis*/
		
		
		
		for(int space=0; space<n; space++) 
		{
			
			List<String> sub_space = generateParenthesis(space);
			List<String> sub_remaining = generateParenthesis(n - space -1);
			List<String> toReturnIntermediate_space = new ArrayList<String>();
			/* Make the part with ( ..generateParenthesis of spacing... ) */
			
			for(int i=0; i<sub_space.size(); i++) 
			{
				
				String mod = "(" + sub_space.get(i) + ")";
				toReturnIntermediate_space.add(mod);
				
			}
			
		
			/* Add the part with ( ..generateParenthesis of spacing... ) to the remaining portion */
			
			for(int i=0; i< toReturnIntermediate_space.size(); i++) 
			{
				for(int j=0; j< sub_remaining.size();j++) 
				{
					
					toReturn.add(toReturnIntermediate_space.get(i) + sub_remaining.get(j));
				}
				
				
			}
			
			
			
		}
		
		return toReturn;
		
		
	}
	
	public static void main(String[] args) 
	{
		
		int n = 2;
		
		System.out.println(generateParenthesis(n));
		
		
		
	}

}
