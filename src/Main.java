// import javafx.application.Application;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import javafx.scene.control.Label;


// public class Main extends Application {

//     public static ObservableList<Integer> sharedValues = ArrayCreator.generateValues(400, 10, 0);


//     @Override
//     public void start(Stage primaryStage) {
//         BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
//         VBox layout = new VBox(visualizer);
//         Scene scene = new Scene(layout, 800, 600);
//         int baseDelay = 5; // milliseconds
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Zmiana wartości na żywo");
//         primaryStage.show();

//         new Thread(() -> {
//             try {
//                 Thread.sleep(2000);
//                 //bubble
// //                BubbleSort sort = new BubbleSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //insertion
// //                InsertionSort sort = new InsertionSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //selection
// //                SelectionSort sort = new SelectionSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //bogo
// //                BogoSort sort = new BogoSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //qsort hoare
// //                QuickSortHoare sort = new QuickSortHoare(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //qsort lomuto
// //                QuickSortLomuto sort = new QuickSortLomuto(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //shell
// //                ShellSort sort = new ShellSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));
//                 //heap
//                 HeapSort sort = new HeapSort(sharedValues, baseDelay,
//                         new Label("label"), visualizer, new Label("finish label"));
//                 //merge
// //                MergeSort sort = new MergeSort(sharedValues, baseDelay,
// //                        new Label("label"), visualizer, new Label("finish label"));

//                 //start sorting
//                 sort.startSorting();

//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//         }).start();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        showAlgorithmSelectionScreen(primaryStage);
    }

    private void showAlgorithmSelectionScreen(Stage stage) {
        ComboBox<String> algorithmComboBox = new ComboBox<>();
        algorithmComboBox.getItems().addAll(
                "ShellSort", "HeapSort", "BubbleSort", "InsertionSort", "SelectionSort",
                "QuickSortHoare", "QuickSortLomuto", "BogoSort"
        );
        algorithmComboBox.setValue("ShellSort");

        Button nextButton = new Button("Dalej");

        nextButton.setOnAction(e -> {
            String selectedAlgorithm = algorithmComboBox.getValue();
            showElementCountScreen(stage, selectedAlgorithm);
        });

        VBox layout = new VBox(20, new Label("Wybierz algorytm sortowania:"), algorithmComboBox, nextButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 400, 200));
        stage.setTitle("Wybór algorytmu");
        stage.show();
    }

    private void showElementCountScreen(Stage stage, String selectedAlgorithm) {
        Slider slider = new Slider(10, 1000, 100);
        slider.setMajorTickUnit(100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        Button startButton = new Button("Start wizualizacji");

        startButton.setOnAction(e -> {
            int count = (int) slider.getValue();
            showVisualizationScreen(stage, selectedAlgorithm, count);
        });

        VBox layout = new VBox(20, new Label("Wybierz liczbę elementów:"), slider, startButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(layout, 400, 200));
    }

    private void showVisualizationScreen(Stage stage, String algorithm, int count) {
        Button backButton = new Button("Powrót");
    backButton.setOnAction(ev -> {
    showAlgorithmSelectionScreen(stage);
    });

        ObservableList<Integer> values = ArrayCreator.generateValues(count, 10, 0);
        BarChartVisualizer visualizer = new BarChartVisualizer(values);
        VBox layout = new VBox(10, backButton, visualizer);
        layout.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Wizualizacja: " + algorithm);
       
        


        new Thread(() -> {
            try {
                Thread.sleep(1000); // małe opóźnienie przed startem

                switch (algorithm) {
                    case "ShellSort" -> {
                        ShellSort sort = new ShellSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "HeapSort" -> {
                        HeapSort sort = new HeapSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "BubbleSort" -> {
                        BubbleSort sort = new BubbleSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "InsertionSort" -> {
                        InsertionSort sort = new InsertionSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "SelectionSort" -> {
                        SelectionSort sort = new SelectionSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "QuickSortHoare" -> {
                        QuickSortHoare sort = new QuickSortHoare(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    case "QuickSortLomuto" -> {
                        QuickSortLomuto sort = new QuickSortLomuto(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    // case "MergeSort" -> {
                    //     MergeSort sort = new MergeSort(values, 5, new Label(), visualizer, new Label());
                    //     sort.startSorting();
                    // }
                    case "BogoSort" -> {
                        BogoSort sort = new BogoSort(values, 5, new Label(), visualizer, new Label());
                        sort.startSorting();
                    }
                    default -> {
                        System.out.println("Nieznany algorytm.");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
