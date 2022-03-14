/**
 * LinearNode represents a node in a linked list.
 *
 * @author Java Foundations, mvail, Erickson
 * @version 4.0
 */
public class LinearDoubleNode<E> {
	private LinearDoubleNode<E> next, previous;
	private E element;

	/**
  	 * Creates an empty node.
  	 */
	public LinearDoubleNode() {
		next = null;
		previous = null;
		element = null;
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public LinearDoubleNode(E elem) {
		next = null;
		previous = null;
		element = elem;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public LinearDoubleNode<E> getNext() {
		return next;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(LinearDoubleNode<E> node) {
		next = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}
	
	
	public LinearDoubleNode<E> getPrevious() {
		return previous;
	}
	
	public void setPrevious(LinearDoubleNode<E> node) {
		previous = node;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null);
	}
}
