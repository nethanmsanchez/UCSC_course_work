#-------------------------------------
# Nethaniel Sanchez   HW1
# negmsanc
# A make file that builds NQueens.jar
# ------------------------------------


NQueens: NQueens.class Queen.class
	echo Main-class: NQueens > Manifest
	jar cvfm NQueens.jar Manifest NQueens.class Queen.class
	rm Manifest

NQueens.class: NQueens.java
	javac -Xlint NQueens.java

Queen.class: Queen.java
	javac -Xlint Queen.java

clean:
	rm -f NQueens.jar NQueens.class Queen.class

