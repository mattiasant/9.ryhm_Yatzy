package org.example.graafikaga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Icon designed by Freepik

public class Tervitus extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating the initial scene
        Scene tervitusScene = createTervitusScene(primaryStage);
        Image ikoon = new Image(getClass().getResourceAsStream("/viirus.png"));

        primaryStage.setTitle("Yatzy Javas, rühm 9 ithink");
        primaryStage.getIcons().add(ikoon);
        primaryStage.setScene(tervitusScene);
        primaryStage.show();
    }

    // Method to create the initial scene
    private Scene createTervitusScene(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: white;");
        root.setPrefSize(800, 600);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        // Adding text
        Label tervitus = new Label("Yatzy");
        tervitus.setFont(javafx.scene.text.Font.font(60));

        // Adding a button
        Button nextButton = new Button("Edasi");
        nextButton.setOnAction(event -> {
            // Switch to the next scene
            Scene nextScene = createPlayerCountScene(primaryStage); // Create the next scene
            primaryStage.setScene(nextScene); // Set the next scene as current scene
        });

        root.getChildren().addAll(tervitus, nextButton);

        return new Scene(root);
    }

    // Method to create the scene for selecting player count
    private Scene createPlayerCountScene(Stage primaryStage) {
        VBox layout = new VBox(10); // Vertical layout
        layout.setStyle("-fx-background-color: lightgray;");
        layout.setPrefSize(800, 600);

        Label label = new Label("Sisesta mängija arv:");
        TextField textField = new TextField();
        Button confirmButton = new Button("Kinnita");
        confirmButton.setOnAction(event -> {
            String input = textField.getText();
            int numberOfPlayers = Integer.parseInt(input); // Convert string to integer
            Scene nextScene = createPlayerNamesScene(primaryStage, numberOfPlayers);
            primaryStage.setScene(nextScene); // Set the next scene as current scene
        });

        layout.getChildren().addAll(label, textField, confirmButton);
        return new Scene(layout);
    }

    // Method to create the scene for entering player names
    private Scene createPlayerNamesScene(Stage primaryStage, int numberOfPlayers) {
        VBox layout = new VBox(10); // Vertical layout
        layout.setStyle("-fx-background-color: lightblue;");
        layout.setPrefSize(800, 600);

        List<M2ngija> m2ngijad = new ArrayList<>();
        // Add labels and text fields for player names
        List<TextField> nimed = new ArrayList<TextField>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            Label nameLabel = new Label("Mängija number " + i + " nimi:");
            TextField nameField = new TextField();
            layout.getChildren().addAll(nameLabel, nameField);
            nimed.add(nameField);
        }

        // Add a button to proceed
        Button nextButton = new Button("Edasi");
        nextButton.setOnAction(event -> {

            for (int i = 0; i < numberOfPlayers; i++) {
                String nimi = nimed.get(i).getText();
                M2ngija m2ngija = new M2ngija(nimi);
                m2ngijad.add(m2ngija);
            }

            Scene nextScene;// = null;
            try {
                nextScene = createScoreGridScene(primaryStage, m2ngijad);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setScene(nextScene); // Set the next scene as current scene
        });
        layout.getChildren().add(nextButton);

        return new Scene(layout);
    }


    private Scene createScoreGridScene(Stage primaryStage, List<M2ngija> m2ngijad) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: lightgray;");

        // Add player names in the first column
        for (int row = 0; row <= m2ngijad.size(); row++) {
            String playerName;
            if (row == 0) {
                playerName = "\t"; // Leave first row blank
            } else {
                playerName = m2ngijad.get(row-1).getNimi(); // Assign player names
            }
            Label nameLabel = new Label(playerName);
            gridPane.add(nameLabel, 0, row);
        }

        // Create labels for the grid
        String[] skoorNimetus = {"Ühed", "Kahed","Kolmed","Neljad","Viied","Kuued",
            "Summa", "Boonus", "Paar","Kaks paari","Kolmik","Nelik","Väike rida",
            "Suur rida", "Maja", "Yatzy","Juhuslik","Summa"};
        for (int row = 0; row <= m2ngijad.size(); row++) {
            for (int col = 1; col < 18; col++) {
                if (row == 0) {
                    Label label = new Label(skoorNimetus[col - 1] + "  ");
                    gridPane.add(label, col, row);
                } else {

                    Label label = new Label("  -- ");
                    gridPane.add(label, col, row);
                }
            }
        }

        Image diceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tvise.gif")));

        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(diceImage);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            gridPane.add(imageView, i + 1, m2ngijad.size() + 1);
        }

        Scene scene = new Scene(gridPane, 800, 600);
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
