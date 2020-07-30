# Lab 4

In this lab, you will continuously prompt the user to input words  from stdin. You will then test whether the word is a palindrome. You  will use some of the same String functions as in the last lab. If  [word] is an anagram, you will print "[word],Y" on one line. Otherwise, you will print "[word],N".

Your program must work with words that have both even and odd numbers of letters.

BONUS: If the word is not an palinrome, print out the location of the first character mismatch after the comma.

For example: aabba has its first mismatch at position 1. So, you would print the following to stdout:

aabba,1

 Your function should be called `boolean isPalindrome(String input)`.