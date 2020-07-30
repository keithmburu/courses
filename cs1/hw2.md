**Week 2 Programming Assignment** 

This assignment has multiple parts.  Do not wait until the last minute!

For this assignment, you will write several functions in a class  called SumProd.  To refresh your memory on a few things, you may find  this helpful (or just use your  book). https://docs.google.com/presentation/d/1CXAsXAcg3inltEtHpQT-520eJL12Ph4NQP6dCS8c2yg/edit?usp=sharing

**You should do all of your work in a directory called week2**.

 

The function `computeRandom()` will compute a  pseudorandom number between 0 and 100 and return that number.  You can  get a pseudorandom number between 0 and 1 by calling `Math.random()`.

Recall that you can define any array of size *n* in this way:

`double foo = new double[n];`,

where *n* is a defined int variable or a number.

 The function *sum* will take as an an argument a single array of floats.  It will sum all elements in the array and return the result.  

1. That is, given an array, **x**, it will compute 

$$
sum(\mathbf{x})\ = \sum_{x=1}^n x_i = x_1 + x_2 + \ldots + x_n
$$



where *n* is the length of the array.  Recall that you can find the length of an array `arr` with `arr.length`.

2. a. Write a function called *factorial,* which will take as an argument an integer *n* and will compute the product in the same way.  I'm providing you with this function, but you should type it yourself (without copying and pasting) and understand how it works.

$$
n!= \prod_{k=1}^n k = n(n-1)(n-2)\ldots(2)(1)
$$



```java
    public int factorial(int n) {        int fac = 1;
        if (n == 0) {
            return fac;
        }
        for (int k = 1; k <= n; k++) {
            fac *= k;
        }
        return fac;
    }
```

b. Create a public static void main() method in this file to test that it works correctly.

In this main() method, you'll have something like this:

```java
int[] . . .

SumProd sp = new SumProd();

sp.sum(. . .)

```

You can then run

```bash
javac SumProd.java
java SumProd
```



3. Create another file called `ExpCalculator.java`, which contains a  class of the same name.  Make sure it's in the same directory as your  other file.

You will approximate the mathematical value of *e* in two different ways. Write a function called *binomialExpansion(*int n) that calculates *e* with the following formula:
$$
 e =  \lim_{n\to\infty}\Big(1+\frac{1}{n}\Big)^n
$$


So, we can approximate *e* by sending this function a very large number.

Create a public static void main() in this file to test that it runs correctly.

```
javac ExpCalculator.java
java ExpCalculator
```

4. Write a function called `factorial(int n)` to calculate the value 

5. Write another formula in the same class that approximates this value, in a function `brothersFormula(int n)`
   $$
   e \approx \sum_{n=0}^\infty \frac{2n+2}{(2n+1)!}
   $$
   

6. Create at third class, called Main (which must be in Main.java).  It will only have a public static void main(String[] args) method.

Do the following in your main method, in this order:

a.  Create an array of ints called *primes* that has the following values: 1, 3, 5, 7, 9, 11, 13, 17, 19.  

**Note: To proceed, you will need to have your classes compiled from the previous parts and in the same directory.**

Create an instance of the class that you wrote previously.  Call it sp.

`SumProd sp = new SumProd();`

b.   Pass the array from part (a) to the function you wrote in step (1). Print the result. For example:

`sp.sum(x);`  //except it's not called x.

c.   Pass the values 1, 10, 100, 1000, 10000, 100000, and 1000000 to the functions you wrote in `ExpCalculator`; then, write a blank line; and then pass the values to the function you wrote in (4).

Run your program and make sure it works.

```
javac Main.java
java Main
```

Bonus (10pts): Do this cleverly with a loop instead of multiple explicit function calls.

Bonus (20 pts): Also complete this assignment in Python.

Hint: For Math.pow(), you may need to use do a cast.

Submit all of your Java files to me.  Remember that assignments are due at 11:55pm.