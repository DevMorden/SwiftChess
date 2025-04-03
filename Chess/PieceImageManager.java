import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class PieceImageManager {
    /*
     * Used to manage pieces in a hashmap so I can just grab them and update them as the game goes on
     */
    private static final HashMap<String, BufferedImage> images = new HashMap<>();

    static {
        try {
            images.put("black_bishop", ImageIO.read(PieceImageManager.class.getResource("/img/black_bishop.png")));
            images.put("black_king", ImageIO.read(PieceImageManager.class.getResource("/img/black_king.png")));
            images.put("black_knight", ImageIO.read(PieceImageManager.class.getResource("/img/black_knight.png")));
            images.put("black_pawn", ImageIO.read(PieceImageManager.class.getResource("/img/black_pawn.png")));
            images.put("black_queen", ImageIO.read(PieceImageManager.class.getResource("/img/black_queen.png")));
            images.put("black_rook", ImageIO.read(PieceImageManager.class.getResource("/img/black_rook.png")));
            images.put("white_pawn", ImageIO.read(PieceImageManager.class.getResource("/img/white_pawn.png")));
            images.put("white_bishop", ImageIO.read(PieceImageManager.class.getResource("/img/white_bishop.png")));
            images.put("white_king", ImageIO.read(PieceImageManager.class.getResource("/img/white_king.png")));
            images.put("white_knight", ImageIO.read(PieceImageManager.class.getResource("/img/white_knight.png")));
            images.put("white_queen", ImageIO.read(PieceImageManager.class.getResource("/img/white_queen.png")));
            images.put("white_rook", ImageIO.read(PieceImageManager.class.getResource("/img/white_rook.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getPieceImage(Piece piece) {
        if (piece instanceof Empty) return null;
        int team = piece.getTeam();
        String teamColour;
        teamColour = (team == 0) ? "white" : "black"; // Team colour will be white for player

        return images.get(teamColour + "_" + piece.getType()); // example: "white_queen"
    }
}
