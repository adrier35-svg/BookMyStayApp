import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Hotel Booking Management System
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates fair handling of booking requests
 * using a FIFO queue.
 *
 * @version 5.1
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


/* ---------- Reservation Class ---------- */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}


/* ---------- Main Application ---------- */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay App - Version 5.1");
        System.out.println("Booking Request Queue (FIFO)");
        System.out.println("=================================");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display available rooms
        displayAvailableRooms(new Room[]{single, doubleRoom, suite}, inventory);

        // Create booking request queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Simulate guest requests
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("Diana", "Single Room"));

        System.out.println("\n--- Booking Requests in Queue (First-Come-First-Served) ---\n");

        for (Reservation res : bookingQueue) {
            res.displayReservation();
        }

        System.out.println("\nNote: Inventory is not modified at this stage (read-only booking queue).");
    }

    /**
     * Display rooms with availability > 0 (read-only)
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