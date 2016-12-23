import java.util.ArrayList;

public class RootToLeafPaths {
	
	
	public static void main(String[] args) 
	{
		
		
		TreeNode root = new TreeNode(1);
		
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		DFSLeaf(root,path);
		
		
		
	}

	public static void DFSLeaf(TreeNode root, ArrayList<Integer> path) 
	{
		
		/*Store a path into an ArrayList. Use DFS. When done with current node, remove it from the ArrayList*/
		
		path.add(root.key);
		
		if(root.left!=null) 
		{
			
			DFSLeaf(root.left,path);
			
		}
		
		if(root.right!=null) 
		{
			
			DFSLeaf(root.right,path);
			
		}
		
		if(root.left==null && root.right==null)  
		{
			
			/*We've reached a leaf*/
			
			for(int i =0;i<path.size();i++) 
			{
				System.out.print(" "+path.get(i));
				
			}
			
			System.out.println();
			
			
		}
		
		
		path.remove(path.size()-1);
		
	}
}
