/**
 * GameDisplay
 *
 * This class is used to interface the game display we're using to display the game of chess
*/

package Chess;

public interface GameDisplay{
    public void displayInvalidMove();

    public int startScreen(int maxDifficulty);
    public Move promptForMove();
    public void displayBoard(Piece[][] board);
    public void summarizeMove(Move move);
    public void gameOver(int winner);
    public boolean promptPlayAgain();
    public int promptPawnPromotion();
}