# Neural Networks



We've discussed the linear classifiers the perceptron, its cousin logistic regression, and, at a high level, the mathematics undergirding their ability to learn to classify data.  Through gradient descent, these algorithms are guaranteed to change their parameters such that they improve the classification performance on the training data since their loss functions are convex.  They have one major limitation, however: they are linear classifiers, meaning that they are ultimately defining a line (or hyperplane) to separate the data into classes.  This fundamentally limits its ability to classify data: if the data aren't **linearly separable**, it's impossible for the classifier to perfectly classify the data.  In practice, this may not be a problem.  In logistic regression, for example, we are still increasing the probability of classifying the examples correctly as we walk down the loss function, even if it's ultimately impossible to classify all of the data correctly.  It might even be an advantage in some cases, as we're less likely to memorize data if we're constrained to linear separating functions.  This is the Occam's Razor principle.  A simple separating function will usually generalize better than a complex, contoured one.



 Even so, there are data on which, if we could find a curve -- or a contoured surface -- instead of just a plane to separate the data, we could classify the data more accurately. Neural networks enable this. In fact, neural networks are known as **universal function approximators** because, in principle, there are no constraints on the kinds of functions they can discover.  While there are often connections made between neural networks and neurons in the brain, let us dispense with this notion for now and view it as we would any other algorithm, because that is what it is.



## Feed-forward neural networks

A **neural network** consists of two or more **layers** of simpler models.  The simplest kind of neural network is a **feed-forward neural network**, so called because the outputs of one layer are fed into the inputs of the next layer.  One of the simplest feed-forward neural networks is the **multi-layer perceptron**.  It is, simply, three or more perceptrons stacked, where the output of one perceptron layer is sent to the input of another layer, and the final layer makes the final prediction.  You can have one or more perceptron on each layer.  

```
TODO MLP graphic

```



You can think of a single-layer perceptron as a simple neural network, but usually we reserve the term "neural network" for those with multiple layers.  Though the perceptron is a linear model, when we stack two or more of them together, we can model non-linear decision boundaries.  In practice, usually we use some kind of sigmoid or other activation function in each of the component perceptrons. Each perceptron (or logistic regression, or whatever) is called a **neuron** in a neural network.  We learned that a single perceptron produces on output which may then be passed through some activation function.  Usually, we use a smooth activation function.  Suppose that we have the simplest possible MLP, with two layers of one neuron each.  When the first layer makes a prediction, its output isn't the final prediction; it's just an intermediate real number that is fed into the second layer.  The input to the neuron in the perceptron in the second layer is then the value of the output of the precious layer.  This final layer then outputs another real number, which is the actual prediction.  We call the first perceptron layer the **hidden layer**, because its contents don't actually make any predictions.  We refer to the final layer as the **output layer**, because it generates the outputs -- the predictions.  The inputs to the first perceptron layer is called the **input layer**. This terminology is a bit confusing, because this "layer" is not a perceptron; it just consists of the feature values: the $$\mathbf{x}$$ vector.



Ultimately, a perceptron is just a set of weights $\mathbf{w}$; so, when we refer to the "hidden layer", we're in fact just referring to the weights on that layer.  



For illustrative purposes, we limited ourselves to two neurons, but in most scenarios, we would not limit ourselves to one neuron per layer.  We usually have many neurons in both the hidden and output layers.  Traditionally, these are **fully-connected**, meaning that every hidden neuron's output is sent to the input of every neuron on the output layer: if we have 128 hidden nodes, each node on the output layer will have 128 inputs.  Assuming we're solving a binary classification problem, the output layer will have one output, just as a perceptron or logistic regression.

```
TODO fully-connected
```



It is not uncommon to have more than one hidden layer.  If we have two hidden layers, then the output of layer 1 becomes the input of layer 2, and the output of layer 2 becomes the input to the output layer.  At some point, when we add sufficient layers, we may call the network "deep."  Classifiers are just functions with some parameters that we must set, and in neural networks we have multiple layers of parameters.  Neural networks are composite functions with weights to set at each layer.



## Setting the Parameters

We learned about gradient descent in the chapter on logistic regression.  We can also use gradient descent with an MLP to set our weights, but because neural networks are composite functions, we must use the chain rule to derive the update rule.  There are numerous resources that explain the mathematics of this, but we will settle for intuition in this section.



Recall that in the perceptron and logistic regression, we compute the change of our weights $\mathbf{w}$ based on a measurement of our error -- how far off our prediction was from the correct label.  We do the same in neural networks *on the output layer*.  In the simplest case, the output layer is just a single node; so, we can compute its loss in the same way we do for linear classifiers. But we must *propagate* this error back through the network, all the way to the first layer.  When we make a prediction, we modify the weights throughout the entire network according to this loss.  This process is baclled **backpropagation**.

