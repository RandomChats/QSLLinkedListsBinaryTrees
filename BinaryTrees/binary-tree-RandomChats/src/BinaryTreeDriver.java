
public class BinaryTreeDriver {
	static BinaryTree<Integer> tree = new BinaryTree<Integer>();

	public static void main(String[] args) {
		tree.addElement(10);
		tree.addElement(15);
		tree.addElement(20);
		tree.addElement(13);
		tree.addElement(12);
		tree.addElement(5);
		tree.addElement(3);
		tree.addElement(7);
		tree.addElement(2);
		tree.addElement(2);
		
		//Original Tree
		System.out.println(tree.toString());
		
		//Tree without 2
		tree.removeAllOccurrences(2);
		System.out.println(tree.toString());
		
		//Tree reset
		tree.addElement(2);
		tree.addElement(2);
		System.out.println();

		//Prints true for the element
		System.out.println(tree.contains(13));
		System.out.println();
		
		//Prints false for the element
		System.out.println(tree.contains(333));
		System.out.println();

		//Prints out the max
		System.out.println(tree.findMax());
		
		//This code tests all of the various methods I made in BinaryTree.java
		
	}
}
