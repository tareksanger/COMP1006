/**
 * Created by Tarek Sanger on 2017-03-10.
 * Student Number: 101059686
 */
/* References:
'COMP1006 - Assignment #7' - by Mark Lanthier
' COMP1006 - Intro to Computer Science II - Course Notes' - by Mark Lanthier
            "Some Code Used from Assignment 7 Specifications"
*/
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FloorBuilderView extends GridPane {
    public static final String[] ROOM_COLORS =                  // <--- Not my code
            {"ORANGE", "YELLOW", "LIGHTGREEN", "DARKGREEN",
                    "LIGHTBLUE", "BLUE", "CYAN", "DARKCYAN",
                    "PINK", "DARKRED", "PURPLE", "GRAY"};

    private Building model;

    private Button[][]          fpButton;
    private Button              overviewButton, colorButton;
    private RadioButton[]       radioButton;
    private TextField           summaryField;
    private ToggleGroup         selectEdit;

    private int                 colorIndex;
    private boolean             tileColorActive;

    public FloorBuilderView(Building m) {
        model = m;
        setPadding(new Insets(0, 10, 10, 10));

        // Create FloorPlan Buttons
        GridPane floorPlanGrid = new GridPane();
        fpButton = new Button[model.getFloorPlan(0).size()][model.getFloorPlan(0).size()];
        for (int row = 0; row < model.getFloorPlan(0).size(); row++) {
            for (int col = 0; col < model.getFloorPlan(0).size(); col++) {
                fpButton[row][col] = new Button();

                fpButton[row][col].setPrefHeight(500);
                fpButton[row][col].setPrefWidth(500);
                fpButton[row][col].setMinHeight(12.5);
                fpButton[row][col].setMinWidth(12.5);

                floorPlanGrid.add(fpButton[row][col], row, col);
            }
        }
        add(floorPlanGrid,0,1);

        // Create Labels
        Label aLabel = new Label("FLOOR LAYOUT");
        aLabel.setMaxHeight(25);
        aLabel.setMaxWidth(135);
        add(aLabel, 0, 0);

        aLabel = new Label("SELECT/EDIT:");
        aLabel.setMaxHeight(25);
        add(aLabel, 1, 0);

        aLabel = new Label("FLOOR SUMMARY:");
        aLabel.setMaxHeight(25);
        aLabel.setMaxWidth(135);
        add(aLabel, 0, 2);

        // Summary Field
        summaryField = new TextField();
        summaryField.setMaxHeight(30);
        summaryField.setEditable(false);
        add(summaryField, 0, 3,2,3);

        // RadioButtons
        Pane buttonPane = new Pane();
        selectEdit = new ToggleGroup();
        radioButton = new RadioButton[4];
        String[] buttonLabels = {"Walls", "Exits", "Room Tiles", "Select Room"};
        for (int i = 0; i < 4; i++) {
            radioButton[i] = new RadioButton(buttonLabels[i]);
            if(i==0)
                radioButton[i].relocate(10,0);
            else
                radioButton[i]. relocate(10,30*i);

            radioButton[i].setPrefSize(100,30);
            radioButton[i].setStyle("-fx-font-size: 12");
            buttonPane.getChildren().add(radioButton[i]);
            radioButton[i].setToggleGroup(selectEdit);
        }

        // Color Button
        colorButton = new Button();
        colorButton.relocate(105,60);
        colorButton.setPrefSize(30,30);
        buttonPane.getChildren().add(colorButton);

        // Overview Button
        overviewButton = new Button("Building Overview");
        overviewButton.relocate(5,120);
        overviewButton.setPrefSize(140,25);
        buttonPane.getChildren().add(overviewButton);

        add(buttonPane,1,1,2,1);

        // Constraints - Col
        ColumnConstraints col0 = new ColumnConstraints(160,800, Integer.MAX_VALUE);
        ColumnConstraints col1 = new ColumnConstraints(140);
        col0.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(col0, col1);

        // Row
        RowConstraints row0 = new RowConstraints(35);
        RowConstraints row1 = new RowConstraints(250,1000,Integer.MAX_VALUE);
        RowConstraints row2 = new RowConstraints(35);
        RowConstraints row3 = new RowConstraints(25);
        row1.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(row0, row1,row2,row3);

        update();
    }
    public Button getFloorPlanButton(int row, int col) { return fpButton[row][col]; }
    public Button getColorButton() { return colorButton; }
    public RadioButton getRadioButton(int i) { return radioButton[i]; }
    public int getColorIndex() { return colorIndex; }
    public boolean getTileColorActive(){ return tileColorActive; }

    public void setColorIndex(){
        if(colorIndex >= ROOM_COLORS.length-1)
            colorIndex = 0;
        else
            colorIndex++;
    }
    public void setTileColorActive(boolean x){tileColorActive = x;}
    public void removeEmptyRooms() {
        for(int i=0; i<ROOM_COLORS.length;i++)
            if(model.getFloorPlan(0).roomWithColor(i) != null)
                if(model.getFloorPlan(0).roomWithColor(i).getNumberOfTiles() == 0)
                    model.getFloorPlan(0).removeRoom(model.getFloorPlan(0).roomWithColor(i));
    }
    public void update() {
        // Makes sure Exits aren't also set as walls
        for(int x=0; x<20;x++) {
            if (model.hasExitAt(0, x))
                model.getFloorPlan(0).setWallAt(0, x, false);
            if (model.hasExitAt(19, x))
                model.getFloorPlan(0).setWallAt(19,x,false);
            if (model.hasExitAt( x,0))
                model.getFloorPlan(0).setWallAt( x,0, false);
            if (model.hasExitAt(x,19))
                model.getFloorPlan(0).setWallAt(x,19,false);
        }
        // Refresh Colors (get Color Index from Rooms & set the tiles colors accordingly)
        for(int row=0; row<model.getFloorPlan(0).size(); row++) {
            for (int col = 0; col < model.getFloorPlan(0).size(); col++) {
                if(model.hasExitAt(row, col)) {
                    fpButton[row][col].setStyle("-fx-base: RED");
                    fpButton[row][col].setText("Exit");
                } else if(model.getFloorPlan(0).wallAt(row,col)) {
                    fpButton[row][col].setStyle("-fx-base: BlACK");
                    fpButton[row][col].setText("");
                } else if(model.getFloorPlan(0).roomAt(row,col) != null) {
                    fpButton[row][col].setStyle("-fx-base:" + ROOM_COLORS[model.getFloorPlan(0).roomAt(row,col).getColorIndex()]);
                    fpButton[row][col].setText("");
                } else {
                    fpButton[row][col].setStyle("-fx-base: WHITE");
                    fpButton[row][col].setText("");
                }
            }
        }
        // Update ColorButton
        colorButton.setStyle("-fx-base:" + ROOM_COLORS[colorIndex]);
        // Disable Color Button
        if(tileColorActive)
            colorButton.setDisable(false);
        else
            colorButton.setDisable(true);
        // Update FLOOR SUMMARY
        summaryField.setText(model.getFloorPlan(0).getName() + " with " + model.getFloorPlan(0).getNumberOfRooms() + " room(s).");
    }
}