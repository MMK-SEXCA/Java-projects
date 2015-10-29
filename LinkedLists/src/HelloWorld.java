
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
		
		ListNode node1 = new ListNode();
		ListNode node2 = new ListNode();
		ListNode node3 = new ListNode();
		node1.setData(10);
		node2.setData(20);
		node3.setData(30);
		node1.setNext(node2);
		node2.setNext(node3);
		
		LinkedList l = new LinkedList();
		l.setHead(node1);
		System.out.println(l.toString());
		//l.removeMatched(new ListNode(10));
		//l.remove(1);
		ListNode x = l.removeFromEnd();
		System.out.println(l.toString());
	}

}
