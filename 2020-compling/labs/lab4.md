# Lab 4 (5 points)

For this lab, you will use word embeddings in a Python package called `gensim`.  This package is already installed on lab machines, but it is easy to install locally with `pip`.  For this assignment, you may use any text that you prefer.  

```python
import nltk
import gensim
from gensim.models import Word2Vec 
```

You may use any corpus for this assignment, e.g., Project Gutenberg, but you may also use NLTK's built-in corpora.  I'll use Melville's *Moby Dick.*

```python
corpus = nltk.corpus.gutenberg.sents('melville-moby_dick.txt') #read all sentences
```

Now, we can use Gensim to create word2vec embeddings with the default parameters.   Since this is a small corpus, it won't take long.

```python
model = gensim.models.Word2Vec(corpus)
```

We can change these defaults if we like.  Use the following documentation to complete the remainder of this lab: https://radimrehurek.com/gensim/models/keyedvectors.html

1. Choose at least one other text to work with for the following questions.
2. Before continuing, write down a hypothesis of relationships of some words of interest that you expect to see based on the texts you're using, and how you suspect your two (or more) texts will exemplify these differences.  At a minimum, use the cosine similarity function and at least one other function.
3. What did you find? Based on your exploring, does your hypothesis seem valid?  Why or why not?  Do you have a new hypothesis?

Submit a short explanation of what you found and how you approached the problem on Moodle.

