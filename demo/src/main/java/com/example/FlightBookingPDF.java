package com.example;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class FlightBookingPDF {
    private String flightNumber;
    private String departure;
    private String arrival;
    private String departureDate;
    Seats seat = new Seats();
    User customer = new User("", "", "", "", "");
    flights flight = new flights();

    public FlightBookingPDF (String flightNumber, String departure, String arrival, String departureDate, String user) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
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

    public static void SaveToPDF(User customer, Seats seat, flights flight) {
        String outputFilePath = ("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/flight_booking.pdf");
        String imagePath = ("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/logo.png");
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            // Create PDF writer
            PdfWriter pdfWriter = new PdfWriter(outputFilePath);

            // Create PDF document
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            // Create document from the PDF document
            Document document = new Document(pdfDocument);

            // Create an image object
            Image image = new Image(ImageDataFactory.create(imagePath));

            // Add flight booking information
            if (seat.getNumBillets() == 1) {
                String[] selectedSeat = seat.getSelectedSeat();
                document.add(image);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\nBOOKING REPORT"));
                document.add(new Paragraph("  User data: " + customer.getName() + " " + customer.getSurname() + "\t\t\t"));
                document.add(new Paragraph("  Flight number: " + seat.getFlightCode() + "\t\t\t"));
                document.add(new Paragraph("  Arrival: " + seat.getArrival() + "\t\t\t\t"));
                document.add(new Paragraph("  Departure: " + seat.getDeparture() + "\t\t\t\t"));
                document.add(new Paragraph("  Departure date: " + "2025-04-24\t\t\t"));
                document.add(new Paragraph("  Seat: " + selectedSeat[0]));
                document.add(new Paragraph("  Price: € " + df.format(seat.getTotalCost()) + "\t\t\t\t"));
            } else {
                String[] selectedSeat = seat.getSelectedSeat();
                document.add(image);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("\nBOOKING REPORT"));
                document.add(new Paragraph("  User data: " + customer.getName() + " " + customer.getSurname() + "\t\t\t"));
                document.add(new Paragraph("  Flight number: " + seat.getFlightCode() + "\t\t\t"));
                document.add(new Paragraph("  Arrival: " + seat.getArrival() + "\t\t\t\t"));
                document.add(new Paragraph("  Departure: " + seat.getDeparture() + "\t\t\t\t"));
                document.add(new Paragraph("  Departure date: " + "2025-04-24\t\t\t"));
                document.add(new Paragraph("  Seats: " + Arrays.toString(selectedSeat)));
                document.add(new Paragraph("  Price: € " + df.format(seat.getTotalCost()) + "\t\t\t\t"));
            }

            // Close the document
            document.close();
        } catch (IOException e) {
            System.err.print("Errore generico: ");
            e.printStackTrace();
        }
    }

    public static void saveToText(User customer, Seats seat, flights flight) {
        String outputFilePath = "/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/flight_booking.txt";
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);

            // Write booking information to the text file
            fileWriter.write("BOOKING REPORT\n\n");
            fileWriter.write("User data: " + customer.getName() + " " + customer.getSurname() + "\n");
            fileWriter.write("Flight number: " + seat.getFlightCode() + "\n");
            fileWriter.write("Arrival: " + seat.getArrival() + "\n");
            fileWriter.write("Departure: " + seat.getDeparture() + "\n");
            fileWriter.write("Departure date: 2025-04-24\n");

            if (seat.getNumBillets() == 1) {
                String[] selectedSeat = seat.getSelectedSeat();
                fileWriter.write("Seat: " + selectedSeat[0] + "\n");
                fileWriter.write("Price: € " + df.format(seat.getTotalCost()) + "\n");
            } else {
                String[] selectedSeats = seat.getSelectedSeat();
                fileWriter.write("Seats: " + Arrays.toString(selectedSeats) + "\n");
                fileWriter.write("Price: € " + df.format(seat.getTotalCost()) + "\n");
            }

            // Close the FileWriter
            fileWriter.close();

            System.out.println();
        } catch (IOException e) {
            System.err.println("Error occurred while saving booking details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
