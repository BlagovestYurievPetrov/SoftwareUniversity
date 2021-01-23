public class BankAccount {
    private final static double DEFAULT_INTEREST_RATE = 0.02;
    private static double interestRate = DEFAULT_INTEREST_RATE;
    private static int bankAccountCount = 1;
    private int id ;

    public static double getInterestRate() {
        return interestRate;
    }

    private double balance;
    BankAccount(){
        this.id=bankAccountCount++;
    }
    public void deposit(double amount){
        this.balance+=amount;
    }

    public static void setInterestRate(double interestRate) {
        BankAccount.interestRate = interestRate;
    }

    public double getInterest (int years){

        return BankAccount.interestRate*years*this.balance;
    }

}
