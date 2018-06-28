/* 
* START: TO DO: Import the packages you need to support your I/O operations.
*/
import java.io.*;
import java.util.*;
import​ java.util.Hashtable;
import java.io.PrintStream; 
import java.io.InputStream;
/*
* END: TO DO: Import the packages you need to support your I/O operations.
*/

public class TraceryRecursion {

	/*
	* START: TO DO: outputGrammar(Hashtable<String, Rule> grammar, PrintStream ps)
	* Change the code so everything that is currently output to the console using System.out.println is now output to the PrintStream
	* using the PrintStream.println() method.  
	*/
	public static void outputGrammar(Hashtable<String, Rule[]> grammar, PrintStream ps) {
		PrintStream newPS = new PrintStream(System.out);
		newPS.println("\nGRAMMAR:");
		for ( String key : grammar.keySet() ) {
			String line = "";
			line += key + ": " + String.format("%1$" + (20 - key.length()) + "s", " ");
			for (Rule rule : grammar.get(key)) {
				line += "\"" + rule + "\"," ;
			}
			newPS.println(line);
		}
	}
	/*
	* END: TO DO: changing outputGrammar to use a PrintStream
	*/


	// Given an InputStream, load the grammar at that InputStream
	public static Hashtable<String, Rule[]> loadGrammar(InputStream inStream)throws IOException {

		Hashtable<String, Rule[]> grammar = new Hashtable<String, Rule[]>();
            
		// TO DO: create a new BufferedReader based on inStream that you'll use to read the stream line by line (using readLine())
         InputStreamReader input = new InputStreamReader(inStream);
         BufferedReader iputS = new BufferedReader(input);
         while(iputS.ready()){
         String line = iputS.readLine();
          String[] ruleString = line.split(":");
			String[] expansions = ruleString[1].split(",");
			Rule[] rules = new Rule[expansions.length];
			for(int i=0; i < expansions.length; i++) {
				rules[i] = new Rule(expansions[i]);
			}
			grammar.put(ruleString[0], rules);
		}
         
         
       

		/* 
		* START: TO DO: Make a loop that reads a new line from the BufferedReader line by line and adds it to the grammar
		*/

           

			/* 
			* Put your code that takes each line and adds it to the grammar inside the loop. Below is the code from our solution for doing this,
			* but feel free to substitute this with the code from your own assignment. 
			*/ 
			/* String[] ruleString = line.split(":");
			String[] expansions = ruleString[1].split(",");
			Rule[] rules = new Rule[expansions.length];
			for(int i=0; i < expansions.length; i++) {
				rules[i] = new Rule(expansions[i]);
			}
			grammar.put(ruleString[0], rules); */

		/* 
		* END: TO DO: Make a loop that reads a new line from the BufferedReader line by line and processes it.
		*/ 

		return grammar;
	}


	/*
	* START: TO DO: public static InputStream getInputStream(String[] args)

    FileReader inFile = new FileReader("grammar-emojistory.txt");
	FileReader inStream = new FileReader(inFile);
	BufferedReader input = new BufferedReader(new FileReader("grammar-emojistory.txt"));
	*/
	public static InputStream getInputStream(String[] args){
//System.out.println("My file is: "+String.join("|",args) + " len:" + args.length);
		try {
        for(int i =0; i<args.length; i++){
		if((args[i]).equals("-in")){ // make sure "- in exist"
		//System.out.println("Test "+" " +i+" "+ args[i]);
    int index = i; // find the index of "-in"
    FileInputStream inStream  = new FileInputStream(args[index + 1]);
    return inStream; 
    }
}  
}
	catch(IOException e){
	System.out.print("Input file does not exist!"); 
}
return  System.in;

  }
	/* 
	* END: TO DO: public static InputStream getInputStream(String[] args)
	*/


	/*
	* START: TO DO: public static PrintStream getOutputStream(String[] args)
	*/
public static PrintStream getOutputStream(String[] args){
	try {
		for(int i =0; i<args.length; i++){
    if((args[i]).equals("-out")){  // make sure "- out exist"
    int index = i; // find the index of "-out"
    PrintStream PrintS = new PrintStream(args[index+1]); 
    return PrintS; 
}
}
}
	catch(IOException e){
	System.out.print("Output file does not exist!"); 
	
}
return  System.out; 

}
	/* 
	* END: TO DO: public static PrintStream getOutputStream(String[] args)
	*/


	public static void main(String[] args)throws IOException{
		//PrintStream newPS = new PrintStream(System.out);
		System.out.println("Running TraceryRecursion...");
	//	System.out.println(args.length);

		//String argString = String.join(" ", args);
		//System.out.println("string version: " + argString);
		//String[] args2 = argString.split(" ");
		//System.out.println("array version: " + String.join(",", args2));

		String startSymbol = "#origin#"; 

		int count = 10; 
		long seed = 1; 
		/*
		* START: TO DO: call getInputStream(args) and getOutputStream(args) to get the InputStream and PrintStream to use
		*/
	 InputStream input  = getInputStream(args);
	 PrintStream output = getOutputStream(args);
		/*
		* END: TO DO: call getInputStream(args) and getOutputStream(args) to get the InputStream and PrintStream to use
		*/

		Rule.setSeed(seed); // Set the seed using a static method defined on Rule

		// To DO: comment this line back in to load the grammar into the Hashtable once you've set the inputStream you're using
		 Hashtable<String, Rule[]> grammar = loadGrammar(input); 

		// TO DO: comment this line back in to print the loaded grammar. You'll need to set outStream correctly
		 outputGrammar(grammar, output); 

		Rule rule = new Rule(startSymbol); // Create a new Rule object using the startSymbol
		// Expand the start symbol until there are no more symbols to expand. Do this 'count' number of times.
		for (int i = 0; i < count; i++) { 
			// TO DO: Change the line below so it prints to the correct PrintStream instead of always System.out
			 output.println(rule.expand(grammar));
		}
	}
}
