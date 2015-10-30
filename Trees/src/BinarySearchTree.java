import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
	private BinaryTreeNode root;

	public BinarySearchTree(BinaryTreeNode node) {
		this.root = node;
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
    
    public void insert(int data){
    	this.insert(this.getRoot(),data);
    }
    
    /* Function to insert data in binary search tree.*/
    private BinaryTreeNode insert(BinaryTreeNode node,int data){	
    	if(node == null){
    		BinaryTreeNode newnode = new BinaryTreeNode(data);
    		newnode.left = null;
    		newnode.right = null;
    		return newnode;
    	}
    	else{
    		if(data <= node.data){
    			node.left = insert(node.left,data);
    			return node;
    		}else{
    			node.right = insert(node.right,data);
    			return node;
    		}
    	}
    }     
}
