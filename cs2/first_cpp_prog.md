# Small Homework: First C++ Program

To get your feet wet with C++, by Thursday, I'd like you to convert your second lab into a C++ program.

Write the program in `lab2.cpp`, and place your function declaration in `lab2.h` with the appropriate [header guards](https://www.learncpp.com/cpp-tutorial/header-guards/).  You should use a basic header file with your function declarations. Your program should start with something like this:

```c++
#include <iostream> //includes I/O operations (cout, cin, etc.)
#include "lab2.h" //includes local header file
```



Header guards prevent files from being included multiple times.  In your `lab2.h`, ensure that your code is sandwiched between the following:

```c++
#ifndef LAB2_H
#define LAB2_H

//your header code here

#endif
```



Compile the program with `g++`.

```
g++ lab2.cpp -o lab2 -std=c++11
```

The `-=std=c++11` flag ensures that you're using C++ 11, which is the version I'll assume you're using when grading your assignments.

Also read through lecture 4 of the MIT OCW lecture notes, and be prepared with questions.  Note that modern C++ includes a `string` type, so it's no longer necessary to use pure character arrays to represent strings.

https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-096-introduction-to-c-january-iap-2011/lecture-notes/