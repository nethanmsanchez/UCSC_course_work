//-----------------------------------------------------------------------------
// Nethaniel Sanchez  HW4
// Negmsanc
// NQueens.java
// Uses Queens.java to finds and prints the answers to an n queens problem when given a file with
// the board size, and position of given queens.
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.lang.*;

class NQueens{
	public static Queen[] queens;
	public static Queen[] answer;
	public static int[] qrows;
	public static int[] qcols;
	public static int[] firstqueens;
	public static int boardsize;
	public static int[] rowpos;
	public static int[] orirowpos;
	public static int firstqcol;
	public static int firstqrow;
	public static int backtrackflag = 0;
	public static int currcol = 0;
	public static int index = 1;
	public static Stack<Queen> qstack = new Stack<Queen> ();
	public static Queen throwaway;
	public static boolean skip = false;
	public static boolean tru = true;
	public static boolean nosol = false;
	public static boolean solfound = false;
	public static boolean restart = false;

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
     			skip = false;
     			throwaway = null;
     			done = 0;
     			currcol = 0;
         		// trim leading and trailing spaces, then add one trailing space so 
         		// split works on blank lines
         		String line = in.nextLine().trim() + " "; 

         		// split line around white space 
         		String[] token = line.split("\\s+");  
         		
         		
         	// sets boardsize
    			num = token[0];
    			boardsize = Integer.parseInt(num);
     			
     			//creates and initializes variables
    			queens = new Queen[boardsize+1];
    			answer = new Queen[boardsize+1];
				qrows = new int[boardsize];
				qcols = new int[boardsize];
				firstqueens = new int[boardsize + 1];
				rowpos = new int[boardsize + 1];
				orirowpos = new int[boardsize+1];
				for(int i = 0; i <boardsize;i++){
					qrows[i] = 0;
					orirowpos[i] = 0;
					qcols[i] = 0;
					rowpos[i] = 0;
					firstqueens[i] = 0;
					queens[i] = null;
				}
				backtrackflag = 0;

			// grabs the input and stores the pieces in qcol and qrow
			for(int i = 1; i < token.length - 1; i=i+2){
				num2 = token[i];
				num3 = token[i+1];
				firstqcol = Integer.parseInt(num2);
				firstqrow = Integer.parseInt(num3);
				qcols[firstqcol-1] = firstqcol;
				qrows[firstqcol-1] = firstqrow;
				firstqueens[firstqcol-1] = 1;
				throwaway = new Queen(firstqcol-1,firstqrow-1);
				if(queens[firstqcol-1]!=null) {
					skip = true;
					nosol = true;
					done = 1;
				}
				queens[firstqcol-1] = throwaway;
				rowpos[firstqcol-1] = firstqrow-1;
				orirowpos[firstqcol-1] = firstqrow-1;
			}
			
			for(int i=0; i<=boardsize; i++){
				if(queens[i]!=null){
					for(int e=0; e<=boardsize; e++) {
						if(queens[e]!=null) {
							if(i!=e) {
								if(queens[i].isAttacking(queens[e], boardsize)){
									skip = true;
									nosol = true;
									done = 1;
								}
								//if(queens[i].col==queens[e].col) {
								//	skip = true;
								//	nosol = true;
								//	done = 1;
								//}
							}
						}
					}
				}
			}
			//       a test to make sure the input was grabbed and stored correctly
			//for(int x = 0;x < boardsize;x++) {
			//	out.println(qcols[x]+" "+ qrows[x]);
			//}
				
			if(skip!=true) {
				tru = true;
				nosol = false;
				solfound = false;
				restart = false;
			}
			while(done==0) {
				restart = false;
				while(tru) {
					int attacked = 0;
					
					//Checks for no solution
					if(currcol<0) {
						done = 1;
						nosol = true;
						break;
					}
			
					//Checks if a solution has been found
					if(currcol>=boardsize) {
						done = 1;
						solfound = true;
						break;
					}
			
					//Checks if one of the first queens was placed into this collumn
					if(firstqueens[currcol]==1) {
						if(backtrackflag == 1) {
							//backtrackflag = 0;
							if(currcol==0) {
								done = 1;
								nosol = true;
								break;
							}
							throwaway = qstack.pop();
							rowpos[currcol-1] = rowpos[currcol-1] + 1;
							currcol = currcol - 1;
							break;
						}else {
							Queen cue = new Queen(currcol+1, orirowpos[currcol]+1);
							qstack.push(cue);
							currcol = currcol + 1;
							break;
						}
					}
			
					int placed = 0;
					while(placed==0) {
						
						// Checks if all the squares in currcol have been tried
						if(rowpos[currcol]>=boardsize) {
							if(currcol==0) {
								done = 1;
								nosol = true;
								break;
							}
							if(firstqueens[currcol-1]==0) {
								rowpos[currcol-1] = rowpos[currcol-1] + 1;
							}
							queens[currcol] = null;
							rowpos[currcol] = 0;
							backtrackflag = 1;
							currcol = currcol -1;
							throwaway = qstack.pop();
							restart = true;
							break;
						}else {
							backtrackflag = 0;
						}
						
						
						Queen q = new Queen(currcol,rowpos[currcol]);
						queens[currcol] = q;
						
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
						
						if(attacked==0) {
							Queen cue = new Queen(currcol+1,rowpos[currcol]+1);
							qstack.push(cue);
							currcol = currcol + 1;
							break;
						}
						rowpos[currcol] = rowpos[currcol] + 1;
						queens[currcol] = null;
						attacked = 0;
					}// end of while(placed)
		
					if(restart==true) {
						restart = false;
						break;
					}
					// clear variables for next line of input
					num = null;
					num2 = null;
					num3 = null;
					done = 0;
					firstqcol = 0;
					firstqrow = 0;
					if(nosol==true) {
						done = 1;
						break;
					}
				}// end of while(tru)
			}// end of while(done==0)
			if(nosol==true) {
				out.println("No solution");
			}
			
			if(solfound==true && nosol==false) {
				for(int i=boardsize-1;i>=0;i--) {
					if(qstack.empty()==false) {
						throwaway = qstack.pop();
						answer[i] = throwaway;
					}
				}
				for(int i=0;i<boardsize;i++) {
					if(answer[i]!=null) {
						out.print(answer[i].col +" "+answer[i].row+" ");
					}
				}
				out.println("");
			}
			
			while(qstack.empty()==false) {
				throwaway = qstack.pop();
				if(qstack.empty()==true) {
					break;
				}
			}
			
		}// end of while(hasnextint)

      		// close files
      		in.close();
      		out.close();
	}// end of main
}// end of class NQueens
