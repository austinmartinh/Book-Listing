package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CharacterAddController implements Initializable {
	
	
	//these are columns of the table view 
	@FXML TableView <Character> tableView;
	@FXML private TableColumn <Character,String> nameColumn;
	@FXML private TableColumn <Character,String> genderColumn;
	@FXML private TableColumn <Character,String>descriptionColumn;
	
	
	@FXML private TextField nameTextField;
	@FXML private TextField genderTextField;//publisher
	@FXML private TextField descriptionTextField;
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setup columns in the table
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Name"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Gender"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Description"));
		
//		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
//			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
//				for(Character eachCharacter : eachBook.getCharacterList())
//				{
//					tableView.getItems().add(eachCharacter);
//				}
//			}
//		}	
		for(int i = 0; i < BookApp.unsortedCharacterTable.unsortedTable.length; i++)
		{
			for(Character eachCharacter: (MyLinkedList<Character>) BookApp.unsortedCharacterTable.unsortedTable[i])
			{
				tableView.getItems().add(eachCharacter);		
			}
		}
	}
	
	public void saveBookButtonClicked() throws Exception {
		Character characterToAdd = new Character(nameTextField.getText(), genderTextField.getText()
				,descriptionTextField.getText());
		tableView.getItems().add(characterToAdd);
		BookApp.currentBook.getCharacterList().add(characterToAdd);
		BookApp.unsortedCharacterTable.addWithoutSort(characterToAdd);
		System.out.println(BookApp.currentBook.getCharacterList().show());
		save();
	}
	
	public void addSelectedButtonClicked() throws Exception {
		Character characterToAdd = tableView.getSelectionModel().getSelectedItem();
		BookApp.currentBook.getCharacterList().add(characterToAdd);
	}
	
	
	public void changeSceneToBookDetailsScene(ActionEvent event) throws IOException {
		Parent BookDetails = FXMLLoader.load(getClass().getResource("BookDetails.fxml"));
		Scene BookDetailsview = new Scene(BookDetails);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookDetailsview);
		window.show();
	}

	
	
	public ObservableList<Character> getCharacters(){
		ObservableList<Character>characters = FXCollections.observableArrayList();
		characters.add(new Character(null, null, null));
	
	return characters;
	}
	
	//this is the load method that loads saved data to form the xml
	@SuppressWarnings("unchecked")
    public  void load()
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("characters.xml"));
      BookApp.unsortedCharacterTable=((CharacterTable) is.readObject());
        is.close();
    }
    
	/**
	 * this function saves the program data to xml file 
	 * @throws Exception
	 */
    public void save() 
    		throws Exception{
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("characters.xml"));
        out.writeObject(BookApp.unsortedCharacterTable);
        out.close();    
    }

}
