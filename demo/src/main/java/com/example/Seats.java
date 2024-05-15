package com.example;

public class Seats {
    private int routeId;
    private int flightId;
    private String flightCode;
    private String departure;
    private String arrival;
    private int totalSeats = 120;
    private int rows = 6;
    private int cols = 20;
    private boolean[][] available =  new boolean[rows][cols];
    private int nAvailable = totalSeats;
    private int nOccupied = 0;
    private int numBillets;
    private String[] selectedSeat;
    private double price;
    private double priceMoltBusiness = 1.75;
    private double priceMoltPremium = 1.10;
    private double priceMoltEconomy = 0.75;
    private double totalCost;

    // Metodo per generare casualmente i posti a sedere per ogni volo - non serve più ma è figo quindi lo tengo
    /*public void generateSeatsMap() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                available[i][j] = rand.nextBoolean();
            }
        }
    }*/

    // Metodi Setter e Getter -----------------------------------------------------------------------------
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
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

    public String getFlightCode() {
        return flightCode;
    }
    
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getCols() {
        return cols;
    }

    public void setAvailable(boolean[][] available) {
        this.available = available;
    }

    public boolean[][] getAvailable() {
        return available;
    }

    public boolean getAvailabilitySeat(int coordX, int coordY) {
        return available[coordY][coordX];
    }

    public void setnAvailable(int nAvailable) {
        this.nAvailable = nAvailable;
    }

    public int getnAvailable() {
        return nAvailable;
    }

    public void setnOccupied(int nOccupied) {
        this.nOccupied = nOccupied;
    }

    public int getnOccupied() {
        return nOccupied;
    }

    public void setNumBillets(int numBillets) {
        this.numBillets = numBillets;
    }
    
    public int getNumBillets() {
        return numBillets;
    }

    public void setSelectedSeat(String[] selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public String[] getSelectedSeat() {
        return selectedSeat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPriceMoltBusiness(double priceMoltBusiness) {
        this.priceMoltBusiness = priceMoltBusiness;
    }
    
    public double getPriceMoltBusiness() {
        return priceMoltBusiness;
    }

    public void setPriceMoltPremium(double priceMoltPremium) {
        this.priceMoltPremium = priceMoltPremium;
    }

    public double getPriceMoltPremium() {
        return priceMoltPremium;
    }

    public void setPriceMoltEconomy(double priceMoltEconomy) {
        this.priceMoltEconomy = priceMoltEconomy;
    }

    public double getPriceMoltEconomy() {
        return priceMoltEconomy;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }
    // ----------------------------------------------------------------------------------------------------

    // Metodo per stampare i posti a sedere
    static void printSeatsMap(boolean[][] available, int nAvailable, int nOccupied, int rows, int cols) {
        System.out.println();
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String lightYellow = "\u001B[33m";
        String reset = "\u001B[0m";
        int n = 1;
        char c = 'A';
        String[] headers = {"Business", "Premium", "Economy"};
        
        // STAMPA DEI POSTI A SEDERE
        System.out.println(reset + lightYellow + "\nMAPPATURA POSTI A SEDERE:" + reset);
        System.out.println(green + "Posti disponibili: " + (nAvailable + reset));
        System.out.println(red + "Posti occupati: " + (nOccupied + reset + "\n"));
        
        // Stampa delle intestazioni
        int s = 0;
        System.out.print(" ");
        for (String header : headers) {
            if (header.equals("Business")) {
                System.out.printf("%16s", "  " + header);
                System.out.print("\t    ");
                s++;
            } else if (header.equals("Premium")) {
                System.out.printf("%19s", "       " + header);
                System.out.print("\t");
                s++;
            } else {
                System.out.printf("%23s", "    " + header);
                System.out.print("\t    ");
            }
            if (s == 2) {
                System.out.print("\t");
            }
        }
        System.out.println();
        // Stampa delle lettere
        System.out.print("   ");
        for (int i = 0; i < cols; i++) {
            if (i == (cols / 3) || (i == ((cols /3) * 2) + 1)) {
                System.out.print(reset + "    " + reset);
            }
            System.out.print(reset + c + "   ");
            c++;
            
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(reset + n + " " + reset); // Stampa dei numeri
            n++;
            for (int j = 0; j < cols; j++) {
                if (j == (cols / 3) || (j == ((cols /3) * 2) + 1)) { // Suddivide l'aereo in 3 categorie
                    System.out.print(reset + " \u2551  " + reset);
                }
                if (available[i][j]) {
                    System.out.print(green + "[\u2593] " + reset);
                } else {
                    System.out.print(red + "[\u2593] " + reset);
                }
            }
            if (i == ((rows / 2) - 1)) { // Stampa il corridoio centrale
                System.out.println("\n");
            } else {
                System.out.println();
            }
        }
        System.out.println();
    }
}
