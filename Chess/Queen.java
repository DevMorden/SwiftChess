/**
 * Queen
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the piece class and runs everything for the Queen piece
*/

public class Queen extends Piece{
    private final int VALUE = 100;
    // Constructors
    public Queen(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("Q");
        }else{
            System.out.print("q");
        }
    }

    public String getName(){
        return "Queen";
    }

    public String getCaptureLine(){
        return "Queen captured.";
    }

    public boolean validateMove(Move move, Piece[][] board){
        int xDir = move.getMoveX() - move.getPieceX();
        if(xDir > 0){ // Right direction
            xDir = 1;
        }else if(xDir < 0){ // Left direction
            xDir = -1;
        }else{ // Does not move on the x-axis
            xDir = 0;
        }

        int yDir = (move.getMoveY() - move.getPieceY());
        if(yDir > 0){ // Right direction
            yDir = 1;
        }else if(yDir < 0){ // Left direction
            yDir = -1;
        }else{ // Does not move on the x-axis
            yDir = 0;
        }
        
        int xDist = Math.abs(move.getMoveX() - move.getPieceX());
        int yDist = Math.abs(move.getMoveY() - move.getPieceY());

        if(xDist == yDist){
            for(int i = 1; i < xDist; i++){
                // System.out.printf("board[%d][%d] has team: %d\n", move.getPieceX() + i*xDir, move.getPieceY() + i*yDir, board[move.getPieceX() + i*xDir][move.getPieceY() + i*yDir].getTeam());
                if(board[move.getPieceX() + i*xDir][move.getPieceY() + i*yDir].getTeam() != -1){
                    return false;
                }
            }
            return true;
        }else if(xDist == 0 || yDist == 0){
            int dist = Math.max(xDist, yDist);
            for(int i = 1; i < dist; i++){
                // System.out.printf("board[%d][%d] has team: %d\n", move.getPieceX() + i*xDir, move.getPieceY() + i*yDir, board[move.getPieceX() + i*xDir][move.getPieceY() + i*yDir].getTeam());
                if(board[move.getPieceX() + i*xDir][move.getPieceY() + i*yDir].getTeam() != -1){
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
}