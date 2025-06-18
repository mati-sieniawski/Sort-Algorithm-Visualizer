import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class QuickSortHoare extends SortingAlgorithm {

    public QuickSortHoare(ObservableList<Integer> list, int baseDelayMillis,
                     Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            qsort(0, listSize-1);
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm QuickSortHoare zakończył działanie."));
        }).start();
    }

    public void qsort(int left, int right){
        incrementCounterIf();
        if (left < right) {
            int partitionIndex = partitionHoare(left, right);
            qsort(left, partitionIndex);
            qsort(partitionIndex + 1, right);
        }
        if (left == right) { highlightSorted(left); }
    }

    protected int partitionHoare(int left, int right) {
        highlightClear();
        int pivot = (left + right) / 2;
        int pivotValue = list.get(pivot); incrementCounterGet();
        int l = left;
        int r = right;

        highlightCursor(pivot);
        highlightCompare(l, r);
        while (true) {
            while (list.get(l) < pivotValue){
                incrementCounterIf();
                incrementCounterGet();
                l++;
                sleep();
                highlightCompare(l, r);
            }
            incrementCounterIf();
            incrementCounterGet();

            while (list.get(r) > pivotValue){
                incrementCounterIf();
                incrementCounterGet();
                r--;
                sleep();
                highlightCompare(l, r);
            }
            incrementCounterIf();
            incrementCounterGet();

            incrementCounterIf();
            if (l >= r) {
                highlightClear();
                return r;
            }
            sleep();
            swap(l, r);
        }
    }
}

