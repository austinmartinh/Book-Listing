package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.stream.events.Characters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookDetailsController implements Initializable {
	@FXML private TextField searchTextField;



	
	@FXML private ChoiceBox<String> sortChoices = new ChoiceBox<String>();
	@FXML private Button sortButton = new Button();

	@FXML private TableView <Book> otherAppearances;
	@FXML private TableView <Book> currentBookDisplay;
	@FXML private TableView<Character> charactersNameList;
	
	@FXML private TableColumn <Character,String> nameColumn;
	@FXML private TableColumn <Character,String> genderColumn;
	@FXML private TableColumn <Character,String> descriptionColumn;
	
	@FXML private TableColumn <Book,String> titleColumn;
	@FXML private TableColumn <Book,String> authorColumn;
	@FXML private TableColumn <Book,Integer> yearOfPublicationColumn;
	@FXML private TableColumn <Book,String> publisherColumn;
	@FXML private TableColumn <Book,Integer>numberOfPagesColumn;
	@FXML private TableColumn <Book,String>genreColumn;
	@FXML private TableColumn <Book,String>plotDescriptionColumn;
	@FXML private TableColumn <Book,String>bookCoverColumn;
	
	@FXML private TableColumn <Book,String> otherBookTitles;


	/**
	 * When a book in other appearences is clicked on, redraw the screen with that as the book whose details are being viewed
	 * 
	 */
	public void selectNewBook(MouseEvent e)
	{
		BookApp.currentBook = otherAppearances.getSelectionModel().getSelectedItem();
		currentBookDisplay.getItems().clear();
		currentBookDisplay.getItems().add(BookApp.currentBook);
		charactersNameList.getItems().clear();
		for(Character character : (MyLinkedList<Character>) BookApp.currentBook.getCharacterList()){
			charactersNameList.getItems().add(character);
			charactersNameList.getSelectionModel().getSelectedIndex();
		}
		otherAppearances.getItems().clear();
		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
				for(Character eachCharacterInTotalList : (MyLinkedList<Character>) eachBook.getCharacterList())
				{
					if(eachCharacterInTotalList.equals(BookApp.currentCharacter) && eachBook.equals(BookApp.currentBook))
					{
						otherAppearances.getItems().add(eachBook);
					}
				}
			}
		}
		
		
	}
	public void selectCharacterFromList(MouseEvent e) {		
		if(charactersNameList.getSelectionModel().getSelectedIndex()>=0) {
			otherAppearances.getItems().clear();
			BookApp.currentCharacter=charactersNameList.getSelectionModel().getSelectedItem();
		}
		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
				for(Character eachCharacterInTotalList : (MyLinkedList<Character>) eachBook.getCharacterList())
				{
					if(eachCharacterInTotalList.equals(BookApp.currentCharacter) && !eachBook.equals(BookApp.currentBook))
					{
						otherAppearances.getItems().add(eachBook);
					}
				}
			}
		}
	}
	
	
	public void changeSceneToAddCharacterScene(ActionEvent event) throws IOException {
		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("CharacterAdd.fxml"));
		Scene BookAddFacilityview = new Scene(BookAddFacility);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookAddFacilityview);
		window.show();
	}
	
	public void changeSceneToAddBookScene(ActionEvent event) throws IOException {
		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
		Scene BookAddFacilityview = new Scene(BookAddFacility);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookAddFacilityview);
		window.show();
	}


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		for(int i = 0; i< BookApp.unsortedCharacterTable.unsortedTable.length; i++) {
//			for(Character character : (MyLinkedList<Character>) BookApp.unsortedCharacterTable.unsortedTable[i]) {
//				charactersNameList.getItems().add(character.getName());
//				charactersNameList.getSelectionModel().getSelectedIndex();
//			}
//		}
//		
//		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
//			for(Book book : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
//				bookTitles.getItems().add(book.getTitle());
//				bookTitles.getSelectionModel().getSelectedIndex();
//			}
//			
//		
//		}
			titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
			yearOfPublicationColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("yearOfPublication"));
			publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
			numberOfPagesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numberOfPages"));
			genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
			plotDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("plotDescription"));
			bookCoverColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("bookCoverImageLink"));
			
			currentBookDisplay.getItems().add(BookApp.currentBook);
			
			nameColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Name"));
			genderColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Gender"));
			descriptionColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("Description"));
			
			for(Character character : (MyLinkedList<Character>) BookApp.currentBook.getCharacterList()){
				charactersNameList.getItems().add(character);
				charactersNameList.getSelectionModel().getSelectedIndex();
			}
			
			otherBookTitles.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
			
			
				
			
			

		
			sortChoices.getItems().addAll("Author","Title","Year","Genre");
			
			
	}
	
	

}




