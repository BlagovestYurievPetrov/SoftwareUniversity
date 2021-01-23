import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");
        List<BankAccount> accounts = new ArrayList<>();
        int createCounter = 1;
        while (!tokens[0].equals("End")){
            String command = tokens[0];
            if (command.equals("Create")){
                BankAccount bankAccount = new BankAccount();
                System.out.printf("Account ID%d created%n",createCounter);
                createCounter++;
                accounts.add(bankAccount);


            } else if (command.equals("Deposit")){
                if (accounts.size()<Integer.parseInt(tokens[1])) {
                    System.out.println("Account does not exist");
                } else {
                    accounts.get(Integer.parseInt(tokens[1])-1).deposit(Double.parseDouble(tokens[2]));
                    System.out.printf("Deposited %.0f to ID%s%n", Double.parseDouble(tokens[2]), tokens[1]);
                }
            } else if (command.equals("SetInterest")){
                BankAccount.setInterestRate(Double.parseDouble(tokens[1]));

            } else if (command.equals("GetInterest")){
                if (accounts.size()<Integer.parseInt(tokens[1])){
                    System.out.println("Account does not exist");
                } else {
                    double interest = accounts.get(Integer.parseInt(tokens[1]) - 1).getInterest(Integer.parseInt(tokens[2]));
                    System.out.printf("%.2f%n",interest);
                }
            }





            tokens=scanner.nextLine().split("\\s+");
        }



    }
}
