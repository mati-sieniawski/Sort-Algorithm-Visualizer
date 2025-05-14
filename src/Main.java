import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    public static ObservableList<Integer> sharedValues = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        showMenuScreen(primaryStage);
    }

    private void showMenuScreen(Stage stage) {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(20));

        Label title = new Label("Wybierz algorytm sortowania");
        title.setFont(Font.font(20));

        Button bubbleSortButton = new Button("Sortowanie bąbelkowe");
        bubbleSortButton.setOnAction(e -> showRangeSelectionScreen(stage, "Bubble Sort"));

        menuLayout.getChildren().addAll(title, bubbleSortButton);

        stage.setScene(new Scene(menuLayout, 800, 600));
        stage.setTitle("Menu główne");
        stage.show();
    }

    private void showRangeSelectionScreen(Stage stage, String algorithm) {
        VBox rangeLayout = new VBox(10);
        rangeLayout.setAlignment(Pos.CENTER);
        rangeLayout.setPadding(new Insets(20));

        Label label = new Label("Zakres danych do posortowania");
        TextField minField = new TextField("10");
        TextField maxField = new TextField("100");
        Label info = new Label("Liczby z zakresu 0 - wartość maksymalna");

        Button startButton = new Button("Wizualizuj");
        startButton.setOnAction(e -> {
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            generateRandomList(min, max);
            showVisualizationScreen(stage);
        });
        

        rangeLayout.getChildren().addAll(label, new Label("Ilość elementów:"), minField,
                new Label("Maksymalna wartość:"), maxField, info, startButton);

        stage.setScene(new Scene(rangeLayout, 800, 600));
        stage.setTitle("Konfiguracja");
    }

    private void generateRandomList(int count, int maxValue) {
        sharedValues.clear();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            sharedValues.add(rand.nextInt(maxValue + 1));
        }
    }

    private void showVisualizationScreen(Stage stage) {
        BorderPane root = new BorderPane();

        BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
        Label stepCounterLabel = new Label("Kroki: 0");
        stepCounterLabel.setFont(Font.font(16));

        Label finishedLabel = new Label();
        finishedLabel.setFont(Font.font(16));
        finishedLabel.setStyle("-fx-text-fill: forestgreen;");

        VBox centerBox = new VBox(visualizer, finishedLabel);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);
        root.setTop(stepCounterLabel);
        BorderPane.setMargin(stepCounterLabel, new Insets(10));

        // Speed Controls
        HBox speedControls = new HBox(10);
        Button slower = new Button("←");
        Label speedLabel = new Label("x1.0");
        Button faster = new Button("→");

        speedControls.getChildren().addAll(slower, speedLabel, faster);
        speedControls.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(speedControls, Pos.TOP_RIGHT);
        BorderPane.setMargin(speedControls, new Insets(10));
        root.setRight(speedControls);

        // Back to Menu Button
        Button backButton = new Button("Powrót do menu");
        backButton.setOnAction(e -> showMenuScreen(stage));
        root.setTop(new HBox(backButton, stepCounterLabel));

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Wizualizacja");
        stage.show();

        BubbleSort sorter = new BubbleSort(sharedValues, 300, 0, sharedValues.size() - 1, stepCounterLabel, visualizer, finishedLabel);
        slower.setOnAction(e -> sorter.decreaseSpeed());
        faster.setOnAction(e -> sorter.increaseSpeed());
        sorter.setSpeedLabel(speedLabel);
        sorter.startSorting();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
