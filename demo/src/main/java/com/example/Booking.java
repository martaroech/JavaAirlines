package com.example;

public class Booking {
    private String[] booking = {""};
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureDate;
    Seats seat = new Seats();
    User customer = new User("", "", "", "", "");
    flights flight = new flights();

    public Booking (String[] booking, String flightNumber, String departure, String arrival, String departureDate, String user) {
        this.booking = booking;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
    }

    public void setBooking(String[] booking) {
        this.booking = booking;
    }

    public String[] getBooking() {
        return booking;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture() {
        return departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrival() {
        return arrival;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setSeats(Seats seats) {
        seat = seats;
    }

    public Seats getSeats() {
        return seat;
    }

    public void setUser(User user) {
        customer = user;
    }

    public User getUser() {
        return customer;
    }

    public void setFlight(flights flights) {
        flight = flights;
    }

    public flights getFlight() {
        return flight;
    }

    public static void printBooking(User customer, Seats seat, flights flight) {
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String lightYellow = "\u001B[93m";

        System.out.println(bold + lightYellow + "\nBOOKING REPORT" + reset);
        System.out.println(lightYellow + "┌───────────────────────────────────────────────┐");
        System.out.println(lightYellow + "│" + reset + bold + "  Flight number: " + reset + seat.getFlightCode() + lightYellow + "\t\t\t│");
        System.out.println(lightYellow + "│" + reset + bold + "  Departure: " + reset + seat.getDeparture() + lightYellow + "\t\t\t\t│");
        System.out.println(lightYellow + "│" + reset + bold + "  Arrival: " + reset + seat.getArrival() + lightYellow + "\t\t\t\t│");
        System.out.println(lightYellow + "│" + reset + bold + "  Departure date: " + reset + "2025-04-24\t\t\t" + lightYellow + "│");
        System.out.println(lightYellow + "│" + reset + bold + "  User data: " + reset + customer.getName() + " " + customer.getSurname() + lightYellow + "\t\t\t\t│");
        System.out.println(lightYellow + "└───────────────────────────────────────────────┘" + reset);
        System.out.println();
    }

    public static void readBookinsFromFile() {
        
    }
}
