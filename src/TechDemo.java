/*
    Tech demo for LitEngine v.0.3 - Olle Kaiser 11-09-17
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


public class TechDemo{

    public static int random(int max){
        double random = Math.floor(Math.random()*max);
        int returnRandom = (int)random;
        return returnRandom;
    }

    public static String p1Head = "X";
    public static String p2Head = "O";

    public static double hero_x = 14;
    public static double hero_y = 9;

    public static double player_x = 75;
    public static double player_y = 9;

    // Position for menu
    public static int position = 0;
    // Game started or not. Know when to stop rendering menu.
    public static boolean gameStarted = false;




    public static void main(String[] args) throws InterruptedException, IOException {
        // Initiate LitEngine

        // Calling start() is essential, make sure you do that first (or second first after setRes).
        // Args: "border" or "clear" weather you want a border around it, or not. User the same args for LigEngine.clear(type)
        // when clearing.

        // When developing, and debugging you can use methods starting with "debug" to help yourself.
        // For example, LitEngine.debugDisableSplash(); will disable the splash screen.
        //LitEngine.debugDisableSplash();
        LitEngine.debugShowPressedKeys();

        // To set resolution, use LitEngine.setRes(x,y) before you start the engine.
        LitEngine.setRes(90, 20);
        LitEngine.setTitle("LitEngine Tech Demo");
        LitEngine.start("border");


        // Input
        keyHandlerMenu();

        // Menu

        drawMenu();

        startGame();


        LitEngine.clear("border");
        LitEngine.printAnimatedCentered("Hero died!", 20);
    }


    public static void startGame() throws InterruptedException {


        boolean game = true;

        int heroDir = 0;
        int playerDir = 0;



        ArrayList<Shots> shots = new ArrayList<>();


        hero_x = 14;
        hero_y = 9;

        player_x = 75;
        player_y = 9;

        int intHero_y = (int)Math.round(hero_y);
        int intHero_x = (int)Math.round(hero_x);

        int intPlayer_y = (int)Math.round(player_y);
        int intPlayer_x = (int)Math.round(player_x);

        int heroHealth = 15;
        int playerHealth = 15;


        while(game){

            // Save last player positions
            int lastHero_y = intHero_y;
            int lastHero_x = intHero_x;

            int lastPlayer_y = intPlayer_y;
            int lastPlayer_x = intPlayer_x;


            intHero_y = (int)Math.round(hero_y);
            intHero_x = (int)Math.round(hero_x);

            intPlayer_y = (int)Math.round(player_y);
            intPlayer_x = (int)Math.round(player_x);


            // Shots - Player weapon controls
            if(LitEngine.checkKey(32)){
                shots.add(new Shots(intHero_x+3, intHero_y+1, heroDir, 0));
            }

            if(LitEngine.checkKey(16)){
               shots.add(new Shots(intPlayer_x+3, intPlayer_y+1, playerDir, 1));
            }

            double speed = 0.5;

            if(LitEngine.checkKey(87)){
                // If W is pressed
                hero_y -= speed/2;
                heroDir = 0;
            }
            if(LitEngine.checkKey(83)){
                // If S is pressed
                hero_y += speed/2;
                heroDir = 1;
            }
            if(LitEngine.checkKey(65)){
                // If A is pressed
                hero_x -= speed;
                heroDir = 2;
            }
            if(LitEngine.checkKey(68)){
                // If D is pressed
                hero_x += speed;
                heroDir = 3;
            }

            // Check for monster keys (arrows)
            if(LitEngine.checkKey(38)){
                // If Arrow up is pressed
                player_y -= speed/2;
                playerDir = 0;
            }
            if(LitEngine.checkKey(40)){
                // If Arrow down is pressed
                player_y += speed/2;
                playerDir = 1;
            }
            if(LitEngine.checkKey(37)){
                // If Arrow left is pressed
                player_x -= speed;
                playerDir = 2;
            }
            if(LitEngine.checkKey(39)){
                // If Arrow right is pressed
                player_x += speed;
                playerDir = 3;
            }


            // Position shots
            for(int i = 0; i < shots.size(); i++){
                int dir = shots.get(i).dir;
                if(dir == 0){
                    // Up
                    shots.get(i).y -= 1;
                }
                if(dir == 1){
                    // Down
                    shots.get(i).y += 1;
                }
                if(dir == 2){
                    // Left
                    shots.get(i).x -= 1;
                }
                if(dir == 3){
                    // Right
                    shots.get(i).x += 1;
                }

                // Check if player is hit.
                if(shots.get(i).author == 1){
                    if(Math.abs((hero_x + 2)-shots.get(i).x)<2 && Math.abs((hero_y + 2)-shots.get(i).y)<2){
                        heroHealth = heroHealth - 1;
                        shots.remove(i);
                    }
                } else {
                    if(Math.abs((player_x + 2)-shots.get(i).x)<2 && Math.abs((player_y + 2)-shots.get(i).y)<2){
                        playerHealth = playerHealth - 1;
                        shots.remove(i);
                    }
                }

                try{
                // Remove shot if it's out of bounds.
                if(shots.get(i).y > 19 || shots.get(i).y < 1 || shots.get(i).x > 89 || shots.get(i).x < 1){
                    shots.remove(i);
                }

                // Check if there is a wall ahead.

                    int rawPos = LitEngine.getPos(shots.get(i).x, shots.get(i).y);
                    if(LitEngine.renderArray[rawPos].value == "|"){
                        shots.remove(i);
                    }
                } catch(IndexOutOfBoundsException e){
                }


            }

            // Check wall collisions for players

            try{
                int playerRaw = LitEngine.getPos(intPlayer_x + 3, intPlayer_y + 2);
                // Draw walls just for collision detection.
                Doodles.Walls(0,0);
                if(LitEngine.renderArray[playerRaw].value == "|"){
                    player_x = lastPlayer_x;
                    player_y = lastPlayer_y;
                }
            } catch(IndexOutOfBoundsException e){
            }


            try{
                int heroRaw = LitEngine.getPos(intHero_x + 3, intHero_y + 2);
                // Draw walls just for collision detection.
                Doodles.Walls(0,0);
                if(LitEngine.renderArray[heroRaw].value == "|"){
                    hero_x = lastHero_x;
                    hero_y = lastHero_y;
                }
            } catch(IndexOutOfBoundsException e){
            }




            LitEngine.clearNoRender("border");



            // Collisions
            hero_x = checkCollision_x(hero_x);
            hero_y = checkCollision_y(hero_y);

            player_x = checkCollision_x(player_x);
            player_y = checkCollision_y(player_y);

            // Clear and draw everything

            Doodles.Hero(intHero_x,intHero_y);
            Doodles.Monster(intPlayer_x,intPlayer_y);
            Doodles.Walls(0,0);
            Doodles.HealthBars(0,1);
            // Draw shots


            for(int i = 0; i < shots.size(); i++){
                LitEngine.drawNoRenderColor(shots.get(i).x, shots.get(i).y, "*", 1);
            }

            for(int i = 0; i < playerHealth; i++){
                LitEngine.drawNoRenderColor(2+i, 20, "*",3);
            }
            for(int i = heroHealth;i > -1; i = i-1){
                LitEngine.drawNoRenderColor(74+i, 20, "*",3);
            }

            if(heroHealth < 1){
                // Player won
                winner(p1Head);
            }
            if(playerHealth < 1){
                // Hero won
                winner(p2Head);
            }
            LitEngine.debugShowPressedKeys();
            LitEngine.render();
            Thread.sleep(4);
        }
    }


    public static void winner(String winner) throws InterruptedException {



        LitEngine.clear("border");
        LitEngine.printAnimatedCentered(winner + " won! - Hit enter to play again.", 10);
        Thread.sleep(1000);
        startGame();
    }

    public static void drawMenu() throws InterruptedException {

        while(gameStarted == false){



            int cursorPosition = 12;
            boolean drawingMenu = true;

            while(drawingMenu){
                if(gameStarted == true){
                    break;
                }

                if(position % 2 == 0){
                    cursorPosition = 12;
                } else {
                    cursorPosition = 14;
                }

            LitEngine.clearNoRender("border");
            Doodles.MenuBoxes(0,0);
            Doodles.Gun(0,1);
            LitEngine.printNoRender(9,12, "Start game");
            LitEngine.printNoRender(9,14, "Change heads");

            // Draw cursor
            LitEngine.drawNoRenderColor(6, cursorPosition, ">", 0);


            LitEngine.render();
            Thread.sleep(16);

            }
        }
    }


    public static void changeHeads(){

        p1Head = LitEngine.inputStringWithMessage("Player 1 Head: ");
        p2Head = LitEngine.inputStringWithMessage("Player 2 Head: ");
    }


    public static void keyHandlerMenu(){

        LitEngine.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                // Player controller handler
                if(e.getKeyCode() == 38){
                   position++;
                }
                if(e.getKeyCode() == 40){
                    position -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){


                    if(position % 2 == 0){
                        gameStarted = true;
                    } else {
                        changeHeads();
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public static double checkCollision_x(double x){

        // Check for out of bounds
        if(x > 85){
            x = 85;
        }
        if(x < 0){
            x = 0;
        }

        return x;
    }

    public static double checkCollision_y(double y){

        if(y > 16){
            y = 16;
        }
        if(y < 0){
            y = 0;
        }

        return y;
    }


    
}

class Shots{
    int x;
    int y;
    int dir;
    int author;

    public Shots(int xIn, int yIn, int dirIn, int inAuthor){
        x= xIn;
        y = yIn;
        dir = dirIn;
        author = inAuthor;
    }
}

