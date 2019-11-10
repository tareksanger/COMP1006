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

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
        if((topLeftY + height) < 6) {
            for(GamePiece p : b.getGamePieces()) {
                if (p instanceof HorizontalGamePiece && (topLeftY + height) == p.getTopLeftY()
                        && topLeftX >= p.getTopLeftX() && topLeftX < (p.getTopLeftX() + p.getWidth())) {
                    return false;
                } else if (p instanceof VerticalGamePiece && topLeftX == p.getTopLeftX()
                        && (topLeftY + height) == p.getTopLeftY())
                    return  false;
            } return true;
        } else
            return false;
    }
    public boolean canMoveUpIn(GameBoard b) {
        if(topLeftY > 0) {
            for(GamePiece p : b.getGamePieces()) {
                if (p instanceof HorizontalGamePiece && topLeftY == (p.getTopLeftY() + p.getHeight())
                        && topLeftX >= p.getTopLeftX() && topLeftX < (p.getTopLeftX() + p.getWidth())) {
                    return false;
                } else if (p instanceof VerticalGamePiece && topLeftX == p.getTopLeftX()
                        && topLeftY == (p.getTopLeftY() + p.getHeight()))
                    return false;
            }
            return true;
        } else
            return false;
    }
}