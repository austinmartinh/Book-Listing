package application;

public class BookTable {


	 int oldestYear = 0;
	 int mostRecentYear = 0;
	MyLinkedList[] hashTable = new MyLinkedList[13];

	
	public BookTable()
	{
	 for(int i = 0; i<hashTable.length; i++)
		{
			hashTable[i] = new MyLinkedList<Book>();
		}
	}
	 
	/**
	 * Method for populating unsorted table of objects. Also checks book and updates oldest/newest release year accordingly
	 * @param bookToAdd The book being added to the unsorted table
	 */
	@SuppressWarnings("unchecked")
	public void addWithoutSort(Book bookToAdd)
	{
		int bucket = bookToAdd.hashCode() % 13;
		hashTable[bucket].addToEnd(new Node<Book>(bookToAdd));
		updateYearBoundaries(bookToAdd);
	}

	/**
	 * Generates a number from 0-12 based on the values passed into it. The size of the range which corresponds to each value is calculated as the range of values/13
	 * @param numberToCheck	This number will be used to generate the bucket value
	 * @param max Maximum possible value for the number being checked, used to calculate range and split size
	 * @param oldestYear Minimum possible value for the number being checked, used to calculate range and split size
	 * @return	0-12 based on the value being checked
	 */
	public int hashByYear(int numberToCheck)
	{
		int splitRange = (mostRecentYear - oldestYear)/13;	//We have 13 buckets and this calculates the range of values to fit in each bucket to be 1/13 of the range of possible values for the number we are checking
		
		if(numberToCheck >=oldestYear && numberToCheck < oldestYear + splitRange)	//if the value checked is between the minimum value and min + split range
			return 0;
		else if(numberToCheck >=oldestYear+splitRange && numberToCheck < oldestYear + (2*splitRange))	//if the value is between the previous max and previous max + split range
			return 1;
		else if(numberToCheck >=oldestYear+(2*splitRange) && numberToCheck < oldestYear + (3*splitRange))	// etc...
			return 2;
		else if(numberToCheck >=oldestYear+(3*splitRange) && numberToCheck < oldestYear + (4*splitRange))
			return 3;
		else if(numberToCheck >=oldestYear+(4*splitRange) && numberToCheck < oldestYear + (5*splitRange))
			return 4;
		else if(numberToCheck >=oldestYear+(5*splitRange) && numberToCheck < oldestYear + (6*splitRange))
			return 5;
		else if(numberToCheck >=oldestYear+(6*splitRange) && numberToCheck < oldestYear + (7*splitRange))
			return 6;
		else if(numberToCheck >=oldestYear+(7*splitRange) && numberToCheck < oldestYear + (8*splitRange))
			return 7;
		else if(numberToCheck >=oldestYear+(8*splitRange) && numberToCheck < oldestYear + (9*splitRange))
			return 8;
		else if(numberToCheck >=oldestYear+(9*splitRange) && numberToCheck < oldestYear + (10*splitRange))
			return 9;
		else if(numberToCheck >=oldestYear+(10*splitRange) && numberToCheck < oldestYear + (11*splitRange))
			return 10;
		else if(numberToCheck >=oldestYear+(12*splitRange) && numberToCheck <= oldestYear + (12*splitRange))
			return 11;
		else
			return 12;		
	}
	
	/**
	 * Generates a number from 0-12 based on the first letter of the String passed to the method
	 * Used to select a hashtable bucket to store object in
	 * @param stringToCheck The string whose first letter is used to generate bucket value
	 * @return A number from 0-12 to correspond to a bucket in our hashtable
	 */
	public int hashByLetter(String stringToCheck)
	{	
		if (charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 65, 66)) 		//if the first letter of the string to be checked is a or b
			return 0; 																	//return 0 for array index to place item in 
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 67, 68)) // c or d
			return 1;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 69, 70)) 	// e or f
			return 2;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 71, 72)) 	// etc...
			return 3;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 73, 74))
			return 4;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 75, 76))
			return 5;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 77, 78))
			return 6;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 79, 80))
			return 7;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 81, 82))
			return 8;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 83, 84)) 
			return 9;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 85, 86)) 
			return 10;
		else if(charValueBetweenRange(stringToCheck.toUpperCase().charAt(0), 87, 88)) 
			return 11;
		else 
			return 12;	
	}
	
	public int hashByGenre(String genre)
	{
		switch(genre) {
		case "Autobiography":
			return 0;
		case "Biography":
			return 1;
		case "Comedy":
			return 2;
		case "Fantasy":
			return 3;
		case "Fiction":
			return 4;
		case "History":
			return 5;
		case "Horror":
			return 6;
		case "Journal":
			return 7;
		case "Mystery":
			return 8;
		case "Religion":
			return 9;
		case "Science":
			return 10;
		case "Self-Help":
			return 11;
		default:
			return 12;
		}
	}
	
	
	/**
	 * This method takes a new book and adds it to a sorted list based on the name of the author of the book
	 * @param bookToAdd	The book object which is to be sorted into the list
	 * @param sortedList The list into which the book is to be sorted
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addByAuthorName(Book bookToAdd, MyLinkedList[] authorSortedTable)
	{
		int bucket = (hashByLetter(bookToAdd.getAuthor()));
		boolean sorted = false;
		int listPosition = 0;
		
		if(authorSortedTable[bucket].getLength() == 0)
		{
			authorSortedTable[bucket].addToEnd(new Node<Book>(bookToAdd));
			sorted=!sorted;
			
			return;
		}
				
		while(!sorted)
		{
			if(authorSortedTable[bucket].findByPosition(listPosition) ==null)		//if list is empty, just add to list
			{
				authorSortedTable[bucket].addToEnd(new Node<Book>(bookToAdd));
				return;
			}
			
			Book bookToCompareTo = (Book) authorSortedTable[bucket].findByPosition(listPosition).getContents();
			int charPosition = 0;
			if(bookToCompareTo.getAuthor().toUpperCase().equals(bookToAdd.getAuthor().toUpperCase()))	//if titles are equal, just add after book
			{
				authorSortedTable[bucket].addAfter(new Node<Book>(bookToAdd), bookToCompareTo);
				return;
			}

			if(bookToCompareTo.getAuthor().length() < bookToAdd.getAuthor().length())
			{
				if(bookToCompareTo.getAuthor().equals(bookToAdd.getAuthor().substring(0,bookToCompareTo.getAuthor().length())))
				{
					authorSortedTable[bucket].addAfter(new Node<Book>(bookToAdd), bookToCompareTo);
					return;
				}
			}
			else if(bookToAdd.getAuthor().length() < bookToCompareTo.getAuthor().length())
			{
				if(bookToAdd.getAuthor().equals(bookToCompareTo.getAuthor().substring(0,bookToAdd.getAuthor().length())))
				{
					authorSortedTable[bucket].addAfter(new Node<Book>(bookToCompareTo), bookToAdd);
					return;
				}
			}
				while( bookToCompareTo.getAuthor().toUpperCase().charAt(charPosition) == bookToAdd.getAuthor().toUpperCase().charAt(charPosition)) //make this a comparator
					{
					
						charPosition++; //this ensures the characters we compare are not equal...will currently break @ string.length being reached
					}
				
			if(bookToCompareTo.getAuthor().toUpperCase().charAt(charPosition) < bookToAdd.getAuthor().toUpperCase().charAt(charPosition))
			{
				listPosition++;
			}
			else
			{
				sorted = !sorted;
			}
			
		}
		authorSortedTable[bucket].addToPosition(listPosition, new Node<Book>(bookToAdd));	
		return;
	}
	
	/**
	 * Method which will iterate through the unsorted table and add each book to a sorted table based on author name
	 * @param inputData The hashtable of unsorted books
	 * @return authorSortedList A sorted table of books based on author name
	 */
	@SuppressWarnings("rawtypes")
	public MyLinkedList[] sortByAuthor()
	{
		MyLinkedList[] authorSortedTable = new MyLinkedList[13];
		
		
		 for(int i = 0; i<hashTable.length; i++)
			{
				authorSortedTable[i] = new MyLinkedList<Book>();
			}
		
		for(int bucket =0; bucket < 13; bucket++)
		{
			
			for(int position = 0; position < hashTable[bucket].getLength(); position++)
			{
				Book bookToSort = (Book) hashTable[bucket].findByPosition(position).getContents();
				addByAuthorName(bookToSort, authorSortedTable);
			}
		}
		
		return authorSortedTable;
	}
	
	/**
	 * This method takes a new book and adds it to a sorted list based on the title of the book
	 * @param bookToAdd	The book object which is to be sorted into the list
	 * @param sortedList The list into which the book is to be sorted
	 */
	public void addByTitle(Book bookToAdd, MyLinkedList[] titleSortedTable)
	{
		int bucket = (hashByLetter(bookToAdd.getTitle()));
		boolean sorted = false;
		int listPosition = 0;
		
		if(titleSortedTable[bucket].getHead() == null)
		{
			titleSortedTable[bucket].addToEnd(new Node<Book>(bookToAdd));
			return;
		}
		
	
		
		while(!sorted)
		{
			if(titleSortedTable[bucket].findByPosition(listPosition) ==null)
			{
				titleSortedTable[bucket].addToEnd(new Node<Book>(bookToAdd));
				return;
			}
			
			Book bookToCompareTo = (Book) titleSortedTable[bucket].findByPosition(listPosition).getContents();
			
			
			int charPosition = 0;
			if(bookToCompareTo.getTitle().toUpperCase().equals(bookToAdd.getTitle().toUpperCase())) //adds new book after equivalent titled book if stringns equal
			{
				titleSortedTable[bucket].addAfter(new Node<Book>(bookToAdd), bookToCompareTo);
				return;
				
			}

			if(bookToCompareTo.getTitle().length() < bookToAdd.getTitle().length())
			{
				if(bookToCompareTo.getTitle().equals(bookToAdd.getTitle().substring(0,bookToCompareTo.getTitle().length())))
				{
					
					titleSortedTable[bucket].addToPosition(listPosition+1, new Node<Book>(bookToAdd));
					return;
				}
			}
			else if(bookToAdd.getTitle().length() < bookToCompareTo.getTitle().length())
			{
				if(bookToAdd.getTitle().equals(bookToCompareTo.getTitle().substring(0,bookToAdd.getTitle().length())))
				{
					titleSortedTable[bucket].addToPosition(listPosition+1, new Node<Book>(bookToAdd));
					return;
				}
			}
			
				while(bookToCompareTo.getTitle().toUpperCase().charAt(charPosition) == bookToAdd.getTitle().toUpperCase().charAt(charPosition)) //make this a comparator
					{
						charPosition++; //this ensures the characters we compare are not equal. We already know strings are not equivalent
					}
				
			if(bookToCompareTo.getTitle().toUpperCase().charAt(charPosition) < bookToAdd.getTitle().toUpperCase().charAt(charPosition))
			{
				listPosition++;
			}
			else
			{
				sorted = !sorted;
			}
		}
		titleSortedTable[bucket].addToPosition(listPosition, new Node<Book>(bookToAdd));	
		return;	
	}
	

	/**
	 * Method which will iterate through the unsorted table and add each book to a sorted table based on publishing year
	 * @param inputData The hashtable of unsorted books
	 * @return authorSortedList A sorted table of books based on book title
	 */
	public MyLinkedList[] sortByTitle()
	{
		MyLinkedList[] titleSortedTable = new MyLinkedList[13];
		
		for(int i = 0; i<hashTable.length; i++)
		{
			titleSortedTable[i] = new MyLinkedList<Book>();
		}
		
		for(int i =0; i < 13; i++)
		{
			for(int j = 0; j < hashTable[i].getLength(); j++)
			{
				Book bookToSort = (Book) hashTable[i].findByPosition(j).getContents();
				addByTitle(bookToSort, titleSortedTable);
			}
		}
		
		return titleSortedTable;
	}

	/**
	 * This method takes a new book and adds it to a sorted list based on the year of release of the book
	 * @param bookToAdd	The book object which is to be sorted into the list
	 * @param sortedList The list into which the book is to be sorted
	 */
	@SuppressWarnings("unchecked")
	public void addByYear(Book bookToAdd, MyLinkedList[] yearSortedList)
	{
		int bucket = (hashByYear(bookToAdd.getYearOfPublication()));
		boolean sorted = false;
		int listPosition = 0;
		
		if(yearSortedList[bucket].getHead() == null)
		{
			yearSortedList[bucket].addToEnd(new Node<Book>(bookToAdd));
			return;
		}
		while(!sorted)
		{
			if(yearSortedList[bucket].findByPosition(listPosition) == null)
			{
				yearSortedList[bucket].addToEnd(new Node<Book>(bookToAdd));
				return;
			}
			
			Book bookToCompareTo = (Book) yearSortedList[bucket].findByPosition(listPosition).getContents();
			
			if(bookToCompareTo.getYearOfPublication() == (bookToAdd.getYearOfPublication())) //adds new book after equivalent titled book if stringns equal
			{
				yearSortedList[bucket].addToPosition(listPosition+1,new Node<Book>(bookToAdd));
				return;
			}
			if(bookToCompareTo.getYearOfPublication() < bookToAdd.getYearOfPublication())
			{
				listPosition++;
			}
			else
			{
				sorted = !sorted;
			}
		}
		yearSortedList[bucket].addToPosition(listPosition, new Node<Book>(bookToAdd));	
		return;	
	}
	
	/**
	 * Method which will iterate through the unsorted table and add each book to a sorted table based on book title
	 * @param inputData The hashtable of unsorted books
	 * @return yearSortedList A sorted table of books based on release year
	 */
	@SuppressWarnings("rawtypes")
	public MyLinkedList[] sortByYear()
	{
		MyLinkedList[] yearSortedTable = new MyLinkedList[13];
		
		for(int i = 0; i<hashTable.length; i++)
		{
			yearSortedTable[i] = new MyLinkedList<Book>();
		}
		
		for(int i =0; i < 13; i++)
		{
			for(int j = 0; j < hashTable[i].getLength(); j++)
			{
				Book bookToSort = (Book) hashTable[i].findByPosition(j).getContents();
				addByYear(bookToSort, yearSortedTable);
			}
		}
		
		return yearSortedTable;
	}

	/**
	 * Method for sorting an unsorted table of books into a new table with one genre to each list
	 * @param bookToAdd Book object to be sorted
	 * @param genreSortedList Output, a table of books with each list in the table holding a different genre
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addByGenre(Book bookToAdd, MyLinkedList[] genreSortedList)
	{
		int bucket = hashByGenre(bookToAdd.getGenre());
	
		genreSortedList[bucket].addToEnd(new Node<Book>(bookToAdd));
		
	}
	
	@SuppressWarnings("rawtypes")
	public MyLinkedList[] sortByGenre( /*,MyLinkedList[] genreSortedTable*/)
	{
		MyLinkedList[] genreSortedTable = new MyLinkedList[hashTable.length];
		
		for(int i = 0; i<hashTable.length; i++)
		{
			genreSortedTable[i] = new MyLinkedList<Book>();
		}
		
		for(int i =0; i < 13; i++)
		{
			for(int j = 0; j < hashTable[i].getLength(); j++)
			{
				Book bookToSort = (Book) hashTable[i].findByPosition(j).getContents();
				addByGenre(bookToSort, genreSortedTable);
			}
		}
		
		return genreSortedTable;
	}
	
/**
 * Helper method for testing whether the int value of a character lies between two specified values 	
 * @param c The character to test 
 * @param lowerBound 
 * @param upperBound
 * @return True if the character is equal or between the specified values
 */public boolean charValueBetweenRange(char c, int lowerBound, int upperBound)
	{
		return ((int) c <= upperBound) && ((int) c >=lowerBound);
	}

	/***************** Searching ************************/
	
/**
 * A search method which takes an author name, determines the bucket holding that authors books and then adds all such book to a list	
 * @param author The search term which must match 
 * @return
 */
public MyLinkedList<Book> findAllAuthorMatches(String author/*, MyLinkedList<Book>[] authorSortedTable*/)
{
	@SuppressWarnings("unchecked")
	MyLinkedList<Book>[] authorSortedTable = sortByAuthor();
	
	MyLinkedList<Book> searchResults = new MyLinkedList<Book>();
	int bucket = hashByLetter(author.toUpperCase());
	
	for(int i=0; i<authorSortedTable[bucket].getLength(); i++)
	{
		Book itemToCheck = (Book) authorSortedTable[bucket].findByPosition(i).getContents();
		
		if(itemToCheck.getAuthor().toUpperCase().contains(author.toUpperCase()))
		{
			searchResults.addToEnd(new Node<Book>(itemToCheck));
		}
	}
	return searchResults;
}

/**
 * A search method which determines the bucket which should hold a book title and add all matching titles to a list
 * @param title
 * @return A list holding all books with title matching the search term 
 */
public MyLinkedList<Book> findAllTitleMatches(String title/*, MyLinkedList<Book>[] titleSortedTable*/)
{
	MyLinkedList<Book>[] titleSortedTable = sortByTitle();	
	
MyLinkedList<Book> searchResults = new MyLinkedList<Book>();
int bucket = hashByLetter(title.toUpperCase());

for(int i=0; i<titleSortedTable[bucket].getLength(); i++)
{
	Book itemToCheck = (Book) titleSortedTable[bucket].findByPosition(i).getContents();
	
	if(itemToCheck.getTitle().toUpperCase().contains(title.toUpperCase()))
	{
		searchResults.addToEnd(new Node<Book>(itemToCheck));
	}
}
return searchResults;
}

/**
 * A search method which takes a genre, finds the bucket holding that genre and iterates through that list looking for matching genres
 * @param genre The genre to be searched for
 * @return A list of all books with matching genres
 */
public MyLinkedList<Book> findAllGenreMatches(String genre/*, MyLinkedList<Book>[] sortedTable*/)
{
	MyLinkedList<Book>[] genreSortedTable = sortByGenre();
	MyLinkedList<Book> searchResults = new MyLinkedList<Book>();
	int bucket = hashByGenre(genre);
	
	for(int i=0; i<genreSortedTable[bucket].getLength(); i++)
	{
		Book itemToCheck = (Book) genreSortedTable[bucket].findByPosition(i).getContents();
		searchResults.addToEnd(new Node<Book>(itemToCheck));	
	}
	return searchResults;
}

/**
 * Search which takes year boundaries and a search year, returns a list of all books with years matching the search year
 * @param year Int representing a matching year
 * @return	A list containing all books with a matching year
 */
public MyLinkedList<Book> findAllYearMatches(String year/*, MyLinkedList<Book>[] sortedTable*/)
{
	MyLinkedList<Book>[] yearSortedTable = sortByYear();
	int searchYear = Integer.parseInt(year);
	MyLinkedList<Book> searchResults = new MyLinkedList<Book>();
	int bucket = hashByYear(searchYear);
	
	for(int i=0; i<yearSortedTable[bucket].getLength(); i++)
	{
		Book itemToCheck = (Book) yearSortedTable[bucket].findByPosition(i).getContents();
		
		if(itemToCheck.getYearOfPublication() == (searchYear))
		{
			searchResults.addToEnd(new Node<Book>(itemToCheck));
		}
}
return searchResults;
}

public String show() {
	String showAll = "";
	for(int i = 0; i<hashTable.length; i++)
	{
		showAll+=hashTable[i].show();
	}
	return showAll;
}
/***************** Setters ***************************/

	public void updateYearBoundaries(Book bookToAdd)
	{
		if(bookToAdd.getYearOfPublication() > this.mostRecentYear)
			this.mostRecentYear = bookToAdd.getYearOfPublication();
		if(bookToAdd.getYearOfPublication() < this.oldestYear)
			this.oldestYear = bookToAdd.getYearOfPublication();
	}
	
/***************** Getters ***************************/
	
	
	
}
