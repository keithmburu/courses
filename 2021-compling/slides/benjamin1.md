---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2021\nAlvin Grissom II, Haverford College

---
# Heuristic Multiclass Classification

---
- Logistic regression, linear perceptron, etc. only support two classes.  Usually we have two classes, e.g., $Y=\{1,0\}$; now we have $Y=\{1, 2, \ldots, k\}$.

- Several tricks for generalizing to multiple classes
    - Heuristic approaches
    - Multinomial logistic regression (MaxEnt)

---
# Hrutistic Multiclass Classification

- Heuristic: one-vs-all
- To classify $k=|Y|$ classes, train $k-1$ classifiers

---
# Heuristic Multiclass Classification

- Heuristic: one-vs-all
- Training: To classify $k=|Y|$ classes, train $k-1$ classifiers
    - For each classifier, the positive class is one of the $k$ classes
    - The negative class is *everything else*

---
# Heuristic Multiclass Classification

- Heuristic: one-vs-all
- Training: For $k=|Y|$ classes, train $k-1$ classifiers
    - For each classifier, the positive class is one of the $k$ classes
    - The negative class is *everything else*


---
# Heuristic Multiclass Classification

- Heuristic: one-vs-all
- Training: For $k=|Y|$ classes, train $k-1$ classifiers
    - For each classifier, the positive class is one of the $k$ classes
    - The negative class is *everything else*
- Train $k$ classifiers $f_c(\mathbf{x})$, where $c$ is one class from the set of classes $Y$ which returns $P(c=1)$.
    - One-vs-all classifier
    - $f(\mathbf{x}) = \underset{c}{\operatorname{argmax}} f_c(\mathbf{x})$ predicts the class $c$ that maximizes $P(c=1)$.

---
# Heuristic Multiclass Classification

- Heuristic: one-vs-all
- At test time, use each classifier to make a prediction of its positive class (one of the $k$ classes)

---
# Heuristic Multiclass Classification
- Heuristic: one-vs-all
- At test time, use each classifier to make a prediction of its positive class (one of the $k$ classes)
- Rank each classifier's prediction by confidence (probability, for logistic regression)
- Predict the class with the highest score.

---
# Heuristic Multiclass Classification
- Heuristic: one-vs-all
- Advantages
    - Easy to impelment and understand
    - Works well in practice (Rifkin et al., 2004)
- Disadvantages
    - Doesn't produce probability distribution over classes
    - Requires training $k$ and testing $k$ separate classifiers
- Heuristic alternatives: error correcting codes, one-vs-one
    - Recall Word2Vec trick: reframe problem as binary


---
# Heuristic Multiclass Classification
Example:

Recall: word embedding objective function:
$$
\sum_{+\in D} \log P(\text{true}|t,c) + \sum_{-\in D} \log P(\text{false}|t, c)
$$

- Original problem: Which missing word $t$ belongs in context window $c$? (multiclass)
- Reframed problem: Does word $t$ belong with context $c$? (binary)

---
# Heuristic Multiclass Classification
- More principled approach: Maximum Entropy (MaxEnt)
    - a.k.a. multimonial logistic regression, multiclass logistic regression, softmax regression, multinomial logit, conditional maximum entropy model


---
# Maximum Entropy Classification
- More principled approach: Maximum Entropy (MaxEnt)
    - a.k.a. multimonial logistic regression, multiclass logistic regression, softmax regression, multinomial logit, conditional maximum entropy model

---
# Maximum Entropy Classification

Note on the name:
- Principle of Maximum Entropy (Jaynes, 1957): The probability distribution which best models the data is the one with the largest entropy given the data.
    - Occam's Razor: We want the simplest model possible that gives us the correct answers.


---
# Maximum Entropy Classification

Note on the name:
- Principle of Maximum Entropy (Jaynes, 1957): The probability distribution which best models the data is the one with the largest entropy given the data.
    - Occam's Razor: We want the simplest model possible that gives us the correct answers.


---
# Maximum Entropy Classification

- We now have $k$ classes $Y=\{1, 2, \ldots, k\}$ instead of $\{1,0\}$ (logisitic regression) $\{1,-1\}$ (perceptron).
- Estimate $P(y=k\vert \mathbf{x})$ *directly*.

---
# Maximum Entropy Classification

- We now have $k$ classes $Y=\{1, 2, \ldots, k\}$ instead of $\{1,0\}$ (logisitic regression) $\{1,-1\}$ (perceptron).
- Estimate $P(y=k\vert \mathbf{x})$ *directly*.
- Remember logistic function:
 $$
 \sigma(t) = \frac{e^t}{1 + e^t} =\frac{1}{1+e^{-t}}
 $$
 
---
# Maximum Entropy Classification
 $$
 \sigma(t) = \frac{e^t}{1 + e^t} =\frac{1}{1+e^{-t}}
 $$

  - Recall: For binary problems, gives us 
  $$												
	\begin{align*}
    P(y=1|\mathbf{w};\mathbf{x}) &= \sigma(\mathbf{w}\cdot\mathbf{x}+b) \\&= \frac{1}{1+\text{exp}[-(\mathbf{w}\cdot\mathbf{x}+b)]}\\&= \frac{1}{1+\text{exp}[-(\sum_{i=1}^n w_i x_i + b)]}
    \end{align*}
	$$
---
# Maximum Entropy Classification

- For multiple classes use **softmax**.
    - Multi-class generalization of logistic function.
    - **Vector** that generates probabilities for every class.
    - Instead of one set of weights $\mathbf{w}$, we have $k$ sets of weights $\mathbf{w_i}$ for each class $y_i$.
				
				
---
# Maximum Entropy Classification

- For multiple classes use **softmax**.
    - Multi-class generalization of logistic function.
    - **Vector** that generates probabilities for every class.
    - Instead of one set of weights $\mathbf{w}$, we have $k$ sets of weights $\mathbf{w_i}$ for each class $y_i$.
	$$
    \text{softmax}(z_i)=\frac{e^{z_i}}{\sum_{i=j}^k e^{z_i}},
    $$			
				
---
# Maximum Entropy Classification
$$
  \text{softmax}(z_i)=\frac{e^{z_i}}{\sum_{i=j}^k e^{z_i}},
    $$			 
  Compare to:
  $$
 \sigma(z) = \frac{e^z}{1 + e^z} 
  $$
- Both map to (0,1).
- Denominator of $\text{softmax}$ normalizes to probability space (0,1).

---
# Maximum Entropy Classification

Let $\mathbf{w}=[\mathbf{w_1}, \mathbf{w_2}, \ldots, \mathbf{w_k}]$.


$$
  \text{softmax}(\mathbf{w})=\frac{e^{z_i}}{\sum_{i=j}^k e^{z_i}},
$$

$$
=
 \begin{bmatrix} 
 P(y=1|\mathbf{x};\mathbf{w}) \\
 P(y=2|\mathbf{x};\mathbf{w}) \\
 \cdots \\
 P(y=k|\mathbf{x};\mathbf{w})\\
\end{bmatrix}
$$
$$
= \frac{1}{{\sum\limits_{i=1}^k}\exp[\mathbf{w_i}\cdot\mathbf{x}+b_i]}

 \begin{bmatrix} 
 \exp[\mathbf{w_1} \cdot \mathbf{x}] \\
 \exp[\mathbf{w_2} \cdot \mathbf{x}] \\
 \cdots \\
 \exp[\mathbf{w_k} \cdot \mathbf{x} ]\\
\end{bmatrix}
$$

