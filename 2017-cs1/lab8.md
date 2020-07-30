# Lab 8: Pong

In this lab, you will modify the code given to create something like Pong. 

First, download the BouncingBall.zip file. Unzip it with the unzip command

`unzip BouncingBall.zip`

You'll have a directory with the files. Open Netbeans. Click File  -> Open Project, and find the correct directory to open it.

To run the project, right click *BouncingBall* under *Projects*, and click *Run.* If you press the "a" key, the paddle will move to the left. 

 

Study the code in the BouncingBall.java file: in particular, the `keyPressed()` method and the `paintComponent()` method. Whenever you press a key, the `keyPressed()` method is called; it's sent what's called an *event*, passed in as an object, *e*.  Currently, it says that if the "a" key is pressed (VK_A), move the padX variable (the x-coordinate of the paddle to the left by 10 pixels) and  redraw the screen. 

Somewhat confusingly, the repaint method calls `paintComponent()`. Look at `paintComponent()`. The variables *dx* and *dy* determine the change in the x and y positions, respectively, of the ball. If it  hits a window boundary, the direction is inverted.

**What to do:**

1. Modify the program to move the paddle to the right if the user presses the "d" key.

2. Modify the program to bounce the ball off of the paddle. 

 