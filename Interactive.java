import java.util.*;


/* This class contains the methods needed for the programs interactive mode */
public class Interactive
{
    private double likingProb;
    private double followingProb;
   

    public Interactive(DSAGraph graph)
    {
        /* Default probs of 1 */
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