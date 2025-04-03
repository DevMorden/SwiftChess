/**
 * GameDisplay
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class is used to interface the game display we're using to display the game of chess
*/

public interface GameDisplay{
    public void displayInvalidMove();
    public int promptForOpponentDifficulty(int maxDifficulty);
    public Move promptForMove();
    public void displayBoard(Piece[][] board);
    public void summarizeMove(Move move);
    public void gameOver(int winner);
    public boolean promptPlayAgain();
    public int promptPawnPromotion();
}