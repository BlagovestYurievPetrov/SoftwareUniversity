import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] startingPoints = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Rectangle rectangle = new Rectangle(new Point(startingPoints[0], startingPoints[1]), new Point(startingPoints[2], startingPoints[3]));
        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0){
            startingPoints = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            System.out.println(rectangle.Contains(new Point(startingPoints[0],startingPoints[1]), rectangle));
        }
    }
}
