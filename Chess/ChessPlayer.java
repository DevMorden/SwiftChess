/**
 * ChessPlayer
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class is the interface to the computers of the chess game
*/

public interface ChessPlayer{
    public Move makeMove(Move lastMove, Piece[][] board);
    public void removePiece(Move move);
    public void pawnPromotion(Piece[][] board, Move move);
}