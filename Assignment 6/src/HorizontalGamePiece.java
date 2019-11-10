/**
 * Created by Tarek Sanger on 2017-03-04.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #6' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
            "Some Code Used from Assignment 6 Specifications"
*/
import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }

    public boolean canMoveLeftIn(GameBoard b) {
        if (topLeftX > 0) {
            for (GamePiece p : b.getGamePieces()) {
                if (p instanceof VerticalGamePiece && topLeftX == (p.getTopLeftX() + p.getWidth()) && topLeftY >= p.getTopLeftY()
                            && topLeftY < (p.getTopLeftY() + p.getHeight())) {
                        return false;
                } else if(p instanceof HorizontalGamePiece && topLeftY == p.getTopLeftY() && p.getTopLeftX() < topLeftX
                        && (p.getTopLeftX() + p.getWidth()) == topLeftX)
                        return false;
            } return true;
        } else
            return false;
    }
    public boolean canMoveRightIn(GameBoard b) {
        if((topLeftX + width) < 6) {
            for (GamePiece p : b.getGamePieces()) {
                if (p instanceof VerticalGamePiece && (topLeftX + width) == p.getTopLeftX()
                        && topLeftY >= p.getTopLeftY() && topLeftY < (p.getTopLeftY() + p.getHeight())) {
                        return false;
                } else if (p instanceof HorizontalGamePiece && topLeftY == p.getTopLeftY() && p.getTopLeftX() > topLeftY
                        && (topLeftX + width) == p.getTopLeftX())
                    return false;
            }
            return true;
        } else
            return false;
    }
}
