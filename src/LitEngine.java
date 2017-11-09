import javax.swing.*;

/*
    LitEngine (the Livingfor.it ASCII Game Engine)
    Olle Kaiser 2017(11-10) / GitHub.com/Yogsther
*/


public class LitEngine implements Runnable {

    // Variables for render engine
    public static int height = 20+1;
    public static int width = 60+1;
    public static boolean doRender = false;
    public static JTextArea textArea = new JTextArea();
    public static String[] renderArray = new String[width*height];
    public static boolean running = false;

    public static int fps = 30;


    public LitEngine(){

    }

    public static void main(String[] args) throws InterruptedException {
        LitEngine game = new LitEngine();
        game.start();
    }

    public static void draw(int x, int y, String value){
        // Draw method do draw a single pixel. "Value" is recommended to be a single character.
        int pos = getPos(x,y);
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
    }


    // Translate coordinates to array index.
    public static int getPos(int x, int y){
        int row = y * width;
        return row + x;
    }


    private static void init(){
        // Initiate engine.
        // Create array render array
        int i = 0;
        while(i < (width*height)){
            renderArray[i] = " ";
            i++;
        }
        render();
    }

    public static void clear(){
        init();
    }

    public synchronized void start(){
        init();
        running = true;
        Thread runThread = new Thread(this);
        runThread.start();
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


    public void run() {
        while(true){
            render();
            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void setRes(int x, int y){
        width = x + 1;
        height = y + 1;
        return;
    }













}
