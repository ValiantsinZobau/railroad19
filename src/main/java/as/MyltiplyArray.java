package as;

import java.util.Arrays;
import java.util.List;

public class MyltiplyArray {
    public static void main(String[] args) {
        Integer[] array = { 1, 2, 3, 4, 5 };
        List<Integer> list = Arrays.asList(array);
        System.out.println("the result: " + list.stream().reduce((a, b) -> a * b).get());
    }
}
