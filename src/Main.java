import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {

    public static ObservableList<Integer> sharedValues = ArrayCreator.generateValues(10, 30);
//            FXCollections.observableArrayList(
//        200, 70, 50, 90, 20, 40,120, 150, 55, 210,30, 100
//    );

    @Override
    public void start(Stage primaryStage) {
        BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
        VBox layout = new VBox(visualizer);
        Scene scene = new Scene(layout, 800, 600);
        int baseDelay = 500; // milliseconds
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zmiana wartości na żywo");
        primaryStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                BubbleSort bubbleSort = new BubbleSort(sharedValues, baseDelay,
                        new Label("label"), visualizer, new Label("finish label"));
                bubbleSort.startSorting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
