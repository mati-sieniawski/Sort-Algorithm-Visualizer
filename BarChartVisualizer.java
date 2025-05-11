import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.collections.ObservableList;

public class BarChartVisualizer extends Pane {

    private ObservableList<Integer> values;
    private double width = 800;
    private double height = 600;

    public BarChartVisualizer(ObservableList<Integer> values) {
        this.values = values;
        setPrefSize(width, height);

        this.values.addListener((javafx.collections.ListChangeListener<? super Integer>) change -> {
            updateBars();
        });

        updateBars();
    }

    public void updateBars() {
        getChildren().clear();

        int max = values.stream().mapToInt(i -> i).max().orElse(1);
        double barWidth = width / values.size();

        for (int i = 0; i < values.size(); i++) {
            int val = values.get(i);
            double barHeight = (val / (double) max) * (height - 50);
            Rectangle bar = new Rectangle(
                i * barWidth + 5,
                height - barHeight,
                barWidth - 10,
                barHeight
            );
            bar.setFill(Color.CORNFLOWERBLUE);
            getChildren().add(bar);
        }
    }
}

