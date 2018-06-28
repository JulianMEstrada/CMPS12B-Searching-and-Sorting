import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// javac SearchAndSort.java && java SearchAndSort
public class SearchAndSort {
// Utility function: split a string into words, 
// making them all lowercase and ignoring all non-text characters
	public static String[] splitIntoWords(String str) {
	// Handle apostrophes: "lula's", "'top o' the mornin''"
	// Ignore any non-alphabetical characters ("1942", "1920s")
		str = str.toLowerCase();
		str = str.replaceAll("'","").replaceAll("\\s+", " ").replaceAll("[^a-zA-Z ]", " ");
	// Split on any amount of spaces
		String[] words = str.split("\\s+");
		System.out.print(words.length+"<<<<<");
		return words;
	}

// Load all of the words in this filename
	public static String[] createWordList(String filename) {
		try {
			String text = new String(Files.readAllBytes(Paths.get(filename)));
			return splitIntoWords(text);
		} catch (IOException ex){
			ex.printStackTrace();
		}
		return new String[0];
	}
/*
* ========================================================================
* START TODO #1: "countWordsInUnsorted"
*/
public static int [] countWordsInUnsorted(String [] allWords, String [] queryWords){ 
	int [] count = new int [queryWords.length]; 
	for (int j=0;j<queryWords.length; j++){
		count[j] = 0;
		for (int i = 0;i<allWords.length;i++ ) {
			if ((queryWords[j]).equals(allWords[i])) {
				count[j]++;
			}
		}
		
	}
	return count;
	
}
/*
* END TODO #1: "countWordsInUnsorted"
* ========================================================================
*/
/*
* ========================================================================
* START TODO #2: "mergeSort"
Helpful Souece used: Data Abstraction and Problem Solving with JAVA, Pg. 527-533:  <Janet J Prichard, Frank M. Carrano> (<April 11, 2018>) <Sorting> (<Java>) [<Third Ed>]. Pearson.
	int Sindex = 0;
	System.out.println(Sindex+"<<<<<<<<<<<<<<<<!!!!!!!!!");
*/
	public static <sort extends Comparable <? super sort>> 
void mergeSort(sort [] arrayToSort, String[] tempArray, int first, int lastIndex){
	for(;lastIndex>=1;lastIndex--){
		      int Zindex = largestWord(arrayToSort,lastIndex);
	            sort mergeSort = arrayToSort[Zindex];
				arrayToSort[Zindex]=arrayToSort[lastIndex];
			    arrayToSort[lastIndex]=mergeSort;
	}
}
 // Find the largest value in arrayToSort:
public static <sort extends Comparable <? super sort>>  
 int largestWord(sort [] arrayToSort, int lastIndex){
 	int index = 0; 
	        for(int j=1; j<lastIndex+1; j++ ){
			if(arrayToSort[j].compareTo(arrayToSort[index])>0){
				index = j; 
			}
}
return index;
}
/*
* END TODO #2: "mergeSort"
* ========================================================================
*/
/*
System.out.println(query + " pivot:" + sortedWords[0] + " " + startIndex + "-"
+ endIndex + " " + sortedWords[startIndex] + "-" + sortedWords[endIndex-1]);
* ========================================================================
* START TODO #3: binary search
*/
public static int binarySearch(String[] sortedWords, String query, int startIndex, int endIndex){

	int mid =startIndex+((endIndex-startIndex)/2); // midPoint  *Syntax prevents stackoverflow
	//System.out.println(mid+"This is the midPoint as it changes");
	int smallestValue = 0; 
	int largestValue = 0;
	int allCount = 0;
     // Make sure word IS in the sorted array 
 if (startIndex>endIndex) {
 return 0; 
	}
	// Existing query word is greater than sortedWords[middle] 
	if(query.compareTo(sortedWords[mid])>0){ 
		return binarySearch(sortedWords,query,mid+1, endIndex);
	}
	// else if word is less than sortedWords[middle]
	else if(query.compareTo(sortedWords[mid])<0){ 
		return binarySearch(sortedWords,query,startIndex,mid-1);
	}
	  if(query.equals(sortedWords[mid]) && query.equals(sortedWords[mid-1]) || query.equals(sortedWords[mid])  ){ 
		// run get smallest
		smallestValue = getSmallestIndex(sortedWords,query,startIndex,mid);
	}
	 if(query.equals(sortedWords[mid]) && query.equals(sortedWords[mid+1]) || query.equals(sortedWords[mid]) ){ 
		// run get largest
		largestValue = getLargestIndex(sortedWords,query,mid,endIndex);
	}
	else if(query.equals(sortedWords[mid])&&!query.equals(sortedWords[mid+1])&&!query.equals(sortedWords[mid-1])){ 
		//One of one word in the middle case
		return 1; 
	}
	if (largestValue==smallestValue){
		return 1; 
	}

 allCount = (largestValue-smallestValue)+1; // plus one for lost index of 0 rather than length.
	// System.out.println(allCount+" This is the amount of times query appears*");
   return allCount; 
}

public static int getSmallestIndex(String[] sortedWords,String query, int startIndex, int endIndex){
 int smallestValue = 0; 
	if(query.equals(sortedWords[endIndex-1])){
        return getSmallestIndex(sortedWords,query,startIndex,endIndex-1);
	}
	else if(query.equals(sortedWords[endIndex])&&!query.equals(sortedWords[endIndex-1])){
            smallestValue = endIndex; 
	}
	// System.out.println(endIndex+" This is the SMALLEST Index of query*");
	return smallestValue;
}

public static int getLargestIndex(String[] sortedWords, String query, int startIndex, int endIndex){
	int largestValue = 0; 
	if(query.equals(sortedWords[startIndex+1])){
        return getLargestIndex(sortedWords,query,startIndex+1,endIndex);
	}
	else if(query.equals(sortedWords[startIndex])&&!query.equals(sortedWords[startIndex+1])){
            largestValue = startIndex; 
	}
	// System.out.println(startIndex+" This is the LARGEST Index of query*");
	return largestValue;
}



/*
* END TODO #3: binary search
* ========================================================================
*/
public static void main(String []args) {
	
	// Create a word list from Frankenstein
	String[] allWords = createWordList("frankenstein.txt");

	// Save the arguments

	String[] queryWords = {"doctor", "frankenstein", "the", "monster", "igor", "student", "college", "lightning", "electricity", "blood", "soul"};
	int timingCount = 100;

	if (args.length > 0) {
		// There is an argument, so some different words to search for and count were passed in.
		queryWords = args[0].split(",");
	}			

	
	System.out.println("\nSEARCH AND SORT");
	System.out.println("\nSearching and counting the words " + String.join(",", queryWords));		
	
	System.out.println("\nNAIVE SEARCH:");

	
	// Record the current time
	long t0 = (new Date()).getTime();

	// Time how long it takes to run timingCount loops
	//   for countWordsInUnsorted 
	for (int j = 0; j < timingCount; j++) { 
		// Search for and count the words timingCount times in order to get an average time

		for (int i = 0; i < queryWords.length; i++) {
			// 

			/*
			* ========================================================================
			* 	START: TODO #1
			*/

			int [] count = countWordsInUnsorted(allWords,queryWords); // Replace the 0 in this line of code with the call to countWordsInUnsorted once you've written it
			/*
			* 	END: TODO #1
			* ========================================================================
			*/

			// For the first time the words are counted, print out the value
			if (j == 0)
				System.out.println(queryWords[i] + ":" + count[i]);
			
		}
	}

	// Record the current time
	long t1 = (new Date()).getTime();

	long timeToSeachNaive = t1 - t0;
	int searchCount = timingCount*queryWords.length;

	// Output how long the searches took, for how many searches 
	// (remember: searches = timingcount * the number of words searched)
	System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachNaive, searchCount, timeToSeachNaive*1.0f/searchCount);

	// Sort the list of words
	System.out.println("\nSORTING: ");

	/*
	* ========================================================================
	* 	START: TODO #2
	*/
	String tempArray [] = new String[allWords.length]; 
	int startIndex = 0; 
	int lastIndex = allWords.length-1; 
	mergeSort(allWords, tempArray, startIndex, lastIndex); 
	
	/*
	* 	END: TODO #2
	* ========================================================================
	*/

	// Record the current time
	long t2 = (new Date()).getTime();

	// Output how long the sorting took
	long timeToSort = t2 - t1;
	System.out.printf("%d ms to sort %d words\n", timeToSort, allWords.length);

	// Output every 1000th word of your sorted wordlist
	int step = (int)(allWords.length*.00663 + 1);
	System.out.print("\nSORTED (every " + step + " word): ");
	for (int i = 0; i < allWords.length; i++) {
		if (i%step == 0)
			System.out.print(allWords[i] + " ");
	}
	System.out.println("\n");


	System.out.println("BINARY SEARCH:");

	
	
        
	// Run timingCount loops for countWordsInSorted 
	// for the first loop, output the count for each word

	for (int j = 0; j < timingCount; j++) {
		for (int i = 0; i < queryWords.length; i++) {
			/*
			* ========================================================================
			* 	START: TODO #3
			*/ 
		
         int count = binarySearch(allWords,queryWords[i],startIndex,lastIndex); 
        
			/*
			* 	END: TODO #3
			* ========================================================================
			*/

			// For the first one, print out the value
			if (j == 0)
				System.out.println(queryWords[i] + ":" + count);
		    count = binarySearch(allWords,queryWords[i],startIndex,lastIndex); 
		}
	}

	// Output how long the searches took, for how many searches 
	// (remember: searchCount = timingcount * the number of words searched. This is computed above.)

	// Record the current time
	long t3 = (new Date()).getTime();

	long timeToSeachBinary = t3 - t2;
	System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachBinary, searchCount, timeToSeachBinary*1.0f/searchCount);


}



}