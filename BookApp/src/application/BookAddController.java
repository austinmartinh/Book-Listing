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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookAddController implements Initializable {	
	

	@FXML TableView <Book> tableView;
	
	@FXML private TableColumn <Book,String> titleColumn;
	@FXML private TableColumn <Book,String> authorColumn;
	@FXML private TableColumn <Book,Integer> yearOfPublicationColumn;
	@FXML private TableColumn <Book,String>publisherColumn;
	@FXML private TableColumn <Book,Integer>numberOfPagesColumn;
	@FXML private TableColumn <Book,String>genreColumn;
	@FXML private TableColumn <Book,String>plotDescriptionColumn;
	@FXML private TableColumn <Book,String>bookCoverColumn;
	@FXML private ChoiceBox<String> choicebox = new ChoiceBox<String>();
	@FXML private ChoiceBox<String> searchModes = new ChoiceBox<String>();
	@FXML private ChoiceBox<String> sortChoices = new ChoiceBox<String>();
	@FXML private ChoiceBox<String> displayMode = new ChoiceBox<String>();
	
	//Initialise the variable in the 
	@FXML private TextField searchTextField;
	@FXML private TextField titleTextField;
	@FXML private TextField authorTextField;//author
	@FXML private TextField yearOfPublicationTextField;
	@FXML private TextField publisherTextField;//publisher
	@FXML private TextField numberOfPagesTextField;

	//@FXML private TextField genreTextField;//genre
	@FXML private TextArea plotDecriptionTextArea;
	@FXML private TextField imageCoverlinkTextField;
	private boolean dataHasBeenSearched = false;

	private MyLinkedList<Book> searchResults = new MyLinkedList<Book>();
	
	//save button puts the data in the table
	public void saveBookButtonClicked() throws Exception {
		
		
		Book book = new Book(titleTextField.getText(), authorTextField.getText(),Integer.parseInt(yearOfPublicationTextField.getText()),publisherTextField.getText(),Integer.parseInt(numberOfPagesTextField.getText()),choicebox.getValue(),
		plotDecriptionTextArea.getText(),imageCoverlinkTextField.getText());
		BookApp.unsortedBookTable.addWithoutSort(book);
		
		BookApp.authorSortedTable.hashTable = BookApp.unsortedBookTable.sortByAuthor();
		tableView.getItems().clear();
		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
				tableView.getItems().add(eachBook);
				tableView.getSelectionModel().getSelectedIndex();
			}
		}
	}
	
	public void selectBookFromList(MouseEvent e) {		
		if(tableView.getSelectionModel().getSelectedIndex()>=0) {
			BookApp.currentBook=tableView.getSelectionModel().getSelectedItem();
		}
	}
	
	//this changes the scene form the table add and list to the main list
	public void changeSceneToMainScene(ActionEvent event) throws IOException {
		Parent MainView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene Mainscene = new Scene(MainView);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(Mainscene);
		window.show();
	}
	
	//this goes to the edit bookscene
	public void changeSceneToEditScene(ActionEvent event) throws IOException {
		Parent EditScene= FXMLLoader.load(getClass().getResource("EditScene.fxml"));
		Scene EditSceneView= new Scene(EditScene);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(EditSceneView);
		window.show();
	}
	
	//this changes the scene form the add book to the book details scene
	
	public void changeSceneToBookDetailsScene(ActionEvent event) throws IOException {
		Parent BookDetails = FXMLLoader.load(getClass().getResource("BookDetails.fxml"));
		Scene BookDetailsview = new Scene(BookDetails);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookDetailsview);
		window.show();
		BookApp.currentBook = tableView.getSelectionModel().getSelectedItem();
	}
	
	
	//this method initializes the table 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setup columns in the table
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
		yearOfPublicationColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("yearOfPublication"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
		numberOfPagesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numberOfPages"));
		genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
		plotDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("plotDescription"));
		bookCoverColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("bookCoverImageLink"));
		//tableView.setItems(getBooks());
		
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		choicebox.getItems().addAll("Autobiography","Biography","Comedy","Fantasy","Fiction","History","Horror","Journal","Mystery","Religion","Science","Self-Help","Uncatagorised");
		sortChoices.getItems().addAll("Author","Title","Year","Genre");
		searchModes.getItems().addAll("Author","Title","Year","Genre");		
		displayMode.getItems().addAll("Descending", "Ascending");
		
		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
				tableView.getItems().add(eachBook);
				tableView.getSelectionModel().getSelectedIndex();
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void sort()
	{
		String sortChoice = sortChoices.getValue();
		String chosenDisplayMode = displayMode.getValue();
		switch(sortChoice) {
			case "Author":
				BookApp.authorSortedTable.hashTable = BookApp.unsortedBookTable.sortByAuthor();
				tableView.getItems().clear();
				if(chosenDisplayMode=="Descending") {
					if(!dataHasBeenSearched) {
						for(int i = BookApp.authorSortedTable.hashTable.length-1; i>=0; i--) {
							for(int j =BookApp.authorSortedTable.hashTable[i].getLength()-1; j>=0;j--)
							{
								tableView.getItems().add( (Book) BookApp.authorSortedTable.hashTable[i].findByPosition(j).getContents());
								tableView.getSelectionModel().getSelectedIndex();
							}
					
						}
					}
					else
					{
					
						for(int j =searchResults.getLength()-1; j>=0;j--)
						{
							tableView.getItems().add( (Book) searchResults.findByPosition(j).getContents());
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				else
				{
					if(!dataHasBeenSearched)
					{
						for(int i = 0; i< BookApp.authorSortedTable.hashTable.length; i++) {		
							for(Book book : (MyLinkedList<Book>) BookApp.authorSortedTable.hashTable[i]) {
								tableView.getItems().add(book);
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(Book eachBook: searchResults) {
							tableView.getItems().add( eachBook);
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
			break;
			case "Title":
				BookApp.titleSortedTable.hashTable = BookApp.unsortedBookTable.sortByTitle();
				tableView.getItems().clear();
				
				if(chosenDisplayMode=="Descending") {
					if(!dataHasBeenSearched)
					{
						for(int i = BookApp.titleSortedTable.hashTable.length-1; i>=0; i--) {
							for(int j =BookApp.titleSortedTable.hashTable[i].getLength()-1; j>=0;j--)
							{
								tableView.getItems().add( (Book) BookApp.titleSortedTable.hashTable[i].findByPosition(j).getContents());
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(int j =searchResults.getLength()-1; j>=0;j--)
						{
							tableView.getItems().add( (Book) searchResults.findByPosition(j).getContents());
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				else 
				{
					if(!dataHasBeenSearched)
					{
						for(int i = 0; i< BookApp.titleSortedTable.hashTable.length; i++) {		
							for(Book book : (MyLinkedList<Book>) BookApp.titleSortedTable.hashTable[i]) {
								tableView.getItems().add(book);
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
							for(Book eachBook: searchResults) {
								tableView.getItems().add( eachBook);
								tableView.getSelectionModel().getSelectedIndex();
							}
					}
					
				}
				break;
			case "Year":
				BookApp.yearSortedTable.hashTable = BookApp.unsortedBookTable.sortByYear();
				tableView.getItems().clear();
				if(chosenDisplayMode=="Descending") {
					if(!dataHasBeenSearched)
					{
						for(int i = BookApp.yearSortedTable.hashTable.length-1; i>=0; i--) {
							for(int j =BookApp.yearSortedTable.hashTable[i].getLength()-1; j>=0;j--)
							{
								tableView.getItems().add( (Book) BookApp.yearSortedTable.hashTable[i].findByPosition(j).getContents());
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(int j =searchResults.getLength()-1; j>=0;j--)
						{
							tableView.getItems().add( (Book) searchResults.findByPosition(j).getContents());
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				else
				{
					if(!dataHasBeenSearched)
					{
						for(int i = 0; i< BookApp.yearSortedTable.hashTable.length; i++) {		
							for(Book book : (MyLinkedList<Book>) BookApp.yearSortedTable.hashTable[i]) {
								tableView.getItems().add(book);
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(Book book : (MyLinkedList<Book>) searchResults) {
							tableView.getItems().add(book);
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				break;
			case "Genre":
				BookApp.genreSortedTable.hashTable = BookApp.unsortedBookTable.sortByGenre();
				tableView.getItems().clear();
				if(chosenDisplayMode=="Descending") {
					if(!dataHasBeenSearched)
					{
						for(int i = BookApp.genreSortedTable.hashTable.length-1; i>=0; i--) {
							for(int j =BookApp.genreSortedTable.hashTable[i].getLength()-1; j>=0;j--)
							{
								tableView.getItems().add( (Book) BookApp.genreSortedTable.hashTable[i].findByPosition(j).getContents());
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(int j =searchResults.getLength()-1; j>=0;j--)
						{
							tableView.getItems().add( (Book) searchResults.findByPosition(j).getContents());
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				else
				{
					if(!dataHasBeenSearched)
					{
						for(int i = 0; i< BookApp.genreSortedTable.hashTable.length; i++) {		
							for(Book book : (MyLinkedList<Book>) BookApp.genreSortedTable.hashTable[i]) {
								tableView.getItems().add(book);
								tableView.getSelectionModel().getSelectedIndex();
							}
						}
					}
					else
					{
						for(Book book : (MyLinkedList<Book>) searchResults) {
							tableView.getItems().add(book);
							tableView.getSelectionModel().getSelectedIndex();
						}
					}
				}
				break;
			default:
				break;
		
			
		}
	}
	
	public void search()
	{
		String searchChoice = searchModes.getValue();
		String searchTerm = searchTextField.getText();
		dataHasBeenSearched = true;
		switch(searchChoice){
			case "Author":
				tableView.getItems().clear();
				
				searchResults =BookApp.unsortedBookTable.findAllAuthorMatches(searchTerm);
				for(Book book : searchResults) {
					tableView.getItems().add(book);
					tableView.getSelectionModel().getSelectedIndex();
				}
			break;
			case "Title":
				tableView.getItems().clear();
				searchResults =BookApp.unsortedBookTable.findAllTitleMatches(searchTerm);

				for(Book book : searchResults) {
					tableView.getItems().add(book);
					tableView.getSelectionModel().getSelectedIndex();
				}
				break;
			case "Year":
				tableView.getItems().clear();
				searchResults =BookApp.unsortedBookTable.findAllYearMatches(searchTerm);

				for(Book book : searchResults) {
					tableView.getItems().add(book);
					tableView.getSelectionModel().getSelectedIndex();
				}
				break;
			case "Genre":
				tableView.getItems().clear();
				searchResults =BookApp.unsortedBookTable.findAllGenreMatches(searchTerm);
				for(Book book : searchResults) {
					tableView.getItems().add(book);
					tableView.getSelectionModel().getSelectedIndex();
				}
				break;
			default:
				break;
			
		}
	}
	
	public void clearSearch()
	{
		dataHasBeenSearched = false;
		sort();
	}
	
	//this method changes the data to the arraylist collections so that it can be displayed in the table view
	public ObservableList<Book> getBooks(){
		ObservableList<Book>books = FXCollections.observableArrayList();
		books.add(new Book("Manxi", "Manxi jose", 250, "Manxi", 1200, "Manxi", "Manxi", "Manxi"));
	
	return books;
	}
	
	//deleting selected row from the table
	public void deleteButtonClicked() {
		ObservableList<Book> selectedRows, allBooks;
		allBooks = tableView.getItems();
		selectedRows = tableView.getSelectionModel().getSelectedItems();
		
		for(Book books:selectedRows) {
			allBooks.remove(books);
		}
		
		for(int i =0; i < BookApp.unsortedBookTable.hashTable.length; i++)
		{
			for(Book eachBook: (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i])
			{
				if(eachBook.equals(BookApp.currentBook))
				{
					 BookApp.unsortedBookTable.hashTable[i].remove(BookApp.currentBook);
				}
			}
		}
	}	

    
	
	public void load() throws Exception{		
		BookApp.loadAllTables();
		for(int i = 0; i< BookApp.unsortedBookTable.hashTable.length; i++) {		
			for(Book eachBook : (MyLinkedList<Book>) BookApp.unsortedBookTable.hashTable[i]) {
				tableView.getItems().add(eachBook);
				tableView.getSelectionModel().getSelectedIndex();
			}
		}
	}
	
	public void save() throws Exception{
		BookApp.saveAllTables();
	}
}
    
    
	


