import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

		static WordDictionary dictionary = new WordDictionary(); // txt file of words find in file

	public static String[] getWords(String text, int index) {// Get all words that can be formed starting at this index
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	} // end getWords

public static String stackToReverseString(MyStack stack) { // revese stack and turn into string
	MyStack tempStack = new MyStack();
	while (!stack.isEmpty()) {
		tempStack.push(stack.pop());
	}

        String rString = tempStack.toString(); // keep 

        while (!tempStack.isEmpty()) {
        	stack.push(tempStack.pop());
        }

        return rString;
    } // end of reverseStack

    public static String reverseStringAndRemoveNonAlpha(String text) {// reverse string and remove anything that is not char
    	MyStack tempStack = new MyStack();
    	for (int i = 0; i < text.length(); i++){
    		char c = text.charAt(i); 
    		if(Character.isAlphabetic(c)==true){ 
    			tempStack.push(c);
    		} 
    	}

    	return tempStack.toString();
} // end of reverseString 

	public static boolean isPalindrome(String text) { // checks if word is a palindrome, use test in comand line
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
		text.toLowerCase();
		MyQueue newQueue = new MyQueue();
		MyStack newStack = new MyStack();
		String q = "";
		String s = "";
		for (int i = 0; i < text.length(); i++){
			char c = text.charAt(i); 
			if(Character.isAlphabetic(c)==true){
				newQueue.enqueue(c);
				newStack.push(c);
			} 
		}
		s =stackToReverseString(newStack).toLowerCase(); 
		q = ""+newQueue;
		q = reverseStringAndRemoveNonAlpha(q).toLowerCase();
		if(s.equals(q)){ 
	return true; // is palindrome
}
else{

		return false; // is not palindrome 
}
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	} // end of isPalindrome 
public static void explorePalindrome(String text) { //reverses given text and is precursor to following recrusive meathod
	String textToDecompose = reverseStringAndRemoveNonAlpha(text).toLowerCase();
    	//System.out.println(textToDecompose+" <<<<<<");
	int index = 0; 
	MyStack builtUpWords = new MyStack();
	decomposeText(text, textToDecompose, index, builtUpWords);
	}// explorePalindrome
public static void decomposeText(String originalText, String textToDecompose,int index, MyStack builtUpWords){// recursive meathod that finds words within the palindrome
		   if(index == textToDecompose.length()){ // base case 
		  System.out.println(originalText+": "+ stackToReverseString(builtUpWords)); // finally print when words found, stack needs to be reversed
		} 
		else if (index < textToDecompose.length()){
			for (String word : getWords(textToDecompose,index)){ // loop given groupWrods at given index 
				builtUpWords.push(word +" ");
				decomposeText(originalText, textToDecompose,index+word.length(),builtUpWords);
            builtUpWords.pop(); // must pop, do not remove 
        }
    }
 }// end decompose text
 /* END
	* TODO 6
	*/
	// DO NOT EDIT BELOW 
	// This function looks at the arguments that are passed 
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}	
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}