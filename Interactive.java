import java.util.*;


/* This class contains the methods needed for the programs interactive mode */
public class Interactive
{
    private DSAGraph people;
    private double likingProb;
    private double followingProb;
   

    public Interactive(DSAGraph graph)
    {
        /* Default probs of 1 */
        people = graph;
        likingProb = 1;
        followingProb = 1;
    }

    /*(2) Set probabilities */
    public void setProbs()
    {
        /* Sets the classfields */
        this.likingProb = validateDouble("Please enter a probability for liking a post", 0, 1);
        this.followingProb = validateDouble("Please enter a prob between 0 and 1 for following", 0, 1); 
    }

    /* (3) Node operations (find, insert, delete) */

    public Object findPerson(String name)
    {
        if(!people.hasVertex(name))
        {
            throw new IllegalArgumentException("ERROR:Person doesn't exist in the network");
        }

        return people.getVertex(name);
    }

    public void insertPerson(String name)
    {
        Person person;
        /* Throws an exception if the person is already in the graph */
        if(people.hasVertex(name))
        {
            throw new IllegalArgumentException("ERROR: Person already in the network");
        }

        person = new Person(name);
        people.addVertex(name, person);
    }

    public void removePerson(String name)
    {
        /* Throws an exception if the person isn't found in the graph */
        if(!people.hasVertex(name))
        {
            System.out.println("Name: " + name + "Vertex: " + people.hasVertex(name));
            throw new IllegalArgumentException("ERROR: Person doesn't exist in the network");
        }

        people.removeVertex(name);
        
        /* NEED TO IMPLEMENT REMOVE VERTEX INTO GRAPH CLASS */
    }


    /* (4) Edge operations (like/follow - add, remove) */

    /* PersonOne will be followed by personTwo */
    public void addFollow(String nameOne, String nameTwo)
    {
        if(!people.hasVertex(nameOne) || !people.hasVertex(nameTwo))
        {
            throw new IllegalArgumentException("ERROR: Can't add a follow");
        }
        people.addEdge(nameOne, nameTwo);    
    }

    public void removeFollow(Person personOne, Person personTwo)
    {
        /* remove each node from the other's links list */
    }

    public void addLike(Person personOne, Person personTwo, Post post)
    {

        
        /* need to iterate posts list in the nameOne person node 
            then add the nameTwo to the post's likedBy LL 
            then incremenet likes count */
    }

    public void removeLike(Person personOne, Person personTwo, Post post)
    {
        /* Need to iteratae posts list in the nameOne person node
            then remove the nameTwo from the post's likedBy LL
            then decrement the likes count */
    }

    /* (5) New post */
    public void addPost(String name, String message)
    {
        /* Create a new post object, then insertLast into posts LL in the person 
            object */
    }

    /* (6) Display network */
    /* Call the matrix display method? 
        but then need to somehow visually display the posts ect*/

    /* Taken from my oopd assignment */
    private static double validateDouble(String prompt, double min, double max)
    {
        Scanner sc = new Scanner(System.in);
        double value = min - 1;
        String strOut = prompt;
        String error;

        do
        {
            try
            {
                System.out.println(strOut);
                value = sc.nextDouble();
                error = "Error, please enter a value between " + min + " and "
                    + max;
				strOut = error + "\n" + prompt;
            }
            catch(InputMismatchException e)
            {
                sc.nextLine();
                error = "Error, input must be a number";
                strOut = error + "\n" + prompt;
            }   
        } while(value < min || value > max);   
        return value; 
    } 
}