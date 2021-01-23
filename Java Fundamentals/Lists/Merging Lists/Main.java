import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] line = scanner.nextLine().split(" ");
        String [] lineTwo = scanner.nextLine().split(" ");
        List<Integer> firstNumbers = getIntegersListFromStringInput(line);
        List<Integer> secondNumbers = getIntegersListFromStringInput(lineTwo);
        List<Integer> results = new ArrayList<>();
        int max = Math.max(secondNumbers.size(),firstNumbers.size());
        for (int i = 0; i < max; i++) {
            if (i<firstNumbers.size()){
                results.add(firstNumbers.get(i));
            }
            if (i<secondNumbers.size()){
                results.add(secondNumbers.get(i));
            }
        }
        ListPrinting(results);
    }

    private static void ListPrinting(List<Integer> numbers) {
        for (int number:numbers) {
            System.out.print(number + " ");

        }
    }


    private static List<Integer> getIntegersListFromStringInput(String[] line) {
        List <Integer> numbers = new ArrayList<>();
        for (String string:line){
            int number = Integer.parseInt(string);
            numbers.add(number);
        }
        return numbers;
    }
}

