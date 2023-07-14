package as;

import java.util.Arrays;
import java.util.Comparator;

public class HighestNumber {
    public static void main(String[] args) {
        Integer[] array = { 1, 6, 3, 4, 5, 6 };
        System.out.println(Arrays.stream(array).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());
    }
}
