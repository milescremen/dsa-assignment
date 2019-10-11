/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: DSAQueue
 * Purpose: Abstract class for the two queues, circular and shuffling
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.util.*;
public class DSAQueue implements Iterable
{
    //Private classfields
    protected DSALinkedList queue; 

    //Default Constructor
    public DSAQueue()
    {
        queue = new DSALinkedList();
    }

    //Returns if the queue is empty
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

   //PURPOSE: Adds the imported value to the start of the queue, to the tail
    //FIFO: First in First Out
    public void enqueue(Object value)
    {
        queue.insertLast(value);
    }

    //PURPOSE: Removes the first element in the queue (located at the head)
    public Object dequeue()
    {
        return queue.removeFirst();
    }

    //PURPOSE: returns the value at the head of the queue (but doesn't remove it)
    public Object peek()
    {
        return queue.peekLast();
    }

    public Iterator iterator()
    {
        return queue.iterator();
    }
}
