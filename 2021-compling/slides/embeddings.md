---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2021\nAlvin Grissom II, Haverford College

---
# Distributional Semantics

---
# Distributional Semantics
- Semantics - study of meaning
- In linguistics, mostly associated with logical semantics.
    - Assign a logical formula to describe truth conditions of a sentence.
    - In first order logic, "The cat is faster than the dog" becomes:
     $\exists c,d \mid \text{cat}(\text{c}) \land \text{dog}(d) \land \text{speed}(c) > \text{speed}(d)$.

---
# Distributional Semantics
Another approach
- **Distributional semantics**: Define words in terms of their relationships to other words.
- Based on the **distributional hyptothesis** .
    - Words with similar meanings tend to occur in similar contexts.
    - Articulate by scientists as early as the 1940s.

---
# Distributional Sematnics
- Simple Approach: co-occurrence matrix.
- Given vocabulary $V$, build $|V|\times |V|$ matrix.
    - Rows and columns represent each word in vocabulary.
    - Elements in matrix represent number of times a word occurs close to another word.
    - Fill matrix by scanning corpus or corpora.

---
# Distributional Semantics
Co-occurrence Matrix
|       | cat | dog | car | boat | river |
|-------|-----|-----|-----|------|-------|
| cat   | 1   | 10  | 1   | 0    | 0     |
| dog   | 10  | 1   | 1   | 1    | 0     |
| car   | 1   | 1   | 1   | 0    | 0     |
| boat  | 0   | 1   | 0   | 1    | 9     |
| river | 0   | 0   | 0   | 9    | 1     |

---
# Distributional Semantics

|       | cat | dog | car | boat | river |
|-------|-----|-----|-----|------|-------|
| cat   | 1   | 10  | 1   | 0    | 0     |
| dog   | 10  | 1   | 1   | 1    | 0     |
| car   | 1   | 1   | 1   | 0    | 0     |
| boat  | 0   | 1   | 0   | 1    | 9     |
| river | 0   | 0   | 0   | 9    | 1     |
- Symmetric, **sparse**
- Every word has a row and column vector

---
# Distributional Semantics
Co-occurrence Matrix Algorithm
```python
function build_cooccurrence_matrix(corpus):
    1. Build vocabulary, map each word to an index
	1. Set every element in |V| x |V| matrix to 0.
    2. for each span in corpus:
	      for each word1 in span:
              for each word2 in span:
                   matrix[word1][word2] += 1
                   matrix[word2][word1] += 1    
```

---

# Distributional Semantics
Co-occurrence Matrix: Computing Similarity

- Given a co-occurrence matrix, we use **cosine similarity** to determine how similar two word vectors are.
$$
\text{cos}(\mathbf{u},\mathbf{v})=\frac{\mathbf{u}\cdot\mathbf{v}}{|\mathbf{u}||\mathbf{v}|},
$$
- Dot product nomralized by the size of the word vectors.
    - Intuition: Numerator is a similarity metric, denominator prevents vectors from being considered similar based on length alone. 

 ---
# Distributional Semantics

- Co-occurrence matrices are basis of other techniques
    - e.g., latent semantic analysis (LSA)
    - In LSA, rows and columns are documents instead of words
        - Uses singular value decomposition to reduce size of matrix while maintaining similarity information
        - Probabilistic version (PLSA).

---

# Distributional Semantics
Word Embeddings
- Rather than computer cosine similarity on huge, sparse vectors, modern alternative is to use smaller, **dense** vectors.
- Called **word embeddings**.
- First version known as **Word2vec**.

---

# Distributional Semantics
Word2Vec
- Captures similarity across various dimensions
- Captures subtleties that co-occurrence matrices cannot.
- Often used as component of other NLP models.

---

# Distributional Semantics
Word2Vec
- Original Word2Vec paper introduced two ways of building models.
    - Skip-gram
    - Continuous bag of words (CBOW)

---

# Distributional Semantics
Word2Vec CBOW Model
- CBOW algorithm doesn't create a co-occurrence matrix
- Trains logistic regression to predict whether a word will occur given surrounding text
    - Problem framed as binary classification
    -Is target word the middle word or not (yes/no)?

---
# Distributional Semantics
Word2Vec CBOW Model
- Consider "the quick brown fox jumped over the lazy dog"
- Algorithm: Is target word the middle word or not?
- We might try to predict "fox given  two surrounding words on either side, e.g., (quick, brown, jumped, over).

---
# Distributional Semantics
Word2Vec CBOW Model
- Predict "fox" given two words on either side.
$$
P(\text{fox}|\text{quick}), P(\text{fox|brown}), P(\text{fox}|\text{jumped}), P(\text{fox}|\text{over})
$$
- But this isn't binary classification!

---
# Distributional Semantics
Word2Vec CBOW Model
- Predict "fox" given two words on either side.
$$
P(\text{fox}|\text{quick}), P(\text{fox|brown}), P(\text{fox}|\text{jumped}), P(\text{fox}|\text{over})
$$
- But this isn't binary classification
- Instead, formulate as
$$
P(\text{true}|t=\text{fox}; c=\text{quick}), P(\text{true}|t=\text{fox}; \text{c=brown})\text{, etc.}
$$

---
# Distributional Semantics
Word2Vec CBOW Model
- Like any logistic regression, we use a dot product and send it through a logistic function $\sigma(z)$ to calculate the probability of 1 (true).

- But there's a problem.


---
# Distributional Semantics
Word2Vec CBOW Model
- Like any logistic regression, we use a dot product and send it through a logistic function $\sigma(z)$ to calculate the probability of 1 (true).

- But there's a problem.
- Classifier needs negative examples to learn anything (why?)

---
# Distributional Semantics
Word2Vec CBOW Model
- Use **negative sampling.**
    - General ML technique whereby we choose a random incorrect example as a negative example.
    - So, we might replace "fox" with some other random word that doesn't occur in this context.

---
# Distributional Semantics
Word2Vec CBOW Model
- Of what are we taking the dot product?
    - Context word $c$ and target word $t$.
    - The words $c$ and $t$ exist in two different matrices $T$ and $C$.
    - Consider $C$ **context embeddings** and $T$ **word embeddings**.
    - Initialized randomly.
    - With each prediction, we modify thse vectors to increase the probability the classifier will make correct prediction next time.

---
# Distributional Semantics
Word2Vec CBOW Model
- We're maximizing $P(\text{true} \vert t, c)$ on all of the positive  examples and $P(\text{false}|t,c)$ on all of the negative examples.
- Thus, our objective function is slightly different than the one of vanilla logistic regression.
$$
\sum_{+\in D} \log P(\text{true}|t,c) + \sum_{-\in D} \log P(\text{false}|t, c)
$$

---
# Distributional Semantics
Word2Vec CBOW Model

- We can maximize our objective function by ensuring that $t$ and $c$ have high weights along the same dimensions.
- Over time, entries in $C$ an $T$ become more similar if the words occur in similar contexts (for positive examples) and less similar if they don't (for negative examples).
- Optimize with stochasitc gradient descent.

---
# Distributional Semantics
Word2Vec CBOW Model

- Result: two dense embedding matrices.
    - Words occuring in similar contexts have weights learned by determining how predictive they were of surrounding words.
    -We keep $C$ and either merge it with $T$ or discard $T$.

---
  
# Distributional Semantics
Word2Vec Skip-gram Model

- A **skip-gram** is like an $n$-gram, but it allows gaps between the sequences.
- Consider "the quick brown fox chased the lazy dog".
- We could take skip grams within a window of every three ords on either side of the target word.
- Continuous skip-gram model largely the same but flips prediction direction.
    - Instead of predicting the middle word; use the middle word to predict surrounding words.


---
  
# Distributional Semantics
Applications
- Interesting intrinsically.
    - Which words are simialr in a given corpus? Time period? Span of text?
    - What associations do certain authors make?
    - Vector arithmetic/analogies:
        - MApplicationsan:Woman::King:Queen
- Practical Applicaitons
    - Used as semantic information in deep learning models.
    - Replaces one-hot-vector approach.
