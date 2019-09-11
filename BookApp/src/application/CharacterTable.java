package application;

public class CharacterTable {


	MyLinkedList[] unsortedTable = new MyLinkedList[13];

/*************** Constructors *************************/

	public CharacterTable()
	{
	 for(int i = 0; i<unsortedTable.length; i++)
		{
			unsortedTable[i] = new MyLinkedList<Character>();
		}
	}
	
/*************** Add & Sort Methods ***************/	
		 
	/**
	 * Method for adding a character to the unsorted table
	 * @param characterToAdd
	 */
	public void addWithoutSort(Character characterToAdd)
	{
		int bucket = characterToAdd.hashCode() % 13;
		unsortedTable[bucket].addToEnd(new Node<Character>(characterToAdd));
	}

	/** This method takes a new book and adds it to a sorted list based on the name of the author of the book
	 * @param characterToAdd	The book object which is to be sorted into the list
	 * @param sortedList The list into which the book is to be sorted
	 */
	public void addByCharacterName(Character characterToAdd, MyLinkedList[] nameSortedList)
	{
		int bucket = (hashByLetter(characterToAdd.getName().toUpperCase()));
		boolean sorted = false;
		int listPosition = 0;
		
		if(nameSortedList[bucket].getLength() == 0)
		{
			nameSortedList[bucket].addToEnd(new Node<Character>(characterToAdd));
			sorted=!sorted;
			
			return;
		}
				
		while(!sorted)
		{
			if(nameSortedList[bucket].findByPosition(listPosition) ==null)
			{
				nameSortedList[bucket].addToEnd(new Node<Character>(characterToAdd));
				return;
			}
			
			Character characterToCompareTo = (Character) nameSortedList[bucket].findByPosition(listPosition).getContents();
			int charPosition = 0;
			if(characterToCompareTo.getName().toUpperCase().equals(characterToAdd.getName().toUpperCase()))
			{
				nameSortedList[bucket].addAfter(new Node<Character>(characterToAdd), characterToCompareTo);
				return;
				
			}
				while(characterToCompareTo.getName().toUpperCase().charAt(charPosition) == characterToAdd.getName().toUpperCase().charAt(charPosition)) //make this a comparator
					{
						charPosition++; //this ensures the characters we compare are not equal...will currently break @ string.length being reached
					}
			
			if(characterToCompareTo.getName().toUpperCase().charAt(charPosition) < characterToAdd.getName().toUpperCase().charAt(charPosition))
			{
				listPosition++;
			}
			else
			{
				sorted = !sorted;
			}
			
		}
		nameSortedList[bucket].addToPosition(listPosition, new Node<Character>(characterToAdd));	
		return;
	}
	
	/**
	 * Method which will iterate through the unsorted table and add each Character to a sorted table based on Character name
	 * @param inputData The hashtable of unsorted Characters
	 * @return nameSortedList A sorted table of Characters based on Character name
	 */
	@SuppressWarnings("rawtypes")
	public MyLinkedList[] sortByName(MyLinkedList[] unsortedTable)
	{
		MyLinkedList[] nameSortedTable = new MyLinkedList[unsortedTable.length];
		 for(int i = 0; i<unsortedTable.length; i++)
		{
			nameSortedTable[i] = new MyLinkedList<Character>();
		}
		
		for(int bucket =0; bucket < unsortedTable.length; bucket++)
		{
			
			for(int position = 0; position < unsortedTable[bucket].getLength(); position++)
			{
				Character characterToSort = (Character) unsortedTable[bucket].findByPosition(position).getContents();
				addByCharacterName(characterToSort, nameSortedTable);
			}
		}
		
		return nameSortedTable;
	}
	
	/**
	 * Method for sorting the unsorted table into two lists with male characters in one list and female characters in the other
	 * @param characterToAdd The character to sort by gender
	 * @param genderSortedList 
	 */
	@SuppressWarnings("unchecked")
	public void addByGender(Character characterToAdd, MyLinkedList[] genderSortedList)
	{
		int bucket;

		
		if(characterToAdd.getGender().toUpperCase().charAt(0)=='M')
		{
			bucket =0;
		}
		else
		{
			bucket =1;
		}
		
		genderSortedList[bucket].addToEnd(new Node<Character>(characterToAdd));
	}
	
	/**
	 * Method for sorting through each character in the unsorted table and adding them in a sorted manner to the sorted table based on their gender
	 * @param unsortedTable Input table to be sorted
	 * @return genderSortedTable A sorted table with each gender in their own list
	 */
	@SuppressWarnings("rawtypes")
	public MyLinkedList[] sortByGender(MyLinkedList[] unsortedTable)
	{
		MyLinkedList[] genderSortedTable = new MyLinkedList[2];
		for(int i = 0; i < 2; i++)
		{
			genderSortedTable[i] = new MyLinkedList<Character>();
		}
		
		for(int bucket = 0; bucket < unsortedTable.length; bucket++)
		{
			for(int position = 0; position< unsortedTable[bucket].getLength(); position++)
			{
				Character characterToSort = (Character) unsortedTable[bucket].findByPosition(position).getContents();
				addByGender(characterToSort, genderSortedTable);
			}
		}
		return genderSortedTable;
	}
	
	/***************** Searching ************************/
	
/**
 * A search method which takes an author name, determines the bucket holding that authors books and then adds all such book to a list	
 * @param name The search term which must match 
 * @return
 */
public MyLinkedList<Character> findAllNameMatches(String name, MyLinkedList<Character>[] nameSortedTable)
{
	MyLinkedList<Character> searchResults = new MyLinkedList<Character>();
	int bucket = hashByLetter(name.toUpperCase());
	
	for(int i=0; i<nameSortedTable[bucket].getLength(); i++)
	{
		Character itemToCheck = (Character) nameSortedTable[bucket].findByPosition(i).getContents();
		
		if(itemToCheck.getName().toUpperCase() == name.toUpperCase())
		{
			searchResults.addToEnd(new Node<Character>(itemToCheck));
		}
	}
	return searchResults;
}

/**
 * Method which looks returns the list of all male or all female characters which are registered in the 
 * @param gender
 * @param genderSortedTable
 * @return
 */
public MyLinkedList<Character> findAllGenderMatches(String gender, MyLinkedList<Character>[] genderSortedTable)
{
	int bucket;
	if(gender.toUpperCase().charAt(0)=='M')
	{
		bucket =0;
	}
	else
	{
		bucket =1;
	}
	MyLinkedList<Character> searchResults = genderSortedTable[bucket];
	return searchResults;
}
	/**************** Hashing Methods *************************/

		

 /** Generates a number from 0-12 based on the first letter of the String passed to the method
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
	
	/*********************** Helper Methods *******************************/
	
	/**
	 * Helper method for testing whether the int value of a character lies between two specified values 	
	 * @param c The character to test 
	 * @param lowerBound 
	 * @param upperBound
	 * @return True if the character is equal or between the specified values
	 */
		public boolean charValueBetweenRange(char c, int lowerBound, int upperBound)
		{
			return ((int) c <= upperBound) && ((int) c >=lowerBound);
		}

	
	
}
