/**
 * PieceImageManager
 *
 * This class is used to determine what piece to place in what spot
*/

import java.util.HashMap;

public class PieceImageManager {
    /*
     * Used to manage pieces in a hashmap so I can just grab them and update them as the game goes on
     */
    private static final HashMap<String, String> images = new HashMap<>();

    static {
        images.put("black_bishop", "img/black_bishop.png");
        images.put("black_king", "img/black_king.png");
        images.put("black_knight", "img/black_knight.png");
        images.put("black_pawn", "img/black_pawn.png");
        images.put("black_queen", "img/black_queen.png");
        images.put("black_rook", "img/black_rook.png");
        images.put("white_pawn", "img/white_pawn.png");
        images.put("white_bishop", "img/white_bishop.png");
        images.put("white_king", "img/white_king.png");
        images.put("white_knight", "img/white_knight.png");
        images.put("white_queen", "img/white_queen.png");
        images.put("white_rook", "img/white_rook.png");
    }

    public static String getPieceImage(Piece piece) {
        if (piece instanceof Empty)
            return "img/empty.png";
        int team = piece.getTeam();
        String teamColour;
        teamColour = (team == 0) ? "white" : "black"; // Team colour will be white for player

        return images.get(teamColour + "_" + piece.getType()); // example: "white_queen"
    }
}
