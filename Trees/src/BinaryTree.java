import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	private BinaryTreeNode root;
	private Queue<BinaryTreeNode> q;

	public BinaryTree(BinaryTreeNode root) {
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
    
    public int numberOfNodes(){
    	return numberOfNodes(this.root);
    }
    
    private int numberOfNodes(BinaryTreeNode node){
    	int leftCount =  node.left == null? 0 : numberOfNodes(node.left);
    	int rightCount = node.right == null? 0 : numberOfNodes(node.right);
    	return 1 + leftCount + rightCount;
    }
    
    public void inOrderTraversal(BinaryTreeNode node){
    	if(node != null){
    		inOrderTraversal(node.left);
    		System.out.print(node.data+" ");
    		inOrderTraversal(node.right);
    	}
    }
    
    
    public void postOrderTraversal(BinaryTreeNode node){
    	if(node != null){
    		postOrderTraversal(node.left);
    		postOrderTraversal(node.right);
    		System.out.print(node.data+" ");
    	}
    }
    
    public void preOrderTraversal(BinaryTreeNode node){
    	if(node != null){
    		System.out.print(node.data+" ");
    		preOrderTraversal(node.left);
    		preOrderTraversal(node.right);
    	}
    }
    
    public void levelOrderTraversal(BinaryTreeNode node){
    	Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
    	q.add(node);
    	while(!q.isEmpty()){
    		System.out.print(q.peek().data + " ");
    		if(q.peek().left != null)
    			q.add(q.peek().left);
    		if(q.peek().right != null)
    			q.add(q.peek().right);
    		q.poll();
    	}
    }
    
    public boolean searchBinaryTree(int data){
    	Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
    	q.add(this.getRoot());
    	while(!q.isEmpty()){
    		if(q.peek().data==data){
    			return true;
    		}else{
    			if(q.peek().left != null)
    				q.add(q.peek().left);
    			if(q.peek().right != null)
    				q.add(q.peek().right);
    		}
    		q.poll();
    	}
    	return false;
    }
    
    public boolean searchBinaryTreeRecursive(BinaryTreeNode node,int data){
    	if(node == null){
    		return false;
    	}else if(node.data==data){
    		return true;
    	}else{
    		return searchBinaryTreeRecursive(node.left,data)||searchBinaryTreeRecursive(node.right,data);
    	}
    }
    
    // Returns the depth of this binary tree. The depth of a binary tree is the
 	// length of the longest path from this node to a leaf. The depth of a
 	// binary tree with no descendants (that is, just a leaf) is zero.
    public int maxDepthBinaryTreeRecursive(BinaryTreeNode node){
    	if(node == null){
    		return 0;
    	}else{
    		return 1+ Math.max(maxDepthBinaryTreeRecursive(node.left),maxDepthBinaryTreeRecursive(node.right));
    	}
    }
}
