
public class LinkedListCollision {

	
	public static void main(String[] ars) 
	{
		
		LLNode first = new LLNode(2);
		first.next = new LLNode(4);
		first.next.next = new LLNode(5);
		first.next.next.next = new LLNode(6);
		first.next.next.next.next = new LLNode(7);
		first.next.next.next.next.next = new LLNode(8);
		first.next.next.next.next.next.next = new LLNode(9);
		first.next.next.next.next.next.next.next = new LLNode(10);
		first.next.next.next.next.next.next.next.next = first.next.next.next;
		
		/* Now , its time to make a slow and fast pointer	*/
		LLNode slow=first, fast=first; /*slow moves one node at a time, fast moves two nodes at a time*/
		
		while(true) 
		{
			fast = fast.next.next;
			slow = slow.next;
			
			if(fast==slow)
			{
				System.out.println("Cycle Detected");
				break;/*fast and slow pointers are now equal. Cycle detected*/
			}
		}
		/*Cycle detected*/
		
		int circ=0; 
		
		while(true) 
		{
			slow= slow.next;
			circ++;
			if(slow==fast)
			{
				System.out.println("Circumference is "+circ);
				break;
			}
			
			
			
		}
		int i=1;
		LLNode one = first;
		LLNode two = first;
		while(i<circ) 
		{
			two =two.next;
			i++;
			
		}
		System.out.println(two.data);
		while(one!=two.next) 
		{
			
			one = one.next;
			two=two.next;
			
			
		}
		
		System.out.println("The loop starts at : "+ one.data);
	}
}
