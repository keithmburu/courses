---
marp: true
theme: default
class:
#- invert
inlinesvg: true
paginate: true
footer: Machine Learning, Fall 2022\nAlvin Grissom II, Haverford College

---
# Prediction and Logistic Regression
2022-2-6

---
# Regularization
 - We want to stabilize the learning algorithm.
    - Small changes in input will not lead to large changes in output.
Given a loss function $\mathscr{L}(\mathbf{w})$, we add a regularization function $R(\mathbf{w})$.
Our regularized loss then becomes
$$
\mathcal{L}(\mathbf{w}) + R(\mathbf{w}).
$$
- The regularization termm favors simpler hypotheses.
---
# Reminder: Convex Function
<img src ="images/regularization/convex_function.svg" style="width:800px" align="center">

---
# $\mathscr{l}^2$ (Tikhonov) Regularization
With $\mathscr{l}^2$ regularization, $R(\mathbf{w})=\lambda\mathbf{\|w\|}_2^2$, where $\lambda>0$.
Our regularized objective then becomes
$$
\begin{align}
\mathcal{L}_2(\mathbf{w})&=L(\mathbf{w}) + \lambda\mathbf{\|w}\|_2^2\\
&= L(\mathbf{w}) + \lambda \sum_{j=1}^n w^2.
\end{align}
$$
- Here, $\|w\|_2^2$ **stabilizes** the learning.
    - I.e., if we replace one training example with another, the loss/prediction shouldn't change much.

---
# $\mathscr{l}^2$ (Tikhonov) Regularization
Bayesian interpretation:
- Assumes weights are distributed according to a Gaussian distribution with mean $\mu=0$.  
- In Gaussian, farther from the mean $\rightarrow$  lower probability; weights tend toward 0.
![](images/probability/normal_distribution_standard_deviation.png)

---
# Lipschitness
 - A Lipschitz function has a slope that is bounded; it can't chnge too fast.
- A $\rho$-Lipschitz-smooth function has a slope bounded
$$
\|f(\mathbf{w_1})-f(\mathbf{w_2})\|\leq \rho \| \mathbf{w_1} - \mathbf{w_2} \|
$$
For differentiable functions, by the mean value theorem,
$$
f(w_1) - f(w_2) = f'(u)(w_1 - w_2)
$$
where $u$ is a point between $w_1$ and $w_2$
This implies that the derivative, $|f'| \leq \rho$.

---
# Regularization: Bounded Empirical Risk
- If the loss function is $\rho$-Lipschitz and convex, and $R(\mathbf{w})=\lambda \|\mathbf{w} \|^2$, then
$$
    \mathbb{E}[L_D] - \mathbb{E}[L_S] \leq \frac{2\rho^2}{\lambda m},
$$
where $L_D$ is the true risk and $L_S$ is the empirical risk.
i.e., the empirical risk (average loss on training data) vs. the true risk (how well algoirthm works in practice) is bounded.

---

# Regularization: 
We can write:
$$
    \underset{S}{\mathbb{E}}[L_D] =\underset{S}{\mathbb{E}}[L_S] + \underset{S}{\mathbb{E}}[L_D] - L_S
$$
- Second term: difference between real and empirical risk (stability of learning algorithm)
- As $\lambda$ increases, stability  increases, but so does empirical risk.
    - Tradeoff between fitting and overfitting.


---

# Regularization: 
We can write:
$$
    \underset{S}{\mathbb{E}}[L_D] =\underset{S}{\mathbb{E}}[L_S] + \underset{S}{\mathbb{E}}[L_D] - L_S
$$
- Second term: difference between real and empirical risk (stability of learning algorithm)
- As $\lambda$ increases, stability  increases, but so does empirical risk.
    - Tradeoff between fitting and overfitting.
- We can also guarantee learnability.  For every distribution $D$,
$$
    \underset{S}{\mathbb{E}}[L_D] \leq \underset{\mathbf{w}\in \mathcal{H}}{\min}L_D(\mathbf{w}) + \epsilon
$$

---

# Regularization: Learnability
We can write:
$$
    \underset{S}{\mathbb{E}}[L_D] =\underset{S}{\mathbb{E}}[L_S] + \underset{S}{\mathbb{E}}[L_D] - L_S
$$
- Second term: difference between real and empirical risk (stability of learning algorithm)
- As $\lambda$ increases, stability  increases, but so does empirical risk.
    - Tradeoff between fitting and overfitting.
- We can also guarantee learnability if certain conditions are met (convexity, $\beta$-smoothness).  For every distribution $D$,
$$
    \underset{S}{\mathbb{E}}[L_D] \leq \underset{\mathbf{w}\in \mathcal{H}}{\min}L_D(\mathbf{w}) + \epsilon
$$

I.e., empirical risk is bounded by the true loss plus some $\epsilon$.

---

# Regularization: Learnability with SGD
$$
    \underset{S}{\mathbb{E}}[L_D] \leq \underset{\mathbf{w}\in \mathcal{H}}{\min}L_D(\mathbf{w}) + \epsilon
$$
For a convex-Lipschitz-bounded learning problem with parameters $\rho, B$, stochastic gradient descent satisfies this when the number of iterations $T \geq \frac{B^2\rho^2}{\epsilon^2}$ and $\eta=\sqrt{\frac{B^2}{\rho^2 T}}$.

- See Understanding Machine Learninga by Shalev-Shwartz and Ben-David for more detail.
---

# Regularized Logistic Regression with SGD
Recall our loss function for a single example:
$$
y\log p + (1-y)\log(1-p)
$$
and
$$
\frac{\partial}{\partial w_j} \log P(Y=y|\mathbf{x};\mathbf{w}) = (y-p)x_j,
$$
making our update rule
$$
\mathbf{w} := \mathbf{w} + \eta(y - p)\mathbf{x}.
$$

---

# Regularized Logistic Regression with SGD
Recall our loss function for a single example:
$$
y\log p + (1-y)\log(1-p)
$$
and
$$
\frac{\partial}{\partial w_j} \log P(Y=y|\mathbf{x};\mathbf{w}) = (y-p)x_j,
$$
making our update rule
$$
\mathbf{w} := \mathbf{w} + \eta(y - p)\mathbf{x}.
$$
- But we need $R(\mathbf{w}$)

---
# Regularized Logistic Regression with SGD
Our loss function for a single example:
$$
y\log p + (1-y)\log(1-p) - R(\mathbf{x})
$$
and
$$
\frac{\partial}{\partial w_j} \log P(Y=y|\mathbf{x};\mathbf{w}) = (y-p)x_j - \color{green}\mu (w_j)^2,
$$
making our <u>regularized</u> update rule
$$
\begin{align}
w_j &:= w_j + \eta((y - p)x_j - \color{green}2\mu w_j)\\
           &:= w_j + \eta(y - p)x_j - \color{green}2\eta\mu w_j\\
\end{align}
$$


---
# Regularized Logistic Regression with SGD
$$
\begin{align}
w_j &:= w_j + \eta((y - p)x_j - \color{green}2\mu w_j)\\
           &:= w_j + \eta(y - p)x_j - \color{green}2\eta\mu w_j\\
\end{align}
$$
- Reduces overfitting, but requires updating every weight on every iteration, since regularization term doesn't depend on $\mathbf{x}$.

---
# Lazy Regularized Logistic Regression with SGD
$$
\begin{align}
w_j &:= w_j + \eta((y - p)x_j - \color{green}2\mu w_j)\\
           &:= w_j + \eta(y - p)x_j - \color{green}2\eta\mu w_j\\
\end{align}
$$
- Reduces overfitting, but requires updating every weight on every iteration, since regularization term doesn't depend on $\mathbf{x}$.
- Efficiency Trick: Skip regularization updates until the weight is udpated; then catch up.
    - Split update into regularization and log likelihood updates terms.
- Regularization update:
$$
\begin{align}
w_j &:= w_j - 2\eta\mu w_j\\
    &:= w_j(1-2\eta\mu)
\end{align}
$$
- Likelihood update:
If $x_j=0$, $w_j :=w_j + \eta(y-p)w_j$

---
# Lazy Regularized Logistic Regression with SGD
Regularization update: $w_j :=  w_j(1-2\eta\mu)$
Likelihood update: if $x_j=0$, $w_j :=w_j + \eta(y-p)w_j$

- Trick: Skip regularization updates with $x_i=0$.
- Track (in a hash table) how many updates have passed since last regularization update for each weight $w_j$
- When $w_j$ is finally updated with a likelihood updated $(x_j \neq 0)$, perform $m$ regularization updates all at once to catch up and reset counter for $w_j$ to 1.
$$
w_j := w_j^*(1-2\lambda\mu)^m
$$
- Finally catch up one more time before stopping.
- See [Cohen (2012)](https://www.cs.cmu.edu/~wcohen/10-605/notes/sgd-notes.pdf).