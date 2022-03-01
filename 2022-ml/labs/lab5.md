# Lab 5: Finding Support Vectors (15 points)

For this lab, given a primal support vector instance, you will find the support vectors, weight vector, and the slack vectors.  Use the provided skeleton and test [code](../code/lab5).

1. In the `weight_vector()` function, compute and return $\mathbf{w}$.
   Recall that
   $$
   \mathbf{w}=\sum_{i=1}^l \alpha_i y_i \mathbf{x}_i
   $$
   Note that $\mathbf{x_i}$ is a vector, while $\alpha_i$ and $y_i$ are scalars.  In the code, `x` is a 2D array where `x[i][j]` is the $j^{th}$ element of $\mathbf{x}_i$.

2. Recall that support vectors are located at when $y_i(\mathbf{w}\cdot\mathbf{x}+b) \pm 1$.  Return a set of support vector indices in the `find_support()` function.  Use the `allclose()` function to determine whether $x\approx y$ with a tolerance by using the `atol` parameter.

3. Find the slack vectors. In `find_slack()` return a set of indices of all slack vectors.  There are two cases: when instances are on the wrong side of the margin and when instances are within the margin.  Implement the case for an instance within the margin.  The first case is already implemented.

This lab will be graded based on passing the unit tests.  Place your name in the first comment line of your code.