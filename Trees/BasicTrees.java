class TreeNode
{
	
	int key;

	TreeNode left=null;
	TreeNode right=null;

	public TreeNode(int key) 
	{
		
		
	this.key = key;	
		
		
	}
	
	public void setLeft(TreeNode node) 
	{
		
		this.left = node;
		
		
	}
	
	public void setRight(TreeNode node) 
	{
		
		this.right = node;
		
		
	}
	
	public TreeNode getLeft() 
	{
		
		return this.left;
		
		
	}
	
	public TreeNode getRight() 
	{
		
		return this.right;
		
		
	}
	
	
	public int getKey() 
	{
		
		return this.key;
		
	}

}

class Tree 
{
	
	private TreeNode root;
	
	
	public Tree(TreeNode node)
	{
		
		this.root = node;
		
	}
	
	public TreeNode getRoot() 
	{
		
		return this.root;
		
	}
	
	public int getMaxLevelSum() 
	{
		
		
		
		
		return 0;
	}
	
	public void printCornerNodes() 
	{
		
		System.out.print(this.root.getKey()+" ");
		
		TreeNode currentLeftNode = this.root;
		TreeNode currentRightNode = this.root;
		
		while(currentLeftNode.getLeft()!=null || currentRightNode.getRight()!=null ) 
		{
			
			if(currentLeftNode.getLeft()!=null) 
			{
				
				System.out.print(currentLeftNode.getLeft().getKey()+" ");
				currentLeftNode = currentLeftNode.getLeft();
				
			}
			
			else if(currentLeftNode.getLeft()==null && currentLeftNode.getRight()!=null && currentLeftNode!=this.root)
			{
				
				System.out.print(currentLeftNode.getRight().getKey()+" ");
				
			}
			
			if(currentRightNode.getRight()!=null) 
			{
				
				System.out.print(currentRightNode.getRight().getKey()+" ");
				currentRightNode = currentRightNode.getRight();
				
				
			}
			
			else if(currentRightNode.getRight()==null && currentRightNode.getLeft()!=null && currentRightNode!=this.root)
			{
				
				System.out.print(currentRightNode.getLeft().getKey()+" ");
				
			}
			
			
			
			
			
			
		}
		
		
		
	}

}


public class BasicTrees {

	
	public static void main(String [] args) 
	{
		
		Tree tree =  new Tree(new TreeNode(15));
		TreeNode root = tree.getRoot();
		root.setLeft(new TreeNode(10));
		root.setRight(new TreeNode(20));
		
		root.getLeft().setLeft(new TreeNode(8));
		root.getLeft().setRight(new TreeNode(12));
		
		root.getRight().setLeft(new TreeNode(16));
		root.getRight().setRight(new TreeNode(25));
		
		
		/*root.setRight(new TreeNode(2));
		root.getRight().setRight(new TreeNode(3));*/
		
		
		
		tree.printCornerNodes();
		
		
	}
	
}
