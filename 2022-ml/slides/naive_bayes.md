---
marp: true
theme: default
class:
paginate: true
footer: Machine Learning, Spring 2022\nAlvin Grissom II, Haverford College
div.colwrap {
  background-color: inherit;
  color: inherit;
  width: 100%;
  height: 100%;
}
div.colwrap div h1:first-child, div.colwrap div h2:first-child {
  margin-top: 0px !important;
}
div.colwrap div.left, div.colwrap div.right {
  position: absolute;
  top: 0;
  bottom: 0;
  padding: 70px 35px 70px 70px;
}
div.colwrap div.left {
  right: 50%;
  left: 0;
}
div.colwrap div.right {
  left: 50%;
  right: 0;
}

---
# Naive Bayes Classification

---
# Probabilistic Classification

Given:

- A universe $\mathbb{X}$ from which our examples can come, e.g., English documents with some docabulary $V$.
    - Examples are represented in this space.
    - A fixed set of labels $y\in\mathbb{C}$
        - The classes are human-defined for the needs of the application (e.g., spam vs. ham)/
- We learn a classifier $\gamma$ that maps documents to class probabilities:

$$
\gamma : (x,y) \rightarrow [0,1]
$$

such that $\sum_{y} \gamma(x,y)=1$.

---
# Generative vs. Discriminative Models

**Generative**

Model joint probability $p(x,y)$ including the data $x$.

Naive Bayes

- Use Bayes's Rule to reverse conditioning: $p(x|y)\rightarrow p(y|x)$.

- Naive because it ignores joint probabilities within the data distribution.

**Discriminative**

- Model only conditional probability $p(y|x), excluding data $x$.
Logistic Regression
    - Uses logistic function to get probability
    - Regression: combines a weights vector with observations to get answer
    - General way of generating probability distributions
--- 
# Naive Bayes Motivating Example

---
# A Classification Problem.
- Suppose we have two coins $C_1$ and $C_2$.
- Now suppose I pull a coin out of my pocket, flip it a bunh of times, an drecord the outcomes.
```
C1: 0 1 1 1 1
C1: 1 1 0
C2: 1 0 0 0 0 0 0 1
C1: 0 1
C1: 1 1 0 1 1 1
C2: 0 0 1 1 0 1
C2: 1 0 0 0
```
---
# A Classification Problem
- Suppose we have two coins $C_1$ and $C_2$.
- Now suppose I pull a coin out of my pocket, flip it a bunh of times, an drecord the outcomes.
```
C1: 0 1 1 1 1
C1: 1 1 0
C2: 1 0 0 0 0 0 0 1
C1: 0 1
C1: 1 1 0 1 1 1
C2: 0 0 1 1 0 1
C2: 1 0 0 0
```
- Now suppose I'm given a new sequence, `0 0 1`.  Which coin is it from?

---
# A Classification Problem
This problem has particular challenges:
    - different numbers of each observations
- But there is some structure:
    - Easy to get $P(C_1), P(C_2)$.
    - Easy to get $P(X_i=1|C_1)$ and $P(X_i=1|C_2)$.
    - By conditional independence,
    $P(X=010 | C_1) = P(X_1=0|C_1)P(X_2=1|C_1)P(X_3=0|C_1$.)
    - Can we ue these to get $P(C_1|X=001)$?


---

# A Classification Problem
This problem has particular challenges:
    - different numbers of each observations
- But there is some structure:
    - Easy to get $P(C_1)=4/7, P(C_2=3/7)$.
    - Easy to get $P(X_i=1|C_1)=12/16$ and $P(X_i=1|C_2)=6/18$.
    - By conditional independence,
    $P(X=010 | C_1) = P(X_1=0|C_1)P(X_2=1|C_1)P(X_3=0|C_1$.)
    - Can we ue these to get $P(C_1|X=001)$?

---

# A Classification Problem
    
Summary: have $P(\text{data} | \text{class}$, want $P(\text{class} | \text{data})$
- Solution Bayes's Rule"
$$
	P(\text{class}| \text{data})  = \frac{P(\text{data} | \text{class}) P(\text{class})}{P(\text{data})}
$$
$$
	  = \frac{P(\text{data} | \text{class}) P(\text{class})}{\sum_{\text{class} = 1}^C P(\text{data} | \text{class}) P(\text{class})}
$$