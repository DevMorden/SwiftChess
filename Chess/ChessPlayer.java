/**
 * ChessPlayer
 *
 * This class is the interface to the computers of the chess game
*/

package Chess;

public interface ChessPlayer{
    public Move makeMove(Move lastMove, Piece[][] board);
    public void removePiece(Move move);
    public void pawnPromotion(Piece[][] board, Move move);
}