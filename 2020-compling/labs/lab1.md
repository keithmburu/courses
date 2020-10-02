Computational Linguistics, Fall 2020

# Lab 1: Markov Text Generation

The purpose of this program is to (1) give students practice using Python and (2) give students practice working with conditional probabilities. 

Write the following Python program.  If you do not finish during the lab time, complete it as a homework assignment. This lab is worth 5 points.  You may find the code in [lab1.py](code/lab1.py) to be useful.

1. Draw the probabilistic finite state machine represented by the following probabilities.  For this lab, you may simply draw it and take a photo, but you may want to use this as an opportunity to learn to easily create graphs with [Mermaid](https://support.typora.io/Draw-Diagrams-With-Markdown/), which is integrated into Typora, or some other tool, to generate graphs, such as GraphViz.

- The first letter is always "I".

- The subsequent letters will be generated with the following probabilities:

  

- P(A | _) = 0.5

- P(L | _) = 0.5

- P(M | A) = 0.4

- P(_ | M) = 0.8

- P(! | M) = 0.2

- P(L | A) = 0.6

- P(I | L) = 1

- P(_ | I) = 0.2

- P(N | I) = 0.25

- P(V | I) = 0.55

- P(E | V) = 1

- P(E | N) = 1

- P(! | E) = 1

- P(_ | !) = 0.7

- P(I | !) = 0.2

- P(! | !) = 0.1

  

2. Write a Python program that outputs the result of this Markov model. The program will output 100 letters per line, on 10 lines.  It will output *nothing else*.  You can access `stdin` and `stdout` streams directly by importing from `sys`, which will allow you to print without newlines.  Alternatively, you can use `print` with an extra optional argument. 

   ```python
   print("a string", end = '')
   ```

   