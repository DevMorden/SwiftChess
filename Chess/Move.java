/**
 * Move
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class is used to track a move which includes where the piece is and where it's going.
*/

public class Move{
    private int pieceX;
    private int pieceY;
    private Piece piece;
    private int moveX;
    private int moveY;
    private Piece capturePiece;
    private boolean capture;

    public Move(int initPieceX, int initPieceY, int initMoveX, int initMoveY){
        pieceX = initPieceX;
        pieceY = initPieceY;
        moveX = initMoveX;
        moveY = initMoveY;
        capture = false;
    }

    public Move(Piece initPiece, int initPieceX, int initPieceY){
        piece = initPiece;
        pieceX = initPieceX;
        pieceY = initPieceY;
        capture = false;
    }

    // Getter and setters
    public void setPiece(Piece toSet){
        piece = toSet;
    }

    public void setCapturePiece(Piece toSet){
        capturePiece = toSet;
    }

    public void setPieceX(int toSet){
        pieceX = toSet;
    }

    public void setPieceY(int toSet){
        pieceY = toSet;
    }

    public int getPieceX(){
        return pieceX;
    }

    public int getPieceY(){
        return pieceY;
    }

    public Piece getPiece(){
        return piece;
    }

    public Piece getCapturePiece(){
        return capturePiece;
    }

    public int getMoveX(){
        return moveX;
    }

    public int getMoveY(){
        return moveY;
    }

    public boolean getCapture(){
        return capture;
    }

    // Methods
    public boolean equals(Move compare){
        if(pieceX == compare.getPieceX() && pieceY == compare.getPieceY()){
            return true;
        }

        return false;
    }
}