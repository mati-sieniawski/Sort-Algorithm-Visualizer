import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static ObservableList<Integer> sharedValues = FXCollections.observableArrayList(
        200, 70, 50, 90, 20, 40
    );

    @Override
    public void start(Stage primaryStage) {
        BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
        VBox layout = new VBox(visualizer);
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zmiana wartości na żywo");
        primaryStage.show();

       new Thread(() -> {
    try {
        Thread.sleep(2000); 
        BubbleSort bubbleSort = new BubbleSort(sharedValues, 500);
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
