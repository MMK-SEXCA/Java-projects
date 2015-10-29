
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
}
