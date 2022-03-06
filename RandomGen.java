import java.util.Random;

/**
 * A simple random number generator
 *
 * @author     asbestos
 * @version    1.0
 */
public class RandomGen {

    //Initializes the Random object
    private Random randomGen;

    /**
     * Creates a new Random object from the Java library
     */
    public RandomGen() {
        randomGen = new Random();
    }

    /**
     * Generates a random number between two values
     * @param numberMin the lowest number it will generate between
     * @param numberMax the highest number it will generate between
     * @return returns the generated number
     */
    public int randomNumber(int numberMin, int numberMax) {
        int generatedNumber = randomGen.nextInt(numberMax + 1 - numberMin) + numberMin;
        return generatedNumber;
    }
}
