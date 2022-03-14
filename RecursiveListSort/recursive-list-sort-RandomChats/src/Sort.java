import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 * @author Corbin McLaughlin
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		int size = list.size();
		IndexedUnsortedList<T> left = new WrappedDLL<T>();
		IndexedUnsortedList<T> right = new WrappedDLL<T>();
		
		int leftListSize = list.size()/2;
		
		
		//Grabs only a small amount of elements and puts them in the left list
		for (int i = 0; i < leftListSize; i++) {
			left.add(list.get(0));
			list.removeFirst();
		}	
		//Takes the rest of the elements and puts them in the right
		while (list.isEmpty() == false) {
			right.add(list.get(0));
			list.removeFirst();
		}
		//Checks if the left list is bigger than 1 element, then if the right is bigger than 1 element
		if (left.size() > 1) {
			mergesort(left);
		}
		if (right.size() > 1) {
			mergesort(right);
		}
		
		//Sets a while loop to the original list size
		while (list.size() != size) {
			//Checks if the left list is not empty
			if (left.isEmpty() == false) {
				//Checks if the right list is not empty
				if (right.isEmpty() == false) {
					//Sees if the right list element 0 is less then, greater then, or equal to the left list element 0 in that order
					if(left.get(0).compareTo(right.get(0)) == -1) {
						//If left element is greater than right element
						list.add(left.get(0));
						left.remove(0);
					} else if (left.get(0).compareTo(right.get(0)) == 1) {
						//If left element is less than right element
						list.add(right.get(0));
						right.remove(0);
					} else {
						//If elements are equal
						list.add(left.get(0));
						list.add(right.get(0));
						left.remove(0);
						right.remove(0);
					}
				} else {
					//If right is empty, it adds all the left elements in the list
					while (left.size() != 0) {
						list.add(left.get(0));
						left.remove(0);
					}
				}
			} else {
				//If left is empty, it adds all the right elements in the list
				while (right.size() != 0) {
					list.add(right.get(0));
					right.remove(0);
				}
			}
		}
		
		
		
		
		
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		int size = list.size();
		IndexedUnsortedList<T> left = new WrappedDLL<T>();
		IndexedUnsortedList<T> right = new WrappedDLL<T>();
		
		int leftListSize = list.size()/2;
		
		
		//Grabs only a small amount of elements and puts them in the left list
		for (int i = 0; i < leftListSize; i++) {
			left.add(list.get(0));
			list.removeFirst();
		}	
		//Takes the rest of the elements and puts them in the right
		while (list.isEmpty() == false) {
			right.add(list.get(0));
			list.removeFirst();
		}
		//Checks if the left list is bigger than 1 element, then if the right is bigger than 1 element
		if (left.size() > 1) {
			mergesort(left, c);
		}
		if (right.size() > 1) {
			mergesort(right, c);
		}
		
		//Sets a while loop to the original list size
		while (list.size() != size) {
			//Checks if the left list is not empty
			if (left.isEmpty() == false) {
				//Checks if the right list is not empty
				if (right.isEmpty() == false) {
					//Sees if the right list element 0 is less then, greater then, or equal to the left list element 0 in that order
					if(c.compare(left.get(0), right.get(0)) == -1) {
						//If left element is greater than right element
						list.add(left.get(0));
						left.remove(0);
					} else if (c.compare(left.get(0), right.get(0)) == 1) {
						//If left element is less than right element
						list.add(right.get(0));
						right.remove(0);
					} else {
						//If elements are equal
						list.add(left.get(0));
						list.add(right.get(0));
						left.remove(0);
						right.remove(0);
					}
				} else {
					//If right is empty, it adds all the left elements in the list
					while (left.size() != 0) {
						list.add(left.get(0));
						left.remove(0);
					}
				}
			} else {
				//If left is empty, it adds all the right elements in the list
				while (right.size() != 0) {
					list.add(right.get(0));
					right.remove(0);
				}
			}
		}

	}
	
}
