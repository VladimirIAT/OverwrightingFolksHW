import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // 1 задание - несовершеннолетние, количество
        System.out.println("1 задание - несовершеннолетние, количеств");
        //System.out.println(persons);
        Stream<Person> stream_1 = persons.stream();
        Stream<Person> stream_2 = stream_1.filter(x -> x.getAge() > 18);
        System.out.println(stream_2.count());

        // 1 задание - несовершеннолетние, количество 2 вариант
        long count = persons.stream()
                .filter(x -> x.getAge() > 18)
                .count();
        System.out.println(count);

        // 2 задание - призывники, список фамилий
        System.out.println("2 задание - призывники, список фамилий");
        Stream<Person> stream_3 = persons.stream();
        Stream<Person> stream_4 = stream_3.filter(x -> x.getSex() == Sex.MAN);
        Stream<Person> stream_5 = stream_4.filter(x -> x.getAge() > 18);
        Stream<Person> stream_6 = stream_5.filter(x -> x.getAge() < 27);
        Stream<String> stream_7 = stream_6.map(Person::getFamily);
        List<String> result = stream_7.limit(10).collect(Collectors.toList());
        System.out.println(result);

        // 3 задание - потенциально работоспособные с высшим образованием
        System.out.println("3 задание - потенциально работоспособные с высшим образованием");
        Stream<Person> stream_8 = persons.stream();
        Stream<Person> stream_9 = stream_8.filter(x -> x.getEducation() == Education.HIGHER);
        Stream<Person> stream_10 = stream_9.filter(x -> x.getAge() > 18);
        Stream<Person> stream_11 = stream_10.filter(x -> x.getSex() == Sex.MAN ?
                x.getAge() < 65 :
                x.getAge() < 60);
        Stream<Person> stream_12 = stream_11.sorted(Comparator.comparing(Person::getFamily));
        Stream<String> stream_13 = stream_12.map(Person::getFamily);
        List<String> result_2 = stream_13.limit(10).collect(Collectors.toList());
        System.out.println(result_2);
    }
}
