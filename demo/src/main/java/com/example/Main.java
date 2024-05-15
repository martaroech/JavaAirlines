package com.example;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
@SuppressWarnings("resource")

public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User(null, null, null, null, null);
        Seats seats = new Seats();
        flights flights = new flights();
        boolean loop = true;
        
        while (loop) {
            user = menu1(user);
            printLogo();
            
            menu2(user, seats, flights);
        }
    }
    
    private static void printLogo() {
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String lightYellow = "\u001B[93m";
        System.out.println(lightYellow);
        System.out.println(bold);
        System.out.println("   __          __     _                                    _          ");
        System.out.println("   \\ \\        / /    | |                                  | |         ");
        System.out.println("    \\ \\  /\\  / / ___ | |  ___   ___   _ __ ___    ___     | |_   ___  ");
        System.out.println("     \\ \\/  \\/ / / _ \\| | / __| / _ \\ | '_ ` _ \\  / _ \\    | __| / _ \\ ");
        System.out.println("      \\  /\\  / |  __/| || (__ | (_) || | | | | ||  __/    | |_ | (_) |");
        System.out.println("       \\/  \\/   \\___||_| \\___| \\___/ |_| |_| |_| \\___|     \\__| \\___/ ");
        System.out.println("                                                                   ");
        System.out.println("      _                                 _        _  _                   ");
        System.out.println("     | |                         /\\    (_)      | |(_)                  ");
        System.out.println("     | |  __ _ __   __  __ _    /  \\    _  _ __ | | _  _ __    ___  ___ ");
        System.out.println(" _   | | / _` |\\ \\ / / / _` |  / /\\ \\  | || '__|| || || '_ \\  / _ \\/ __|");
        System.out.println("| |__| || (_| | \\ V / | (_| | / ____ \\ | || |   | || || | | ||  __/\\__ \\");
        System.out.println(" \\____/  \\__,_|  \\_/   \\__,_|/_/    \\_\\|_||_|   |_||_||_| |_| \\___||___/");
        System.out.println("                                                                        ");
        System.out.println(reset);
    }

    // Metodo per stampare il primo menu
    private static User menu1(User user) {
        Scanner scan = new Scanner(System.in);
        String reset = "\u001B[0m";
        String lightYellow = "\u001B[93m";
        String red = "\u001B[91m";
        boolean loop2 = true;

        while (loop2) {
            System.out.println(lightYellow + "Scegli un'opzione:" + reset);
            System.out.println("1. Effettua il login");
            System.out.println("2. Registrati");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            if (scan.hasNextInt()) {
                int choice1 = scan.nextInt();
                scan.nextLine();
                System.out.println();
                switch (choice1) {
                    case 1:
                        User.login(user);
                        if (user.getName() == null) {
                            System.out.println();
                            continue;
                        }
                        System.out.println();
                        loop2 = false;
                        break;
                    case 2:
                        User.createAccount(user);
                        System.out.println();
                        loop2 = false;
                        break;
                    case 3:
                        System.out.println(lightYellow + "\nGrazie e arrivederci!" + reset);
                        loop2 = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println(red + "ATTENZIONE! Scelta non valida! Riprova.\n" + reset);
                }
            } else {
                System.out.println(red + "ATTENZIONE! Scelta non valida! Riprova." + reset);
                menu1(user);
            }
        }
        return user;
    }

    // Metodo per stampare il secondo menu
    private static void menu2(User user, Seats seats, flights flights) {
        Scanner scan = new Scanner(System.in);
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String lightYellow = "\u001B[93m";
        String red = "\u001B[91m";
        boolean loop3 = true;

        while (loop3) {
            System.out.println(lightYellow + "Scegli un'opzione:" + reset);
            System.out.println("1. Visualizza il menu utente");
            System.out.println("2. Prenota un volo");
            System.out.println("3. Visualizza prenotazioni");
            System.out.println("4. Cambia utente");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            if (scan.hasNextInt()) {
                int choice2 = scan.nextInt();
                scan.nextLine();
                switch (choice2) {
                    case 1:
                        User.viewProfile(user);
                        break;
                    case 2:
                        System.out.println();
                        String[] results = selectAirports(seats);
                        if(!results[0].equals("") || !results[1].equals("")) {
                            String departure = results[0];
                            String arrival = results[1];
                            
                            int routeNumber = flights.getIdTratte(flights.fileToArray(), departure, arrival);
                            
                            flights.printDestinazioniDaPartenza(flights.fileToArray(), departure, arrival);
                            
                            System.out.print("Inserisci il numero del volo: ");
                            if (scan.hasNextInt()) {
                                int flightNumber = scan.nextInt();
                                scan.nextLine();
                                if (flightNumber >= 1 && flightNumber <= 3) {
                                    if (flightNumber > 0 && flightNumber <= flights.fileToArray()[routeNumber].length) {
                                        System.out.println();
                                        Main.readFromFile(seats, routeNumber, flightNumber);
                                        System.out.println("Tratta di volo da " + departure + " a " + arrival + " con il volo n°" + flightNumber + " selezionata.\n\n");
                            
                                        System.out.println(lightYellow + bold + "SELEZIONE POSTI A SEDERE" + reset);
                                        selectSeat(seats);
                                        writeToFile(seats, routeNumber, flightNumber);
                                    }
                                } else {
                                    System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                                }
                            }
                        }
                        break;
                    case 3:
                        bookingsPDF(user, seats, flights);
                        String pdfPath = "/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/flight_booking.pdf";
                        EmailSender.sendEmailWithAttachment(user.getEmail(), pdfPath, seats, user);
                        System.out.println("\nControlla la mail ;) (Anche nelle spam...)\n");
                        break;
                    case 4:
                        System.out.println(lightYellow + "\nLogout effettuato correttamente. " + bold + "Grazie e arrivederci!\n" + reset);
                        user.setNull();
                        menu1(user);
                        break;
                    case 5:
                        System.out.println(lightYellow + "\nLogout effettuato correttamente. " + bold + "Grazie e arrivederci!\n" + reset);
                        System.exit(0);
                        break;
                    default:
                        System.out.println(red + "\nATTENZIONE! Scelta non valida! Riprova.\n" + reset);
                }
            } else {
                System.out.println(red + "ATTENZIONE! Scelta non valida! Riprova." + reset);
                menu2(user, seats, flights);
            }
        }
    }


    // Metodo per leggere da file
    static void readFromFile(Seats seats, int routeNumber, int flightNumber) {
        String reset = "\u001B[0m";
        String red = "\u001B[91m";
        try {
            File file = new File("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/SeatsPerFlight.txt");
            Scanner scan = new Scanner(file);
            String[] data = new String[126];
            Seats seatsPerFlight = new Seats();
            String routeNumberString = String.valueOf(routeNumber);
            String flightNumberString = String.valueOf(flightNumber);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                boolean[][] available = new boolean[seats.getRows()][seats.getCols()];
                data = line.split(";");
                if (data[0].equalsIgnoreCase("routeId")) {
                    continue;
                } else {
                    seatsPerFlight.setRouteId(Integer.parseInt(data[0]));
                    seatsPerFlight.setFlightId(Integer.parseInt(data[1]));
                    int count = 2;
                    int nAvailable = seatsPerFlight.getTotalSeats();
                    int nOccupied = 0;
                    for (int i = 0; i < seats.getRows(); i++) {
                        for (int j = 0; j < seats.getCols(); j++) {
                            available[i][j] = Boolean.parseBoolean(data[count]);
                            count++;
                            if (available[i][j] == false) {
                                nAvailable--;
                                nOccupied++;
                            }
                        }
                    }
                    seatsPerFlight.setAvailable(available);
                    seatsPerFlight.setnAvailable(nAvailable);
                    seatsPerFlight.setnOccupied(nOccupied);
                    seatsPerFlight.setPrice(Double.parseDouble(data[125]));
                    if ((data[0].equals(routeNumberString)) && (data[1].equals(flightNumberString))) {
                        seats.setAvailable(available);
                        seats.setRouteId(routeNumber);
                        seats.setFlightId(flightNumber);
                        seats.setRows(seatsPerFlight.getRows());
                        seats.setCols(seatsPerFlight.getCols());
                        seats.setTotalSeats(seatsPerFlight.getTotalSeats());
                        seats.setnAvailable(seatsPerFlight.getnAvailable());
                        seats.setnOccupied(seatsPerFlight.getnOccupied());
                        seats.setPrice(seatsPerFlight.getPrice());
                        seats.setPriceMoltBusiness(seatsPerFlight.getPriceMoltBusiness());
                        seats.setPriceMoltPremium(seatsPerFlight.getPriceMoltPremium());
                        seats.setPriceMoltEconomy(seatsPerFlight.getPriceMoltEconomy());
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(red + "File non trovato: " + e.getMessage() + reset);
        } catch (Exception e) {
            System.out.println(red + "Errore generico durante l'importazione dei dati: " + e.getMessage() + reset);
        }
    }

    // Metodo per leggere i dati esistenti nel file e riscriverli cambiando solo i dati relativi alla tratta selezionata
    static void writeToFile(Seats seats, int routeNumber, int flightNumber) {
        String reset = "\u001B[0m";
        String red = "\u001B[91m";
        try {
            File file = new File("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/SeatsPerFlight.txt");
            File tempFile = new File("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/TempSeatsPerFlight.txt");
            FileWriter fileWriter = new FileWriter(tempFile);
    
            Scanner scan = new Scanner(file);
    
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(";");
                if (data[0].equalsIgnoreCase("routeId")) {
                    continue;
                } else {
                    if (data[0].equals(String.valueOf(routeNumber)) && data[1].equals(String.valueOf(flightNumber))) {
                        fileWriter.write(data[0] + ";" + data[1] + ";");
                        for (int i = 0; i < seats.getRows(); i++) {
                            for (int j = 0; j < seats.getCols(); j++) {
                                fileWriter.write(seats.getAvailable()[i][j] + ";");
                                if (seats.getAvailable()[i][j] = false) {
                                    seats.setnAvailable(seats.getnAvailable() - 1);
                                    seats.setnOccupied(seats.getnOccupied() + 1);
                                }
                            }
                        }
                        fileWriter.write(seats.getTotalSeats() + ";" + seats.getnAvailable() + ";" + seats.getnOccupied() + ";" + seats.getPrice());
                        fileWriter.write("\n");
                    } else {
                        fileWriter.write(line + "\n");
                    }
                }
            }
            fileWriter.close();
            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            System.out.println(red + "Errore durante l'operazione di scrittura: " + e.getMessage() + reset);
        }
    }

    // Metodo per salvare su file posti generati casualmente -- NON SERVE PIU'
    /* static void writeToFile(Seats seats) throws IOException {
        try {
            Random rand = new Random();
            FileWriter file = new FileWriter("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/SeatsPerFlight.txt", false);
            int routeId = 1; // Variabile per il numero delle tratte dei voli
            
            file.write("routeId;flightId;seats[][];totalSeats;available;occupied\n"); // Scrittura intestazione colonne
            while (routeId <= 90) {
                int flightId = 1; // Variabile per il numero totale dei voli
                for (int i = 0; i < 3; i++) {
                    file.write(routeId + ";" + flightId + ";");
                    int available = 120, occupied = 0;
                    for (int j = 0; j < seats.getTotalSeats(); j++) {
                        boolean seat = rand.nextBoolean();
                        file.write(seat + ";");
                        if (seat == false) {
                            available--;
                            occupied++;
                        }
                    }
                    file.write(seats.getTotalSeats() + ";" + available + ";" + occupied);
                    file.write("\n");
                    flightId++;
                }
                routeId++;
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Errore nella scrittura su file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore generico durante l'esportazione dei dati: " + e.getMessage());
        }
    } */

    // Metodo per selezionare i posti a sedere
    static void selectSeat(Seats seats) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        String reset = "\u001B[0m";
        String italic = "\u001B[3m";
        String bold = "\u001B[1m";
        String lightYellow = "\u001B[93m";
        String lightBlue = "\u001B[94m";
        String red = "\u001B[31m";

        System.out.print("Quanti biglietti vuoi acquistare? ");
        if (scan.hasNextInt()) {
            int numBillets = scan.nextInt();
            scan.nextLine();
            if (numBillets > 0 && numBillets < seats.getTotalSeats() && numBillets <= seats.getnAvailable()) {
                seats.setNumBillets(numBillets);
                String[] reservedSeats = {""};
                double totalPrice = 0;
                
                for (int i = 0; i < numBillets; i++) {
                    Seats.printSeatsMap(seats.getAvailable(), seats.getnAvailable(), seats.getnOccupied(), seats.getRows(), seats.getCols());
                    System.out.print("Scegli un posto a sedere " + lightBlue + "(" + italic + "Esempio: A1" + reset + lightBlue + ")" + reset + ": ");
                    if (scan.hasNextLine()) {
                        String input = scan.nextLine();
                        boolean control = checkAvailability(seats, input);
                        double price = seats.getPrice();

                        if (control) {
                            char c = input.toUpperCase().charAt(0);
                            int n = input.charAt(1) - '0';
                            if (seats.getAvailabilitySeat((c-'A'), (n -1))) {
                                if (((c - 'A') + 1) <= (seats.getCols() / 3)) {
                                    price = price * (double)seats.getPriceMoltBusiness();
                                    System.out.println("\nIl posto a sedere " + c + n + " è disponibile. Il prezzo del biglietto è di € " + df.format(price));
                                } else if (((c - 'A') +1 ) > (seats.getCols() / 3) && (((c - 'A') + 1) < ((seats.getCols() / 3) * 2) + 2)) {
                                    price = price * (double)seats.getPriceMoltPremium();
                                    System.out.println("\nIl posto a sedere " + c + n + " è disponibile. Il prezzo del biglietto è di € " + df.format(price));
                                } else {
                                    price = price * (double)seats.getPriceMoltEconomy();
                                    System.out.println("\nIl posto a sedere " + c + n + " è disponibile. Il prezzo del biglietto è di € " + df.format(price));
                                }
                                System.out.print("Vuoi prenotarlo? [Y/N] ");
                                if (scan.hasNextLine()) {
                                    String confirm = scan.nextLine();
                                    if (!confirm.isBlank()) {
                                        if (confirm.equalsIgnoreCase("Y")) {
                                            seats.setnAvailable(seats.getnAvailable() - 1);
                                            seats.setnOccupied(seats.getnOccupied() + 1);
                                            if (numBillets == 1) {
                                                seats.getAvailable()[n-1][c-'A'] = false;
                                                reservedSeats[0] = c + "" + n;
                                                seats.setSelectedSeat(reservedSeats);
                                                seats.setTotalCost(price);
                                                System.out.println("\nPosto a sedere " + lightYellow + c + n + reset + " prenotato per il costo di " + lightYellow + "€ " + df.format(price) + reset + ". Grazie mille, ti auguriamo un buon viaggio!\n");
                                            } else {
                                                seats.getAvailable()[n-1][c-'A'] = false;
                                                reservedSeats[0] += c + "" + n + ";";
                                                totalPrice += price;
                                                if (i < (numBillets - 2)) {
                                                    System.out.println("Posto a sedere " + c + n + " prenotato. Prosegui con i prossimi.\n");
                                                } else if (i == (numBillets - 2)) {
                                                    System.out.println("Posto a sedere " + c + n + " prenotato. Prosegui con il prossimo.\n");
                                                } else if (i == numBillets - 1) {
                                                    System.out.println(lightYellow + bold + "\nPRENOTAZIONE CONFERMATA" + reset);
                                                    seats.setTotalCost(totalPrice);
                                                    System.out.println("Hai prenotato un totale di " + lightYellow + numBillets + " biglietti " + reset + "per un costo totale di " + lightYellow + "€ " + df.format(totalPrice) + reset + ". Grazie mille, ti auguriamo un buon volo!\n");
                                                    seats.setSelectedSeat(reservedSeats);
                                                    //reservedSeats[0] += df.format(price) + ";";
                                                }
                                            }
                                        } else if (confirm.equalsIgnoreCase("N")) {
                                            i--;
                                            System.out.println("Prenotazione annullata.\n");
                                        } else {
                                            i--;
                                            System.out.println(red + "ATTENZIONE! Inputo non valido. Riprova.\n" + reset);
                                        }
                                    } else {
                                        i--;
                                        System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                                    }
                                }
                                else {
                                    i--;
                                    System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                                }
                            } else {
                                i--;
                                System.out.println(red + "Il posto selezionato non è disponibile! Scegline un altro.\n" + reset);
                            }
                        } else {
                            i--;
                        }
                    } else {
                        i--;
                        System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                    }
                }
            } else {
                System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
            }
        }
    }

    static boolean checkAvailability(Seats seats, String input) {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        
        if (input.length() == 2) {
            char c = input.toUpperCase().charAt(0);
            int n = input.charAt(1) - '0';
            if ((c >= 'A' && c <= 'T') && (n >= 1 && n <= 6)) {
                return true;
            } else {
                System.out.println(red + "ATTENZIONE! Hai inserito delle coordinate sbagliate! Riprova." + reset);
                return false;
            }
        } else {
            System.out.println(red + "ATTENZIONE! Hai inserito delle coordinate sbagliate! Riprova." + reset);
            return false;
        }
    }

    static String[] selectAirports(Seats seats) {
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String lightYellow = "\u001B[93m";
        String strikerought = "\u001B[9m";
        String grey = "\u001B[90m";
        String red = "\u001B[31m";
        Scanner scan = new Scanner(System.in);
        String[] results = {"", ""};
        final String[] airports = {"Italia", "Germania", "Francia", "Spagna", "Regno Unito", "Paesi Bassi", "Svezia", "Austria", "Svizzera", "Belgio"};

        System.out.println(lightYellow + bold + "SELEZIONE AEROPORTI E VOLO" + reset);
        boolean check1 = false;
        while (check1 == false) {   
            System.out.println("Scegli l'aeroporto di partenza:");
            int i = 1;
            for (String airport : airports) {
                System.out.println(i + ". " + airport);
                i++;
            }
            System.out.print("Scelta: ");
            
            if (scan.hasNextInt()) {
                int departureChoice = scan.nextInt();
                scan.nextLine();
                
                if (departureChoice >= 1 && departureChoice <= airports.length) {
                    check1 = true;
                    String departure = airports[departureChoice-1];
                    seats.setDeparture(departure);
                    boolean check2 = false;
                    while (check2 == false) {
                        System.out.println("\nScegli l'aeroporto di arrivo:");
                        i = 0;
                        for (String airport : airports) {
                            i++;
                            if (i != departureChoice) {
                                System.out.println(i + ". " + airport);
                            } else {
                                System.out.println(grey + strikerought + i + ". " + airport + reset);
                            }
                        }
                        System.out.print("Scelta: ");
                        
                        if (scan.hasNextInt()) {
                            int arrivalChoice = scan.nextInt();
                            scan.nextLine();
                            if (arrivalChoice >= 1 && arrivalChoice <= airports.length) {
                                if (arrivalChoice != departureChoice) {
                                    check2 = true;
                                    String arrival = airports[arrivalChoice-1];
                                    seats.setArrival(arrival);
                                    results[0] = departure;
                                    results[1] = arrival;
                                    seats.setFlightCode(generateFlightCode(departure, arrival, departureChoice, arrivalChoice));
                                }
                                else {
                                    System.out.println(red + "ATTENZIONE! Non puoi scegliere lo stesso aeroporto di partenza come aeroporto di arrivo! Riprova.\n" + reset);
                                }
                            } else {
                                System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                                
                            }
                        } else {
                            System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                            break;
                        }
                    }
                } else {
                    System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                }
            } else {
                System.out.println(red + "ATTENZIONE! Input non valido. Riprova.\n" + reset);
                break;
            }
        }
        return results;
    }

    private static String generateFlightCode(String departure, String arrival, int departureChoice, int arrivalChoice) {
        char c1 = departure.toUpperCase().charAt(0);
        char c2 = departure.toUpperCase().charAt(1);
        char c3 = (char)(departureChoice + '0');
        char c4 = (char)(arrivalChoice + '0');
        char c5 = arrival.toUpperCase().charAt(0);
        char c6 = arrival.toUpperCase().charAt(1);
        
        String flightCode = String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) +String.valueOf(c5) + String.valueOf(c6);
        return flightCode;
    }

    public static void bookingsPDF(User user, Seats seats, flights flights) {
        FlightBookingPDF.SaveToPDF(user, seats, flights);
        FlightBookingPDF.saveToText(user, seats, flights);
    }

    /* static void sendEmail(User user) {
        String recipientEmail = user.getEmail(); // email ricevente
        
        String attachmentPath = "/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/flight_booking.pdf"; // Path 
        EmailSender.sendEmailWithAttachment(recipientEmail, attachmentPath);
    } */
}
