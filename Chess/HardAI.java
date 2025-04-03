/**
 * HardAI
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the ChessPlayer interface and is the harder AI
*/

public class HardAI implements ChessPlayer{
    private final int MAX_PIECES = 16;
    private Move[] pieces; // Computer's pieces with locations
    private int numPieces;

    // This constructor initializes the pieces the computer still has in the game. It also tells the computer 
    // which pieces it has and where they are.
    //
    // PARAMETERS:
    //    firstParameter: The board parameter is the current state of the board (is passed the board at the 
    //                    start of the game) to tell the computer what pieces it has currently in the game and where.
    public HardAI(Piece[][] board){
        pieces = new Move[MAX_PIECES];
        numPieces = 0;

        // Setting up pieces
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    if(j == 0 || j == 7){
                        pieces[numPieces] = new Move(board[j][i], j, i);
                        numPieces++;
                    }else if(j == 1 || j == 6){
                        pieces[numPieces] = new Move(board[j][i], j, i);
                        numPieces++;
                    }else if(j == 2 || j == 5){
                        pieces[numPieces] = new Move(board[j][i], j, i);
                        numPieces++;
                    }else if(j == 3){
                        pieces[numPieces] = new Move(board[j][i], j, i);
                        numPieces++;
                    }else if(j == 4){
                        pieces[numPieces] = new Move(board[j][i], j, i);
                        numPieces++;
                    }
                }else if(i == 1){
                    pieces[numPieces] = new Move(board[j][i], j, i);
                    numPieces++;
                }
            }
        }
    }

    // Public methods
    // This method is used to make a valid move we want the computer to perform. The way my hard AI creates a 
    // move is that it goes through every possible move for every single computer piece currently in the game 
    // and then verifies each and everyone one as their made while assigning the move a value. Once every valid 
    // move is made, it will find the move with the highest value. Value is determined by the piece which is being 
    // captured. Kings have the highest value and will be prioritized.
    //
    // PARAMETERS:
    //    firstParameter: The lastMove parameter was necessary but I didn't use it :3
    //    secondParameter: The board parameter is the current state of the board.
    //    
    // RETURNS:
    //    This method returns a valid to move to perform on the board.
    public Move makeMove(Move lastMove, Piece[][] board){
        int randomNumber;
        LinkedList validMoves = new LinkedList();
        int numValidMoves = 0;

        // Creating all possible moves
        for(int i = 0; i < numPieces; i++){
            Move move = pieces[i];
            if(move.getPiece() instanceof Pawn){ // Pawn
                for(int j = -1; j <= 1; j++){ // Check left, middle, and right direction
                    if(move.getPieceX() + j <= 7 && move.getPieceX() + j >= 0 && move.getPieceY() + 1 <= 7 && move.getPieceY() >= 0){
                        Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX() + j, move.getPieceY() + 1); 
                        hold.setPiece(move.getPiece());
                        hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);

                        if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                            validMoves.add(hold, hold.getCapturePiece().getValue());
                        }
                    }
                }
            }else if(move.getPiece() instanceof Rook){ // Rook
                for(int j = -1; j < 2; j++){
                    if(j != 0){ // Left and right
                        int x = move.getPieceX() + j; // Used to track piece placement
                        while(x <= 7 && x >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), x, move.getPieceY()); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                            x += j;

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                            }
                        }
                    }else{ // Up and down
                        for(int k = -1; k < 2; k += 2){
                            int y = move.getPieceY() + k; // Used to track piece placement
                            while(y <= 7 && y >= 0){
                                Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX(), y); 
                                hold.setPiece(move.getPiece());
                                hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                                y += k;

                                if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                    validMoves.add(hold, hold.getCapturePiece().getValue());
                                    numValidMoves++;
                                }
                            }
                        }
                    }
                }
            }else if(move.getPiece() instanceof Knight){ // Knight
                // Checking left and right
                for(int j = -2; j < 3; j += 4){
                    for(int k = -1; k < 2; k += 2){
                        if(move.getPieceX() + j <= 7 && move.getPieceX() + j >= 0 && move.getPieceY() + k <= 7 && move.getPieceY() + k >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX() + j, move.getPieceY() + k); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                                numValidMoves++;
                            }
                        }
                    }
                }

                // Checking up and down
                for(int j = -2; j < 3; j += 4){
                    for(int k = -1; k < 2; k += 2){
                        if(move.getPieceX() + k <= 7 && move.getPieceX() + k >= 0 && move.getPieceY() + j <= 7 && move.getPieceY() + j >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX() + k, move.getPieceY() + j); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                                numValidMoves++;
                            }
                        }
                    }
                }
            }else if(move.getPiece() instanceof Bishop){ // Bishop
                for(int j = -1; j < 2; j += 2){
                    for(int k = -1; k < 2; k += 2){
                        int x = move.getPieceX() + j; // Used to track piece placement
                        int y = move.getPieceY() + k; // Used to track piece placement

                        while(x <= 7 && x >= 0 && y <= 7 && y >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), x, y); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                            y += k;
                            x += j;

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                                numValidMoves++;
                            }
                        }
                    }
                }
            }else if(move.getPiece() instanceof Queen){ // Queen
                for(int j = -1; j < 2; j += 2){
                    for(int k = -1; k < 2; k += 2){
                        int x = move.getPieceX() + j; // Used to track piece placement
                        int y = move.getPieceY() + k; // Used to track piece placement

                        while(x <= 7 && x >= 0 && y <= 7 && y >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), x, y); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                            y += k;
                            x += j;

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                                numValidMoves++;
                            }
                        }
                    }
                }

                for(int j = -1; j < 2; j++){
                    if(j != 0){ // Left and right
                        int x = move.getPieceX() + j; // Used to track piece placement
                        while(x <= 7 && x >= 0){
                            Move hold = new Move(move.getPieceX(), move.getPieceY(), x, move.getPieceY()); 
                            hold.setPiece(move.getPiece());
                            hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                            x += j;

                            if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                validMoves.add(hold, hold.getCapturePiece().getValue());
                                numValidMoves++;
                            }
                        }
                    }else{ // Up and down
                        for(int k = -1; k < 2; k += 2){
                            int y = move.getPieceY() + k; // Used to track piece placement
                            while(y <= 7 && y >= 0){
                                Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX(), y); 
                                hold.setPiece(move.getPiece());
                                hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                                y += k;

                                if(board[hold.getMoveX()][hold.getMoveY()].getTeam() != 1 && hold.getPiece().validateMove(hold, board)){
                                    validMoves.add(hold, hold.getCapturePiece().getValue());
                                    numValidMoves++;
                                }
                            }
                        }
                    }
                }
            }else if(move.getPiece() instanceof King){ // King
                for(int j = -1; j < 2; j++){ // X
                    for(int k = -1; k < 2; k++){ // Y
                        if(j != 0 || k != 0){
                            if(move.getPieceX() + j <= 7 && move.getPieceX() + j >= 0 && move.getPieceY() + k <= 7 && move.getPieceY() + k >= 0){
                                if(board[move.getPieceX() + j][move.getPieceY() + k].getTeam() != 1){
                                    Move hold = new Move(move.getPieceX(), move.getPieceY(), move.getPieceX() + j, move.getPieceY() + k); 
                                    hold.setPiece(move.getPiece());
                                    hold.setCapturePiece(board[hold.getMoveX()][hold.getMoveY()]);
                                    if(hold.getPiece().validateMove(hold, board)){
                                        validMoves.add(hold, hold.getCapturePiece().getValue());
                                        numValidMoves++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        randomNumber = (int)(Math.random() * numValidMoves);
        Move toReturn = validMoves.search(randomNumber);

        // Search through pieces and update it
        for(int i = 0; i < numPieces; i++){
            if(pieces[i].equals(toReturn)){
                pieces[i].setPieceX(toReturn.getMoveX());
                pieces[i].setPieceY(toReturn.getMoveY());
            }
        }

        return toReturn;
    }

    // This method finds the piece that was just captured and removes it from the current computer pieces on the board.
    //
    // PARAMETERS:
    //    firstParameter: The move parameter is actually the piece that was just captured that we want to remove 
    //                    from the current computer pieces on the board.
    public void removePiece(Move move){
        for(int i = 0; i < numPieces; i++){
            if(pieces[i].getPieceX() == move.getMoveX() && pieces[i].getPieceY() == move.getMoveY()){
                for(int j = i; j < numPieces - 1; j++){
                    pieces[j] = pieces[j + 1];
                }
            }
            
        }
    }

    public void pawnPromotion(Piece[][] board, Move move){
        System.out.printf("Pawn at (%d,%d) promoted to Queen.\n", move.getMoveX(), move.getMoveY());
        move.setPiece(new Queen(1));
        board[move.getMoveX()][move.getMoveY()] = move.getPiece();
    }
}