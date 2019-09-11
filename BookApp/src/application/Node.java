package application;

public class Node <E> {
 
	private Node<E> next;
	private E contents;
	
	public Node()
	{
		
	}
	
	
	public Node(E newContents)
	{
		this.contents = newContents;
	}
	
	
	public E getContents()
	{
		return contents;
	}
	
	
	public Node<E> getNext()
	{
		return next;
	}
	
	
	public void setContents(E contents)
	{
		this.contents = contents;
	}
	
	
	public void setNext(Node<E> next)
	{
		this.next = next;
	}
	
	
	
}

