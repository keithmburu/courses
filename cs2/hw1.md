CS174: Object-oriented Programming

Spring 2020

Ursinus College

#Homework 1: IntVector



For your first homework assignment, you will implement a vector for integers.  It will duplicate the functionality of the `ArrayList` in Java.  Recall that arrays cannot be resized.  Vectors, however, can.  This is accomplished by creating a class that contains an array and expanding the array when it is filled.  You are provided with a file that runs some tests on your code to let you know if you're on the right track and gives you an *estimated* grade.  My tests will be more thorough, and I will look at your code.  Look at the rubric before you turn in your assignments.



For this assignment, it is not necessary or recommended that you use an IDE, such as NetBeans.

You will implement the following functions in a public class, called `IntVector`. 

```java
/**
* This helper function expands the internal array to accommodate
* more values by doubling its size.
**/
private void expandArray();

/**
* Constructor that defines the initial size of the array.
* @param initialSize the initial size of the internal array
**/
public IntVector(int initialSize);

/**
* Adds a new value to the end of the vecotr.
* @param val the value to be added
**/
public void add(int val);

/**
* Removes the last element of the vector in one line of code
**/
public void removeLast();

/** 
* Returns the value contained at location 'index'
* @param index the index of the value to be returned
* @return the value at index
**/
public int get(int index);
/**
* Sets the value to 'val' at location 'index'
* @param index the index
* @param val the value to set
**/
public void set(int index, int val);

/**
* Removes the element at location index, shifting any values
* remaining after it to the left in the internal array
* @param index the index of the element to be removed
**/
public void remove(int index) throws ArrayIndexOutOfBoundsException;
/** 
* Returns the size of the vector (not the internal array) in one line
* @return the size of the vector
**/
public int size();

/**
* Prints out a string representation of the elements in the vector.
* The format matches that of Java's ArrayList exactly.  This will
* be used by the test program to determine correctness.
**/
@Override
public String toString();

```



When you instantiate the `IntVector`, it will have an initial size of 0.  Recall that the size of an array is fixed.  It cannot be changed.  The purpose of the `IntVector` class is to allow one to add an arbitrary number of elements to the vector.  If you fill up the array, you create a new array and copy all of the elements from the old array into the new one.

Likewise, in a basic array, one cannot "delete'' elements.  Again, the size of the array is fixed.  However, your `IntVector` class will be able to remove elements.  When an element is removed, all of the elements following it will be shifted to the left, so that they remain contiguous.



## What to submit

Submit a `tar.gz` file with your `IntVector.java` file in a directory named `lastname\_firstname` (with your actual last and first name).  So, when you decompress the file, there should be a directory with your files therein.

Your program *must* run if compiled with `javac` and run with `java` from the command line. 