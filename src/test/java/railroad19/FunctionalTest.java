package railroad19;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Implement the mean, median, mode and range methods of the Calculator class in Java 8 Functional
 * style with the minimum code necessary to pass the tests.
 */
class FunctionalTest {

    @Test
    void range() {
        assertEquals(14, calculator.range(3, 17, 15, 11, 9));
    }

    @Test
    void mean() {
        assertEquals(12.5, calculator.mean(13, 19, null, 14, 16, 5, 8), 0);
    }

    @Test
    void median() {
        assertEquals(6, calculator.median(7, 11, 6, 2, 5), 0);
        assertEquals(13.5, calculator.median(13, 18, 14, 16, 5, 8), 0);
    }

    @Test
    void mode() {
        assertArrayEquals(new int[] { 3 }, calculator.mode(5, 2, 3, 6, 4, 1, 3));
        assertArrayEquals(new int[] { 3, 5 }, calculator.mode(4, 5, 3, 1, 3, 2, 5, 6));
        assertArrayEquals(new int[] { 5 }, calculator.mode(4, 5, 5, 3, 1, 3, 2, 5, 6));
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, calculator.mode(1, 3, 2, 4, 5));
    }

    private class Calculator {

        /**
         * range: the difference between min and max values
         */
        int range(int... input) {
            List<Integer> result = Arrays.stream(input).sorted().mapToObj(x -> (Integer) x)
                    .collect(Collectors.toList());
            var min = result.get(0);
            var max = result.get(input.length - 1);

            return max - min;
        }

        /**
         *  mean: the average of the numbers
         */
        double mean(Integer... input) {
            var result = Arrays.stream(input)
                    .filter(x -> x != null)
                    .mapToDouble(x -> (double) x)
                    .average()
                    .orElse(Double.NaN);
            return result;
        }

        /**
         * median: the middle number in a sorted list
         * ...if there are two middle values, return the average of the two
        */
        double median(int... input) {
            List<Integer> result = Arrays.stream(input).sorted().mapToObj(x -> (Integer) x)
                    .collect(Collectors.toList());
            var middle = input.length / 2;
            if (middle * 2 == input.length) { // average of 2
                var startMiddle = result.get(middle - 1);
                var finishMiddle = result.get(middle);

                return (((double) startMiddle) + ((double) finishMiddle)) / 2;
            }
            return result.get(middle);

        }

        /**
         *  mode: the most frequently occurring number
         */
        int[] mode(int... input) {
            Map<Integer, Long> result = Arrays.stream(input)
                    .boxed()
                    .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

            var count = result.entrySet().stream()
                    .map(e -> e.getValue()).sorted(Comparator.reverseOrder()).findFirst().get();

            List<Integer> finalResult = result.entrySet().stream()
                    .filter(x -> count.equals(x.getValue()))
                    .map(x -> x.getKey())
                    .collect(Collectors.toList());
            var array = new int[finalResult.size()];
            for (var i = 0; i < finalResult.size(); i++) {
                array[i] = finalResult.get(i);
            }
            return array;
        }
    }

    private final Calculator calculator = new Calculator();
}
