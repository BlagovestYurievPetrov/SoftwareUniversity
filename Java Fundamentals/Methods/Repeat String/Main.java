import java.util.Scanner;
public class Main {
    public static String copyString(String line, int n) {
        String sum = "";
        for (int i = 0; i <n ; i++) {
            sum += line;
        }
        return sum;
    }


    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());
        String result = copyString(line,n);
        System.out.println(result);

    }
}