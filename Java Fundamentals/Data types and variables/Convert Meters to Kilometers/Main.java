import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int met = scanner.nextInt();
        double k = met/1000.0;
        System.out.printf("%.2f",k);
    }
}
