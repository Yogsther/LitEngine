import javax.swing.*;

public class LitEngine {

    // Variables for render engine
    public static int fps = 30;
    public static int height = 20;
    public static int width = 60;
    public static boolean doRender = true;
    public static JTextArea textArea = new JTextArea();


    public static void main(String[] args) throws InterruptedException {
        int renderWait = 1000/fps;
        while(doRender){
            render();
            Thread.sleep(renderWait);
        }
    }


    public static void render(){

        // Render Engine
        int drawn = 0;
        int current = 0;
        String print = "\r";

        // Insert blank spaces
        for(int i=0; i<50;i++){
            print = print+"\n";
        }

        // Draw out grid
        while(drawn < (height*width)){
            while(current < width){
                print = print + "#";
                current++;
                drawn++;
            }
            current = 0;
            print = print + "\n";
        }
        System.out.print(print);
    }
}
