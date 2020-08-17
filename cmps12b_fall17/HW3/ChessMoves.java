//---------------------------
// Nethaniel Sanchez   HW3
// Negmsanc
// ChessMoves.java
// A file containing main() that takes input from a file stores chesspieces,
// and then makes moves if they are legal
// ---------------------------

import java.io.*;
import java.util.Scanner;
import java.lang.*;

class ChessMoves{
		
	public static Llist blacklist;
	public static Llist whitelist;
	public static int wtf;
	 
   	public static void main(String[] args) throws IOException{		
   		
   		//out.println("hello");
   		//variables for first loop that creates chesspieces
   		String num = null;
   		String num2 = null;
   		String num3 = null;
   		int counter = 0;
   		int firstblack = 0;
  		int firstwhite = 0;
  		int colinput = -1;
  		int rowinput = -1;
  		char charinput = 'z';
  		char holder;
  		boolean always = true;
  		//-----------------------------
  		String num4 = null;
  		String num5 = null;
  		String num6 = null;
  		String num7 = null;
  		int descol;
  		int desrow;
  		int oricol;
  		int orirow;
  		Chesspiece piece;
  		Llist combinedList = null;
  		int currcolor = -1;
  		int prevcolor = -1;
  		Chesspiece capturedpiece;
  		int capturedcolor = -1;
  		int blacklistsize = -1;
  		int whitelistsize = -1;
  		boolean nopiece = false;
  		boolean notalt = false;
		boolean nogo = false;
		boolean sameteam = false;
		boolean roadblock = false;
		boolean incheck = false;
		boolean finished = false;
		boolean done = false;
		boolean blackfirst = false;
  		
      		// check number of command line arguments is at least 2  (from FileTokens)
      		if(args.length < 2){
         		System.out.println("Usage: java -jar FileTokens.jar <input file> <output file>");
         		System.exit(1);
      		}

      		// open files
      		Scanner in = new Scanner(new File(args[0]));
      		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      		// read lines from in, extract and print tokens from each line
     		while( in.hasNext() ){
     			firstblack = 0;
     			firstwhite = 0;
     			counter = 0;
     			
         		// trim leading and trailing spaces, then add one trailing space so 
         		// split works on blank lines
         		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
         		String[] token = line.split("\\s+");  

         		// This while grabs all the input before the colon and creates the
         		// Linked list of chesspieces
         		int zzz = 0;
         		for(int i = 0; i <token.length; i=i+3) {
         			//------------------------------
         			//test
         			//if(counter+1>=token.length || counter>=token.length || counter+2>=token.length) {
         			//	out.println("wtf");
         			//	break;
         			//}
         			
         			//------------------------------
         			num = token[counter];
         			num2 = token[counter+1];		
         			num3 = token[counter+2];
         			if(num3.length() > 1) {
         				finished = true;
         				charinput = num.charAt(0);
         				colinput = Integer.parseInt(num2);
         				holder = num3.charAt(0);
         				rowinput = Character.getNumericValue(holder);
         				//out.println("last chesspiece vals: " + charinput + " " + colinput + " " + rowinput);
         			}else {
         			charinput = num.charAt(0);
         			colinput = Integer.parseInt(num2);
         			rowinput = Integer.parseInt(num3);
         			}
         			
         			//out.println(charinput + " " + colinput + " " + rowinput);
         			
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
         					//out.println("black king added");
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
         					//out.println("white king added");
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
         			//counter = counter +3;
         			if(finished==true) {
         				break;
         			}
         			counter = counter + 3;
         		}// end of the while loop that stores all the chesspieces
         		
         		finished = false;
         		counter = counter +3;
         		
         		int blsize = blacklist.getSize();
         		int wlsize = whitelist.getSize();
         		//out.println(blacklistsize + " = " + blsize + " " + whitelistsize + " = " + wlsize);
         		
         		
         		currcolor = -100;
         		prevcolor = -200;
         		boolean firstmove = true;
         		
         		while(done==false) {
         			nopiece = false;
         	  		notalt = false;
         			nogo = false;
         			sameteam = false;
         			roadblock = false;
         			incheck = false;
         			blackfirst = false;
         		
         			
         			
         			//if(counter>=token.length || counter+1>=token.length || counter+2>=token.length || counter+3>=token.length) {
         			//	out.println("legal");
         			//	break;
         			//}
         			if(counter>=token.length) {
         				out.println("legal");
         				break;
         			}
         			
         			num4 = token[counter];
         			num5 = token[counter+1];
         			num6 = token[counter+2];
         			num7 = token[counter+3];
         			oricol = Integer.parseInt(num4);
         			orirow = Integer.parseInt(num5);
         			descol = Integer.parseInt(num6);
         			desrow = Integer.parseInt(num7);
         			//out.println(blacklistsize+whitelistsize);
         			if(blacklist==null) {
         				if(whitelist==null) {
         					out.println("whitelist is null");
         					break;   
         				}
         				out.println("black list is null");
         				break;
         			}
         			
         				combinedList = blacklist.combinelists(whitelist);
         				Node curr = combinedList.head;
         				//while(curr!=null) {
         					//out.println("type:"+curr.item.type+" col:"+curr.item.col+" row:"+curr.item.row);
         					//curr = curr.next;
         				//}
         				int clsize = combinedList.getSize();
         				//out.println("combinedList size = "+clsize);
         				piece = combinedList.searchList(oricol, orirow);
         				if(piece!=null) {
         					currcolor = colorCheck(piece);
         				}
         				if(firstmove==true && currcolor!=0) {
         					blackfirst = true;
         				}
         				//last check
         			capturedpiece = combinedList.searchList(descol, desrow);
         			//out.println(capturedpiece.col+" "+capturedpiece.row);
         			if(capturedpiece!=null) {
         				capturedcolor = colorCheck(capturedpiece);
         			}
         			if(capturedpiece==null){
         				//out.println("no piece captured");
         				capturedcolor = -100;
         			}
         			//last check
         			
         			if(piece==null) {
         				nopiece = true;
         				out.println(oricol+" "+orirow+" "+descol+" "+desrow+" illegal");
         				break;
         			}
         			
         			
         			if(prevcolor==currcolor) {
         				notalt = true;
         			}
         			
         			if(navCheck(piece, descol, desrow)==false) {
         				nogo = true;
         				
         			}
         			//out.println("capturedcolor = "+capturedcolor+" currcolor ="+currcolor);
         			if(capturedcolor==currcolor) {
         				sameteam = true;
         			}
         			
         			if(pathBlocked(combinedList, piece, descol, desrow)==true) {
         				roadblock = true;
         			}
         			
         			Chesspiece testpiece = null;
         			if(capturedpiece!=null) {
         				//out.println("wtf");
         				if(currcolor==0) {
         					blacklist.removeNode(capturedpiece);
         				}
         				if(currcolor==1) {
         					whitelist.removeNode(capturedpiece);
         				}
         			}
         			
         			if(currcolor==0) {
         				whitelist.movePiece(oricol, orirow, descol, desrow);
         			}
         			if(currcolor==1) {
         				blacklist.movePiece(oricol, orirow, descol, desrow);
         			}
         			//int howbig = blacklist.getSize();
         			//out.println(howbig);
         			//out.println(testpiece.col+" "+ testpiece.row);
         			//Node testcurr = blacklist.head;
            		//while(testcurr!=null) {
            		//	out.println("type:"+testcurr.item.type+" col:"+testcurr.item.col+" row:"+testcurr.item.row);
            		//	testcurr = testcurr.next;
            		//}
         			
         			if(currcolor==0) {
         				//out.println("hey");
         				if(kingCheck(whitelist, blacklist)==true) {
         					incheck = true;
         				}
         			}
         			
         			if(currcolor==1) {
         				if(kingCheck(blacklist, whitelist)==true) {
         					incheck = true;
         				}
         			}
         			//out.println(wtf);
         			
         			boolean illegal = false;
         			if(nopiece==true) {
         				//out.println("no piece");
         				illegal = true;
         			}
         			if(blackfirst==true) {
         				//out.println("blackfirst");
         				illegal = true;
         			}
         			if(notalt==true) {
         				//out.println("not alt");
         				illegal = true;
         			}
         			if(nogo==true) {
         				//out.println("no go");
         				illegal = true;
         			}
         			if(sameteam==true) {
         				//out.println("same team");
         				illegal = true;
         			}
         			if(roadblock == true) {
         				//out.println("road block");
         				illegal = true;
         			}
         			if(incheck == true) {
         				//out.println("in check");
         				illegal = true;
         			}
         			if(illegal==true) {
         				out.println(oricol+" "+orirow+" "+descol+" "+desrow+" illegal");
         				break;
         			}
         			
         			
         			
         			prevcolor = currcolor;
         			currcolor = -1;
         			counter = counter +4;
         			firstmove = false;
         		}// end of while(true)
         		//counter = 0;
         		
     		}// end of while(hasnextint)
     		/*
     		whitelist.movePiece(1, 5, 1, 4);
     		
     		Node curr = blacklist.head;
    		while(curr!=null) {
    			out.println("type:"+curr.item.type+" col:"+curr.item.col+" row:"+curr.item.row);
    			curr = curr.next;
    		}
    		Node currr = whitelist.head;
    		while(currr!=null) {
    			out.println("type:"+currr.item.type+" col:"+currr.item.col+" row:"+currr.item.row);
    			currr = currr.next;
    		}
         		
         	int fml = 0;
         	fml = kingCheck(whitelist, blacklist);
         	out.println(fml);
     	*/
     				
         		
      		// close files
     		in.close();
      		out.close();
	}// end of main
   	
   	// a method that prints a linked list
   	/*public static Chesspiece printList(Llist list) {
		Node curr = list.head;
		while(curr!=null) {
			out.println("type:"+curr.type+" col:"+curr.col+" row:"+curr.row);
			curr = curr.next;
		}
		return null;
	}*/
   	
   	// a method that checks if the king is in check, returns true if it is
   	public static boolean kingCheck(Llist list1, Llist list2) {//changed boolean to int to test
   		int x = 0;
   		int y = 0;
   		Llist newList = list1.combinelists(list2);
   		boolean blocked = false;
   		Chesspiece King = list1.findKing();
   		Chesspiece[] array = list2.underAttack(King);
   		if(array[0]==null) {
   			return false;// was false
   		}
   		while(array[x]!=null) {
   			blocked = false;
   			blocked = pathBlocked(newList, array[x], King.col, King.row);
   			if(blocked == false) {//was false
   				return true;// was true
   			}
   			x++;
   		}
   		return false;// was false
   	}
   	
   	// a method that checks if the piece can travel to a given square.
   	// returns true if it can, false if not.
   	public static boolean navCheck(Chesspiece g, int col, int row) {
   		Chesspiece k;
   		char c = 'p';
   		k = new Bpawn(col, row, c);
   		
   		if(g instanceof Queen) {
			Queen theQueen = (Queen)g;
			if(theQueen.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof King) {
			King theKing = (King)g;
			if(theKing.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof Bishop) {
			Bishop theBishop = (Bishop)g;
			if(theBishop.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof Rook) {
			Rook theRook = (Rook)g;
			if(theRook.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof Knight) {
			Knight theKnight = (Knight)g;
			if(theKnight.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof Bpawn) {
			Bpawn theBpawn = (Bpawn)g;
			if(theBpawn.checkAttack(k)) {
				return true;
			}
		}
		
		if(g instanceof Wpawn) {
			Wpawn theWpawn = (Wpawn)g;
			if(theWpawn.checkAttack(k)) {
				return true;
			}
		}
		return false;
   	}
   	
   	// returns 0 if white, 1 if black, and -1 if non (which would be an error)
   	public static int colorCheck(Chesspiece g) {
   		if(g.type=='k' || g.type=='q' || g.type=='r' || g.type=='b' || g.type=='n' || g.type=='p') {
   			return 0;
   		}
   		if(g.type=='K' || g.type=='Q' || g.type=='R' || g.type=='B' || g.type=='N' || g.type=='P') {
   			return 1;
   		}
   		return -1;
   	}
   	
   	// returns true if the path is blocked else false
   	public static boolean pathBlocked(Llist l, Chesspiece x, int descol, int desrow){//changed boolean to int for testing
   		int currcol = x.col;
   		int currrow = x.row;
   		
   		// checks queen path
   		if(x instanceof Queen) {
   			
   			if(x.col==descol) {

   				if((x.row-desrow) > 0) {
   					while(currrow>desrow) {
   						currrow--;
   						if(currrow!=desrow) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   			
   			
   				if((x.row-desrow) < 0) {
   					while(currrow<desrow) {
   						currrow++;
   						if(currrow!=desrow) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   			}// end of row move path check
   			
   			if(x.row==desrow) {
   				
   				if((x.col-descol) > 0) {
   					while(currcol>descol) {
   						currcol--;
   						if(currcol!=descol) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   				if((x.col-descol) < 0) {
   					while(currcol<descol) {
   						currcol++;
   						if(currcol!=descol) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   			}// end of col move path check
   			
   			//checks diagonal down to the left
   			if((x.col-descol) > 0 && (x.row-desrow) > 0) {
   				
   				while(currcol>descol && currrow>desrow) {
   					currcol--;
   					currrow--;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   			
   			//checks diagonal up and to the left
   			if((x.col-descol) > 0 && (x.row-desrow) < 0) {
   				
   				while(currcol>descol && currrow<desrow) {
   					
   					currcol--;
   					currrow++;
   					if(currcol!=descol && currrow != desrow) {
   						
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				
   				return false;
   			}
   			
   			//checks diagonal down to the right
   			if((x.col-descol) < 0 && (x.row-desrow) > 0) {
   				while(currcol<descol && currrow>desrow) {
   					currcol++;
   					currrow--;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   			
   		//checks diagonal up to the right
   			if((x.col-descol) < 0 && (x.row-desrow) < 0) {
   				while(currcol<descol && currrow<desrow) {
   					currcol++;
   					currrow++;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   		}// end of Queen path check
   		
   		// checks rook path
   		if(x instanceof Rook) {
   			
   			if(x.col==descol) {
   				if((x.row-desrow) > 0) {
   					while(currrow>desrow) {
   						currrow--;
   						if(currrow != desrow) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   				if((x.row-desrow) < 0) {
   					while(currrow<desrow) {
   						currrow++;
   						if(currrow != desrow) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   			}// end of row move path check
   			
   			if(x.row==desrow) {
   				if((x.col-descol) > 0) {
   					while(currcol>descol) {
   						currcol--;
   						if(currcol!=descol) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   				if((x.col-descol) < 0) {
   					while(currcol<descol) {
   						currcol++;
   						if(currcol!=descol) {
   							if((l.searchList(currcol,currrow)) != null) {
   								return true;
   							}
   						}
   					}
   					return false;
   				}
   			}// end of col move path check
   		}
   		
   		// checks bishop path
   		if(x instanceof Bishop) {
   			
   		//checks diagonal down to the left
   			if((x.col-descol) > 0 && (x.row-desrow) > 0) {
   				while(currcol>descol && currrow>desrow) {
   					currcol--;
   					currrow--;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   			
   			//checks diagonal up and to the left
   			if((x.col-descol) > 0 && (x.row-desrow) < 0) {
   				while(currcol>descol && currrow<desrow) {
   					currcol--;
   					currrow++;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   			
   			//checks diagonal down to the right
   			if((x.col-descol) < 0 && (x.row-desrow) > 0) {
   				while(currcol<descol && currrow>desrow) {
   					currcol++;
   					currrow--;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   			
   		//checks diagonal up to the right
   			if((x.col-descol) < 0 && (x.row-desrow) < 0) {
   				while(currcol<descol && currrow<desrow) {
   					currcol++;
   					currrow++;
   					if(currcol!=descol && currrow != desrow) {
   						if((l.searchList(currcol, currrow)) != null) {
   							return true;
   						}
   					}
   				}
   				return false;
   			}
   		}
   		return false;
   	}// end of pathBlocked() method
   	
   	
}// end of chess board class