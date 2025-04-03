/**
 * TextGameDisplay
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class implements the GameDisplay interface and displays the game all through the console
*/

import java.util.Scanner;

public class TextGameDisplay implements GameDisplay{
    private Scanner scanner;

    public TextGameDisplay(){
        scanner = new Scanner(System.in);
    }

    public void displayInvalidMove(){
        System.out.println("The move you have entered is invalid. Please enter another move.");
    }

    public int promptForOpponentDifficulty(int maxDifficulty){
        while(true){
            try{
                System.out.println("Please enter the desired opponent difficulty, between 0 and " + maxDifficulty + ", where 0 is easiest opponent and " + maxDifficulty + " is hardest opponent.");
                int input = Integer.parseInt(scanner.nextLine());
                if(input <= maxDifficulty){
                    return input;
                }
            }catch(NumberFormatException e){
                // Catch exception and try again
            }
        }
    }

    public Move promptForMove(){
        int pieceX = 0;
        int pieceY = 0;
        int moveX = 0;
        int moveY = 0;
        int input = -1;

        try{
            System.out.println("Please enter the row of the piece you would like to move. Enter 0 to forfeit game.");
            input = Integer.parseInt(scanner.nextLine());
            if(input == 0){
                return null;
            }
            pieceY = input - 1;

            System.out.println("Please enter the column of the piece you would like to move.");
            input = Integer.parseInt(scanner.nextLine());
            pieceX = input - 1;

            System.out.println("Please enter the row of the destination.");
            input = Integer.parseInt(scanner.nextLine());
            moveY = input - 1;

            System.out.println("Please enter the column of the destination.");
            input = Integer.parseInt(scanner.nextLine());
            moveX = input - 1;

            return new Move(pieceX, pieceY, moveX, moveY);
        }catch(NumberFormatException e){
            return new Move(1,1,1,1); // CHANGE
        }
        
    }

    public void displayBoard(Piece[][] board){
        int counterA = 1; // Used for indices
        int counterB = 1; // Also used for indices
        int x = 0; // To place pieces
        int y = 0; // To place pieces

        System.out.println();
        for(int i = 0; i < 18; i++){
            for(int j = 0; j < 18; j++){
                if(i == 0){
                    if(j % 2 == 0){ // Even
                        if(j == 0){
                            System.out.print(" ");
                        }else{
                            System.out.print(counterA);
                            counterA++;
                        }
                    }else{ // Odd
                        System.out.print(" ");
                    }
                }else{
                    if(i % 2 == 0){
                        if(j == 0){
                            System.out.print(counterB);
                            counterB++;
                        }else{
                            if(j % 2 == 0){
                                board[y][x].print();
                                y++;
                            }else{
                                System.out.print("|");
                            }
                        }
                    }else{
                        System.out.print("-");
                    }
                }
            }

            System.out.println();
            y = 0;
            if(i % 2 == 0 && i != 0){
                x++;
            }
        }
    }

    public void summarizeMove(Move move){
        System.out.printf("%s moved from (%d, %d) to (%d, %d). %s\n", move.getPiece().getName(), move.getPieceX() + 1, move.getPieceY() + 1, move.getMoveX() + 1, 
        move.getMoveY() + 1, move.getCapturePiece().getCaptureLine());
    }

    public void gameOver(int winner){
        // 1 means player has won
        // 2 means computer has won
        // 3 means player has forfeit
        if(winner == 1){
            System.out.println("You won! Game over!");
        }else if(winner == 2){
            System.out.println("Computer has won. Game over!");
        }else if(winner == 3){
            System.out.println("You forfeit. Game over!");
        }
    }

    public boolean promptPlayAgain(){
        int input = -1;

        while(true){
            System.out.println("Would you like to play again? Please enter 0 for yes or 1 for no.");

            try{
                input = Integer.parseInt(scanner.nextLine());
                if(input == 0){
                    return true;
                }else if(input == 1){
                    // scanner.close();
                    return false;
                }
            }catch(NumberFormatException e){
                // Catch exception and try again
            }
        }
    }

    public int promptPawnPromotion(){
        int input;
        while(true){
            try{
                System.out.println("Pawn has reached other side. What piece would you like to promote it to? Please enter 0 for rook, 1 for knight, 2 for bishop, and 3 for queen.");
                input = Integer.parseInt(scanner.nextLine());
                if(input >= 0 && input <= 3){
                    return input;
                }
            }catch(NumberFormatException e){
                // Catch exception and try again
            }
        }
    }
}