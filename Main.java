/**
 * Asbestos: Method to start GameEngine
 * Brandon Barajas: Opens a command line and runs some other class in the jar
 * Ankush Mundhra: Method to clear CMD window
 * Hecanet: Wait method, pauses code execution
 * @author asbestos, Brandon Barajas, Ankush Mundhra, Hecanet
 * @version    1.0
 */
import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;

public class Main{

    private GameEngine engine;

    /**
     * Opens a Windows CMD window and calls startGame
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        }else{
            Main main = new Main();
            main.startGame();
            shutdownSeq();
        }
    }

    /**
     * Constructs a GameEngine object and runs the game
     */
    public void startGame(){
        engine = new GameEngine();
        engine.runEngine();
    }

    /**
     * Clears the screen of a CMD window
     */
    public static void clearScr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    /**
     * Suspends the process thread to wait with code execution
     * @param ms The amount to wait in milliseconds
     */
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Creates a line that covers a CMD window in it's default size
     */
    public static void line() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Clears the screen and exits the program
     */
    public static void shutdownSeq() {
        clearScr();
        System.out.println("Exiting the program, type \"exit\" again to close CMD");
        System.exit(0);
    }
}