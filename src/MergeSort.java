import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class MergeSort extends SortingAlgorithm {

    private final ArrayList<Integer> copyList = new ArrayList<>();

    public MergeSort(ObservableList<Integer> list, int baseDelayMillis,
                    Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting() {
        new Thread(() -> {

            copyList.addAll(list);
            for (int i = 0; i < listSize; i++) { incrementCounterGet(); }

            mergeSort(0, listSize - 1);
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm MergeSort zakończył działanie."));
        }).start();
    }

    private void merge(int left, int right, int mid){
        for (int i = left; i <= right; i++) { highlightSortedDel(i); }
        highlightCompare(left, right);
        int l = 0;
        int r = 0;
        int k = left;
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
        highlightCursor(k);
        while (l < lengthLeft && r < lengthRight){
            incrementCounterIf(); incrementCounterIf();
            highlightCursor(k);
            sleep();
            incrementCounterGet();incrementCounterGet();
            incrementCounterIf();
            if (copyList.get(left + l) < copyList.get(mid + 1 + r)){
                list.set(k++, copyList.get(left + l));
                l++;
            } else {
                list.set(k++, copyList.get(mid + 1 + r));
                incrementCounterGet();
                r++;
            }
            sleep();
            highlightSorted(k-1);
        }
        if (l < lengthLeft) { incrementCounterIf(); }
        incrementCounterIf();

        while (l < lengthLeft){
            incrementCounterIf();
            highlightCursor(k);
            sleep();
            list.set(k++, copyList.get(left + l));
            incrementCounterGet();
            l++;
            sleep();
            highlightSorted(k-1);
        }
        incrementCounterIf();

        while (r < lengthRight){
            incrementCounterIf();
            highlightCursor(k);
            sleep();
            list.set(k++, copyList.get(mid + 1 + r));
            incrementCounterGet();
            r++;
            sleep();
            highlightSorted(k-1);
        }
        incrementCounterIf();

        for (int i = left; i <= right; i++){
            copyList.set(i, list.get(i));
            incrementCounterIf();
            incrementCounterGet();
        }
        incrementCounterIf();
    }

    private void mergeSort(int left, int right){
        incrementCounterIf();
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, right, mid);
        }
    }
}
