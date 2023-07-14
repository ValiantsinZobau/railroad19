package as;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HighestPossibleIntersectionCommonStrings {
    public static void main(String[] args) {
        String[] arr1 = { "AAA", "BBB", "CCC" };
        String[] arr2 = { "AAA", "BBB", "DDD", "EEE" };
        Map<String, Long> result = Stream.of(arr1, arr2).flatMap(arr -> Stream.of(arr))
                .collect(Collectors.groupingBy(Object::toString, LinkedHashMap::new, Collectors.counting()));

        System.out.println(result.entrySet().stream().filter(e -> e.getValue() > 1).map(e -> e.getKey())
                .collect(Collectors.toList()));
        System.out.println(result.entrySet().stream().filter(e -> e.getValue() == 1).map(e -> e.getKey())
                .collect(Collectors.toList()));
    }
}
