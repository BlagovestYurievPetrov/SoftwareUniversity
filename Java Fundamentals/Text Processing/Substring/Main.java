import java.util.*;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String last = scanner.nextLine();
        while (last.contains(first)){
            last = last.replace(first,"");}
        System.out.println(last);
    }
}



