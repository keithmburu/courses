# Lab 3: Binary to Decimal

NOTE: If you are still using Gedit to write Java code, I would like you to migrate to Atom or emacs.  

If you use Atom, I recommend turning on auto-indent:  Settings->Editor Settings->Auto-Indent. It will ensure that your  code is nicely formatted.

 

Ubuntu users can install it with:

 

```
sudo add-apt-repository ppa:webupd8team/atomsudo apt updatesudo apt install atom
```

 

For this lab, you will convert a binary string to decimal.  

I have provided the framework code for this assignment in [BinaryConverter.java](https://ursinus.instructure.com/courses/6375/files/325194/download)[![Preview the document](https://ursinus.instructure.com/images/preview.png)](https://ursinus.instructure.com/courses/6375/files/325194/download). You need only implement the `decimalToBinary()` function.

You will need to use either the String class's `charAt()` and `size()`  function or `toCharArray()`.  See the JavaDoc documentation.  Part of this lab involves familiarizing yourself with it. [https://docs.oracle.com/javase/7/docs/api/java/lang/String.html (Links to an external site.)](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)

If you have a String, you can get a character array with the individual characters easily. 

For example:

````java
String s = "hello";
char[] characters = s.toCharArray();
//the array contains the characters h, e, l, l,* and o.
````

 

As we have discussed binary in class, I have provided a brief explanation below.

Binary strings are made up of 1s and 0s.  Each place represents a  power of 2, with the powers increasing from left to right..  If there's a 1 in the position, we add it to the total; otherwise, we skip it.

For example, consider 110:

**1      1**     0

**2^2   2^1**    2^0

Since there is a 0 in the 2^0 position, we don't add it.  So, we have:

2^2 + 2^1 = 6

 

Consider 010:

0      **1**     0

2^2   **2^1**    2^0

2^1 = 2

 

Consider 111 101:

**1     1      1    1**     0     **1**

**2^5  2^4   2^3   2^2**   2^1    **2^0**

 

2^5 + 2^4 + 2^3 + 2^2 + 2^0 = 61

 

 