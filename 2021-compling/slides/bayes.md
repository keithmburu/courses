---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2020\nAlvin Grissom II, Haverford College

---
# Bayes's Rule

---
# Bayes's Rule

- Discovered by Thomas Bayes in 1700's
- Published in 1763
- Extremely important in science

---
# Bayes's Rule
- Suppose we have two events, $A, B$, where $P(B)\neq 0$. 
- We want to estimate $P(A\vert B)$
    -  the probability that $B$ *generates* $A$
    - the probability that we get $A$ if we take $B$ for granted

---
# Bayes's Rule
- Recall: we can use relative frequency to calculate $P(\text{science}\vert\text{computer})$
- But what if we want to calculate $P(A\vert B$) but we only have access to $P(B\vert A)$?

---
# Bayes's Rule
- Bayes's Theorem states:
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)}.
$$
- Estimate probability of $A$ if we take $B$ for granted.

---
# Baye's Rule
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)}.
$$
- $P(A)$ is called the *prior*.
---
