import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List <Integer> sorted = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).sorted((n1, n2) -> n2.compareTo(n1)).collect(Collectors.toList());
        if(sorted.size()==1){
            for (int i = 0; i <1 ; i++) {
                System.out.print(sorted.get(i)+ " ");
            }
        } else if (sorted.size()==2){
            for (int i = 0; i <2 ; i++) {
                System.out.print(sorted.get(i)+ " ");
            }
        } else if (sorted.size()>=3){
            for (int i = 0; i <3 ; i++) {
                System.out.print(sorted.get(i)+ " ");
            }
        }

    }
}




