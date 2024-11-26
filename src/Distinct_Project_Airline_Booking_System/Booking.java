package Distinct_Project_Airline_Booking_System;

import java.util.HashMap;
import java.util.UUID;

public class Booking {
    private String bookingNo;
    private int adultTicket;
    private int childTicket;
    private int concessionTicket;
    private String customerID;
    private String flightID;

    private static HashMap<String, Booking> bookingDatabase = new HashMap<>();

    public Booking(String bookingNo, int adultTicket, int childTicket, int concessionTicket, String customerID,
            String flightID) {
        this.bookingNo = bookingNo;
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
        this.customerID = customerID;
        this.flightID = flightID;

        bookingDatabase.put(bookingNo, this);
    }

    public Booking(int adultTicket, int childTicket, int concessionTicket, String customerID, String flightID) {
        if (!Customer.customerExists(customerID)) {
            throw new IllegalArgumentException("Customer ID does not exist: " + customerID);
        }
        if (!Flight.flightExists(flightID)) {
            throw new IllegalArgumentException("Flight ID does not exist: " + flightID);
        }

        this.bookingNo = UUID.randomUUID().toString().substring(0, 6);
        this.adultTicket = adultTicket;
        this.childTicket = childTicket;
        this.concessionTicket = concessionTicket;
        this.customerID = customerID;
        this.flightID = flightID;

        // bookingDatabase.put(bookingNo, this);
    }

    public static HashMap<String, Booking> getBookingDatabase() {
        return bookingDatabase;
    }

    public static void addBooking(Booking booking) {
        bookingDatabase.put(booking.getBookingNo(), booking);
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public static void showBookings() {
        System.out.println("=== Bookings ===");
        for (Booking booking : bookingDatabase.values()) {
            System.out.println(booking);
        }
    }

    @Override
    public String toString() {
        return "Booking [bookingNo=" + bookingNo + ", adultTicket=" + adultTicket + ", childTicket=" + childTicket
                + ", concessionTicket=" + concessionTicket + ", customerID=" + customerID + ", flightID=" + flightID
                + "]";
    }

    public String toCSV() {
        return bookingNo + "," + adultTicket + "," + childTicket + "," + concessionTicket + "," + customerID + ","
                + flightID;
    }

}
