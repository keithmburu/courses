# Lab 8 (Optional) Debugging with GDB

This lab will introduce you to debugging C/C++ programs with GDB.  To compile a program for debugging, we ad the `-g` flag to `gcc`.  For example, to compile a file `myfile.cpp`into an executable file called `myprog` in debug mode, we type:

```bash
gcc -g myfile.cpp -o myprog
```

To begin debugging, type:

```bash
gdb -tui myprog
```

(Of course, replace `myprog` with whatever your actual program's name is.)  The `-tui` flag gives us a helpful view of the source code as we're debugging.  You can quit `gdb` with `quit`.  You can type `help` at any time to see possible commands.

Consider the provided program, `buggy1.cpp`.  It should print out the first 6 primes numbers, but if you run it, you see that this is not the output.  Let's use GDB to debug it.

```bash
g++ -g buggy1.cpp -o buggy1
gdb -tui buggy1
```

Type `run` (or just `r`).

The program will run and exit successfully, but the output will not be what we want.  Let's examine the program with GDB.

We can seet a *breakpoint* with the `break` command.  This tells the debugger to halt execution at this point so that we can examine the state of the program.

```
(gdb) break buggy1.cpp:12
```

This will break at line 12.  Type `run`.

We brek in the `main()` function on line 12.  We can now examine the memory.

```
(gdb) print primes
$1 = (int *) 0x55555556ae70
```

This tells us that the `primes` variable is an `int*` at this memory address.

```
print primes[0]
$3 = 1
```

This tells us that the value of `primes[0]` is 1.

Note: You can also break at specific functions. For example `break go` will break at the function called `go()` if it exists.

You can continue to the next breakpoint with `continue`or go to the next line of code with `step`.  (or `s` and `n`).  Pressing [ENTER] will repeat the previous command.

If you want to stop the program whenever a particular variable (say, `x`) is modified, use `watch`.

```
(gdb) watch x
```

You can also use the dot and arrow operators for objects/structs pointers to them.

Other useful commands are:

`finish` : run until current function is finished

`backtrace`: generate function stack trace leading to a segmentation fault. 

GDB is a powerful tool, and this only scratches the surface.  You can also run GDB directly from `emacs` with `M-x` `gdb`.  See: https://kb.iu.edu/d/aqsy

## Assignment

Use GDB to fix `buggy2.cpp`.

