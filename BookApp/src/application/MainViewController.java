package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainViewController implements Initializable {
    @FXML
    Button addBookButton;
    @FXML
    TextField search;


    // function that takes you to the add book scene
    public void changeSceneToAddBookScene(ActionEvent event) throws IOException {
        Parent BookAddFacility = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
        Scene BookAddFacilityview = new Scene(BookAddFacility);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(BookAddFacilityview);
        window.show();
    }


    //function that takes you to the add character scene
//	public void changeSceneToAddCharacterScene(ActionEvent event) throws IOException {
//		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("CharacterAdd.fxml"));
//		Scene BookAddFacilityview = new Scene(BookAddFacility);
//		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//		window.setScene(BookAddFacilityview);
//		window.show();
//	}


    public void searchForBookOrCharacters() {

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }


}
