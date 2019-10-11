/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: DSAStack
 * Purpose: Contains the stack algorithm in an array
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.util.*;
public class DSAStack implements Iterable
{
    //Private classfields
    protected DSALinkedList stack;
    
    //Default constructor
    public DSAStack()
    {
        stack = new DSALinkedList(); 
    }
        
    //Returns if the stack is empty 
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    //Adds the imported object to the stack
    public void push(Object value)
    {
        stack.insertFirst(value);
    }
    
    //Removes the last object from the stack
    //LIFO: Last in First out
    public Object pop()
    {
        return stack.removeFirst();
    }

    //Returns the last object from the stack but doesn't remove it
    public Object top()
    {
        return stack.peekFirst();
    }

    public Iterator iterator()
    {
        return stack.iterator();
    }
}


