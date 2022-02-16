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
$
 
![bg right 99%](images/svm/maxmargin.png)