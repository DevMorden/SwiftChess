/**
 * Knight
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the piece class and runs everything for the Knight piece
*/

public class Knight extends Piece{
    private final int VALUE = 50;

    // Constructors
    public Knight(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("N");
        }else{
            System.out.print("n");
        }
    }

    public String getName(){
        return "Knight";
    }

    public String getCaptureLine(){
        return "Knight captured.";
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

        boolean a = xDist == 2;
        boolean b = yDist == 2;
        if((a && !b) || (!a && b)){
            if(xDist != 1 && yDist != 1){
                return false;
            }

            if(move.getPieceX() + xDist*xDir == move.getMoveX() && move.getPieceY() + yDist*yDir == move.getMoveY()){
                return true;
            }
        }
        
        return false;
    }

    public int getValue(){
        return VALUE;
    }
}