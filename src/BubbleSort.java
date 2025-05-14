import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class BubbleSort {

    private final ObservableList<Integer> list;
    private final int baseDelayMillis;
    private final int from;
    private final int to;
    private final Label stepLabel;
    private final BarChartVisualizer visualizer;
    private final Label finishedLabel;
    private Label speedLabel;
    private Thread sortingThread;  // Zmienna do trzymania wątku sortowania
    private volatile boolean isSorting = true;  // Zmienna do kontrolowania stanu sortowania

    private final double[] speedSteps = {0.25, 0.5, 1, 2, 4, 10, 25, 100, 500, 1000};
    private int currentSpeedIndex = 2; // x1
    public double speedMultiplier = speedSteps[currentSpeedIndex];
    private int stepCounter = 0;

    public BubbleSort(ObservableList<Integer> list, int baseDelayMillis, int from, int to,
                      Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        this.list = list;
        this.baseDelayMillis = baseDelayMillis;
        this.from = from;
        this.to = to;
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

    private void updateSpeedLabel() {
        if (speedLabel != null) {
            Platform.runLater(() -> speedLabel.setText("x" + speedSteps[currentSpeedIndex]));
        }
    }

    public void startSorting() {
        new Thread(() -> {
            int n = list.size();
            for (int i = from; i <= to; i++) {
                for (int j = from; j < to - i + from; j++) {
                    highlight(j, j + 1);
                    if (list.get(j) > list.get(j + 1)) {
                        swap(j, j + 1);
                    }
                    stepCounter++;
                    updateStepCounter();
                    sleep();
                    visualizer.clearHighlights();
                }
            }
            Platform.runLater(() -> finishedLabel.setText("✔️ Sortowanie zakończone!"));
        }).start();
    }

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void highlight(int i, int j) {
        visualizer.highlightBars(i, j);
    }

    private void updateStepCounter() {
        Platform.runLater(() -> stepLabel.setText("Kroki: " + stepCounter));
    }

    private void sleep() {
        try {
            Thread.sleep((long) (baseDelayMillis / speedMultiplier));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
