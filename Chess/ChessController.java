/**
 * ChessController
 *
 * This interface is used to control the game of chess, by moving pieces, resetting/starting the game, and playing the game
*/

package Chess;

public interface ChessController{
    public boolean movePiece(Move move, int team);
    public void reset();
    public void playGame();
}