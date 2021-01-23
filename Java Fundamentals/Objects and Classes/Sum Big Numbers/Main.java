import java.math.BigInteger;
import java.util.*;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger a = new BigInteger(scanner.nextLine());
        BigInteger b = new BigInteger(scanner.nextLine());
        BigInteger sum = a.add(b);
        System.out.println(sum);
    }


    private static void ListPrinting (List < Integer > numbers) {
        for (int number : numbers) {
            System.out.print(number + " ");

        }
    }


    private static List<Integer> getIntegersListFromStringInput (String[]line){
        List<Integer> numbers = new ArrayList<>();
        for (String string : line) {
            int number = Integer.parseInt(string);
            numbers.add(number);
        }
        return numbers;
    }
}

