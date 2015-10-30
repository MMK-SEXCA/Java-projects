import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	private BinaryTreeNode root;
	private Queue<BinaryTreeNode> q;

	public BinaryTree(BinaryTreeNode root) {
		super();
		this.root = root;
		q = new LinkedList<BinaryTreeNode>();
		q.add(root);
	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	
	/* Function to check if tree is empty */
    public boolean isEmpty(){
        return root == null;
    }
    
    public String toString(){
		return this.root.toString();
    }
    
    /* Function to insert data in level order.*/
    public void insert(int data){	
    	Queue<BinaryTreeNode> tempq = new LinkedList<BinaryTreeNode>();
        tempq.add(this.root);
        while(!tempq.isEmpty()){
        	BinaryTreeNode tempNode = tempq.poll();
        	if(tempNode.left == null){
        		BinaryTreeNode node = new BinaryTreeNode(data);
        		tempNode.left = node;
        		q.add(node);
        		return;
        	}else if(tempNode.right == null){
        		BinaryTreeNode node = new BinaryTreeNode(data);
        		tempNode.right = node;
        		q.add(node);
        		return;
        	}else{
        		tempq.add(tempNode.left);
        		tempq.add(tempNode.right);
        	}
        }
    }     
}
