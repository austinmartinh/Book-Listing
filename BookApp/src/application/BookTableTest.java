package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import application.BookApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Book;
import application.BookTable;
import application.MyLinkedList;

class BookTableTest {
	
	BookTable bookTable = new BookTable();
	Book book1 = new Book("Manzi","Manzi", 1901, "Manzi", 2000, "Horror", "url", "he is great");
	Book book2 = new Book("Austin","Austin", 1950, "Austin", 2010, "Autobiography", "url1", "he is genius");
	Book book3 = new Book("girl","girl", 2000, "girl", 2001, "Fantasy", "url", "she is great");
	Book book4 = new Book("boy","boy", 2025, "boy", 2013, "Fiction", "url", "he is great");
	
	

	@BeforeEach
	void setUp() throws Exception {
		for(int x = 0; x<13; x++)
		{
			bookTable.hashTable[x].setHead(null);
		}
		bookTable.oldestYear = 1900;
		bookTable.mostRecentYear = 2030;
	}

	@Test
	void hashByYear()
	{
		Assert.assertEquals(0, bookTable.hashByYear(1901));
		Assert.assertEquals(5, bookTable.hashByYear(1950));
		Assert.assertEquals(10, bookTable.hashByYear(2000));
		Assert.assertEquals(12, bookTable.hashByYear(2025));
	}
	@Test
	void hashByLetterTest()
	{
		Assert.assertEquals(0,bookTable.hashByLetter("Austin"));
		Assert.assertEquals(0,bookTable.hashByLetter("ben"));
		Assert.assertEquals(1,bookTable.hashByLetter("Carl"));
		Assert.assertEquals(1,bookTable.hashByLetter("Dennis"));
		Assert.assertEquals(2,bookTable.hashByLetter("Evan"));
		Assert.assertEquals(2,bookTable.hashByLetter("Frank"));
		Assert.assertEquals(3,bookTable.hashByLetter("Geoff"));
		Assert.assertEquals(3,bookTable.hashByLetter("Henry"));
		Assert.assertEquals(4,bookTable.hashByLetter("Ivan"));
		Assert.assertEquals(4,bookTable.hashByLetter("Jacob"));
		Assert.assertEquals(5,bookTable.hashByLetter("Kelly"));
		Assert.assertEquals(5,bookTable.hashByLetter("Linda"));
		Assert.assertEquals(6,bookTable.hashByLetter("Manzi"));
		Assert.assertEquals(6,bookTable.hashByLetter("Noel"));
		Assert.assertEquals(7,bookTable.hashByLetter("Orlaith"));
		Assert.assertEquals(7,bookTable.hashByLetter("Priscilla"));
		Assert.assertEquals(8,bookTable.hashByLetter("Quentin"));
		Assert.assertEquals(8,bookTable.hashByLetter("Rudolf"));
		Assert.assertEquals(9,bookTable.hashByLetter("Sara"));
		Assert.assertEquals(9,bookTable.hashByLetter("Thomas"));
		Assert.assertEquals(10,bookTable.hashByLetter("Una"));
		Assert.assertEquals(10,bookTable.hashByLetter("Valerie"));
		Assert.assertEquals(11,bookTable.hashByLetter("Wyatt"));
		Assert.assertEquals(11,bookTable.hashByLetter("Xhosa"));
		Assert.assertEquals(12,bookTable.hashByLetter("Yanich"));
		Assert.assertEquals(12,bookTable.hashByLetter("Zazie"));
	}
	
	
	
	@Test 
	void hashByGenreTest()
	{
		Assert.assertEquals(0,bookTable.hashByGenre("Autobiography"));
		Assert.assertEquals(1,bookTable.hashByGenre("Biography"));
		Assert.assertEquals(2,bookTable.hashByGenre("Comedy"));
		Assert.assertEquals(3,bookTable.hashByGenre("Fantasy"));
		Assert.assertEquals(4,bookTable.hashByGenre("Fiction"));
		Assert.assertEquals(5,bookTable.hashByGenre("History"));
		Assert.assertEquals(6,bookTable.hashByGenre("Horror"));
		Assert.assertEquals(7,bookTable.hashByGenre("Journal"));
		Assert.assertEquals(8,bookTable.hashByGenre("Mystery"));
		Assert.assertEquals(9,bookTable.hashByGenre("Religion"));
		Assert.assertEquals(10,bookTable.hashByGenre("Science"));
		Assert.assertEquals(11,bookTable.hashByGenre("Self-Help"));
		Assert.assertEquals(12,bookTable.hashByGenre("dfghjk"));
	
	}
	
	@Test
	void addByAuthortest() {

		bookTable.addByAuthorName(book1, bookTable.hashTable);
		bookTable.addByAuthorName(book2, bookTable.hashTable);
		bookTable.addByAuthorName(book3, bookTable.hashTable);
		bookTable.addByAuthorName(book4, bookTable.hashTable);
		
		Assert.assertEquals(true,bookTable.hashTable[6].doesDataExistInList(book1));
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book2));
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book4));
		Assert.assertEquals(true,bookTable.hashTable[3].doesDataExistInList(book3));
		
	}
	
	@Test
	void addByTitleTest() {
		bookTable.addByTitle(book1, bookTable.hashTable);
		bookTable.addByTitle(book2, bookTable.hashTable);
		bookTable.addByTitle(book3, bookTable.hashTable);
		bookTable.addByTitle(book4, bookTable.hashTable);
		
		Assert.assertEquals(true,bookTable.hashTable[6].doesDataExistInList(book1));
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book2));
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book4));
		Assert.assertEquals(true,bookTable.hashTable[3].doesDataExistInList(book3));
		
	}
	
	@Test
	void addByGenreTest()
	{
		bookTable.addByGenre(book1, bookTable.hashTable);
		bookTable.addByGenre(book2, bookTable.hashTable);
		bookTable.addByGenre(book3, bookTable.hashTable);
		bookTable.addByGenre(book4, bookTable.hashTable);
		
		Assert.assertEquals(true,bookTable.hashTable[6].doesDataExistInList(book1));
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book2));
		Assert.assertEquals(true,bookTable.hashTable[4].doesDataExistInList(book4));
		Assert.assertEquals(true,bookTable.hashTable[3].doesDataExistInList(book3));
		
	}
	
	@Test
	void addBYearTest()
	{
		
		bookTable.addByYear(book1, bookTable.hashTable);
		bookTable.addByYear(book2, bookTable.hashTable);
		bookTable.addByYear(book3, bookTable.hashTable);
		bookTable.addByYear(book4, bookTable.hashTable);
		
		Assert.assertEquals(true,bookTable.hashTable[0].doesDataExistInList(book1));
		Assert.assertEquals(true,bookTable.hashTable[5].doesDataExistInList(book2));
		Assert.assertEquals(true,bookTable.hashTable[12].doesDataExistInList(book4));
		Assert.assertEquals(true,bookTable.hashTable[10].doesDataExistInList(book3));
		
	}
	

}