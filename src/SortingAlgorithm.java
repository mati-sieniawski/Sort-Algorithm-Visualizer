import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

// klasa bazowa do budowy algorytmow
// zawiera podstawowe funkcje
// klasy zawierajace poszczegolne algorytmy maja dziedziczyc po SortingAlgorithm
public class SortingAlgorithm {
    protected final ObservableList<Integer> list;
    protected final int baseDelayMillis;
    protected final int listSize;
    protected final Label stepLabel;
    protected final BarChartVisualizer visualizer;
    protected final Label finishedLabel;
    protected Label speedLabel;
    protected Thread sortingThread;  // Zmienna do trzymania wątku sortowania
    protected volatile boolean isSorting = true;  // Zmienna do kontrolowania stanu sortowania

    protected final double[] speedSteps = {0.25, 0.5, 1, 2, 4, 10, 25, 100, 500, 1000};
    protected int currentSpeedIndex = 2; // x1
    public double speedMultiplier = speedSteps[currentSpeedIndex];
    protected int stepCounter = 0;

    public SortingAlgorithm(ObservableList<Integer> list, int baseDelayMillis,
                      Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        this.list = list;
        this.baseDelayMillis = baseDelayMillis;
        this.listSize = list.size();
        this.stepLabel = stepLabel;
        this.visualizer = visualizer;
        this.finishedLabel = finishedLabel;
    }

    public void setSpeedLabel(Label label) {
        this.speedLabel = label;
        updateSpeedLabel();
    }

    public void increaseSpeed() {
        if (currentSpeedIndex < speedSteps.length - 1) {
            currentSpeedIndex++;
            speedMultiplier = speedSteps[currentSpeedIndex];
            updateSpeedLabel();
        }
    }

    public void decreaseSpeed() {
        if (currentSpeedIndex > 0) {
            currentSpeedIndex--;
            speedMultiplier = speedSteps[currentSpeedIndex];
            updateSpeedLabel();
        }
    }

    protected void updateSpeedLabel() {
        if (speedLabel != null) {
            Platform.runLater(() -> speedLabel.setText("x" + speedSteps[currentSpeedIndex]));
        }
    }

    protected void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // indeks wskaznika, indeks do porownania
    protected void highlight(int i, int j) {
        visualizer.highlightBars(i, j);
    }

    protected void updateStepCounter() {
        Platform.runLater(() -> stepLabel.setText("Kroki: " + stepCounter));
    }

    protected void sleep() {
        try {
            Thread.sleep((long) (baseDelayMillis / speedMultiplier));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
