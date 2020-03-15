CS174 Spring 2020

Ursinus College



# Labs 3 and 4: I/O Redirection and Piping

## Lab 3

In this lab, you will again calculate $\pi$, this time using an infinite series.  To do this, you will implement the following formula:
$$
\label{eq:pi_series}
\pi = 4 \sum_{k=1}^{n} \frac{(-1)^{k+1}}{2k -1} = 1 - \frac{1}{3} + \frac{1}{5} - \ldots
$$
Instead of outputting the result directly, print to `stdout`, on each line, the floating point representation of each of the fractions used in the summation and nothing else.  Place your code in a file called `PiSeries.java` in a class called PiSeries.  (Recall that class names should always match their file names in Java, including capitalization.)



Also write a file called `Summer.java`.  This file will take as input a list of numbers, line by line, from `stdin` and output the sum of the numbers to `stdout`.



Pipe the output from `PiSeries` to `Summer` and redirect the output of `Summer` to a file called `result.txt`.  If your code is correct, you should get $\pi$.  This can all be done in one command.



## Lab 4

For this lab, write a class called `BinaryNumber`.  This class will have the following functions.



```java
/**
* Constructor that takes as input a String representation of a binary number.
* Sets internal bits array to hold the 1/0 values of the binary number represented by the String
* @param binaryString a string representation of a binary number
**/
public BinaryString(String binaryString);

/**
* Returns an array where each element of the array is a 1 or 0.  The array represents the binary number.
* @return an array of bit values for the binary number 
**/
public byte[] getBits();

/**
* Replaces the internal bits array that represents this binary number
* @param am array of 1/0 bit values that represent the binary number
**/
public void setBits(byte[] bits);

/**
* Converts the binary number (bits array) into an integer and returns it
* @return integer representation of number 
**/
public int toInt();
```



This class will have an **instance variable** of the binary string.  The `main()` method will read binary numbers (as Strings) from `stdin` and immediately output the decimal representation to `stdout`.  Use the provided text file as input and **pipe the output** into `Summer` from Lab 3 to get the result (which should be pi).  Output this result into a file called `result.txt`.  Save all of the commands you used in a shell script called `lab4.sh`.

What happens if you use `binary.txt` as the input to `Summer`?  Output the result into `bitSum.txt`.  Upload these files to Canvas, with your code.