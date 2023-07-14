package as;

import java.util.Map;
import java.util.stream.Collectors;

public class StreamOnString {
    public static void main(String[] args) {
        var input = "hello hello";
        Map<String, Long> result = input.chars().mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        System.out.println(result);
    }

}
