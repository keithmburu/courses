# Lab 2: Loops and Functions

For this lab, you will approximate pi with the following formula:
$$
 \pi\:=4\sum^{\infty}_{k=1}\frac{\:\left(-1\right)^{k+1}}{2k-1}
$$


Part 1:

You will use two classes: the first will be called PiApproximator, in a file called `PiApproximator.java`

In this file, you will have the following method:

`double approximatePi(int n)`

Since you can't loop to infinity, you will pass a sufficiently large  number as n.  This will replace the infinity in the expression and give  us an approximation of pi.

To test that this runs correctly, you can put a main method in your class with the following code:

`PiApproximator p = new PiApproximator()`

This creates an object (or "instance") of PiApproximator that you can use to call the function you wrote.  (An "object" is an instance of a  class.  Here, p is an object of type PiApproximator.)  We'll learn more  about this later.  For now, you can think of PiApproximator as a new  type we're creating and p as the variable that refers to one instance of it.

`p.approximatePy(1000000)`

(for example)

To test your program, you can run

```
javac PiApproximator.java
java PiApproximator
```

Part 2:

This part of the lab will give you practice using multiple classes.

Create a second class called Main, in a file called Main.java

Main.java will have one method: main().

This main method will create a `PiApproximator` object and call the method, as described at the end of Part 1.

When run, this class will also take one argument from the command line, which will be the *n* that you pass into `PiApproximator`.

For example:

```
java PiApproximator 100000
```

By doing this, you *n* should be set to 100000 when the method is called.