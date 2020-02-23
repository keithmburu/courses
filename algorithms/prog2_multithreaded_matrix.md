Data Structures and Algorithms
Spring 2020

# Programming Assignment 2: Multithreaded Matrix Multiplication

With the proliferation of computers and other devices with multiple CPU cores, now, more than ever, parallelization is used to create algorithms that provide a substantial speedup on computer with multiple CPU cores. Classical, non-parallelized algorithms cannot directly take advantage of these cores and are limited to using only one CPU. In the past, this was reasonable for many use cases, but this is less so the case now.  

This assignment has several parts. 

## Part 1: Matrix Multiplication (10 points)

Given two matrices of arbitrary size (*n* rows and *m* columns), compute their product.

For example, $$ \begin{bmatrix}
1 & 2 & 3 \\
4 & 5 & 6 \\
\end{bmatrix} \times
\begin{bmatrix}
 7 & 8 \\
 9 & 10 \\
 11 & 12 
\end{bmatrix} =
\begin{bmatrix}
58 & 64 \\
139 & 154 \\
\end{bmatrix}.$$

The naïve, iterative $O(n^3)$ algorithm for matrix multiplication is straightforward to implement by following the pseudocode, which is available on the Wikipedia page. We will assume that the multipled matrices have the correct dimensions.

To begin, implement the following function, which takes two matrices, represented by two two-dimensional arrays, `a`, and `b`, and returns their product.  Ensure that this works correctly before moving to the next part.

```java
public static int[]] multiply(final int[][] a, final int[][] b)
```

This method is static because it does not depend on a class state.  Place this function in a class called `MyMatrixMultiply`.

## Part 2: Parallel Matrix Multiplication (20 points)

There are several ways of parallelizing matrix multiplication.  One such algorithm is described in the aforementioned Wikipedia page.  One of the simplest ways is to decouple the innermost loop, wherein the dot product of a given row and column is calculated, allowing the threads to do this work independent of the main thread.

In the same class, `MyMatrixMultiply`, implement the following function:

```java
public static int[] multiplyThreaded(final int[][] a, final int[][] b)
```

To write a basic multithreaded program, you'll have to implement the `Runnable` interface, which stipulates that you implement the `void run()` method.  This method executes when a thread is spawned.  While it's possible to manage threads manually, it's easier for this algorithm to use an `ExecutorService` to manage the threads.

I recommend writing a separate function:

```java
public static int dotProd(final int[][] a,
                          final int[]] b,
                          final int i,
                          final int j)
```

This function can be used for computing the value for a given element in the resultant matrix.  The `i` and `j` can be set when the class is instantiated in the constructor:

```java
public MyMatirxMultiply(final int[][] a,
                        final int[][] b,
                        final int[][] matrix,
                        final int i,
                        final int j)
```



This is necessary because `run()` cannot take arguments.

I've provided you with the compiled `MatrixMultiple.class` of my implementation of matrix multiplication.  Put the `.class`file in the same directory as your Java code, which will allow you to call `MatrixMultiply.multiply()` and `MatrixMultiply.multiplyThreaded()`.  When you run your code, make sure that your answer matches.

In addition, I've provided you with parallelized code to generate mrandom matrices of a specified size.  You can use this as an example of how to write a multithreaded Java program.

## Part 3: Report (20 points)

Your report should be submitted in PDF format, written in $\LaTeX$ or Markdown.

1. Describe your algorithm with a reasonable style of pseudocode.  I recommend the `algorithmic` or `algorithmicx` package in $$\LaTeX$$.  If you use something like Typora for Markdown, you can create reasonable pseudocode by following the following method or use the `pseudocode` language in a code fence: https://github.com/typora/typora-issues/issues/860

2. Experimentally profile your algorithms using the provided code to randomly generate matrices of various sizes. (Do not count the generation step in your profiling.) While this isn't a perfect test on systems that run multiple processes at once (which steal CPU time), as the number of operations grows, we should see a substantial difference between the time each algorithm takes. **Make sure that you run your program on matrices sufficiently large.** It's reasonable to start with extremely small matrices and go into hundreds of thousands of rows and columns.  Is there an approximate function/ratio for the speedup of using multithreading?

    Show the performance as a function of matrix size, and, optionally, show how performance varies when you use different thread pool types.  Do this in the form of a graph. The Seaborn package in Python makes professional-looking graphs.

3. Describe any issues you had in your implementation and how you resolved them, if you did.  Speculate about the causes.

4. Note how many CPU cores your computer has.  If you don't know, you can use the following in Java to get the total number of logical cores.  This may differ from the number of physical cores.

   ```java
   int cores = Runtime.getRuntime().availableProcessors();
   ```

   

## Part 4: Optional: Further Parallelize (Up to 20 extra points)

* Can you improve upon the speed of matrix multiplication even further?
* Can you change the slow random matrix generation such that the multithreaded approach is faster than the single-threaded approach.
* Analyze the performance as a function of the number of threads, in addition to the size of the matrices.

## Part 5: Board Participation (Optional: 5 points)

Post questions and try to answer each other's questions on Canvas.  Do not post code.  

## Deliverables

* Report as a single PDF file

* `.java` files.  **If** you use an IDE, submit a self-contained `.jar` file that can be run from the command line and clear instructions on how to run your code.  Test this before submitting to ensure that it works.

* Code runnable with a single command that shows your matrix multiplication is correct and reports time.

  I should be able to run:

  ```bash
  java MyMatrixMultiply [rowsA] [colsA] [rowsB] [colsB] [numThreads]
  ```

  where `rowsA` is a number that refers to the number of rows in the first matrix, etc. The `numThreads` parameter detemines the number of threads to use for the multi-threaded approach.

  For example:

  ```java
  java MyMatrixMultiply 500 500 500 500 4
  ```

  generates two 500 x 500 matrices and multiples them, first with the single-threaded approach and then with the multi-threaded approach, using four threads. **You do not need to print out the matrices.**

## Included files

You've been provided with the following:

* `MatrixMultiply.class`, a complete, compiled implementation of Parts 1 and 2 that uses the number of logical CPU cores on your machine to determine the number of threads. This can be run with 
  `java MyMatrixMultiply`.  **You can also call my version of the functions described above from your code to ensure that your answers are correct.**
*  `MatrixGenerate.java` A complete, naïve implementation of random matrix generation using both single-threaded and multi-threaded approaches.  (The included multi-threaded implementation is slow.)
* This document.

