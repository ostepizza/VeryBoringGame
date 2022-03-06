import java.util.HashSet;

/**
 * GameEngine does all the things required for the game to be a game.
 * The mechanics of the game are in a while loop
 *
 * @author     asbestos
 * @version    0.6
 */
public class GameEngine {
    public static String version;
    private final InputReader reader;
    private final InputReader readerDiff;
    private final RandomGen randomGen;
    private final Animations animations;
    private int score;

    /**
     * Constructs all the required objects and initializes version & score
     * Then runs the runEngine method.
     */
    public GameEngine() {
        // Constructs all necessary objects
        reader = new InputReader();
        readerDiff = new InputReader();
        randomGen = new RandomGen();
        animations = new Animations();
        // The version number displayed to the user:
        version = "0.6";
        // Keeps count of the score:
        score = 0;
    }

    /**
     * Runs the game and it's mechanics
     */
    public void runEngine(){
        boolean finished = false;

        // Runs the intro from Animations
        clearScreen();
        animations.intro();
        loadingBar();
        clearScreen();

        // Sets initial difficulty settings
        // Sets the middle number (what over or under is based on):
        int difficultyMiddle = 5;
        // Sets how much the score will increase at a correct guess:
        int difficultyScorePlus = 2;
        // Sets how much the score will decrease at an incorrect guess:
        int difficultyScoreMinus = 1;
        // Sets how much the score will increase at a correct guess that the number is equal to the middle number:
        int difficultyScoreSamePlus = 10;
        // Sets how much the score will decrease at an incorrect guess that the number is equal to the middle number:
        int difficultyScoreSameMinus = 5;
        // Sets the lowest number that can be generated
        int difficultyNumberMin = 0;
        // Sets the highest number that can be generated
        int difficultyNumberMax = 10;

        openingMessage();

        // The mechanics of the game in a while loop using if-else statements
        // If the user types a word that's listed the related code runs
        while(!finished) {
            HashSet<String> input = reader.getInput();

            int yourNumber;
            if (input.contains("exit")) {
                finished = true;
            } else if (input.contains("over")) {
                // Generates a random number. If the number is higher than the number set
                // by the difficulty setting, the user gets two points. Otherwise, they lose
                // a point
                yourNumber = randomGen.randomNumber(difficultyNumberMin, difficultyNumberMax);
                clearScreen();
                Main.line();
                write("You chose over");
                write("Computer generated number " + yourNumber);
                if (yourNumber > difficultyMiddle) {
                    write("You win, +" + difficultyScorePlus + " score");
                    score = score + difficultyScorePlus;
                } else {
                    write("You lose, -" + difficultyScoreMinus + " score");
                    score = score - difficultyScoreMinus;
                }
                Main.line();
                write("Current score: " + score);
                Main.line();
            } else if (input.contains("under")) {
                // Generates a random number. If the number is lower than the number set
                // by the difficulty setting, the user gets two points. Otherwise, they lose
                // a point
                yourNumber = randomGen.randomNumber(difficultyNumberMin, difficultyNumberMax);
                clearScreen();
                Main.line();
                write("You chose under");
                write("Computer generated number " + yourNumber);
                if (yourNumber < difficultyMiddle) {
                    write("You win, +" + difficultyScorePlus + " score");
                    score = score + difficultyScorePlus;
                } else {
                    write("You lose, -" + difficultyScoreMinus + " score");
                    score = score - difficultyScoreMinus;
                }
                Main.line();
                write("Current score: " + score);
                Main.line();
            } else if (input.contains("same")) {
                // Generates a random number. If the number is the same as the number set
                // by the difficulty setting, the user gets points based on the difficulty.
                // Otherwise, they lose points based on the difficulty.
                yourNumber = randomGen.randomNumber(difficultyNumberMin, difficultyNumberMax);
                clearScreen();
                Main.line();
                write("You chose same");
                write("Computer generated number " + yourNumber);
                if (yourNumber == difficultyMiddle) {
                    write("You win, +" + difficultyScoreSamePlus + " score");
                    score = score + difficultyScoreSamePlus;
                } else {
                    write("You lose, -" + difficultyScoreSameMinus + " score");
                    score = score - difficultyScoreSameMinus;
                }
                Main.line();
                write("Current score: " + score);
                Main.line();
            } else if (input.contains("help")) {
                // Displays what commands/recognized words are available to the user
                clearScreen();
                Main.line();
                write("Available commands:");
                write("over  - guesses that the computer will generate a number above 5");
                write("under - guesses that the computer will generate a number under 5");
                write("same  - guesses that the computer will generate the number 5");
                write("diff  - Changes your difficulty and resets score.");
                write("reset - resets your score");
                write("ver   - displays version info and credits");
                write("exit  - exits the program");
                write("");
                write("Points system:");
                write("If you guess correctly over or under you get 2 points");
                write("If you guess incorrectly over or under you lose 1 point");
                write("If you guess correctly same you get 10 points at level 1, 100 at 2, 1000 at 3");
                write("If you guess incorrectly same you lose 5 points at level 1, 25 at 2, 100 at 3");
                Main.line();
            } else if (input.contains("reset")) {
                // Resets the score
                clearScreen();
                score = 0;
                Main.line();
                write("Score has been reset");
                Main.line();
                loadingBar();
                openingMessage();
            } else if (input.contains("ver")) {
                // Displays version info and credits
                clearScreen();
                score = 0;
                Main.line();
                write("Version " + version);
                write("Made by asbestos");
                write("Input reader by Michael Kölling and David J. Barnes");
                write("CMD executer by Brandon Barajas");
                write("Clear CMD by Ankush Mundhra");
                write("Wait method by Hecanet");
                Main.line();
            } else if (input.contains("diff")) {
                // Lets the user change their difficulty to a new setting
                // Starts a new while loop that waits for input. Exits loop
                // after a recognized input is typed.
                // Displays an error if the input isn't recognized.
                clearScreen();

                Main.line();
                write("Available difficulty levels are:");
                write("[1][2][3]");
                Main.line();

                boolean finishedDiff = false;
                while (!finishedDiff) {
                    HashSet<String> inputDiff = readerDiff.getInput();
                    if (inputDiff.contains("1")) {
                        // All the settings related to Difficulty setting 1
                        score = 0;
                        difficultyMiddle = 5;
                        difficultyScorePlus = 2;
                        difficultyScoreMinus = 1;
                        difficultyScoreSamePlus = 10;
                        difficultyScoreSameMinus = 5;
                        difficultyNumberMin = 0;
                        difficultyNumberMax = 10;

                        clearScreen();
                        Main.line();
                        write("Difficulty level has been set to 1");
                        Main.line();
                        loadingBar();
                        finishedDiff = true;

                        openingMessage();
                    } else if (inputDiff.contains("2")) {
                        // All the settings related to Difficulty setting 1
                        score = 0;
                        difficultyMiddle = 50;
                        difficultyScorePlus = 2;
                        difficultyScoreMinus = 1;
                        difficultyScoreSamePlus = 100;
                        difficultyScoreSameMinus = 25;
                        difficultyNumberMin = 0;
                        difficultyNumberMax = 100;

                        clearScreen();
                        Main.line();
                        write("Difficulty level has been set to 2");
                        Main.line();
                        loadingBar();
                        finishedDiff = true;

                        openingMessage();
                    } else if (inputDiff.contains("3")) {
                        // All the settings related to Difficulty setting 1
                        score = 0;
                        difficultyMiddle = 500;
                        difficultyScorePlus = 2;
                        difficultyScoreMinus = 1;
                        difficultyScoreSamePlus = 1000;
                        difficultyScoreSameMinus = 100;
                        difficultyNumberMin = 0;
                        difficultyNumberMax = 1000;

                        clearScreen();
                        Main.line();
                        write("Difficulty level has been set to 3");
                        Main.line();
                        loadingBar();
                        finishedDiff = true;

                        openingMessage();
                    } else {
                        // Error message displayed if the user doesn't type 1, 2 or 3
                        clearScreen();
                        Main.line();
                        write("That difficulty level is not recognized.");
                        write("Available difficulty levels are:");
                        write("[1][2][3]");
                        Main.line();
                    }
                }
                clearScreen();
            } else if(input.contains("asbestos")) {
                // Displays the intro animation again
                clearScreen();
                animations.intro();
                eggText();
            } else if(input.contains("zoom")) {
                // Displays the "zoom" easter-egg
                clearScreen();
                animations.zoom();
                eggText();
            } else if(input.contains("niceloading")) {
                // Displays the "loading-bar" easter-egg
                clearScreen();
                loadingBar();
                System.out.println();
                loadingBar();
                System.out.println();
                loadingBar();
                System.out.println();
                loadingBar();
                System.out.println();
                loadingBar();
                System.out.println();
                loadingBar();
                System.out.println();
                eggText();
            } else {
                // Error message if an input isn't recognized
                clearScreen();
                Main.line();
                write("That command is not recognized");
                Main.line();
            }
        }
        Main.shutdownSeq();
    }

    public static void clearScreen() {
        // Clears the console window.
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Main.clearScr();
    }

    public static void write(String text) {
        // An alternative to System.out.println() that adds " > " to the front of every line
        System.out.println(" > " + text);
    }

    /**
     * Used to display text after displaying an easter-egg
     */
    public void eggText() {
        Main.line();
        write("Hope you enjoyed the easter-egg :)");
        write("Game is ready to continue");
        loadingBar();

        openingMessage();
    }

    /**
     * Used to present the user an introduction when starting the game
     */
    public void openingMessage() {
        clearScreen();

        // Prints the opening message
        Main.line();
        write("Welcome to Very Boring Game!");
        Main.line();
        write("In this \"game\" you are supposed to guess if the");
        write("computer generates a number over or under 5, 50 or");
        write("500 (based on the difficulty). If you guess");
        write("correctly, your score increases. If you don't, the");
        write("score goes down.");
        Main.line();
        write("Type \"help\" for available commands");
        Main.line();
    }

    /**
     * A loading-bar (used after displaying info messages to give the user
     * some time before returning to the welcome text)
     */
    public void loadingBar() {
        System.out.print(" > [░░░░░░]");
        Main.wait(250);

        System.out.print("\b\b\b\b\b\b\b▓░░░░░]");
        Main.wait(250);

        System.out.print("\b\b\b\b\b\b▓░░░░]");
        Main.wait(250);

        System.out.print("\b\b\b\b\b▓░░░]");
        Main.wait(250);

        System.out.print("\b\b\b\b▓░░]");
        Main.wait(250);

        System.out.print("\b\b\b▓░]");
        Main.wait(250);

        System.out.print("\b\b▓]");
    }

}
