import java.util.*;

public class Main {


    static class Student {

        private String firstName;
        private String lastName;
        private int age;
        private String homeTown;

        public Student(String firstName, String lastName, int age, String homeTown) {

            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.homeTown = homeTown;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getHomeTown() {
            return homeTown;
        }

        public int getAge() {
            return age;
        }

        public void setHomeTown(String homeTown) {
            this.homeTown = homeTown;
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List <Student> students = new ArrayList<>();
        String input= scanner.next();
        while(!input.equals("end")){
            String firstName = input;
            String lastName = scanner.next();
            int age = scanner.nextInt();
            String homeTown = scanner.next();

            Student student = new Student(firstName,lastName,age,homeTown);
            students.add(student);
            input = scanner.next();
        }
        String cityName = scanner.next();
        for (Student student : students) {
            if (student.getHomeTown().equals(cityName)){
                System.out.printf("%s %s is %d years old%n", student.getFirstName(), student.getLastName(),student.getAge());
            }
        }

    }
}
