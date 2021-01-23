import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char fName = scanner.nextLine().charAt(0);
        char sName = scanner.nextLine().charAt(0);
        char deli = scanner.nextLine().charAt(0);
        System.out.printf("%c %c %c", deli, sName, fName);
    }
}
