package as;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ArrayToMap {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Val", "Zobau", 1),
                new Person("Mike", "Zobau", 2),
                new Person("Nata", "Zobava", 3),
                new Person("Ulia", "Zobava", 4),
                new Person("Katia", "Zobava", 5));
        Map<String, List<Person>> map = persons.stream().collect(
                Collectors.groupingBy(Person::getLastName));
        Map<String, Set<String>> lastNamesByCity = persons
                .stream().collect(
                        groupingBy(Person::getLastName, mapping(Person::getName, toSet())));
        map.forEach((k, v) -> System.out
                .println("Surname : " + k
                        + " Names: " + v.stream().map(Person::getName).collect(Collectors.joining(", "))));

        lastNamesByCity.forEach((k, v) -> System.out
                .println("TT Surname : " + k
                        + " Names: " + v.stream().collect(Collectors.joining(", "))));
        // highest salary overall
        var person = persons.stream().collect(Collectors.minBy(Comparator.comparing(Person::getSalary))).get();
        System.out.println("highest salary overall : " + person.getLastName() + " - " + person.getSalary());
        // highest salary in each surname
        Map<String, Optional<Person>> highestSalaryBySurname = persons.stream().collect(
                Collectors.groupingBy(Person::getLastName,
                        Collectors.reducing(
                                BinaryOperator.minBy(
                                        Comparator.comparing(Person::getSalary)))));
        highestSalaryBySurname.forEach(
                (k, v) -> System.out.println("surname - " + k + " ; name with lowest salary - " + v.get().name));
        System.out.println("----------------------");
        // count of persons in each surname
        Map<String, Long> personsInSurname = persons.stream().collect(
                Collectors.groupingBy(Person::getLastName,
                        Collectors.counting()));
        personsInSurname.forEach((k, v) -> System.out.println("Surname :" + k + " has count of members: " + v));
    }

    public static class Person {

        public Person(String name, String lastName, Integer salary) {
            super();
            this.name = name;
            this.lastName = lastName;
            this.salary = salary;
        }

        Integer salary;
        String name;
        String lastName;

        public Integer getSalary() {
            return salary;
        }

        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }

    }
}
