Data Structure and Algorithms

Spring 2020

#  Multithreaded MergeSort with Fork-Join

For this assignment, you will take an implementation of the MergeSort algorithm and parallelize it.  The MergeSort algorithm itself is particularly elegant and simple.  We'll base this assignment on a web implementation.[^1] You've been provided with some [starter code](https://github.com/acgrissom/courses/tree/master/algorithms/code/parallel_mergesort).

This assignment is designed to be educational but not particularly verbose; so, don't make it more complicated than it is.  If you're writing a copious amounts of code for the required sections, you're doing something wrong.

As is, this implementation of MergeSort will take an array of integers and sort them.   You are provided with code for generating a text file of random numbers, which can be output to a text file.  For example, to generate 10,000,000 random numbers, you would use I/O redirection to run:

```bash
java RandomNumGenerator 10000000 > 10000000.txt
```

I've also provided you with the basic, functioning, non-parallel MegeSort Java file which can take numbers in an array, sort them, and output the amount of time the program took to sort them.

For this assignment, you will use the Java **fork-join** framework.  This framework was added to Java 7 and it greatly simplifies multithreaded tasks which can be formulated recursively.  The paradigm itself is more general and is not limited to Java programs.  In Java, most of the management of the threads is done by the fork-join library itself; the programmer can therefore focus on writing the program recursively.

There are more advanced features of this paradigm, but the simplest way to use fork-join for MergeSort is to extend `java.util.concurrent.RecursiveAction`.  This will require that you implement `public void compute()`.  This function is analogous to `run()` in `Runnable`.

**Forking** happens when one instance of your class creates another instance of itself to solve a subproblem.  For MergeSort, the subproblems are clear: sorting the left and right halves of your array.  Thus, your `compute()` function should be extremely simple.

###  Part 1 (10 pts):  Sequential MergeSort in fork-join

You'll need to use I/O redirection to read in random number text files generated from RandomNumGenerator.  (Don't just read the filename from the arguments; this is not following directions.)

Use a `Scanner` to read from `System.in` (`stdin`).  Split the line by space and convert each string into an integer to store in your array.

For this part, change the code of the MergeSort algorithm to use fork-join.  You should only have to change the constructor, add a couple of instance variables, and implement `compute()`.  You can then call `compute()`instead of `mergeSort()`.



###  Part 2 (20 pts): Implement parallel MergeSort in fork-join

You can do this with the [varargs](https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/) function `java.util.concurrent.ForkJoinPool.invokeAll()` instead of calling `comptue()` directly.

###  Part 3: (20 pts): Report

Write a report with a graph showing the input size (of random numbers) vs. time. As usual, time should be on the  y-axis and the input size on the x-axis for both threaded and multithreaded approaches (with varying numbers of threads).   What was the speedup? Document any issues you may have had in your implementation.  You should try this **at least** up to 10,000,000 numbers.  Did you verify that the results were the same? 

For larger numbers, you may run into heap space issues if you naively read in your file.  If you're using a shared machine, make sure that no one else is running this experiment at the same time.

#### Bonus (5 points): Use the Python [seaborn](https://seaborn.pydata.org/) library to generate your graph.

#### Bonus: (15 points) Change your implementation to use Java generics to work with any type `T` which extends `Comparable`.

####  Deliverables

1. Your code (zipped as a .zip or tar.gz).
2. If you use an IDE with some kind of directory structure, include a runnable JAR file with your code.
3. Your report, written in Markdown or LaTeX, as a PDF.
4. Instructions on how to run your code in a README.md

[^1]: https://www.baeldung.com/java-merge-sort









