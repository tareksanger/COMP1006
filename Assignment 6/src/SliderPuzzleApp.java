/**
 * Created by Tarek Sanger on 2017-03-04.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #6' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
            "Some Code Used from Assignment 6 Specifications"
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame    model;
    private SliderPuzzleView    view;

    private GamePiece           selectedPiece;
    private boolean             justGrabbed;
    private int                 lastX;
    private int                 lastY;

    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);

        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionMove(mouseEvent);
                    }
                });
            }
        }

        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });

        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.WIDTH+2),55+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.HEIGHT+2)));
        primaryStage.show();

        // Update the view upon startup
        view.update();
    }
    private void handleGridSectionSelection(MouseEvent mouseEvent) {
        for (int x=1; x<7; x++) {
            for (int y=1; y<7; y++) {
                if(mouseEvent.getSource().equals(view.getGridSection(x, y))) {
                    selectedPiece = model.getCurrentBoard().pieceAt(x-1, y-1);
                    if(selectedPiece != null)
                        justGrabbed = true;
                    lastX = (int)mouseEvent.getX();
                    lastY = (int)mouseEvent.getY();
                }
            }
        }
    }
    private void handleGridSectionMove(MouseEvent mouseEvent) {
        int currentGridX = (int)mouseEvent.getX();
        int currentGridY = (int)mouseEvent.getY();

        if(justGrabbed) {
            if ((Math.abs(currentGridY) - lastY) > 15) {
                if (selectedPiece instanceof VerticalGamePiece) {
                    if (((currentGridY - lastY) <= -40) && selectedPiece.canMoveUpIn(model.getCurrentBoard())) {
                        model.getCurrentBoard().pieceAt(selectedPiece.topLeftX, selectedPiece.topLeftY).moveUp();
                        model.makeAMove();
                        view.update();
                        justGrabbed = false;


                    } else if (((currentGridY - lastY) >= 40) && selectedPiece.canMoveDownIn(model.getCurrentBoard())){
                        model.getCurrentBoard().pieceAt(selectedPiece.topLeftX, selectedPiece.topLeftY).moveDown();
                        model.makeAMove();
                        view.update();
                        justGrabbed = false;

                    }
                }
            } else if ((Math.abs(currentGridX) - lastX) > 15 ){
                if (selectedPiece instanceof HorizontalGamePiece) {
                    if (((currentGridX - lastX) <= -40) && selectedPiece.canMoveLeftIn(model.getCurrentBoard())) {
                            model.getCurrentBoard().pieceAt(selectedPiece.topLeftX, selectedPiece.topLeftY).moveLeft();
                            model.makeAMove();
                            view.update();
                            justGrabbed = false;
                    } else if (((currentGridX - lastX) >= 40) && selectedPiece.canMoveRightIn(model.getCurrentBoard()) ) {
                        model.getCurrentBoard().pieceAt(selectedPiece.topLeftX, selectedPiece.topLeftY).moveRight();
                        model.makeAMove();
                        if(selectedPiece instanceof GoalPiece) {
                            model.getCurrentBoard().checkCompletion(selectedPiece);
                            if(model.getCurrentBoard().isCompleted()){
                                model.completeBoard();
                                view.update();
                            }
                        }
                        view.update();
                        justGrabbed = false;
                    }
                }
            }
        }
    }
    public static void main(String[] args) { launch(args); }
}