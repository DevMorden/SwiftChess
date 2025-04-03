/**
 * Bishop
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the piece class and runs everything for the Bishop piece
*/

public class Bishop extends Piece{
    private final int VALUE = 30;

    // Constructors
    public Bishop(int team){
        super(team);
    }

    // Methods
    public void print(){
        if(getTeam() == 0){
            System.out.print("B");
        }else{
            System.out.print("b");
        }
    }

    public String getName(){
        return "Bishop";
    }

    public String getCaptureLine(){
        return "Bishop captured.";
    }

    public boolean validateMove(Move move, Piece[][] board){
        int xDir = (move.getMoveX() - move.getPieceX()) > 0 ? 1 : -1; // 1 means its a right direction, -1 means its a left direction
        int yDir = (move.getMoveY() - move.getPieceY()) > 0 ? 1 : -1; // 1 means its an up direction, -1 means its a down direction
        int xDist = Math.abs(move.getMoveX() - move.getPieceX());
        int yDist = Math.abs(move.getMoveY() - move.getPieceY());

        if(xDist != yDist){
            return false;
        }else{
            for(int i = 1; i < xDist; i++){
                if(board[move.getPieceX() + i*xDir][move.getPieceY() + i*yDir].getTeam() != -1){
                    return false;
                }
            }
        }
    
        return true;
    }

    public int getValue(){
        return VALUE;
    }

    public String getType(){
        return "bishop";
    }
}