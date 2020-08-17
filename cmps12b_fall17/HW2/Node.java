//--------------------
//Nethaniel Sanchez  HW2
//Negmsanc
//Node.java
//A file with the node class for a linked list of Chesspieces
//---------------------
public class Node {
	public Chesspiece item;
	public Node next;
	
	//constructor
	public Node(Chesspiece x){
		item = x;
		next = null;
	}
}