import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double pound = scanner.nextDouble();
        double dollars = pound*1.31;
        System.out.printf("%.3f",dollars);
    }
}
