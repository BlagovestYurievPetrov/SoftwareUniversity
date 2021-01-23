import java.util.Scanner;
public class Main {
    static void ordering (String order,int quantity) {
        double coffeePrice = 1.5;
        double waterPrice = 1.0;
        double cokePrice = 1.4;
        double snacksPrice = 2.0;
        if (order.equals("coffee")) {
            System.out.printf("%.2f", quantity * coffeePrice);
        } else if (order.equals("water")) {
            System.out.printf("%.2f", quantity * waterPrice);
        } else if (order.equals("coke")) {
            System.out.printf("%.2f", quantity * cokePrice);
        } else if (order.equals("snacks")) {
            System.out.printf("%.2f", quantity * snacksPrice);
        }
    }

    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        String order = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());
        ordering(order,quantity);
    }
}