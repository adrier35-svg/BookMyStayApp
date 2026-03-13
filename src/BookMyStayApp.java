import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Hotel Booking Management System
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates safe, read-only search of available rooms
 * without modifying inventory.
 *
 * @version 4.1
 */


/* ---------- Abstract Room Class ---------- */
abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq.ft");
        System.out.println("Price     : $" + price);
    }
}


/* ---------- Single Room ---------- */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}


/* ---------- Double Room ---------- */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}


/* ---------- Suite Room ---------- */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 500, 300);
    }
}


/* ---------- Room Inventory ---------- */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Map<String, Integer> getInventoryMap() {
        return new HashMap<>(inventory); // defensive copy
    }
}


/* ---------- Main Application ---------- */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay App - Version 4.1");
        System.out.println("Room Search & Availability Check");
        System.out.println("=================================");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display room search results
        displayAvailableRooms(new Room[] { single, doubleRoom, suite }, inventory);

        System.out.println("\nApplication Finished.");
    }

    /**
     * Display rooms with availability > 0
     * without modifying the inventory (read-only)
     */
    private static void displayAvailableRooms(Room[] rooms, RoomInventory inventory) {

        System.out.println("\n--- Available Rooms ---\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.roomType);
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available Rooms : " + available);
                System.out.println();
            }
        }
    }
}