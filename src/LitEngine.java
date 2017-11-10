import javax.swing.*;
import java.util.Scanner;

/*
    LitEngine (the Livingfor.it ASCII Game Engine)
    Olle Kaiser 2017(11-10) / GitHub.com/Yogsther
*/


public class LitEngine{

    // Variables for render engine
    public static int height = 20+1;
    public static int width = 60+1;
    public static boolean doRender = false;
    public static JTextArea textArea = new JTextArea();
    public static String[] renderArray = new String[width*height];


    public static void main(String[] args) throws InterruptedException {

    }

    // Call this method on startup.
    public static synchronized void start(String type){
        init(type);
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


    private static void init(String request){
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

    }

    public static void clear(String type){
        init(type);
    }



    private static void render(){
        // Render Engine
        int drawn = 0;
        int current = 0;
        String print = "\r";

        // Insert blank spaces
        for (int i = 0; i < 50; i++) {
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


    // !! Do not use this function, not complete. Keep original resolution.
    public static void setRes(int x, int y){
        width = x + 1;
        height = y + 1;
        return;
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
