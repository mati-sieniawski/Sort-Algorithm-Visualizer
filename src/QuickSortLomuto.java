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
             Platform.runLater(() -> finishedLabel.setText("✅Algorytm ShellSort zakończył działanie."));
        }).start();
    }

    public void qsort(int left, int right) {
        if (left == right) { highlightSorted(left);incrementCounterIf(); }
        if (left < right) {
            incrementCounterIf();
            int p = partitionLomuto(left, right);
            qsort(left, p - 1);
            qsort(p + 1, right);
        }
    }

    protected int partitionLomuto(int left, int right) {
        int pivotValue = list.get(right);
        int i = left - 1;
        incrementCounterGet();
        highlightCursor(right);

        for (int j = left; j < right; j++) {
            sleep();
            highlightCompare(i, j);
            incrementCounterIf();
            if (list.get(j) <= pivotValue) {
                sleep();
                i++;
                highlightCompare(i, j);
                swap(i, j);
                incrementCounterIf();
                incrementCounterGet();
            }
        }
        sleep();
        swap(i + 1, right);
        highlightClear();
        highlightSorted(i + 1);
        return i + 1;
    }
}

