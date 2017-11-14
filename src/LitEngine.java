import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/*
    LitEngine (the Livingfor.it ASCII Game Engine)
    Version: 0.2
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

    To Fix:
    - Font

    To Add:
    - Input
    - Audio support

*/

public class LitEngine{

    // Variables for render engine
    public static int height = 20+1;
    public static int width = 60+1;

    public static String[] renderArray = new String[width*height];
    public static boolean renderSplash = true;
    public static String gameTitle = "L.it Engine";
    public static JFrame frame;
    public static JLabel pixelFrame;


    public static void main(String[] args) throws InterruptedException {
        // main() should not be run, but if it does, an error message gets printed.
        start("clear");
        doodle();
        printAnimated(0, 0, "You ran the L.it Engine class, LitEngine.main(); should not be called. Make another class, and call LitEngine.start() and other methods instead. Read the documentation at www.Livingforit.xyz/lit_engine", 20);
        while(true){
            Thread.sleep(1000);
        }
    }

    // Call this method on startup.
    public static synchronized void start(String type) throws InterruptedException {

        // Set up JFrame
        frame = new JFrame(gameTitle);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((width*10), (height*10*2));
        frame.setResizable(false);

        // Set background to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Setup pixelFrame, the text that displays all ASCII stuff.
        pixelFrame = new JLabel("Starting...");
        pixelFrame.setLayout(null);

        // Position pixelFrame
        pixelFrame.setBounds(0,-15,width*10, height*20);
        pixelFrame.setBackground(Color.BLACK);


        // Change color on pixelFrame
        pixelFrame.setForeground(Color.WHITE);

        // Add pixelFrame
        frame.getContentPane().add(pixelFrame);

        // Set JFrame to visible
        frame.setVisible(true);

        // Set font
        // TODO Not working
        pixelFrame.setFont(new Font("Courier New", Font.PLAIN, 12));


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


    public static void setTitle(String title){
        gameTitle = title;
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
            // Insert empty spaces ‌‌
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
        // Open HTML
        String print = "<html><pre>";

        // Draw out grid
        while (drawn < (height * width)) {
            while (current < width) {
                print = print + renderArray[drawn];
                current++;
                drawn++;
            }
            current = 0;
            print = print + "<br>";
        }
        print = print + "</pre></html>";
        // Print out the rendered text.
        pixelFrame.setText(print);

        // Old render system - System.out.print(print);
    }


    public static void setRes(int x, int y){
        width = x + 1;
        height = y + 1;
        renderArray = new String[width*height];
    }



    // Get input string
    public static String inputString(){
        String input = JOptionPane.showInputDialog(null);
        return input;
    }
    // Get input int
    public static int inputInt(){
        int input = Integer.parseInt(JOptionPane.showInputDialog(null));
        return input;
    }


    private static void doodle(){
     /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(18, 6, "*");
        LitEngine.drawNoRender(19, 4, "*");
        LitEngine.drawNoRender(20, 4, "*");
        LitEngine.drawNoRender(21, 4, "*");
        LitEngine.drawNoRender(22, 4, "*");
        LitEngine.drawNoRender(23, 3, "*");
        LitEngine.drawNoRender(24, 3, "*");
        LitEngine.drawNoRender(25, 3, "*");
        LitEngine.drawNoRender(27, 4, "*");
        LitEngine.drawNoRender(29, 4, "*");
        LitEngine.drawNoRender(32, 4, "*");
        LitEngine.drawNoRender(34, 4, "*");
        LitEngine.drawNoRender(36, 5, "*");
        LitEngine.drawNoRender(37, 5, "*");
        LitEngine.drawNoRender(38, 5, "*");
        LitEngine.drawNoRender(39, 6, "*");
        LitEngine.drawNoRender(40, 7, "*");
        LitEngine.drawNoRender(41, 8, "*");
        LitEngine.drawNoRender(41, 9, "*");
        LitEngine.drawNoRender(42, 10, "*");
        LitEngine.drawNoRender(42, 11, "*");
        LitEngine.drawNoRender(41, 12, "*");
        LitEngine.drawNoRender(40, 13, "*");
        LitEngine.drawNoRender(39, 14, "*");
        LitEngine.drawNoRender(37, 14, "*");
        LitEngine.drawNoRender(35, 15, "*");
        LitEngine.drawNoRender(33, 15, "*");
        LitEngine.drawNoRender(31, 15, "*");
        LitEngine.drawNoRender(29, 15, "*");
        LitEngine.drawNoRender(27, 15, "*");
        LitEngine.drawNoRender(25, 15, "*");
        LitEngine.drawNoRender(24, 15, "*");
        LitEngine.drawNoRender(22, 15, "*");
        LitEngine.drawNoRender(21, 14, "*");
        LitEngine.drawNoRender(20, 13, "*");
        LitEngine.drawNoRender(19, 13, "*");
        LitEngine.drawNoRender(19, 12, "*");
        LitEngine.drawNoRender(18, 11, "*");
        LitEngine.drawNoRender(18, 10, "*");
        LitEngine.drawNoRender(17, 9, "*");
        LitEngine.drawNoRender(17, 8, "*");
        LitEngine.drawNoRender(17, 7, "*");
        LitEngine.drawNoRender(19, 5, "*");
        LitEngine.drawNoRender(20, 5, "*");
        LitEngine.drawNoRender(23, 4, "*");
        LitEngine.drawNoRender(24, 4, "*");
        LitEngine.drawNoRender(25, 4, "*");
        LitEngine.drawNoRender(30, 4, "*");
        LitEngine.drawNoRender(35, 4, "*");
        LitEngine.drawNoRender(36, 4, "*");
        LitEngine.drawNoRender(37, 6, "*");
        LitEngine.drawNoRender(38, 7, "*");
        LitEngine.drawNoRender(38, 8, "*");
        LitEngine.drawNoRender(38, 9, "*");
        LitEngine.drawNoRender(39, 9, "*");
        LitEngine.drawNoRender(39, 10, "*");
        LitEngine.drawNoRender(39, 11, "*");
        LitEngine.drawNoRender(39, 12, "*");
        LitEngine.drawNoRender(38, 12, "*");
        LitEngine.drawNoRender(38, 13, "*");
        LitEngine.drawNoRender(37, 13, "*");
        LitEngine.drawNoRender(36, 13, "*");
        LitEngine.drawNoRender(35, 14, "*");
        LitEngine.drawNoRender(34, 14, "*");
        LitEngine.drawNoRender(33, 14, "*");
        LitEngine.drawNoRender(32, 14, "*");
        LitEngine.drawNoRender(31, 14, "*");
        LitEngine.drawNoRender(30, 14, "*");
        LitEngine.drawNoRender(29, 14, "*");
        LitEngine.drawNoRender(28, 14, "*");
        LitEngine.drawNoRender(26, 14, "*");
        LitEngine.drawNoRender(25, 14, "*");
        LitEngine.drawNoRender(24, 14, "*");
        LitEngine.drawNoRender(23, 13, "*");
        LitEngine.drawNoRender(22, 13, "*");
        LitEngine.drawNoRender(22, 12, "*");
        LitEngine.drawNoRender(21, 12, "*");
        LitEngine.drawNoRender(21, 11, "*");
        LitEngine.drawNoRender(20, 10, "*");
        LitEngine.drawNoRender(20, 9, "*");
        LitEngine.drawNoRender(20, 8, "*");
        LitEngine.drawNoRender(20, 7, "*");
        LitEngine.drawNoRender(20, 6, "*");
        LitEngine.drawNoRender(21, 5, "*");
        LitEngine.drawNoRender(26, 4, "*");
        LitEngine.drawNoRender(28, 4, "*");
        LitEngine.drawNoRender(33, 4, "*");
        LitEngine.drawNoRender(35, 5, "*");
        LitEngine.drawNoRender(36, 6, "*");
        LitEngine.drawNoRender(37, 7, "*");
        LitEngine.drawNoRender(37, 8, "*");
        LitEngine.drawNoRender(38, 10, "*");
        LitEngine.drawNoRender(38, 11, "*");
        LitEngine.drawNoRender(38, 14, "*");
        LitEngine.drawNoRender(36, 14, "*");
        LitEngine.drawNoRender(34, 15, "*");
        LitEngine.drawNoRender(32, 15, "*");
        LitEngine.drawNoRender(26, 15, "*");
        LitEngine.drawNoRender(23, 14, "*");
        LitEngine.drawNoRender(22, 14, "*");
        LitEngine.drawNoRender(20, 14, "*");
        LitEngine.drawNoRender(18, 12, "*");
        LitEngine.drawNoRender(17, 11, "*");
        LitEngine.drawNoRender(17, 10, "*");
        LitEngine.drawNoRender(17, 6, "*");
        LitEngine.drawNoRender(25, 7, "*");
        LitEngine.drawNoRender(34, 7, "*");
        LitEngine.drawNoRender(32, 10, "*");
        LitEngine.drawNoRender(27, 10, "*");
        LitEngine.drawNoRender(27, 11, "*");
        LitEngine.drawNoRender(28, 11, "*");
        LitEngine.drawNoRender(29, 11, "*");
        LitEngine.drawNoRender(30, 11, "*");
        LitEngine.drawNoRender(31, 11, "*");
        LitEngine.drawNoRender(32, 11, "*");
        LitEngine.drawNoRender(20, 12, "*");
        LitEngine.drawNoRender(13, 12, "*");
        LitEngine.drawNoRender(12, 12, "*");
        LitEngine.drawNoRender(11, 12, "*");
        LitEngine.drawNoRender(10, 12, "*");
        LitEngine.drawNoRender(9, 12, "*");
        LitEngine.drawNoRender(8, 12, "*");
        LitEngine.drawNoRender(7, 12, "*");
        LitEngine.drawNoRender(14, 12, "*");
        LitEngine.drawNoRender(15, 12, "*");
        LitEngine.drawNoRender(16, 12, "*");
        LitEngine.drawNoRender(40, 12, "*");
        LitEngine.drawNoRender(42, 12, "*");
        LitEngine.drawNoRender(43, 12, "*");
        LitEngine.drawNoRender(44, 12, "*");
        LitEngine.drawNoRender(45, 12, "*");
        LitEngine.drawNoRender(46, 12, "*");
        LitEngine.drawNoRender(47, 12, "*");
        LitEngine.drawNoRender(48, 12, "*");
        LitEngine.drawNoRender(49, 12, "*");
        LitEngine.drawNoRender(50, 12, "*");
        LitEngine.drawNoRender(51, 12, "*");
        LitEngine.drawNoRender(52, 12, "*");
        LitEngine.drawNoRender(52, 11, "*");
        LitEngine.drawNoRender(8, 11, "*");
        LitEngine.drawNoRender(7, 11, "*");
        LitEngine.drawNoRender(51, 11, "*");
        LitEngine.drawNoRender(17, 12, "*");
        LitEngine.drawNoRender(19, 6, "*");
        LitEngine.drawNoRender(19, 11, "*");
        LitEngine.drawNoRender(19, 7, "*");
        LitEngine.drawNoRender(19, 8, "*");
        LitEngine.drawNoRender(19, 9, "*");
        LitEngine.drawNoRender(19, 10, "*");
        LitEngine.drawNoRender(18, 9, "*");
        LitEngine.drawNoRender(18, 7, "*");
        LitEngine.drawNoRender(18, 8, "*");
        LitEngine.drawNoRender(21, 13, "*");
        LitEngine.drawNoRender(23, 15, "*");
        LitEngine.drawNoRender(28, 15, "*");
        LitEngine.drawNoRender(30, 15, "*");
        LitEngine.drawNoRender(27, 14, "*");
        LitEngine.drawNoRender(38, 6, "*");
        LitEngine.drawNoRender(39, 7, "*");
        LitEngine.drawNoRender(39, 8, "*");
        LitEngine.drawNoRender(40, 8, "*");
        LitEngine.drawNoRender(40, 9, "*");
        LitEngine.drawNoRender(41, 10, "*");
        LitEngine.drawNoRender(41, 11, "*");
        LitEngine.drawNoRender(40, 11, "*");
        LitEngine.drawNoRender(40, 10, "*");
        LitEngine.drawNoRender(39, 13, "*");
        LitEngine.drawNoRender(20, 11, "*");
        LitEngine.drawNoRender(32, 3, "*");
        LitEngine.drawNoRender(31, 3, "*");
        LitEngine.drawNoRender(30, 3, "*");
        LitEngine.drawNoRender(29, 3, "*");
        LitEngine.drawNoRender(31, 4, "*");
        LitEngine.drawNoRender(28, 3, "*");
        LitEngine.drawNoRender(27, 3, "*");
        LitEngine.drawNoRender(26, 3, "*");
        LitEngine.drawNoRender(36, 15, "*");
        LitEngine.drawNoRender(46, 1, "*");
        LitEngine.drawNoRender(13, 5, "*");
        LitEngine.drawNoRender(14, 17, "*");
        LitEngine.drawNoRender(12, 18, "*");
        LitEngine.drawNoRender(45, 2, "*");
        LitEngine.drawNoRender(49, 1, "*");
        LitEngine.drawNoRender(47, 6, "*");
        LitEngine.drawNoRender(50, 6, "*");
        LitEngine.drawNoRender(42, 17, "*");
        LitEngine.drawNoRender(45, 18, "*");
        LitEngine.drawNoRender(6, 15, "*");
        LitEngine.drawNoRender(3, 16, "*");
        LitEngine.drawNoRender(9, 3, "*");
        LitEngine.drawNoRender(10, 5, "*");
        LitEngine.drawNoRender(12, 3, "*");
        LitEngine.drawNoRender(49, 1, "*");
        LitEngine.drawNoRender(59, 1, "*");
        LitEngine.drawNoRender(7, 13, "*");
        LitEngine.drawNoRender(14, 13, "*");
        LitEngine.drawNoRender(15, 13, "*");
        LitEngine.drawNoRender(16, 13, "*");
        LitEngine.drawNoRender(17, 13, "*");
        LitEngine.drawNoRender(18, 13, "*");
        LitEngine.drawNoRender(13, 13, "*");
        LitEngine.drawNoRender(11, 13, "*");
        LitEngine.drawNoRender(10, 13, "*");
        LitEngine.drawNoRender(9, 13, "*");
        LitEngine.drawNoRender(8, 13, "*");
        LitEngine.drawNoRender(6, 13, "*");
        LitEngine.drawNoRender(12, 13, "*");
        LitEngine.drawNoRender(42, 13, "*");
        LitEngine.drawNoRender(43, 13, "*");
        LitEngine.drawNoRender(44, 13, "*");
        LitEngine.drawNoRender(45, 13, "*");
        LitEngine.drawNoRender(46, 13, "*");
        LitEngine.drawNoRender(47, 13, "*");
        LitEngine.drawNoRender(48, 13, "*");
        LitEngine.drawNoRender(49, 13, "*");
        LitEngine.drawNoRender(50, 13, "*");
        LitEngine.drawNoRender(51, 13, "*");
        LitEngine.drawNoRender(52, 13, "*");
        LitEngine.drawNoRender(53, 13, "*");
        LitEngine.drawNoRender(41, 13, "*");
        LitEngine.drawNoRender(6, 12, "*");
        LitEngine.drawNoRender(53, 12, "*");
        LitEngine.drawNoRender(53, 11, "*");
        LitEngine.drawNoRender(6, 11, "*");
        LitEngine.render();


    }







}
