# Lab 7: Identifying Parts of Speech

Download [POSTag-2.java](https://ursinus.instructure.com/courses/6375/files/347741/download?wrap=1)[![Preview the document](https://ursinus.instructure.com/images/preview.png)](https://ursinus.instructure.com/courses/6375/files/347741/download?wrap=1)  (Rename it to POSTag.java with the mv command).

`mv POSTag-2.java POSTag.java`

Today's lab is a game (of sorts).

You write the code for the function `guessTag()` in the code provided.

In this lab, you will write some simple rules to identify whether a  word is a noun, a verb, an adjective, or an adverb.  I have provided a  simple if-statement that identifies about half of the adverbs.  You need to write the remainder of the function to handle as many of the other  cases as possible. You probably won't get 100% accuracy, but I encourage you to try to outperform your classmates.

Do **not** write a lot of rules that simply enumerate the words themselves. Instead, use the `startsWith()`, `endsWith()` (and other?)[ String functions (Links to an external site.)](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to use as few rules as possible.  Look for patterns in the words that you can exploit.  The goal, as in science, is simplicity and generalizability. 

Hint: the order in which you use your if-statements, if-else  statements, and else-statements can make be differences in accuracy. 

Can you beat your classmates on accuracy? I encourage you to work in groups to get the accuracy as high as possible. It can often be  helpful to go to the board to try to figure this stuff out as a team.

One of the goals of this lab is to force you to think logically and to think about how to translate that logic into code.

If you like, you can write your own function to compute overall accuracy.

Note that in a[ real part-of-speech tagger (Links to an external site.)](http://corenlp.run/), such rules would be much too simplistic for English.

**What to submit:**

1. Upload your POSTag.java file

2. Copy and paste the accuracy scores that you managed to obtain in  the text box. If someone(s) does particularly well, I may talk about it in class.