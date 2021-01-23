import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int odd = -1;
        int counter =1;
        while (counter<=n){
            odd+=2;
            System.out.printf("%d%n", odd);
            sum+=odd;
            counter++;
        }
        System.out.printf("Sum: %d", sum);
    }
}