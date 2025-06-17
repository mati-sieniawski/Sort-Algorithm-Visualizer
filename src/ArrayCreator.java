import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;
import java.util.Collections;

public class ArrayCreator {
    private static ObservableList<Integer> list = FXCollections.observableArrayList();
    public static ObservableList<Integer> generateValues(int size, int baseValue, int mode){
        list = FXCollections.observableArrayList(Collections.nCopies(size, 0));
        if (mode == 0) { fillRandom(size, baseValue); }
        else if (mode == 1) {fillSorted(size, baseValue); }
        else if (mode == 2) {fillSortedReverse(size, baseValue); }
        return list;
    }

    private static void fillRandom(int size, int baseValue){
        int i = 1, id;
        Random gen = new Random();
        while(i - 1 < size){
            id = gen.nextInt(size);
            if(list.get(id) == 0){
                list.set(id, i*baseValue);
                i++;
            }
        }
    }

    private static void fillSorted(int size, int baseValue){
        for (int i = 0; i < size; i++){
            list.set(i , (i+1)*baseValue);
        }
    }

    private static void fillSortedReverse(int size, int baseValue){
        for (int i = 0; i < size; i++){
            list.set(i, baseValue*(size - i));
        }
    }

}
