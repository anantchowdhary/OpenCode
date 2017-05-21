
public class SortLinkedList {
	
	
	public static LLNode sortLL(LLNode head, int start, int end) 
	{

		if(start>= end) return head;
		
		int mid = (start + end)/2;
		LLNode curr = head;
		LLNode temp_head = head;
		int Head = start;
		while(Head<mid) 
		{
			Head++;
			curr = curr.next;
			
			
		}
		
		LLNode MidElementNext = curr.next;
		
		LLNode LeftHead = sortLL(temp_head, start, mid); // Sort part of list from start to mid
		LLNode RightHead = sortLL(MidElementNext, mid+1, end);    //  Sort part of list from (mid + 1) to end
		
		/*Merge the resulting lists*/
		int start_left = start; //left start
		int start_right = mid + 1; // right start
		
		LLNode NewHead;
		
		if(LeftHead.data > RightHead.data) 
		{
			
			NewHead = RightHead;
			RightHead = RightHead.next;
			start_right++;
		}
		else 
		{
			NewHead = LeftHead;
			LeftHead = LeftHead.next;
			start_left++;
			
		}
		
		
		
		curr = NewHead;
		
		
		while((start_left<=mid) || (start_right <= end) )
		{
			
			if((start_left<=mid) && (start_right <= end)) 
			{
				if(LeftHead.data > RightHead.data) 
				{
					
					curr.next = RightHead;
					RightHead = RightHead.next;
					curr = curr.next;
					start_right++;
					
				}
				else 
				{
					
					curr.next = LeftHead;
					LeftHead = LeftHead.next;
					curr = curr.next;
					start_left++;
				}
			}
			
			if((start_left<=mid) && (start_right > end)) 
			{
				
				curr.next = LeftHead;
				LeftHead = LeftHead.next;
				curr = curr.next;
				start_left++;
			}
			
			if((start_left>mid) && (start_right <= end)) 
			{
				
				curr.next = RightHead;
				RightHead = RightHead.next;
				curr = curr.next;
				start_right++;
			}
			
		
			
		}
		
		
		return NewHead;
		
		
	}
	
	public static void main(String[] args) 
	{
		
		LLNode head = new LLNode(2);
		head.next = new LLNode(1);
		/*head.next.next = new LLNode(0);
		head.next.next.next = new LLNode(-1);
		head.next.next.next.next = new LLNode(3);
		*/
		
		
		int start=0 ,end = -1;
		LLNode temp_head = head;
		
		while(temp_head!=null) 
		{
			end++;
			temp_head = temp_head.next;
			
		}
		
		
		head = sortLL(head, start,end);
		
		while(start<=end) 
		{
			
			System.out.print(head.data+" ");
			head = head.next;
			start++;
			
		}
		
		
	}

}
