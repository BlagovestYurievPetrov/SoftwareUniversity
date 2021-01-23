package Hello;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Bulgarian("Kiro"));
        people.add(new European("Hans"));
        people.add(new Chinese("Keng"));
        for (Person person : people) {
            System.out.println(person.sayHello());
        }
    }
}
