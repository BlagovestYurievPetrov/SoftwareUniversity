import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        switch (country){
            case "England":
                System.out.println("English");
                break;
            case "USA":
                System.out.println("English");
                break;
            case "Spain":
                System.out.println("Spanish");
                break;
            case "Argentina":
                System.out.println("Spanish");
                break;
            case "Mexico":
                System.out.println("Spanish");
                break;
            default:
                System.out.println("unknown");
                break;
        }

    }
}