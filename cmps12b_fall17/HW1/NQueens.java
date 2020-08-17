//-----------------------------------------------------------------------------
// Nethaniel Sanchez  HW1
// Negmsanc
// NQueens.java
// Uses Queens.java to finds and prints the answers to an n queens problem when given a file with
// the board size, and position of a given queen.
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;
import java.lang.*;

class NQueens{
		public static int[] qrows;
		public static int[] qcols;
		public static int boardsize;
		public static Queen[] queens;
		public static int[] rowpos;
		public static int firstqcol;
		public static int firstqrow;
		public static int backtrackflag = 0;

   	public static void main(String[] args) throws IOException{		

      		// check number of command line arguments is at least 2  (from FileTokens)
      		if(args.length < 2){
         		System.out.println("Usage: java -jar FileTokens.jar <input file> <output file>");
         		System.exit(1);
      		}

      		// open files
      		Scanner in = new Scanner(new File(args[0]));
      		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		// initialize variables
		String num = null;
		String num2 = null;
		String num3 = null;
		int done =0;

      		// read lines from in, extract and print tokens from each line
     		while( in.hasNextInt() ){


         		// trim leading and trailing spaces, then add one trailing space so 
         		// split works on blank lines
         		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
         		String[] token = line.split("\\s+");  

			//int zero = 0;
			num = token[0];
			num2 = token[1];
			num3 = token[2];
			boardsize = Integer.parseInt(num);// was = Integer.parseInt(num);
			firstqcol = Integer.parseInt(num2);
			firstqrow = Integer.parseInt(num3);

			//used to check if all three tokens were taken from input file
			if(num3!=null){
				done=1;
			}

			if(done==1){
					
				//creates and initializes variables
				queens = new Queen[boardsize+1]; //added one to see if it would stop error
				qrows = new int[boardsize];
				qcols = new int[boardsize];
				rowpos = new int[boardsize + 1];
				
				for(int i = 0; i <boardsize;i++){
					qrows[i] = 0;
					qcols[i] = 0;
					rowpos[i] = 0;
					queens[i] = null;
				}
				backtrackflag = 0;
				qcols[firstqcol-1] = firstqcol;
				qrows[firstqcol-1] = firstqrow;
				queens[firstqcol-1] = new Queen(firstqcol-1,firstqrow-1);

				// runs method the that does everything
				if(methodName(boardsize)){
					for(int i=0;i<boardsize;i++){
						out.print(qcols[i] + " " + qrows[i] + " ");
					}
					out.println("");
				}else {
					out.println("No solution");
				}
								
				// clear variables for next line of input
				num = null;
				num2 = null;
				num3 = null;
				done = 0;
				firstqcol = 0;
				firstqrow = 0;
			}
		}

      		// close files
      		in.close();
      		out.close();
	}
	
	public static boolean methodName(int n){
		int currcol = boardsize-n;
		int attacked = 0;
		
		// checks if no solution
		if(currcol<0){	
			return false;
		}
		
		//checks if a sol has been found (Base case)
		if(currcol==boardsize){
			return true;
		}

		//checks if the first queen was put into this collumn, if it was skip to next iteration
		if(firstqcol==currcol+1){
			if(backtrackflag == 1){
				backtrackflag = 0;// clears the backtrack flag
				if(currcol==0){
					return methodName(n+1);
				}
				rowpos[currcol-1] = rowpos[currcol-1] +1; // added so that if firstq is in this col it won't 
				return methodName(n+1);
			}else{
				return methodName(n-1);
			}
		}	

		// The while loop runs until the queen is placed
		int placed = 0;
		while(placed==0){
			
			//checks if all the squares in currcol have been tried, if they have backtrack
			if(rowpos[currcol]==boardsize){ //changed > to ==
				
				if(currcol==0){
					return false;
				}
				rowpos[currcol-1] = rowpos[currcol-1] + 1;
				queens[currcol] = null;
				rowpos[currcol] = 0;
				backtrackflag = 1;
				return methodName(n+1);
			}else {
				backtrackflag = 0;
			}
			Queen q = new Queen(currcol,rowpos[currcol]);
			queens[currcol] = q;
			// the for loop is used to check all queens agains current queen
			for(int i=0; i<boardsize; i++){
				if(i==currcol){
					i++;
				}
				if(queens[i]!=null){// run the code and check the error
		
					if(q.isAttacking(queens[i], boardsize)){
						attacked = 1;  
					}
				}
			}
			
			if(attacked==0){
				qcols[currcol] = currcol+1;
				qrows[currcol] = rowpos[currcol]+1;
				return methodName(n-1);
			}
			rowpos[currcol] = rowpos[currcol]+1;
			queens[currcol] = null;
			attacked = 0;
			
		}
		return false;
	}
}

