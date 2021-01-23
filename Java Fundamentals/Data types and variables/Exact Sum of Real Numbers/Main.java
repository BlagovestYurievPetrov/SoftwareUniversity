import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigDecimal sum = new BigDecimal(0.);
        for (int i = 1; i <=n ; i++) {
            BigDecimal x = scanner.nextBigDecimal();
            sum = sum.add(x);
        }
        System.out.println(sum);
    }
}
