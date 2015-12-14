
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World Trees");
		
		BinaryTreeNode node = new BinaryTreeNode(10);
		/*
		BinaryTree tree = new BinaryTree(node);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(60);
		*/
		//BinarySearchTree tree = new BinarySearchTree(node);
		BinaryTree tree = new BinaryTree(node);
		node.insertLeft(20);
		node.insertRight(30);
		node.getLeft().insertLeft(40);
		node.getLeft().insertRight(50);
		node.getLeft().getLeft().insertLeft(60);
		node.getLeft().getLeft().insertRight(70);
		//node.getLeft()
		//tree.insert(20);
		//tree.insert(10);
		//tree.insert(50);
		//tree.insert(40);
		//tree.insert(60);
		//tree.insert(25);
		
		System.out.println(tree.toString());
		tree.setRoot(function(tree.getRoot(),null,null));
		System.out.println(tree.toString());
		//System.out.println("Number of nodes in the tree : " + tree.numberOfNodes());
		/*
		tree.inOrderTraversal(tree.getRoot());
		System.out.println();
		tree.preOrderTraversal(tree.getRoot());
		System.out.println();
		tree.postOrderTraversal(tree.getRoot());
		System.out.println();
		tree.levelOrderTraversal(tree.getRoot());
		System.out.println();
		*/
		//System.out.println("element found is : "+tree.searchBinaryTree(23));
		System.out.println("depth of the tree : "+tree.maxDepthBinaryTreeRecursive(tree.getRoot()));
		BinaryTreeNode node2 = new BinaryTreeNode(Integer.MIN_VALUE);
		//node2 = tree.extractLeafList(tree.getRoot(),node2);
		//System.out.println(node2.data+" "+node2.right.data);
	}
	
	public static BinaryTreeNode function(BinaryTreeNode parent, BinaryTreeNode node1, BinaryTreeNode node2) {
		BinaryTreeNode temp1 = parent.getLeft();
		BinaryTreeNode temp2 = parent.getRight();
		parent.left = node1;
		parent.right = node2;
		if(temp1 != null){
			return function(temp1, parent, temp2);
		}else{
			return parent;
		}
	}
}
