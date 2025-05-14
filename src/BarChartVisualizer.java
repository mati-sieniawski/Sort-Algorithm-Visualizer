import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.collections.ObservableList;

public class BarChartVisualizer extends Pane {

    private ObservableList<Integer> values;
    private double width = 800;
    private double height = 600;
    private int highlightA = -1;
    private int highlightB = -1;

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

        this.values.addListener((javafx.collections.ListChangeListener<? super Integer>) change -> {
            updateBars();
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
        if (i == highlightA || i == highlightB) {
    rect.setFill(Color.ORANGE);
} else {
    rect.setFill(Color.CORNFLOWERBLUE);
}

    }
}

}

