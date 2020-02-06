Data Structures and Algorithms

# Programming Assignment 1: Efficient $n$-grams with a queue

Read this document in its entirety before beginning the assignment.



This assignment has three purposes: (1) to ease you back into writing Java programs and working with the command line, (2) to give you practice using a queue, and (3) to give you practice writing efficient code by using an appropriate data structure.

An $n$-gram is a sequence of n words extracted from a sequence of text. They are typically used in natural

language processing for a variety of tasks, but also in other areas of machine learning and bioinformatics.



Suppose we have a sentence consisting of words $w_1, w_2, \ldots, w_n $ The 2-grams would be $(w_1, w_2), (w_2, w_3), \ldots, (w_{n-1}, w_n)$ .  The 3-grams are $(w_1, w_2, w_3), (w_2, w_3, w_4), \ldots, (w_{n-2}, w_{n-1}, w_n) $.

For example, given the sentence, "The quick brown fox chased the lazy dog", the 2-grams (bigrams) are: the

quick, quick brown, brown fox, fox chased, chased the, the lazy, lazy dog. the 3-grams (trigrams) are: the quick brown, quick brown fox, brown fox chased, fox chased the, the lazy dog.

Your objective for this assignment is to generate the $n$-grams on **one pass through the input String.**

## Part 1 (25 points):

Use a queue to generate all of the n-grams on one pass of a string. Return the complete set of n-grams. You

may have duplicate n-grams.

The input to your function will be a string of lower-case words and a number $n$.

Create a class called Ngramizer in Ngramizer.java . Inside this class, create a public function called:

```java
public List<String> ngrams(int n, String sentence)
```



This will generate all of the n-grams of size n with only one pass through the list using a queue. You may create your own queue class or use one of Java's data structures. It is fairly straightforward to use a `LinkedList` as a queue. Java provides a `Queue` interface which is extended by `LinkedList` and other classes. Each element in the returned list will be an $n$-gram.

```java
Queue<String> q = new LinkedList<>();
```



## Part 2: (25 points):

Create another public method in a second class `BackoffNgramizer` , called:

```java
public List<String> ngrams_backoff(int n, String sentence)
```



This will generate not only the n-grams, but also the $n-1$-grams, $n-2$-grams, and so on, down to the 1-grams. But it must do this in only _at most two_ pass through the list.  The programs will accept words from `stdin` (i.e., `System.in`) **They will not prompt the user or print any information.**

The programs will output each $n$-gram on one line through `stdout`. **They will not print out anything else.**

**Example:**

```bash
java Ngramizer 2
```

**Keyboard Input**

```bash
the quick brown fox chased the lazy dog
```

**Output**

```
<s> the
the quick
quick brown
brown fox
fox chased
chased the
the lazy
lazy dog
dog </s>
```



Recall that you can redirect I/O with theh `<` and `>` symbols.  See Chapter 1 of your book for more information.

For example, the following code will redirect the text in `mytest.txt` to `stdin`:

```bash
java Ngramizer < mytest.txt
```



## Style (10 points)

Follow the Ursinus style guide for you code.

## How to proceed

* To do this assignment, you segment the input sentence into words, splitting by space.  There are several ways to split by space in Java: there's the `String` class's `split()` method, `StringTokenizezr` (deprecated), and manually checking each character in the string by using the `String` class's `charAt()` method.  Do some research, either online or by doing your own experiments, to determine which method to use.
*  You must avoid going through the string multiple times.  Including doing the split, you may go through the string or its tokenization a **maximum of twice**.  If you're clever, it's possible to do it once. 
* The purpose of the queue is to hold in memory the current words that you need to create the $n$-gram.  If you go through the string from left to right when creating bigrams, for example, at any given time, your queue should have two words in it.  You can then pop one word off and push the next one to create the next $n$-gram.  Begin each sentence with `<s>` and end with `</s>`.
* You can use any reasonable text to test your program, but I've provided a program to generate random lists of numbers ("words") for your to empirically profile the performance of your program.  You can find it [here](/code/generate_ngrams).  You can use output redirection to send the its output to a file.

```bash
java GenerateNgrams 1 5 10000 > myfile.txt
```

Alternatively, you can pipe the output of the generated $n$-grams directly into your program.

```bash
java GenerateNgrams 1 5 1000 | java Ngramizer
```

One way of simplifying an empirical analysis is to create a bash shell script that automatically generates your data.

* If you prefer to use another programming language for this assignment, discuss it with me first.



## Report (10 poins)

* Write a short report that describes your implementation decisions and the describes time complexity of the two respective algorithms.  You may do this analysis either analytically or empirically.  For the latter, graphs are the most easily understandable way to visualize this information.  Packages such as matplotlib and [seaborn](https://seaborn.pydata.org/), which I highly recommend, are best for this.  (These use Python, but basic Python is easy to learn and the amount required to generate these graphs is small.)   See, for example https://seaborn.pydata.org/generated/seaborn.scatterplot.html
* For the report, use clearly dileniated sections (Implementation, Complexity Analysis, etc.). Use of graphs or tables is strongly encouraged.
* Your code should be easily runnable.



## Deliverables

* Submit your code, which must be easily runnable from the command line, in the manner described.
* Submit your report as a PDF file to Canvas.  I recommend writing it in Markdown for simplicity, but $\LaTeX$ is also acceptable.  An editor such as Typora allows for easy embedding of $\LaTeX$.

