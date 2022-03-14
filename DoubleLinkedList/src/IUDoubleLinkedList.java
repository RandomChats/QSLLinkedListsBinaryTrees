import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	/*
	 * head is the current element of the list
	 * 
	 * tail is the last element of the list
	 */
	private LinearDoubleNode<T> head, tail, current, previous;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}

	
	@Override
	public void addToFront(T element) {
		current = new LinearDoubleNode<T>(element);
		
		if(size == 0) {
			head = current;
			tail = current;
			tail.setNext(null);
			head.setPrevious(null);
		} else {
			current.setNext(head);
			current.setElement(element);
			current.setPrevious(null);
			head = current;
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		
		LinearDoubleNode<T> item = new LinearDoubleNode<T>(element);
		current = head;
		if (size == 0) {
			head = tail = item;
			tail.setNext(null);
			head.setPrevious(null);
		} else {
			int counter = 0;
			while (counter != size-1) {
				current = current.getNext();
				counter++;
			}
			tail.setNext(item);
			tail = item;
			tail.setPrevious(current);
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
			LinearDoubleNode<T> set = new LinearDoubleNode<T>(element);
			LinearDoubleNode<T> next = new LinearDoubleNode<T>();
			current = head;
			
			for (int i=0; i < size; i++) {
				if (current.getElement() == target) {
					
					if(current.getNext() == null) {
						addToRear(element);
						size--;
						modCount--;;
					} else {
						next = current.getNext();
						next.setPrevious(set);
						set.setNext(next);
						current.setNext(set);
						set.setPrevious(current);
					}
					break;
				} else {
					current = current.getNext();
				}
			}
		} else {
			throw new NoSuchElementException();
		}
		size++;
		modCount++;
		
	}

	public void add(int index, T element) {
		LinearDoubleNode<T> set = new LinearDoubleNode<T>(element);
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addToFront(element);
		} else if (index == size) {
			addToRear(element);
		} else {
			int counter = 0;
			current = head;
			while (counter != index-1) {
				current = current.getNext();
				counter++;
			}
			set.setNext(tail);
			tail.setPrevious(set);
			tail.setNext(null);
			current.setNext(set);
			set.setPrevious(current);
			size++;
			modCount++;
			

		}
		
	}

	@Override
	public T removeFirst() {
		return remove(first());
	}

	@Override
	public T removeLast() {
		return remove(last());
	}

	@Override
	public T remove(T element) {
		LinearDoubleNode<T> item = new LinearDoubleNode<T>();

		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		boolean found = false;
		item = null;
		current = head;
		
		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = item;
				item = current;
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
			tail = item;
			tail.setNext(null);
			tail.setPrevious(previous);
		} else { //somewhere in the middle
			item.setNext(current.getNext());
			item.setPrevious(previous);
		}
		
		size--;
		modCount++;
		
		return current.getElement();
	}

	@Override
	public T remove(int index) {
		LinearDoubleNode<T> item = new LinearDoubleNode<T>();

		int counter = 0;
		current = head;
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size-1) {
			return removeLast();
		} else {
			while(counter != index) {
				previous = item;
				item = current;
				current = current.getNext();
				counter++;
			}
			item.setNext(current.getNext());
			item.setPrevious(previous);
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
				current = current.getNext();
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
		return listIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}
	
	/** Iterator for IUDoubleLinkedList */
	private class DLLIterator implements ListIterator<T> {
		private LinearDoubleNode<T> nextNode;
		private int iterModCount, index;
		private boolean nextCheck, previousCheck;
		
		
		/** Creates a new iterator for the list */
		public DLLIterator() {
			nextNode = head;
			iterModCount = modCount;
			index = 0;
			
			nextCheck = false;
			previousCheck = false;
		}

		/**
		 * @param startingIndex Asks for an index to start the list at
		 */
		public DLLIterator(int startingIndex) {
			nextNode = head;
			iterModCount = modCount;
			index = 0;
			nextCheck = false;
			previousCheck = false;
			int counter = 0;
			while(counter != startingIndex) {
				current = nextNode;
				nextNode = nextNode.getNext();
				counter++;
				index++;
			}
		}

		@Override
		public boolean hasNext() {
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
		public boolean hasPrevious() {
			if (iterModCount == modCount) {
				if (isEmpty() || current == null) {
					return false;
				} else {
					if (index == 0) {
						return false;
					} else {
						
						return true;
					}
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public T next() {
			if (iterModCount == modCount) {
				if (hasNext() == true) {
					nextCheck = true;
					current = nextNode;
					nextNode = nextNode.getNext();
					index++;
					return current.getElement();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public T previous() {
			if (iterModCount == modCount) {
				if (hasPrevious() == true) {
					previousCheck = true;
					LinearDoubleNode<T> ting = current;
					nextNode = current;
					current = current.getPrevious();
					index--;
					return ting.getElement();
				} else {
					throw new NoSuchElementException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public void remove() {
			if (iterModCount == modCount) {
				if (nextCheck == true) {
					if (IUDoubleLinkedList.this.size() == 0) {
						throw new NoSuchElementException();
					} else {
						IUDoubleLinkedList.this.remove(current.getElement());
						iterModCount++;
						nextCheck = false;
						previousCheck = false;
					}
				} else if (previousCheck == true) {
					if (IUDoubleLinkedList.this.size() == 0) {
						throw new NoSuchElementException();
					} else {
						IUDoubleLinkedList.this.remove(nextNode.getElement());
						iterModCount++;
						nextCheck = false;
						previousCheck = false;
					}
				} else {
					throw new IllegalStateException();
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public int nextIndex() {
			if (iterModCount == modCount) {
				if (size() == index) {
					return size;
				} else {
					return index++;
				}
			} else {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public int previousIndex() {
			if (iterModCount == modCount) {
				if (index == 0) {
					return -1;
				} else {
					return index--;
				}
			} else {
				throw new ConcurrentModificationException();
			}
			
		}

		@Override
		public void set(T e) {
			if (iterModCount == modCount) {
				if (nextCheck == true || previousCheck == true) {
					if (index == 0) {
						IUDoubleLinkedList.this.set(index, e);
						iterModCount++;
					} else {
						IUDoubleLinkedList.this.set(index-1, e);
						iterModCount++;
					}

				} else {
					throw new IllegalStateException();
				}
			} else {
				throw new ConcurrentModificationException();
			}			
		}

		@Override
		public void add(T e) {
			if (iterModCount == modCount) {
				IUDoubleLinkedList.this.add(index, e);
				iterModCount++;
			} else {
				throw new ConcurrentModificationException();
			}
		}
	}
}

