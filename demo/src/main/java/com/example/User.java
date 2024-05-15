package com.example;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
@SuppressWarnings("resource")

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

    public User (String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /* public User () {
        this.username = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.email = "";
    } */

    //Getter e Setter di Username 
    public String getUsername() {
        return username; 
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Getter e Setter di Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getter e Setter di Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Getter e Setter di Surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //Getter e Setter di Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Set null
    public void setNull() {
        this.username = null;
        this.password = null;
        this.name = null;
        this.surname = null;
        this.email = null;
    }

    //Creazione Account Utente
    public static void createAccount(User utente) {
        Console console = System.console();
        Scanner scan = new Scanner(System.in);
        String reset = "\u001b[0m";
        String italic = "\u001b[3m";
        String bold = "\u001b[1m";
        String lightBlue = "\u001b[94m";

        System.out.println(bold + "Inserisci l'username" + reset);
        System.out.print("Username: ");
        if (scan.hasNextLine()) {
            utente.setUsername(scan.nextLine());
            System.out.println();
            System.out.println(bold + "Inserisci la password " + reset + lightBlue + "(" + italic + "Deve essere lunga almeno 6 caratteri, avere almeno una maiuscola, un carattere speciale e un numero." + reset + lightBlue + ")" + reset);
            while (true) {
                System.out.print("Password: ");
                char[] passwordChars = console.readPassword();
                String password = new String(passwordChars);
                if (password.length() < 6 || password.isEmpty() || password.isBlank()) {
                    System.out.println("La password deve essere lunga almeno 6 caratteri");
                    continue;
                } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>-_].*")) {
                    System.out.println("La password deve contenere almeno un carattere speciale");
                    continue;
                } else if (!password.matches(".*[0-9].*")) {
                    System.out.println("La password deve contenere almeno un numero");
                    continue;
                } else {
                    utente.setPassword(password);
                    System.out.println();
                    break;
                }
            }
            System.out.println(bold + "Inserisci il nome" + reset);
            while (true) {
                System.out.print("Nome: ");
                String input = scan.nextLine();
                String name = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                
                if (!name.matches("[a-zA-Z]+") || name == null || name.isEmpty() || name.isBlank() || name.equals("")) {
                    System.out.println("Il nome non può contenere numeri o essere vuoto!");
                    continue;
                } else {
                    utente.setName(name);
                    System.out.println();
                    break;
                }
            }
            System.out.println(bold + "Inserisci il cognome" + reset);
            while (true) {
                System.out.print("Cognome: ");
                String input = scan.nextLine();
                String surname = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                
                if (!surname.matches("[a-zA-Z]+") || surname == null || surname.isEmpty() || surname.isBlank() || surname.equals("")) {
                    System.out.println("Il cognome non può contenere numeri o essere vuoto!");
                    continue;
                } else {
                    utente.setSurname(surname);
                    System.out.println();
                    break;
                }
            }
            System.out.println(bold + "Inserisci la tua email" + reset);
            while (true) {
                System.out.print("Email: ");
                String email = scan.nextLine();

                if (email == null || email.isEmpty() || email.isBlank() || email.equals("") || !email.contains("@") || !email.contains(".")) {
                    System.out.println("L'email che hai inserito non è valida! Riprova.");
                    continue;
                } else {
                    utente.setEmail(email);
                    System.out.println();
                    break;
                }
            }
            saveUser(utente.getUsername(), utente.getPassword(), utente.getName(), utente.getSurname(), utente.getEmail());
        }
    }
 

    //Scrivere dati utente su file
    public static void saveUser(String username, String password, String name, String surname, String email) {
        User user = new User(username, password, name, surname, email);
        String reset = "\u001B[0m";
        String lightYellow = "\u001B[33m";
        try {
            File file = new File("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/user.txt");
            if (!file.exists()) {
                file.createNewFile();
                FileWriter writer = new FileWriter(file, true);
                writer.write("Username; Password; Name; Surname; Email\n");
                writer.close();
            }

            Scanner reader = new Scanner(file);
            boolean userExists = false;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");

                int index = 0;
                if (parts[index].trim().equals(username)) {
                    userExists = true;
                    break;
                }
            }

            reader.close();
            
            if (userExists) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Questo nome utente è già in uso. Vuoi: \n 1 - Registrarti con un altro nome utente\n 2 - Fare l'accesso");
                String answer = scan.nextLine();
                if (answer.equals("1")) {
                    User.createAccount(user);
                    return;
                } else if (answer.equals("2")) {
                    User.login(user);
                    return;
                } else {
                    System.out.println("Comando non riconosciuto");
                }

            } else {
                FileWriter writer = new FileWriter(file, true);
                writer.write(username + "; " + password + "; " + name + "; " + surname + "; " + email + "; \n");
                writer.close();
                System.out.println(lightYellow + "Utente registrato!\n" + reset);
                System.out.println(lightYellow + "Benvenuto " + username + "!" + reset);
            }    
                
        } catch (IOException e) {
            System.out.println("Qualcosa è andato storto :/");
            e.printStackTrace();
        } 
    }

    //Login utente
    public static User login(User user) {
        Scanner scan = new Scanner(System.in);
        Console console = System.console();
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        if (console == null) {
            return null;
        }
        System.out.print(bold + "Username: " + reset);
        String username = scan.nextLine();
        user.setUsername(username);
        System.out.print(bold + "Password: " + reset);
        char[] passwordChars = console.readPassword();
        String password = new String(passwordChars);
        user.setPassword(password);
    
        boolean check = checkUser(user);
        if (check) {
            return user;
        } else {
            return null;
        }
    }

    //Leggere il file per vedere se l'utente c'è già
    public static boolean checkUser(User user) {
        String reset = "\u001B[0m";
        String red = "\u001B[91m";
        String lightYellow = "\u001B[33m";
        
        try {
            File file = new File("/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/user.txt");
            Scanner scan = new Scanner(file);
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split("; ");
                if (line.length() >= 5) {
                    String savedUsername = data[0].trim();
                    String savedPassword = data[1].trim();
                    String savedName = data[2].trim();
                    String savedSurname = data[3].trim();
                    String savedEmail = data[4].trim();
                    
                    if (savedPassword.equals(user.getPassword())) {
                        if (savedUsername.equals(user.getUsername())){
                            System.out.println(lightYellow + "\nBentornato " + user.getUsername() + "!" + reset);
                            user.setUsername(savedUsername);
                            user.setPassword(savedPassword);
                            user.setName(savedName);
                            user.setSurname(savedSurname);
                            user.setEmail(savedEmail);
                            break;
                        }
                    }
                }
            }
            if (user.getName() == null || user.getSurname() == null || user.getEmail() == null) {
                System.out.println(red + "ATTENZIONE! Hai inserito una password o un nome utente errati! Riprova." + reset);
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            System.out.println(red + "ATTENZIONE! Hai inserito una password o un nome utente errati! Riprova." + reset);
            return false;
        }
    }

    // Metodo per vedere il profilo utente
    public static void viewProfile(User user) {
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        String cyan = "\u001B[36m";
        System.out.println(bold + cyan + "\nProfilo Utente:" + reset);
        System.out.println("Username:\t" + user.getUsername());
        String censoredPassword = new String(new char[user.getPassword().length()]).replace("\0", "*");
        System.out.println("Password:\t" + censoredPassword);;
        System.out.println("Nome:\t\t" + user.getName());
        System.out.println("Cognome:\t" + user.getSurname());
        System.out.println("Email:\t\t" + user.getEmail());
        System.out.println();
    }
}
