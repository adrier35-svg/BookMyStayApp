/**
 * Book My Stay App
 * Hotel Booking Management System
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Demonstrates abstraction, inheritance, and polymorphism
 * for modeling hotel room types.
 *
 * @author Student
 * @version 2.1
 */

/* ---------- Abstract Room Class ---------- */
abstract class Room {

    private String roomType;
    private int beds;
    private int size;
    private double price;

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
        super("Single Room", 1, 200, 100.0);
    }
}

/* ---------- Double Room ---------- */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180.0);
    }
}

/* ---------- Suite Room ---------- */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 300.0);
    }
}

/* ---------- Main Application Class ---------- */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("Book My Stay App - Version 2.1");
        System.out.println("Basic Room Types & Availability");
        System.out.println("===================================");

        // Creating room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("\n--- Room Details ---\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + singleAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + doubleAvailability);
        System.out.println();

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + suiteAvailability);

        System.out.println("\nApplication Finished.");
    }
}