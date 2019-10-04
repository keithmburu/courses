Artificial Intelligence, Fall 2019

Alvin Grissom II, Ursinus College


# Weekly Homework 6: Visualizing Learning


Assignment 2 encouraged you to try various combinations of layers and activations improve performance and to visualize the structure of your network..  In this assignment, we'll learn some basic techniques for monitoring how the model is performing on training and test data.

## Part 0: Install Packages

For this assignment, we need to install matplotlib.

    pip3 install matplotlib



## Part 1: Plot Loss and Accuracy

Recall that we typically train our model on one set of data **training data** and test on some smaller set of **test data**.  It isn't enough to simply memorize the training data; we want to *generalize* to test data that the model hasn't yet seen, reducing our **generalization error**.  We also typically have some **development data** (also called **validation data** or dev set) which the model hasn't seen.  The dev set is test data that we use to tune our model. (We'll discuss hyperparameter tuning later.)  Keras automatically sets about some percentage of the training data as a dev set during training and reports the "validation accuracy."  A decription how to determine this is described in the Keras API documentation: [https://keras.io/models/sequential/]()

### Model Training History

Let's look at the code for Assignment 2.  In particular, let's look at this line:

```python
history = model.fit(x_train, y_train,
                    batch_size=batch_size,
                    epochs=epochs,
                    verbose=1,
                    validation_data=(x_test, y_test))
```

Recall that `model.fit()` trains the model, but it returns an object that has some useful properties for analyzing the model.  We can access a [dictionary](https://www.w3schools.com/python/python_dictionaries.asp) (hash table) by accessing `history.history`

```python

history_dict = history.history
```

If we like, we can call `print(history_dict.keys())` function to see its keys.  The keys we're concerned here are `'loss'`, `'acc'`, and `'val_acc'`,  which refer to the training loss, the accuracy on the training data, and the accuracy on the validataion data, respectively.

### Visualize Learning

To visualize the learning process, we need to import `matplotlib`.

    import matplotlib.pyplot as plt

Now, let's plot the loss as a function of the number of epochs.

```python
plt.plot(range(epochs), history_dict['loss'], label='Loss')

```

If you're not familiar with the `range` keyword, take a moment to look it up.  Matplotlib's plot function has a number of optional arguments, but we're only using three here.  The first is a seuqnce of number from 0 to 4, one for each epoch.  The second is a list of five real numbers that give our cross-entropy loss.  The last argument will only be used if we have a legend.   We can plot what we have with `plt.show()`.

Graphs should generally label the appropriate axes.  Let's do so and add a legend.



```python
plt.xlabel('Epoch')
plt.ylabel('Performance')
plt.legend()
plt.show()

```

We may want to save the figure to a file rather than display it in a window.  We can do this with `plt.savefig()`.  Usually, for research papers, we want to use SVG (vector graphics) formats, but if you don't currently have a way of viewing these, try saving as a PNG or JPEG file.

If you're training for a long time, you can save images intermittently to get a quick snapshot of how your model is doing.

### Plotting Image Data

Recall that MNIST is stored in matrices.  These are called **tensors** in deep learning terminology.  Tensors are generalizations of matrices that can exist in multiple dimensions.  A 3D tensor, for example, can be viewed as a cube.

 We can plot these on a gray colormap with Matplotlib.  You can plot these as long as the dimensions are compatable with the plot.  If the following doesn't work, look for changes you have made to the format of the data that prevent it from being plotted in 2D space.

```python
plt.imshow(x_train[0], cmap=plt.get_cmap('gray'))

```

## Part 2: Homework

Keras provides a number of common optimization algorithms for minimizing loss: one example example is stochastic gradient descent (SGD),which we have discussed, but there also several others.  We will discuss the differences in these later, but for this assignment, it is sufficient to experiment.

Use at least three different optimization algorithms on the MNIST data to investigate their effect on learning.  (You may add other data sets if you like, but it is not required).  Keep in mind that there are many variables at play: the learning rate, the depth of the network, the number of nodes, the number of epocs.  When drawing a conclusion, be sure to control for the appropriate variables.
