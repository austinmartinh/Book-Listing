package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
		// variables for the book class
		//public BookClass head;
		private SimpleStringProperty title,author,publisher,genre,plotDescription,bookCoverImageLink;
		private SimpleIntegerProperty yearOfPublication,numberOfPages;
		
		private MyLinkedList<Character> characterList=new MyLinkedList<>();
		
		/**
		 * constructor for the book class
		 * @param title
		 * @param author
		 * @param yearOfPublication
		 * @param publisher
		 * @param numberOfPages
		 * @param genre
		 * @param plotDescription
		 * @param bookCoverImageLink
		 */
		public Book(String title,String author,int yearOfPublication,String publisher,int numberOfPages,String genre,String plotDescription,String bookCoverImageLink) {
			this.title = new SimpleStringProperty(title);
			this.author = new SimpleStringProperty(author);
			this.yearOfPublication = new SimpleIntegerProperty(yearOfPublication);
			this.publisher = new SimpleStringProperty(publisher);
			this.numberOfPages = new SimpleIntegerProperty(numberOfPages);
			this.genre = new SimpleStringProperty(genre);
			this.plotDescription = new SimpleStringProperty(plotDescription);
			this.bookCoverImageLink = new SimpleStringProperty(bookCoverImageLink);
		}
		
		
		public Book() {
			// TODO Auto-generated constructor stub
		}


		public MyLinkedList<Character> getCharacterList() {
			return characterList;
		}


		public void setCharacterList(MyLinkedList<Character> characterList) {
			this.characterList = characterList;
		}


		/**
		 * getters and setters for the Book class variables
		 * @return
		 */
		public String getTitle() {
			return title.get();
		}
		public void setTitle(String title) {
			this.title = new SimpleStringProperty(title);
		}
		public String getAuthor() {
			return author.get();
		}
		public void setAuthor(String author) {
			this.author = new SimpleStringProperty(author);
		}
		public int getYearOfPublication() {
			return yearOfPublication.get();
		}
		public void setYearOfPublication(int yearOfPublication) {
			this.yearOfPublication = new SimpleIntegerProperty(yearOfPublication);
		}
		public String getPublisher() {
			return publisher.get();
		}
		public void setPublisher(String publisher) {
			this.publisher = new SimpleStringProperty(publisher);
		}
		public int getNumberOfPages() {
			return numberOfPages.get();
		}
		public void setNumberOfPages(int numberOfPages) {
			this.numberOfPages = new SimpleIntegerProperty(numberOfPages);
		}
		public String getGenre() {
			return genre.get();
		}
		public void setGenre(String genre) {
			this.genre = new SimpleStringProperty(genre);
		}
		public String getPlotDescription() {
			return plotDescription.get();
		}
		public void setPlotDescription(String plotDescription) {
			this.plotDescription = new SimpleStringProperty(plotDescription);
		}
		public String getBookCoverImageLink() {
			return bookCoverImageLink.get();
		}
		public void setBookCoverImageLink(String bookCoverImageLink) {
			this.bookCoverImageLink = new SimpleStringProperty(bookCoverImageLink);
		}
		
		
		public String toString() {
			return "The title of the book is: " + title + "\n" + 
					"The author of the book is: " + author + "\n" +
					"The book was published in: " + yearOfPublication + "\n" +
					"The book was published by: " + publisher + "\n" +
					"The book has " + numberOfPages + " number of pages" + "\n" +
					"The genre of the book is: " + genre + "\n" +
					"The book's plot is: " + plotDescription + "\n" +
					"The book cover images link below: " + bookCoverImageLink ;
			
		}

	}



