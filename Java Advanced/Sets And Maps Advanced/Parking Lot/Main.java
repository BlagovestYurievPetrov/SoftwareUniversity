import java.util.LinkedHashSet;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        LinkedHashSet <String> parking = new LinkedHashSet<>();
        while (!input.equals("END")){
            String [] tokens = input.split(", ");
            String command = tokens[0];
            String car = tokens[1];
            if (command.equals("IN")){
                parking.add(car);
            } else {
                parking.remove(car);
            }
            input=scanner.nextLine();
        }
        if (!parking.isEmpty()){
            for (String s : parking) {
                System.out.println(s);
            }
        } else {
            System.out.println("Parking Lot is Empty");
        }

    }
}
