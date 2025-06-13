import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Comparator;

public class BarChartVisualizer extends Pane {

    private ObservableList<Integer> values;
    private double width = 1000;
    private double height = 600;
    private int highlightA = -1;
    private int highlightB = -1;
    private int highlightC = -1;
    private final ArrayList<Integer> sortedIds = new ArrayList<Integer>();
    private final ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

    public void addSorted(Integer id){
        sortedIds.add(id);
        sortedIds.sort(Comparator.naturalOrder()); // sortowanie posortowanych indeksow
    }

    public void visCursor(int a){
        this.highlightA = a;
        Platform.runLater(this::updateBars);
    }

    public void visCompare(int b, int c) {
        this.highlightB = b;
        this.highlightC = c;
        Platform.runLater(this::updateBars);
    }

    public void clearHighlights() {
        this.highlightA = -1;
        this.highlightB = -1;
        this.highlightC = -1;
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

    private Color getColor(int i){
        if (i == highlightA) { return Color.RED; }
        if (i == highlightB) { return Color.ORANGE; }
        if (i == highlightC) { return Color.TOMATO; }
        if (sortedIds.contains(i)) { return Color.LIMEGREEN; }
        return Color.CORNFLOWERBLUE;
    }

    private void updateBars() {
        int numBars = values.size();

        double width = getWidth() > 0 ? getWidth() : 800;
        double height = getHeight() > 0 ? getHeight() : 600;
        double barWidth = Math.max(1, width / numBars);
        double maxValue = values.stream().max(Integer::compareTo).orElse(1);

        // Synchronizacja liczby prostokątów z liczbą wartości
        while (rectangles.size() < numBars) {
            Rectangle rect = new Rectangle();
            rectangles.add(rect);
            getChildren().add(rect);
        }

        for (int i = 0; i < numBars; i++) {
            double value = values.get(i);
            double barHeight = value / (double) maxValue * height;

            Rectangle rect = rectangles.get(i);
            rect.setWidth(barWidth);
            rect.setHeight(barHeight);
            rect.setTranslateX(i * barWidth);
            rect.setTranslateY(height - barHeight);
            rect.setFill(getColor(i));
        }

    }

}

