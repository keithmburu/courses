# Lab 5: Primes

**Due Tuesday, October 17, 11:55pm.**

**Autoboxing and Unboxing**

Java has classes that correspond to each of its primitive types.  These are classes that serve as "wrappers" (because they wrap around  primitives) for primitives. For example, Java has classes Integer,  Character, Float, Byte, Boolean, etc. Whenever you use  Integer.parseInt(), for example, you are calling a static method in the  Integer class.

To make things easier for programmers, Java automatically converts  between primitive types and the wrapper types. This is called  autoboxing and unboxing. This is **only true for these special classes**.

To define an Integer, you can either type:

`int i = 0;`

or 

`Integer i = 0;`

**ArrayLists**

An ArrayList is like an array, but it is a class (obviously), and it  will resize itself as needed. An ArrayList can hold one type of class  each. (ArrayLists cannot hold primitives directly) To use it, you'll  need to import java.util.ArrayList. To define an ArrayList of type Integer,  for example, type:

```java
ArrayList<Integer> myIntList = new ArrayList<>();
```



The "Integer" goes inside of the angled brackets on the left-hand  side. (You can also use them on the right-hand size, but this isn't  necessary).  To add elements, you could type:

```java
myIntList.add(5); //adds 5 at element 0
myIntList.add(4); //adds 4 at element 1.
Integer k = myIntList.get(1); //sets k to 4
myIntList.remove(0); //Removes 5 from element 0 and shifts 4 to element 0.
myIntList.remove(0); //Removes 4 from element 0, leaving an empty list.
```



Internally, an ArrayList has an actual array, and Java creates a new, larger one whenever necessary. This has more overhead than a simple  array, but it's safer.

 To use strings, you would type:

`ArrayList<String> myList = new ArrayList<String>();` 

**Lab Assignment.**

Put this in a file called Primers.java.

1. Write a static method with the definition:

`public static boolean isPrime(int num).`

As the name suggests, this will return true if the number passed in is prime and false otherwise.

2. Create a list with all of the prime numbers between 1 and 100,000

3. Your output should have one prime number on each line and **nothing else**.
4.  (Bonus, but you should definitely write this.) Write a method with the following definition:

`public static double primeProbability(int upperBound, ArrayList<Integer> primes)`.

This will print out the probability that a number is prime in  increments of 10, all the way to 100,000. If you do this, it will  replace the output from step (3).

For example:

P(x is prime | x < 10) = [your answer]

P(x is prime | x < 20) = [your answer]

. . . and so on.

(This is read "the probability that x is prime, given x <  10" The vertical bar means "given"). To calculate this, just divide  the number of prime numbers by the upper bound.

 