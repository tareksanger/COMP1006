/**
 * Created by Tarek Sanger on 2017-03-18.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #8' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
    * Used Mark Lanthier Solutions for Assignment 7 *
*/
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuildingDialog extends Dialog {

    public BuildingDialog(Stage owner, String title, Building model) {
        setTitle(title);

        // Set the Button Types
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(okButtonType);

        // Set all the details
        GridPane dialogBox = new GridPane();
        dialogBox.setHgap(10);
        dialogBox.setVgap(10);
        dialogBox.setMaxWidth(150);
        dialogBox.setPadding(new Insets(10,10,10,10));

        // Labels
        Label aLabel;
        aLabel = new Label("Num Floors");
        dialogBox.add(aLabel, 0,0);

        aLabel = new Label("Num Exits:");
        dialogBox.add(aLabel, 0,1);

        aLabel = new Label("Num Rooms:");
        dialogBox.add(aLabel, 0,2);

        aLabel = new Label("Total Size:");
        dialogBox.add(aLabel, 0,3);

        // TextFields
        TextField numFloorsField = new TextField(String.valueOf(model.getFloorPlans().length));
        dialogBox.add(numFloorsField,1,0);
        numFloorsField.setMaxWidth(100);
        numFloorsField.setEditable(false);
        numFloorsField.setFocusTraversable(false);


        TextField numExitsField = new TextField(String.valueOf(model.getExits().length));
        dialogBox.add(numExitsField,1,1);
        numExitsField.setMaxWidth(100);
        numExitsField.setEditable(false);
        numExitsField.setFocusTraversable(false);

        // Calculate number of rooms
        int numRooms = 0;
        for(int i=0; i<model.getFloorPlans().length;i++)
            numRooms += model.getFloorPlan(i).getNumberOfRooms();

        TextField numRoomsField = new TextField(String.valueOf(numRooms));
        dialogBox.add(numRoomsField,1,2);
        numRoomsField.setMaxWidth(100);
        numRoomsField.setEditable(false);
        numRoomsField.setFocusTraversable(false);

        int totalSize = 0;
        for(int i=0; i<model.getFloorPlans().length;i++) {
            for(int r=0; r<model.getFloorPlan(0).size(); r++) {
                for(int c=0; c<model.getFloorPlan(0).size(); c++) {
                    if(!model.getFloorPlan(i).wallAt(r,c) || !model.hasExitAt(r,c))
                        totalSize += 1;
                }
            }
        }
        TextField totalSizeField = new TextField(totalSize + " Sq. Ft.");
        dialogBox.add(totalSizeField,1,3);
        totalSizeField.setMaxWidth(100);
        totalSizeField.setEditable(false);
        totalSizeField.setFocusTraversable(false);

        // Button
        Button directortyListing = new Button("Directory Listing");
        dialogBox.add(directortyListing,0,4,2,4);
        directortyListing.setMinWidth(190);
        getDialogPane().setContent(dialogBox);

        directortyListing.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Dialog dialog = new DirectoryDialog(owner, "Directory Listing", model);
                dialog.showAndWait();
            }
        });

    }
}
