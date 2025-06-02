import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;
import java.util.Collections;

public class ArrayCreator {
    public static ObservableList<Integer> generateValues(int size, int baseValue){
        ObservableList<Integer> list = FXCollections.observableArrayList(Collections.nCopies(size, 0)); //wypelnienie 0
        int i = 1, id;
        Random gen = new Random();
        // wybieranie pozycji do ustawienia kolejnej wartosci w kolejnosci
        // jezeli pole niepuste szuka nastepnego
        while(i - 1 < size){
            id = gen.nextInt(size);
            if(list.get(id) == 0){
                list.set(id, i*baseValue);
                i++;
            }
        }
    return list;
    }

}
