module org.example.binary_word_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.binary_word_game to javafx.fxml;
    exports org.example.binary_word_game;
}