# SwiftChess
Chess game with a GUI

I wrote this game of chess in my second year of University to practice Java and inheritance. In the game, you are the white pieces and you're playing against a simple or hard AI.
The simple AI just makes random moves while the hard AI calculates all it's possible moves (only 1 step ahead) and determines the best one to perform.
When I first built it, I used the console as the user interface. You would choose what piece you want to move by inputting an x and y coordinate and then choosing where you want to put it in the same way.
Then the gameLogic would check if the move was valid and move it if it was. 
Now, I've added a GUI which simplifies the whole process by making everything buttons and much more enjoyable!

The game is built as an Model-View-Controller (MVC) which makes the transition to a GUI much easier as I only need to change the interface and none of the logic.

# How it was built
I first started by creating all the interfaces I would need for the game (the controller, the game logic, the display). 
I started by building the main game loop and then moved onto building the logic for moving the pieces. The Piece class is an abstract class and all the pieces are children of it.
They inherit similar functions. The hardest part about this part of the process was not being able to test out my code since I didn't have a board to try it on.
 I moved onto making the simple text display of the chess game just so I could test my logic. It would simply print out an 2D array of Pieces into the terminal and ask for
 input through the terminal as well. It was very tedious to have to be like "input piece x. Okay now input piece y".
 After I had the whole game running well enough, I started builidng the GUI to make the game easier and more accessible to play.

# How to install
All of my code is available, but you only need to download the .jar file if you want to play it and test it out. ADD STEPS ON HOW TO DOWNLOAD AND PLAY
You must have Java 17+ downloaded on your machine for it to run.
