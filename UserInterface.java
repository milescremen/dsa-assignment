import java.util.*;
/* This class contains all the user interactive methods such as menus ect */
public class UserInterface
{
    public static void menu(DSAGraph people)
    {
        String menuPrompt;
        int menuValue;
        Interactive interactive = new Interactive(people);
        menuPrompt = "Please choose an option from the following\n" +
            "(1) Load network\n" +
            "(2) Set probabilities\n" +
            "(3) Node operations (find, insert, delete)\n" + 
            "(4) Edge operations (follow - add, remove)\n" + 
            "(5) New post\n" + 
            "(6) Display network\n" +
            "(7) Display statistics\n" +
            "(8) Update (run a timestep)\n" +
            "(9) Save network\n" +
            "(10) Exit";

        FileManager.loadNetwork("network.txt", people);
        do
        {
            menuValue = validateInteger(menuPrompt, 1, 10);
            switch(menuValue)
            {
                /* Load Network */
                case 1:
                    //prompt = "Please enter the name of the network file";
                    // FileManager.loadNetwork(validateString, people);
                    FileManager.loadNetwork("network.txt", people);
                    break;
                /* Set Probabilites */
                case 2:
                    interactive.setProbs();
                    break;
                /* Node operations */
                case 3:
                    nodeMenu(interactive, people);
                    break;
                /* Edge operations */
                case 4:
                    edgeMenu(interactive, people);
                    break; 
                /* New post */
                case 5: 
                    break;
                /* Display Network */
                case 6:
                    people.displayAsList();

                    break;
                /* Display statistics */
                case 7:
                    break;
                /* Update (run a timestep */
                case 8:
                    break;
                /* Save network */
                case 9:
                    break;
                /* Exit */
                case 10:
                    System.out.println("Exiting...");
                    break;
            }
        } while(menuValue != 10);
    }

    private static void nodeMenu(Interactive interactive, DSAGraph people)
    {
        String menuPrompt;
        int menuValue;
        String name;

        menuPrompt = "Please select from the following options\n" +
            "(1) Find a person\n" +
            "(2) Insert a person\n" +
            "(3) Delete a person\n" +
            "(4) Return to previous menu";
        do
        {
            menuValue = validateInteger(menuPrompt, 1, 4);

            switch(menuValue)
            {
                /* Find a person */
                case 1:
                    name = validateString("Please enter a name");
                    System.out.println(people.getVertex(name).toString()); 
                    break;
                /* Insert a person */
                case 2:
                    name = validateString("Please enter a name");
                    people.addVertex(name);
                    break;
                /* Delete a person */
                case 3:
                    name = validateString("Please enter a name");
                    people.removeVertex(name);
                    break;
                case 4:
                    System.out.println("Returning to previous menu");
                    break;
            }
        } while(menuValue != 4);
    }

    private static void edgeMenu(Interactive interactive, DSAGraph people)
    {
        String menuPrompt;
        int menuValue;
        String nameOne; 
        String nameTwo;

        menuPrompt = "Please select from the following options\n" +
            "(1) Add a follow\n" +
            "(2) Remove a follow\n" +
            "(3) Return to previous menu";
        do
        {
            menuValue = validateInteger(menuPrompt, 1, 3);

            switch(menuValue)
            {
                /* Add a follow */
                case 1:
                    nameOne = validateString("Please enter a name of the person to follow");
                    nameTwo = validateString("Please enter a name of the person who wants to follow " + nameOne);
                    people.addEdge(nameOne, nameTwo);
                    break;
                /* Remove a follow */
                case 2:
                    nameOne = validateString("Please enter a name of the person to remove the follow");
                    nameTwo = validateString("Please enter a name of the person who wants to stop following " + nameOne);
                    people.removeEdge(nameOne, nameTwo); 
                    break;
                case 3:
                    System.out.println("Returning to previous menu");
                    break;
            }
        } while(menuValue != 3);
    }

    private static int validateInteger(String prompt, int min, int max)
    {
        Scanner sc = new Scanner(System.in);
        int value = min - 1;
        String strOut = prompt;
        String error;

        do
        {
            try
            {
                System.out.println(strOut);
                value = sc.nextInt();
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

    
    private static String validateString(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String strOut;
        String error;
        String fileName;
        fileName = null;
        error = null;
        do 
        {
            strOut = prompt;
            try
            {
                System.out.println(strOut);
                fileName = sc.nextLine();
                strOut = error + "\n" + prompt;
            }
            catch(InputMismatchException e)
            {
                sc.nextLine();  
                error = "Error, input must be of type STRING";
                strOut = error + "\n" + prompt;
            }
        } while(fileName == null);
        return fileName;
    }
}