/*
    Tech demo for LitEngine v.0.1 - Olle Kaiser 11-09-17
 */
import java.io.IOException;

public class TechDemo {

    // Initiate LitEngine
    // To clear the render, call LitEngine.clear()


    public static void main(String[] args) throws InterruptedException, IOException {

        // You have to first initiate the LitEngine, by calling it's main method.
        // You can call it with "args" or "null".
        LitEngine.main(args);

        // If you wish to change the resolution (default 60x20) - Use LitEngine.setRes(x,y)
        LitEngine.setRes(60, 20);

        // To draw a pixel, use the draw function as shown below.
        // This will draw one star (*) in each corner:
        LitEngine.draw(0,0,"*");
        LitEngine.draw(0,20,"*");
        LitEngine.draw(60,0,"*");
        LitEngine.draw(60,20,"*");


        // User LitEngine.print(x,y,value) to print out text, or longer values.
        // If you use draw to print more then one character at a time, the resolution would be distorted.

        welcomeText();

    }

    private static int welcomePos = 0;

    public static void welcomeText() throws InterruptedException {
        String welcomeText = "Welcome to the TechDemo";
        String output;

        welcomePos++;
        output = welcomeText.substring(0, welcomePos);
        LitEngine.print(15, 10, output);
        Thread.sleep(50);
        if(welcomePos == welcomeText.length()){
            welcomePos = 0;
            LitEngine.clear();
            LitEngine.draw(0,0,"*");
            LitEngine.draw(0,20,"*");
            LitEngine.draw(60,0,"*");
            LitEngine.draw(60,20,"*");

        }
        welcomeText();

    }
}
