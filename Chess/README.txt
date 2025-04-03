README

Matthew Morden, 7965196
Assignment 3

Compiling instructions
_____________________________________________
All my .java files are in the same directory when you extract the .zip file. Input this line first into aviary:

    javac *.java

After that line has ran, input this line:

    java A3MordenMatthew.java 

It should have fully ran and outputted to the console.

File names
_____________________________________________
A3MordenMatthew.java: This program starts a chess game against a computer.

GameDisplay.java: This class is used to interface the game display we're using to display the game of chess.
TestGameDisplay: This class implements the GameDisplay interface and displays the game all through the console.

ChessController.java: This class is used to control the game of chess, by moving pieces, resetting/starting the game, and playing the game.
GameLogic: This class implements the ChessController interface and controlsa all the game logic.

Move.java: This class is used to track a move which includes where the piece is and where it's going.
Node.java: This class contains the data contained inside of the Linked List class
LinkedList.java: This class contains the data contained inside of the Linked List class

ChessPlayer.java: This class is the interface to the computers of the chess game.
EasyAI.java: This class implements the ChessPlayer interface and is the easier AI
HardAI.java: This class implements the ChessPlayer interface and is the harder AI

Piece.java: This class is an abstract class for all the pieces
Rook.java: This class implements the piece class and runs everything for the Rook piece
Knight.java: This class implements the piece class and runs everything for the Knight piece
Bishop.java: This class implements the piece class and runs everything for the Bishop piece
Queen.java: This class implements the piece class and runs everything for the Queen piece
King.java: This class implements the piece class and runs everything for the King piece
Pawn.java: This class implements the piece class and runs everything for the Pawn piece
Empty.java: This class implements the piece class and runs everything for the Empty space

Platform and compiler/IDE
_____________________________________________
Windows 10 with Visual Studio Code Version 1.85.2

Other
_____________________________________________
Everything was finished
