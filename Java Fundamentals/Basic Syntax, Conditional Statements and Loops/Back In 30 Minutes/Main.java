import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hours = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());
        int hoursAfter = hours;
        int minutesAfter = minutes+30;
        //..............
        if(minutesAfter>59){
            hoursAfter = hoursAfter + 1;
            minutesAfter -=60;
        }
        if(hoursAfter>23){
            hoursAfter=0;
        }

        System.out.printf("%d:%02d", hoursAfter, minutesAfter);
    }
}