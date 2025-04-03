import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoardPanel extends JPanel {

    private final int TILE_SIZE = 100;
    private final int BOARD_SIZE = 8;

    public ChessBoardPanel() {
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = e.getY() / TILE_SIZE;
                int col = e.getX() / TILE_SIZE;
                System.out.println("Clicked on: " + row + ", " + col);
                // Call your game logic here, like: game.handleClick(row, col);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? Color.decode("#f0d9b5") : Color.decode("#b58863"));
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // TODO: Draw pieces here using game state
    }
}
