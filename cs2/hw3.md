CS-174 Object-oriented Programming

Spring 2020

Ursinus College



# Programming Assignment 3: C++ IntVector

To ease you into writing C++ programs, you will implement the IntVector that you have already implemented in Java.  Though the data structure is conceptually identical, you will learn the following important concepts and conventions unique to C++.

* C++ class syntax
* `const` functions
* the relationship between arrays and pointers
* memory management in classes
  * `delete`, `delete[]`, and destructors
  * using the free store for dynamically-sized arrays
  * separating header file declarations from implementations



For this assignment, implement the IntVector in C++ in `intvector.cpp`.  As in the previous assignment, when the internal array is full, double its size.  You will not be provided with test code to test this assignment, but you are encouraged to write your own. You have been provided with `intvector.h`.  You should implement all of its functions according to the same logic as the first assignment.

Be sure to add the header guards to the header file:

```c++
#ifndef INTVECTOR_H_
#define INTVECTOR_H_

//header code here

#endif  
```



Compile your file with C++11.

Submit to Canvas a file in the format `lastname_firstname.tar.gz` with your `.cpp` and `.h` files in a directory called `/lastname_firstname` with your actual first and last names.

You may want to complete the debugging lab before working on this assignment.  In addition to the course notes, you may find the following references useful:

http://faculty.cs.niu.edu/~mcmahon/CS241/c241man/node90.html

https://www.w3schools.com/cpp/

https://en.cppreference.com/w/

