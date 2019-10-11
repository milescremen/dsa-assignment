/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: DSALinkedList
 * Purpose: Contains algorithm for the double-ended doubly linked list
 *          Also contains an iterator 
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.util.*;
import java.io.*;
public class DSALinkedList implements Iterable, Serializable
{

    //Creates an iterator object using the current list
    public Iterator iterator() 
    {
        return new DSALinkedListIterator(this);
    }
   
    //Contains methods allowing u to iterate through the list
    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;
       
        //Alternate constructor 
        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        //Returns true if there is another node in the list
        public boolean hasNext()
        {
            return (iterNext != null);
        }

        //returns the next node object
        public Object next()
        {
            Object value;
            if(iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }
        //Not used
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    //Private class containing all methods relevant to the node
    private class DSAListNode implements Serializable
    {
        //Doubly linked so contains next and prev classfields
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;
        
        //Alternate constructor that creates a new node
        public DSAListNode(Object value)
        {
            this.value = value;
            next = null;
            prev = null;
        }

        //Returns the value of the node
        public Object getValue()
        {
            return value;
        }
       
        //returns the next node object
        public DSAListNode getNext() 
        {
            return next;
        }

        //returns the previous node object
        public DSAListNode getPrev()
        {
            return prev;
        }

        //Overrides the current value of the node
        public void setValue(Object value)
        {
            this.value = value;
        }

        //links the next node to the current node
        public void setNext(DSAListNode next)
        {   
            this.next = next;
        }

        //links the previous node to the current node
        public void setPrev(DSAListNode prev)
        {
            this.prev = prev;
        }
    }
    
    //Private class fields for DSALinkedList
    private DSAListNode head;
    private DSAListNode tail;

    //Default constructor
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }    
   
    //Creates a new node using the imported Object and inserts it at the 
    //Start of the list
    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue); 
        if(isEmpty())
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
    }   
   
    //Creates a new node using the imported object and inserts it at the end of
    //the list
    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty())
        {
            head = newNd;
            tail = newNd;
        } 
        else
        {
            newNd.setPrev(tail);
            tail.setNext(newNd);
            tail = newNd;
        }  
    }
   
    //returns true if the list is empty
    public boolean isEmpty()
    {
        return (head == null);
    }

    //Returns the value of the first node
    public Object peekFirst()
    {
        Object nodeValue;
        if(isEmpty())
        {
            throw new IllegalArgumentException("ERROR: List is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }   
        return nodeValue;
    } 
   
    //Returns the value of the last node
    public Object peekLast()
    {   
        Object nodeValue;
        if(isEmpty())
        {
            throw new IllegalArgumentException("ERROR: List is empty");
        }
        else
        {   
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }
   
    //Returns the value of the first node and removes it by changing the 
    //Prev of the next node to null and setting the next node to the head
    public Object removeFirst()
    {   
        Object nodeValue;
        if(isEmpty())
        {
            throw new IllegalArgumentException("ERROR: List is empty");
        }
        else if(head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = head.getNext();
        }
        else
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        return nodeValue;
    } 

    //Returns the value of the last node and removes it by changing the 
    //next of the previous node to null and setting the previous node to the tail
    public Object removeLast()
    {
        Object nodeValue;
        if(isEmpty())
        {
            throw new IllegalArgumentException("ERROR: List is empty");
        }
        else
        {   
            nodeValue = tail.getValue();
            tail = tail.getPrev();
        }
        return nodeValue;
    }
}



















    
