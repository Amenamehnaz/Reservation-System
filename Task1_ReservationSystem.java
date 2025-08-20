import java.util.*;

class ReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> userDB = new HashMap<>();
    static List<Map<String, String>> bookings = new ArrayList<>();
    
    public static void main(String[] args) {
        // Predefined login
        userDB.put("admin", "admin123");

        System.out.println("===== Welcome to Online Reservation System =====");
        if (login()) {
            int choice;
            do {
                System.out.println("\nMain Menu:");
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. View All Bookings");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        makeReservation();
                        break;
                    case 2:
                        cancelReservation();
                        break;
                    case 3:
                        viewBookings();
                        break;
                    case 4:
                        System.out.println("Thank you for using the Reservation System.");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 4);
        } else {
            System.out.println("Access Denied. Exiting...");
        }
    }

    public static boolean login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pwd = sc.nextLine();

        return userDB.containsKey(uname) && userDB.get(uname).equals(pwd);
    }

    public static void makeReservation() {
        Map<String, String> booking = new HashMap<>();
        System.out.print("Enter Passenger Name: ");
        booking.put("name", sc.nextLine());

        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();
        booking.put("trainNo", trainNo);

        String trainName = getTrainName(trainNo);
        booking.put("trainName", trainName);

        System.out.print("Enter Class Type (e.g., Sleeper/AC): ");
        booking.put("classType", sc.nextLine());

        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        booking.put("date", sc.nextLine());

        System.out.print("Enter From (Source): ");
        booking.put("from", sc.nextLine());

        System.out.print("Enter To (Destination): ");
        booking.put("to", sc.nextLine());

        String pnr = UUID.randomUUID().toString().substring(0, 8);
        booking.put("pnr", pnr);
        bookings.add(booking);

        System.out.println("Reservation successful! Your PNR: " + pnr);
    }

    public static void cancelReservation() {
        System.out.print("Enter your PNR to cancel: ");
        String pnr = sc.nextLine();
        boolean found = false;

        Iterator<Map<String, String>> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Map<String, String> booking = iterator.next();
            if (booking.get("pnr").equals(pnr)) {
                System.out.println("Booking Found: " + booking);
                System.out.print("Confirm cancellation (yes/no)? ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    iterator.remove();
                    System.out.println("Booking cancelled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No booking found with PNR: " + pnr);
        }
    }

    public static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("All Bookings:");
            for (Map<String, String> booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    public static String getTrainName(String trainNo) {
        // Dummy logic to return train name
        switch (trainNo) {
            case "101":
                return "SuperFast Express";
            case "102":
                return "Rajdhani Express";
            case "103":
                return "Garib Rath";
            default:
                return "Local Passenger";
        }
    }
}
