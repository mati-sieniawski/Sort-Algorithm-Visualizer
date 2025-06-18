import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Collections;

public class ShellSort extends SortingAlgorithm {

    private final ArrayList<Integer> spacing = new ArrayList<>();
    private final ArrayList<Integer> indexes = new ArrayList<>();

    public ShellSort(ObservableList<Integer> list, int baseDelayMillis,
                     Label stepLabel, BarChartVisualizer visualizer, Label finishedLabel) {
        super(list, baseDelayMillis, stepLabel, visualizer, finishedLabel);
    }

    public void startSorting(){
        new Thread(() -> {
            generateSpacing();
            for (int interval : spacing){
                for (int startid = 0; startid < interval; startid++){
                    incrementCounterIf();
                    for (int i = startid; i < listSize; i+=interval){
                        incrementCounterIf();
                        indexes.add(i);
                    }
                    incrementCounterIf();
                    simpleInsertionSort(interval);
                    indexes.clear();
                }
                incrementCounterIf();
            }
            Platform.runLater(() -> finishedLabel.setText("✅Algorytm ShellSort zakończył działanie."));
        }).start();
        
    }

    private void generateSpacing(){
        int max = listSize/3;
        int k = 1;
        spacing.add(1);
        while (powerInt(2, k) + 1 < max){
            spacing.add(powerInt(2, k) + 1);
            k++;
        }
        spacing.sort(Collections.reverseOrder());
    }

    private void simpleInsertionSort(int check) {
        for (int i = 1; i < indexes.size(); i++){
            incrementCounterIf();
            highlightCursor(indexes.get(i));
            if (check == 1) { highlightSorted(0);}
            int j = i - 1;
            highlightCompare(indexes.get(j), -1);
            int tempValue = list.get(indexes.get(i)); incrementCounterGet();
            while (j >= 0 && list.get(indexes.get(j)) > tempValue){
                incrementCounterGet();
                incrementCounterIf(); incrementCounterIf();
                sleep();
                highlightCompare(indexes.get(j), indexes.get(j + 1));
                sleep();
                set(indexes.get(j + 1), list.get(indexes.get(j)));
                incrementCounterGet();
                j--;
            }
            if (j >= 0) { incrementCounterIf(); incrementCounterGet();}
            incrementCounterIf();
            sleep();
            set(indexes.get(j + 1), tempValue);
            sleep();
            highlightClear();
            if (check == 1) { highlightSorted(i); }
        }
    }
}