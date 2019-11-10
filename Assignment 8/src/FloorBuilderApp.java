/**
 * Created by Tarek Sanger on 2017-03-16.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #8' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
    * Used Mark Lanthier Solutions for Assignment 7 *
*/
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class FloorBuilderApp extends Application  {
    private FloorBuilderView   view;
    private Building           model;
    private int                currentFloor;    // Index of the floor being displayed
    private int                currentColor;    // Index of the current room color

    public void start(Stage primaryStage) {
        model = Building.example();
        currentFloor = 0;
        currentColor = 0;

        // Create Menus
        Menu selectFloor = new Menu ("_Select Floor");

        // Create new MenuBar, add menu to menuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(selectFloor);

        MenuItem floorOne = new MenuItem(model.getFloorPlan(0).getName());
        MenuItem floorTwo = new MenuItem(model.getFloorPlan(1).getName());
        MenuItem floorThree = new MenuItem(model.getFloorPlan(2).getName());
        MenuItem floorFour = new MenuItem(model.getFloorPlan(3).getName());
        MenuItem floorFive = new MenuItem(model.getFloorPlan(4).getName());

        selectFloor.getItems().addAll(floorFour, floorThree, floorTwo, floorOne, new SeparatorMenuItem(), floorFive);


        VBox aPane = new VBox();
        view = new FloorBuilderView(model);
        view.setPrefWidth(Integer.MAX_VALUE);
        view.setPrefHeight(Integer.MAX_VALUE);

        aPane.getChildren().addAll(menuBar, view);
        primaryStage.setTitle("Floor Plan Builder");
        primaryStage.setMinWidth(370);
        primaryStage.setMinHeight(350);
        primaryStage.setScene(new Scene(aPane, 370,350));
        primaryStage.show();

        // MenuBar handlers
        floorOne.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentFloor = 0;
                view.update(currentFloor, currentColor);
            }});
        floorTwo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentFloor = 1;
                view.update(currentFloor, currentColor);
            }});
        floorThree.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentFloor = 2;
                view.update(currentFloor, currentColor);
            }});
        floorFour.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentFloor = 3;
                view.update(currentFloor, currentColor);
            }});
        floorFive.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentFloor = 4;
                view.update(currentFloor, currentColor);
            }});

        // Plug in the floor panel event handlers:
        for (int r=0; r<model.getFloorPlan(0).size(); r++) {
            for (int c=0; c<model.getFloorPlan(0).size(); c++) {
                view.getFloorTileButton(r, c).setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                            handleTileSelection(event, primaryStage);

                    }});
            }
        }

        // Plug in the radioButton event handlers
        view.getEditWallsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getSelectExitsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getEditRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});
        view.getDefineRoomsButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.update(currentFloor, currentColor);
            }});

        // Plug in the office color button
        view.getRoomColorButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                currentColor = (currentColor + 1)%view.ROOM_COLORS.length;
                view.update(currentFloor, currentColor);
            }});


        view.getBuildingOverviewButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Dialog dialog = new BuildingDialog(primaryStage, "Building Overview",model);
                dialog.showAndWait();
            }
        });

        view.update(currentFloor, currentColor);
    }



    // Handle a Floor Tile Selection
    private void handleTileSelection(ActionEvent e, Stage owner) {
        // Determine which row and column was selected
        int r=0, c=0;
        OUTER:
        for (r=0; r<model.getFloorPlan(0).size(); r++) {
            for (c=0; c<model.getFloorPlan(0).size(); c++) {
                if (e.getSource() == view.getFloorTileButton(r, c))
                    break OUTER;
            }
        }
        // Check if we are in edit wall mode, then toggle the wall
        if (view.getEditWallsButton().isSelected()) {
            model.getFloorPlan(currentFloor).setWallAt(r, c, !model.getFloorPlan(currentFloor).wallAt(r, c));
            // Remove this tile from the room if it is on one, because it is now a wall
            Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
            if (room != null)
                room.removeTile(r, c);
        }

        // Otherwise check if we are in edit exits mode
        else if (view.getSelectExitsButton().isSelected()) {
            if (model.exitAt(r, c) != null)
                model.removeExit(r, c);
            else {
                model.addExit(r, c);
                // Remove this tile from the room if it is on one, because it is now an exit
                Room off = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (off != null)
                    off.removeTile(r, c);
            }
        }
        // Otherwise check if we are selecting a room tile
        else if (view.getEditRoomsButton().isSelected()) {
            if (!model.getFloorPlan(currentFloor).wallAt(r, c) && !model.hasExitAt(r, c)) {
                Room room = model.getFloorPlan(currentFloor).roomAt(r, c);
                if (room != null) {
                    room.removeTile(r, c);
                    if (room.getNumberOfTiles() == 0)
                        model.getFloorPlan(currentFloor).removeRoom(room);
                }
                else {
                    room = model.getFloorPlan(currentFloor).roomWithColor(currentColor);
                    if (room == null) {
                        room = model.getFloorPlan(currentFloor).addRoomAt(r, c);
                        room.setColorIndex(currentColor);
                    }
                    else {
                        room.addTile(r, c);
                    }
                }
            }
        } else if (view.getDefineRoomsButton().isSelected()) {
            if(model.getFloorPlan(currentFloor).roomAt(r,c) != null) {
                Dialog dialog = new RoomInfoDialog(owner, "Room Details", model,model.getFloorPlan(currentFloor).roomWithColor(model.getFloorPlan(currentFloor).roomAt(r,c).getColorIndex()), currentFloor);

                Optional result = dialog.showAndWait();
                if(result.isPresent())
                    view.update(currentFloor, currentColor);

            } else {
                // Create Define Room Alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText(null);
                alert.setContentText("You must select a tile that belongs to a room.");
                alert.showAndWait();

            }

        } else {

        }
        view.update(currentFloor, currentColor);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

/*
if (view.getDefineRoomsButton().isSelected()) {
            if(model.getFloorPlan(currentFloor).roomAt(r,c).contains(r,c)) {
                Dialog dialog = new RoomInfoDialog("Room Details", model, currentFloor,r,c);

                Optional<Building> result = dialog.showAndWait();
                if(result.isPresent());
                    view.update(currentFloor, currentColor);

            }else {
                // Create Define Room Alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText(null);
                alert.setContentText("You must select a tile that belongs to a room.");
                alert.show();

            }
 */