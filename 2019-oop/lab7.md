# CS 174 Lab 7: Simple Parsing in C++

## Introduction

The purpose of this lab is to give you practice writing and structuring a C++ program.

Hyptertext markup language (HTML) is the original language or web pages.  It was grown more complex over the decades, but the basic idea is simple.  An HTML file is a text file with *tags* that determine how the various text and images will be displayed on the page.  The web browser _parses_ the .html file (just as the Java and C++ compilers parse the .java and .cpp files) and displays interprets it to display rich text.

For example, to display the title of a web page, which would appear in the title bar, you would type the following:

```php+HTML
<title>My Web Page</title>
```

The opening tag `<title>` has a closing tag `</title>, and the text between the opening and closing tags displays the title.

To create a [hyperlink](), which you can click, you would type the following:

```html
<a href="http://www.ursinus.edu">Ursinus</a>
```

This creates a clickable hyperlink which will take you to Ursinus's web page, displaying the text "Ursinus" in a web browser.

## Provided Code

I've provided you with some starter code, `extractor.h` and `extractor.cpp`.  Study them to get a sense of what's going on, and ask questions during the lab if you're unsure about something.  I've also provided some code for extracting the title from a web page.  Study it to understand how it works.

This code uses two C++ string library functions, `find()` and `substr()`.  You may use others.

You can find the documentation for these functions here:

http://www.cplusplus.com/reference/string/string/substr/

http://www.cplusplus.com/reference/string/string/find/

It's easy to find others by searching the web for what you want to do and adding "C++ 11."

## Assignment

## 

1. HTML tags are case insensitive.  Modify the provided code to return the title, even if `<TITLE>` and `</TITLE>` are are capitalized.
2. Implement the code for `get_hyperlink()`.  For example, if the first hyperlink were the Ursinus link above, the function should return a `string` with the followng contents:

```
Link Name:
Location: http://www.ursinus.edu
```

3. In your `main()`, read the file and test your functions.  You can either do this through `stdin` (`cin`) or through reading the file directly, as here: http://www.cplusplus.com/doc/tutorial/files/

Compile your program with C++ 11:

```bash
g++ extractor.cpp -o extractor -std=c++11
```

