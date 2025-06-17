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
    //protected Thread sortingThread;  
    //protected volatile boolean isSorting = true;  
    protected int counterIf = 0;   
    protected int counterGet = 0;

    protected final double[] speedSteps = {0.01, 0.05, 0.1, 0.5, 1, 2, 4, 10};
    protected int currentSpeedIndex = 2;
    public double speedMultiplier = speedSteps[currentSpeedIndex];

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

    protected int powerInt(int value, int exponent){
        if (exponent == 0) { return 1; }
        int out = value;
        for (int i = 1; i < exponent; i++){
            out *= value;
        }
        return out;
    }

    protected void set(int i, int value){
        list.set(i, value);
    }

    protected void highlightCursor(int i) {
        visualizer.visCursor(i);
    }

    protected void highlightCompare(int i, int j) {
        visualizer.visCompare(i, j);
    }

    protected void highlightCompareLeft(int i) {
        visualizer.visCompareLeft(i);
    }

    protected void highlightCompareRigth(int i) {
        visualizer.visCompareRight(i);
    }

    protected void highlightClear() { visualizer.clearHighlights(); }

    protected void highlightSorted(int i) { visualizer.addSorted(i); }

    protected void highlightSortedDel(int i) { visualizer.delSorted(i); }

    protected void sleep() {
        try {
            Thread.sleep((long) (baseDelayMillis / speedMultiplier));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void incrementCounterIf() {
    counterIf++;
    updateStepLabels();
}

protected void incrementCounterGet() {
    counterGet++;
    updateStepLabels();
}

private void updateStepLabels() {
    if (stepLabel != null) {
        Platform.runLater(() -> {
            stepLabel.setText("Porównania: " + counterIf + "    Dostępy: " + counterGet);
        });
    }
}

}
