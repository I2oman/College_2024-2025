package Distinct_Project_Airline_Booking_System;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static String txtDir = "src/Distinct_Project_Airline_Booking_System/txtDir/";
    private static Scanner scanner = new Scanner(System.in);
    private static FileManager fileManager;

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
    private static final Pattern ZIP_CODE_PATTERN = Pattern.compile("[A-Z]{2}\\d{3}[A-Z]{2}");
    private static final Pattern LOCATION_PATTERN = Pattern.compile("[A-Z]{3}");

    public static void main(String[] args) {
        fileManager = new FileManager(txtDir);
        fileManager.loadAllTables();
        int choice;
        while (true) {
            System.out.println("===== Airline Management System =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Route");
            System.out.println("3. Add Flight");
            System.out.println("4. Add Booking");
            System.out.println("5. Retrieve Data");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> addRoute();
                case 3 -> addFlight();
                case 4 -> addBooking();

                case 5 -> retrieveData();

                case 0 -> {
                    System.out.println("Exiting...");
                    fileManager.saveAllTables();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void retrieveData() {
        System.out.println("===== Retrieve Data =====");
        System.out.println("1. Customers");
        System.out.println("2. Routes");
        System.out.println("3. Flights");
        System.out.println("4. Bookings");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> Customer.showCustomers();
            case 2 -> Route.showRoutes();
            case 3 -> Flight.showFlights();
            case 4 -> Booking.showBookings();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addCustomer() {
        String forename = getValidInput("Enter Forename: ");
        String surname = getValidInput("Enter Surname: ");
        String street = getValidInput("Enter Street: ");
        String town = getValidInput("Enter Town: ");
        String postcode = getValidZipCode("Enter Postcode: ");

        Customer customer = new Customer(forename, surname, street, town, postcode);
        Customer.addCustomer(customer);
        fileManager.saveCustomers();
    }

    private static void addRoute() {
        String departFrom = getValidLocation("Enter Departure Location (3 chars): ");
        String arriveAt = getValidLocation("Enter Arrival Location (3 chars): ");
        String midStopOne = getValidLocation("Enter Mid Stop One (3 chars, optional): ", true);
        String midStopTwo = getValidLocation("Enter Mid Stop Two (3 chars, optional): ", true);

        Route route = new Route(departFrom, arriveAt, midStopOne, midStopTwo);
        Route.addRoute(route);
        fileManager.saveRoutes();
    }

    private static void addFlight() {
        String departureDate = getValidDateInput("Enter Departure Date (YYYY-MM-DD): ");
        String departureTime = getValidTimeInput("Enter Departure Time (HH:MM:SS): ");
        String arrivalDate = getValidDateInput("Enter Arrival Date (YYYY-MM-DD): ");
        String arrivalTime = getValidTimeInput("Enter Arrival Time (HH:MM:SS): ");
        int capacity = getValidIntInput("Enter Capacity: ");
        String routeID;
        while (true) {
            routeID = getValidInput("Enter Route ID (4 chars): ");
            if (!Route.routeExists(routeID) || routeID.length() != 4) {
                System.out.println("Route ID does not exist. Cannot create flight.");
            } else {
                break;
            }
        }

        Flight flight = new Flight(departureDate, departureTime, arrivalDate, arrivalTime, capacity, routeID);
        Flight.addFlight(flight);
        fileManager.saveFlights();
    }

    private static void addBooking() {
        int adultTicket = getValidIntInput("Enter Adult Ticket count: ");
        int childTicket = getValidIntInput("Enter Child Ticket count: ");
        int concessionTicket = getValidIntInput("Enter Concession Ticket count: ");
        String customerID;
        while (true) {
            customerID = getValidInput("Enter Customer ID (7 chars): ");
            if (!Customer.customerExists(customerID) || customerID.length() != 7) {
                System.out.println("Customer ID does not exist. Cannot create booking.");
            } else {
                break;
            }
        }
        String flightID;
        while (true) {
            flightID = getValidInput("Enter Flight ID (5 chars): ");
            if (!Flight.flightExists(flightID) || flightID.length() != 5) {
                System.out.println("Flight ID does not exist. Cannot create booking.");
            } else {
                break;
            }
        }

        Booking booking = new Booking(adultTicket, childTicket, concessionTicket, customerID, flightID);
        Booking.addBooking(booking);
        fileManager.saveBookings();
    }

    private static String getValidInput(String prompt) {
        return getValidInput(prompt, false);
    }

    private static String getValidInput(String prompt, boolean canBeNull) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.isEmpty() && !canBeNull) {
                System.out.println("Input cannot be empty. Please enter again.");
            }
            if (input.isEmpty() && canBeNull) {
                return input;
            }
        } while (input.isEmpty());
        return input;
    }

    private static int getValidIntInput(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    private static String getValidDateInput(String prompt) {
        String input;
        do {
            input = getValidInput(prompt);
            if (!DATE_PATTERN.matcher(input).matches()) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } while (!DATE_PATTERN.matcher(input).matches());
        return input;
    }

    private static String getValidTimeInput(String prompt) {
        String input;
        do {
            input = getValidInput(prompt);
            if (!TIME_PATTERN.matcher(input).matches()) {
                System.out.println("Invalid time format. Please use HH:MM:SS.");
            }
        } while (!TIME_PATTERN.matcher(input).matches());
        return input;
    }

    private static String getValidZipCode(String prompt) {
        String input;
        do {
            input = getValidInput(prompt);
            if (!ZIP_CODE_PATTERN.matcher(input).matches()) {
                System.out.println("Invalid postcode format. Please enter in format: 2 letters, 3 numbers, 2 letters.");
            }
        } while (!ZIP_CODE_PATTERN.matcher(input).matches());
        return input;
    }

    private static String getValidLocation(String prompt) {
        return getValidLocation(prompt, false);
    }

    private static String getValidLocation(String prompt, boolean canBeNull) {
        String input;
        do {
            input = getValidInput(prompt, canBeNull);
            if (!LOCATION_PATTERN.matcher(input).matches() && !canBeNull) {
                System.out.println("Invalid location format. Please enter 3 uppercase letters.");
            }
            if (input == "" && canBeNull) {
                return input;
            }
        } while (!LOCATION_PATTERN.matcher(input).matches());
        return input;
    }

}
