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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSceneController implements Initializable {
	
	@FXML private TextField titleTextField;
	@FXML private TextField authorTextField;
	@FXML private TextField yearOfPublicationTextField;
	@FXML private TextField publisherTextField;
	@FXML private TextField genreTextField;
	@FXML private TextField numberOfPagesTextField;
	@FXML private TextArea plotDecriptionTextArea;
	@FXML private TextField imageCoverlinkTextField;

	
	public void updateEditedBook() {
		BookApp.currentBook.setTitle(titleTextField.getText());
		BookApp.currentBook.setAuthor(authorTextField.getText());
		BookApp.currentBook.setYearOfPublication(Integer.parseInt(yearOfPublicationTextField.getText()));
		BookApp.currentBook.setPublisher(publisherTextField.getText());
		BookApp.currentBook.setNumberOfPages(Integer.parseInt(numberOfPagesTextField.getText()));
		BookApp.currentBook.setPlotDescription(plotDecriptionTextArea.getText());
		BookApp.currentBook.setBookCoverImageLink(imageCoverlinkTextField.getText());
		
	}
	
	
	// function that takes you to the add book scene
	public void changeSceneToAddBookScene(ActionEvent event) throws IOException {
		Parent BookAddFacility = FXMLLoader.load(getClass().getResource("BookAdd.fxml"));
		Scene BookAddFacilityview = new Scene(BookAddFacility);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(BookAddFacilityview);
		window.show();
	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleTextField.setText(BookApp.currentBook.getTitle());
		authorTextField.setText(BookApp.currentBook.getAuthor());
		yearOfPublicationTextField.setText(Integer.toString(BookApp.currentBook.getYearOfPublication()));
		publisherTextField.setText(BookApp.currentBook.getPublisher());
		genreTextField.setText(BookApp.currentBook.getGenre());
		numberOfPagesTextField.setText(Integer.toString(BookApp.currentBook.getNumberOfPages()));
		plotDecriptionTextArea.setText(BookApp.currentBook.getPlotDescription());
		imageCoverlinkTextField.setText(BookApp.currentBook.getBookCoverImageLink());
	}

}
