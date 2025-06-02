import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Comparator;

public class BarChartVisualizer extends Pane {

    private ObservableList<Integer> values;
    private double width = 800;
    private double height = 600;
    private int highlightA = -1;
    private int highlightB = -1;
    private ArrayList<Integer> sortedIds = new ArrayList<Integer>();

    public void addSorted(int id){
        sortedIds.add(id);
        sortedIds.sort(Comparator.naturalOrder()); // sortowanie posortowanych indeksow
    }

    public void highlightBars(int a, int b) {
        this.highlightA = a;
        this.highlightB = b;
        Platform.runLater(this::updateBars);
    }

    public void clearHighlights() {
        this.highlightA = -1;
        this.highlightB = -1;
        Platform.runLater(this::updateBars);
    }

    public BarChartVisualizer(ObservableList<Integer> values) {
        this.values = values;
        setPrefSize(width, height);

        //Exception in thread "Thread-4" java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-4
        //this -> ... powodowalo bledy
        this.values.addListener((javafx.collections.ListChangeListener<? super Integer>) change -> {
            Platform.runLater(() -> {
                updateBars();
            });
        });

        updateBars();
    }

    private void updateBars() {
        getChildren().clear();
        int numBars = values.size();
        double width = getWidth() > 0 ? getWidth() : 800;
        double height = getHeight() > 0 ? getHeight() : 600;
    

        double barWidth = Math.max(1, width / numBars); // minimalna szerokość 1
        for (int i = 0; i < numBars; i++) {
            double value = values.get(i);
            double barHeight = value / 400.0 * height; // zakładamy max 400
            Rectangle rect = new Rectangle(barWidth, barHeight);
            rect.setTranslateX(i * barWidth);
            rect.setTranslateY(height - barHeight);
            rect.setFill(Color.CORNFLOWERBLUE);
            getChildren().add(rect);
            if (i == highlightA) {
                rect.setFill(Color.RED);
            } else if (i == highlightB) {
                rect.setFill(Color.ORANGE);
            } else if (sortedIds.contains(i)){
                rect.setFill(Color.LIMEGREEN);
            } else {
                rect.setFill(Color.CORNFLOWERBLUE);
            }
        }
    }

}

