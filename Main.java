import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        Вывод на экран списка всего населения
//        for (Person person : persons) {
//            System.out.println(person);
//        }

        long cnt = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

//        Вывод на экран количества несовершеннолетних
//        System.out.println("Количество несовершеннолетних граждан: " + cnt);

        List<String> conscripts = persons.stream()
                .filter(man -> man.getSex() == Sex.MAN)
                .filter(man -> man.getAge() >= 18 && man.getAge() < 27)
                .map(man -> man.getFamily())
                .collect(Collectors.toList());

//        Вывод на экран фамилий призывников
//        System.out.println("Фамилии призывников: " + conscripts);

        List<Person> workingPopulationHigherEducation = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && person.getAge() < 65)
                .filter(person -> !(person.getSex() == Sex.WOMAN && person.getAge() >= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

//        Вывод на экран списка работоспособных с высшим образованием
//        System.out.println("Работоспособные с высшим образованием: ");
//        for (Person person : workingPopulationHigherEducation) {
//            System.out.println(person);
//        }
    }
}
