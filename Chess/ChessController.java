/**
 * ChessController
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class is used to control the game of chess, by moving pieces, resetting/starting the game, and playing the game
*/

public interface ChessController{
    public boolean movePiece(Move move, int team);
    public void reset();
    public void playGame();
}