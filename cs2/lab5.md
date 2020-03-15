CS174: Object-oriented Programming

Spring 2020

Ursinus College

# Lab 5: Multithreaded Prime Number Generation

This lab is designed to introduce you to using multiple threads to solve a problem.  In a more complex multithreaded program, you would ideally use data structures optimized for threading, but this lab is intended give you some basic practice.


You will generate prime numbers.  A prime number is any number divisible by only itself and 1.  To make the computation faster, you will generate prime numbers in at least two threads.  Nearly all modern computers have at least two cores; so, there should be a substantial speedup over using only a single thread.

To make things easier, I've already written the function `isPrime()` to test for prime numbers with a naïve algorithm.  It simply tests whether any number less than *n* can evenly divide it. (Can you think of a way to make it more efficient?)



First, you must implement the `Runnable` interface, which requires that you implement a `void run()` method.  See the JavaDoc documentation for `Runnable` here: https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html (or just use a search engine).

The `run()` method is executed when the thread is started. 
    

 Your class's constructor should take a start index, an end index, and an `ArrayList<Integer>`.
 Each thread will find primes in the range of [*start,end*) and add them to the `ArrayList`.

Your program will add a new prime number to the `ArrayList` whenever it finds one.  When both threads have completed, print out the contents of the `ArrayList`.  You can wait for threads to finish with `join()`.


 Since you've written an `ArrayList` (a vector) from scratch, you know that, under the hood, it is performing some relatively complex operations.  

 First, try running your program with two threads, one going from 1 to 100 and another going from 101-200.  What happens?

 You may see a `ConcurrentModificationException`.  Look at the line number of the error.  What's happening is that you have two threads trying to access the `ArrayList` at the same time.  To prevent this, we must prevent more than one thread from modifying the `ArrayList` concurrently.  We can do this with the `synchronized` modifier in the appropriate place.  

To do this, put your ArrayList's `add()` operation inside of a `synchronized` method, and call it instead of doing the modification directly.

