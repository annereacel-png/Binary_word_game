package
org.example.binary_word_game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Binary_word_game {
    public static void main(String[] args) {

        Scanner input = new
                Scanner(System.in);


        System.out.println("===BINARY WORD MATCH===");
        System.out.println("\nINSTRUCTIONS:");
        System.out.println("1. A word will be shown in every round.");
        System.out.println("2. Each letter has a decimal number (A=1, B=2, ..., Z=26).");
        System.out.println("3. Convert each decimal number from Base 10 to Base 2.");
        System.out.println("4. Match your answer with the correct binary choice.");
        System.out.println("5. There are 5 rounds.");
        System.out.println("6. Choose only numbers 1 to 5.");
        System.out.println("7. Each correct answer is worth 1 point.");

        int score = 0;
        int round = 1;

        String[] words = {"CAT", "SIT", "MAP", "LAP", "BAD"};

        //diria add ang instructions

        while (round <= 5) {

            System.out.println("\n---Round " + round + "---");

            String word = words[round - 1];
            System.out.println("\n=== ROUND " + round + " ===");
            System.out.println("Word: " + word);

            System.out.println("\nConvert each letter from Decimal to Binary:");

            for (int i = 0; i< word.length(); i++) {
                char letter = word.charAt(i);
                int number = letter;

                System.out.println(letter + " = " + number);

                System.out.println("\nEach letter uses 8 bits.");
            }

            System.out.println("\nYour word is: " + word);
            System.out.println("\nBinary Choices: ");

        ArrayList<String> choices = new ArrayList<>();
        for (String w : words) {
        choices.add(w);
        }

        Collections.shuffle(choices);

            for (int i = 0; i < words.length; i++) {
                String binaryOption = wordToBinary(words[i]);
                System.out.println((i + 1) + ". " + binaryOption);
            }
            System.out.println("\nChoose the correct binary (1-5): ");
            int choice = input.nextInt();
            System.out.println("You chose option " + choice);

            if (choice == round) {
                System.out.println("CORRECT!! :D ");
                score++;
            } else {
                System.out.println("WRONG! :[ ");
            }

            System.out.println("Score: " + score);

        Collections.shuffle(choices);

            //game goes here

            round++;
        }

        System.out.println("\n=== GAME OVER ===");
        System.out.println("Final Score: " + score + "/5");

        if (score == 5 ) {
            System.out.println("Perfect score! GREAT JOB! ◝(ᵔᗜᵔ)◜");
        }
        else if (score >= 3 ){
            System.out.println("Good Job! ദ്ദി ˉ͈̀꒳ˉ͈́ )✧");
        }
        else {
            System.out.println("Keep practicing your conversions!");
        }
    }

        public static String wordToBinary (String word){

            String binaryWord = "";

            for (int i = 0; i < word.length(); i++) {

                char letter = word.charAt(i);
                int number = letter;

                String binary = String.format("%8s", Integer.toBinaryString(number)).replace(' ', '0');

                binaryWord += binary;
            }

            return binaryWord;

        }
    }






