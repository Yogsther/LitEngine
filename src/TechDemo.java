/*
    Tech demo for LitEngine v.0.3 - Olle Kaiser 11-09-17
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

// TODO add timer, add winner message

public class TechDemo{

    public static int random(int max){
        double random = Math.floor(Math.random()*max);
        int returnRandom = (int)random;
        return returnRandom;
    }

    public static int hero_x = random(85);
    public static int hero_y = random(16);

    public static int player_x = random(85);
    public static int player_y = random(16);


    public static void main(String[] args) throws InterruptedException, IOException {
        // Initiate LitEngine

        // Calling start() is essential, make sure you do that first (or second first after setRes).
        // Args: "border" or "clear" weather you want a border around it, or not. User the same args for LigEngine.clear(type)
        // when clearing.

        // When developing, and debugging you can use methods starting with "debug" to help yourself.
        // For example, LitEngine.debugDisableSplash(); will disable the splash screen.
        LitEngine.debugDisableSplash();

        // To set resolution, use LitEngine.setRes(x,y) before you start the engine.
        LitEngine.setRes(90,20);
        LitEngine.setTitle("LitEngine Tech Demo");
        LitEngine.start("border");


        LitEngine.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                // Hero controller handler
                if(e.getKeyCode() == 87){
                    TechDemo.hero_y -= 1;
                }
                if(e.getKeyCode() == 83){
                    TechDemo.hero_y++;
                }
                if(e.getKeyCode() == 65){
                    TechDemo.hero_x -= 1;
                }
                if(e.getKeyCode() == 68){
                    TechDemo.hero_x++;
                }

                // Player controller handler
                if(e.getKeyCode() == 38){
                    player_y -= 1;
                }
                if(e.getKeyCode() == 40){
                    player_y++;
                }
                if(e.getKeyCode() == 37){
                    player_x -= 1;
                }
                if(e.getKeyCode() == 39){
                    player_x++;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        boolean alive= true;

        while(alive){
            LitEngine.clearNoRender("border");

            // Collisions
            hero_x = checkCollision_x(hero_x);
            hero_y = checkCollision_y(hero_y);

            player_x = checkCollision_x(player_x);
            player_y = checkCollision_y(player_y);


            Doodles.Hero(hero_x,hero_y);
            Doodles.Monster(player_x,player_y);

            if(Math.abs(hero_x-player_x)<2 && Math.abs(hero_y-player_y)<2){
                alive = false;
            }

            LitEngine.render();
            Thread.sleep(16);
        }



        LitEngine.clear("border");
        LitEngine.printAnimatedCentered("Hero died!", 20);


        Thread.sleep(200000);


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


    public static int checkCollision_x(int x){

        // Check for out of bounds
        if(x > 85){
            x = 85;
        }
        if(x < 0){
            x = 0;
        }

        return x;
    }

    public static int checkCollision_y(int y){

        if(y > 16){
            y = 16;
        }
        if(y < 0){
            y = 0;
        }

        return y;
    }


    
}
