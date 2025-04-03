/**
 * Piece
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class is an abstract class for all the pieces
*/

public abstract class Piece{
    private int team; // 0 means it's the player and 1 means it's the computer

    // Constructors
    public Piece(int initTeam){
        team = initTeam;
    }

    // Getter and setters
    public int getTeam(){
        return team;
    }

    // Abstract methods
    abstract void print();
    abstract String getName();
    abstract String getCaptureLine();
    abstract boolean validateMove(Move move, Piece[][] board);
    abstract int getValue();
    abstract String getType();
}