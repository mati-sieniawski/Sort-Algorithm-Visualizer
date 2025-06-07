import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class SelectionSort extends SortingAlgorithm{

    public SelectionSort(ObservableList<Integer> list, int baseDelayMillis,
                         Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {
            for (int i = 0; i < listSize - 1; i++) {
                int minIndex = i;
                visualizer.visCursor(i);
                for (int j = i + 1; j < listSize; j++) {
                    visualizer.visCompare(j, minIndex);
                    sleep();
                    if (list.get(j) < list.get(minIndex)) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    swap(i, minIndex);
                    sleep();
                };
                visualizer.clearHighlights();
                visualizer.addSorted(i);
            }
            visualizer.addSorted(listSize - 1);
        }).start();
    }
}






