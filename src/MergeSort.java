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

            mergeSort(0, listSize - 1);
            highlightClear();
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm ShellSort zakończył działanie."));
        }).start();
    }

    private void merge(int left, int right, int mid){
        for (int i = left; i <= right; i++) {highlightSortedDel(i);}
        highlightCompare(left, right);
        int l = 0;
        int r = 0;
        int k = left;
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
        incrementCounterIf();

        highlightCursor(k);
        while (l < lengthLeft && r < lengthRight){
            highlightCursor(k);
            incrementCounterIf();
            sleep();
            if (copyList.get(left + l) < copyList.get(mid + 1 + r)){
                list.set(k++, copyList.get(left + l));
                incrementCounterGet();incrementCounterGet();incrementCounterGet();
                incrementCounterIf();
                l++;
            } else {
                list.set(k++, copyList.get(mid + 1 + r));
                incrementCounterGet();
                r++;
            }
            sleep();
            highlightSorted(k-1);
        }
        while (l < lengthLeft){
            incrementCounterIf();
            highlightCursor(k);
            sleep();
            list.set(k++, copyList.get(left + l));
            l++;
            incrementCounterGet();
            sleep();
            highlightSorted(k-1);
        }
        while (r < lengthRight){
            incrementCounterGet();
            incrementCounterIf();
            highlightCursor(k);
            sleep();
            list.set(k++, copyList.get(mid + 1 + r));
            r++;
            sleep();
            highlightSorted(k-1);
        }

        for (int i = left; i <= right; i++){
            copyList.set(i, list.get(i));
            incrementCounterGet();
        }
    }

    private void mergeSort(int left, int right){
        if (left < right) {
            incrementCounterIf();
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, right, mid);
        }
    }



}
