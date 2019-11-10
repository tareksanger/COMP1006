/**
 * Created by Tarek Sanger on 2017-02-22.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #5' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ShoppingListApp extends Application {
    private     Shopper                 model;
    private     ShoppingListView        view;


    public void start(Stage primaryStage) {
        // Create model & view
        model = new Shopper();
        view = new ShoppingListView(model);

        view.getBuyButton().setOnAction(new EventHandler<ActionEvent>() {               // Buy Button Press
            public void handle(ActionEvent actionEvent) { handleBuyItem(); }
        });
        view.getProductsList().setOnMouseClicked(new EventHandler<MouseEvent>() {       // Buy Selected item on double click ( Not Required in Assignment )
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if(mouseEvent.getClickCount() == 2){
                        handleBuyItem();
                    }
                }
            }
        });
        view.getReturnButton().setOnAction(new EventHandler<ActionEvent>() {                // Return Button Press
            public void handle(ActionEvent event) { handleReturnItem(); }
        });
        view.getCartList().setOnMouseClicked(new EventHandler<MouseEvent>() {               // Return Selected item on double click ( Not Required in Assignment )
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if(mouseEvent.getClickCount() == 2){
                        handleReturnItem();
                    }
                }
            }
        });
        view.getCheckoutButton().setOnAction(new EventHandler<ActionEvent>() {              // Checkout Button Press
            public void handle(ActionEvent event) {
                handleCheckoutButton();
            }
        });
        view.getProductsList().setOnMousePressed(new EventHandler<MouseEvent>() {           // Enable/Disable Buy Button
            public void handle(MouseEvent mouseEvent) { handleListSelection(); }
        });
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {               // Enable/Disable Return Button
            public void handle(MouseEvent mouseEvent) { handleListSelection(); }
        });
        primaryStage.setTitle("Grocery Store Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 740,420));
        primaryStage.show();
    }
    // Handlers
    private void handleBuyItem() {                                                          // Buy Button/DoubleClick Event Handlers
        model.addItem(view.getProductsList().getSelectionModel().getSelectedItem());
        view.update();
    }
    private void handleReturnItem() {                                                       // Return Button/DoubleClick Event Handlers
        int index = view.getCartList().getSelectionModel().getSelectedIndex();
        if(index >= 0) {
            model.removeItem(model.getCart()[index]);
            view.getProductsList().getSelectionModel().clearSelection();
            view.update();
        }
    }
    private void handleCheckoutButton() {                                                     // Checkout/RestartShopping Event Handler
        if(view.getCheckoutButton().getText().matches("Checkout")) {          // If Checkout has NOT been clicked
            // Print Receipt
            System.out.println();
            for (int i = 0; i < model.getNumItems(); i++) {
                System.out.println(String.format("%-35s%.2f", model.getCart()[i].getDescription(), model.getCart()[i].getPrice()));
            }
            System.out.println("-----------------------------------------");
            System.out.println(String.format("%-35s%s", "TOTAL", model.computeTotalCost()));
            // Make Changes for Checking out
            model.packBags();                                       // Pack Bags
            view.updateCartList();                                  // update Cart List one last time
            view.getCheckoutButton().setText("Restart Shopping");   // Change Text: Checkout --> Restart Shopping
            view.getProductsList().setDisable(true);                // Disable Products List
            view.getBuyButton().setDisable(true);                   // Disable BuyButton
            view.getReturnButton().setDisable(true);                // Disable ReturnButton
            view.update();
        } else {                                    // If Check has been Clicked
            model = new Shopper();
            view = new ShoppingListView(model);
            Stage newStage = new Stage();
            start(newStage);
        }
    }
    private void handleListSelection() { view.update(); }           // List Selection handler

    public static void main(String[] args) {
        launch(args);
    }
}
