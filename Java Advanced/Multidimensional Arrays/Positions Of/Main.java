import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");
        int r = Integer.parseInt(tokens[0]);
        int c = Integer.parseInt(tokens[1]);
        int [][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            int[] ints = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[i]=ints;
        }
        int comparator = Integer.parseInt(scanner.nextLine());
        boolean bul = false;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j]==comparator){
                    System.out.println(i+" "+j);
                    bul = true;
                }
            }
        }
        if (!bul){
            System.out.println("not found");
        }

    }
}

