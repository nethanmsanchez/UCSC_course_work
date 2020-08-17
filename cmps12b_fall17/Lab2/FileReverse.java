//-----------------------------------------------------------------------------
// Nethaniel Sanchez   Lab2
// negmsanc
// FileReverse.java
// Takes a file as input and prints each word backwords on it's own line.
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class FileReverse{
   	public static void main(String[] args) throws IOException{

      		int lineNumber = 0;

      		// check number of command line arguments is at least 2
      		if(args.length < 2){
         		System.out.println("Usage: java –jar FileReverse.jar <input file> <output file>");
         		System.exit(1);
      		}

      		// open files
     		Scanner in = new Scanner(new File(args[0]));
     		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      		// read lines from in, extract and print tokens from each line
      		while( in.hasNextLine() ){
         		lineNumber++;

         	       	// trim leading and trailing spaces, then add one trailing space so 
        		// split works on blank lines
        		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
        		String[] token = line.split("\\s+");  

         		// print out tokens       
         		int n = token.length;
         		//out.println("Line " + lineNumber + " contains " + n + " tokens:");
         		for(int i=0; i<n; i++){
            			out.println(""+ stringReverse(token[i]));
         		}
      		}

      		// close files
     		in.close();
     		out.close();
   	}
	
	// My function that reverses a string
	public static String stringReverse(String s){
		String reversedstring = "";
		int stringlen = s.length();
		char holder;
		while(stringlen>0){
			holder = s.charAt(stringlen - 1);
			String tempstring = String.valueOf(holder);
			reversedstring = reversedstring.concat(tempstring);
			stringlen = stringlen - 1;
		}
		return reversedstring;
	}
}
