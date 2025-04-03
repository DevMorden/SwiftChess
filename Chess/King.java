/**
 * King
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the piece class and runs everything for the King piece
*/

public class King extends Piece{
    private final int VALUE = 999;
    // Constructors
    public King(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("K");
        }else{
            System.out.print("k");
        }
    }

    public String getName(){
        return "King";
    }

    public String getCaptureLine(){
        return "King captured.";
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
        if(yDir > 0){ // Up direction
            yDir = 1;
        }else if(yDir < 0){ // Down direction
            yDir = -1;
        }else{ // Does not move on the y-axis
            yDir = 0;
        }

        if(move.getMoveY() == move.getPieceY() + yDir && move.getMoveX() == move.getPieceX() + xDir){
            return true;
        }else{
            return false;
        } 
    }

    public int getValue(){
        return VALUE;
    }

    public String getType(){
        return "king";
    }
}