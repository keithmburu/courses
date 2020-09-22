

Computational Linguistics Primer (Draft)

Alvin Grissom II

## Bayes's Rule

Discovered by Thomas Bayes in the 1700s and published in 1763, it's difficult to overstate the impact this theorem has had on perhaps every scientific field.

Suppose we have two events, $A, B$, where $P(B)\neq 0$.  We want to estimate $P(A\vert B)$, that is, the probability that $B$ *generates* $A$, or the probability that we get $A$ if we take $B$ for granted.  We saw earlier that, with sequential data such as words, if we have access the counts of $A$ and $B$, we can use the relative frequency of the subsequences to calculate, for instance, $P(\text{science}\vert\text{computer})$. But what if we want to calculate $P(A\vert B$) and but we only have access to $P(B\vert A)$?

Bayes's Theorem states
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)}.
$$
The goal of this formula, as stated, is to estimate the probability of $A$ if we take $B$ for granted.

First, let's focus on the numerator.  $P(A)$ here is called the **prior**.  Prior to knowing anything about $B$, what is our estimate of $P(A)$?  A data-based method of estimating $P(\text{computer})$ is simply counting and dividing, as earlier. Now, taking $A$ for granted, what is the probability of $B$?  It's $P(B\vert A)$, which we can estimate by using relative frequency.  The numerator, then, is the proportion of time $A$ occurs times the proportion of $that$ time that we get $A$. Put another way, we can say that there is a proportion of the probability space in which $A$ occurs, $P(A)$, and $P(B\vert A)$ is the proportion of $P(A)$'s space in which we also get B; so, $P(A)P(B\vert A)$ gives us the the probability of both events co-occurring: first, that we'll get $A$, and then, having gotten $A$, we'll get $B$.  Getting $A$ is one event.  Getting $B$ with $A$ is another.  We can interpret $P(A)$, the prior, as our **degree of belief** that we'll get $A$, absent any other knowledge; we can interpret $P(B\vert A)$ as our degree of belief that we'll see $B$, having seen $A$ already.  

How does the denominator fit in here?  Well, the denominator must represent all possible outcomes. The fact that $P(B)$ is in the denominator tells us is that our final probability -- our proportion -- is *out of*  the proportion of our total probability space occupied by already getting $B$.  That is, this equation is assuming that we've already seen a $B$ event, and the division tells us: *of the space in which we've already seen a $B$*, how likely is it to generate an $A$?  This is exactly what the left side of the equation says. The insight of this equation allows us to calculate $P(A\vert B)$ even when we don't have access to direct data telling us $P(A\vert B)$, by using $P(B\vert A)$ instead, and using simple multiplication and division to calculate $P(A\vert B)$.

Let's look at it from another angle.  The difference between $P(A\cap B)$ and $P(A\vert B)$ is that in the latter case, we're assuming that we know $B$ already; it's given. When calculating $P(A\cap B)$, we're not making any such assumptions. This is why we need to multiply by a prior to account for the occurrence of $B$ when calculating a conditional probability $P(A\vert B)$.  Another way to look at the numerator is that
$$
P(B\vert A)P(A) = P(A\cap B).
$$
Thus,
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)} = \frac{P(A\cap B)}{P(B)}.
$$
And since we know that $P(A\cap B) = P(B\cap A)$,
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)} = \frac{P(A\cap B)}{P(B)} = \frac{P(B\cap A)}{P(B)} = \frac{P(A\vert B)\cancel{P(B)}}{\cancel{P(B)}}
$$


You may be wondering what will happen if we divide by $P(A)$ instead of $P(B)$ in the above formula.  Let's see.
$$
\frac{P(A\vert B){P(B)}}{{P(A)}}
$$
The value of the numerator is the same; we're just going about calculating it differently, by assuming $B$ and accounting for its probability.  Now that the numerator is $P(A)$, we know that we're in $A$'s space, so we must be assuming $A$ has occurred.  Indeed, this is the canonical Bayesian formula for $P(B\vert A)$.  We've just swapped the $A$'s and the $B$'s.

Often, we may not have direct access to $P(B)$ or $P(A)$; we may instead have counts of B.  Instead, we can expand it.  In this case, we only have two possible events, $A$ and $B$.  Thus,
$$
P(A\vert B) = \frac{P(B\vert A)P(A)}{P(B)}	

= \frac{P(B\vert A)P(A)}{P(B\vert A) + P(B\vert \lnot A)}.
$$
We've discussed the prior, $P(A)$.  The conditional probability in the numerator, $P(B\vert A)$, is called the **likelihood**, while the denominator is . the **normalizing constant.**  Normalizing in this context means that we make all of the values sum to 1.   In the previous formula, we're only considering two possible conditions for $B$ -- either with or without $A$.  But there may be more possibilities.