import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {

    public static ObservableList<Integer> sharedValues = ArrayCreator.generateValues(200, 10);


    @Override
    public void start(Stage primaryStage) {
        BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
        VBox layout = new VBox(visualizer);
        Scene scene = new Scene(layout, 1000, 600);
        int baseDelay = 1; // milliseconds
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zmiana wartości na żywo");
        primaryStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                //bubble
//                BubbleSort sort = new BubbleSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //insertion
                InsertionSort sort = new InsertionSort(sharedValues, baseDelay,
                        new Label("label"), visualizer, new Label("finish label"));
                //selection
//                SelectionSort sort = new SelectionSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //bogo
//                BogoSort sort = new BogoSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));

                //start sorting
                sort.startSorting();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
