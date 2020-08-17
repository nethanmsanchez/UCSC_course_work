//--------------------------------------
// Nethaniel Sanchez    HW1
// Negmsanc
// Queen.java
// A file containing the class Queens, which is used by NQueens.java
// --------------------------------------


public class Queen{
	int col;
	int row;
	int bsize;

	public Queen(int c, int r){
		// Constructor
		col = c;
		row = r;
	}

	boolean isAttacking(Queen q, int n){
		bsize = n;
		// checks if queen attacks queen q on rows and cols
		if(col == q.col || row == q.row){
			return true;
		}
		int k = 0;
		// checks if queen attacks queen q on all four diagonals
		while(k<bsize){
			if((col == q.col + k && row == q.row + k) || (col ==q.col - k && row == q.row - k) || (col == q.col + k && row == q.row - k) || (col == q.col - k && row == q.row + k)){
				return true;
			}
			k++;
		}
		// if queen doesn't attack on cols, rows, or diagonals, then return false
		return false;
	}
}
