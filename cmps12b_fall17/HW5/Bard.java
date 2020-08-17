//-----------------------------------------------------------------------------
// Nethaniel Sanchez  HW5
// Negmsanc
// Bard.java
// A program that finds the word based on word length, frequency, and
// alphabetical order from all of shakespearse work
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.*;
import java.lang.*;

public class Bard{
	

   	public static void main(String[] args) throws IOException{		
   		//Enumeration words;
   		boolean bandaid = false;
   		Integer num;
   		Integer num2;
   		Hashtable<Integer, ArrayList<Word>> hashtable = new Hashtable<Integer, ArrayList<Word>>();
   		
   		
      		// check number of command line arguments is at least 2  (from FileTokens)
      		if(args.length < 2){
         		System.out.println("Usage: java -jar FileTokens.jar <input file> <output file>");
         		System.exit(1);
      		}

      		// open files
      		Scanner in = new Scanner(new File(args[0]));
      		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
      		Scanner shakespeare = new Scanner(new File("shakespeare.txt"));
      		
      		while( shakespeare.hasNext()) {
      			String sline = shakespeare.nextLine().trim() + " ";
      			sline = sline.replace('?',' ');
      			sline = sline.replace(',',' ');
      			sline = sline.replace('.',' ');
      			sline = sline.replace('!',' ');
      			sline = sline.replace(';',' ');
      			sline = sline.replace(':',' ');
      			sline = sline.replace('[',' ');
      			sline = sline.replace(']',' ');
      			sline = sline.toLowerCase();
      			String[] stoken = sline.split("\\s+");
      			boolean found = false;
      			
      			for(int i = 0; i <stoken.length; i=i+1) {
      				//out.println(stoken[i]);
      				if(bandaid==false) {
      					String tstring = "the";
      					stoken[i] = tstring;
      					bandaid = true;
      				}
      				Word tempword = new Word(stoken[i]);
      				//out.println(tempword.name);
      				found = false;
      				if(hashtable.containsKey(stoken[i].length())) {
      					ArrayList<Word> x = hashtable.get(stoken[i].length());
      					for(int g = 0; g<x.size();g++) {
      						Word w = x.get(g);
      						//out.println("key: "+stoken[i].length()+ " tempword: "+tempword.name+" listword "+w.name);
      						if(w.name.equals(tempword.name)) {
      							//out.println(tempword.name+" has been repeated " +w.frequency+" times");
      							w.frequency++;
      							found = true;
      						}
      					}
      					if(found==false){
      						//out.println(tempword.name);
      						x.add(tempword);
      						hashtable.put(stoken[i].length(), x);
      					}
      				}else{// end of if(hashtable has key)
      					//out.println("The first word and length: "+tempword.name);
      					ArrayList<Word> y = new ArrayList<Word>();
      					y.add(tempword);
      					hashtable.put(stoken[i].length(), y);
      				}//end of else
      			}// end of for loop that goes through every stoken[i]
      		}// end of While(shakespeare.hasNext())
      		
      		//at this point the hash table is created with frequency of words
      		
      	// sorts by alphabetical order, then by frequency
      		Set<Integer> keys = hashtable.keySet();
      		Iterator<Integer> itr = keys.iterator();
      		while(itr.hasNext()) {
      			num = itr.next();
      			ArrayList<Word> templist = hashtable.get(num);
      			Collections.sort(templist, new Sortbyalph());
      		}
      		
      		Set<Integer> keys2 = hashtable.keySet();
      		Iterator<Integer> itr2 = keys.iterator();
      		while(itr2.hasNext()) {
      			num2 = itr2.next();
      			ArrayList<Word> templist2 = hashtable.get(num2);
      			Collections.sort(templist2, new Sortbyfreq());
      		}
      	// at this point the hashtable is built and sorted, ready for queries
      		/*ArrayList<Word> testlist = hashtable.get(3);
      		for(int i = 0; i<testlist.size();i++) {
      			Word testword = testlist.get(i);
      			out.println("word: "+testword.name+" rank "+i+" freq: "+testword.frequency);
      		}*/
      		

      		// initialize variables
      		//String num = null;
      		//String num2 = null;
      		String num3 = null;
      		String num4 = null;
      		Integer lenquery = null;
      		Integer rankquery = null;
      		int done =0;
      		boolean next = false;

      		// read lines from in, extract and print tokens from each line
     		while( in.hasNext() ){
     			next = false;
     			boolean jump = false;
     			boolean fly = false;
     			// trim leading and trailing spaces, then add one trailing space so 
         		// split works on blank lines
         		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
         		String[] token = line.split("\\s+");  
     			
         		
         		for(int k = 0; k < token.length - 1; k = k+2) {
         			num3 = token[k];
         			num4 = token[k+1];
         			lenquery = Integer.parseInt(num3);
         			rankquery = Integer.parseInt(num4);
         			//out.println("lenquery = "+lenquery);
         		}
         		if(lenquery==null) {
         			//out.println("lenquery is null????!!??");
         			next = true;
         		}
         		if(next==false) {
         			if(!(hashtable.containsKey(lenquery))){
         				out.println("-");
         				jump = true;
         			}
         			if(jump==false) {
         				ArrayList<Word> checklist = hashtable.get(lenquery);
         				if(rankquery>=checklist.size()) {
         					out.println("-");
         					fly = true;
         				}
         				if(fly==false) {
         					Word checkword = checklist.get(rankquery);
         					if(checkword!=null) {
         						out.println(checkword.name);
         					}else {
         						out.println("-");
         					}
         				}
         			}
         		}
         		
     		}// end of while(hasnextint)

      		// close files
      		in.close();
      		out.close();
      		shakespeare.close();
	}// end of main
}// end of class Bard

class Word{
	String name;
	int length;
	int frequency;
	//constructor
	public Word(String word) {
		name = word;
		length = word.length();
		frequency = 1;
	}
}

class Sortbyfreq implements Comparator<Word>{
	public int compare(Word a, Word b) {
		return b.frequency - a.frequency;
	}
}

class Sortbyalph implements Comparator<Word>{
	public int compare(Word a, Word b) {
		return a.name.compareTo(b.name);
	}
}



/*
class Word implements Comparator<Word>, Comparable<Word>{
	private String name;
	private int length;
	private int frequency;
	Word(String word){
		name = word;
		length = word.length();
		frequency = 1;
	}
	
	public int compareTo(Word w) {
		return name.compareTo(w.name);
	}
	
	public int compare(Word w, Word w1) {
		return w.age - w1.age;
	}
}
*/
