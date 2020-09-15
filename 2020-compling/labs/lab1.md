Computational Linguistics, Fall 2020

# Lab 1: Markov Text Generation

Write the following Python program.  If you do not finish during the lab time, complete it as a homework assignment.

1. Draw the probabilistic finite state machine represented by the following probabilities.  For this lab, you may simply draw it and take a photo, but you may want to use this as an opportunity to learn to easily create graphs with [Mermaid](https://support.typora.io/Draw-Diagrams-With-Markdown/) (or some other tool, such as GraphViz).

- The first letter is always "I".
- The subsequent letters will be generated with the following probabilities:
- P(_ | I) = 1
- P(A | _) = 0.5
- P(L | _) = 0.5
- P(M | A) = 0.4
- P(_ | M) = 0.8
- P(! | M) = 0.2
- P(L | A) = 0.6
- P(I | L) = 1
- P(V | I) = 0.95
- P(E | V) = 1
- P(N | I) = 0.05
- P(E | N) = 1
- P(! | E) = 1
- P(_ | !) = 0.7
- P(I | !) = 0.2
- P(! | !) = 0.1

2. Write a Python program that outputs the result of this Markov model. The program will output 100 letters per line, on 10 lines.  It will output *nothing else*.