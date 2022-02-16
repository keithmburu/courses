---
marp: true
theme: default
class:
#- invert
inlinesvg: true
paginate: true
footer: Machine Learning, Fall 2022\nAlvin Grissom II, Haverford College

---
# Support Vector Machines
2022-2-15

---

- So far, we've talked about two linear classifiers
    - perceptron: use the sign of $\mathbf{w}^T\mathbf{x} + b$ to predict $y\in\{1,-1\}$.
    - logistic regression: use $P(y = 1 | \mathbf{x}) = \sigma(\mathbf{w}^T\mathbf{x} + b)$ to predict $y\in \{0,1\}$.
    - Both find linear decision boundaries.

---
# Support Vector Machines
   * Support vector machines (SVMs) are powerful linear classifiers.
     - also use sign of $\mathbf{w}^T\mathbf{x} + b$ to predict $\hat{y}\in\{1,-1\}$.
     - not probabilistic
     
---
# Support Vector Machines
   * Support vector machines (SVMs) are powerful linear classifiers.
     - also use sign of $\mathbf{w}^T\mathbf{x} + b$ to predict $\hat{y}\in\{1,-1\}$.
     - not probabilistic

 $$
    {h(x)}=
    \begin{cases}
    1,& \text{ if}\quad \mathbf{w}^T\mathbf{x} + b \ge 0\\
    -1,& \text{ if}\quad \mathbf{w}^T\mathbf{x} + b < 0
    \end{cases}
 $$
- Decision function is therefore at $\mathbf{w}^T\mathbf{x} + b = 0$.

---
# Maximum Margins
Assume data are linearly separable.
- We want to find a hyperplane that perfelctly separates the data
![bg right](images/svm/hyperplanes.svg)

---
# Maximum Margins
Assume data are linearly separable.
- We want to find a hyperplane that perfelctly separates the data
- But there are infinitely many such hyperplanes.
    - Which one should we find?
![bg right](images/svm/hyperplanes.svg)


---
# Maximum Margins
Assume data are linearly separable.
- SVMs find hyperplane with a **maximum margin**.
    - creates **maximum margin classifier**
- $H_2$ separates data, but has a small margin.
- $H_3$ separates data with wide margin.

![bg right](images/svm/hyperplanes.svg)

---
# Maximum Margin Training
- Idea: creaate as much space as possible between data and margin.
- How do we choose this hyperplane?
![bg right](images/svm/hyperplanes.svg)

---
# Maximum Margin Training
- Idea: creaate as much space as possible between data and margin.
- Find weights that make this margin maximally wide.
![bg right](images/svm/hyperplanes.svg)

---
# Maximum Margins
Let $\mathbf{w}^T\mathbf{x} + b$ be a linear classifier.
- The geometric **margin** of a classifier at a point $\mathbf{x}$ is its Euclidean distance to the hyperplane $\mathbf{w}^T\mathbf{x} + b = 0$.
- The margin (yellow) is determined by the points closest to the separating hyperplane, called **support vectors**.
- Why choose the maximum margin?
![bg right 99%](images/svm/maxmargin.png)

---
# Maximum Margins
Let $\mathbf{w}^T\mathbf{x} + b$ be a linear classifier.
- The geometric **margin** of a classifier at a point $\mathbf{x}$ is its Euclidean distance to the hyperplane $\mathbf{w}^T\mathbf{x} + b = 0$.
- The margin (yellow) is determined by the points closest to the separating hyperplane, called **support vectors**.
- Why choose the maximum margin? "Safest" margin
 
![bg right 99%](images/svm/maxmargin.png)

---
# Maximum Margins
Let $\mathbf{w}^T\mathbf{x} + b$ be a linear classifier.
- The equations for these parallel hyperplanes that form the margin are $\mathbf{w}^T\mathbf{x} + b = \pm 1$.
- $\mathbf{w}$ is a vector normal to the hyperplane
- Size of margin is $\frac{2}{\|w\|}$
    - Note: $\frac{b}{\mathbf{\|w\|}}$ [determines offset of the hyperplane from the origin](https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_plane).
 
![bg right 99%](images/svm/maxmargin.png)

---
The distance from the hyperplane of a point x is given by $\mathbf{x}$.
$$
\rho_h(\mathbf{x})=\frac{|\mathbf{w}^T\mathbf{x} + b|}{\|\mathbf{w}\|_2}
$$
The **margin** $\rho_h(\mathbf{x})$ of the classifier is its distance to the hyperplane $\mathbf{w}^T\mathbf{x}+b=0$, where the numerator is 1 (because $\|\mathbf{w}\|$ is the one-sided margin size):
$$
\rho_h(\mathbf{x})\equiv\frac{1}{\|\mathbf{w}\|}
$$
- 
![bg right 99%](images/svm/maxmargin.png)

---
The distance from the hyperplane of a point x is given by $\mathbf{x}$.
$$
\rho_h(\mathbf{x})\equiv\frac{1}{\|\mathbf{w}\|}
$$
- Thus, to maximize margin, we need to minimize $\|\mathbf{w}\|$, such that there are no points between the margin.

![bg right 99%](images/svm/maxmargin.png)


---
The distance from the hyperplane of a point x is given by $\mathbf{x}$.
$$
\rho_h(\mathbf{x})\equiv\frac{1}{\|\mathbf{w}\|}
$$
- Thus, to maximize margin, we need to minimize $\|\mathbf{w}\|$, such that there are no points between the margin.
- Find $(\mathbf{w}, b)$ that optimize
$$
    \underset{\mathbf{w}, b}{\min}\frac{1}{2}\|\mathbf{w}\|^2,
$$

where $\forall i \in [1,m], y_i(\mathbf{w}\cdot\mathbf{x}+b)\ge 1$
- The squared version is used for convenience.
- This is a quadratic programming problem.
- Has a unique solution.

---
# Maximum Margins
- How does this differ from logistic regression?
 - LR maximizes the probability of the correct class given the data.
    - SVMs maximize the **distance** of the margin from the closest training examples to the margin.
      We call these "hard margin" SVMs because there is an absolute cutoff.
![bg right 99%](images/svm/maxmargin.png)

---
# Maximum Margins
- How does this differ from logistic regression?
 - Hard SVMs assume that the training data are linearly separable.
 - Logistic regression does not assume this. (Why?)
 - Navie Bayes, logistic regression, perceptron, and SVMs are are all linear classifiers.
 ![bg right 99%](images/svm/maxmargin.png)

---
# Suppoer Vector Machines
- SVMs are one of the most popular ML methods.
    - Have been extremely popular for several decades.
    - Recently, neural networks/deep learning are more popular.

---
# Support Vector Machines?
- What's the obvious problem here?

---
# Support Vector MAchines?
- What's the obvious problem here?
    - Data often aren't linearly separable.
    - There are a couple of ways to deal with this.

---
# Soft-margin SVMs
- In practical settings, often training data are not linearly separable.
- This makes the QP problem impossible to solve.

---
# Soft-margin SVMs
- In practical settings, often training data are not linearly separable.
- This makes the QP problem impossible to solve, i.e., there's an $\mathbf{x}_i\in[m]$, such that
$$
y_i[\mathbf{w}\cdot\mathbf{x}+b] \ngeq 1
$$
- We can introduce a relaxation: 
For each $i\in[m]$, $\exists\xi_i \ge 0$, such that 
$$
y_i[\mathbf{w}\cdot\mathbf{x}+b] \ge 1 - \xi_i
$$