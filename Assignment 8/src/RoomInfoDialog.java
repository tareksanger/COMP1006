/**
 * Created by Tarek Sanger on 2017-03-17.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #8' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
    * Used Mark Lanthier Solutions for Assignment 7 *
*/
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RoomInfoDialog extends Dialog {
    public static final String[]    ROOM_COLORS = {"ORANGE", "YELLOW", "LIGHTGREEN", "DARKGREEN",
            "LIGHTBLUE", "BLUE", "CYAN", "DARKCYAN", "PINK", "DARKRED", "PURPLE", "GRAY"};

    public RoomInfoDialog(Stage Owner, String title, Building model, Room room, int currentFloor){
        setTitle(title);

        // Set the Button Types
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

       // Set all the details
        GridPane dialogBox = new GridPane();
        dialogBox.setHgap(10);
        dialogBox.setVgap(10);
        dialogBox.setMaxWidth(310);
        dialogBox.setPadding(new Insets(10,10,10,10));

        // Create labels
        Label aLabel;
        aLabel = new Label("Occupant:");
        dialogBox.add(aLabel, 0,0);
        
        aLabel = new Label("Position:");
        dialogBox.add(aLabel, 0,1);

        aLabel = new Label("Number:");
        dialogBox.add(aLabel, 0,2);

        aLabel = new Label("Floor:");
        dialogBox.add(aLabel, 0,3);

        aLabel = new Label("Size:");
        dialogBox.add(aLabel, 0,4);

        // Create TextFields
        TextField occupantField = new TextField(room.getOccupant());
        dialogBox.add(occupantField, 1,0);
        occupantField.setPromptText("Person who occupies this room");
        occupantField.setMaxWidth(300);

        TextField positionField = new TextField(room.getPosition());
        dialogBox.add(positionField,1,1);
        positionField.setPromptText("Job position/title of this person");
        positionField.setMaxWidth(300);

        TextField floorField = new TextField(model.getFloorPlan(currentFloor).getName());
        dialogBox.add(floorField, 1,3);
        floorField.setMaxWidth(300);
        floorField.setEditable(false);


        TextField sizeField = new TextField(room.getNumberOfTiles() + " Sq. Ft.");
        dialogBox.add(sizeField, 1,4);
        floorField.setMaxWidth(300);
        sizeField.setEditable(false);

        // Number Text Field and Color Button
        HBox numColBox = new HBox();
        numColBox.setSpacing(10);
        numColBox.setMinWidth(300);

        TextField numberField = new TextField(room.getNumber());
        numColBox.getChildren().add(numberField);
        numberField.setMaxWidth(145);
        numberField.setPromptText("The room number");

        Button colorButton = new Button();
        numColBox.getChildren().add(colorButton);
        colorButton.setStyle("-fx-base: " + ROOM_COLORS[room.getColorIndex()] + ";");
        colorButton.setFocusTraversable(false);
        colorButton.setMinWidth(145);
        dialogBox.add(numColBox,1,2);

        getDialogPane().setContent(dialogBox);

        Node okButton = getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);
        occupantField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                okButton.setDisable(occupantField.getText().trim().isEmpty()
                                || positionField.getText().trim().isEmpty()
                                || numberField.getText().trim().isEmpty());
            }
        });
        positionField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                okButton.setDisable(occupantField.getText().trim().isEmpty()
                        || positionField.getText().trim().isEmpty()
                        || numberField.getText().trim().isEmpty());
            }
        });
        numberField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                okButton.setDisable(occupantField.getText().trim().isEmpty()
                        || positionField.getText().trim().isEmpty()
                        || numberField.getText().trim().isEmpty());
            }
        });

        setResultConverter(b -> {
            if (b == okButtonType) {
                room.setOccupant(occupantField.getText().trim());
                room.setPosition(positionField.getText().trim());
                room.setNumber(numberField.getText().trim());
                return model;
            }
            return null;
        });
    }
}
