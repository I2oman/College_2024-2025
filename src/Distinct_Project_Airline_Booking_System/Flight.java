package Distinct_Project_Airline_Booking_System;

import java.util.HashMap;
import java.util.UUID;

public class Flight {
    private String flightID;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private int capacity;
    private String routeID;

    private static HashMap<String, Flight> flightDatabase = new HashMap<>();

    public Flight(String flightID, String departureDate, String departureTime, String arrivalDate, String arrivalTime,
            int capacity, String routeID) {
        this.flightID = flightID;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.routeID = routeID;

        flightDatabase.put(flightID, this);
    }

    public Flight(String departureDate, String departureTime, String arrivalDate, String arrivalTime, int capacity,
            String routeID) {
        if (!Route.routeExists(routeID)) {
            throw new IllegalArgumentException("Route ID does not exist: " + routeID);
        }
        this.flightID = UUID.randomUUID().toString().substring(0, 5);
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.routeID = routeID;

        // flightDatabase.put(flightID, this);
    }

    public static HashMap<String, Flight> getFlightDatabase() {
        return flightDatabase;
    }

    public static void addFlight(Flight flight) {
        flightDatabase.put(flight.getFlightID(), flight);
    }

    public String getFlightID() {
        return flightID;
    }

    public static boolean flightExists(String flightID) {
        return flightDatabase.containsKey(flightID);
    }

    public static void showFlights() {
        System.out.println("=== Flights ===");
        for (Flight flight : flightDatabase.values()) {
            System.out.println(flight);
        }
    }

    @Override
    public String toString() {
        return "Flight [flightID=" + flightID + ", departureDate=" + departureDate + ", departureTime=" + departureTime
                + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", capacity=" + capacity
                + ", routeID=" + routeID + "]";
    }

    public String toCVS() {
        return flightID + "," + departureDate + "," + departureTime + "," + arrivalDate + "," + arrivalTime + ","
                + capacity + "," + routeID;
    }

}
