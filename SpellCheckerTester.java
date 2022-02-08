import java.util.*;


public class SpellCheckerTester {
    
    public static void main (String[] args) {
        SpellChecker check = new SpellChecker("words.txt");
        
        List<String> incorrectWords = new ArrayList<String>();
        incorrectWords =  check.getIncorrectWords("test.txt");
        //System.out.println(check.getSuggestions("recedig"));
        
       
        for (String word: incorrectWords) {
            System.out.println(word);
            System.out.println(check.getSuggestions(word));
        }
        
        
       
        
      
        
    }
}