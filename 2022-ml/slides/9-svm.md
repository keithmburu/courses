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

 Slides have some content from Foundations of Machine Learning (Mohri) and slides by Jordan Boyd-Graber's slides. 

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
The $\xi_i$ variables are called **slack variables**.

---
$$
y_i[\mathbf{w}\cdot\mathbf{x}+b] \ge 1 - \xi_i
$$
- Slack variables measure distance by which a vector $\mathbf{x}$_i violated original inequality constraints.
- An $\mathbf{x}$_i with $\xi_i > 0$ can be called an *outlier*.
- To not be considered an outlier, the $\mathbf{x}_i$ must be on the correct side of the hyperplane.
    - A point within the margin but on the correct side of the hyperplane is still an outlier.
---
# Slack SVMs
![bg right 99%](images/svm/slack_margin.png)
- Slack variables allow us to tolerate some outliers.
- We need to change the objective function.
$$
	\begin{equation}
		\min_{w, b, \xi} {\frac{1}{2}||w||^2} + {C} \sum_{i=1} {\xi_i}^{{p}}
	\end{equation}
$$
subject to $y_i(w\cdot x_i + b) \geq 1 - \xi_i$ and $\xi_i \geq 0, i \in [1, m]$
Image from Mohri et al.

---
# Slack SVMs
![bg right 99%](images/svm/slack_margin.png)

$$
	\begin{equation}
		\min_{w, b, \xi} {\frac{1}{2}||w||^2} + {C} \sum_{i=1} {\xi_i}^{{p}}
	\end{equation}
$$
subject to $y_i(w\cdot x_i + b) \geq 1 - \xi_i$ and $\xi_i \geq 0, i \in [1, m]$
- The larger $\xi_i$, the higher the penalty.
- The constant $C$ determinnes how much weight we give to this penalty.

---
# Slack SVMs
![bg right 99%](images/svm/slack_margin.png)

$$
	\begin{equation}
		\min_{w, b, \xi} {\frac{1}{2}||w||^2} + {C} \sum_{i=1} {\xi_i}^{{p}}
	\end{equation}
$$
subject to $y_i(w\cdot x_i + b) \geq 1 - \xi_i$ and $\xi_i \geq 0, i \in [1, m]$
- The larger $\xi_i$, the higher the penalty.
- The constant $C$ determinnes how much weight we give to this penalty.
- We can use a *loss function*.

---
# Slack SVMs
## Loss Functions
- Logistic regression used cross-entropy loss.
- 0-1 loss gives a loss of 1 for every incorrect example an 0 for every correct example.
- **Hinge loss** gives a loss of 0 for every correct example, with the loss increasing linearly afterwards.
![bg right](images/logistic_regression/hinge_vs_01_loss.svg)

---
# Slack SVMs
## Constrained Optimization
- We can't just use SGD now because we have additional constraints to consider when minimizing the loss function.
- In constrained optimization, we have a method of Lagrange multipliers
  Theorem: Given functions $f(x_1, \dots x_n)$ and $g(x_1, \dots x_n)$, the critical points of $f$ restricted to the set $g=0$ are solutions to equations:
$$
  \begin{align*}
    \frac{\partial{f}}{\partial x_i}(x_1,  \dots x_n) = & \lambda \frac{\partial{g}}{\partial x_i} (x_1, \dots x_n) \hphantom{\dots} \forall i \\
    g(x_1, \dots x_n) = 0 &
  \end{align*}
  $$
  This is $n+1$ equations in the $n+1$ variables $x_1, \dots x_n, \lambda$.

---
# Slack SVMs: Constrained Optimization
Given functions $f(x_1, \dots x_n)$ and $g(x_1, \dots x_n)$, the critical points of $f$ restricted to the set $g=0$ are solutions to equations:
$$
  \begin{align*}
    \frac{\partial{f}}{\partial x_i}(x_1,  \dots x_n) = & \lambda \frac{\partial{g}}{\partial x_i} (x_1, \dots x_n) \hphantom{\dots} \forall i \\
    g(x_1, \dots x_n) = 0 &
  \end{align*}
  $$

  Idea: Given original objective function $f$ with constraints $g$, introduce new variables $\lambda$ that enforce $g$'s constraints.
  - Do this by taking partial derivative of both $f$ and $gb$ to create system of equations for each of the variables to optimize.

  ---
  # Constrained Optimization Example
  - Suppose you want to maximize  $f(x,y) = \sqrt{xy}$ subject to the constraint $20x + 10y = 200$.

---
### Constrained Optimization Example

- Suppose you want to maximize  $f(x,y) = \sqrt{xy}$ subject to the constraint $20x + 10y = 200$.

- Compute derivatives
$$
    \begin{align*}
      \frac{\partial f}{\partial x} = \frac{1}{2} \sqrt{\frac{y}{x}} & \hphantom{\dots} \frac{\partial g}{\partial x} = 20 \\
      \frac{\partial f}{\partial y} = \frac{1}{2} \sqrt{\frac{x}{y}} & \hphantom{\dots} \frac{\partial g}{\partial y} = 10
    \end{align*}
$$
- Create new system of equations.
$$
    \begin{align*}
      \frac{1}{2} \sqrt{\frac{y}{x}} & = 20 \lambda \\
      \frac{1}{2} \sqrt{\frac{x}{y}} & = 10 \lambda \\
      20x + 10y = 200
    \end{align*}
$$

---
- Dividing the first equation by the second gives us
$$
      \begin{equation}
        \frac{y}{x} = 2
      \end{equation}
$$
which means $y=2x$, plugging this into the constraint equation gives:
$$
      \begin{align*}
        20x + 10(2x) = 200 & \\
        x = 5 & \Rightarrow y = 10
      \end{align*}
      $$
- This gives us the solution to optimizing $f(x,y) = \sqrt{xy}$ subject to the constraint $20x + 10y = 200$.

---
# New Lagangian Objective Function

$$
\begin{align}
	\mathscr{L}(\vec w, b, \vec \xi, \vec \alpha, \vec \beta) = & \frac{1}{2} ||w||^2 + C \sum_{i=1}^m \xi_i \\
	 & - \sum_{i=1}^m \alpha_i \left[y_i(w \cdot x_i + b) - 1 + \xi_i \right] \\
	 & - \sum_{i=1}^m \beta_i \xi_i
\end{align}
$$
- Constraint: classifications must be correct, except when they have slack variables (line 2), which must be non-negative (line 3).

---
$$
\begin{align}
	\mathscr{L}(\vec w, b, \vec \xi, \vec \alpha, \vec \beta) = & \frac{1}{2} ||w||^2 + C \sum_{i=1}^m \xi_i \\
	 & - \sum_{i=1}^m \alpha_i \left[y_i(w \cdot x_i + b) - 1 + \xi_i \right] \\
	 & - \sum_{i=1}^m \beta_i \xi_i
\end{align}
$$
Taking the gradients $(\nabla_w \mathscr{L}, {\nabla_b
  \mathscr{L}}, {\nabla_{\xi_i} \mathscr{L}}$) and solving for zero gives us
$$
\begin{equation}
{\sum_{i=1}^m \alpha_i y_i = 0}
\end{equation}
$$
$$
\begin{equation}
{\vec w = \sum_{i=1}^m \alpha_i y_i x_i}
\end{equation}
$$
$$
\begin{equation}
{\alpha_i + \beta_i = C}
\end{equation}
$$

---
# Simplifying Dual Objective
Now, we can simplify the objective function by replacing variables with our new ones.

$$
\begin{equation*}
\textcolor{red}{\sum_{i=1}^m \alpha_i y_i = 0}
\end{equation*}
$$

$$
\begin{equation*}
\textcolor{blue}{\vec w = \sum_{i=1}^m \alpha_i y_i x_i}
\end{equation*}
$$

$$
\begin{equation*}
\textcolor{green}{\alpha_i + \beta_i = C}
\end{equation*}
$$

$$
\begin{equation}
	\mathscr{L}= \left\lVert{\textcolor{red}{\frac{1}{2} \|{{\vec w_i} {{\sum_{i=1}^m \alpha_i y_i \vec x_i}}}}}\right\rVert^2  -  \sum_i^m { \textcolor{}{\sum_j^m \alpha_i \alpha_j y_i y_j (\vec x_j}} \cdot \vec x_i{)} {{- \sum_i^m \alpha_i y_i b}} {- \sum_{i=1}^m \beta_i \xi_i} {{+ \sum_i^m \alpha_i} }
\end{equation}
$$
- First two terms are the same!
- Just like separable case, except that we add the constraint that $\alpha_i \leq C$.
