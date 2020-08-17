#------------------------------
# Nethaniel Sanchez   HW2
# negmsanc
# A make file that builds ChessBoard.jar
#------------------------------


ChessBoard: ChessBoard.class Chesspiece.class Llist.class Node.class
	echo Main-class: ChessBoard > Manifest
	jar cvfm ChessBoard.jar Manifest ChessBoard.class Chesspiece.class Llist.class Node.class Queen.class King.class Bishop.class Rook.class Knight.class Bpawn.class Wpawn.class
	rm Manifest

ChessBoard.class: ChessBoard.java
	javac ChessBoard.java

Chesspiece.class: Chesspiece.java
	javac Chesspiece.java

Llist.class: Llist.java
	javac Llist.java

Node.class: Node.java
	javac Node.java

clean:
	rm -f ChessBoard.jar ChessBoard.class Llist.class Node.class
