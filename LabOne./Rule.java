
import java.io.*;
import java.util.*;
import java.util.Random;

/*
* A rule is an object with alternating plaintext and symbols. This class is used to store the expansion on the righthand
* side of a rule. The starting symbol is also represented using a Rule object. The main thing you do with a Rule object 
* is expand any symbols that appear in it looking up the rule indexed by the symbol. 
*/

public class Rule {
private static Random random;

// The raw expansion string. It can include a mixture of text and symbols (hashtagged text)
private String raw; 

// Array with text and symbols as separate entries
private String[] sections; 

public static void setSeed(long seed) {
  System.out.println("Set seed " + seed);
  random = new Random(seed); // Create a new random number generator with the specified seed
}

public String expand(Hashtable<String, Rule[]> grammar) {
  /*
  * START: TO DO #3
  *Objective Notes:
  * Create an array of strings named results that is the same size as the rules section (sections.length).
  * Create a for loop that iterates down the section array.
  * For each section, if it is EVEN (i%2==0), copy the text from the rule section into the results array. 
  * If its ODD then this is a symbol that we need to expand. Use the grammar to find the array of expansions for this symbol.
  * Get a random int with random.nextInt (int bound). 
  * Pick out the expansion and save it as the variable rule selectedExpansion. 
  * Store the value in our results array, but process it first. We can do this by calling a meathod on selected Expansion.
  * Now that we have rules use String.join("", results); to join everything together and now return it. 
  */
  
   String [] results = new String [sections.length]; 
   
   for(int i=0; i<sections.length; i++){

    if(i%2==0){
       results[i] = sections[i]; 
       
    }
    else {
       Rule [] selectedExpansion = grammar.get(sections[i]);
        int index = random.nextInt(selectedExpansion.length); 
         Rule selec = selectedExpansion[index]; 
          results[i] = selec.expand(grammar);
          // grammar.get(sections[index]);
            



    }



   }

  return String.join("", results); // reutn 
  /*
  * END: TO DO #3
  */
}

public String toString() {
  return raw;
}

Rule(String raw) {
  this.raw = raw;
  sections = raw.split("#");
}
}
