Computational Linguistics Primer (Draft)

Alvin Grissom II

# Word Embeddings

## Distributional Semantics

One method of defining the meaning of words is through logical form, such as first-order logic and lambda calculus.  This is formal semantics or logical semantics.  Another approach is to define words in terms of their relationships to other words.  The latter approach is central to the notional of **distributional semantics**, which follows from the **distributional hypothesis**, which is that words with similar meanings tend t occur in similar contexts.  This was articulated by scientists as early as the 1940s.

## Co-occurrence Matrix

Perhaps the simplest way of representing this is with a **co-occurrence matrix**. Given a vocabulary size of $|V|$, we build a $|V|\times|V|$ matrix, where the rows and the columns represent the each word in the vocabulary (i.e., the rows and the columns represent the same words).  We can examine a corpus and count the number of times each word occurs in close proximity to every other word, incrementing the count in each cell each time we observe a co-occurrence.  The result is our co-occurrence matrix.  What counts as "close proximity" is something that must be decided beforehand.  We might define it as window of +/- four words, or we might define it as an entire document.

In the following co-occurrence matrix, we see that words like cat and dog tend to occur together.  Such a matrix is symmetric.  Every word, then, has a corresponding row and column vector.



|           | cat  | dog  | car  | boat | river |
| --------- | ---- | ---- | ---- | ---- | ----- |
| **cat**   | 1    | 10   | 1    | 0    | 0     |
| **dog**   | 10   | 1    | 1    | 1    | 0     |
| **car**   | 1    | 1    | 1    | 4    | 0     |
| **boat**  | 0    | 1    | 4    | 1    | 9     |
| **river** | 0    | 0    | 0    | 9    | 1     |

The algorithm looks something like this:

```python
function build_cooccurrence_matrix(corpus):
    1. Build vocabulary, map each word to an index
	1. Set every element in |V| x |V| matrix to 0.
    2. for each span in corpus:
	      for each word1 in span:
              for each word2 in span:
                   matrix[word1][word2] += 1        
```



Given this matrix, we can use **cosine similarity** to determine how similar two vectors are
$$
\text{cos}(\mathbf{u},\mathbf{v})=\frac{\mathbf{u}\cdot\mathbf{v}}{|\mathbf{u}||\mathbf{v}|},
$$
which is just the dot product of the two vectors normalized by their length.  The dot product serves as a similarity metric, and the denominator compensates for the length of the vectors, ensuring two vectors aren't considered similar just because they're longer.  Given two arbitrary words from our co-occurrence matrix, we can take the dot product of the row for word $\mathbf{u}$ and the column for row $\mathbf{v}$ -- or vice versa, since the matrix is symmetric -- and calculate the cosine similarity to determine how similar the terms are.  These matrices are typically extremely **sparse**, containing mostly 0 entries, and obviously quite large.

Co-occurrence matrices were also the basis of other techniques, such as latent semantic analysis (LSA), also called latent semantic indexing (LSI).  In LSA's co-occurrence matrix, however, the rows are words and the columns are documents.  LSA uses a linear algebra technique known as singular value decomposition (SVD) to reduce the size of the matrix while maintaining the similarity information.  There is a probabilistic version known as probabilistic LSA (PLSA, PLSI) based on Bayesian statistics, which is a precursor of topic models.  

## word2Vec



Rather than compute cosine similarity directly on sparse vectors, the modern alternative is to use smaller, **dense** vectors, known as **word embeddings**.  The first version of this is known as **word2vec**.  Word embeddings allow us to capture similarity along a number of different dimensions and often capture subtleties in association that a co-occurrence matrix does not.  Word embeddings are often used as one component of neural network NLP models, because they capture comparatively rich representations in text when compared to the alternatives.



The word2vec paper introduced two different ways of building these dense matrices, the skip-gram methods and the continuous bag or words (CBOW).  



The word2vec CBOW algorithm doesn't create a co-occurrence matrix; instead, it trains a logistic regression to predict whether a given word will occur within a window of text given the words surrounding it.   The problem is reframed as one of binary classification:  Is the target word the middle word or not? In our sentence above, we might try to predict the word "fox" given its surrounding two words on either side (quick, brown, jumped, over).  That is,
$$
P(\text{fox}|\text{quick}), P(\text{fox|brown}), P(\text{fox}|\text{jumped}), P(\text{fox}|\text{over})
$$
But since this isn't a binary classification problem, we formulate it as:
$$
P(\text{true}|t=\text{fox}; c=\text{quick}), P(\text{true}|t=\text{fox}; \text{c=brown})\text{, etc.}
$$
Now we only have two options per classification.



Like any binary logistic regression, we find this probability but shunting a dot product through the logistic function.

The classifier can't learn anything without negative examples, however; it'll just learn to predict the positive class for everything. So we use **negative sampling**.  Negative sampling is a general machine learning technique whereby we choose a random incorrect answer as a negative example, forcing the classifier to learn how to differentiate between correct answers and everything else.  Alternatively, we can model this as a multi-class classification problem with the softmax activation function instead of the logistic function.



I mentioned a dot product above, but I didn't specify what we're taking the dot product of.  We're taking the dot product of the context word $c$ and the target word $t$.  These exist in two different matrices. Let's call them $C$ and $T$ for our context embeddings and target/word embeddings, respectively.  In the beginning, they are initialized randomly.  But with each prediction, we modify these vectors to increase the probability that the classifier will make the correct prediction next time.  We want to maximize $P(\text{true}|t, c)$ for all of the positive examples and maximize $P(\text{false}|t,c)$ for all of the negative examples, making our objective function
$$
\sum_{+\in D} \log P(\text{true}|t,c) + \sum_{-\in D} \log P(\text{false}|t, c)
$$
Recall that we can do this by ensuring that $t$ and $c$ have high weights along the same dimensions.  Over time, we make the entries in $C$ and $T$ more similar if they occur in similar contexts (from the positive examples) and less similar if they don't (from the negative examples).  We can do this with stochastic gradient descent, just as with any other logistic regression.  We modify the weights in proportion to the gradient, scaled by the step size.  The result is two embedding matrices in which words that occur in similar contexts have dense entries which were learned by seeing how predictive they were of surrounding words.  We can either keep the term embeddings and discard the context matrix or merge them.



The other major approach of word2vec is the **skip-gram** algorithm . A **skip-gram** gram is like an n-gram, but there may be words in between.  For example, given a sentence "the quick brown fox chased after the lazy dog," we could take skip-grams of every individual word within a window of every 3 words on either side of the target word.  The continuous skip gram word2vec algorithm is largely the same, but instead of learning to predict the target word, the middle term is used to predict the surrounding words.



When training word2vec word embeddings, there are a number of hyperparameters, most notably the context window size, the particular algorithm (CBOW or skip-gram), and the number of dimensions.  The dimensionality of the vector space constrains what can be represented, but a dimensionality too large may be too sparse.

## Applications

Word embeddings are interesting both intrinsically and because of their huge benefits on downstream tasks when used as pat of other models, making them a standard part of neural NLP models.  On their own, they can be used to investigate term similarity and perform some version of analogical reasoning with vector arithmetic. 