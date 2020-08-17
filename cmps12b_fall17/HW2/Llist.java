
//------------------------
//Nethan Sanchez  HW1
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
			
			curr=curr.next;
		}
		return false;
	}
	
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