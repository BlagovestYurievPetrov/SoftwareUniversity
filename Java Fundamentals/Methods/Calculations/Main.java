import java.util.Scanner;
public class Main {
    static void substract(int a,int b) {
        int result = a-b;
        System.out.println(result);
    }
    static void add(int a,int b) {
        int result = a+b;
        System.out.println(result);
    }
    static void multiply(int a,int b) {
        int result = a*b;
        System.out.println(result);
    }
    static void divide(int a,int b) {
        int result = a/b;
        System.out.println(result);
    }


    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        if (command.equals("substract")){
            substract(a,b);
        } else if (command.equals("add")){
            add(a,b);
        } else if(command.equals("multiply")){
            multiply(a,b);
        } else if(command.equals("divide")){
            divide(a,b);
        }

    }
}