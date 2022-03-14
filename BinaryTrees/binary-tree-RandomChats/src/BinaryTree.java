import java.util.*;

/**
 * 
 * @author Corbin McLaughlin
 *
 * @param <T>
 */

public class BinaryTree<T extends Comparable<T>> implements BinarySearchTreeADT<T> {
	
	private TreeNode<T> current, root, globalPrevious;
	private int size;
	
	public BinaryTree() {
		root = null;
		size = 0;
	}
	
	@Override
	public T getRootElement() {
		return root.getElement();
	}
	
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T targetElement) {
		boolean nullChecker = false;
		current = root;
		while (!nullChecker) {

			if (targetElement.compareTo(current.getElement()) == 1) {
				//Element > Current Go Right
				if (current.getRight() == null) {
					return false;
				} else {
					current = current.getRight();
				}
			} else if (targetElement.compareTo(current.getElement()) == -1)  {
				//Element < Current Go left
				if (current.getLeft() == null) {
					return false;
				} else {
					current = current.getLeft();
				}
			} else if (targetElement.compareTo(current.getElement()) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T find(T targetElement) {
		boolean nullChecker = false;
		current = root;
		while (!nullChecker) {
			if (targetElement.compareTo(current.getElement()) == 1) {
				//Element > Current Go Right
				if (current.getRight() == null) {
					return null;
				} else {
					current = current.getRight();
				}
			} else if (targetElement.compareTo(current.getElement()) == -1)  {
				//Element < Current Go left
				if (current.getLeft() == null) {
					return null;
				} else {
					current = current.getLeft();
				}
			} else if (targetElement.compareTo(current.getElement()) == 0) {
				return current.getElement();
			}
		}
		return null;
	}
	

	@Override
	public void addElement(T element) {		
		//First checks if node is empty
		if (root == null) {
			root = new TreeNode<T>(element);
		} else {
			current = root;
			boolean nullChecker = false;
			
			while (!nullChecker) {
				//Recursively adds the element after sorting it
				if (element.compareTo(current.getElement()) == 1) {
					//Element > Current Go Right
					if (current.getRight() == null) {
						current.setRight(new TreeNode<T>(element));
						size++;
						nullChecker = true;
						break;
					} else {
						current = current.getRight();
					}
				} else if (element.compareTo(current.getElement()) <= 0)  {
					//Element <= Current Go left
					if (current.getLeft() == null) {
						current.setLeft(new TreeNode<T>(element));
						size++;
						nullChecker = true;
						break;
					} else {
						current = current.getLeft();
					}
				}
			}
		}
	}

	@Override
	public T removeElement(T targetElement) {
		// TODO Auto-generated method stub		
		if (root == null) {
			return root.getElement();
		}
		current = root;
		TreeNode<T> deleteNode = new TreeNode<T>();
		TreeNode<T> previous = new TreeNode<T>();
		boolean elementFinder = false;
		
		//Code that finds the element to be deleted
		while (!elementFinder) {
			if (targetElement.compareTo(current.getElement()) == 1) {
				previous = current;
				//Element > Current Go Right
				if (current.getRight() == null) {
				} else {
					current = current.getRight();
				}
			} else if (targetElement.compareTo(current.getElement()) == -1)  {
				previous = current;
				//Element < Current Go left
				if (current.getLeft() == null) {
				} else {
					current = current.getLeft();
				}
			} else if (targetElement.compareTo(current.getElement()) == 0) {
				deleteNode = current;
				elementFinder = true;
			}
		}

		//Both nodes are empty
		if (deleteNode.getRight() == null && deleteNode.getLeft() == null) {
			//Checks for left element
			if (previous.getLeft().getElement().compareTo(current.getElement()) == 0) {
				previous.setLeft(null);
			} else if (previous.getRight().getElement().compareTo(current.getElement()) == 0) {
				previous.setRight(null);
			}
			deleteNode = null;
			return current.getElement();
		} else if (deleteNode.getRight() != null && deleteNode.getLeft() != null) {
			//Both nodes are filled
			TreeNode<T> down = new TreeNode<T>();
			TreeNode<T> move = new TreeNode<T>();
			move = current;
			
			move = move.getRight();
			down.setElement(findMin(move));
			
			//Deletes the bottom node from it's parent
			if (globalPrevious.getLeft() == down.getElement()) {
				globalPrevious.setLeft(null);
			} else if (globalPrevious.getRight() == down.getElement()) {
				globalPrevious.setRight(null);
			}
			
			
			down.setLeft(deleteNode.getLeft());
			down.setRight(deleteNode.getRight());
			
			if (previous.getLeft() == deleteNode) {
				previous.setLeft(down);
			} else if (previous.getRight() == deleteNode) {
				previous.setRight(down);
			}
			
			return current.getElement();
		} else if (deleteNode.getRight() != null || deleteNode.getLeft() != null) {
			//Either left or right is full, but not both
			
			if (deleteNode.getRight() != null) {
				previous.setRight(deleteNode.getRight());
				return current.getElement();
			} else if (deleteNode.getLeft() != null) {
				previous.setLeft(deleteNode.getLeft());
				return current.getElement();
			}
		}
		
		
		return null;
	}

	@Override
	public void removeAllOccurrences(T targetElement) {
		// TODO Auto-generated method stub
		
		if (!contains(targetElement)) {
			throw new NoSuchElementException();
		} else {
			while(contains(targetElement)) {
				removeElement(targetElement);
			}
		}
		
		
	}

	@Override
	//Removes the minimum from the root of the tree
	public T removeMin() {
		return removeMin(root);
	}
	
	//Removes the minimum from a specific element
	public T removeMin(TreeNode<T> node) {
		// TODO Auto-generated method stub
		current = node;
		//Element < Current Go left
		boolean minChecker = false;
		while (!minChecker) {
			if (current.getLeft() == null) {
				minChecker = true;
			} else {
				current = current.getLeft();
			}
		}
		node = current;
		current = null;
		return node.getElement();
	}

	@Override
	//Removes the maximum from the root of the tree
	public T removeMax() {
		return removeMax(root);
	}
	
	//Removes the maximum from a specific element
	public T removeMax(TreeNode<T> node) {
		current = node;
		//Element < Current Go left
		boolean maxChecker = false;
		while (!maxChecker) {
			if (current.getRight() == null) {
				maxChecker = true;
			} else {
				current = current.getRight();
			}
		}
		node = current;
		current = null;
		return node.getElement();
	}

	@Override
	//Finds the minimum from the root of the tree
	public T findMin() {
		return findMin(root);
	}
	
	//Finds the minimum from a specific element
	public T findMin(TreeNode<T> node) {
		current = root;
		if (node.getElement().compareTo(current.getElement()) == 1) {
			//Element < Current Go Left
			if (current.getLeft() == null) {
				return node.getElement();
			} else {
				globalPrevious = current;
				current = current.getLeft();
				findMin(current);
			}
	}
		return null;
	}

	@Override
	//Finds the maximum from the root of the tree
	public T findMax() {
		return findMax(root);
	}
	
	//Finds the maximum from a specific element
	public T findMax(TreeNode<T> node) {
		current = node;
		boolean maxChecker = false;
		while (!maxChecker) {
			if (current.getRight() == null) {
				return current.getElement();
			} else {
				if (node.getElement().compareTo(current.getRight().getElement()) == -1) {
					//Element > Current Go Right
					globalPrevious = current;
					current = current.getRight();
				}
			}	
		}
		return null;
	}
	
	
	//All Code here was made by Mason Swanson, credit goes to them for the To String
	public String toString() {
        String retVal = (root != null) ? "N: " + toStringPreorder(root, "") : "(empty tree)";
		return retVal;
}
 
	private String toStringPreorder(TreeNode<T> node, String indents) {
        String retVal = "";

        if (node == null) {
            return "";
        }

        retVal += node.getElement();
        if (node.getRight() != null || node.getLeft() != null) {
            retVal += "\n" + indents + "+--R: " + toStringPreorder(node.getRight(), indents + "¦  ");
            retVal += "\n" + indents + "±--L: " + toStringPreorder(node.getLeft(), indents + "   ");
        }
        return retVal;
    }
	
	
}
