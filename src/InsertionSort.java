import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort(ObservableList<Integer> list, int baseDelayMillis,
                      Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            visualizer.addSorted(0);
            for (int i = 1; i < listSize; i++) { incrementCounterIf();
                highlightCursor(i);
                int key = list.get(i); incrementCounterGet();
                int j = i - 1;
                highlightCompare(j, -1);
                while (j >= 0 && list.get(j) > key) {
                    incrementCounterIf(); incrementCounterIf();
                    incrementCounterGet();
                    sleep();
                    highlightCompare(j, -1);
                    sleep();
                    set(j + 1, list.get(j));
                    j--;
                }
                if (j >= 0) { incrementCounterIf(); incrementCounterGet(); }
                incrementCounterIf();
                sleep();
                set(j + 1, key);
                highlightCompare(j + 1, i);
                sleep();
                highlightSorted(i);
            }
            incrementCounterIf();
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm InsertionSort zakończył działanie."));
        }).start();
    }
}
