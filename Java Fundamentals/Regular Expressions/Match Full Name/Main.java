import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "\\b([A-Z][a-z]+)\\s([A-Z][a-z]+)";
        String text = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(text);

        while (matcher.find()){
            System.out.print(matcher.group()+ " ");
        }


    }
}

