
package org.example.binary_word_game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Binary_word_game {

    static final String HISTORY_FILE = "binary_game_history.txt";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== BINARY WORD MATCH ===");
        System.out.print("Enter your name: ");

        String playerName = input.nextLine().trim();

        while (playerName.isEmpty()) {
            System.out.print("Name cannot be empty. Enter your name: ");
            playerName = input.nextLine().trim();
        }

        System.out.println("\nWelcome, " + playerName + "!");

        int menuChoice = 0;

        while (menuChoice != 4) {

            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Play Game");
            System.out.println("2. View Game History");
            System.out.println("3. Change Player");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                menuChoice = Integer.parseInt(input.nextLine());

                switch (menuChoice) {

                    case 1:
                        int score = playGame(input, playerName);
                        saveHistory(playerName, score);
                        break;

                    case 2:
                        showHistory();
                        break;

                    case 3:
                        System.out.print("Enter new player name: ");
                        String newName = input.nextLine().trim();

                        if (!newName.isEmpty()) {
                            playerName = newName;
                            System.out.println(
                                    "Welcome, " + playerName + "!"
                            );
                        } else {
                            System.out.println(
                                    "Name cannot be empty. Player unchanged."
                            );
                        }
                        break;

                    case 4:
                        System.out.println(
                                "Thank you for playing, " + playerName + "!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice! Please choose 1 to 4."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        input.close();
    }

    public static int playGame(Scanner input, String playerName) {

        System.out.println("\n=== GAME START ===");
        System.out.println("Player: " + playerName);

        System.out.println("\nINSTRUCTIONS:");
        System.out.println("1. A word will be shown in every round.");
        System.out.println("2. Each letter has a decimal number");
        System.out.println("3. Convert each decimal number from Base 10 to Base 2.");
        System.out.println("4. Match your answer with the correct binary choice.");
        System.out.println("5. There are 5 rounds.");
        System.out.println("6. Choose only numbers 1 to 5.");
        System.out.println("7. Each correct answer is worth 1 point.");


        int score = 0;
        int round = 1;

        String[] words = {"CAT", "SIT", "MAP", "LAP", "BAD"};

        while (round <= 5) {

            System.out.println("\n--- ROUND " + round + " OF 5 ---");

            String word = words[round - 1];

            System.out.println("\nWord: " + word);
            System.out.println("\nLetter Values:");

            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                int number = letter;

                System.out.println(letter + " = " + number);
            }

            ArrayList<String> choices = new ArrayList<>();

            for (String w : words) {
                choices.add(w);
            }

            Collections.shuffle(choices);

            System.out.println("\nBinary Choices:");

            for (int i = 0; i < choices.size(); i++) {
                String binaryOption = wordToBinary(choices.get(i));
                System.out.println((i + 1) + ". " + binaryOption);
            }

            int choice = 0;

            while (choice < 1 || choice > 5) {
                System.out.print("\nChoose the correct binary (1-5): ");

                try {
                    choice = Integer.parseInt(input.nextLine());

                    if (choice < 1 || choice > 5) {
                        System.out.println(
                                "Invalid choice! Enter a number from 1 to 5."
                        );
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    choice = 0;
                }
            }

            String selectedWord = choices.get(choice - 1);

            if (selectedWord.equals(word)) {
                System.out.println("CORRECT!!");
                score++;
            } else {
                System.out.println("WRONG!");
            }

            System.out.println("Score: " + score);
            round++;
        }

        System.out.println("\n=== GAME OVER ===");
        System.out.println("Player: " + playerName);
        System.out.println("Final Score: " + score + "/5");

        if (score == 5) {
            System.out.println("Perfect score! GREAT JOB!");
        } else if (score >= 3) {
            System.out.println("Good Job!");
        } else {
            System.out.println("Keep practicing your conversions!");
        }

        return score;
    }

    public static void saveHistory(String playerName, int score) {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String date = now.format(formatter);

        try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {

            writer.write(
                    playerName + " | Score: " + score + "/5 | " + date
                            + System.lineSeparator()
            );

            System.out.println("Game history saved!");

        } catch (IOException e) {
            System.out.println("Unable to save game history.");
        }
    }

    public static void showHistory() {

        System.out.println("\n=== GAME HISTORY ===");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(HISTORY_FILE))) {

            String record = reader.readLine();

            if (record == null) {
                System.out.println("No game history yet.");
                return;
            }

            do {
                System.out.println(record);
                record = reader.readLine();
            } while (record != null);

        } catch (IOException e) {
            System.out.println("No game history found yet.");
        }
    }

    public static String wordToBinary(String word) {

        String binaryWord = "";

        for (int i = 0; i < word.length(); i++) {

            char letter = word.charAt(i);
            int number = letter;

            String binary = String.format(
                    "%8s",
                    Integer.toBinaryString(number)
            ).replace(' ', '0');

            binaryWord += binary + " ";
        }

        return binaryWord;
    }
}
