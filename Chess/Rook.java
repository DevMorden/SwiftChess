/**
 * Rook
 *
 * This class implements the piece class and runs everything for the Rook piece
*/

package Chess;

public class Rook extends Piece{
    private final int VALUE = 25;

    // Constructors
    public Rook(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("R");
        }else{
            System.out.print("r");
        }
    }

    public String getName(){
        return "Rook";
    }

    public String getCaptureLine(){
        return "Rook captured.";
    }

    public boolean validateMove(Move move, Piece[][] board){
        // Checks what direction the rook is moving
        int xDir = move.getMoveX() - move.getPieceX();
        if(xDir > 0){ // Right direction
            xDir = 1;
        }else if(xDir < 0){ // Left direction
            xDir = -1;
        }else{ // Does not move on the x-axis
            xDir = 0;
        }

        int yDir = (move.getMoveY() - move.getPieceY());
        if (yDir > 0) { // Up direction
            yDir = 1;
        } else if (yDir < 0) { // Down direction
            yDir = -1;
        } else { // Does not move on the y-axis
            yDir = 0;
        }
        
        int xDist = Math.abs(move.getMoveX() - move.getPieceX());
        int yDist = Math.abs(move.getMoveY() - move.getPieceY());
        
        if(xDist == 0 || yDist == 0){
            int dist = Math.max(xDist, yDist);
            for(int i = 1; i < dist; i++){
                if (board[move.getPieceY() + i * yDir][move.getPieceX() + i * xDir].getTeam() != -1) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public int getValue(){
        return VALUE;
    }

    public String getType(){
        return "rook";
    }
}