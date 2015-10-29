
public class ListNode {
	public ListNode next;
	public int data;
	
	//default Constructor.
	public ListNode(){
		next = null;
		data = Integer.MIN_VALUE;
	}
	
	//Specific Constructor.
	public ListNode(int data){
		next = null;
		this.data = data;
	}
	
	//returns the next node.
	public ListNode getNext(){
		return this.next;
	}
	
	//sets the node that follows.
	public void setNext(ListNode node){
		this.next = node;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
	//serialization to string.
	public String toString(){
		return Integer.toString(this.data);
	}
}
