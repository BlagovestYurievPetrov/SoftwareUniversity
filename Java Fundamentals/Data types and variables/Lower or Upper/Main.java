import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char input = scanner.nextLine().charAt(0);
        if (input>=65&&input<=90){
            System.out.printf("upper-case");
        } else {
            System.out.printf("lower-case");
        }
    }
}
