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


        // User LitEngine.print(x,y,value) to print out text, or longer values.
        // If you use draw to print more then one character at a time, the resolution would be distorted.

    }
    
}
