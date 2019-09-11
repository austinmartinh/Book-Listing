package application;

import javafx.beans.property.SimpleStringProperty;

public class Character {
		
		private SimpleStringProperty name;
		private SimpleStringProperty gender;
		private SimpleStringProperty description;
		private int hashValue;
		

		private MyLinkedList<Book> bookList=new MyLinkedList<>();

	
		public Character() {
		
		}
		
		public Character(String name,String gender,String description) {
			this.name = new SimpleStringProperty(name);
			this.gender = new SimpleStringProperty(gender);
			this.description =new SimpleStringProperty(description);
			this.setHashValue(sumString(name) + sumString(description) *13);
		}
		
		/**
		 * getters and setters for the class variables
		 * @return
		 */
		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name = new SimpleStringProperty(name);
		}

		public String getGender() {
			return gender.get();
		}

		public void setGender(String gender) {
			this.gender = new SimpleStringProperty(gender);
		}

		public String getDescription() {
			return description.get();
		}

		public void setDescription(String description) {
			this.description = new SimpleStringProperty(description);
		}
		
		public int getHashValue() {
			return hashValue;
		}

		public void setHashValue(int hashValue) {
			this.hashValue = hashValue;
		}
		
		
		public MyLinkedList<Book> getBookList() {
			return bookList;
		}

		public void setBookList(MyLinkedList<Book> bookList) {
			this.bookList = bookList;
		}
		
		public String toString() {
			return "The name of the character is: " + name + "\n" +
					"The gneder of the character is: " + gender + "\n" +
					"summary description of the charaster: "  + description;
		}
		
	
		
		/**
		 * Generates a value for a string by adding the ascii character values of that string
		 * @param stringToTotal	The string whose value is to be calculated
		 * @return an integer representing the sum of ascii char values for the string
		 */
		public int sumString(String stringToTotal)
		{
			int value=0;
			for(int i = 0; i<stringToTotal.length(); i ++)
			{
				value += stringToTotal.charAt(i);
			}		
			return value;
		}
		
		@Override
		public boolean equals(Object comparedTo)
		{
			return (((Character) comparedTo).getHashValue() == this.hashValue);
		}



	}



