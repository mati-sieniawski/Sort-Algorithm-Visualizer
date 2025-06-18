import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class QuickSortLomuto extends SortingAlgorithm {

    public QuickSortLomuto(ObservableList<Integer> list, int baseDelayMillis,
                          Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            qsort(0, listSize-1);
             Platform.runLater(() -> finishedLabel.setText("✅Algorytm QuickSortLomuto zakończył działanie."));
        }).start();
    }

    public void qsort(int left, int right) {
        if (left == right) { highlightSorted(left); }
        incrementCounterIf();
        if (left < right) {
            int p = partitionLomuto(left, right);
            qsort(left, p - 1);
            qsort(p + 1, right);
        }
    }

    protected int partitionLomuto(int left, int right) {
        int pivotValue = list.get(right); incrementCounterGet();
        int i = left - 1;
        highlightCursor(right);

        for (int j = left; j < right; j++) {
            incrementCounterIf();
            sleep();
            highlightCompare(i, j);

            incrementCounterIf();
            incrementCounterGet();
            if (list.get(j) <= pivotValue) {
                sleep();
                i++;
                highlightCompare(i, j);
                swap(i, j);
            }
        }
        incrementCounterIf();
        sleep();
        swap(i + 1, right);
        highlightClear();
        highlightSorted(i + 1);
        return i + 1;
    }
}

