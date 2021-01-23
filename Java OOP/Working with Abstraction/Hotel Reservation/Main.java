import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //50.25 5 Summer VIP
        String[] split = scanner.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(split[0]);
        int days = Integer.parseInt(split[1]);
        Season season = Season.valueOf(split[2].toUpperCase());
        Discount discount = Discount.valueOf(split[3].toUpperCase());
        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay, days, season, discount);
        System.out.printf("%.2f",priceCalculator.calculatePrice(priceCalculator));


    }
}
