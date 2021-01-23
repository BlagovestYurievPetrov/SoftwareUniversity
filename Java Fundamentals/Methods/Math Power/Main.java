import java.text.DecimalFormat;
import java.util.Scanner;
public class Main {
    private static double toPower(double number, int power) {
        return Math.pow(number,power);
    }


    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        double number = Double.parseDouble(scanner.nextLine());
        int power = Integer.parseInt(scanner.nextLine());
        double powered = toPower(number,power);
        System.out.println(new DecimalFormat("0.####").format(powered));

    }
}