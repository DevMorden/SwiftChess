/**
 * Pawn
 *
 * This class implements the piece class and runs everything for the Pawn piece
*/

public class Pawn extends Piece{
    private final int VALUE = 5;

    // Constructors
    public Pawn(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("P");
        }else{
            System.out.print("p");
        }
    }

    public String getName(){
        return "Pawn";
    }

    public String getCaptureLine(){
        return "Pawn captured.";
    }

    public boolean validateMove(Move move, Piece[][] board){
        if(getTeam() == 0){
            if (board[move.getMoveY()][move.getMoveX()].getTeam() == 1) { // Check if you're attacking another piece
                int xDirection = (move.getMoveX() - move.getPieceX()) > 0 ? 1 : -1; // 1 means its a right direction, -1 means its a left direction
                if(move.getMoveY() == move.getPieceY() - 1 && move.getMoveX() == move.getPieceX() + xDirection){
                    return true;
                }
            }else{
                // Just moving
                if (move.getMoveY() == move.getPieceY() - 1 && move.getMoveX() == move.getPieceX()) {
                    return true;
                } else if (move.getPieceY() == 6){
                    if (move.getMoveY() == move.getPieceY() - 2 && move.getMoveX() == move.getPieceX()) {
                        return true;
                    }
                }
            }
        }else if(getTeam() == 1){
            if (board[move.getMoveY()][move.getMoveX()].getTeam() == 0) { // Check if you're attacking another piece
                int xDirection = (move.getMoveX() - move.getPieceX()) > 0 ? 1 : -1; // 1 means its a right direction, -1 means its a left direction
                if(move.getMoveY() == move.getPieceY() + 1 && move.getMoveX() == move.getPieceX() + xDirection){
                    return true;
                }
            }else{
                // Just moving
                if (move.getMoveY() == move.getPieceY() + 1 && move.getMoveX() == move.getPieceX()) {
                    return true;
                } else if(move.getPieceY() == 6) {
                    if (move.getMoveY() == move.getPieceY() + 2 && move.getMoveX() == move.getPieceX()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int getValue(){
        return VALUE;
    }

    public String getType(){
        return "pawn";
    }
}