import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] words = scanner.nextLine().split(" ");
        for (int i = 0; i < words.length; i++) {
            int x = words[i].length();
            for (int j = 0; j < x; j++) {
                System.out.print(words[i]);
            }
        }
    }
}



