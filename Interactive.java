
public class Interactive
{
    private DSAGraph people;
    private int likingProb;
    private int followingProb;

    public Interactive()
    {
        /* Default probs of 0.5 */
        people = new DSAGraph();
        likingProb = 0.5;
        followingProb = 0.5;
    }

    public void menu()
    {
        String menuPrompt;
        int menuValue;

        menuPrompt = "Please choose an option from the following\n" +
            "(1) Load network\n" +
            "(2) Set probabilities\n" +
            "(3) Node operations (find, insert, delete)\n" + 
            "(4) Edge operations (like/follow - add, remove)\n" + 
            "(5) New post\n" + 
            "(6) Display network\n" +
            "(7) Display statistics\n" +
            "(8) Update (run a timestep)\n" +
            "(9) Save network\n" +
            "(10) Exit";

        do
        {
            menuValue = validateInteger(menuPrompt, min, max);
            switch(menuValue)
            {
                /* Load Network */
                case 1:
                    FileManager("file.txt", people);
                    break;
                /* Set Probabilites */
                case 2:
                    break;
                /* Node operations */
                case 3:
                    break;
                /* Edge operations */
                case 4:
                    break; 
                /* New post */
                case 5: 
                    break;
                /* Display Network */
                case 6:
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



    public void setProbs()
    {
        /* Sets the classfields */
        this.likingProb = validateDouble("Please enter a probability for liking a post", 0, 1);
        this.followingProb = validateDouble("Please enter a prob between 0 and 1 for following", 0, 1); 
    }

    /* Taken from my oopd assignment */
    private double validateDouble(String prompt, double min, double max)
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