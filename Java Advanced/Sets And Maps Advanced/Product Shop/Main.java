import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap <String, HashMap<String,Double>> bigMap = new TreeMap<>();
        String [] input = scanner.nextLine().split(", ");
        while (!input[0].equals("Revision")){
            String shop = input[0];
            String product = input[1];
            double price = Double.parseDouble(input[2]);
            bigMap.putIfAbsent(shop,new LinkedHashMap<>());
            bigMap.get(shop).put(product,price);

            input= scanner.nextLine().split(", ");
        }
        bigMap.entrySet()
                .stream()
                .forEach(entry ->{
                    System.out.println(entry.getKey()+"->");
                    entry.getValue().entrySet()
                            .stream()
                            .forEach(innerEntry -> {
                                System.out.printf("Product: %s, Price: %.1f%n",innerEntry.getKey(),innerEntry.getValue());
                            });
                });

    }
}