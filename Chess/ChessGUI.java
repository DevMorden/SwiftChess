/*
 * ChessGUI
 * 
 * This contains all the code that runs the GUI for the chess game. It includes starting the game, ending the game, 
 * moving pieces, selecting difficulty. This speaks directly to the GameLogic which controls the game and doens't have to do any of the game logic itself.
 */

package Chess;

import javax.swing.*;
import java.awt.*;

public class ChessGUI implements GameDisplay{
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JLabel winnerLabel; // Have to update depending on winner
    private Piece[][] currBoard;
    private JButton[][] buttons;
    private JPanel boardPanel;
    private int clickCounter = 0; // Used to check amount of moves
    private final int WIDTH = 800;
    private final int HEIGHT = 900;
    private boolean playAgain = false;
    private boolean endGame = false;

    // For moves
    private int pieceX;
    private int pieceY;
    private int moveX;
    private int moveY;
    private int prevRow = -1;
    private int prevCol = -1;

    // Sync objects for difficulty
    private final Object lock = new Object();
    private int selectedDifficulty;

    public ChessGUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("img/icon.png"));
        frame.setIconImage(image.getImage());
        frame.setResizable(false); // Makes it so people can't mess with size


        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add screens
        mainPanel.add(createStartScreen(), "start");
        mainPanel.add(createGameScreen(), "game");
        mainPanel.add(createEndScreen(), "end");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createStartScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.setBackground(new Color(0xfac89d));
        JLabel title = new JLabel("Java Chess Game");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font(null,Font.BOLD,50));
        JLabel author = new JLabel("Made by: Matthew Morden");
        author.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        author.setFont(new Font(null,Font.ITALIC,15));
        JLabel difficultyPrompt = new JLabel("Choose your opponent's difficulty");
        difficultyPrompt.setFont(new Font(null,Font.ROMAN_BASELINE,15));
        difficultyPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Setting up buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonPanel.setBackground(new Color(0xfac89d));
        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font(null,Font.BOLD,20));
        easyButton.setPreferredSize(new Dimension(100, 40));
        easyButton.setFocusable(false);
        easyButton.setBackground(new Color(0x8be890));
        easyButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x2c9131), 2),BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font(null,Font.BOLD,20));
        hardButton.setPreferredSize(new Dimension(100, 40));
        hardButton.setFocusable(false);
        hardButton.setBackground(new Color(0xf77965));
        hardButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0xa63e2e), 2),BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        buttonPanel.add(easyButton);
        buttonPanel.add(hardButton);

        easyButton.addActionListener(e -> {
            selectedDifficulty = 0;
            synchronized (lock) {
                lock.notify();
            }
        });

        hardButton.addActionListener(e -> {
            selectedDifficulty = 1;
            synchronized (lock) {
                lock.notify();
            }
        });

        panel.add(Box.createVerticalStrut(100));
        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(author);
        panel.add(Box.createVerticalStrut(20));
        panel.add(difficultyPrompt);
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createGameScreen() {
        JPanel gamePanel = new JPanel(); 
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        boardPanel = new JPanel(new GridLayout(8, 8));
        buttons = new JButton[8][8]; 

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(WIDTH/8, (HEIGHT-100)/8));
                button.setFocusPainted(false);

                if ((row + col) % 2 == 0) {
                    button.setBackground(new Color(0xfac89d)); 
                    button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x9e5c23), 2),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                } else {
                    button.setBackground(new Color(0xf0a15d));
                    button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x9e5c23), 2),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                }

                final int r = row, c = col;
                button.addActionListener(e -> handleClick(r, c));
                
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        JPanel bottomPanel = new JPanel();
        bottomPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        bottomPanel.setBounds(0,800,800,100);
        bottomPanel.setBackground(new Color(0xd48039));
        bottomPanel.setLayout(new BorderLayout());
        JButton quitButton = new JButton("End Game");
        quitButton.setFont(new Font(null,Font.BOLD,20));
        // quitButton.setPreferredSize(new Dimension(100, 40));
        quitButton.setFocusable(false);
        quitButton.setBackground(new Color(0xf0a15d));
        quitButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0xd48039), 2),BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        bottomPanel.add(quitButton);

        quitButton.addActionListener(e -> {
            endGame = true;
            synchronized (lock) {
                lock.notify();
            }        
        });

        gamePanel.add(boardPanel); // put the board in the center
        gamePanel.add(bottomPanel);

        return gamePanel;
    }

    private JPanel createEndScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.setBackground(new Color(0xfac89d));
        winnerLabel = new JLabel("PLACEHOLDER");
        winnerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        winnerLabel.setFont(new Font(null,Font.BOLD,50));

        // Setting up buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonPanel.setBackground(new Color(0xfac89d));
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font(null,Font.BOLD,20));
        newGameButton.setPreferredSize(new Dimension(200, 40));
        newGameButton.setFocusable(false);
        newGameButton.setBackground(new Color(0x8be890));
        newGameButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x2c9131), 2),BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(null,Font.BOLD,20));
        quitButton.setPreferredSize(new Dimension(200, 40));
        quitButton.setFocusable(false);
        quitButton.setBackground(new Color(0xf77965));
        quitButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0xa63e2e), 2),BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

        newGameButton.addActionListener(e -> {
            playAgain = true;
            synchronized (lock) {
                lock.notify();
            }
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
            synchronized (lock) {
                lock.notify();
            }
        });

        panel.add(Box.createVerticalStrut(100));
        panel.add(winnerLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    public int startScreen(int maxDifficulty) {
        cardLayout.show(mainPanel, "start");
        synchronized (lock) {
            try {
                lock.wait(); // wait until user clicks Easy or Hard
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cardLayout.show(mainPanel, "game");
        return selectedDifficulty;
    }

    // Handles when piece is clicked
    private void handleClick(int row, int col) {  
        if(!(currBoard[row][col].getTeam() != 0 && clickCounter == 0)){
            if (clickCounter == 0){ // First, choose your piece
                pieceX = col;
                pieceY = row;
                clickCounter++;
                prevCol = col;
                prevRow = row;
                buttons[row][col].setBackground(new Color(0xa86428)); 
                buttons[row][col].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x804716), 2),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            } else if (clickCounter == 1){ // Second, choose where you want to move the piece
                moveX = col;
                moveY = row;
                clickCounter++;
                if ((prevRow + prevCol) % 2 == 0) {
                    buttons[prevRow][prevCol].setBackground(new Color(0xfac89d)); 
                    buttons[prevRow][prevCol].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x9e5c23), 2),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                } else {
                    buttons[prevRow][prevCol].setBackground(new Color(0xf0a15d));
                    buttons[prevRow][prevCol].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0x9e5c23), 2),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                }
            } 
            
            if (clickCounter == 2) { // Perform move and prepare for next move
                clickCounter = 0;
                synchronized (lock) {
                    lock.notify();
                }
            }
        }
    }
    

    public void displayInvalidMove() {
        // Skip
    }

    // This function waits until a complete move is performed (choose piece, choose location)
    public Move promptForMove() {
        synchronized (lock) {
            try {
                lock.wait(); // pause until a piece is clicked
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (endGame) { // Check if the quit button was pressed
            endGame = false;
            return null;
        }

        return new Move(pieceX,pieceY,moveX,moveY);
    }

    public void displayBoard(Piece[][] board) {
        currBoard = board;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                JButton button = buttons[row][col];
                button.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PieceImageManager.getPieceImage(piece))));
            }
        }
    }

    public void summarizeMove(Move move) {
        // Skip
    }

    public void gameOver(int winner) {
        // Change message shown on screen here
        if (winner == 1) {
            winnerLabel.setText("You win!");
        } else if (winner == 2) {
            winnerLabel.setText("You lost!");
        } else {
            winnerLabel.setText("You quit!");
        }
    }

    public boolean promptPlayAgain() {
        cardLayout.show(mainPanel, "end");
        synchronized (lock) {
            try {
                lock.wait(); // wait until user clicks Play Again or quit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        return playAgain;
    }

    public int promptPawnPromotion() {
        String[] options = {"Rook", "Knight", "Bishop","Queen"};
        int choice = JOptionPane.showOptionDialog(null, "Choose a piece:", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return choice;
    }
}

