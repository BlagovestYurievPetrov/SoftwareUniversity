import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List <Character> digits = new ArrayList<>();
        List <Character> letters= new ArrayList<>();
        List <Character> other= new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))){
                digits.add(input.charAt(i));
            } else if (Character.isLetter(input.charAt(i))){
                letters.add(input.charAt(i));
            } else {
                other.add(input.charAt(i));
            }
        }
        for (Character digit : digits) {
            System.out.print(digit);
        }
        System.out.println();
        for (Character letter : letters) {
            System.out.print(letter);
        }
        System.out.println();
        for (Character character : other) {
            System.out.print(character);
        }


    }
}
