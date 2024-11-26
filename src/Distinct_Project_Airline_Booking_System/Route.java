package Distinct_Project_Airline_Booking_System;

import java.util.HashMap;
import java.util.UUID;

public class Route {
    private String routeID;
    private String departFrom;
    private String arriveAt;
    private String midStopOne;
    private String midStopTwo;

    private static HashMap<String, Route> routeDatabase = new HashMap<>();

    public Route(String routeID, String departFrom, String arriveAt, String midStopOne, String midStopTwo) {
        this.routeID = routeID;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.midStopOne = midStopOne;
        this.midStopTwo = midStopTwo;

        routeDatabase.put(routeID, this);
    }

    public Route(String departFrom, String arriveAt, String midStopOne, String midStopTwo) {
        this.routeID = UUID.randomUUID().toString().substring(0, 4);
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.midStopOne = midStopOne;
        this.midStopTwo = midStopTwo;

        // routeDatabase.put(routeID, this);
    }

    public static HashMap<String, Route> getRouteDatabase() {
        return routeDatabase;
    }

    public static void addRoute(Route route) {
        routeDatabase.put(route.getRouteID(), route);
    }

    public String getRouteID() {
        return routeID;
    }

    public static boolean routeExists(String routeID) {
        return routeDatabase.containsKey(routeID);
    }

    public static void showRoutes() {
        System.out.println("=== Routes ===");
        for (Route route : routeDatabase.values()) {
            System.out.println(route);
        }
    }

    @Override
    public String toString() {
        return "Route [routeID=" + routeID + ", departFrom=" + departFrom + ", arriveAt=" + arriveAt + ", midStopOne="
                + midStopOne + ", midStopTwo=" + midStopTwo + "]";
    }

    public String toCSV() {
        return routeID + "," + departFrom + "," + arriveAt + "," + (midStopOne == "" ? " " : midStopOne) + ","
                + (midStopTwo == "" ? " " : midStopTwo);
    }

}
