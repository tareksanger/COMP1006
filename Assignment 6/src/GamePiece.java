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

public abstract class GamePiece {
    protected int      width;
    protected int      height;
    protected Color    color;
    protected int      topLeftX;
    protected int      topLeftY;

    public GamePiece(int w, int h, Color c, int x, int y) {
        width = w;
        height = h;
        color = c;
        topLeftX = x;
        topLeftY = y;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }
    public int getTopLeftX() { return topLeftX; }
    public int getTopLeftY() { return topLeftY; }

    public void moveRight() { topLeftX += 1;}
    public void moveLeft() { topLeftX -= 1;}
    public void moveDown() { topLeftY += 1;}
    public void moveUp() { topLeftY -= 1;}

    public boolean canMoveLeftIn(GameBoard b) { return false; }
    public boolean canMoveRightIn(GameBoard b) { return false; }
    public boolean canMoveDownIn(GameBoard b) { return false; }
    public boolean canMoveUpIn(GameBoard b) { return false; }
}
