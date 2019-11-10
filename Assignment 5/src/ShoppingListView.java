/**
 * Created by Tarek Sanger on 2017-02-22.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #5' - by Mark Lanthier (Used code from Specifications as instructed)
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
*/
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class ShoppingListView extends Pane {
    private     Shopper                     model;

    private     ListView<GroceryItem>       productsList, contentsList;
    private     ListView<String>            cartList;
    private     TextField                   priceField;
    private     Button                      buyButton, returnButton, checkoutButton;

    // Get Methods
    public      ListView<GroceryItem>       getProductsList(){ return productsList; }
    public      ListView<String>            getCartList(){ return cartList; }
    public      Button                      getBuyButton(){ return buyButton; }
    public      Button                      getReturnButton(){ return returnButton; }
    public      Button                      getCheckoutButton(){ return checkoutButton; }

    public ShoppingListView(Shopper m) {
        model = m;
        // Create Labels
        Label productsLabel = new Label("Products");                // Products label
        productsLabel.relocate(10,10);
        getChildren().add(productsLabel);

        Label cartLabel = new Label("Shopping Cart");               // Cart Label
        cartLabel.relocate(220,10);
        getChildren().add(cartLabel);

        Label contentsLabel = new Label("Contents");                // Contents Label
        contentsLabel.relocate(430,10);
        getChildren().add(contentsLabel);

        Label totalPriceLabel = new Label("Total Price:");          // Total Price Label
        totalPriceLabel.relocate(565, 355); totalPriceLabel.setPrefSize(65,25);
        totalPriceLabel.setStyle("-fx-font: 10 arial");
        getChildren().add(totalPriceLabel);

        // Create Total Price Text Field
        priceField = new TextField("$0.00");                        // Total Price Field
        priceField.relocate(630,355); priceField.setPrefSize(100,25);
        priceField.setAlignment(Pos.CENTER_RIGHT);
        priceField.setDisable(true);
        priceField.setEditable(false);
        getChildren().add(priceField);

        // Create Buttons
        buyButton = new Button("Buy");                              // Buy Button
        buyButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        buyButton.relocate(10,355); buyButton.setPrefSize(200,25);
        getChildren().add(buyButton);

        returnButton = new Button("Return");                        // Return Button
        returnButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,0,100); -fx-text-fill: rgb(255,255,255);");
        returnButton.relocate(220,355); returnButton.setPrefSize(200,25);
        getChildren().add(returnButton);

        checkoutButton = new Button("Checkout");                    // Checkout( & Restart Shopping) Button
        checkoutButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(100,0,0); -fx-text-fill: rgb(255,255,255);");
        checkoutButton.relocate(430,355); checkoutButton.setPrefSize(120,25);
        getChildren().add(checkoutButton);
        //checkoutClicked = false;

        // Create products ListView Array           (Code used from Assignment 5)
        GroceryItem[] products = {
                new FreezerItem("Smart-Ones Frozen Entrees", 1.99f, 0.311f),
                new GroceryItem("SnackPack Pudding", 0.99f, 0.396f),
                new FreezerItem("Breyers Chocolate Icecream",2.99f,2.27f),
                new GroceryItem("Nabob Coffee", 3.99f, 0.326f),
                new GroceryItem("Gold Seal Salmon", 1.99f, 0.213f),
                new GroceryItem("Ocean Spray Cranberry Cocktail",2.99f,2.26f),
                new GroceryItem("Heinz Beans Original", 0.79f, 0.477f),
                new RefrigeratorItem("Lean Ground Beef", 4.94f, 0.75f),
                new FreezerItem("5-Alive Frozen Juice",0.75f,0.426f),
                new GroceryItem("Coca-Cola 12-pack", 3.49f, 5.112f),
                new GroceryItem("Toilet Paper - 48 pack", 40.96f, 10.89f),
                new RefrigeratorItem("2L Sealtest Milk",2.99f,2.06f),
                new RefrigeratorItem("Extra-Large Eggs",1.79f,0.77f),
                new RefrigeratorItem("Yoplait Yogurt 6-pack",4.74f,1.02f),
                new FreezerItem("Mega-Sized Chocolate Icecream",67.93f,15.03f)};

        // Create the lists
        productsList = new ListView<>();                                // Products List
        productsList.setItems(FXCollections.observableArrayList(products));
        productsList.relocate(10,45); productsList.setPrefSize(200,300);
        getChildren().add(productsList);

        cartList = new ListView<>();                                    // Cart List
        cartList.relocate(220, 45); cartList.setPrefSize(200,300);
        getChildren().add(cartList);

        contentsList = new ListView<>();                                // Contents List
        contentsList.relocate(430,45); contentsList.setPrefSize(300,300);
        getChildren().add(contentsList);
        update();
    }
    public void update() {
        // Disables Buy Button if Focus is Shopping cart list by deselection Product List & Disables Return if Focus is Products List by deselecting Shopping Cart List
        if(cartList.isFocused())
            productsList.getSelectionModel().clearSelection();
        else if(productsList.isFocused())
            cartList.getSelectionModel().clearSelection();

        // Checkout hasn't been clicked
        if(checkoutButton.getText().matches("Checkout")) {
            updateCartList();
            // Disable/Enable buttons accordingly
            returnButton.setDisable(cartList.getSelectionModel().getSelectedIndex() < 0);
            buyButton.setDisable(productsList.getSelectionModel().getSelectedIndex() < 0);
            checkoutButton.setDisable(model.getNumItems() <= 0);
            // Set Text for Total Price
            priceField.setText("$" + model.computeTotalCost());

        } else { //If checkout Button has been clicked run.
            if (cartList.getSelectionModel().getSelectedIndex() >= 0) {       // Checks if an item in Shopping cart list has been selected
                if (model.getCart()[cartList.getSelectionModel().getSelectedIndex()] instanceof GroceryBag) { // Checks if Selected Item is a GroceryBag
                    // Creates new GroceryItem list, contents, with NumItems of the selected GroceryBag in cartList
                    // Matching the index of the cartList to the 'model'
                    // then adds the models GroceryBag items to contents & and assigns them to contentsList
                    GroceryItem[] contents = new GroceryItem[(((GroceryBag) model.getCart()[cartList.getSelectionModel().getSelectedIndex()])).getNumItems()];
                    for (int i = 0; i < (((GroceryBag) model.getCart()[cartList.getSelectionModel().getSelectedIndex()])).getNumItems(); i++)
                        contents[i] = (((GroceryBag) model.getCart()[cartList.getSelectionModel().getSelectedIndex()])).getItems()[i];
                    contentsList.setItems(FXCollections.observableArrayList(contents));
                } else { // If the selected Shopping cart item is a GroceryItem contentsList is set null
                    contentsList.setItems(null);
                }
            }
        }
    }
    // Updates the cartList . . . Trying to keep the application
    // from updating the view more than necessary after the checkout button has been clicked
    // Create String Array for Shopping Cart and set Items
    public void updateCartList(){
        String[] exactList = new String[model.getNumItems()];
        for(int i=0; i<model.getNumItems();i++)
            exactList[i] = model.getCart()[i].getDescription();
        cartList.setItems(FXCollections.observableArrayList(exactList));
    }
}