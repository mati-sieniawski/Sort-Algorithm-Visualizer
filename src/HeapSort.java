import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class HeapSort extends SortingAlgorithm {

    public HeapSort(ObservableList<Integer> list, int baseDelayMillis,
                    Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {

            for (int i = listSize / 2 - 1; i >= 0; i--) {
                heapify(listSize, i);
                sleep();
            }

            for (int i = listSize - 1; i > 0; i--) {
                sleep();
                highlightClear();
                swap(0, i);
                highlightSorted(i);
                sleep();
                heapify(i, 0);
            }
            highlightSorted(0);
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm HeapSort zakończył działanie."));
        }).start();
    }

    private void heapify(int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        highlightCursor(rootIndex);
        if (left < heapSize) {
            incrementCounterIf();
            highlightCompareLeft(left);
            if (list.get(left) > list.get(largest)) {
                largest = left;
                incrementCounterIf();
                incrementCounterGet(); incrementCounterGet();

            }
        }

        if (right < heapSize) {
            highlightCompareRigth(right);
            incrementCounterIf();
            if (list.get(right) > list.get(largest)) {
                largest = right;
                incrementCounterIf();
                incrementCounterGet(); incrementCounterGet();
            }
        }

        if (largest != rootIndex) {
            incrementCounterIf();
            sleep();
            swap(rootIndex, largest);
            sleep();
            highlightClear();
            heapify(heapSize, largest);
        }
    }
}