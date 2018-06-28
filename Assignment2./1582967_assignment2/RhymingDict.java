import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
public class RhymingDict { 	
	// Given a pronunciation, get the rhyme group
	// get the more *heavily emphasized vowel* and follwing syllables
	// For "tomato", this is "-ato", and not "-omato", or "-o"
	// Tomato shares a rhyming group with "potato", but not "grow"
	private static String getRhymeGroup(String line) {

		int firstSpace = line.indexOf(" "); 

		String pronunciation = line.substring(firstSpace + 1, line.length());

		int stress0 = pronunciation.indexOf("0");
		int stress1 = pronunciation.indexOf("1");
		int stress2 = pronunciation.indexOf("2");

		if (stress2 >= 0)
			return pronunciation.substring(stress2 - 2, pronunciation.length());
		if (stress1 >= 0)
			return pronunciation.substring(stress1 - 2, pronunciation.length());
		if (stress0 >= 0)
			return pronunciation.substring(stress0 - 2, pronunciation.length());
		
		// No vowels at all? ("hmmm", "mmm", "shh")
		return pronunciation;
	}

	private static String getWord(String line) {
		int firstSpace = line.indexOf(" ");

		String word = line.substring(0, firstSpace);

		return word; 
	}

	// Load the dictionary
	private static String[] loadDictionary() {
		// Load the file and read it

		String[] lines = null; // Array we'll return holding all the lines of the dictionary
		
		try {
			String path = "cmudict/cmudict-short.dict";
			// Creating an array of strings, one for each line in the file
			lines = new String(Files.readAllBytes(Paths.get(path))).split("\\r?\\n");
			
		}
		catch (IOException ex){
			ex.printStackTrace();
		}

		return lines; 
	}
	public static void main(String []inputWords) {


		/* This code is in here to help you test MyLinkedList without having to mess around with the dictionary. 
		   Feel free to change this test code as you're testing your linked list. But be sure to comment this code
		   out when you submit it. */


		/* TODO: Add in your code to load the dictionary into your linked lists. Remember that rhymeGroups is a 
		   list of RhymeGroupWords. Inside each of this objects is another linked list which is a list of words within the same
		   rhyme group. I would recommend first getting this working with MyLinkedList for both lists (rhyme groups and 
		   word lists) then get it working using MySortedLinkedList for the word groups. */
        ListInterface rhymeGroups = new MyLinkedList(); 
		 ListInterface wordList = new MySortedLinkedList();
		  String[] dictionaryLines = loadDictionary();

         RhymeGroupWords sameWord = new RhymeGroupWords(getRhymeGroup(dictionaryLines[0]), wordList);
         rhymeGroups.add(0, sameWord);
    
		for(int i = 0; i < dictionaryLines.length; i++) {
            int j;
            for(j = 0; j < rhymeGroups.size(); j++) {
                sameWord = (RhymeGroupWords) rhymeGroups.get(j);
                if(getRhymeGroup(dictionaryLines[i]).equals(sameWord.getRhymeGroup())) {
                    ((MySortedLinkedList) sameWord.getWordList()).add(getWord(dictionaryLines[i]));
                    break;
                }
            }
            if (j == rhymeGroups.size()){
                    wordList = new MySortedLinkedList();
                    sameWord = new RhymeGroupWords (getRhymeGroup(dictionaryLines[i]), wordList);
                   ((MySortedLinkedList) sameWord.getWordList()).add(getWord(dictionaryLines[i]));
                    rhymeGroups.add(0, sameWord);
            }
        } 
      //  System.out.println(rhymeGroups.size()+"<<<<<<<<<<<<<<");

           /* End TODO for adding dictionary in rhymeGroups.*/
		// This code prints out the rhyme groups that have been loaded above. 
            for(int i =0; i < rhymeGroups.size(); i++) {
            	RhymeGroupWords rg = (RhymeGroupWords) rhymeGroups.get(i);
            	System.out.print(rg.getRhymeGroup() + ":");
            	System.out.println(rg.getWordList());
            } 
 
/*TODO: Add the code here to iterate through pairs of arguments, testing to see if they are in the same rhyme group or not.

java RhymingDict crumbling mumbling collections abbreviated vegetate mutate

crumbling and mumbling rhyme
collections and abbreviated don't rhyme
mutate is not in the dictionary
*/
boolean PairOne = false;
        boolean PairTwo = false;
        for(int i = 0; i < inputWords.length; i = i + 2) {
            PairOne = false;
            PairTwo = false;
            int j;
            for(j = 0; j < rhymeGroups.size(); j++) {
                RhymeGroupWords ArgumentsGroups = (RhymeGroupWords) rhymeGroups.get(j);
                // Iterates through wordLists
                for(int x = 0; x < ArgumentsGroups.getWordList().size(); x++) {
                    if(inputWords[i].equals(((String) ArgumentsGroups.getWordList().get(x)))) {
                        PairOne = true;
                    } else if (inputWords[i+1].equals(((String) ArgumentsGroups.getWordList().get(x)))) {
                        PairTwo = true;
                    }
                } if(PairOne == true && PairTwo == true) {
                    System.out.println(inputWords[i] + " & " + inputWords[i+1] + " rhyme.");
                    break;
                }
            } if(j == rhymeGroups.size() && PairOne == false || PairTwo == false) {
                System.out.println(inputWords[i] + " & " + inputWords[i+1] + " don't rhyme.");
            }else if(PairOne == false) {
                System.out.println(inputWords[i] + " found");
            } else if(PairTwo == false) {
                System.out.println(inputWords[i+1] + " found");
            }
        }
    
        }
    }
