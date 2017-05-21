import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneLetterCombinations {
	
	
	
	public static List<String> letterCombinations(String digits) 
	
	{	String[] Two = {"a","b","c"};
		String[] Three = {"d","e","f"};
		String[] Four = {"g","h","i"};
		String[] Five = {"j","k","l"};
		String[] Six = {"m","n","o"};
		String[] Seven = {"p","q","r","s"};
		String[] Eight = {"t","u","v"};
		String[] Nine = {"w","x","y","z"};
		
		ArrayList<String> toReturn = new ArrayList();
		if(digits.length()==0) return toReturn;
		
		if(digits.length()==1) 
		{
			if(digits.equals("2")) 
			{
				for(int  i =0; i<Two.length;i++) 
				{
					
					toReturn.add(Two[i]);
					
				}
				
				return toReturn;
			}
			if(digits.equals("3")) 
			{
				for(int  i =0; i<Three.length;i++) 
				{
					
					toReturn.add(Three[i]);
					
				}
				
				return toReturn;
			}
			
			if(digits.equals("4")) 
			{
				for(int  i =0; i<Four.length;i++) 
				{
					
					toReturn.add(Four[i]);
					
				}
				
				return toReturn;
			}
			
			if(digits.equals("5")) 
			{
				for(int  i =0; i<Five.length;i++) 
				{
					
					toReturn.add(Five[i]);
					
				}
				
				return toReturn;
			}
			
			if(digits.equals("6")) 
			{
				for(int  i =0; i<Six.length;i++) 
				{
					
					toReturn.add(Six[i]);
					
				}
				
				return toReturn;
			}
			
			if(digits.equals("7")) 
			{
				for(int  i =0; i<Seven.length;i++) 
				{
					
					toReturn.add(Seven[i]);
					
				}
				
				return toReturn;
			}
			
			if(digits.equals("8")) 
			{
				for(int  i =0; i<Eight.length;i++) 
				{
					
					toReturn.add(Eight[i]);
					
				}
				
				return toReturn;
			}
			if(digits.equals("9")) 
			{
				for(int  i =0; i<Nine.length;i++) 
				{
					
					toReturn.add(Nine[i]);
					
				}
				
				return toReturn;
			}
			
			
			
		}
		
		String first = digits.charAt(0) +"";
		String remaining  = digits.substring(1, digits.length());
		
		List<String> firstList = letterCombinations(first);
		List<String> remainingList = letterCombinations(remaining);
		List<String> finalList = new ArrayList<String>();
		
		for(int i=0; i<firstList.size();i++) 
		{
			for(int j=0; j<remainingList.size();j++) 
			{
				finalList.add(firstList.get(i) +""+remainingList.get(j));
				
			} 
			
		}
		
		
		
		
		
		return finalList;
        
    }
	
	public static void main(String[] args) {
		
		
		String in = "4";
		System.out.println(letterCombinations(in));
		
	}
	

	
	
}
