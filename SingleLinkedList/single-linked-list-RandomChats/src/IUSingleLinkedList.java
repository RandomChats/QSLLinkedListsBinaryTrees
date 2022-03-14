import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	/*
	 * head is the current element of the list
	 * 
	 * tail is the last element of the list
	 */
	private Node<T> head, tail, previous, current;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		previous = new Node<T>();
		current = new Node<T>(element);
		
		if(size == 0) {
			head = current;
			tail = current;
			tail.setNext(null);
		} else {
			previous.setNext(head);
			previous.setElement(element);
			head = previous;
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		
		Node<T> item = new Node<T>(element);
		if (size == 0) {
			head = tail = item;
			tail.setNext(null);
		} else {
			tail.setNext(item);
			tail = item;
			tail.setNext(null);
		}
		size++;
		modCount++;
		
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {		
		if (contains(target) == true) {
			Node<T> set = new Node<T>(element);
			current = head;
		
			for (int i=0; i < size; i++) {
				if (current.getElement() == target) {
					current.setNext(set);
					break;
				} else {
					current.getNext();
				}
			}
		} else {
			throw new NoSuchElementException();
		}
		size++;
		modCount++;
		
	}

	@Override
	public void add(int index, T element) {
		Node<T> set = new Node<T>(element);
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addToFront(element);
		} else if (index == size) {
			addToRear(element);
		} else {
			int counter = 0;
			current = head;
			while (counter != index) {
				current = current.getNext();
				counter++;
			}
			current.setNext(set);
			size++;
			modCount++;
		}
		
	}

	@Override
	public T removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		current = head;
		head = head.getNext();
		size--;
		modCount++;
				
		return current.getElement();
	}

	@Override
	public T removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		previous = head;
		
		for (int i=0; i < size; i++) {
			previous.getNext();
		}
		Node<T> removed = new Node<T>();
		removed.setElement(tail.getElement());
		tail.setElement(null);
		tail = previous;
		
		size--;
		modCount++;
		return removed.getElement();
	}

	@Override
	public T remove(T element) {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		boolean found = false;
		previous = null;
		current = head;
		
		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
		
		if (!found) {
			throw new NoSuchElementException();
		}
		
		if (size() == 1) { //only node
			head = tail = null;
		} else if (current == head) { //first node
			head = current.getNext();
		} else if (current == tail) { //last node
			tail = previous;
			tail.setNext(null);
		} else { //somewhere in the middle
			previous.setNext(current.getNext());
		}
		
		size--;
		modCount++;
		
		return current.getElement();
	}

	@Override
	public T remove(int index) {
		int counter = 0;
		current = head;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size) {
			return removeLast();
		} else {
			while(counter != index) {
				previous = current;
				current = current.getNext();
				counter++;
			}
			previous.setNext(current.getNext());
			size--;
			modCount++;
		}
		return current.getElement();
	}

	@Override
	public void set(int index, T element) {
		int counter = 0;
		current = head;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			head.setElement(element);
		} else {
			while(counter != index) {
				current = current.getNext();
				counter++;
			}
			current.setElement(element);
		}
		modCount++;
	}

	@Override
	public T get(int index) {
		int counter = 0;
		current = head;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return head.getElement();
		} else {
			while(counter != index) {
				current = current.getNext();
				counter++;
			}
		
			return current.getElement();
		}
	}

	@Override
	public int indexOf(T element) {
		current = head;
		for (int i=0; i < size; i++) {
			if (current.getElement() == element) {
				return i;
			} else {
				current.getNext();
			}
		}
		return -1;
	}

	@Override
	public T first() {
		if(size == 0) {
			throw new NoSuchElementException();
		} else {
		return head.getElement();
		}
	}

	@Override
	public T last() {		
		if(size == 0) {
			throw new NoSuchElementException();
		} 
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		current = head;
		for (int i=0; i < size; i++) {
			if (current.getElement() == target) {
				return true;
			} else {
				current = current.getNext();
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	public String toString() {
		
		current = head;
		
		if(size == 0) {
			return "[]";
		}
		else if(size == 1) {
			return "[" + head.getElement() + "]";
		}
		else {	
			String result = "[" + head.getElement();
			for(int i=1; i < size; i++) {
				result += ", " + current.getNext();
			}
			result += "]";
		return result;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private Node<T> nextNode;
		private int iterModCount;
		private boolean nextCheck;
		
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			nextCheck = false;
		}

		@Override
		public boolean hasNext() {
			// TODO REFERENCE IU ARRAY LIST FOR ALL OF THIS
			if (iterModCount == modCount) {
				if (isEmpty() || nextNode == null) {
					return false;
				} else {
					return true;
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public T next() {
			// TODO 
			if (iterModCount == modCount) {
				if (hasNext() == true) {
					nextCheck = true;
					current = nextNode;
					nextNode = nextNode.getNext();
					return current.getElement();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public void remove() {
			// TODO
			if (iterModCount == modCount) {
				if (nextCheck == true) {
					if (IUSingleLinkedList.this.size() == 0) {
						throw new NoSuchElementException();
					} else {
						IUSingleLinkedList.this.remove(current.getElement());
						iterModCount++;
						nextCheck = false;
					}
				} else {
					throw new IllegalStateException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}
	}
}
