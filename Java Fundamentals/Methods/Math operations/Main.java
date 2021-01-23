import java.util.Scanner;
public class Main {
    public static double calculator (double num1, String operator, double num2){
        double result = 0.0;
        switch (operator){
            case "-":
                result= num1-num2;
                break;
            case "*":
                result= num1*num2;
                break;
            case "+":
                result= num1+num2;
                break;
            case "/":
                result= num1/num2;
                break;

        }
        return result;
    }

    public static void main (String[]args){
        Scanner scanner = new Scanner(System.in);
        double num1 = Double.parseDouble(scanner.nextLine());
        String operator = scanner.nextLine();
        double num2 = Double.parseDouble(scanner.nextLine());
        System.out.printf("%.0f",calculator(num1, operator,num2));
    }
}