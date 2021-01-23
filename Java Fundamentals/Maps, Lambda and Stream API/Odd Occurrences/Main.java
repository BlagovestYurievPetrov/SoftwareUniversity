import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] words = scanner.nextLine().split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i]=words[i].toLowerCase();
        }
        LinkedHashMap<String,Integer> wordsMap = new LinkedHashMap<>();
        for (String word : words) {
            if(wordsMap.containsKey(word)){
                wordsMap.put(word,wordsMap.get(word)+1);
            } else {
                wordsMap.put(word,1);
            }
        }
        List <String> odds = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getValue()%2==1){
                odds.add(entry.getKey());
            }
        }
        for (int i = 0; i < odds.size(); i++) {
            System.out.print(odds.get(i));
            if (i<odds.size()-1){
                System.out.print(", ");
            }
        }
    }
}



