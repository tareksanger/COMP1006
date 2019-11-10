/**
 * Created by Tarek Sanger on 2017-03-18.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #8' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
    * Used Mark Lanthier Solutions for Assignment 7 *
*/
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class DirectoryDialog extends Dialog {

   public DirectoryDialog(Stage owner, String title ,Building model){
       setTitle(title);

       // Set the Button Types
       ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
       getDialogPane().getButtonTypes().add(okButtonType);

       // Set all the details
       GridPane dialogBox = new GridPane();
       dialogBox.setVgap(10);
       dialogBox.setMinWidth(250);
       dialogBox.setPadding(new Insets(10,10,10,10));

       // Creates ArrayList for ListView
       ArrayList<String> directoryList = new ArrayList<>();
       for(int i=0; i<model.getFloorPlans().length;i++) {
           for (int j = 0; j < 12; j++) {
               if (model.getFloorPlan(i).roomWithColor(j) != null) {
                   Room occupantsRoom = model.getFloorPlan(i).roomWithColor(j);
                   if(occupantsRoom.getNumber() != "")
                       directoryList.add(occupantsRoom.getNumber() + " - " + occupantsRoom.getOccupant() + " (" + occupantsRoom.getPosition() + ")");
               }
           }
       }
       // Create ListView
       ListView<String> directoryListView = new ListView<>();
       dialogBox.add(directoryListView,0,0);
       directoryListView.setItems(FXCollections.observableArrayList(directoryList));
       directoryListView.setMinWidth(350);
       directoryListView.setMaxHeight(200);
       ScrollBar bar1 = (ScrollBar)directoryListView.lookup(".scroll-bar");

       // Create Search Button
       Button searchButton = new Button("Search");
       dialogBox.add(searchButton,0,1);
       searchButton.setAlignment(Pos.CENTER);
       searchButton.setMinWidth(350);

       getDialogPane().setContent(dialogBox);


       searchButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               TextInputDialog searchOccupant = new TextInputDialog();
               searchOccupant.setTitle("Input Required");
               searchOccupant.setHeaderText(null);
               searchOccupant.setContentText("Please enter the full name of the person that you are searching for: ");

               Optional<String> result = searchOccupant.showAndWait();
               if (result.isPresent()) {
                   Alert searchResult = new Alert(Alert.AlertType.INFORMATION);
                   searchResult.setTitle("Search Results");
                   searchResult.setHeaderText(null);
                   boolean personFound = false;
                   outerLoop:
                   for (int i = 0; i < model.getFloorPlans().length; i++) {
                       for (int j = 0; j < 12; j++) {
                           if(model.getFloorPlan(i).roomWithColor(j) != null) {
                               if (Objects.equals(result.get().trim(), model.getFloorPlan(i).roomWithColor(j).getOccupant())) {
                                   personFound = true;
                                   searchResult.setContentText(result.get().trim() + " is our " + model.getFloorPlan(i).roomWithColor(j).getPosition()
                                           + " and can be located on the " + model.getFloorPlan(i).getName() + " in room " + model.getFloorPlan(i).roomWithColor(j).getNumber());
                                   break outerLoop;
                               }
                           }
                       }
                   }
                   if(!personFound)
                       searchResult.setContentText("That name does not match anyone in our records, please try again.");
                   searchResult.show();
               }

           }

       });
   }
}

