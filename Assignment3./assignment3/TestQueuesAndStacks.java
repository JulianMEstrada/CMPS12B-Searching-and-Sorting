import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class TestQueuesAndStacks {


	static WordDictionary dictionary = new WordDictionary(); 

	
	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}


public static String stackToReverseString(MyStack stack) {
	MyStack tempStack = new MyStack();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        String finals = tempStack.toString();

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return finals;
    }
    public static String reverseStringAndRemoveNonAlpha(String text) {
  MyStack tempStack = new MyStack();
  for (int i = 0; i < text.length(); i++){
    char c = text.charAt(i); 
     if(Character.isAlphabetic(c)==true){
      tempStack.push(c);
    } 
  }
  
  return tempStack.toString();
}

	public static boolean isPalindrome(String text) {
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
    s =stackToReverseString(newStack); 
    q = ""+newQueue;

if(s.equals(q)){
	return true;
}


		return false;
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	}
public static void explorePalindrome(String text) {
    	String textToDecompose = reverseStringAndRemoveNonAlpha(text).toLowerCase();
    	System.out.println(textToDecompose+" <<<<<<");
    	int index = 0; 
    	MyStack builtUpWords = new MyStack();
    	decomposeText(text, textToDecompose, index, builtUpWords);
	}
public static void decomposeText(String originalText, String textToDecompose,int index, MyStack builtUpWords){
		   if(index == textToDecompose.length()){
		  System.out.println(originalText+":"+ stackToReverseString(builtUpWords));
		} 

		else if (index < textToDecompose.length()){
			for (String word : getWords(textToDecompose,index)){
			builtUpWords.push(word +" ");
            decomposeText(originalText, textToDecompose,index+word.length(),builtUpWords);
            builtUpWords.pop(); 
			}

		}

 
 

 }

  
 /* END
	* TODO 6
	*/
	// Test Queues
	public static void testQueue() {
		System.out.println("Test queues");
		MyQueue test = new MyQueue();
		test.enqueue("a");
		test.enqueue("b");
		System.out.println(test);
		test.enqueue("c");
		test.enqueue("d");
		test.enqueue("e");
		test.enqueue("f");
		System.out.println(test);
		String s = (String)test.dequeue();
		System.out.println("dequeued " + s + ": " + test);
		test.enqueue("x");
		test.enqueue("y");
		test.enqueue("z");
		System.out.println(test);
		try {
			for (int i = 0; i < 10; i++) {
				s = (String)test.dequeue();
				System.out.println("dequeued " + s + ": " + test);
			}
		} 
		catch(QueueException ex) {
			System.out.println("EXCEPTION: " + ex);
		}
		test.enqueue("j");
		test.enqueue("k");
		test.enqueue("l");
		System.out.println("Final value: " + test);
	}

	// Test Stacks
	public static void testStack() {

		System.out.println("Test stacks");
		MyStack test = new MyStack();
		test.push(" apple"); 
		test.push(" bees"); 
        test.push(" cat"); 
        test.push(" dog"); 
		test.push(" effect"); 
        test.push(" fam"); 
        System.out.println(stackToReverseString(test));
         System.out.println(reverseStringAndRemoveNonAlpha("T e stt-"));
        System.out.println(isPalindrome("A car, a man, a maraca."));
        
		/*
		test.push("a");
		test.push("b");
		System.out.println(test);
		test.push("c");
		test.push("d");
		test.push("e");
		test.push("f");
		System.out.println(test);
		String s = (String)test.pop();
		System.out.println("popped " + s + ": " + test);
		test.push("x");
		test.push("y");
		test.push("z");
		System.out.println(test);
		try{
			for (int i = 0; i < 10; i++) {
				s = (String)test.pop();
				System.out.println("popped " + s + ": " + test);
			}
		}
		catch(StackException ex) {
			System.out.println("EXCEPTION: " + ex);
		}
		test.push("j");
		test.push("k");
		test.push("l");
		System.out.println("Final value: " + test); */

		
	}
	public static void main(String[] args) {

		testQueue();
		testStack();
		explorePalindrome("an era live");

	}
}
