/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: DSAGraph
 * Purpose: Contains an implementation of graph and the inner class DSAGraphVertex
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.util.*;
public class DSAGraph
{
    //private inner class
    //Looks after its label, value and what its connected to
    private class DSAGraphVertex
    {
        //Private classfields
        private String label;
        private DSALinkedList posts;
        private DSALinkedList links;
        private boolean visited;

        public DSAGraphVertex(String label)
        {
            this.label = label;
            this.posts = new DSALinkedList(); 
            this.links = new DSALinkedList();
            this.visited = false;
        }            

        public String getLabel()
        {
            return this.label;
        }

        public DSALinkedList getPosts()
        {
            return this.posts;
        }

        public DSALinkedList getAdjacent()
        {   
            return this.links;
        }

        public void addEdge(DSAGraphVertex vertex)
        {
            //If they aren't adjacent, then there isn't a link between them yet
            if(!isAdjacent(this.getLabel(), vertex.getLabel()))
            {
                this.links.insertLast(vertex);
            }
        }

        public void removeEdge(DSAGraphVertex vertex)
        {
            if(isAdjacent(this.getLabel(), vertex.getLabel()))
            {
                links.remove(vertex);
            }
        }

        public void setVisited()
        {
            this.visited = true;
        }

        public void clearVisited()
        {
            this.visited = false;
        }

        public boolean getVisited()
        {
            return this.visited;
        }

        public String toString()
        {
            String adjacent = "";
            Iterator iter = links.iterator();
            while(iter.hasNext())
            {
                adjacent += ((DSAGraphVertex)iter.next()).getLabel() + " ";
            }
            return this.getLabel() + "\t\tFollowers: " + adjacent; 
        }
    }

    //Private class fields
    private DSALinkedList vertices;
    
    public DSAGraph()
    {
        this.vertices = new DSALinkedList();
    }

    public void addVertex(String label)
    {
        //If statement, makes sure we don't get any duplicates
        if(!this.hasVertex(label))
        {
            this.vertices.insertLast(new DSAGraphVertex(label));
        }
    }

    public void removeVertex(String label)
    {
        DSAGraphVertex inVertex = (DSAGraphVertex)getVertex(label);
        String edgeName;
        DSAGraphVertex temp;
        if(this.hasVertex(label))
        {
            Iterator iter = vertices.iterator();
            while(iter.hasNext())
            {
                temp = (DSAGraphVertex)iter.next();
                edgeName = temp.getLabel();
                System.out.println(edgeName);

                if(hasEdge(edgeName, label))
                {
                    removeEdge(edgeName, label);
                }
            }

            vertices.remove(inVertex);
        }
    }

    public void addEdge(String labelOne, String labelTwo)
    {   
        /* Get the vertex value that matches with the imported label from the 
            vertices list */
        DSAGraphVertex vertexOne;
        DSAGraphVertex vertexTwo;
        vertexOne = (DSAGraphVertex)getVertex(labelOne);
        vertexTwo = (DSAGraphVertex)getVertex(labelTwo);

        /* Adds each vertex to the other vertex's links list */
        vertexOne.addEdge(vertexTwo);
        /* vertexTwo.addEdge(vertexOne); */
    }

    public void removeEdge(String labelOne, String labelTwo)
    {
        DSAGraphVertex vertexOne;
        DSAGraphVertex vertexTwo;
        vertexOne = (DSAGraphVertex)getVertex(labelOne);
        vertexTwo = (DSAGraphVertex)getVertex(labelTwo);

        vertexOne.removeEdge(vertexTwo);
    }
    
    /* Checks if labelTwo is in labelOnes links list */
    public boolean hasEdge(String labelOne, String labelTwo)
    {
        boolean hasEdge = false;
        DSAGraphVertex tempOne;
        DSAGraphVertex tempTwo;

        tempOne = (DSAGraphVertex)getVertex(labelOne);

        Iterator iter = tempOne.getAdjacent().iterator();
        while(iter.hasNext())
        {
            tempTwo = (DSAGraphVertex)iter.next();
            if(labelTwo.equals(tempTwo.getLabel()))
            {
                hasEdge = true;
            }
        }
        System.out.println("hasEdge: " + labelOne + " " + labelTwo + " " + hasEdge);
        return hasEdge;
    }

    /* Iterates through the vertices stored in the linked list and 
        returns true if there is a match found with the imported label */
    public boolean hasVertex(String label)
    {
        DSAGraphVertex temp;
        boolean hasVertex = false;
        Iterator iter = vertices.iterator();
        //Can't use iter.next() to check and stop iteration early because
        //It actually iterates to the next value
        //Could fix using a temp variable but for now cbf
        while(iter.hasNext())// && !iter.next().equals(label))
        {
            temp = (DSAGraphVertex)iter.next();
            //System.out.println(temp);
            if(temp.getLabel().equals(label))
            {
                hasVertex = true;
            }
        }
        return hasVertex;
    }

    /* Iterates through vertices stored in the linked list, adding 1 to the count
    for each object, Alternatively I will add a count classfield and increment 
    the count when adding a vertex */
    public int getVertexCount()
    {
        int count = 0;
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            count++;
            iter.next();
        }
        return count;
    }
    
    /*Iterates through vertices stored in the linked list, checking
        if they match the imported value, if so it will return that vertex
        - WHILE loop used because i don't want to keep looping if vertex is
        found - */
    public Object getVertex(String label)
    {
        DSAGraphVertex found = null;
        DSAGraphVertex currVertex;
        Iterator iter = vertices.iterator();
        while(iter.hasNext()) // && !iter.next().equals(label))
        {
            currVertex = (DSAGraphVertex)iter.next();
            if(currVertex.getLabel().equals(label))
            {
                found = currVertex;
            }
        }
        if(found == null) 
        {
            throw new IllegalArgumentException(label + " not found");
        }
        return found;
    }

    /*calls the get vertex method which returns the vertex object that matches
        the imported label, and then gets the returned list containing the 
        adjacent vertices with getAdjacent */
    public DSALinkedList getAdjacent(String label)
    {
        DSAGraphVertex vertex = (DSAGraphVertex)getVertex(label);
        return vertex.getAdjacent();
    } 

    public void displayAsList()
    {
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next().toString());
        }
    }

    /* Methods checks if the imported label is in the links list of the current vertex */ 
    public boolean isAdjacent(String labelOne, String labelTwo)
    {
        DSAGraphVertex temp;
        DSAGraphVertex vertexOne = (DSAGraphVertex)getVertex(labelOne);

        boolean isAdjacent = false;
        //Iterates through the adjacent list of vertex one and checks label two against it
        Iterator iter = vertexOne.getAdjacent().iterator();
        while(iter.hasNext())
        {   
            temp = (DSAGraphVertex)iter.next();
            if(temp.getLabel().equals(labelTwo))
            {
                isAdjacent = true;
            }
        }
        return isAdjacent;
    }

    public void displayAsMatrix()
    {
        DSAGraphVertex temp1;
        DSAGraphVertex temp2;
        String output = "";
        DSALinkedList list;
        System.out.print(" ");
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            temp1 = (DSAGraphVertex)iter.next();
            System.out.print(" " + temp1.getLabel());
            
            output += "\n" + temp1.getLabel() + " ";
            Iterator iter2 = vertices.iterator();
            while(iter2.hasNext())
            {
                temp2 = (DSAGraphVertex)iter2.next();
                if(isAdjacent(temp1.getLabel(), temp2.getLabel()))
                {
                    output += 1 + " ";
                }
                else
                {
                    output += 0 + " ";
                }
            }
        }
        System.out.println(output);
    }

    public void addPost(String name, String message)
    {
        DSAGraphVertex person;
        Post newPost;
        if(!hasVertex(name))
        {
            throw new IllegalArgumentException("ERROR: Person doesn't exist in the network");
        }
        /* if the person is in the graph, then we create a newPost object and store it in the posts linkedlist */
        person = (DSAGraphVertex)getVertex(name);
        newPost = new Post(message);
        person.posts.insertLast(newPost);
    }

    public void removePost(String name, String message)
    {
        DSAGraphVertex person;
        if(!hasVertex(name))
        {
            throw new IllegalArgumentException("ERROR: Person doesn't exist in the network");
        }

        person = (DSAGraphVertex)getVertex(name);
        person.posts.remove(message); /* this doesn't work im sure */

    
    }
   
}
