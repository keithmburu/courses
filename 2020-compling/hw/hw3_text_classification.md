[Computational Linguistics, Fall 2020]()

# Text Classification (50pts)

### Linear Perceptron

Let's implement the linear perceptron in Keras.  First, we need to import some libraries.  Numpy is our numerical library.  The `Sequential` library allows us to build a neural network by stacking layers together.  We will come back to layers later

```python
import numpy as np
from keras.datasets import mnist
from keras.models import Sequential
from keras.optimizers import SGD
from keras.layers import Dense
```

Recall that, in machine learning, the features of an example are usually called **x** and a label is $y$.  We have four Python variables here for the set of all examples and labels of our training and test data, respectively.  We're interested in language in this class, but just for practice, let's try classifying handwritten digits in MNIST, the data for which is included with Keras.

```
(x_train, y_train), (x_test, y_test) = mnist.load_data()
```

MNIST images are grayscale, consisting of 28 x 28 = 784 pixel values in  [0,255], representing the shade of a given pixel.  We can normalize  these values to the range [0,1] by dividing the values by 255 if we  like, which will increase performance.

![mnist image](https://camo.githubusercontent.com/2d86395e8cb5f606d03cf4848d1a754b6c346668/68747470733a2f2f75706c6f61642e77696b696d656469612e6f72672f77696b6970656469612f636f6d6d6f6e732f322f32372f4d6e6973744578616d706c65732e706e67)

MNIST images are grayscale, consisting of 28 x 28 = 784 pixel values  in [0,255], representing the shade of a given pixel.  We can normalize  these values to the range [0,1] by dividing the values by 255 if we  like.  (This is left up to you to try).

Let's flatten them to a 1D vector of length 784.

```
x_train = x_train.reshape(60000, 784)
x_test = x_test.reshape(10000, 784)
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')
```

We can then have one feature for each pixel.  This is our *x* feature vector.  Our Y = {0, 1, 2, ..., 9}, representing the digit.  We can use a **one hot vector** encoding for this, where 0 = [0 0 0 0 0 0 0 0 0 0], 1 = [ 0 1 0 0 0 0 0 0 0 0], 2 = [0 0 1 0 0 0 0 0 0 0], etc. 

 Keras does this for us.  We want to represent each of these classes as a *category* (or class label), not as a numeric value.

```
y_train = keras.utils.to_categorical(y_train, num_classes)
y_test = keras.utils.to_categorical(y_test, num_classes)
```

#### Build the Model

The `Sequential` interface of Keras allows you to stack layers with simple function calls.

```
model = Sequential()
```

We want a basic linear perceptron that has one feature for every  pixel, with all of the features initialized to 0.  We want to find the  best weights for our model with stochastic gradient descent (SGD) with a learning rate of 0.01.  We have ten classes.  Our input dimension must be the same as the number of features --in this case, one per pixel. Our **loss function** is mean squared error (MSE), the standard for the perceptron.  This can be changed.  (See Keras's other loss functions here: https://keras.io/api/losses/).  The `linear` activation tells us that we're not using any kind of [activation function](https://keras.io/api/layers/activations/).

```
model.add(Dense(10, activation='linear', input_shape=(784,)))

model.compile(loss = "mse", optimizer = SGD(lr = 0.01),
metrics=['accuracy'])
```

#### Train the Model

The `fit` function trains our model on the `x_train` and `y_train` data for a certain number of iterations (epochs).  The **validation data** is some *held-out* data that tests our model's accuracy at each epoch.  The batch size  determines how many examples we consider at one time to modify our  parameters.  You'll have to set these variables.  A reasonable batch  size to start with is 128, with 20 or so epochs.

After training, we **evaluate** to get our final accuracy on the test data.

```
history = model.fit(x_train, y_train,
                batch_size=batch_size,
                epochs=epochs,
                verbose=1,
                validation_data=(x_test, y_test))
score = model.evaluate(x_test, y_test, verbose=0)
print('Test loss:', score[0])
print('Test accuracy:', score[1])
```

How did it do?

### Multi-layer Perceptron

Now, let's use a simple neural network to tackle a language dataset, the dataset of IMDB reviews.  We have two possible sentiments for every sentence in the dataset, making this a binary classification problem.

In this assignment, we'll take a simple approach to **sentiment analysis**.  Sentiment analysis attempts to classify the "sentiment" of text.  For example, the sentence, "I'm happy today" has a positive sentiment, while the sentiment of "I hate this weather" is negative.  The sentiment of "The chance of rain today is 40%" would be neutral.  

## Bag of words classification

Recall that a **bag of words** model does not consider the order of the words (or **tokens**).  Given the sentence, "the dog rode a bike up the waterfall with a cat", the words are {a, bike, the, cat, dog, rode, with, waterfall, words}.  Note that this is a set, not a list, since there is no sense of order.    This is one of the simplest representations of the data.  A more complex representation would take into account some sense of order.  

You can read a description of this dataset here: https://keras.io/datasets/#imdb-movie-reviews-sentiment-classification.

```python
from keras.preprocessing import sequence
from keras.models import Sequential
from keras.layers import Dense, Embedding, Flatten, Dropout, LSTM
from keras.datasets import imdb

max_features = 500
maxlen=50
(X_train, y_train), (X_test, y_test) = imdb.load_data(num_words=max_features)
```

In this code, `max_features` refers to the maximum number of words we can use for features in all of the data.  These data are already sorted by frequency, so, as is, the code will use the 100 most frequent words the entire dataset.  The `maxlen` variable refers to the maximum length, in words, of a given example.  

#### Zero Padding

Since we're using sequence data,  not all sentences are the same size and some may be shorter than `maxlen`.  This is a problem when using mini-batching because our tensors expect data of a certain size.  The standard solution to this is to use **zero padding**.  This just means that we fill in any unused slots in our tensor with zeroes, making all of our examples the same size.  While it is technically possible to train an LSTM on inputs of any size, we would need to feed our model one example at a time during training, which would be extremely slow and not obviously preferable.

````python
x_train = sequence.pad_sequences(x_train, maxlen=maxlen)
x_test = sequence.pad_sequences(x_test, maxlen=maxlen)
````

#### Embedding Layer

We could represent every word or character in our input vector using a **one-hot-vector** representation, and our classifier would learn to make predictions with this data.  Despite its name, one-hot-vector representations are quite boring.  Given a vocabulary size $$|V|$$, each word is represented by a binary vector of length $$|V|$$.  For each word, a unique bit is flipped to 1.  So, for example, if we have a vocabulary of 3 words, {cat, dog, bike}, we our vector (and the corresponding input layer) must be of length 3.  One possible representation is:
dog = [1 0 0], cat = [0 1 0], bike = [0 0 1].  Each "word" then, is just represented by a bit in a vector.   If both "dog" and "cat" occur in our example, the input is [1 1 0].  
For an example of this, see [Intro to text classification with Keras: automatically tagging Stack Overflow posts](https://cloud.google.com/blog/products/gcp/intro-to-text-classification-with-keras-automatically-tagging-stack-overflow-posts).  But storing these words would need a large, sparse matrix, and they encode nothing about the relationships of words to each other.  Instead, we typically use **word embeddings** if we have sufficient data. 

Word embeddings are a relatively new innovation. The first implementation was released as a program called word2vec [(Mikilov, 2013)](https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf), though there are now competing versions, e.g., GloVe [(Pennington et al. 2014)](https://nlp.stanford.edu/pubs/glove.pdf)

As we've discussed in class, the idea is that we want to map our word vectors into a high dimensional space where similar words are close to each other and dissimilar words are farther away.  See [GloVe: Global Vectors for Word Representation](https://nlp.stanford.edu/projects/glove/). Further, words should ideally be closer to each other *along the dimensions that make sense*.   For example, ideally, the word *king* should be close to the word *queen* along some implicit "status" dimension but far away along some implicit *gender* dimension. Word embedding algorithms do this by observing which words tend to **co-occur**, yielding a compact representation that encodes semantic relationships between words.  

We can use these in an **embedding layer**.  The embedding layer encodes the words in a dense matrix of real numbers that implicitly represents the relationships between words.  Our neural network can then use these dense representations as a rich source of information about what the words mean -- much richer than a matrix of mostly zeroes and a few ones.  Recall that an embedding layer is learned by learning to predict which words with a logistic regression and using the learned weights matrix as a dense representation of the words.  We can add an [embedding layer](https://keras.io/api/layers/core_layers/embedding/) in Keras easily.

````python
model.add(Embedding(max_features, 128, input_length=maxlen))
model.add(Flatten())
model.add(Dense(128))
model.add(Dense(1, activation='sigmoid'))
````

In the above code, our `Embedding`] layer takes in `max_features` words and creates a 128-dimensions embedding space.

It's possible to learn an embedding layer separately and even to use embedding layers that someone else has trained. In this code, we train the embedding layer with backpropagation as part of the overall training process.  Here, we have a word embedding matrix of 128 dimensions.  We need to specify the `input_length` parameter when passing to a `Dense` layer.  Dense layers take one-dimensional inputs, so we flatten before passing the `Embedding`'s output to a dense layer with 128 nodes.  Finally, we send the result through a sigmoid activation layer.  

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

## Sequence Classification

Our previous model uses no order information.  Every word is isolated, and the neural network uses this bag of words representation to make predictions.  Thus far, we've used these kinds orderless representations for every task.  But we know that language has order.  What if we could encode this order information explicitly in the model?  Recurrent neural networks allow us to do this.

A **recurrent neural network** (RNN), not to be confused with a recursive neural network, is a network with recursive units: this means that the output of the unit is passed back into itself.   Thus far, we have only looked at units which take an input vector **x**, combine it with some weights **W**, and then sent the result of the dot product through an activation function to produce the result.  These units have no **memory**.  But a recurrent unit preserves memory by passing its output back into itself.  With each recursive operation, this memory (weight) decays, meaning that the further "back in time" something is, the weaker its signal.  We'll study this in more detail soon.  The crux is that RNNs allow us to model sequence data, such as that found in language, much better than a standard perceptron. 

Two common recurrent nodes are **long short-term memory** (LSTM) units and **gated recurrent units** (GRUs).  LSTMs were introduced in 1997 by [Sepp Hochreiter and Shmidinhuber](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.676.4320&rep=rep1&type=pdf) but didn't see widespread use until the last several years.  GRUs were introduced in 2014 by [Cho et al.](https://arxiv.org/pdf/1406.1078.pdf) for neural machine translation.  In our code, we can delete the `Dense` and its requisite `Flatten` operation and replace it with an `LSTM` or `GRU` layer.

````python
model = Sequential()
model.add(Embedding(max_features, 128))
model.add(GRU(128))
model.add(Dense(1, activation='sigmoid'))
````

And with that, our model can use sequence information much more effectively.  LSTMs are extremely popular in natural language processing because of their effectiveness in modeling sequences.  Recently, GRUs have gained more popularity, because they have been shown to have comparable performance to LSTMs on many tasks, but they take less time to train.  We will cover recurrent units in much more detail soon.

### Dropout 

Dropout is a kind of **regularization** for neural networks.  The purpose of the regularization is to reduce overfitting and increase generalizability.  Intuitively, if we randomly remove some percentage of the nodes in our network during training, these nodes  learn to depend less on the adjacent nodes, leading them to "evolve" to be stronger on their own.  We can add a `dropout=0.2` parameter to our GRU layer to use this.  There's also a `recurrent_dropout` option, which does the same for the recurrent unit itself.

## : Assignment

Write a report in Markdown or LaTeX that addresses the following.  It should be written in complete, grammatical English, and it should be formatted correctly.  Otherwise, points will be deducted.  

Tackle the sentiment analysis task with three kinds of recurrent units. 

Analyze (1) the amount of time required to train, e.g., how well/stably/quickly it learns and converges (use graphs), and (2) the accuracy.  You should also include non-recurrent results as a baseline.  Also try changing the vocabulary size and using dropout.  The easiest way to do this for this assignment is to check the accuracy at checkpoints.

You may, of course, add more layers and modify other aspects of the model for a more thorough analysis, but this is not required.  Detail whether your results are expected or not.

To gain full credit, include the following in your analysis:

* a linear perceptron baseline
* an equivalent logistic regression baseline
* a multi-layer perceptron (feed-forward neural network)
  * Try changing the depth (number of layers) and breadth (number of neurons per layer).  Do you notice a trend in performance?
* LSTM recurrent neural network
  * Same as MLP.

Extra Credit (+10pts): Figure out how to use a pre-trained GloVe embedding layer instead of learning one from scratch and add this to your performance analysis.

For this assignment, you may use some Keras example code, on which some of the code in this document is based, to begin.   If you do this,  use Git to clone the Keras repository into a directory.  You can see this repository here: https://github.com/keras-team/keras.

Clone it to your local machine by typing:

````shell
 git clone https://github.com/keras-team/keras
````

This will copy the contents of this directory to your computer.  We're specifically interested a file called `imdb_lstm.py` in the `examples` subdirectory.

### Tips:

1.  Be concise. 
2. Graphs can often save you a lot of words. If you decide to include graphs, I recommend using the [seaborn](https://seaborn.pydata.org/) Python package.




