
public class LinkedList {
	
	public ListNode head;
	private int length;
	
	// default constructor: 
	public LinkedList() {
		length = 0;
	}

	public ListNode getHead() {
		return head;
	}

	public void setHead(ListNode head) {
		this.head = head;
	}

	public void insertAtBegin(ListNode node){
		node.setNext(this.head);
		this.head = node;
		length++;
	}
	
	public void insertAtEnd(ListNode node){
		if(head == null){
			this.setHead(node);
		}else{
			ListNode p,q;
			for(p=this.head;(q = p.getNext()) != null;p=q);
			p.setNext(node);
		}
	}
	
	// Remove the value at a given position.
	// If the position is less than 0, remove the value at position 0.
	// If the position is greater than 0, remove the value at the last position.
	public void remove(int position) {
		// fix position
		if (position < 0) {
			position = 0;
		}
		
		if (position >= length) {
			position = length-1;
		}
		
		// if nothing in the list, do nothing
		if (head == null)
			return;
		
		// if removing the head element...
		if (position == 0) {
			head = head.getNext();
		}
		// else advance to the correct position and remove
		else {
			ListNode temp = head;
			for (int i=1; i<position; i+=1) {
				temp = temp.getNext();
			}
			temp.setNext(temp.getNext().getNext());
		}
		// reduce the length of the list
		length -= 1;
	}
	
	//remove and return the node at the end of the list.
	public ListNode removeFromEnd(){
		if(this.head == null)
			return null;
		ListNode p,q;
		p = this.head;
		q = this.head.next;
		if(q == null){
			this.head = null;
			length--;
			return p;
		}else{
			while(q.next != null){
				p = q;
				q = q.next;
			}
			p.next = null;
			length--;
			return q;
		}
	}
	// Remove a node matching the specified node from the list.  
	// Use equals() instead of == to test for a matched node.
	public void removeMatched(ListNode node) {
		if (head == null) 
			return;
		if (node.data == head.data) { 
			head = head.getNext(); 
			// reduce the length of the list
			length-=1;			
			return;
		}
		ListNode p = head, q = null;
		while((q = p.getNext()) != null) {
			if (node.data == q.data) {
				p.setNext(q.getNext());
				// reduce the length of the list
				length-=1;				
				return;
			}
			p = q;
		}
	}	
	
	// Return a string representation of this collection, in the form ["str1","str2",...].
	public String toString() {
		String result = "[";
		if (head == null) {
			return result+"]";
		}
		result = result + head.getData();
		ListNode temp = head.getNext();
		while (temp != null) {
			result = result + "," + temp.getData();
			temp = temp.getNext();
		}
		return result + "]";
	}
	
	// Size of the list.
	public boolean isEmpty(){
		return length==0;
	}
	
	// Remove everything from the list.
	public void clearList(){
		head = null;
		length = 0;
	}
}
