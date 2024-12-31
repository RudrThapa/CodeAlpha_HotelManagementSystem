import java.util.*;

//Represents a room in the hotel with details such as room number, category, availability, and price
class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    public Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Room " + roomNumber + " (" + category + ") - Price: Rs." + price + (isAvailable ? " [Available]" : " [Booked]");
    }
}

//Represents a booking made by a guest, including guest details, room number, date, and amount paid
class Booking {
    int bookingId;
    String guestName;
    int roomNumber;
    String date;
    double amount;

    public Booking(int bookingId, String guestName, int roomNumber, String date, double amount) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Guest: " + guestName + ", Room: " + roomNumber + ", Date: " + date + ", Amount: Rs." + amount;
    }
}

//Handles the core logic of the hotel reservation system, including room management and bookings
class ReservationManager {
    private final List<Room> rooms = new ArrayList<>(); //List to store all the rooms
    private final List<Booking> bookings = new ArrayList<>(); //List to store all the bookings
    private int bookingIdCounter = 1; //Counter to generate unique booking IDs

    public ReservationManager() {
        initializeRooms();
    }

    //Adds predefined rooms to the hotel system during initialization
    private void initializeRooms() {
        rooms.add(new Room(101, "Deluxe", true, 3500));
        rooms.add(new Room(102, "Standard", true, 2500));
        rooms.add(new Room(103, "Suite", true, 5000));
        rooms.add(new Room(104, "Deluxe", true, 3500));
        rooms.add(new Room(105, "Standard", true, 2500));
    }

    //Displays all rooms that are currently available for booking
    public void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room != null && room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    //Books a room for a guest if it is available, otherwise notifies the user
    public void bookRoom(String guestName, int roomNumber, String date) {
        for (Room room : rooms) {
            if (room != null && room.roomNumber == roomNumber) {
                if (room.isAvailable) {
                    room.isAvailable = false;
                    Booking booking = new Booking(bookingIdCounter++, guestName, roomNumber, date, room.price);
                    bookings.add(booking);
                    System.out.println("Booking successful! Here are your details:");
                    System.out.println(booking);
                } else {
                    System.out.println("Sorry, this room is already booked.");
                }
                return;
            }
        }
        System.out.println("Room not found.");
    }

    //Displays all bookings that have been made by guests
    public void showBookings() {
        System.out.println("Your Bookings:");
        for (Booking booking : bookings) {
            if (booking != null) {
                System.out.println(booking);
            }
        }
    }
}

//Main class to run the hotel reservation system program
public class HotelReservationSystem {
    public static void main(String[] args) {
        ReservationManager reservationManager = new ReservationManager(); //Create an instance of the ReservationManager
        Scanner sc = new Scanner(System.in); //Scanner to read user input

        while (true) {
            // Display menu options to the user
            System.out.println("\nHotel Reservation System:");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Show My Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); //Consume newline

            //Handle user input based on their choice
            switch (choice) {
                case 1:
                    //Show all available rooms
                    reservationManager.showAvailableRooms();
                    break;
                case 2:
                    //Book a room
                    System.out.print("Enter your name: ");
                    String guestName = sc.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter booking date (dd/mm/yyyy): ");
                    String bookingDate = sc.nextLine();
                    reservationManager.bookRoom(guestName, roomNumber, bookingDate);
                    break;
                case 3:
                    //Display all bookings made by the user
                    reservationManager.showBookings();
                    break;
                case 4:
                    //Exit the program
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    return;
                default:
                    //Handle invalid choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
