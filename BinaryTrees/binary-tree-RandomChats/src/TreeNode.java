/**
 * 
 * @author corbin.mclaughlin
 *
 */
public class TreeNode<T> {
	private TreeNode<T> left, right;
	private T element;
	
	/**
	 * sets up the basic TreeNode elements
	 */
	public TreeNode() {
		left = null;
		right = null;
		element = null;
	}
	
	/**
	 * 
	 * @param elem sets the element of the node
	 */
	public TreeNode(T elem) {
		left = null;
		right = null;
		element = elem;
	}
	
	/**
	 * 
	 * @return the element of the TreeNode
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * @return the left element that is connected to the element
	 */
	public TreeNode<T> getLeft() {
		return left;
	}
	
	/**
	 * @return the right element that is connected to the element
	 */
	public TreeNode<T> getRight() {
		return right;
	}
	
	/**
	 * @param node sets the left node to the user input
	 */
	public void setLeft(TreeNode<T> node) {
		left = node;
	}
	
	/**
	 * 
	 * @param node sets the right node to the user input
	 */
	public void setRight(TreeNode<T> node) {
		right = node;
	}
	/**
	 * 
	 * @param elem sets the element of the tree node
	 */
	public void setElement(T elem) {
		element = elem;
	}


}
