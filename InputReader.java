import java.util.Scanner;
import java.util.HashSet;

/**
 * InputReader reads typed text input from the standard text terminal.
 * The text typed by a user is then chopped into words, and a set of words
 * is provided.
 *
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
public class InputReader {
    private Scanner reader;

    public InputReader() {
        reader = new Scanner(System.in);
    }

    public HashSet<String> getInput() {
        System.out.print(" > ");
        String inputLine = reader.nextLine().trim().toLowerCase();

        String[] wordArray = inputLine.split(" ");
        HashSet<String> words = new HashSet<>();
        for(String word : wordArray) {
            words.add(word);
        }

        return words;
    }
}
