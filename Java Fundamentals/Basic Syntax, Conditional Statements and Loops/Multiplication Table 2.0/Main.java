import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int counter = Integer.parseInt(scanner.nextLine());
        if (counter>10){
            System.out.printf("%d X %d = %d", n, counter, n*counter);
        } else {
            while (counter <= 10) {
                System.out.printf("%d X %d = %d%n", n, counter, n * counter);
                counter++;
            }
        }
    }
}