import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        printRow(n);
        printBot(n);
    }

    private static void printRow(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                System.out.print(" ");
            }
            for (int j = -1; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    private static void printBot(int n){
        for (int i = 0; i < n; i++) {
            for (int j = -1; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j < n-i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }
}
