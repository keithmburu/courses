# FAQ

What is argmax?

Consider the following expression.


$$
\DeclareMathOperator*{\argmax}{argmax}
\argmax_{x \in \mathcal{X}} f(x)
$$
This function returns the $x$ that maximizes $f(x)$.  In machine learning, often we see this in terms of probabilities and classes.

For example, given a set of classes $C$,  the class $c$ that returns the highest probability under Bayes's Rule is:
$$
\DeclareMathOperator*{\argmax}{argmax}
\argmax_{c \in \mathcal{C}} \frac{P(c)P(\textbf{x}\vert c)}{P(\textbf{x})}.
$$
The $\text{argmax}$ returns the *class* that maximizes the probability. The $\text{max}$ returns the maximum probability itself.

