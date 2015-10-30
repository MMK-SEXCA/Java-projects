
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World Trees");
		
		BinaryTreeNode node = new BinaryTreeNode(10);
		BinaryTree tree = new BinaryTree(node);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(60);
		System.out.println(tree.toString());
	}
}
