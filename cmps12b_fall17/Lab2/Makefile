# Nethaniel Sanchez   Lab02
# negmsanc
# a makefile used to build FileReverse
#
FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse.jar Manifest FileReverse.class
	rm Manifest

FileReverse.class: FileReverse.java
	javac FileReverse.java

clean:
	rm -f FileReverse.jar FileReverse.class
