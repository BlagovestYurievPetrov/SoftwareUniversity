import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        long population = scanner.nextLong();
        double area = scanner.nextDouble();
        System.out.printf("Town %s has population of %d and area %.0f square km.",name,population,area);
    }
}
