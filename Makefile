default: run

compile:
	javac -cp .:junit5.jar FrontendDeveloperTests.java

run: compile UserPlaylistFrontendFD.class
	java UserPlaylistFrontendFD

FrontendDeveloperTests.class: FrontendDeveloperTests.java UserPlaylistFrontendFD.class RBTreeBackendBD.class
	javac FrontendDeveloperTests.java

UserPlaylistFrontendFD.class: UserPlaylistFrontendFD.java UserPlaylistFrontendInterfaceFD.java RBTreeBackendBD.java
	javac UserPlaylistFrontendFD.java UserPlaylistFrontendInterfaceFD.java RBTreeBackendBD.java

RBTreeBackendFD.class: RBTreeBackendBD.java RBTreeBackendInterfaceBD.java RedBlackTreeTraversalAE.class SongReaderDW.cl\
ass
	javac RBTreeBackendBD.java RBTreeBackendInterfaceBD.java

RedBlackTreeTraversalFD.class: RedBlackTreeTraversalAE.java RedBlackTreeTraversalInterfaceAE.java
	javac RedBlackTreeTraversalFD RedBlackTreeTraversalInterfaceAE

SongDW.class: SongDW.java
	javac SongDW

SongReaderFD.class: SongReaderDW.java
	javac SongReaderDW.java

runTests:
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar  junit5.jar -cp . --select-class=AlgorithmEngineerTests
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

clean:
	rm *.class
