Computational Linguistics, Fall 2021

# Lab 3: Exploring a Corpus with NLTK Text Processing

The first purpose of this lab is to give students more practice with Python, and, specifically, practice using the NLTK library for text processing, which will be useful for Homework 2.  Students may find Chapter 3 of [Natural Language Processing with Python](https://www.nltk.org/book/) useful as a reference.  The second purpose is that students will begin thinking about corpus exploration and what they can learn by examining corpus statistics.  In computational linguistics, understanding the data under examination is of paramount importance.

First, download a text from [Project Gutenberg](http://www.gutenberg.org) in plain-text (UTF-8) format.  You can do this with Python or with `curl` or `wget`.  The following will download Heroditus's *The History of Heroditus* into a file called `text.txt`.

```bash
curl https://www.gutenberg.org/cache/epub/2707/pg2707.txt > text.txt
```

There are a few ways of opening a text file in Python.  The standard `open()` function typically works with Latin character sets, but for dealing with Unicode (UTF-8), like our Gutenberg text, we should use `codecs.open()`

```python
import codecs
f = codecs.open('text.txt', encoding='utf8')
```

If the file is relatively small, we can read the entire file into memory with `readlines()`, but since many files are quite large, we'll often prefer to stream the file.  The `readline()` method will read one line at a time.  The Gutenberg texts are rather small, so we can use `readlines()`, which returns a list.

The following code will print every line, which isn't particularly useful unless you're just verifying that the file is what you expect.

```python
lines = f.readlines()
for line in lines:
    print(line)
```

If we print out a line, say `line[2]`, we can verify that the text is, indeed, English text.

We can use `join()` to concatenate all of the lines by a character, in this case a space.  We'll also convert everything to lower case with `lower()`.

```python
all_text = ' '.join(lines).lower()
```

And we can tokenize the text into words.

```python
tokens = nltk.word_tokenize(all_text)
```

To determine the number of tokens, we can just query the length of the list.

```python
print(len(tokens))
```

In my case, there are 188634 distinct tokens.

We can use the `FreqDist` (frequency distribution) class to count each token.

```python
fd = FreqDist(tokens)
fd.most_common(10)
```

We can also use `RegexpTokenizer()` instead of `word_tokenizer()` if we have a reason to do so, such as punctuation dominating our list of tokens with the word tokenizer.

```python
tokenizer = RegexpTokenizer('\w+')
tokens = tokenizer.tokenize(all_text)
```

When building a `FreqDist` object, we can also index it like a `dict` and change or query the counts manually.  For example:

```
fd['the'] += 1
```



In English text, this will be dominated by **stop words** such as *the*, *he*, *to*, etc., which is usually not very interesting.  Because of this, when doing any kind of frequency analysis, it's a common **preprocessing** step to remove them entirely, along with the punctuation.  We can use NLTK's list of common English stopwords and filter them out of our list of tokens.

```python3
from nltk.corpus import stopwords 
english_stopwords = stopwords.words('english')
filtered_tokens = [w for w in tokens if w not in english_stopwords]
```

Line 3 uses a Python list comprehension to filter the stop words out.  A list comprehension in Python creates a new list that obeys the predicates inside the brackets--in this case, $\{w\vert w \in \text{tokens} \land \lnot(w \in \text{english\_stopwords})\}$​. The order is preserved because this is executing a for-loop linearly through the list.  However, this is **extremely** slow, due to each check requiring an iteration through 179 English stop words.  We can improve this by first copying the stop words list into a Python set, which is backed by a hash table, giving $O(1)$​ lookup time.

```python
stopwords_set = set(english_stopwords)
```

Now our results should be more interesting and obtaining them reasonably quick.  

## Deliverables:

In your submission to Moodle, answer:

1.  After removing punctuation and stop words, how many words **types** and **tokens** are in the lower cased version of your corpus?  

2. Modify the code to examine the top *n* bigrams and trigrams.  (The number is up to you.)  Can you garner any insights by observing the most frequent unigrams and bigrams?   You can either use `nltk.util.bigrams` and `nltk.util.trigrams`or write the code for extracting bigrams and trigrams manually.  How do the results change when lemmatizing all of the tokens with the [WordNet](https://wordnet.princeton.edu/) lemmatizer?  You can do this in one line with a Python list comprehension or use a for-loop.

   ```python
   from nltk.stem import WordNetLemmatizer
   lemmatizer = WordNetLemmatizer()
   lemmatizer.lemmatize('computerize') 
   ```

   
