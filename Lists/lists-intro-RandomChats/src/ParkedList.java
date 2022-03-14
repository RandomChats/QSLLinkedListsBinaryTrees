import java.util.*;

/**
 * 
 * @author corbin.mclaughlin
 *
 */
public class ParkedList {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int placeholder = 0;
		ParkingSpot spot;
		LinkedList<ParkingSpot> parkedSpots = new LinkedList<ParkingSpot>();
		
		while (placeholder != 4) {
			parkedSpots.add(spot = new ParkingSpot(placeholder + " Street", rand.nextInt(100), rand.nextInt(100)));
			placeholder++;
		}
		System.out.println();
		System.out.println("The current size of the list is " + parkedSpots.size());
		
		System.out.println();
		System.out.println("Removing this parking spot: " + parkedSpots.remove());
		
		System.out.println();
		parkedSpots.add(3, spot = new ParkingSpot(4 + "th Street", rand.nextInt(100), rand.nextInt(100)));
		System.out.println("Adding a new parking spot: " + parkedSpots.get(3));


		System.out.println();
		System.out.println("Spot in slot 3: " + parkedSpots.get(2));
		System.out.println("Removing the 3rd spot");
		parkedSpots.remove(2);
		System.out.println("New spot in slot 3: " + parkedSpots.get(2));
		
		System.out.println();
		System.out.println("Size of list after all the changes: " + parkedSpots.size());

	}
}
