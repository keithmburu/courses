Computational Linguistics, Fall 2020

# Matrices and I/O Redirection

## Background

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



1. Write a Python program that multiplies two matrices of arbitrary size together and reports and error if the matrices cannot be multiplied due to their dimensions.  The matrices will be read from a `stdin` in the following format:

```
1 2 3
4 5 6
X
7 8
9 10
11 12
--
1 3 4 9
3 8 9 10
X
2 8 
1 8
3 0
8 1
--

```

The first matrix is followed by an `X`, which is then followed the by the second matrix, which is terminated by two dashes.  Then, the next matrix example is given.  The *only* output of your code should be the result, one per line.  For example:

```
38
30
38
11
38
```

2.  Write a Python program that, given an input file in the same format as the input file of Part 1, prints  to `stdout`  the transpose of the matrices in the input file, in the same format.  For example, given the input from Part 1, the output should be:

```
1 2
3 4
5 6
X
7 9 11
8 10 12
--
1 3
3 8
4 9
9 10
X
2 1 3 8
8 8 0 1
```

Check your answers with numpy.

3. Using I/O redirection, pipe the output of Part 2 into the input of Part 1 and send the output of this into a file called `results.txt`.  Put the command used to achieve this in a file called matrices.sh

