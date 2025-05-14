import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    public static ObservableList<Integer> sharedValues = FXCollections.observableArrayList(
        200, 70, 50, 90, 20, 40
    );

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    private void showMainMenu() {
        Button bubbleSortButton = new Button("Bubble Sort");

        bubbleSortButton.setOnAction(e -> showConfigScreen("Bubble Sort"));

        VBox layout = new VBox(15, new Label("Wybierz algorytm sortowania:"), bubbleSortButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.setTitle("Menu Główne");
        primaryStage.show();
    }

    private void showConfigScreen(String algorithmName) {
        Label info = new Label("Zakres sortowania (indeksy):");

        TextField fromField = new TextField("0");
        TextField toField = new TextField(String.valueOf(sharedValues.size() - 1));

        Button startButton = new Button("Wizualizuj");
        Button backButton = new Button("Powrót");

        HBox rangeInputs = new HBox(10, new Label("Od:"), fromField, new Label("Do:"), toField);
        rangeInputs.setStyle("-fx-alignment: center;");

        startButton.setOnAction(e -> {
            int from = Integer.parseInt(fromField.getText());
            int to = Integer.parseInt(toField.getText());
            showVisualizationScreen(algorithmName, from, to);
        });

        backButton.setOnAction(e -> showMainMenu());

        VBox layout = new VBox(15, info, rangeInputs, startButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        primaryStage.setScene(new Scene(layout, 400, 300));
    }

    private void showVisualizationScreen(String algorithmName, int from, int to) {
    // Losowe wartości
    for (int i = from; i <= to; i++) {
        int randomValue = (int) (Math.random() * 400);
        if (i < sharedValues.size()) {
            sharedValues.set(i, randomValue);
        } else {
            sharedValues.add(randomValue);
        }
    }
    while (sharedValues.size() > to + 1) {
        sharedValues.remove(sharedValues.size() - 1);
    }

    // UI elementy
    Label stepCounterLabel = new Label("Kroki: 0");
    Label speedLabel = new Label("x1.0");

    Button slowerButton = new Button("←");
    Button fasterButton = new Button("→");

    HBox speedControls = new HBox(5, slowerButton, speedLabel, fasterButton);
    speedControls.setStyle("-fx-alignment: center-right;");

    BorderPane topPane = new BorderPane();
    topPane.setLeft(stepCounterLabel);
    topPane.setRight(speedControls);
    BorderPane.setMargin(stepCounterLabel, new javafx.geometry.Insets(10));
    BorderPane.setMargin(speedControls, new javafx.geometry.Insets(10));

    BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
    Button backButton = new Button("Powrót do menu");
    backButton.setOnAction(e -> showMainMenu());

    BorderPane root = new BorderPane();
    root.setTop(topPane);
    root.setCenter(visualizer);
    root.setBottom(backButton);
    BorderPane.setMargin(backButton, new javafx.geometry.Insets(10));

    primaryStage.setScene(new Scene(root, 800, 600));

    if (algorithmName.equals("Bubble Sort")) {
        BubbleSort sorter = new BubbleSort(sharedValues, 300, from, to, stepCounterLabel);
        sorter.setSpeedLabel(speedLabel);

        slowerButton.setOnAction(e -> sorter.decreaseSpeed());
        fasterButton.setOnAction(e -> sorter.increaseSpeed());

        new Thread(sorter::startSorting).start();
    }
}



    public static void main(String[] args) {
        launch(args);
    }
}
