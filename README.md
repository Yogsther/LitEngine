![logo](https://i.imgur.com/jFBC4nP.png)

## LitEngine (the Livingfor.it Engine) is an ASCII Game Engine.
### Made to be very easy, and user friendly. Runs directly in your terminal.

[Check out the Website and full documentation!](http://livingforit.xyz/lit_engine/ "L.it's Home.")

Try running TechDemo.class to see what this beast can do.


Setup:

Make sure LitEngine.class is in your project.
First thing on launch, initate LitEngine:

LitEngine.start(String type); "type" is the type of render you want, border or clear. 
Border will draw a border around the window.

ex:
```java
LitEngine.start("border");
```

Now you are ready to use the engine. 
Here are some methods you can use.

### Draw
```java

// Draw pixel (value should only be one character.)
LitEngine.draw(int x, int y, String value);

// Print text
LitEngine.print(int x, int y, String value);

// Animate printed text (speed is how fast it prints out.)
LitEngine.printAnimated(int x, int y, String value, int speed);

// Draw rectangle (value should only be one character)
LitEngine.drawRect(int x, int y, int width, int height, String value);

// Draw animated rectangle (value should only be one character)
LitEngine.drawRectAnimated(int x, int y, int width, int height, String value, int speed);


// Draw circle (value should only be one character)
LitEngine.drawCircle(int x, int y, int radius, String value);

// Clear everything (type = "border" || "clear")
LitEngine.clear(String type)
```

### Input
```java

// Get input
// Get String
String string = LitEngine.inputString();

// Get int
int num = LitEngine.inputInt();

```


