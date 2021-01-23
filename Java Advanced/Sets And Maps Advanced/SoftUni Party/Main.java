

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        TreeSet<String> vip = new TreeSet<>();
        TreeSet<String> regular = new TreeSet<>();
        while (!input.equals("PARTY")){
            if (input.charAt(0)>=48&&input.charAt(0)<=57){
                vip.add(input);
            } else {
                regular.add(input);
            }
            input = scanner.nextLine();
        }
        input = scanner.nextLine();
        while (!input.equals("END")){
            vip.remove(input);
            regular.remove(input);
            input=scanner.nextLine();
        }
        System.out.println(vip.size()+regular.size());
        for (String s : vip) {
            System.out.println(s);
        }
        for (String s : regular) {
            System.out.println(s);
        }
    }
}
