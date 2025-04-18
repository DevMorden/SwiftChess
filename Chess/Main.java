/**
 * Main
 *
 * Starts the game
*/

public class Main{
    public static void main(String[] args) {
        GameLogic chessGame = new GameLogic(new ChessGUI()); // Play game with ChessGUI
        chessGame.playGame(); // Start the game!
    }

}