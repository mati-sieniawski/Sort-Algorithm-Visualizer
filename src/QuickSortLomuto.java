import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Label;

public class QuickSortLomuto extends SortingAlgorithm {

    public QuickSortLomuto(ObservableList<Integer> list, int baseDelayMillis,
                          Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            qsort(0, listSize-1);
        }).start();
    }

    public void qsort(int left, int right) {
        if (left == right) { highlightSorted(left); }
        if (left < right) {
            int p = partitionLomuto(left, right);
            qsort(left, p - 1);
            qsort(p + 1, right);
        }
    }

    protected int partitionLomuto(int left, int right) {
        int pivotValue = list.get(right); // pivot to ostatni element
        int i = left - 1;

        highlightCursor(right);

        for (int j = left; j < right; j++) {
            sleep();
            highlightCompare(i, j);
            if (list.get(j) <= pivotValue) {
                sleep();
                i++;
                highlightCompare(i, j);
                swap(i, j);
            }
        }
        sleep();
        swap(i + 1, right);
        highlightClear();
        highlightSorted(i + 1);
        return i + 1;
    }
}

