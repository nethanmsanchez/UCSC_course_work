//---------------------------
// Nethaniel Sanchez   HW2
// Negmsanc
// Chessboard.java
// A file containing main() that takes input from a file stores chesspieces,
// searches for the piece at a given position, and checks if it is under attack
// ---------------------------

import java.io.*;
import java.util.Scanner;
import java.lang.*;

class ChessBoard{
		
	// query col and query row
	public static int querycol;
	public static int queryrow;
	public static Llist blacklist;
	public static Llist whitelist;
	public static Chesspiece bquery = null;
	public static Chesspiece wquery = null;
	public static int[] cols;
	public static int[] rows;
	
	
   	public static void main(String[] args) throws IOException{		

   		int blacklistsize = 0;// may not ever be used
   		int whitelistsize = 0;
   		
      		// check number of command line arguments is at least 2  (from FileTokens)
      		if(args.length < 2){
         		System.out.println("Usage: java -jar FileTokens.jar <input file> <output file>");
         		System.exit(1);
      		}

      		// open files
      		Scanner in = new Scanner(new File(args[0]));
      		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      		// initialize variables
      		int counter = 0;
      		String num = null;
      		String num2 = null;
      		char num3 = 'z';
      		String num4 = null;
      		String num5 = null;
      		String num6 = null;
      		int colinput = -1;
      		int rowinput = -1;
      		char charinput = 'z';
      		int firstblack = 0;
      		int firstwhite = 0;
      		int invalidboard = 0;
      		int deleteme = 0;

      		// read lines from in, extract and print tokens from each line
     		while( in.hasNextInt() ){
     			// checking to see if this is the infinite loop
     			//if(deleteme>10) {
     			//	break;
     			//}
     			//deleteme++;
     			
     			//out.println("while(in.hasNextInt())");
     			counter=0;
     			invalidboard = 0;
     			firstblack = 0;
     			firstwhite = 0;
         		// trim leading and trailing spaces, then add one trailing space so 
         		// split works on blank lines
         		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
         		String[] token = line.split("\\s+");  

         		
         		num = token[0];
         		num2 = token[1];
         		num3 = num2.charAt(0);// may need to adjust
         		num2 = String.valueOf(num3);
         		//num3 = token[2];// throw away colon
         		num4 = token[1];
         		
         		querycol = Integer.parseInt(num);// was = Integer.parseInt(num);
         		queryrow = Integer.parseInt(num2);

         		
         		// throwing a null pointer exeption
         		boolean always = true;
         		while(always) {
         			//out.println("while(always)");
         			if(counter+2>=token.length) {
         				break;
         			}
         			
         			//out.println(num+" " + num2+" "+num4+" "+num5+" "+num6);
         			num4 = token[counter+2];
         			
         			num5 = token[counter+3];
         			
         			num6 = token[counter+4];
         			
         			
         			
         			charinput = num4.charAt(0);
         			colinput = Integer.parseInt(num5);
         			rowinput = Integer.parseInt(num6);
         		
         			//out.println(querycol+" "+queryrow+" "+charinput+" "+colinput+" "+rowinput);
         			
         			if(charinput=='Q') {
         				if(firstblack==0) {
         					Queen firstblackqueen = new Queen(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackqueen);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					Queen regblackqueen = new Queen(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackqueen);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='q') {
         				if(firstwhite==0) {
         					Queen firstwhitequeen = new Queen(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhitequeen);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					Queen regwhitequeen = new Queen(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhitequeen);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         			
         			if(charinput=='K') {
         				if(firstblack==0) {
         					King firstblackking = new King(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackking);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					King regblackking = new King(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackking);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='k') {
         				if(firstwhite==0) {
         					King firstwhiteking = new King(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhiteking);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					King regwhiteking = new King(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhiteking);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         		
         			if(charinput=='R') {
         				if(firstblack==0) {
         					Rook firstblackrook = new Rook(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackrook);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					Rook regblackrook = new Rook(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackrook);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='r') {
         				if(firstwhite==0) {
         					Rook firstwhiterook = new Rook(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhiterook);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					Rook regwhiterook = new Rook(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhiterook);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         		
         			if(charinput=='B') {
         				if(firstblack==0) {
         					Bishop firstblackbishop = new Bishop(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackbishop);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					Bishop regblackbishop = new Bishop(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackbishop);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='b') {
         				if(firstwhite==0) {
         					Bishop firstwhitebishop = new Bishop(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhitebishop);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					Bishop regwhitebishop = new Bishop(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhitebishop);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         		
         			if(charinput=='N') {
         				if(firstblack==0) {
         					Knight firstblackknight = new Knight(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackknight);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					Knight regblackknight = new Knight(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackknight);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='n') {
         				if(firstwhite==0) {
         					Knight firstwhiteknight = new Knight(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhiteknight);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					Knight regwhiteknight = new Knight(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhiteknight);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         			
         			if(charinput=='P') {
         				if(firstblack==0) {
         					Bpawn firstblackpawn = new Bpawn(colinput, rowinput, charinput);
         					Node firstblacknode = new Node(firstblackpawn);
         					blacklist = new Llist(firstblacknode);
         					firstblack = 1;
         				}else {
         					Bpawn regblackpawn = new Bpawn(colinput, rowinput, charinput);
         					Node regnode = new Node(regblackpawn);
         					blacklistsize = blacklist.addNode(regnode);
         				}
         			}
         		
         			if(charinput=='p') {
         				if(firstwhite==0) {
         					Wpawn firstwhitepawn = new Wpawn(colinput, rowinput, charinput);
         					Node firstwhitenode = new Node(firstwhitepawn);
         					whitelist = new Llist(firstwhitenode);
         					firstwhite = 1;
         				}else {
         					Wpawn regwhitepawn = new Wpawn(colinput, rowinput, charinput);
         					Node regwhitenode = new Node(regwhitepawn);
         					whitelistsize = whitelist.addNode(regwhitenode);
         				}
         			}
         			
         			//if(charinput!='K' && charinput!='k' && charinput!='Q' && charinput!='q' && charinput!='B' && charinput!='b' && charinput!='R' && charinput!='r' && charinput!='N' && charinput!='n') {
         				//out.println("Invalid");
         				//invalidboard = 1;
         				//break;
         			//}
         			counter = counter +3;
         		}// at this point the linked list should be created
         		
         		//int tester = 0;
         		
         		// checks for invalid board with my method
         		Llist combinedLlist = whitelist.combinelists(blacklist);
         		//combinedLlist.printlist(cols, rows);
         		//for(int j=0; j<30;j++) {
         		//	out.println(cols[j]+" "+rows[j]);
         		//}
         		if(combinedLlist.validcheck()) {
         			out.println("Invalid");
         			invalidboard = 1;
         		}
         		
         		if(invalidboard == 0) {
         			boolean battack = false;
         			bquery = null;
         			wquery = null;
         			char holder = 'z';
         		
         			//out.println();
         			//out.println(querycol+" "+queryrow);
         			//out.println("blacklistsize = "+blacklistsize+" whitelistsize = "+whitelistsize);
         			bquery = blacklist.searchList(querycol, queryrow);
         			wquery = whitelist.searchList(querycol, queryrow);
         		
         			
         			// if the query is not found in either list print out a "-"
         			if(bquery==null && wquery==null) {
         				out.println("-");
         			}
         		
         			if(bquery!=null) {
         				//battack = blacklist.checkAttack(bquery);
         				holder = bquery.type;
         				if(whitelist.checkattack(bquery)) {
         					//holder = bquery.type;
         					out.println(holder + " " + "y");
         				}else {
         					out.println(holder + " " + "n");
         				}
         			}
         			if(wquery!=null) {
         				holder = wquery.type;
         				if(blacklist.checkattack(wquery)) {
         					out.println(holder + " " + "y");
         				}else {
         				out.println(holder + " " + "n");
         				}
         			}
         		}
     		}

      		// close files
     		in.close();
      		out.close();
	}
   	
	
}

