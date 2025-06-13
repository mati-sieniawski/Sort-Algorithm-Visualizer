import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {

    public static ObservableList<Integer> sharedValues = ArrayCreator.generateValues(400, 10, 0);


    @Override
    public void start(Stage primaryStage) {
        BarChartVisualizer visualizer = new BarChartVisualizer(sharedValues);
        VBox layout = new VBox(visualizer);
        Scene scene = new Scene(layout, 800, 600);
        int baseDelay = 10; // milliseconds
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
//                InsertionSort sort = new InsertionSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //selection
//                SelectionSort sort = new SelectionSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //bogo
//                BogoSort sort = new BogoSort(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //qsort hoare
//                QuickSortHoare sort = new QuickSortHoare(sharedValues, baseDelay,
//                        new Label("label"), visualizer, new Label("finish label"));
                //qsort lomuto
                QuickSortLomuto sort = new QuickSortLomuto(sharedValues, baseDelay,
                        new Label("label"), visualizer, new Label("finish label"));
                //shell
//                ShellSort sort = new ShellSort(sharedValues, baseDelay,
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
