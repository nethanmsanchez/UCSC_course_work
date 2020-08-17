//------------------------
// Nethaniel Sanchez  HW2
// Negmsanc
// Chesspieces.java
// A file with the chesspiece superclass, and Queen, King, Rook, Bishop, and knight.
// -----------------------


public class Chesspiece{
	public int col;
	public int row;
	public char type;
}

class Queen extends Chesspiece{
	
	// constructor
	public Queen(int l, int m, char n){
		col = l;
		row = m;
		type = n;
	}

	// a method to check if my queen attacks another Chess piece
	boolean checkAttack(Chesspiece p){
			// checks if queen attacks queen p on rows and cols
			if(col == p.col || row == p.row){
				return true;
			}
			int k = 0;
			// checks if queen attacks queen q on all four diagonals
			while(k<8){
				if((col == p.col + k && row == p.row + k) || (col ==p.col - k && row == p.row - k) || (col == p.col + k && row == p.row - k) || (col == p.col - k && row == p.row + k)){
					return true;
				}
				k++;
			}
			// if queen doesn't attack on cols, rows, or diagonals, then return false
			return false;	
	}
}

class King extends Chesspiece{
	
	// constructor
	public King(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	
	// a method to check if my king attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		if((col-1==p.col && (row+1==p.row || row==p.row || row-1==p.row)) || (col==p.col && (row+1==p.row || row-1==p.row)) || (col+1==p.col && (row+1==p.row || row==p.row || row-1==p.row))) {
			return true;
		}
		return false;
	}
}

class Bishop extends Chesspiece{
	//constructor
	public Bishop(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	//a method to check if my bishop attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		int k = 0;
		while(k<8){
				if((col == p.col + k && row == p.row + k) || (col ==p.col - k && row == p.row - k) || (col == p.col + k && row == p.row - k) || (col == p.col - k && row == p.row + k)){
					return true;
				}
				k++;
			}
		return false;
	}
}

class Rook extends Chesspiece{
	//constructor
	public Rook(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	//a method to check if my bishop attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		if(col == p.col || row == p.row){
			return true;
		}
		return false;
	}
}
class Knight extends Chesspiece{
	//constructor
	public Knight(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	//a method to check if my knight attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		if((col-2==p.col && (row+1==p.row || row-1==p.row)) || (col-1==p.col && (row+2==p.row || row-2==p.row)) || (col+1==p.col && (row+2==p.row || row-2==p.row)) || (col+2==p.col && (row+1==p.row || row-1==p.row))) {
			return true;
		}
		return false;
	}
}

class Bpawn extends Chesspiece{
	//constructor
	public Bpawn(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	//a method to check if my black pawn attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		if((col==p.col-1 && row==p.row+1) || (col==p.col+1 && row==p.row+1)) {
			return true;
		}
		return false;
	}
	
}

class Wpawn extends Chesspiece{
	//constructor
	public Wpawn(int l, int m, char n) {
		col = l;
		row = m;
		type = n;
	}
	//a method to check if my black pawn attacks another Chesspiece
	boolean checkAttack(Chesspiece p) {
		if((col==p.col-1 && row==p.row-1) || (col==p.col+1 && row==p.row-1)) {
			return true;
		}
		return false;
	}
	
}
