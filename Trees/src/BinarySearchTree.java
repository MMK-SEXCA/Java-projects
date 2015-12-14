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
     
     //
     public BinaryTreeNode extractLeafList(BinaryTreeNode node, BinaryTreeNode list_node){
     	if(node == null)
     		return null;
     	if(node.left == null && node.right == null){
     		if(list_node.data == Integer.MIN_VALUE){
     			list_node.data = node.data;
     			list_node.left = null;
     			list_node.right = null;
     			return list_node;
     		}else{
     			BinaryTreeNode newnode = new BinaryTreeNode(node.data);
     			list_node.right = newnode;
     			newnode.left = list_node;
     			return newnode;
     		}
     	}else{
     		return extractLeafList(node.right,extractLeafList(node.left,list_node));
     	}
     }
}
