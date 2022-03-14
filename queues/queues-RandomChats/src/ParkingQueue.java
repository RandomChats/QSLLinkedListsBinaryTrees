import java.util.*;
/**
 * 
 * @author corbin.mclaughlin
 *
 */
public class ParkingQueue {
	
	@SuppressWarnings("unused")
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int placeholder = 0;
		ParkingSpot spot;
		Queue<ParkingSpot> parkedSpots = new LinkedList<ParkingSpot>();
		
		while (placeholder != 5) {
			parkedSpots.add(spot = new ParkingSpot(placeholder + " Street", rand.nextInt(100), rand.nextInt(100)));
			placeholder++;
		}
		System.out.println();

		System.out.println("The item at the front is " + parkedSpots.peek());
		System.out.println("The size of the queue is " + parkedSpots.size());
		
		System.out.println();

		if (parkedSpots.isEmpty()) {
			System.out.println("The Queue is empty");
		} else {
			System.out.println("The Queue is not empty");
		}
		
		System.out.println();
		System.out.println(parkedSpots.remove());
		
		System.out.println();

		System.out.println("The new item at the front is " + parkedSpots.peek());
		System.out.println("The new size of the queue is " + parkedSpots.size());

		System.out.println();
		System.out.println(parkedSpots.toString());
		
	}
	
	
	
}
