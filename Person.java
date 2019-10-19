import java.util.*;
public class Person
{
    private String name;
    private DSALinkedList posts; /* LinkedList containing post objects */
    private DSALinkedList following; //MIGHT NOT NEED 
    private DSALinkedList followedBy;

    public Person(String name)
    {
        this.name = name;
        posts = new DSALinkedList();
        following = new DSALinkedList();
        followedBy = new DSALinkedList();
    }

    public void addFollow(Person person)
    {
        followedBy.insertLast(person); 
    }

    public void removeFollow()
    {
    }
    public String getName()
    {
        return name;
    }

    public Object findPerson(String name, DSALinkedList list)
    {
        Person temp;
        Iterator iter = list.iterator();
        temp = (Person)iter.next();
        while(iter.hasNext() && !name.equals(temp.getName()));
        {
            temp = (Person)iter.next();
        }
        if(!name.equals(temp.getName()))
        {
            throw new IllegalArgumentException(name + "was not found!");
        }

        return temp;
    }

    /* 
    public Object removePerson(String label, DSALinkedList list)
    {
        
    }
    */

    public String toString()
    {
        Post post;
        String postString = "";
        Iterator iter = posts.iterator();
        post = (Post)iter.next();
        while(iter.hasNext())
        {
            postString += post.getMessage();   
            post = (Post)iter.next();
        }

        return postString;
    }


}