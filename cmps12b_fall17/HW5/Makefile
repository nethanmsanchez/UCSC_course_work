#-------------------------------------
# Nethaniel Sanchez   HW5
# negmsanc
# A make file that builds Bard.jar
# ------------------------------------


Bard: Bard.class
	echo Main-class: Bard > Manifest
	jar cvfm Bard.jar Manifest Bard.class Word.class Sortbyfreq.class Sortbyalph.class
	rm Manifest

Bard.class: Bard.java
	javac -Xlint Bard.java

clean:
	rm -f Bard.jar Bard.class
