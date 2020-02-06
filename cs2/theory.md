Midterm

CS174: Object-oriented Programming

F2019 Name: |

1

1. (5 points) Select the most accurate denition of an object.

A. An object is the child of a class. 
B. An object is an instance of a class.
C. An object is a variable of a class type.
D. An object is any variable.



2. (5 points) Select the answer that is false of an interface.

A. An interface may contain unimplemented methods.
B. An interace must be implemented by at least one class to be useful.
C. It is impossible to declare a variable that is the type of an interface.
D. Interfaces are stored in .java les.



3. (5 points) Which of the following is false of an abstract class?

A. Abstract classes cannot have any fully-implemented methods.
B. Abstract classes may have instance variables.
C. Abstract classes must be extended by at least one subclass to be useful.
D. Abstract classes may have both abstract and non-abstract methods.


4. (5 points) Select the false statement about class inheritance.

A. A Java class may extend a superclass and implement an interface.
B. A Java dclass may implement multiple interfaces. 
C. A Java class may directly extend more than one class.
D. A Java class may inherit both methods and instance variables from a superclass.


5. (5 points) Select the false statement about recursion.

A. Recursive solutions are faster than equivalent solutions that use only loops.
B. Recursion in code requires a base case to terminate the recursion.
C. Recursive solutions place multiple calls to the same functions on the stack.
D. Deep recursion runs the risk of overowing the stack.



6. (5 points) Which data structure is used to store function calls?

A. The stack 
B. The heap
C. The linked list
D. The Java queue


7. (5 points) In a multithreaded program, which interface must a thread class implement to run in a separate thread?

A. Thread
B. Runnable 
C. Threadable
D. Run


8. (5 points) Suppose you have typed the following:

```java
MyThreadClass a = new MyThreadClass();
Thread t = new Thread(a);
```
where ```MyThreadClass``` is a thread class. What would you type to start the thread. A. t.run();

B. ```a.run(); ```
C. ```t.start();```

 D. ```t.start();```

9. (5 points) Which of the folowing is false about a linked list?

A. A linked list always uses more memory than a vector.

B. A linked list requires RAM for both its references and its values.

C. Linked list insertions are generally faster than those in a vector.

D. Linked list removals are generally faster than those in a vector.



10. (5 points) Which of the following is false about a vector?

A. A vector manages an internal array.

B. In the worst case, a vector must copy all of its elements to a new array for insertion.

C. For an array that need not be resized, set() operation is generally faster than that of a linked

list.

D. Removing an element in a vector only requires one operation.



11. (5 points) Which of the following is false of a binary tree?

A. A binary tree requires more RAM than a linked list.

B. A binary tree insertion is at least as fast as a linked list with the same elements.

C. A binary tree requires RAM for references.

D. Each node in a binary tree has at most two children.



12. (5 points) Which of the following is a LIFO data structure? A. A stack B. A queue C. A linked

list D. A binary tree



13. (5 points) Which of the following is a FIFO data structure? A. A stack B. A queue C. A linked

list D. A binary tree



14. (5 points) Which instance variable/method type is visible only to classes in the same package? A. volatile

B. public C. private D. protected



15. (5 points) What is the decimal value of the binary number 0101? A. 2 B. 3 C. 4 D. 5



16. (5 points) Suppose you have two progams, A and B. Which command will send the output of A directly

to the input of B.

 A. ```bash A | B```
 B. ```bash A > B```
 C. ```bash A >> B```
 D. ```bash A || B```


17. (5 points) Which of the following is true? 

 A. By default, if output redirection is used, ```stderr``` will still print to the terminal 
 B. The stdout streams of two dierent programs are the same. 
 C. ```stdin```always waits for keyboard input 
 D. The ```stderr``` streams of two dierent programs are the same.



18. (5 points) Consider the following code:

```java
Scanner s = new Scanner(System.in);
while(s.hasNextLine()) {
    System.out.println(s.nextLine());
}
```



When will this loop terminate? A. Never B. When EOF is reached. C. When EOF is reached or

the user interrupts the program. D. When the user interrupts the program.



19. (5 points) Which of the following changes a String a to  ``float``` b? 

    A. ```oat b = (oat)a```; B. ```oat b = oat(a);``` C. ```oat b = Float.parseFloat(a);``` D. ```float b = Float.toString(b);```



20. (5 points) If Parent is a subclass of Child, which of the following must be invalid code? 

    A. ```Child c = new Parent();```

     B. ```Parent p = new Child();```

     C. ```Parent p = new Parent();```

     D. ```Child c = new Child();```

    





