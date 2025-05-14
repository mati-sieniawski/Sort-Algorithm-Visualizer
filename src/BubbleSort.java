import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class BubbleSort {

    private final ObservableList<Integer> list;
    private final int baseDelayMillis;
    private final int from;
    private final int to;
    private final Label stepCounterLabel;
    private double speedMultiplier = 1.0;
    private Label speedLabel;

    private int steps = 0;

    public BubbleSort(ObservableList<Integer> list, int baseDelayMillis, int from, int to, Label stepCounterLabel) {
        this.list = list;
        this.baseDelayMillis = baseDelayMillis;
        this.from = from;
        this.to = to;
        this.stepCounterLabel = stepCounterLabel;
    }

    public void setSpeedLabel(Label label) {
        this.speedLabel = label;
        updateSpeedLabel();
    }

    public void increaseSpeed() {
        if (speedMultiplier < 4.0) {
            speedMultiplier *= 2;
            updateSpeedLabel();
        }
    }

    public void decreaseSpeed() {
        if (speedMultiplier > 0.25) {
            speedMultiplier /= 2;
            updateSpeedLabel();
        }
    }

    private void updateSpeedLabel() {
        if (speedLabel != null) {
            Platform.runLater(() -> speedLabel.setText("x" + String.format("%.1f", speedMultiplier)));
        }
    }

    public void startSorting() {
        new Thread(() -> {
            try {
                for (int i = from; i < to; i++) {
                    for (int j = from; j < to - (i - from); j++) {
                        if (list.get(j) > list.get(j + 1)) {
                            int a = list.get(j);
                            int b = list.get(j + 1);
                            int finalJ = j;
                            Platform.runLater(() -> {
                                list.set(finalJ, b);
                                list.set(finalJ + 1, a);
                            });
                        }
                        steps++;
                        Platform.runLater(() -> stepCounterLabel.setText("Kroki: " + steps));
                        Thread.sleep((long) (baseDelayMillis / speedMultiplier));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
