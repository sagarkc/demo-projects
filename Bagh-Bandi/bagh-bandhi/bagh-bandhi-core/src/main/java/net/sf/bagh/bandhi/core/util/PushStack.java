/**
 * 
 */
package net.sf.bagh.bandhi.core.util;

import java.io.Serializable;

/**
 * It is a general Stack implementation using linked list.
 * If the size is more than the capacity, the last/bottom item
 * from the stack will be removed and the new item shall be added
 * at the top. 
 * <br/><b><i>Note: </i></b>
 * <ul>
 * <li>NULL value shall not be added in the stack</li>
 * <li>Thread-safe</li>
 * </ul>
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public final class PushStack<E> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231189178649645135L;
	private final int MAX_LENGTH;
	private int size;
	private Node<E> head;
	private Node<E> last;
	
	/**
	 * Create an empty stack with maximum capacity.
	 * @param maxLength
	 */
	public PushStack(int maxLength) {
		if(maxLength <= 0){
			throw new IllegalArgumentException("MAX Length must be > 0");
		}
		MAX_LENGTH = maxLength;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}

	/**
	 * Push an element into the stack. NULL element shall not be added in the stack.
	 * @param element
	 */
	public synchronized void push(E element){
		if(null == element)
			return;
		if(null == head){
			head = new Node<E>(element, null, null);
			last = head;
			size ++;
			return;
		}
		if(size < MAX_LENGTH ){
			Node<E> temp = new Node<E>(element);
			temp.next = head;
			head.previous = temp;
			head = temp;
			size ++;
			return;
		}
		last = last.previous;
		last.next = null;
		Node<E> temp = new Node<E>(element);
		temp.next = head;
		head.previous = temp;
		head = temp;
	}
	
	public synchronized E pop(){
		if(!isEmpty()){
			E element = head.element;
			if(head.next != null){
				head = head.next;
				head.previous = null;
			} else {
				head = null;
			}
			size --;
			return element;
		}
		return null;
	}
	
	public E peek(){
		if(!isEmpty()){
			return head.element;
		}
		return null;
	}

	public int size(){
		return size;
	}

	public synchronized void clear(){
		head = null;
	}
	
	private static class Node<E> {
		E element;
		Node<E> next;
		Node<E> previous;
		
		Node(E element){
			this.element = element;
		}
		
		Node(E element, Node<E> next, Node<E> previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			if(null != element)
				return "Node value : " + element.toString();
			return super.toString();
		}
	}

	
}
