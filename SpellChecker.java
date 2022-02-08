/* Tamanna Ananna
*/


import java.util.*;
import java.io.*; //buffered reader is io

public class SpellChecker implements SpellCheckerInterface{
    
    private String file;
    private HashSet<String> dictionary;
    
    /*
    The constructor takes in a String of the file name of the dictionary.
    The constructor parses the dictionary file, and stores the words in a HashSet instance.
    */
    public SpellChecker(String filename) {
        //filename is dictionary
        //this.file = filename;
        this.dictionary = new HashSet<String>();
        
        try {
            
            
            File text = new File(filename);
            Scanner input = new Scanner(text);
            //System.out.println(input.nextLine().toLowerCase());
            
            //read every word from dictionary, make it lower case, and add to hashset
            while (input.hasNextLine()) {
                //read line at a time
                String line = input.nextLine();
                
                //make it lowercase
                line = line.toLowerCase();
                //add it to hashset
                dictionary.add(line);
            }
            
            
            
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
    /*To check for incorrectly spelled words, first read the file,
      convert the file to lowercase,  and process it into words,
      removing all punctuation in generating its list of words
      */
	public List<String> getIncorrectWords(String filename) {
        List<String> incorrectWord = new LinkedList<String>();
        try {
            File text = new File(filename);
            Scanner input = new Scanner(text);
            
            while (input.hasNextLine()) {
                //read line at a time
                String line = input.nextLine();
                
                //make it lowercase,strip it of punctuations
                
                //source: https://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                
                for(int i = 0; i < words.length; i++) {
                    if(!dictionary.contains(words[i])) {
                    incorrectWord.add(words[i]);
                    }
                }
            }
            } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        
        return incorrectWord;
    }
    /*
     * */
	public Set<String> getSuggestions(String word) {
        //System.out.println(word);
        //System.out.println("length of word " + word.length());
        //suggestword = word.substring(0, i+1) + alphabet;
        Set<String> suggestions = new HashSet<String>();
        
        //Add one character 
        for(int i = 0; i < word.length(); i++ ) {
            
            //iterate through a to z- recedig
            for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                String suggestword = "";
                suggestword = word.substring(0, i) + alphabet+ word.substring(i,word.length());
                
                //System.out.println(suggestword);
                if(dictionary.contains(suggestword)) {
                    suggestions.add(suggestword);
                    
                }
            }
        }
        //Add character to the end of the word
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                String suggestword = "";
                suggestword = word.substring(0, word.length()) + alphabet;
                
                //System.out.println(suggestword);
                if(dictionary.contains(suggestword)) {
                    suggestions.add(suggestword);
                    
             }
        }
        
        
        //remove one character at a time
        for(int i = 0; i < word.length(); i++ ) {
            String suggestword = word.substring(0,i)+ word.substring(i+1, word.length());
            
            if(dictionary.contains(suggestword)) {
                    suggestions.add(suggestword);
            }
        }
        
        //Swap adjacent characters
        
        for(int i = 0; i< word.length()-1; i++) {
            //if 1 away from the end of the word
            String suggestword = "";
            if(i == (word.length()-2)) {
                suggestword = word.substring(0,i) + word.charAt(i+1)+ word.charAt(i);
                
            }
            else {
                 suggestword = word.substring(0,i) + word.charAt(i+1)+ word.charAt(i)+ word.substring(i+2, word.length()); 
                
            }
            
            if(dictionary.contains(suggestword)) {
                    suggestions.add(suggestword);
            }
            
            
        }
        
        return suggestions;
    }
}