/*******************************************************************************
 * Author: Miles Cremen 19142852
 * File name: FileManager
 * Purpose: Contains methods for serialization
 * Last Edited: 15/08/2019
 ******************************************************************************/
import java.io.*;

public class FileManager
{
    public static void readFile(String fileName, DSAGraph graph)
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
                    graph.addVertex(split[0], split[0]);
                }
                else if(split.length == 2)
                {
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

}