/**
 * Created by Tarek Sanger on 2017-03-10.
 * Student Number: 101059686
 */

/* References:
'COMP1006 - Assignment #7' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FloorBuilderApp extends Application {
    private static final byte wallButton = 1;
    private static final byte exitButton = 2;
    private static final byte roomTilesButton = 3;
    private static final byte selectRoomButton = 4;

    private Building model;
    private FloorBuilderView view;
    private byte actionSelected;

    public void start(Stage primaryStage) {
        model = Building.example();
        view = new FloorBuilderView(model);

        view.getColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleColorButtonChange(actionEvent);
            }
        });

        for (int i = 0; i < 4; i++)
            view.getRadioButton(i).setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {
                    handleRadioButtonSelection(actionEvent);
                }
            });

        for (int row = 0; row < model.getFloorPlan(0).size(); row++)
            for (int col = 0; col < model.getFloorPlan(0).size(); col++) {
                view.getFloorPlanButton(row, col).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridButtonSelection(mouseEvent);
                    }
                });
            }
        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setMinWidth(470);
        primaryStage.setMinHeight(380);
        primaryStage.setScene(new Scene(view, 470, 345));
        primaryStage.show();
        view.update();
    }

    private void handleColorButtonChange(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(view.getColorButton())) {
            view.setColorIndex();
            view.update();
        }
    }

    private void handleRadioButtonSelection(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(view.getRadioButton(0))) {
            actionSelected = wallButton;
            if (view.getTileColorActive())
                view.setTileColorActive(false);
            view.update();
        } else if (actionEvent.getSource().equals(view.getRadioButton(1))) {
            actionSelected = exitButton;
            if (view.getTileColorActive())
                view.setTileColorActive(false);
            view.update();
        } else if (actionEvent.getSource().equals(view.getRadioButton(2))) {
            actionSelected = roomTilesButton;
            view.setTileColorActive(true);
            view.update();
        } else if (actionEvent.getSource().equals(view.getRadioButton(3))) {
            actionSelected = selectRoomButton;
            if (view.getTileColorActive())
                view.setTileColorActive(false);
        }
    }

    private void handleGridButtonSelection(MouseEvent mouseEvent) {
        outerLoop:
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                if (mouseEvent.getSource().equals(view.getFloorPlanButton(row, col))) {
                    if (model.getFloorPlan(0).wallAt(row, col)) {                             // Wall Tiles
                        if (actionSelected == wallButton) {
                            model.getFloorPlan(0).setWallAt(row, col, false);
                        } else if (actionSelected == exitButton) {
                            model.getFloorPlan(0).setWallAt(row, col, false);
                            model.addExit(row, col);
                        }
                        view.update();
                        break outerLoop;
                    } else if (model.hasExitAt(row, col)) {                                     // Exit Tiles
                        if (actionSelected == wallButton) {
                            model.removeExit(row, col);
                            view.getFloorPlanButton(row, col).setText("");
                            view.getFloorPlanButton(row, col).setStyle("-fx-base: BLACK");
                            model.getFloorPlan(0).setWallAt(row, col, true);
                        } else if (actionSelected == exitButton) {
                            model.removeExit(row, col);
                        }
                        view.update();
                        break outerLoop;
                    } else if (model.getFloorPlan(0).roomAt(row, col) != null) {             // Room Tiles
                        if (actionSelected == wallButton) {
                            model.getFloorPlan(0).setWallAt(row, col, true);
                        } else if (actionSelected == exitButton) {
                            model.getFloorPlan(0).roomAt(row, col).removeTile(row, col);
                            model.addExit(row, col);
                        } else if (actionSelected == roomTilesButton) {
                            if (model.getFloorPlan(0).roomAt(row, col).getColorIndex() == view.getColorIndex()) {
                                model.getFloorPlan(0).roomAt(row, col).removeTile(row, col);
                            } else {
                                if (model.getFloorPlan(0).roomWithColor(view.getColorIndex()) == null) {
                                    model.getFloorPlan(0).roomAt(row, col).removeTile(row, col);
                                    model.getFloorPlan(0).addRoomAt(row, col);
                                    model.getFloorPlan(0).roomAt(row, col).setColorIndex(view.getColorIndex());
                                } else {
                                    model.getFloorPlan(0).roomAt(row, col).removeTile(row, col);
                                    model.getFloorPlan(0).roomWithColor(view.getColorIndex()).addTile(row, col);
                                }
                            }
                        }
                        view.removeEmptyRooms();
                        view.update();
                        break outerLoop;
                    } else {   // < Untagged tile >                                                   // Untagged Tile
                        if (actionSelected == wallButton) {
                            model.getFloorPlan(0).setWallAt(row, col, true);
                        } else if (actionSelected == exitButton) {
                            model.addExit(row, col);
                        } else if (actionSelected == roomTilesButton) {
                            if (model.getFloorPlan(0).roomWithColor(view.getColorIndex()) == null) {
                                model.getFloorPlan(0).addRoomAt(row, col);
                                model.getFloorPlan(0).roomAt(row, col).setColorIndex(view.getColorIndex());
                            } else {
                                model.getFloorPlan(0).roomWithColor(view.getColorIndex()).addTile(row, col);

                            }
                        }
                        view.update();
                        break outerLoop;
                    }
                }
            }
        }
    }

    public static void main(String[] args) { launch(args); }
}