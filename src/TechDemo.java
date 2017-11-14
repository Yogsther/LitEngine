/*
    Tech demo for LitEngine v.0.2 - Olle Kaiser 11-09-17
 */

import java.io.IOException;

public class TechDemo {


    // To clear the render, call LitEngine.clear()


    public static void main(String[] args) throws InterruptedException, IOException {
        // Initiate LitEngine

        // Calling start() is essential, make sure you do that first (or second first after setRes).
        // Args: "border" or "clear" weather you want a border around it, or not. User the same args for LigEngine.clear(type)
        // when clearing.

        // When developing, and debugging you can use methods starting with "debug" to help yourself.
        // For example, LitEngine.debugDisableSplash(); will disable the splash screen.
        // LitEngine.debugDisableSplash();

        // To set resolution, use LitEngine.setRes(x,y) before you start the engine.
        LitEngine.setRes(60,20);
        LitEngine.setTitle("LitEngine Tech Demo");
        LitEngine.start("border");


        // Print text for tech demo
        LitEngine.printAnimated(8,1,"Hello and welcome to the LitEngine Tech Demo!", 20);
        LitEngine.printAnimated(8,2,"Let's draw some circles!", 30);

        // Draw circles
        LitEngine.drawCircle(10,15,5, "#");
        LitEngine.drawCircle(20,10,2, "O");
        LitEngine.drawCircle(30,15,3, "X");
        LitEngine.drawCircle(46,13,9, "*");

        // Wait 5 seconds
        Thread.sleep(5000);


        // Clear view
        LitEngine.clear("border");

        // Print text
        LitEngine.printAnimated(2, 19, "Please enter your name:", 20);
        String name = LitEngine.inputString();
        LitEngine.clear("border");
        LitEngine.printAnimated(2, 19, "Howdy " + name + "!", 20);

        Thread.sleep(2000);

        LitEngine.printAnimated(2,19,"Enter a number:", 20);
        int rootMe = LitEngine.inputInt();
        double root = Math.sqrt(rootMe);
        LitEngine.clear("border");
        LitEngine.printAnimated(2, 19, "The root of your number (" + rootMe + ") is " + root + "!", 20);


        Thread.sleep(4000);

        LitEngine.clear("border");
        LitEngine.drawRect(10,5, 30, 10, "*");



        LitEngine.printAnimated(13, 7, "TechDemo!", 50);
        LitEngine.printAnimated(13, 9, "This is a test.", 50);
        LitEngine.printAnimated(13, 10, "Testing Testing....", 50);
        LitEngine.printAnimated(13, 11, "Slow text.", 300);

        int state = 0;
        while(true){
            if(state == 0){
                LitEngine.drawRect(10,5, 30, 10, "/");
                state = 1;
            } else if(state == 1){
                LitEngine.drawRect(10,5, 30, 10, "\\");
                state = 0;
            }
            Thread.sleep(200);
        }


    }
    
}
