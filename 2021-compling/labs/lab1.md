Computational Linguistics, Fall 2021

Haverford College

Alvin Grissom II

# Lab 1: Command Line and I/O Redirection

The purpose of this lab is to allow students to familiarize themselves with the command line and very basic Unix shell scripting.  Unix tools are commonly used in corpus linguistics. While we could write a Python program to perform the tasks in this lab, it's important to become comfortable with the command line.

First, create a directory in your home directory (`/home/your_username`, but abbreviated as `~`) with the `mkdir` (make directory) command

```bash
mkdir lab1
```

You can change to this directory with `cd`.

```bash
cd lab1
```

By typing `ls`, you'll see that the directory is empty.

Download a plain text file from [Project Gutenberg](https://www.gutenberg.org).  You can retrieve Milton's Paradise Lost here: https://www.gutenberg.org/cache/epub/26/pg26.txt

Rather than using your web browser, you can use the `curl` or `wget` command to retrieve it directly, and use `ls` to confirm that it was downloaded successfully.

You can use `less` or `more` to glance at the file.  While `less` uses pagination and scrolling, `more` does not.  You can paginate with [SPACE] quit `less` with `q`.

Use the `wc` command to note the number of lines in the file.  You can use the `-l` flag to only display the number of lines.  

Now, we're going to count the total number of *unique* words in the file.  To do this, we'll use the `uniq` command, which counts the duplicates in a file that is sorted, looking for duplicates on adjacent lines.  But first we need to sort the file with `sort`.  (Remember the `man` pages if you're not sure how to use a command; often you can also add `--help` to the command to get information.)

We can use either input redirection or pass the file as an argument to sort.  We'll use the former.

```bash
sort < /pg26.txt
```

This prints the entire file to the terminal by default.  When we look at it, we see that it is sorting every line. But we want individual words on each line.  We can use the `tr` (translate) command.

```bash
tr -s ' ' '\n'
```

The first argument, `' '`, is the character being replaced; the second, `\n` is what it's being replaced with.  The `-s` (squeeze) does this with repeated characters -- in this case, spaces, as well.  Try it, and then output the result to a new file with I/O redirection.

Look at the file.  Do you see any problems?  We need to remove all of the non-alphanumeric characters in order to get only words; the punctuation shouldn't be considered part of the word.  Ideally, we would do this *before* we do the sorting. (Why?). We can do this with `tr`, as well:

```bash
tr -cd '[:alnum:]' < pg26.txt 
```

What's wrong now? We got rid of the spaces, as well.

```bash
tr -cd '[:alnum:][:space:]' < pg26.txt
```

We can use a **pipe** to combine these two steps:

```bash
tr -cd '[:alnum:][:space:]' < pg26.txt | tr -s ' ' '\n'
```

Alternatively, we could have used `less` or `cat` and another pipe instead of input redirection:

```bash
cat pg26.txt | tr -cd '[:alnum:][:space:]' | tr -s ' ' '\n'
```

We can improve this by replacing the `' '` argument  of the `tr` command to `'[:space:]'`, which is more general.

Next, we'll `sort` the output, which will give us the same words next to each other.  The `sort` command can also only output unique lines of a sorted file, with the appropriate flag.  There's still a problem involving capitalization.  (What is it?)   You can use an argument to the `sort` command to fix it.  

Combine all of these commands and flags with `wc` and the appropriate flag to output the total number of unique words in the file to a new text file, `total_words.txt`. 

Now, make the appropriate changes to you command to instead print out the total count of *each* word in the file, case-insensitive.  You can use the `uniq` command.  Direct the output to `word_counts.txt`.

A portion of the output should look like this:

```
      4 Zephon
      2 Zephyr
      1 Zephyrus
      2 zodiack
```

Finally, use the `head` or `tail` commands, with the appropriate changes to your pipeline, to print out the counts of the twenty most common words, in the same format as above, to a file called `most_common.txt` 

Put all of your commands in a file called `familyname_givenname.sh` (with your actual names) and upload it to Moodle, along with your final output.



