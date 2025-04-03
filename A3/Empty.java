/**
 * Empty
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the piece class and runs everything for the Empty space
*/

public class Empty extends Piece{
    private final int VALUE = 0;

    // Constructors
    public Empty(int team){
        super(team);
    }

    // Methods
    public void print(){
        System.out.print(" ");
    }

    public String getName(){
        return "";
    }

    public String getCaptureLine(){
        return "No captures made.";
    }

    public boolean validateMove(Move move, Piece[][] board){
        System.out.println("EMPTY");
        return true;
    }

    public int getValue(){
        return VALUE;
    }
    
    public String getType(){
        return null;
    }
}