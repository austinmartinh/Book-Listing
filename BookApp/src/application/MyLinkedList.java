package application;

import java.util.Iterator;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyLinkedList<E> implements Iterable<E> {
		
		private Node<E> head;
		private int length = 0;
		
		public MyLinkedList() {
			
		}
		
		/**
	 * Takes a node and adds it to the end of the linked list
	 * @param newNode The node to be added to the list
	 */
		
		
		/**
		 * this function helps to just add the item by calling this and then adding object.
		 * @param item
		 */
		public void add(E item) {
			addToEnd(new Node<E>(item));
		}
		
		
		
		
		public void addToEnd(Node<E> newNode)		//takes a new node
		{
			Node<E> temp = head;
			if(temp==null)					//if list is empty sets new node as new head
			{
			head = newNode;
			}
			else							//list not empty
			{
				
				while(temp.getNext()!=null)	//as long as we are not at the last element in the list
				{
					temp = temp.getNext();	//move along the list
				}
				temp.setNext(newNode);		//set the next reference of last list item to point to new node
			}
			length++;
		}

	/**
	 * Takes a node and adds it to the start of the list
	 * @param newNode The node to be added
	 */
		public void addToStart(Node<E> newNode) {
					newNode.setNext(head);
					head =  newNode;
					length++;
			}
			
		public void addAfter(Node<E> newNode, E contentToAddAfter)
		{
			if(doesDataExistInList(contentToAddAfter))
			{
				Node<E>temp = findPriorByContents(contentToAddAfter);
				
				if(temp!=null) 
				{
				newNode.setNext(temp.getNext());
				temp.setNext(newNode);
				length++;
				}
				else 
				addToPosition(1,newNode);				
			}
			
			
		}
		
		
		public void addToPosition(int position, Node<E> newNode)
		{
			Node<E> temp = head;
			
			
			if(this.length > 0 && position >0) 
			{
				for(int count = 1; count < position; count++)
				{
					temp = temp.getNext();
					
				}
				newNode.setNext(temp.getNext());
				temp.setNext(newNode);

			}
			else if(this.length > 0)
			{
				newNode.setNext(head);
				head = newNode;
				
			}
			else
				head=newNode;
				
			length++;
		}
		
		/*Moves through list to see if a data point is stored in that list somewhere
		 * @param contents the data to check for in each node
		 * @return True if the data is stored in a list node,false otherwise
		 */
		public boolean doesDataExistInList(E contents)
		{
			Node<E> temp = head;
			if(temp==null)
				return false;
			//if(temp.getContents() == contents)
			if(temp.getContents().equals(contents))
				return true;
			while(temp.getNext()!=null)
			{
				temp=temp.getNext();
				if(temp.getContents() ==contents)
					return true;
			}
			
			return false;
		}
			
		/**
		 * Takes data and searches through the list linearly to find the node it is saved in, then returns that node
		 * @param contents The data to be checked for in each node
		 * @return The node containing the data, or null if no such node exists
		 */
		public Node<E> findByContents(E contents) {
			Node<E> temp = head;
			
			if(temp!=null)
			{
				if(temp.getContents()==contents)
				{
					return temp;
				}
				else
				{
					while(temp.getNext()!=null &&temp.getNext().getContents()!=contents)
				{
						temp=temp.getNext();
					}
					return temp.getNext();
				}
			}
			else
				return null;
		}
		
		public Node<E> findByTest(Predicate<E> p) {
			Node<E> temp = head;
			
			if(temp!=null)
			{
				if(p.test(temp.getContents()))
				{
					return temp;
				}
				else
				{
					while(temp.getNext()!=null && !p.test(temp.getNext().getContents()))
				{
						temp=temp.getNext();
					}
					return temp.getNext();
				}
			}
			else
				return null;
		}
		
		
		
		public Node<E> findByPosition(int position)
		{
			Node<E> temp = head;
			
			
			if(this.length > 0) {
				for(int count = 0; count < position; count++)
				{
					temp = temp.getNext();
					
				}
				return temp;
			}
			else return null;
		}
		
	/**Takes a data point and searches the list until the node prior to the node holding the data is reached, then returns that node
	 * Used in conjunction with the remove methods
	 * 
	 * @param contents data to search for
	 * @return Null if no prior node exists, the node prior to the node holding the data if found
	 */
		public Node<E> findPriorByContents(E contents)
		{
			
			
			Node<E> temp = head;
			if(temp!=null)		//If the list is not empty
			{
				if(temp.getContents()==contents)	//if the 1st item in list is a match
				{
					return null;	//return null as no prior node exists
				}
				else
				{		//while the next node is not null and the contents of the next node do not match
					while(temp.getNext()!=null &&temp.getNext().getContents()!=contents) 
					{	//iterate to the next node
						temp=temp.getNext();
					}
					return temp;	//once the next node is null or a match, return the node we are on
				}
			}
			else
				return null; //If list is empty or Item not in list
		}

	/**
	 * Takes data and and calls the find method to locate the node holding that data
	 * then set the next reference to refer to the node following it, thus orphaning the requested data
	 * @param contents	The data to be searched for and removed from the list
	 */
		public boolean remove(E contents) {
			if(doesDataExistInList(contents)) 
			{		
				Node<E> temp = findPriorByContents(contents);		//Attempts to locate node before node containing requested data
				
				if(temp!= null) {			//if temp has a node, links prior and following nodes
					temp.setNext(temp.getNext().getNext());
					length--;
					return true;
				}
				else if(temp==null) {
					head=head.getNext(); //   If node removed, code not reached, if node not removed, I do not need to adjust head
					length--;
					return true;
				}
			}
			return false;
			
		}
		

	/**
	 * Iterates through the linked list and generates a tostring of all list objects
	 */
		public String show()
		{
			Node<E> temp = head;
			String allItems="";
		
			while(temp.getNext()!=null)
			{
				//System.out.println(temp.getContents().toString());
				allItems +=temp.getContents().toString() + "\n";
				temp = temp.getNext();
			}
		//	System.out.println(temp.getContents().toString());
			allItems += temp.getContents().toString();
			
			return allItems;
			
		}

	/**
	 * Getter for length of list
	 * @return length integer representing length of list
	 */
		public int getLength() {
			return length;
		}
		
		public void setLength(int newLength)
		{
			if(newLength >=0)
				this.length = newLength;
		}

		/**
	 * Getter for head node
	 * @return
	 */
		public Node<E> getHead()
		{
			return head;
		}


		public void setHead(Node<E> newHead)
		{
			this.head = newHead;
			
		}
		
		public ObservableList<E> getObservableList(){
			ObservableList<E> list = FXCollections.observableArrayList();
			Node<E> temp = head;
			for(int i=0;i<length;i++) {
				
				list.add(temp.getContents());
				//head.setNext(head);
				temp=temp.getNext();
			}
			return list;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return new myLinkedListIterator<E>(head);
		}
	}

//================================

class myLinkedListIterator<E> implements Iterator<E> {
	Node<E> temp=null;
	
	
	public myLinkedListIterator(Node<E> head) {
		temp=head;
	}

	@Override
	public boolean hasNext() {
		
		return temp!=null;
	}

	@Override
	public E next() {
		Node<E> temp2=temp;
		
		temp=temp.getNext();
		return temp2.getContents();
	}
	
}




