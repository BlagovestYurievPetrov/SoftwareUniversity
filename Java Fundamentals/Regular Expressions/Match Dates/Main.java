import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "(?<days>\\d{2})(\\.|-|\\/)(?<month>[A-Z][a-z]{2})\\2(?<years>\\d{4})";
        String text = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(text);

        while (matcher.find()){
            System.out.printf("Day: %s, Month: %s, Year: %s%n",matcher.group(1),matcher.group(3),matcher.group(4));
        }

    }
}

