package application;
	
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class BookApp extends Application {
	
	public static BookTable unsortedBookTable = new BookTable();
	public static BookTable authorSortedTable = new BookTable();
	public static BookTable yearSortedTable = new BookTable();
	public static BookTable genreSortedTable = new BookTable();
	public static BookTable titleSortedTable = new BookTable();
	
	public static CharacterTable unsortedCharacterTable = new CharacterTable();
	public static CharacterTable nameSortedTable = new CharacterTable();
	public static CharacterTable genderSortedTable = new CharacterTable();



	
	public static Character currentCharacter= new Character();
	public static Book currentBook = new Book();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(BookApp.class.getResource("MainView.fxml"));
			AnchorPane mainLayout = new AnchorPane();
			mainLayout = loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Interesting Books");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	/**
	 * Loads previously saved dining menu from xml file
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void loadAllTables() throws Exception
	{
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is1 = xstream.createObjectInputStream(new FileReader("unsortedBookTable.xml"));
        unsortedBookTable= (BookTable) is1.readObject();
        ObjectInputStream is2 = xstream.createObjectInputStream(new FileReader("authorSortedTable.xml"));
        authorSortedTable= (BookTable) is2.readObject();
        ObjectInputStream is3 = xstream.createObjectInputStream(new FileReader("titleSortedTable.xml"));
        titleSortedTable= (BookTable) is3.readObject();
        ObjectInputStream is4 = xstream.createObjectInputStream(new FileReader("yearSortedTable.xml"));
        yearSortedTable= (BookTable) is4.readObject();
        ObjectInputStream is5 = xstream.createObjectInputStream(new FileReader("genreSortedTable.xml"));
        genreSortedTable= (BookTable) is5.readObject();
        
        ObjectInputStream is6 = xstream.createObjectInputStream(new FileReader("unsortedCharacterTable.xml"));
        unsortedCharacterTable= (CharacterTable) is6.readObject();
        ObjectInputStream is7 = xstream.createObjectInputStream(new FileReader("nameSortedTable.xml"));
        nameSortedTable= (CharacterTable) is7.readObject();
        ObjectInputStream is8 = xstream.createObjectInputStream(new FileReader("genderSortedTable.xml"));
        genderSortedTable= (CharacterTable) is8.readObject();       
        
        is1.close();
        is2.close();
        is3.close();
        is4.close();
        is5.close();
        is6.close();
        is7.close();
        is8.close();
    }
	
	/**
	 * saves dining menu to xml
	 * @throws Exception
	 */
	public static void saveAllTables() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out1 = xstream.createObjectOutputStream(new FileWriter("unsortedBookTable.xml"));
        out1.writeObject(unsortedBookTable);
        ObjectOutputStream out2 = xstream.createObjectOutputStream(new FileWriter("authorSortedTable.xml"));
        out2.writeObject(authorSortedTable);
        ObjectOutputStream out3 = xstream.createObjectOutputStream(new FileWriter("titleSortedTable.xml"));
        out3.writeObject(titleSortedTable);
        ObjectOutputStream out4 = xstream.createObjectOutputStream(new FileWriter("yearSortedTable.xml"));
        out4.writeObject(yearSortedTable);
        ObjectOutputStream out5 = xstream.createObjectOutputStream(new FileWriter("genreSortedTable.xml"));
        out5.writeObject(genreSortedTable);
        
        ObjectOutputStream out6 = xstream.createObjectOutputStream(new FileWriter("unsortedCharacterTable.xml"));
        out6.writeObject(unsortedCharacterTable);
        ObjectOutputStream out7 = xstream.createObjectOutputStream(new FileWriter("nameSortedTable.xml"));
        out7.writeObject(nameSortedTable);
        ObjectOutputStream out8 = xstream.createObjectOutputStream(new FileWriter("genderSortedTable.xml"));
        out8.writeObject(genderSortedTable);
        
        out1.close();    
        out2.close();
        out3.close();
        out4.close();
        out5.close();
        out6.close();
        out7.close();
        out8.close();
    }
}

