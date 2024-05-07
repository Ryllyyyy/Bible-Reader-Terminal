package com.example.biblereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BibleReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to choose the language
        System.out.println("Choose language:");
        System.out.println("1. English");
        System.out.println("2. French");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();

        String filePath;
        if (choice == 1) {
            filePath = "bible.txt"; // English version file path
        } else if (choice == 2) {
            filePath = "French Translation.txt"; // French version file path
        } else {
            System.out.println("Invalid choice. Exiting program.");
            return;
        }

        // Consume newline
        scanner.nextLine();

        System.out.print("Enter book name (e.g. Genesis/Genese): ");
        String bookName = scanner.nextLine().toLowerCase(); // Convert input to lowercase

        System.out.print("Enter verse number (e.g. 1:1): ");
        String verse = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith(bookName + " ")) {
                    String[] parts = line.split("\t");
                    if (parts.length > 1 && parts[0].split(" ")[1].equals(verse)){
                        System.out.println(line);
                        found = true;
                        break;
                    }
                }
            }

            if (!found){
                System.out.println("Verse not found.");
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
