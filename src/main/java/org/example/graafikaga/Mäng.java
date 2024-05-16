package org.example.graafikaga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

//Icon designed by Freepik

/**
 * Siin toimub mängu mängimine, kõige olulisem meetod on createScoreScene, sest seal toimub mäng.
 * Enamus stseene on mängijalt vajaliku info kogumine.
 *
 * Et mängida siis tuleb veeretada täringuid, vajutada nendel checkboxidel mis tahad alles jätta, lisa skoori.
 */

public class Mäng extends Application {

    @Override
    public void start(Stage primaryStage) {//vajalikud muutujad
        List<M2ngija> m2ngijad = new ArrayList<>();
        ArrayList<T2ring> t2ringud = new ArrayList<>(Arrays.asList(new T2ring("1"),
                new T2ring("2"), new T2ring("3"),
                new T2ring("4"), new T2ring("5")));

        Scene tervitusScene = createTervitusScene(primaryStage, t2ringud);

        Image ikoon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/viirus.png")));

        primaryStage.setTitle("Yatzy Javas");
        primaryStage.getIcons().add(ikoon);
        primaryStage.setScene(tervitusScene);
        primaryStage.show();
    }

    private Scene createTervitusScene(Stage primaryStage, ArrayList<T2ring> t2ringud) {
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: white;");
        root.setPrefSize(900, 600);
        root.setAlignment(Pos.CENTER);

        Label tervitus = new Label("Yatzy");
        tervitus.setFont(Font.font(60));

        Button confirmPlayersButton = new Button("Confirm");
        confirmPlayersButton.setOnAction(event -> {
            primaryStage.setScene(createM2ngijateArvScene(primaryStage, t2ringud));
        });

        root.getChildren().addAll(tervitus, confirmPlayersButton);

        return new Scene(root);
    }

    private Scene createM2ngijateArvScene(Stage primaryStage, ArrayList<T2ring> t2ringud) {
        VBox layout = new VBox(10); // Leiame siin mängijate arvu
        layout.setStyle("-fx-background-color: lightgray;");
        layout.setPrefSize(900, 600);

        Label label = new Label("Sisesta mängija arv:");
        TextField textField = new TextField();
        Button kinnitaButton = new Button("Kinnita");
        kinnitaButton.setOnAction(event -> {
            int numberOfPlayers = Integer.parseInt(textField.getText());
            primaryStage.setScene(createM2ngijateNimedScene(primaryStage, numberOfPlayers, t2ringud));
        });

        layout.getChildren().addAll(label, textField, kinnitaButton);
        return new Scene(layout);
    }

    private Scene createM2ngijateNimedScene(Stage primaryStage, int numberOfPlayers, ArrayList<T2ring> t2ringud) {
        VBox layout = new VBox(10); // Siin küsime nimesid
        layout.setStyle("-fx-background-color: lightblue;");
        layout.setPrefSize(900, 600);

        List<TextField> nameFields = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            Label nameLabel = new Label("Player " + i + " name:");
            TextField nameField = new TextField();
            layout.getChildren().addAll(nameLabel, nameField);
            nameFields.add(nameField);
        }

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> {
            List<String> playerNames = new ArrayList<>();
            for (TextField field : nameFields) {
                String playerName = field.getText().trim();
                if (!playerName.isEmpty()) {
                    playerNames.add(playerName);
                }
            }

            if (playerNames.size() >= numberOfPlayers) {
                List<M2ngija> m2ngijad = new ArrayList<>();
                for (String name : playerNames) {
                    m2ngijad.add(new M2ngija(name));
                }
                primaryStage.setScene(createScoreScene(primaryStage, m2ngijad, t2ringud));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter all names.");
                alert.showAndWait();
            }
        });

        layout.getChildren().add(startGameButton);

        return new Scene(layout);
    }

    //Siin stseenis toimub mäng
    public Scene createScoreScene(Stage primaryStage, List<M2ngija> m2ngijad, ArrayList<T2ring> t2ringud) {
        Paigutaja paigutaja = new Paigutaja();
        AtomicInteger rollCount = new AtomicInteger(0);
        AtomicInteger playerIndex = new AtomicInteger(0);
        AtomicInteger totalScoreCount = new AtomicInteger(0);


        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: lightgray;");

        VBox playerNamesVBox = new VBox();
        playerNamesVBox.setPadding(new Insets(10));
        playerNamesVBox.setSpacing(5);
        Label nameLabel = new Label(m2ngijad.get(playerIndex.get()).getNimi());
        nameLabel.setFont(Font.font(30));
        nameLabel.setPrefSize(300, 30);
        borderPane.setTop(nameLabel);

        VBox centerVBox = new VBox();
        centerVBox.setAlignment(Pos.CENTER);

        HBox diceImagesHBox = new HBox();
        diceImagesHBox.setSpacing(100);
        diceImagesHBox.setAlignment(Pos.BOTTOM_CENTER);

        List<DiceContainer> diceContainers = new ArrayList<>();

        for (T2ring t2ring : t2ringud) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);

            VBox diceVBox = new VBox();
            diceVBox.setAlignment(Pos.CENTER);
            diceVBox.setSpacing(5);

            diceVBox.getChildren().add(imageView);

            CheckBox checkBox = new CheckBox();
            diceVBox.getChildren().add(checkBox);

            diceImagesHBox.getChildren().add(diceVBox);

            DiceContainer diceContainer = new DiceContainer(imageView, checkBox);
            diceContainers.add(diceContainer);
        }

        centerVBox.getChildren().add(diceImagesHBox);

        Button rollDiceButton = new Button("Roll Dice");
        Button stopRollingButton = new Button("Stop Rolling");
        stopRollingButton.setDisable(true);

        Button addToScore = new Button("Lisa skoori");
        addToScore.setDisable(true);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setDisable(true);
        comboBox.getItems().addAll(
                "Ühed", "Kahed", "Kolmed", "Neljad", "Viied",
                "Kuued", "Paar", "Kaks paari", "Kolmik", "Nelik", "Väike rida",
                "Suur rida", "Maja", "Yatzy", "Juhuslik"
        );

        rollDiceButton.setOnAction(event -> {
            stopRollingButton.setDisable(false);
            rollCount.incrementAndGet();
            if (rollCount.get() > 3) {
                rollDiceButton.setDisable(true);
                stopRollingButton.setDisable(true);
                comboBox.setDisable(false);
                addToScore.setDisable(false);
                return;
            }
            for (DiceContainer diceContainer : diceContainers) {
                if (!diceContainer.getCheckBox().isSelected()) {
                    T2ring t2ring = t2ringud.get(diceContainers.indexOf(diceContainer));
                    t2ring.veereta();
                    ImageView imageView = diceContainer.getImageView();
                    CheckBox checkBox = diceContainer.getCheckBox();

                    Image newDiceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tvise.gif")));
                    imageView.setImage(newDiceImage);

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                        Image originalDiceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tv" + (t2ring.getSilmad()) + ".png")));
                        imageView.setImage(originalDiceImage);
                    }));
                    timeline.play();
                }
            }
        });

        addToScore.setOnAction(event -> {
            System.out.println(playerIndex.get());
            M2ngija currentPlayer = m2ngijad.get(playerIndex.get());

            try {
                paigutaja.paiguta(currentPlayer, comboBox.getValue(), t2ringud);
                System.out.println(currentPlayer);
            } catch (AlreadyAValueError e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This score category already has a value. Please choose another one.");
                alert.showAndWait();
                return;
            }
            for (DiceContainer diceContainer : diceContainers) {
                diceContainer.getCheckBox().setSelected(false);
            }

            rollDiceButton.setDisable(false);
            stopRollingButton.setDisable(true);
            comboBox.setDisable(true);
            addToScore.setDisable(true);

            comboBox.getSelectionModel().clearSelection();

            for (DiceContainer diceContainer : diceContainers) {
                ImageView imageView = diceContainer.getImageView();
                Image originalDiceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tvd.png")));
                imageView.setImage(originalDiceImage);
                rollCount.set(0);
            }
            totalScoreCount.getAndIncrement();

            if (totalScoreCount.get() >= m2ngijad.size() * 15) {
                primaryStage.setScene(createEndGameScene(primaryStage, m2ngijad));
                return;
            }

            playerIndex.incrementAndGet();
            if (playerIndex.get() >= m2ngijad.size()) {
                playerIndex.set(0);
            }
            currentPlayer = m2ngijad.get(playerIndex.get());
            nameLabel.setText(currentPlayer.getNimi());
        });


        stopRollingButton.setOnAction(event -> {
            rollDiceButton.setDisable(true);
            stopRollingButton.setDisable(true);
            comboBox.setDisable(false);
            addToScore.setDisable(false);
        });


        HBox buttonBox = new HBox(10, rollDiceButton, stopRollingButton);
        buttonBox.setAlignment(Pos.CENTER);
        centerVBox.getChildren().addAll(buttonBox, comboBox, addToScore);

        borderPane.setCenter(centerVBox);

        Scene scene = new Scene(borderPane, 900, 600);
        return scene;
    }

    private Scene createEndGameScene(Stage primaryStage, List<M2ngija> m2ngijad) {
        VBox layout = new VBox(10); // lõpp stseen
        layout.setStyle("-fx-background-color: lightgray;");
        layout.setPrefSize(900, 600);
        M2ngija winner = findWinner(m2ngijad);
        Label winnerLabel = new Label("Võitja on: " + winner.getNimi());
        winnerLabel.setFont(Font.font(30));
        layout.getChildren().add(winnerLabel);
        Button writeToFileButton = new Button("Kirjuta võitja nimi ja skoor faili");
        writeToFileButton.setOnAction(event -> {
            String filePath = "winner.txt";
            String winnerName = winner.getNimi();
            int winnerScore = winner.getSumma();

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                writer.write(winnerName + ": " + winnerScore + " - " + LocalDateTime.now());
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        });


        layout.getChildren().add(writeToFileButton);

        return new Scene(layout);
    }

    private M2ngija findWinner(List<M2ngija> m2ngijad) {
        M2ngija winner = m2ngijad.get(0);
        for (M2ngija m2ngija : m2ngijad) {
            if (m2ngija.getSumma() > winner.getSumma()) {
                winner = m2ngija;
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
