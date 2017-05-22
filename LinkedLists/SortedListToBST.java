
public class SortedListToBST {
	
	 public static TreeNode sortedListToBST(ListNode head) {
		
		 if(head==null) return null;
		 if(head.next==null) 
		 {
			 return new TreeNode(head.val);
			 
		 }
		 int length=0;
		 ListNode curr = head;
		 while(curr!=null) 
		 {
			 length++;
			 curr  = curr.next;
		 }
		 
		 int mid = length/2;
		 
		 ListNode Head = head;
		 curr = head;
		 ListNode prev = curr;
		 int counter=0;
		 while(counter!=mid) 
		 {
			 prev = curr;
			 curr = curr.next;
			 counter++;
			 
			 
		 }
		 ListNode Mid = curr;
		 ListNode MidNext = curr.next;
		 
		 prev.next = null;
		 
		 TreeNode root = new TreeNode(Mid.val);
		 
		 root.left = sortedListToBST(Head);
		 root.right = sortedListToBST(MidNext);
		 
		 return root;
	        
	    }
	 
	 public static void main(String[] args) 
	 {
		 ListNode Head = new ListNode(4);
		 Head.next = new ListNode(5);
		 /*Head.next.next = new ListNode(3);
		 Head.next.next.next = new ListNode(4);
		 Head.next.next.next.next = new ListNode(5);*/
		 TreeNode root = sortedListToBST(Head);
		 System.out.println(root.key);
		 System.out.println(root.left.key);
		// System.out.println(root.right.key);
		 //System.out.println(root.right.right.key);
		 
		 
	 }

}
