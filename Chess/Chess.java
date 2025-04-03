import javax.swing.*;

public class Chess {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800); // square window
            frame.setResizable(false);
            frame.add(new ChessBoardPanel());
            frame.setVisible(true);
        });
    }
}
