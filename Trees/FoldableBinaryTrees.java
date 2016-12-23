
public class FoldableBinaryTrees {
	
	public static void main(String[] args) 
	{
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(7);
		root.right = new TreeNode(15);
		
		root.left.left = new TreeNode(9);
		root.left.right = new TreeNode(11);
		
		root.right.left = new TreeNode(12);
		root.right.right= new TreeNode(100);
		
		System.out.println(isMirror(root.left,root.right));
		
		
		
	}
	
	public static boolean isMirror(TreeNode n1, TreeNode n2) 
	{
		
		/*Checking if trees rooted at n1 and n2 are mirror images of each other*/
		if(n1==null && n2==null) {return true;}
		if(n1 == null && n2!=null) {return false;}
		if(n2==null && n1!=null) {return false;}
		
	
		
		return isMirror(n1.right, n2.left) && isMirror(n1.left, n2.right);
		
	}

}
