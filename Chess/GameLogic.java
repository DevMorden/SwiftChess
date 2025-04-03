/**
 * GameLogic
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the ChessController interface and controlsa all the game logic
*/

public class GameLogic implements ChessController{
    private final int BOARD_SIZE = 8;
    private GameDisplay display;
    private Piece[][] board;
    private ChessPlayer computer;

    public GameLogic(GameDisplay initDisplay){
        display = initDisplay;
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        reset();
    }

    // This method verifies if a move is valid. If it is, it will update the board appropriately. It first 
    // checks if it's in the bounds of the board, and then checks if the piece we're controlling is ours and 
    // if the piece we're moving onto is the other team"s piece. It also checks for pawn promotion if pawn 
    // reaches the other side of the board.
    //
    // PARAMETERS:
    //    firstParameter: The move parameter is the move we want to test and perform.
    //    secondParameter: The team parameter is the number indicating what team we're performing the move 
    //                     on (0 is player and 1 is computer).
    //    
    // RETURNS:
    //    This method returns true if the move was valid and returns false if the move was not valid.
    public boolean movePiece(Move move, int team){
        if(move.getPieceX() < 0 && move.getPieceX() > 7 || move.getPieceY() < 0 || move.getPieceY() > 7 || move.getMoveX() < 0 || move.getMoveX() > 7 
        || move.getMoveY() < 0 || move.getMoveY() > 7){
            display.displayInvalidMove();
            return false;
        }else if(board[move.getPieceX()][move.getPieceY()].getTeam() != team || board[move.getMoveX()][move.getMoveY()].getTeam() == team){
            display.displayInvalidMove();
            return false;
        }else{
            if(!board[move.getPieceX()][move.getPieceY()].validateMove(move, board)){
                display.displayInvalidMove();
                return false;
            }else{
                // Move is valid and can now perform the move
                move.setPiece(board[move.getPieceX()][move.getPieceY()]);
                move.setCapturePiece(board[move.getMoveX()][move.getMoveY()]);

                // Deleting piece from computer if it's a capture
                if(!(board[move.getMoveX()][move.getMoveY()] instanceof Empty)){
                    computer.removePiece(move);
                }

                board[move.getMoveX()][move.getMoveY()] = board[move.getPieceX()][move.getPieceY()];
                board[move.getPieceX()][move.getPieceY()] = new Empty(-1);

                // Check for pawn promotion
                if(board[move.getMoveX()][move.getMoveY()] instanceof Pawn){
                    if(team == 0){
                        if(move.getMoveY() == 0){
                            int input = display.promptPawnPromotion();
                            if(input == 0){ // Rook
                                board[move.getMoveX()][move.getMoveY()] = new Rook(0);
                            }else if(input == 1){ // Knight
                                board[move.getMoveX()][move.getMoveY()] = new Knight(0);
                            }else if(input == 2){ // Bishop
                                board[move.getMoveX()][move.getMoveY()] = new Bishop(0);
                            }else if(input == 3){ // Queen
                                board[move.getMoveX()][move.getMoveY()] = new Queen(0);
                            }
                        }
                    }else if(team == 1){
                        if(move.getMoveY() == 7){
                            computer.pawnPromotion(board, move);
                        }
                    }

                }

                return true;
            }

        }
    }
    
    // This method resets the board where the players pieces are at the bottom and the computer players are 
    // at the top.
    public void reset(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    if(j == 0 || j == 7){
                        board[j][i] = new Rook(1);
                    }else if(j == 1 || j == 6){
                        board[j][i] = new Knight(1);
                    }else if(j == 2 || j == 5){
                        board[j][i] = new Bishop(1);
                    }else if(j == 3){
                        board[j][i] = new King(1);
                    }else if(j == 4){
                        board[j][i] = new Queen(1);
                    }
                }else if(i == 1){
                    board[j][i] = new Pawn(1);
                }else if(i == 7){
                    if(j == 0 || j == 7){
                        board[j][i] = new Rook(0);
                    }else if(j == 1 || j == 6){
                        board[j][i] = new Knight(0);
                    }else if(j == 2 || j == 5){
                        board[j][i] = new Bishop(0);
                    }else if(j == 3){
                        board[j][i] = new King(0);
                    }else if(j == 4){
                        board[j][i] = new Queen(0);
                    }
                }else if(i == 6){
                    board[j][i] = new Pawn(0);
                }else{
                    board[j][i] = new Empty(-1);
                }
            }
        }
    }

    // This method runs the loop for the game. When you start a game, it will ask you for the difficulty of 
    // the computer. When you forefeit the game, it will ask you if you want to play again. It also checks 
    // when the king of either player has been captured.
    public void playGame(){
        boolean playAgain = true;
        Move move;
        
        while(playAgain){
            reset();
            boolean run = true;
            int difficulty = display.promptForOpponentDifficulty(1);
            if(difficulty == 0){
                computer = new EasyAI(board);
            }else if(difficulty == 1){
                computer = new HardAI(board);
            }
            
            display.displayBoard(board);
    
            while(run){           
                // Player turn
                do{
                    move = display.promptForMove();
    
                    if(move == null){
                        run = false;
                        display.gameOver(3);
                        break; // This will skip asking for a valid move
                    }
                }while(!movePiece(move, 0));
    
                if(run){
                    display.displayBoard(board);
                    display.summarizeMove(move);
    
                    // Check for king capture
                    if(move.getCapturePiece() instanceof King){
                        run = false;
                        display.gameOver(1);
                    }
        
                    if(run){
                        // Computer turn
                        move = computer.makeMove(null, board);
                        movePiece(move, 1);
        
                        display.displayBoard(board);
                        display.summarizeMove(move);
        
                        if(move.getCapturePiece() instanceof King){
                            run = false;
                            display.gameOver(2);
                        }
                    }
                }
    
                if(run == false){
                    if(!display.promptPlayAgain()){
                        playAgain = false;
                    }
                }
            }
        }
    }
}