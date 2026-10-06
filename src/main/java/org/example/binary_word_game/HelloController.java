package org.example.binary_word_game;

import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HelloController {

    private Label feedbackLabel;
    private Button nextButton;

    private String[] words = {"CAT", "SIT", "MAP", "LAP", "BAD"};
    private int currentRound = 0;
    private int score = 0;
    private boolean answered = false;

    @FXML
    private VBox root;

    @FXML
    private void startGame() {

        feedbackLabel = new Label();
        feedbackLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        answered = false;

        nextButton = new Button("NEXT ROUND");
        nextButton.setOnAction(e -> nextRound());
        nextButton.setVisible(false);

        nextButton.setPrefWidth(180);
        nextButton.setPrefHeight(40);
        nextButton.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-color: #7E57C2; " +
                        "-fx-text-fill: white;"
        );

        root.getChildren().clear();
        root.setStyle("-fx-background-color: #F3E5F5;");

        String word = words[currentRound];

        Label roundLabel = new Label("ROUND " + (currentRound + 1) + " / 5");
        roundLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label wordLabel = new Label("Word: " + word);
        wordLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label instructionLabel = new Label("Convert each letter from Decimal to Binary.");
        instructionLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox decimalBox = new VBox(5);
        decimalBox.setAlignment(javafx.geometry.Pos.CENTER);

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int number = letter;

            Label letterLabel = new Label(letter + " = " + number);
            letterLabel.setStyle("-fx-font-size: 20px;");
            decimalBox.getChildren().add(letterLabel);
        }

        root.getChildren().add(roundLabel);
        root.getChildren().add(wordLabel);
        root.getChildren().add(instructionLabel);
        root.getChildren().add(decimalBox);
        root.getChildren().add(feedbackLabel);
        root.getChildren().add(nextButton);

        ArrayList<String> choices = new ArrayList<>();

        for (String w : words) {
            choices.add(w);
        }

        Collections.shuffle(choices);

        Button choice1 = new Button("1. " + wordToBinary(choices.get(0)));
        Button choice2 = new Button("2. " + wordToBinary(choices.get(1)));
        Button choice3 = new Button("3. " + wordToBinary(choices.get(2)));
        Button choice4 = new Button("4. " + wordToBinary(choices.get(3)));
        Button choice5 = new Button("5. " + wordToBinary(choices.get(4)));

        choice1.setStyle("-fx-font-size: 16px; -fx-background-color: #7E57C2; -fx-text-fill: white;");
        choice2.setStyle("-fx-font-size: 16px; -fx-background-color: #7E57C2; -fx-text-fill: white;");
        choice3.setStyle("-fx-font-size: 16px; -fx-background-color: #7E57C2; -fx-text-fill: white;");
        choice4.setStyle("-fx-font-size: 16px; -fx-background-color: #7E57C2; -fx-text-fill: white;");
        choice5.setStyle("-fx-font-size: 16px; -fx-background-color: #7E57C2; -fx-text-fill: white;");

        choice1.setPrefWidth(300);
        choice2.setPrefWidth(300);
        choice3.setPrefWidth(300);
        choice4.setPrefWidth(300);
        choice5.setPrefWidth(300);

        choice1.setPrefHeight(40);
        choice2.setPrefHeight(40);
        choice3.setPrefHeight(40);
        choice4.setPrefHeight(40);
        choice5.setPrefHeight(40);

        choice1.setOnAction(e -> checkAnswer(choices.get(0)));
        choice2.setOnAction(e -> checkAnswer(choices.get(1)));
        choice3.setOnAction(e -> checkAnswer(choices.get(2)));
        choice4.setOnAction(e -> checkAnswer(choices.get(3)));
        choice5.setOnAction(e -> checkAnswer(choices.get(4)));

        root.getChildren().add(choice1);
        root.getChildren().add(choice2);
        root.getChildren().add(choice3);
        root.getChildren().add(choice4);
        root.getChildren().add(choice5);
    }

    private void checkAnswer(String answer) {

        if (answered) {
            return;
        }

        answered = true;

        if (answer.equals(words[currentRound])) {
            feedbackLabel.setText("Correct!");
            score++;
        } else {
            feedbackLabel.setText("Wrong!");
        }

        nextButton.setVisible(true);
    }

    private void nextRound() {

        currentRound++;

        if (currentRound < words.length) {
            startGame();
        } else {
            root.getChildren().clear();

            Label gameOverLabel = new Label("GAME OVER!");
            Label scoreLabel = new Label("Final Score: " + score + "/5");

            root.getChildren().add(gameOverLabel);
            root.getChildren().add(scoreLabel);

            Button playAgainButton = new Button("PLAY AGAIN");

            playAgainButton.setOnAction(e -> {
                currentRound = 0;
                score = 0;
                startGame();
            });

            root.getChildren().add(playAgainButton);
        }
    }

    private String wordToBinary(String word) {
        String binaryWord = "";

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int number = letter;

            String binary = String.format("%8s",
                            Integer.toBinaryString(number))
                    .replace(' ', '0');

            binaryWord += binary;
        }

        return binaryWord;
    }
}
