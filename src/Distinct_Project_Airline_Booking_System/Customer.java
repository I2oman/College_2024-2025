package Distinct_Project_Airline_Booking_System;

import java.util.HashMap;
import java.util.UUID;

public class Customer {
    private String customerID;
    private String forename;
    private String surname;
    private String street;
    private String town;
    private String postcode;

    private static HashMap<String, Customer> customerDatabase = new HashMap<>();

    public Customer(String customerID, String forename, String surname, String street, String town, String postcode) {
        this.customerID = customerID;
        this.forename = forename;
        this.surname = surname;
        this.street = street;
        this.town = town;
        this.postcode = postcode;

        customerDatabase.put(customerID, this);
    }

    public Customer(String forename, String surname, String street, String town, String postcode) {
        this.customerID = UUID.randomUUID().toString().substring(0, 7); // Generate unique ID
        this.forename = forename;
        this.surname = surname;
        this.street = street;
        this.town = town;
        this.postcode = postcode;

        // customerDatabase.put(customerID, this);
    }

    public static HashMap<String, Customer> getCustomerDatabase() {
        return customerDatabase;
    }

    public static void addCustomer(Customer customer) {
        customerDatabase.put(customer.getCustomerID(), customer);
    }

    public String getCustomerID() {
        return customerID;
    }

    public static boolean customerExists(String customerID) {
        return customerDatabase.containsKey(customerID);
    }

    public static void showCustomers() {
        System.out.println("=== Customers ===");
        for (Customer customer : customerDatabase.values()) {
            System.out.println(customer);
        }
    }

    @Override
    public String toString() {
        return "Customer [customerID=" + customerID + ", forename=" + forename + ", surname=" + surname + ", street="
                + street + ", town=" + town + ", postcode=" + postcode + "]";
    }

    public String toCSV() {
        return customerID + "," + forename + "," + surname + "," + street + "," + town + "," + postcode;
    }

}
