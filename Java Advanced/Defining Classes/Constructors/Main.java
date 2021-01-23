import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayDeque<Car> carsQueue = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            if (tokens.length==1){
                Car car = new Car(tokens[0]);
                carsQueue.offer(car);
            } else {
                Car car = new Car(tokens[0],tokens[1],Integer.parseInt(tokens[2]));
                carsQueue.offer(car);
            }


        }
        while (!carsQueue.isEmpty()) {
            System.out.println(carsQueue.poll().carInfo());
        }

    }
}
