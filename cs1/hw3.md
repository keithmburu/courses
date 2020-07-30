# Programming 3: Counting Words

In this assignment, you will read a file and count the number of words or lines.

1. Download the following file:

[http://constitution.org/usdeclar.txt (Links to an external site.)](http://constitution.org/usdeclar.txt)

2. Write a Java program, called WordCounter, that counts the words  in a file by reading from stdin. Its output should match that of the wc command in Linux and be written to stdout.

If you pass the argument `-l`, the program should print out the number of lines, instead.

Your program should have two (non-static) functions, `countLines(Scanner)` and `countWords(Scanner)`

Example:

`WordCounter -l < declaration.txt`

 