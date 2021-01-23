import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] strings = scanner.nextLine().split(" ");
        Map <String, Integer> numbers = new TreeMap<>();
        for (String word : strings) {
            if (numbers.containsKey(word)){
                Integer count = numbers.get(word);
                numbers.put(word,count+1);
            } else {
                numbers.put(word,1);
            }
        }
        for (Map.Entry<String, Integer> stringIntegerEntry : numbers.entrySet()) {
            System.out.printf("%s -> %d%n",stringIntegerEntry.getKey(),stringIntegerEntry.getValue());
        }


    }
}



