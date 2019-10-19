//Need graph nodes
//List of edges (different modes of transportations ect)
//different times ect

//create vertex using a linked list, then create DSAGraphVertex

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
        private Object value;
        private DSALinkedList links;
        private boolean visited;

        public DSAGraphVertex(String label, Object value)
        {
            this.label = label;
            this.value = value;
            this.links = new DSALinkedList();
            this.visited = false;
        }            

        public String getLabel()
        {
            return this.label;
        }

        public Object getValue()
        {
            return this.value;
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
            for(Iterator iter = links.iterator(); iter.hasNext();)
            {
                adjacent += ((DSAGraphVertex)iter.next()).getLabel() + " ";
            }
            return this.getLabel() + " : " + adjacent; 
        }
    }

    //Private class fields
    private DSALinkedList vertices;
    
    public DSAGraph()
    {
        this.vertices = new DSALinkedList();
    }

    public void addVertex(String label, Object value)
    {
        //If statement, makes sure we don't get any duplicates
        if(!this.hasVertex(label))
        {
            this.vertices.insertLast(new DSAGraphVertex(label, value));
        }
    }

    public void removeVertex(String label)
    {
        DSAGraphVertex vertex = getVertex(label);
        String edgeName;
        if(this.hasVertex(label))
        {
            Iterator iter = vertex.getAdjacent().iterator();
            edgeName = ((DSAGraphVertex)iter.next()).getLabel();
            while(iter.hasNext())
            {
                removeEdge(label, edgeName);
                edgeName = ((DSAGraphVertex)iter.next()).getLabel();
            }
            this.vertices.remove(label);
        }
    }

    public void addEdge(String labelOne, String labelTwo)
    {   
        /* Get the vertex value that matches with the imported label from the 
            vertices list */
        DSAGraphVertex vertexOne;
        DSAGraphVertex vertexTwo;
        vertexOne = getVertex(labelOne);
        vertexTwo = getVertex(labelTwo);

        /* Adds each vertex to the other vertex's links list */
        vertexOne.addEdge(vertexTwo);
        /* vertexTwo.addEdge(vertexOne); */
    }

    public void removeEdge(String labelOne, String labelTwo)
    {
        DSAGraphVertex vertexOne;
        DSAGraphVertex vertexTwo;
        vertexOne = getVertex(labelOne);
        vertexTwo = getVertex(labelTwo);

        vertexOne.removeEdge(vertexTwo);
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
            System.out.println(temp);
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
        for(Iterator iter = vertices.iterator(); iter.hasNext();)
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
    public DSAGraphVertex getVertex(String label)
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
        return getVertex(label).getAdjacent();
    } 

    public void displayAsList()
    {
        for(Iterator iter = vertices.iterator(); iter.hasNext();)
        {
            System.out.println(iter.next().toString());
        }
    }

    /* Methods checks if the imported label is in the links list of the current vertex */ 
    public boolean isAdjacent(String labelOne, String labelTwo)
    {
        DSAGraphVertex temp;
        DSAGraphVertex vertexOne = getVertex(labelOne);

        boolean isAdjacent = false;
        //Iterates through the adjacent list of vertex one and checks label two against it
        for(Iterator iter = vertexOne.getAdjacent().iterator(); iter.hasNext();)
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
        for(Iterator iter = vertices.iterator(); iter.hasNext();)
        {
            temp1 = (DSAGraphVertex)iter.next();
            System.out.print(" " + temp1.getLabel());
            
            output += "\n" + temp1.getLabel() + " ";

            for(Iterator iter2 = vertices.iterator(); iter2.hasNext();)
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

   
}
