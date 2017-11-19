import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
    LitEngine (the Livingfor.it ASCII Game Engine)
    Version: 0.3 beta (JFrame integration)
    Olle Kaiser 2017 / GitHub.com/Yogsther

    Github:                     https://github.com/Yogsther/LitEngine
    Website:                    http://livingforit.xyz/lit_engine
    Documentation:              http://livingforit.xyz/lit_engine/documentation
    Plotter (ASCII Art -> LitCode): http://livingforit.xyz/lit_engine/plotter

    Tips:
        - Read the documentation!
        - Use Plotter, it's really helpful!

        If you are having any problems, please contact me on discord, mail or github.

    Contact:

    Discord:  - Yogsther#7884
    Github:   - https://github.com/Yogsther
    Mail:     - yogsther@gmail.com

*/


/*
    TODO list

    Documentation:
    - setTitle(String title);

    Update website:
    - JFrame integration


    To Add:
    - Audio support

*/

public class LitEngine{

    // Variables for render engine
    public static int height = 20+1;
    public static int width = 60+1;

    public static Pixel[] renderArray = new Pixel[width*height];
    public static boolean renderSplash = true;
    public static String gameTitle = "L.it Engine";
    public static JFrame frame;
    public static JLabel pixelFrame;


    public static void main(String[] args) throws InterruptedException {
        // main() should not be run, but if it does, an error message gets printed.
        start("clear");
        printAnimated(0, 0, "You ran the L.it Engine class, LitEngine.main(); should not be called. Make another class, and call LitEngine.start() and other methods instead. Read the documentation at www.Livingforit.xyz/lit_engine", 20);
        while(true){
            Thread.sleep(1000);
        }
    }

    // Call this method to start up LitEngine.
    public static synchronized void start(String type) throws InterruptedException {

        // Set up JFrame
        frame = new JFrame(gameTitle);

        // Set icon image
        Image icon = Toolkit.getDefaultToolkit().getImage(LitEngine.class.getResource("icon.png"));
        frame.setIconImage(icon);

        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set resolution
        double newWidth = width*10*0.72;
        int finalWidth = (int)newWidth;

        frame.setSize(finalWidth, height*19);
        frame.setResizable(false);
        frame.setDefaultLookAndFeelDecorated(true);

        // Set background to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Setup pixelFrame, the text that displays all ASCII stuff.
        pixelFrame = new JLabel("Starting...");

        pixelFrame.setLayout(null);

        // Set font
        pixelFrame.setFont(new Font("Courier New", Font.BOLD, 12));


        // Position pixelFrame
        pixelFrame.setBounds(0,-15,width*10, height*19);
        pixelFrame.setBackground(Color.BLACK);


        // Change color on pixelFrame
        //pixelFrame.setForeground(Color.WHITE);
        // Removed because of added color support.

        // Add pixelFrame
        frame.getContentPane().add(pixelFrame);

        // Set JFrame to visible
        frame.setVisible(true);


        // Setup colors
        loadColors();

        // Load key handler
        keyHandler();

        init(type);



        if(renderSplash){
            // Render splash screen animation


                for (int i = 0; i < 3; i++) {
                    clearNoRender(type);
                    if (i % 2 == 0) {
                        // i == even
                        Doodles.Fire01(0, 0);

                    } else {
                        // Draw other frame
                        Doodles.Fire02(0, 0);

                    }
                    // Draw L.it Logo
                    print(20, 10, "Powered by L.it Engine (v.0.5)");
                    render();
                    Thread.sleep(500);

                }

            init(type);
            Thread.sleep(500);
        }
    }


    // Disable splash screen
    public static void debugDisableSplash(){
        renderSplash = false;
    }

    public static void debugShowPressedKeys(){
        // Show currently pressed keys in the console.
        debugShowKeys = true;
    }


    public static Set<Integer> pressedKeys = new HashSet<Integer>();
    private static boolean debugShowKeys = false;


    public static boolean checkKey(int keycode){
        boolean state = false;

        ArrayList<Integer> intArrPressedKeys = new ArrayList<>(pressedKeys);

        for(int i = 0; i < pressedKeys.size(); i++){
            if(intArrPressedKeys.get(i) == keycode){
                state = true;
                break;
            }
        }
        return state;
    }


    private static void keyHandler(){
        // This key handler will handle all key inputs, and add them to the pressedKeys array.
        // I recommend using the function checkKey(int keycode); to check for inputs.
        // or you can use this array to get key input in your game.

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                if(debugShowKeys){
                    System.out.println("Debug: Key pressed. All pressed keys: " + pressedKeys);
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
                if(debugShowKeys){
                    System.out.println("Debug: Key released. All pressed keys: " + pressedKeys);
                }

            }
        });
    }



    public static void setTitle(String title){
        gameTitle = title;
    }

    public static void draw(int x, int y, String value){
        // Draw method do draw a single pixel. "Value" is recommended to be a single character.
        int pos = getPos(x,y);
        try{
        renderArray[pos] = new Pixel(value, 0);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        render();
        return;
    }

    public static void drawNoRender(int x, int y, String value){
        // Draw method do draw a single pixel. "Value" is recommended to be a single character.
        int pos = getPos(x,y);
        try{
            renderArray[pos] = new Pixel(value, 0);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        return;
    }

    public static void drawColor(int x, int y, String value, int color){
        // Draw with color method.
        int pos = getPos(x,y);
        try{
            renderArray[pos] = new Pixel(value, color);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        render();
        return;
    }


    public static void drawNoRenderColor(int x, int y, String value, int color){
        // Draw with color method.
        int pos = getPos(x,y);
        try{
            renderArray[pos] = new Pixel(value, color);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        return;
    }


    public static void drawRaw(int pos, String value){
        // Raw draw, no coordinates.
        try{
            renderArray[pos] = new Pixel(value, 0);
            render();
        } catch(ArrayIndexOutOfBoundsException e){
            render();
            return;
        }
        return;
    }

    public static void drawRawColor(int pos, String value, int color){
        // Raw draw, no coordinates.
        try{
            renderArray[pos] = new Pixel(value, color);
            render();
        } catch(ArrayIndexOutOfBoundsException e){
            render();
            return;
        }
        return;
    }

    public static void drawRawNoRender(int pos, String value){
        // Raw draw, no coordinates.
        try{
            renderArray[pos] = new Pixel(value, 0);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        return;

    }

    public static void drawRawNoRenderColor(int pos, String value, int color){
        // Raw draw, no coordinates - No render and with color option.
        try{
            renderArray[pos] = new Pixel(value, color);
        } catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        return;

    }


    public static void print(int x, int y, String value){

        int pos = getPos(x,y);
        int endPos = pos + value.length();
        int charPos = 0;
        while(pos < endPos){
            renderArray[pos] = new Pixel(Character.toString(value.charAt(charPos)), 0);
            pos++;
            charPos++;
        }
        render();
    }



    public static void printNoRender(int x, int y, String value){

        int pos = getPos(x,y);
        int endPos = pos + value.length();
        int charPos = 0;
        while(pos < endPos){
            renderArray[pos] = new Pixel(Character.toString(value.charAt(charPos)), 0);
            pos++;
            charPos++;
        }
    }

    public static void printColor(int x, int y, String value, int color){

        int pos = getPos(x,y);
        int endPos = pos + value.length();
        int charPos = 0;
        while(pos < endPos){
            renderArray[pos] = new Pixel(Character.toString(value.charAt(charPos)), color);
            pos++;
            charPos++;
        }
        render();
    }


    public static void printColorNoRender(int x, int y, String value, int color){

        int pos = getPos(x,y);
        int endPos = pos + value.length();
        int charPos = 0;
        while(pos < endPos){
            renderArray[pos] = new Pixel(Character.toString(value.charAt(charPos)), color);
            pos++;
            charPos++;
        }
    }


    public static void drawRectNoRender(int x, int y, int w, int h, String value){

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




    public static void printAnimatedCentered(String text, int speed) throws InterruptedException {

        char[] textToAnimate = text.toCharArray();
        int x = (width / 2) - (textToAnimate.length/2);
        int y = height/2;
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

    public static void clearNoRender(String request){
        initNoRender(request);
    }

    private static void initNoRender(String request){

        // Initiate engine.
        // Create array render array

        summonRenderArray();

        if(request == "border"){
            drawRectNoRender(0, 0, width-1, height-1, "*");
            return;
        }

        if(request == "clear"){
            return;
        }

    }





    private static void summonRenderArray(){

        for(int i = 0;i < (width*height); i++){
            // Insert empty spaces ‌‌

            renderArray[i] = new Pixel(" ", 0);

        }


    }




    private static void init(String request) throws InterruptedException {
        // Initiate engine.
        // Create array render array
        int i;
        summonRenderArray();

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
                renderArray[i] = new Pixel("*", 0);
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



    public static String[] colorValues = new String[11];

    public static void loadColors(){
        colorValues[0] = "white";
        colorValues[1] = "red";
        colorValues[2] = "blue";
        colorValues[3] = "green";
        colorValues[4] = "yellow";
        colorValues[5] = "grey";
        colorValues[6] = "purple";
        colorValues[7] = "orange";
        colorValues[8] = "EMPTY";
        colorValues[9] = "EMPTY";
        colorValues[10] = "black";
    }

    public static void render(){
        // Render Engine

        // Open HTML
        // Start of print string

        String print = "<html><pre><span style='color:white;'>";

        int drawn = 0;
        int current = 0;
        // Draw out grid loop
        while (drawn < (height * width)) {
            while (current < width) {
                // Draw color if requested
                if(renderArray[drawn].color != 0){
                    print += "<span style='color:" + colorValues[renderArray[drawn].color] + "'>";
                }
                // Add next pixel to print string.
                print = print + renderArray[drawn].value;
                // Close color tag
                if(renderArray[drawn].color != 0){
                    print += "</span>";
                }

                current++;
                drawn++;
            }
            current = 0;
            print = print + "<br>";
        }

        // End of print string
        print = print + "</span></pre></html>";
        // Print out the rendered text.
        pixelFrame.setText(print);

        // Old render system - System.out.print(print);
    }


    public static void setRes(int x, int y){
        width = x + 1;
        height = y + 1;
        renderArray = new Pixel[width*height];
    }



    // Get input string
    public static String inputString(){
        String input = JOptionPane.showInputDialog(null);
        return input;
    }

    public static String inputStringWithMessage(String message){
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }
    // Get input int
    public static int inputInt(){
        int input = Integer.parseInt(JOptionPane.showInputDialog(null));
        return input;
    }

    public static int inputIntWithMessage(String message){
        int input = Integer.parseInt(JOptionPane.showInputDialog(null, message));
        return input;
    }







}


class Pixel{

    String value = " ";
    int color = 0;

    public Pixel(String valueInput, int colorInput){
        value = valueInput;
        color = colorInput;
    }


}
