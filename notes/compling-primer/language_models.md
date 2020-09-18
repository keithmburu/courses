Computational Linguistics Primer (Draft)

Alvin Grissom II

# Language Modeling and Probability

Symbols used in this chapter:

$\land$  Logical "and"

$\lor$  Logical "or"

$\cap$  Set intersection

$\cup$ Set union

$\sum$  Summation

$\in$ "element of" a set

$\vert$ conditional probability, "given"

A **language model** is a statistical model that assigns probability to a sequence of tokens.  In English, the tokens usually words, but may be characters or sub-word units.  More formally, a language model is a probability distribution over sequences of tokens. 

Traditionally, until recursive neural networks (RNNs) rose to popularity in the mid-to-late 2010's, language models were almost exclusively based on Bayesian statistics.  While neural network models are popular and easy to implement with modern libraries, Bayesian models are still in use, and studying them is foundationally useful for building intuition and for understanding more complex models.

### Probability

A **probability** may have a number of epistemic interpretations, but for now, assuming that we have access to some **data** of previous events, we will say that the probability of an **event** $e$, $Pr(e)$, can be succinctly estimated as
$$
\frac{count(e)}{count(all)}.
$$
What I mean is that there is a *set* of  **all** possible outcomes and some specific outcome s $e$ with which we're concerned at the moment. We would like to know how likely it is that $e$ will occur, assuming random chance.  If we put all of the possible events in a bag, shake it in order to randomize the events, and choose one at random (called **sampling**), how likely are we to get $e$?

If we flip a fair coin, we have "all" of two possibilities: heads or tails.  Only one of them will occur.  Thus, the probability of heads, $Pr(H)$, is 
$$
\frac{count(H)}{count(H) + count(T)} .
$$
In this case, we only have one heads and one tails possibility, but we can imagine that a labeled die might have more.  First, let's consider a fair, six-sided die with sides labeled 1 through 6.  The probability of any given side $s$ must be
$$
\frac{count(s)}{count(1) + count(2) + count(3) + \dots + count(6)}\\
= \frac{1}{1 + 1 + 1 + 1 + 1 + 1} = \frac{1}{6}.
$$
where $s \in \{1, 2, 3, 4, 5, 6\}$. 

We use the braces (not to be confused with brackets) here to refer to a **set**, which is an unordered collection of distinct objects -- in this case, numbers.  The $\in$, which, despite what you may have heard, is not an epsilon ($\epsilon$), means "is an element of," or simply "in."

Now, let's consider a more complicated six-sided die: each of the six sides has one number from $\{1, 2, 3\}$, and each number appears on the die twice.  Our equation thus changes.  Suppose we'd like to know how likely we are to see a "1" after rolling the die.  We have
$$
P(e = 1) = \frac{count(1)}{count(1) + count(2) + count(3)} \\
= \frac{2}{2 + 2 + 2} = \frac{1}{3}.
$$


There are only three possible labels on the die, and they all exist on two faces of the die, so they all have equal probabilities, assuming the die is fair. The set of all probabilities for $e$--that is, how the probabilities are distributed amongst the variables--is called a **probability distribution**.  If it isn't obvious, convince yourself that the sum of all probabilities in a distribution must sum to 1.  Each die roll or coin flip is an **independent event** because one roll does not depend on the previous one, implying that the probability of one die roll does not depend on the probability of previous events.  Therefore, given fair coin flips, the probability of five successive "heads" is the same as the probability of successive "tails," which is equal probability of HHHHT, which is equal to TTTHT, which is equal to THTHT, and so on.  The same is true for our die.  This is often difficult for those new to probability to accept, as the intuitive heuristics of a mind untrained in probability often lead one astray.

Probabilities of the kind we're interested in must obey certain **axioms**, known as the Kolmogorov axioms.  Colloquially, they are the following:

1. Probabilities of events must be no less than 0. $P(e)\geq 0$ for all $e$.

2. The sum of all probabilities in a distribution must sum to 1.  That is, $P(e_1) + P(e_2) + \ldots + P(e_n) = 1 $.  Or, more succinctly, 
   $$
   \sum_{e\in E}P(e) = 1.
   $$

3. The probability that one or both of two independent events $e_1$ and $e_2$ will occur is the sum of their respective probabilities.  
   $$
   P(e_1\;\textrm{or}\; e_2) = P(e_1 \cup e_2) = P(e_1) + P(e_2)\; \textrm{when}\;e_1 \cap e_2 = \empty
   $$

It can be helpful to visualize these probabilities as diagrams to understand .

![probability disjunction](https://raw.githubusercontent.com/acgrissom/courses/master/2020-compling/slides/images/prob_disjunction.png)

The *disjunction* ("or", $\lor$) of two probabilities $P(e_1)$,  $P(e_2)$ includes all of the **probability space** of $e_1$ and $e_2$--their union.  Imagine that the Venn diagram is a poll in which you blindly throw a coin.  The coin will land at a random location.  The probability that it lands in *either* the space of $e_1$ *or* the space of $e_2$ is the sum of all both of their spaces.  

 The *conjunction* ("and", $\land$) of two probabilities includes only the section of the probability space in which the events $e_1$ and $e_2$ intersect.  While not axiomatic, it is sufficiently fundamental to mention here:

4. The probability that two independent events $e_1$ and $e_2$ *both* occur is given by their product.
   $$
   P(e_1\;\textrm{and}\; e_2) = P(e_1 \cap e_2) = P(e_1)P(e_2)\; \textrm{when}\;e_1 \cap e_2 = \empty
   $$
   

![Probability Conjunction](https://raw.githubusercontent.com/acgrissom/courses/master/2020-compling/slides/images/joint.png)

While the notion of adding probabilities to calculate a disjunction of independent events may be intuitive, the intuition behind multiplying probabilities may be less clear.  We can think of every probability as a *scaling factor*.  Indeed, multiplication itself can be conceptualized in this way.  When we calculate $5 \times 4$, we are saying that we have five fours or four fives.  The product $10 \times 0.5$ is 0.5 tens.  All of our probabilities are between 0 and 1, and all of the probabilities in a probability space must sum to 1.  A probability can be thought of as the fraction of the probability space occupied by an event.  Put another way, it's the fraction of time that an event will occur. 

Suppose you have two events, $e_1$ and $e_2$ where $P(e_1) = 0.2, P(e_2)=0.3$.  To calculate $P(e_1 \land e_2)$, we need the fraction of the time that $e_1$ occurs and the fraction of *that* time in which $e_2$ occurs.  So, we scale the fraction of time  that $e_2$ occurs by the fraction of time that $e_1$ occurs, i.e., we scale $P(e_2)$ by $P(e_1)$ (or vice versa), giving us $P(e_1)P(e_2)$.  Often, instead of $P(e_1 \land e_2)$, we'll write $P(e_1, e_2)$. The probability of two events occurring together, $P(e_1, e_2)$ is a **joint probability**. 

 It's helpful to notice that the union symbol is just a smooth version of the logical "or" symbol and the intersection symbol is a smooth version of the logical "and" symbol.  The logic of intersection and union extends to more than two events.  The probability of independent events $e_1, e_2, e_3$ all occurring is given by $P(e_1\land e_2 \land e_3) = P(e_1 \cap e_2 \cap e_3) = P(e_1)P(e_2)P(e_3)$.  

## Probability of a Word

Suppose we have some **corpus** -- a collection of text -- of sentences in English, and we'd like to calculate the probability that a the next word $w$ that we see is "cat."  Based on what we've so far learned, a simple way of calculating this is to count the number of times "cat" appears in our text file and divide it by the total number of word tokens in the text.  (We must be careful to distinguish between the number of individual word *tokens* and the number of distinct words, known as *types*).  Let's say that the word "cat" appears 10 times out of a total of 100 words.  Then, according to the division method above, the probability that our next word is "cat" is always given by
$$
P(\textrm{cat}) = \frac{count(\textrm{cat})}{count(\textrm{all words})} = \frac{10}{100} = 0.1
$$
So our probability is 10%.  We could write a program to calculate this probability in a few lines.  First, we would collect statistics on each word, calculate the total token count, and then do the division.

Upon reflection, we might, however, think that this probability is impoverished, and we would be correct.  This model has no sense of order.  We know that, for example, the word "cat" is unlikely to proceed after the word "exacerbate," but our model has no sense of this.  It was the simplest version what is known as a **bag of words** model, so called because the probabilities are calculated as though the words were thrown into a bag and shaken. The probability of a given word is the probability that the word is selected randomly, with no sense of context.

## Conditional Probability and $n$-grams

A **conditional probability** is the probability that one event occurs given that we take another for granted.  We use a vertical bar | to mean "given."  The probability of $e_2$ given $e_1$ is $P(e_2 \mid e_1)$. This is the probability that $e_2$ will occur given that we take for granted that $e_1$ occurs.  <u>If</u> $e_1$ and $e_2$ are independent, then 
$$
P(e_2 | e_1) = P(e_2 \land e_1) = P(e_2 \cap e_1) = P(e_1 \cap e_2) = P(e_1)P(e_2)
$$


But what if they're not independent?  That is, what if assuming $e_1$ changes the probability of $e_2$?  Before we address this, let's examine what it means to **update** our probability estimate.  The bare probability $P(e_2)$ is, as described above, based on a raw count and division. It's a rather impoverished corpus-wide estimate.  But assuming the occurrence of $e_1$ gives us *more information* with which to estimate our probability.  If we've collected statistics (counts) about how likely $e_2$ and $e_1$ are to occur together in a corpus, then we can use these counts to calculate a potentially more accurate probability estimate.

### $n$-grams

In the context of computational linguistics and NLP, the "given" here usually to refers to a *sequence*.  Sentences are sequential: one word follows another. In this view of language, we can view words in a sentence as being *generated* by previous words with some probability.  We know that the probability of "science," $P(\textrm{science})$, is $\frac{count(\textrm{science})}{count(\textrm{all words})}$. But what of the probability of "science" given that the previous word is "computer", $P(\textrm{science}\vert \textrm{computer})$?  This is the probability that the *next* word is "science," given that the current word is "computer," i.e., how likely is the word "science" to be generated from the **context** "computer?"

This is given by 
$$
P(\textrm{science}\vert \textrm{computer}) = \frac{count(\textrm{computer science})}{count(\textrm{computer})}.
$$
This is the fraction of occurrences of "computer science" to the occurrences of just "computer."  This is answering the question, Of the instances of "computer," how many of them are followed by "science?" 

We can approach this another way.   $P(\textrm{science}\vert \textrm{computer}) = \frac{P(\textrm{computer science})}{P(\textrm{computer})}$ gives us the same result.  Let $C$ be the count of a word. In this case,
$$
P(\textrm{science}\vert \textrm{computer}) = \frac{P(\textrm{computer science})}{P(\textrm{computer})} \\
= \frac{\frac{C(\textrm{computer science})}{C(\textrm{all words})}}{\frac{C(\textrm{computer})}{C(\textrm{all words})}} \\
= \frac{C(\textrm{computer science})}{C(\textrm{computer})}
$$
More generally, given words $w_1, w_2$,
$$
P(w_2\vert w_1) = \frac{C(w_1, w_2)}{C(w_1)} = \frac{P(w_1, w_2)}{P(w_1)}
$$
Be sure to distinguish between the probability of "computer science" and the probability of "science" given "computer."  This is called a **bigram** probability, because we're using a sequence of two words in our calculation. 

 One might think, correctly, that our estimates can become more accurate if we consider more context.  For example, $P(\textrm{science} \vert \textrm{the computer})$.  Here, we want the **trigram** probability, $\frac{P(\textrm{the computer science})}{P(\textrm{the computer})}$.  We can continue to 4-grams, 5-grams, etc. There's theoretically no limit to the length context we can use, but there is a serious problem, a fundamental one that appears frequently in computational linguistics.  The longer the sequence, the less likely it is to appear in our data at all.  Furthermore, even if longer sequences appear, they may only appear once, giving us essentially useless estimates. If $C(\textrm{the computer science})$ is 0---or, worse, $C(\textrm{the computer})$ is 0---our calculations aren't useful. 

Fundamentally, the problem we have is one of **data sparsity**.  Even in a corpus of everything ever written, it is still not particularly difficult to produce grammatical sentences that have are absent from the corpus.  Noam Chomsky famously used the example near-nonsense sentence, "Colorless green ideas sleep furiously" to illustrate this point.  Ironically, this has made the sentence quite common in linguistics discourse, but one can achieve the same result with some rudimentary word replacements: "Colorless kumquat ideas sleep furiously," for example.

In any corpus that exists, we can be reasonably confident that the joint probability $P(\textrm{colorless kumquat ideas sleep furiously}) = 0$.  To deal with this, we loosen our definition of probability by making some (linguistically unjustified) **independence assumptions.**  In particular, we assume that a limited context determines the probability of the next word.   Models making such independence assumptions are known as **Markov models** because they have the **Markov property** of using a limited history to determine the probability of a word.  In NLP parlance, these are called **language models**, The term "language model" was once virtually synonymous with such probabilistic $n$-gram models, but more recently the term has been used to describe sequential neural network models, as well, which we will discuss later.

We can employ Markov assumptions to decompose the probability of a string into smaller $n$-gram probabilities.  We'll pad the sentence with a dummy word  $\texttt{<s>}$  at the beginning, so that we can calculate the probability that "colorless" is the first word.  We'll also pad on the right with an $\texttt{</s>}$ to model the end of the sentence.  If we don't include this special "word," our probability distribution will not correctly model sentences sentence length, since there will be no notion of the end of the sentence.

 If we're using a bigram model, $P(\textrm{<s> colorless kumquat ideas sleep furiously</s>}) $ is estimated by $P(\textrm{colorless}\vert \textrm{<s>})P(\textrm{kumquat} \vert \textrm{colorless})P(\textrm{ideas}\vert \textrm{kumquat})P(\textrm{sleep}\vert \textrm{ideas})P(\textrm{furiously} \vert \textrm{sleep})P(\textrm{</s>}\vert\textrm{sleep}).$

In this bigram formulation, we have a series of independent conditional probabilities, which we can calculate individually and multiply together.  The trigram probability would use a history (or context) of two words;  a 4-gram model would use a history of three words; etc.  The language model implementation itself is just a table of counts or probabilities for the $n$-grams we need to look up in order to calculate the probabilities.  

### Smoothing

If we have sufficient data, a larger $n$ for the $n$-gram model will generally produce more accurate probability estimates.  But the larger the $n$, the more likely one of the $n$-gram probabilities will be zero.  If just one term is zero, the entire product is zero.  For this reason, all $n$-gram models used for applications use some kind of smoothing.  **Smoothing**, also called a pseudocounting, is how we deal with 0 probability events.  

Suppose that the word "inexorably" never appears in our data.  In such a case, $P(\textrm{inexorably})=0$ and the probability of any bigram containing the word "inexorably" will also be 0, zeroing out the probability of any phrase containing the word.  The simplest way of dealing with this is to add 1 to the count of any word in your vocabulary.  (This vocabulary must be specified in advance.) So, words that do not appear will have a count of 1.  This is called Laplace smoothing or Add-1 smoothing. This means that we will also artificially inflate the total count of words by the size of the vocabulary, since we're adding an extra count for each word.  Where as the count of a word $w$ in an unsmoothed model is
$$
\frac{C(w)}{C(\textrm{all words})},
$$
the count of the same word in a model with Laplace smoothing is
$$
\frac{C(w) + 1}{C(\textrm{all words}) + |V|}.
$$


This solves the problem zero counts, but it has another problem: this approach tends to allocate too much probability mass to events/words that never appear, inflating the probabilities of extremely unlikely words.  A simple way of somewhat mitigating this is to use some small $\alpha < 1$ instead of 1.
$$
\frac{C(w)+\alpha}{C(\textrm{all words}) + \alpha |V|}
$$
Add-1 smoothing, then, is the special case where $\alpha=1$.

There are more sophisticated smoothing methods, such as **Kneser-Ney smoothing** and **Pitman-Yor** smoothing.  The former performs very well and is often used in practice, while the latter performs slightly better but takes a long time to train.  Both use an effective method of zero-mitigation called **backoff**, which falls back on lower-order  $n$-grams when a full $n$-gram does not occur in the corpus.  A more recent algorithm, called Stupid Backoff, is simpler than both of these methods and converges to their performance on extremely large data.  At least for language modeling benchmarks, simple models such as Stupid Backoff often perform as well as more sophisticated models if enough data is used to train them. 

