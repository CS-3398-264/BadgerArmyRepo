package com.mycompany.chessbadgerarmy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FXMLController implements Initializable {
    
     @FXML
    private GridPane Board;

    @FXML
    private Pane SettingPane;

    @FXML
    private Button startButton;

    @FXML
    private TextArea History;

    @FXML
    private RadioButton radioDifficult;

    @FXML
    private RadioButton radioEasy;

    @FXML
    private RadioButton radioPvAI;

    @FXML
    private RadioButton radioPvP;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // make chess board need to add individual elements to an array so that 
        // we can use them later for pieces and suchrt
        final int size = 8 ;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: "+color+";");
                Board.add(square, col, row);
            }
        }  
        
        //set text area to uneditable 
        History.setEditable(false);


             
    }    
    
    
}
