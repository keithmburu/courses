# Lab 7 (10 pts)

You may find [Chapter 8](https://www.nltk.org/book/ch08.html) of the NLTK book useful.  I have also provided starter code.  While it is not necessary, note that if you are working in the Windows Subsystem for Linux or remotely via SSH, you may not be able to use `draw()` to render the tree parses on your screen.  For this, you'll have to run the native Windows version of Python. 

## Description

For this lab, you will modify a context-free grammar to increase its precision.  The provided **lexicalized** grammar is language $\mathscr{L}_1$ from [Chapter 13](https://web.stanford.edu/~jurafsky/slp3/13.pdf) of J&M.  

* The [provided code](https://github.com/acgrissom/courses/blob/master/2020-compling/labs/code/lab6.py) will use `generate()` to generate up to $n$ possible sentences from the grammar specification with a given maximum recursive depth. 
* The provided code uses `parse()` to produce a legal parse of a given string (a sentence or clause, in human language) and print the possible parses in parenthetical form.  If there is more than one parse, the string is **ambiguous.** 

## Assignment

You will notice that several of the sentences allowed by the grammar are ungrammatical in English.  You will improve this grammar by implementing at least **one** of the following without breaking the functional parts of the grammar.

A.

1. Distinguish between subject and object pronouns, disallowing sentences like "I book I" but allowing sentences like "I book me."
2. Implement some version of "and" and "or."
3. Implement any generative form of negation.
4. Handle the prepositional phrase starting with "with", e.g., "with him."

B.  Submit your revised code and a PDF which briefly (a few sentences) describes how you approached this problem and what your solution was.  