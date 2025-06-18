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

            for (int i = listSize / 2 - 1; i >= 0; i--) { incrementCounterIf();
                heapify(listSize, i);
                sleep();
            }
            incrementCounterIf();

            for (int i = listSize - 1; i > 0; i--) { incrementCounterIf();
                sleep();
                highlightClear();
                swap(0, i);
                highlightSorted(i);
                sleep();
                heapify(i, 0);
            }
            incrementCounterIf();

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
        incrementCounterIf();
        if (left < heapSize) {
            highlightCompareLeft(left);
            incrementCounterGet(); incrementCounterGet();
            if (list.get(left) > list.get(largest)) { incrementCounterIf();
                largest = left;
            }
        }

        incrementCounterIf();
        if (right < heapSize) {
            highlightCompareRigth(right);
            incrementCounterIf();
            incrementCounterGet(); incrementCounterGet();
            if (list.get(right) > list.get(largest)) {
                largest = right;
            }

        }

        incrementCounterIf();
        if (largest != rootIndex) {
            sleep();
            swap(rootIndex, largest);
            sleep();
            highlightClear();
            heapify(heapSize, largest);
        }
    }
}