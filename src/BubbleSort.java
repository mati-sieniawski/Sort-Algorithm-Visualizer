import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(ObservableList<Integer> list, int baseDelayMillis,
                      Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            for (int i = 0; i < listSize; i++) {
                for (int j = 0; j < listSize - i - 1; j++) {
                    highlightCompare(j, -1);
                    highlightCursor(j+1);
                    if (list.get(j) > list.get(j + 1)) {
                        incrementCounterIf();
                        incrementCounterGet();incrementCounterGet();
                        sleep();
                        swap(j, j + 1);
                    }
                    sleep();
                    highlightClear();
                }
                highlightSorted(listSize - i - 1);
            }
             Platform.runLater(() -> finishedLabel.setText("✅Algorytm ShellSort zakończył działanie."));
        }).start();
    }


}
