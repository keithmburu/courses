# In-class Exercise: Word Embeddings

Recently, we've discussed and used word embeddings.  For this exercise, we'll get some hands-on experience in building and using them.

1. Install `gensim` and `pandas`, if you haven't already. Also install `ftfy`, a tool for cleaning up text files with encoding errors.  This is a Python package that can be used to create word embeddings (among other models, like topic models) and perform various operations on them.

   ```shell
   pip3 install gensim
   pip3 install pandas
   pip3 install ftfy
   ```

2. I've provided some basic code for training a Word2Vec model and querying the most similar terms. You can find it here:
https://github.com/acgrissom/courses/tree/master/2019-ai/code/w2v_inclass

The easiest way to retrieve all of these files is to use `git`, but you can download them manually if you prefer.

```shell
git clone https://github.com/acgrissom/courses
```

You just need to provide a text file.  You might try downloading one from Project Gutenberg.  https://www.gutenberg.org/ebooks/35013. I've also provided code.  Download the plain text (UTF-8)  file.  You can also download the pretrained Google News embeddings here: https://github.com/mmihaltz/word2vec-GoogleNews-vectors, which you can load with the following line:

```python
model = gensim.models.Word2Vec.load_word2vec_format('./GoogleNews-vectors-negative300.bin', binary=True)  
```

If you use the Google News embeddings, you can skip steps (1) and (2).

1. Strip the punctuation and other special characters with the provided script. 
2. Train the model, which will create a file.  (You may encounter errors at ths point, but I am here to help you.)
3. Query the model and look for the most similar words to some interesting terms.  By default, the package will use [cosine similarity](https://www.machinelearningplus.com/nlp/cosine-similarity/) as a distance metric, but it is possible to use others.
4. For examples of the kinds of functions `gensim` provides (analogy generation, similar owrds, etc.), go here: https://radimrehurek.com/gensim/models/keyedvectors.html


Previously, we've talked about gender, ethnic, and other biases that are recapitulated in word embeddings.  Can you think of any others? Try them and write about what you find.