import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Random;

public class BogoSort extends SortingAlgorithm {

    public BogoSort(ObservableList<Integer> list, int baseDelayMillis,
                         Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    private final Random random = new Random();

    public void startSorting() {
        new Thread(() -> {
            while (!isSorted()) {
                incrementCounterIf();
                shuffleList();
                sleep();
            }
            incrementCounterIf();

            for (int i = 0; i < listSize; i++){
                highlightSorted(i);
            }
            sleep();
            list.set(0, list.getFirst());
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm BogoSort zakończył działanie."));
        }).start();
    }

    private boolean isSorted() {
        for (int i = 0; i < list.size() - 1; i++) { incrementCounterIf();
            incrementCounterIf();
            incrementCounterGet();incrementCounterGet();
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        incrementCounterIf();
        return true;
    }

    private void shuffleList() {
        Platform.runLater(() -> {
            Collections.shuffle(list, random);
        });
    }
}
