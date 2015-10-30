
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World Trees");
		
		BinaryTreeNode node = new BinaryTreeNode(30);
		/*
		BinaryTree tree = new BinaryTree(node);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(60);
		*/
		BinarySearchTree tree = new BinarySearchTree(node);
		tree.insert(20);
		tree.insert(10);
		tree.insert(50);
		tree.insert(40);
		tree.insert(60);
		tree.insert(25);
		
		System.out.println(tree.toString());
		//System.out.println("Number of nodes in the tree : " + tree.numberOfNodes());
	}
}
