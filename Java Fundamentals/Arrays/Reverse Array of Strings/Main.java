import java.util.Scanner;
public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String [] arr = line.split(" ");
        for (int i = 0; i < arr.length/2; i++) {
            String oldLeft = arr[i];
            arr[i]=arr[arr.length-1-i];
            arr[arr.length-1-i]=oldLeft;

        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

}