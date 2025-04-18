import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isBooked;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    String guestName;
    int roomNumber;
    String bookingId;
    double amountPaid;

    Booking(String guestName, int roomNumber, String bookingId, double amountPaid) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.bookingId = bookingId;
        this.amountPaid = amountPaid;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        System.out.println("🏨 Welcome to the Hotel Reservation System");

        boolean running = true;
        while (running) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View My Booking");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    viewBooking();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 250.0));
        rooms.add(new Room(104, "Single", 100.0));
        rooms.add(new Room(105, "Double", 150.0));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.type + " - $" + room.price);
            }
        }
    }

    static void bookRoom() {
        viewAvailableRooms();
        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter room number to book: ");
        int roomNum = sc.nextInt();
        sc.nextLine(); // clear buffer

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNum && !room.isBooked) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.println("Room price: $" + selectedRoom.price);
            System.out.print("Enter payment amount: $");
            double payment = sc.nextDouble();
            sc.nextLine();

            if (payment >= selectedRoom.price) {
                selectedRoom.isBooked = true;
                String bookingId = "BOOK" + new Random().nextInt(10000);
                bookings.add(new Booking(name, roomNum, bookingId, payment));
                System.out.println("✅ Booking confirmed! Booking ID: " + bookingId);
            } else {
                System.out.println("❌ Payment insufficient. Booking failed.");
            }
        } else {
            System.out.println("❌ Room not available or does not exist.");
        }
    }

    static void viewBooking() {
        System.out.print("Enter your name to find booking: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(name)) {
                System.out.println("\n--- Booking Details ---");
                System.out.println("Guest Name: " + booking.guestName);
                System.out.println("Room Number: " + booking.roomNumber);
                System.out.println("Booking ID: " + booking.bookingId);
                System.out.println("Amount Paid: $" + booking.amountPaid);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No booking found under that name.");
        }
    }
}
