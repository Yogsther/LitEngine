import javax.swing.*;
import java.util.Scanner;

/*
    LitEngine (the Livingfor.it ASCII Game Engine)
    v.0.2
    Olle Kaiser 2017 / GitHub.com/Yogsther

    Github:         https://github.com/Yogsther/LitEngine
    Website:        http://livingforit.xyz/lit_engine/
    Documentation:  http://livingforit.xyz/lit_engine/documentation

*/


public class LitEngine{

    // Variables for render engine
    public static int height = 20+1;
    public static int width = 60+1;
    public static boolean doRender = false;
    public static JTextArea textArea = new JTextArea();
    public static String[] renderArray = new String[width*height];
    public static boolean renderSplash = true;


    public static void main(String[] args) throws InterruptedException {
        // main() should not be run, but if it does, an error message gets printed.
        start("clear");
        printAnimated(0, 0, "You ran the L.it Engine class, LitEngine.main(); should not be called. Make another class, and call LitEngine.start() and other methods instead. Read the documentation at www.Livingforit.xyz/lit_engine", 20);
        while(true){
            Thread.sleep(1000);
        }
    }

    // Call this method on startup.
    public static synchronized void start(String type) throws InterruptedException {
        init(type);

        if(renderSplash){
            // Render splash screen
            drawRectAnimated(17,7,24, 6,"*",5);
            printAnimated(20, 9, "Powered by", 20);
            printAnimated(20, 10, "the Livingfor.it", 20);
            printAnimated(20, 11, "Engine (v.0.2)", 20);
            Thread.sleep(1000);

                    drawRectAnimated(17,7,24, 6," ",5);
                    Thread.sleep(500);

                    init(type);
                    Thread.sleep(500);
            }
    }


    public static void debugDisableSplash(){
        renderSplash = false;
    }


    public static void draw(int x, int y, String value){
        // Draw method do draw a single pixel. "Value" is recommended to be a single character.
        int pos = getPos(x,y);
        renderArray[pos] = value;
        render();
        return;
    }

    public static void drawNoRender(int x, int y, String value){
        // Draw method do draw a single pixel. "Value" is recommended to be a single character.
        int pos = getPos(x,y);
        renderArray[pos] = value;
        return;
    }

    public static void drawRaw(int pos, String value){
        // Raw draw, no coordinates.
        renderArray[pos] = value;
        render();
        return;

    }

    public static void drawRawNoRender(int pos, String value){
        // Raw draw, no coordinates.
        renderArray[pos] = value;
        return;

    }


    public static void print(int x, int y, String value){

        int pos = getPos(x,y);
        int endPos = pos + value.length();
        int charPos = 0;
        while(pos < endPos){
            renderArray[pos] = Character.toString(value.charAt(charPos));
            pos++;
            charPos++;
        }
        render();
    }


    public static void drawRect(int x, int y, int w, int h, String value){

        // Draw top
        for(int i=x; i <= (w+x); i++){
            drawNoRender(i,y,value);
        }
        // Draw bottom
        for(int i=x; i <= (w+x); i++){
            drawNoRender(i,(y+h),value);
        }
        // Draw left
        for(int i=y; i <= (h+y); i++){
            drawNoRender(x, i,value);
        }
        // Draw right
        for(int i=y; i <= (h+y); i++){
            drawNoRender((x+w), i,value);
        }
        render();
    }

    public static void drawRectAnimated(int x, int y, int w, int h, String value, int speed) throws InterruptedException {

        // Draw top
        for(int i=x; i <= (w+x); i++){
            drawNoRender(i,y,value);
            Thread.sleep(speed);
            render();
        }

        // Draw right
        for(int i=y; i <= (h+y); i++){
            drawNoRender((x+w), i,value);
            Thread.sleep(speed);
            render();
        }

        // Draw bottom
        for(int i=(x+w); i >= x; i = i-1){
            drawNoRender(i,(y+h),value);
            Thread.sleep(speed);
            render();
        }
        // Draw left
        for(int i=(y+h); i >= y; i = i-1){
            drawNoRender(x, i,value);
            Thread.sleep(speed);
            render();
        }


    }


    public static void drawCircle(int x, int y, int r, String value){

        int dots = r*7;

        for(int i = 1; i < dots ;i++){
            double posX = x + r * Math.cos(2 * Math.PI * i / dots);
            double posY = y + r * Math.sin(2 * Math.PI * i / dots);

            int finalX = (int)Math.round(posX);

            double lastY = Math.round(posY) / 1.2;
            if(r > 8){
                lastY = Math.round(posY) / 1.4;
            }
            int finalY = (int)Math.round(lastY);

            drawNoRender(finalX, finalY, value);
        }
        render();
    }

    public static void printAnimated(int x, int y, String text, int speed) throws InterruptedException {

        char[] textToAnimate = text.toCharArray();
        int startPos = getPos(x,y);

        for(int i = 0; i < text.length(); i++){
            drawRaw(i+startPos, String.valueOf(textToAnimate[i]));
            Thread.sleep(speed);
        }

    }





    // Translate coordinates to array index.
    public static int getPos(int x, int y){
        int row = y * width;
        return row + x;
    }


    private static void init(String request) throws InterruptedException {
        // Initiate engine.
        // Create array render array

        int i = 0;
        while(i < (width*height)){
            renderArray[i] = " ";
            i++;
        }

        if(request == "border"){
            drawRect(0, 0, width-1, height-1, "*");
            render();
            return;
        }

        if(request == "clear"){
            render();
            return;
        }

        if(request == "plot"){

            // Draw entire window
            i = 0;
            while(i < (width*height)){
                renderArray[i] = "*";
                i++;
            }

            i = 0;
            int printInt = i;
            while(i < width){
                // Draw top
                if(printInt > 9){
                    printInt = 0;
                }
                String iString = Integer.toString(printInt);
                draw(i, 0, iString);
                printInt++;
                i++;
            }

            i = 0;
            printInt = 0;
            while(i < height){
                // Draw top
                if(printInt > 9){
                    printInt = 0;
                }
                String iString = Integer.toString(printInt);
                draw(0, i, iString);
                printInt++;
                i++;
            }

            while(true){
                Thread.sleep(1000);
            }
        }

    }

    public static void clear(String type) throws InterruptedException {
        init(type);
    }



    public static void render(){
        // Render Engine
        int drawn = 0;
        int current = 0;
        String print = "\r";

        // Insert blank spaces
        for (int i = 0; i < width; i++) {
            print = print + "\n";
        }

        // Draw out grid
        while (drawn < (height * width)) {
            while (current < width) {
                print = print + renderArray[drawn];
                current++;
                drawn++;
            }
            current = 0;
            print = print + "\n";
        }
        // Print out the rendered text.
        System.out.print(print);
    }


    public static void setRes(int x, int y){
        width = x + 1;
        height = y + 1;
        renderArray = new String[width*height];
    }



    // Get input string
    public static String inputString(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    // Get input int
    public static int inputInt(){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }










}
