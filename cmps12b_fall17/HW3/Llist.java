//------------------------
//Nethan Sanchez  HW3
//Negmsanc
//Llist.java
//A file containing the Llist class
//-------------------------

public class Llist{
	Node head;
	
	// constructor
	public Llist(Node x){
		head = x;
	}
	
	// a method that returns the size of the list
	public int getSize() {
		int size = 0;
		Node curr = head;
		while(curr!=null) {
			size++;
			curr = curr.next;
		}
		return size;
	}
	 
	// a method that moves the piece with given col and row to new col and row
	public void movePiece(int col, int row, int newcol, int newrow) {
		Node curr = head;
		while(curr!=null) {
			if((curr.item.col==col) && (curr.item.row==row)) {
				curr.item.col = newcol;
				curr.item.row = newrow;
			}
			curr = curr.next;
		}
	}
	// a method that removes an item from the list with the given col and row
	public void removeNode(Chesspiece x){
		Node prevnode = null;
		Node curr = head;
		while(curr!=null) {
			if(curr.item.col==x.col && curr.item.row==x.row) {
				if(curr==head) {
					head = curr.next;
					break;
				}
				prevnode.next = curr.next;
				break;
			}
			prevnode = curr;
			curr = curr.next;
		}
		
	}
	
	// a method that returns an array of Chesspieces who can attack the given Chesspiece
	public Chesspiece[] underAttack(Chesspiece piece) {
		Chesspiece[] array = new Chesspiece[50];
		for(int n=0;n<50;n++) {
			array[n]= null;
		}
		int index = 0;
		Node curr = head;
		while(curr!=null) {
			if(curr.item instanceof Queen) {
				Queen theQueen = (Queen)curr.item;
				if(theQueen.checkAttack(piece)) {
					array[index] = theQueen;
					index++;
				}
			}
			
			if(curr.item instanceof King) {
				King theKing = (King)curr.item;
				if(theKing.checkAttack(piece)) {
					array[index] = theKing;
					index++;
				}
			}
			
			if(curr.item instanceof Bishop) {
				Bishop theBishop = (Bishop)curr.item;
				if(theBishop.checkAttack(piece)) {
					array[index] = theBishop;
					index++;
				}
			}
			
			if(curr.item instanceof Rook) {
				Rook theRook = (Rook)curr.item;
				if(theRook.checkAttack(piece)) {
					array[index] = theRook;
					index++;
				}
			}
			
			if(curr.item instanceof Knight) {
				Knight theKnight = (Knight)curr.item;
				if(theKnight.checkAttack(piece)) {
					array[index] = theKnight;
					index++;
				}
			}
			
			if(curr.item instanceof Bpawn) {
				Bpawn theBpawn = (Bpawn)curr.item;
				if(theBpawn.checkAttack(piece)) {
					array[index] = theBpawn;
					index++;
				}
			}
			
			if(curr.item instanceof Wpawn) {
				Wpawn theWpawn = (Wpawn)curr.item;
				if(theWpawn.checkAttack(piece)) {
					array[index] = theWpawn;
					index++;
				}
			}
			array[index] = null;
			curr = curr.next;
		}
		return array;
	}
	
	// a method that returns the king in a given list. returns null if none found
	public Chesspiece findKing() {
		Node curr = head;
		while(curr!=null) {
			if(curr.item instanceof King) {
				return curr.item;
			}
			curr = curr.next;
		}
		return null;
	}
	
	//a method that adds a node and returns the size of the list
	public int addNode(Node z) {
		boolean flag = true;
		int counter=2;
		Node curr = head;// curr should point to head node
		while(flag) {// endless loop that only ends when the end of the list is reached
			if(curr.next==null) {
				curr.next = z;
				return counter;
			}
			counter = counter + 1;
			curr = curr.next;
		}
		return -1;
	}
	
	// a method that searches a list for a node with the given col and row and returns
	// the chesspiece at that location. also removes chesspiece from list to make
	// attacking easier.
	public Chesspiece searchList(int c, int r) {
		Node curr = head;
		while(curr!=null) {
			if(c==curr.item.col && r==curr.item.row) {//not sure if this is right
				return curr.item;
			}
			curr = curr.next;
		}
		return null;
	}
	
	// a method that checks if the given chess piece attacks another piece in the list
	public boolean checkattack(Chesspiece g) {
		Node curr = head;
		while(curr!=null) {
			if(g instanceof Queen) {
				Queen theQueen = (Queen)g;
				if(theQueen.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof King) {
				King theKing = (King)g;
				if(theKing.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof Bishop) {
				Bishop theBishop = (Bishop)g;
				if(theBishop.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof Rook) {
				Rook theRook = (Rook)g;
				if(theRook.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof Knight) {
				Knight theKnight = (Knight)g;
				if(theKnight.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof Bpawn) {
				Bpawn theBpawn = (Bpawn)g;
				if(theBpawn.checkAttack(curr.item)) {
					return true;
				}
			}
			
			if(g instanceof Wpawn) {
				Wpawn theWpawn = (Wpawn)g;
				if(theWpawn.checkAttack(curr.item)) {
					return true;
				}
			}
			
			
			curr=curr.next;
		}
		return false;
	}

	// a method that combines two given lists into one and returns it
	public Llist combinelists(Llist b) {
		int holder = 0;
		Node curr = b.head;
		Node first = head.next;
		Node temp3 = new Node(head.item);
		Llist c = new Llist(temp3);
		while(first!=null) {
			Node temp1 = new Node(first.item);
			holder = c.addNode(temp1);
			first = first.next;
		}
		//curr = b.head;
		while(curr!=null) {
			Node temp2 = new Node(curr.item);
			holder = c.addNode(temp2);
			curr = curr.next;
		}
		return c;
	}
	
	
	// a method that returns false if not valid or true if the board is valid
	public boolean validcheck() {
		Node checkee = head;
		while(checkee!=null) {
			Node curr = head;
			while(curr!=null) {
				if(curr.item!=checkee.item) {
					if((curr.item.col==checkee.item.col) && (curr.item.row==checkee.item.row)) {
						return true;
					}
				}
				curr = curr.next;
			}
			checkee = checkee.next;
		}
		return false;
	}
	
	void printlist(int[] x, int[] y) {
   		Node curr = head;
   		int i = 0;
   		while(curr!=null) {
   			x[i] = curr.item.col;
   			y[i] = curr.item.row;
   			i++;
   		}
   	}
	
}