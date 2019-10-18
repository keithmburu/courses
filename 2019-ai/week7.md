Artificial Intelligence, Fall 2019

Alvin Grissom II, Ursinus College


# Assignment 7: Text Classification

In this assignment, we'll take a simple approach to **sentiment analysis**.  Sentiment analysis atttempts to classify the "sentiment" of text.  For example, the sentence, "I'm happy today" has a positive sentiment, while the sentiment of "I hate this weather" is negative.  The sentiment of "The chance of rain today is 40%" would be neutral.  



## Part 1: Bag of words classification

A **bag of words** model does not consider the order of the words (or **tokens**).  Given the sentence, "the dog rode a bike up the waterfall with a cat", the words are {a, bike, the, cat, dog, rode, with, waterfall, words}.  Note that this is a set, not a list, since there is no sense of order.    This is one of the simplest representations of the data.  A more complex representation would take into account some sense of order.  

### Multi-layer Perceptron

Now, let's use a simple neural network to tackle this problem.  We have two possible sentiments for every sentence in the dataset.

The sentiment data we're using consists of IMDB reviews.  Each of the reviews is labeled for sentiment.  You can see a description of this dataset here: https://keras.io/datasets/#imdb-movie-reviews-sentiment-classification.

```python
from keras.preprocessing import sequence
from keras.models import Sequential
from keras.layers import Dense, Embedding, Flatten, Dropout, LSTM
from keras.datasets import imdb

max_features = 500
maxlen=50
(X_train, y_train), (X_test, y_test) = imdb.load_data(num_words=max_features)
```

In this code, `max_features` refers to the maximum number of words we can use for features in all of the data.  These data are already sorted by frequency, so, as is, the code will use the 500 most frequent words the entire dataset.  The `maxlen` variable refers to the maximum length, in words, of a given example.  

#### Zero Padding

 When learning to classify images, we could assume that every image had the same dimensions.  Since we're using sequence data,  not all sentences are the same size and some may be shorter than `maxlen`.  This is a problem when using mini-batching because our tensors expect data of a certain size.  The standard solution to this is to use **zero padding**.  This just means that we fill in any unused slots in our tensor with zeroes, making all of our examples the same size.  While it is technically possible to train an LSTM on inputs of any size, we would need to feed our model one example at a time during training, which would be extremely slow and not obviously preferable.

````python
x_train = sequence.pad_sequences(x_train, maxlen=maxlen)
x_test = sequence.pad_sequences(x_test, maxlen=maxlen)
````

#### Embedding Layer

"You shall know a word by the company it keeps." (Firth, 1957)

We recently discussed **word embeddings** in class.  Now, we'll use them to represent our vocabulary.

We could represent every word or character in our input vector using a one-hot-vector representation, and our classifier would learn to make predictions with this data.  For an example of this, see [Intro to text classification with Keras: automatically tagging Stack Overflow posts](https://cloud.google.com/blog/products/gcp/intro-to-text-classification-with-keras-automatically-tagging-stack-overflow-posts).  But storing these words would need a large, sparse matrix, and, as we discussed, they encode nothing about the relationships of words to each other.  Instead, we typically use **word embeddings** if we have sufficient data. 

Word embeddings are a relatively new innovation. The first implementation was released as a program called word2vec [(Mikilov, 2013)](https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf), though there are now competing versions, e.g., GloVe [(Pennington et al. 2014)](https://nlp.stanford.edu/pubs/glove.pdf)

The idea is that we want to map our word vectors into a high dimensional space where similar words are close to each other and dissimilar words are farther away.  See [GloVe: Global Vectors for Word Representation](https://nlp.stanford.edu/projects/glove/). Further, words should be closer to each other *along the dimensions that make sense*.   For example, ideally, the word *king* should be close to the word *queen* along some implicit "status" dimension but far away along some implicit *gender* dimension. Word embedding algorithms do this by seeing which words tend to **co-occur**.  We'll come back to this later, but for now, think of word embeddings as a compact representation that encodes semantic relationships between words.  

We can use these in an **embedding layer**, which we have discussed in class.  The embeding layer encodes the words in a dense matrix of real numbers that implicitly represents the relationships between words.  It learns these from unlabeled data.  Our neural network can then use these dense representations as a rich source of knowledge about what the words mean -- much richer than a matrix of mostly zeroes and a few ones.  Recall that an embedding layer is learned by learning to predict which words are likely to occur near other words and filling a matrix with these values.  We can add an embedding layer in Keras.

````python
model.add(Embedding(max_features, 128, input_length=maxlen))
model.add(Flatten())
model.add(Dense(128))
model.add(Dense(1, activation='sigmoid'))
````

It's possible to learn an embedding layer separately and even to use embedding layers that someone else has trained. In this code, we train the embedding layer with backpropagation as part of the overall training process.  Here, we have a word embedding matrix of 128 dimensions.  We need to specify the `input_length` parameter when passing to a `Dense` layer.  Dense layers take one-dimensional inputs, so we flatten before passing it to a dense layer with 128 nodes.  Finally, we send the result through a sigmoid activation layer.  

````python
model.compile(loss='binary_crossentropy',
              optimizer='adam',
              metrics=['accuracy'])

print('Train...')
model.fit(x_train, y_train,
          batch_size=batch_size,
          epochs=15,
          validation_data=(x_test, y_test))
score, acc = model.evaluate(x_test, y_test,
                            batch_size=batch_size)
print('Test score:', score)
print('Test accuracy:', acc)

````

Here, we use `binary_crossentropy` loss because we only have two classes.  We could also use `categorical_crossentropy`, which reduces to binary cross-entropy for two classes.

Try it and see how it does. Since we have two classes, random guesses will give us 50% accuracy.

## Part 2: Sequence Classification

Our previous model uses no order information.  Every word is isolated, and the nueral network uses this bag of words representation to make predictions.  Thus far, we've used these kinds orderless representations for every task.  But we know that language has order.  What if we could encode this order information explicitly in the model?  Recurrent neural networks allow us to do this.

A **recurrent neural network** (RNN), not to be confused with a recursive neural network, is a network with recursive units: this means that the output of the unit is passed back into itself.   Thus far, we have only looked at units which take an input vector **x**, combine it with some weights **W**, and then sent the result of the dot product through an activation function to produce the result.  These units have no **memory**.  But a recurrent unit preserves memory by passing its output back into itself.  With each recursive operation, this memory (weight) decays, meaning that the further "back in time" something is, the weaker its signal.  We'll study this in more detail soon.  The crux is that RNNs allow us to model sequence data, such as that found in language, much better than a standard perceptron. 

Two kinds of recursive nodes are **long short-term memory** (LSTM) units and **gated recurrent units** (GRUs).  LSTMs were introduced in 1997 by [Sepp Hochreiter and Shmidinhuber](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.676.4320&rep=rep1&type=pdf) but didn't see widespread use until the last several years.  GRUs were introduced in 2014 by [Cho et al.](https://arxiv.org/pdf/1406.1078.pdf) for neural machine translation.  In our code, we can delete the `Dense` and its requisite `Flatten` operation and replace it with an `LSTM` or `GRU` layer.

*Note: If you are using GPU-accelerated TensorFlow, you can replace GRU and LSTM with CuDNNGRU and CuDNNLSTM, respectively, in your code for much faster performance. Don't forget to change your imports, as well.*

````python
model = Sequential()
model.add(Embedding(max_features, 128))
model.add(GRU(128))
model.add(Dense(1, activation='sigmoid'))
````

And with that, our model can use sequence information much more effectively.  LSTMs are extremely popular in natural language processing because of their effectiveness in modeling sequences.  Recently, GRUs have gained more popularity, because they have been shown to have comparable performance to LSTMs on many tasks, but they take less time to train.  We will cover recurrent units in much more detail soon.

### Dropout 

In class, we've briefly discussed [dropout](http://jmlr.org/papers/volume15/srivastava14a/srivastava14a.pdf), a kind of **regularization**.  The purpose of the regularization is to reduce overfitting and increase generalizability.  Intuitively, if we randomly remove some percentage of the nodes in our network during training, these nodes  learn to depend less on the adjacent nodes, leading them to "evolve" to be stronger on their own.  We can add a `dropout=0.2` parameter to our GRU layer to use this.  There's also a `recurrent_dropout` option, which does the same for the recurrent unit itself.

## Part 3: Assignment

Write a report that addresses the following.  It should be written in complete, grammatical English, and it should be formatted correctly.  Otherwise, points will be deducted.

Tackle the sentiment analysis task with three kinds of recurrent units. 

Analyze (1) the amount of time required to train, e.g., how well/stably/quickly it learns and converges (use graphs), and (2) the accuracy.  You should also include non-recurrent results as a baseline.  Also try changing the vocabulary size and using dropout.

You may, of course, add more layers and modify other aspects of the model for a more thorough analysis.  Detail whether your results are expected or not.

Extra Credit (+10pts): Figure out how to use a pre-trained GloVe embedding layer instead of learning one from scratch and add this to your performance analysis.

For this assignment, you may use some Keras example code, on which some of the code in this document is based, to begin.   If you do this,  use Git to clone the Keras repository into a directory.  You can see this repository here: https://github.com/keras-team/keras.

Clone it to your local machine by typing:

````shell
 git clone https://github.com/keras-team/keras
````

This will copy the contents of this directory to your computer.  We're specifically interested a file called `imdb_lstm.py` in the `examples` subdirectory.

Open this file in your favorite editor.  

If you're getting a ValueError, this StackOverflow post might be helpful: https://stackoverflow.com/questions/55890813/how-to-fix-object-arrays-cannot-be-loaded-when-allow-pickle-false-for-imdb-loa





