import javafx.application.Platform;
import javafx.collections.ObservableList;

public class BubbleSort {

    private final ObservableList<Integer> list;
    private final long delayMillis;

    public BubbleSort(ObservableList<Integer> list, long delayMillis) {
        this.list = list;
        this.delayMillis = delayMillis;
    }

    public void startSorting() {
        new Thread(() -> {
            try {
                int n = list.size();
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (list.get(j) > list.get(j + 1)) {
                            int temp = list.get(j);
                            int finalJ = j;
                            Platform.runLater(() -> {
                                list.set(finalJ, list.get(finalJ + 1));
                                list.set(finalJ + 1, temp);
                            });

                            Thread.sleep(delayMillis); 
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
