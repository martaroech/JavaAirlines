package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class flights {
    String file_path = "/Users/martaroech/Desktop/311/ACADEMY/ACADEMY - Java/JavaAirlines/demo/src/main/resources/flights.txt";

    //metodo per leggere file txt/csv e creare una matrice dei voli
    public String[][] fileToArray() {
        List<String[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                lines.add(fields);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String[][] linesArray = new String[lines.size()][];
        linesArray = lines.toArray(linesArray);
        return linesArray;
    }

    public void printMatriceVoli(String[][] linesArray) {
        for (String[] line : linesArray) {
            for (String value : line) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public String[] getVoloFromIdVolo(String[][] linesArray, int id_volo) {
        return linesArray[(id_volo) + 1];
    }

    public String[][] getTratteFromIdTratte(String[][] linesArray, int id_tratte) {
        String[][] matrice_tratte = new String[3][13];
        matrice_tratte[0] = linesArray[id_tratte + 1];
        matrice_tratte[1] = linesArray[id_tratte + 2];
        matrice_tratte[2] = linesArray[id_tratte + 3];
        return matrice_tratte;
    }

    public void printPartenze(String[][] linesArray) {
        for (int i = 0; i < linesArray.length; i++) {
            for (int j = 0; j < linesArray[i].length; j++) {
                System.out.print(linesArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printData(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][6]);
    }

    public void printOra(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][7]);
    }

    public void printCompagnia(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][8]);
    }

    public void printPostiTot(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][9]);
    }

    public void printNumPostiDisponibili(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][10]);
    }

    public void printNumPostiOccupati(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][11]);
    }

    public void printCostoBiglietto(String[][] linesArray, int id_volo) {
        System.out.println(linesArray[id_volo][12]);
    }

    public int getNumPostiTot(String[][] linesArray, int id_volo) {
        int posti_tot = Integer.parseInt(linesArray[id_volo][9]);
        return posti_tot;
    }

    public int getNumPostiDisponibili(String[][] linesArray, int id_volo) {
        int posti_disponibili = Integer.parseInt(linesArray[id_volo][10]);
        return posti_disponibili;
    }

    public int getNumPostiOccupati(String[][] linesArray, int id_volo) {
        int posti_occupati = Integer.parseInt(linesArray[id_volo][11]);
        return posti_occupati;
    }

    public int getCostoBiglietto(String[][] linesArray, int id_volo) {
        int costo_biglietto = Integer.parseInt(linesArray[id_volo][12]);
        return costo_biglietto;
    }

    public void printDestinazioniDaPartenza(String[][] linesArray, String partenza, String destinazione) {
        String reset = "\u001B[0m";
        String lightYellow = "\u001B[33m";
        System.out.println(lightYellow + "\nVOLI DISPONIBILI PER LA TRATTA " + partenza.toUpperCase() + " - " + destinazione.toUpperCase() + reset);
        for (int i = 0; i < linesArray.length; i++) {
            if (linesArray[i][3].equals(partenza) && linesArray[i][5].equals(destinazione)) {
                System.out.println(" ┌──────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(" │\t" + linesArray[i][2] + " ----> " + linesArray[i][4] + spaces(100 - 15 - linesArray[i][2].length() - linesArray[i][4].length()) + "│");
                System.out.println(" │\tIl giorno " + linesArray[i][6] + " alle ore " + linesArray[i][7] + spaces(100 - 28 - linesArray[i][6].length() - linesArray[i][7].length()) + "│");
                System.out.println("[" + linesArray[i][1] + "]\t" + "Compagnia aerea: " + linesArray[i][8] + spaces(100 - 25 - linesArray[i][8].length()) + "│");
                System.out.println(" │\tPosti disponibili: " + linesArray[i][10] + "/" + linesArray[i][9] + spaces(100 - 28 - linesArray[i][10].length() - linesArray[i][9].length()) + "│");
                System.out.println(" │\tPrezzo base del biglietto: € " + linesArray[i][12] + spaces(100 - 37 - linesArray[i][12].length()) + "│");
                System.out.println(" └──────────────────────────────────────────────────────────────────────────────────────────────────┘");
            } //la stringa più lunga possibile è 94. la box è lunga 100-98
        }
    }

    // Metodo per generare spazi
    public static String spaces(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    // Metodo per ottenere l'id della tratta
    public int getIdTratte(String[][] linesArray, String partenza, String destinazione) {
        int id_tratta = -1;
        for (int i = 0; i < linesArray.length; i++) {
            if (linesArray[i][3].equals(partenza) && linesArray[i][5].equals(destinazione)) {
                id_tratta = Integer.parseInt(linesArray[i][0]);
            }
        }
        return id_tratta;
    }
}
