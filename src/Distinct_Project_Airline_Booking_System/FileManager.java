package Distinct_Project_Airline_Booking_System;

import java.io.*;

public class FileManager {
    private String TXT_DIR;

    public FileManager(String tXT_DIR) {
        TXT_DIR = tXT_DIR;
    }

    public void loadAllTables() {
        loadCustomers();
        loadRoutes();
        loadFlights();
        loadBookings();
    }

    public void saveAllTables() {
        saveCustomers();
        saveRoutes();
        saveFlights();
        saveBookings();
    }

    // Load Methods
    private void loadCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader(TXT_DIR + "customer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                new Customer(data[0], data[1], data[2], data[3], data[4], data[5]);
            }
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

    private void loadRoutes() {
        try (BufferedReader br = new BufferedReader(new FileReader(TXT_DIR + "route.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                new Route(data[0], data[1], data[2], data[3], data[4]);
            }
        } catch (IOException e) {
            System.out.println("Error loading routes: " + e.getMessage());
        }
    }

    private void loadFlights() {
        try (BufferedReader br = new BufferedReader(new FileReader(TXT_DIR + "flight.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                new Flight(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), data[6]);
            }
        } catch (IOException e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
    }

    private void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader(TXT_DIR + "booking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                new Booking(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        data[4],
                        data[5]);
            }
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }

    public void saveCustomers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_DIR + "customer.txt"))) {
            for (Customer customer : Customer.getCustomerDatabase().values()) {
                bw.write(customer.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public void saveRoutes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_DIR + "route.txt"))) {
            for (Route route : Route.getRouteDatabase().values()) {
                bw.write(route.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving routes: " + e.getMessage());
        }
    }

    public void saveFlights() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_DIR + "flight.txt"))) {
            for (Flight flight : Flight.getFlightDatabase().values()) {
                bw.write(flight.toCVS());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving flights: " + e.getMessage());
        }
    }

    public void saveBookings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_DIR + "booking.txt"))) {
            for (Booking booking : Booking.getBookingDatabase().values()) {
                bw.write(booking.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }
}
