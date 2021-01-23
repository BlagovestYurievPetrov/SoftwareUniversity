import java.util.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] text = scanner.nextLine().split(" ");
        Random rng = new Random();
        for (int i = 0; i <text.length ; i++) {
            int firstIndex = rng.nextInt(text.length);
            int secondIndex = rng.nextInt(text.length);
            String swapWord = text[firstIndex];
            text[firstIndex]=text[secondIndex];
            text [secondIndex]=swapWord;
        }
        for (String message:text){
            System.out.println(message);
        }
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

