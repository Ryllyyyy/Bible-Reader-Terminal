package com.example.biblereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BibleReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book name (e.g. Genesis): ");
        String bookName = scanner.nextLine().toLowerCase(); // Convert input to lowercase

        System.out.print("Enter verse number (e.g. 1:1): ");
        String verse = scanner.nextLine();

        // Get Bible file path, King James Version
        String filePath = "bible.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = Boolean.FALSE;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith(bookName + " ")) { // Check for book name
                    // splits into Verse and text
                    String[] parts = line.split("\t");

                    // checks that verse exists
                    if (parts.length > 1 && parts[0].split(" ")[1].equals(verse)){
                        System.out.println(line);

                        found = Boolean.TRUE;

                        break;
                    }
                }
            }

            // if no matching verse was found, print error
            if (found == Boolean.FALSE){
                System.out.println("Verse not found.");
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
