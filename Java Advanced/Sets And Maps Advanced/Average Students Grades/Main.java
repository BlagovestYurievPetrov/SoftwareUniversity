

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        TreeMap<String, ArrayList<Double>> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String [] tokens = scanner.nextLine().split(" ");
            String name = tokens[0];
            double grade = Double.parseDouble(tokens[1]);
            map.putIfAbsent(name,new ArrayList<>());
            map.get(name).add(grade);



        }
        double sum = 0.0;
        for (String names : map.keySet()) {
            System.out.print(names+" -> ");

            for (int i = 0; i < map.get(names).size(); i++) {
                System.out.printf("%.2f",map.get(names).get(i));
                System.out.print(" ");
                sum+=map.get(names).get(i);
            }
            System.out.print("(avg: ");

            System.out.printf("%.2f",sum/map.get(names).size());
            System.out.print(")");
            System.out.println();
            sum=0.0;
        }

    }
}