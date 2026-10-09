package org.example.binary_word_game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Binary_word_game {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===BINARY WORD MATCH===");

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

            System.out.println("\n---Round " + round + "---");

            String word = words[round - 1];

            System.out.println("\n========================================");
            System.out.println("              ROUND " + round + " OF 5");
            System.out.println("========================================");

            System.out.println("\nWord: " + word);

            System.out.println("\nLetter Values:");

            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                int number = letter;

                System.out.println(letter + " = " + number);
            }

            System.out.println("\nBinary Choices:");
            System.out.println("\nBinary Choices:");


            ArrayList<String> choices = new ArrayList<>();

            for (String w : words) {
                choices.add(w);
            }

            Collections.shuffle(choices);

            for (int i = 0; i < choices.size(); i++) {

                String binaryOption = wordToBinary(choices.get(i));

                System.out.println((i + 1) + ". " + binaryOption);
            }

            System.out.println("\nChoose the correct binary (1-5): ");

            int choice = input.nextInt();

            System.out.println("You chose option " + choice);

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
        System.out.println("Final Score: " + score + "/5");

        if (score == 5) {

            System.out.println("Perfect score! GREAT JOB! ⠀ദ്ദി( °ᗜᵔ) *.✧  ");

        } else if (score >= 3) {

            System.out.println("Good Job! ദ്ദി(•̀ ᗜ <) ");

        } else {

            System.out.println("Keep practicing your conversions! ദ്ദി( ╥ ᴗ ╥)");

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