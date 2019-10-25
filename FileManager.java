/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: FileManager
 * Purpose: Contains methods for serialization
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.io.*;

public class FileManager
{
    public static void loadNetwork(String fileName, DSAGraph graph)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] split;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();

            while(line != null)
            {
                split = line.split(":");
                if(split.length == 1)
                {
                    graph.addVertex(split[0]);
                }
                else if(split.length == 2)
                {
                    graph.addVertex(split[0]);
                    graph.addVertex(split[1]);
                    graph.addEdge(split[0], split[1]);
                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            try
            {
                fileStrm.close();                
            }
            catch(IOException ex)
            {
                //Can't do anything more, file won't close
            }
        }
    }
/*
    public static void loadEvents(String fileName, DSAGraph graph)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
		int lineNum; //This keeps track of the line number(Useful for errors)
		int added = 0; //This keeps track of successfully added ships
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
			
            line = bufRdr.readLine();
			lineNum = 0;
			
            while(line != null)
            {	
                try
                {
                    String[] bits = line.split(":");
                    if (bits.length == 1 && bits[0].equals(""))
                    {
                        throw new IllegalArgumentException("Empty line detected");
                    }

                    switch (Character.toUpperCase(bits[0].charAt(0))) 
                    {
                        case 'P':
                            processPost(bits);
                            break;
                        case 'A':
                            processAdd(bits);
                            break;
                        case 'R':
                            processRemove(bits);
                            break
                        default:
                            throw new IllegalArgumentException("Invalid type detected");
                    }
                    lineNum++;
                }
				catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage() + "\n\tLine number: "
							+ lineNum + "\n\tLine: " + line);
                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            try
            {
                if(fileStrm != null)
                {
                    fileStrm.close();
                }
            }
            catch(IOException ex)
            {
                //file won't close, can't do anything
            }
        }
    }
*/    
}