import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {
    public List<String> getRandomList() {

        List<List<String>> listInputs = new ArrayList<>();
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana", "Grapes"));
        listInputs.add(List.of("Mango", "Orange", "Banana", "Apple", "PineApple", "Banana", "Orange", "Banana", "Grapes"));
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "Banana", "Banana", "Orange", "Banana", "Apple"));
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana", "Banana"));
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "PineApple", "Banana", "Orange", "Banana", "PineApple"));
        listInputs.add(List.of("Apple", "Apple", "Apple", "PineApple", "Banana", "Orange", "Banana", "Grapes"));
        listInputs.add(List.of("Mango", "Orange", "Banana", "Banana", "Orange", "Banana", "Grapes"));
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "Banana", "Banana", "Orange", "Banana", "Apple"));
        listInputs.add(List.of("Apple", "Apple", "Orange", "Apple", "Orange", "Banana", "Banana"));
        listInputs.add(List.of("Apple", "Apple", "Apple", "Apple", "PineApple", "Banana", "Orange", "Banana", "PineApple"));

        Random rand = new Random();
        return listInputs.get(rand.nextInt(0, 10));
    }
}
