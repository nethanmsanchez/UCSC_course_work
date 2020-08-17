#-------------------------
# Nethaniel Sanchez  HW3
# negmsanc
# A Makefile that builds ChessMoves.jar
#-------------------------

ChessMoves: ChessMoves.class Chesspiece.class Llist.class Node.class
	echo Main-class: ChessMoves > Manifest
	jar cvfm ChessMoves.jar Manifest ChessMoves.class Chesspiece.class Llist.class Node.class Queen.class King.class Bishop.class Rook.class Knight.class Bpawn.class Wpawn.class
	rm Manifest
	
ChessMoves.class: ChessMoves.java
	javac ChessMoves.java
	
Chesspiece.class: Chesspiece.java
	javac Chesspiece.java
	
Llist.class: Llist.java
	javac Llist.java
	
Node.class: Node.java
	javac Node.java
	
clean:
	rm -f ChessMoves.jar ChessMoves.class Llist.class Node.class