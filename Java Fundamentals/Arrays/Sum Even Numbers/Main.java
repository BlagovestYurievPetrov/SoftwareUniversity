import java.util.Scanner;
public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String [] arr = line.split(" ");
        int [] numbers = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i]=Integer.parseInt(arr[i]);
            if(numbers[i]%2==0){
                sum+=numbers[i];
            }
        }
        System.out.println(sum);
    }

}