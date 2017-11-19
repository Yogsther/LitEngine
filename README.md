![logo](https://i.imgur.com/jFBC4nP.png)

## LitEngine (the Livingfor.it Engine) is an ASCII Game Engine.
### Made to be very easy, and user friendly. Runs directly in your terminal.

[Check out the Website and full documentation!](http://livingforit.xyz/lit_engine/ "L.it's Home.")

Try downloading the TechDemo.jar from the website or running TechDemo.class to see what this beast can do.

### Installation:

You need three files in your project, LitEngine.java, Doodles.java and icon.png
These files can be found in the src folder.
(Doodles is a class full with doodles, and LitEngine uses some of these for the splash screeen.)

### Starter Guide
```java

// First we initiate the L.it Engine - This is essential for it to work.

LitEngine.start("border"); // "border" decalres the type of window we want to draw, either clear or border.

// Let's draw some text

LitEngine.printColor(20, 10, "Hello World", 1); // Prints "Hello World" in red - at 20, 10

Thread.sleep(3000); // Wait for 3 seconds

// Here I used LitEngine Plotter (avalible on the LitEngine website) to scetch a stick figure.
// I got this method, that I save in another class called Doodles. 

public static void Stickfigure(int x, int y){
	/* Generated with LitEngine Plotter */
	LitEngine.drawNoRenderColor(2 + x, 0 + y, "O",0);
	LitEngine.drawNoRenderColor(0 + x, 1 + y, "-",0);
	LitEngine.drawNoRenderColor(3 + x, 1 + y, "-",0);
	LitEngine.drawNoRenderColor(2 + x, 1 + y, "|",0);
	LitEngine.drawNoRenderColor(2 + x, 2 + y, "^",0);
}

// To draw him, I just call the class and set the x and y offsets.
// Let's draw him!

Doodles.Stickfigure(5,10); // Draws stick figure at 5,10


// If I want the stick figure to be playable, I can change his x and y coordinates to varaiables, and then 
// draw him in a loop. That would look something like this.

while(running){

	if(LitEngine.checkKey(87)){
	// If W is held down
	stickfigure_y -= 1; // Move stickfigure up
	}

	// Draw scene
	LitEngine.clearNoRender("border"); // Clear scene, the "noRender" part is important, since this will not output to the screen until everything is drawn - and prevent flickering.
	Doodles.Stickfigure(stickfigure_x, stickfigure_y); // Draw stick figure
	
	// Render scene
	LitEngine.render();
}




```


