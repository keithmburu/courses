CS174: Object-oriented Programming

Spring 2020

Ursinus College

#Homework 2: Linked List and Inheritance



You will implement a linked list of integers called IntLList. You will also implement a class structure that accommodates your previous assignment. Rename your previous class to `IntArrayList` and rename the filename to `IntArrayList.java`. Create a parent class of both IntLList and IntArrayList called IntList.

`IntList` will implement the functions that apply to any list.

You will implement the following functions in a public class, called `IntList`. The other two classes, `IntLList` and `IntArrayList` will extend it. The functions common to both subclasses will be implemented as abstract classes in `IntList`.

For this assignment, it is not necessary or recommended that you use an IDE, such as NetBeans.

You will write the followeding `abstract` methods in `abstract class IntList` and implemented fully in its children `IntLList` and `IntArrayList`.

```java
/**
* Adds a new value to the end of the list
* @param val the value to be added
**/
public abstract void add(int val);

/**
* Removes the last element of the list.
**/
public abstract void removeLast();

/** 
* Returns the value contained at location 'index'
* @param index the index of the value to be returned
* @return the value at index
**/
public abstract int get(int index);
/**
* Sets the value to 'val' at location 'index'
* @param index the index
* @param val the value to set
**/
public abstract void set(int index, int val);

/**
* Removes the element at location index
* @param index the index of the element to be removed
**/
public abstract void remove(int index) throws ArrayIndexOutOfBoundsException;

/**
* Efficiently removes all elements from the list
**/
public abstract void clear()
```

The following methods must be implemented fully in `IntList`, i.e., they are not abstract.  They will be inherited by the child classes `IntArrayList` and `IntLList`.

```java
/**
* Returns the size of the list
**/
public int size();

/**
* Returns true is the list is empty, false otherwise
* @return true if list size is 0
**/
public boolean isEmpty();
```





## What to submit

Submit a `tar.gz` file with all of your Java files file in a directory named `lastname\_firstname` (with your actual last and first name).  So, when you decompress the file, there should be a directory with your files therein and nothing else.

Your program *must* run if compiled with `javac` and run with `java` from the command line. 