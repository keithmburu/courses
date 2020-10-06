Computational Linguistics Primer (Draft)

Alvin Grissom II

# Multi-class Linear Classification and Neural Networks

The perceptron and logistic regression are the foundation of what we call neural networks.  **Neural networks** are the result of stacking two or more layers perceptron or perceptron-like classifiers, sending the output of one layer to the inputs of the next layer, before finally making a prediction.  The term "neural network" once referred to stacked linear models, e.g., the multi-layer perceptron (MLP). But the term is now vague and refers to many very different classes of models.  What they have in common is that they stack simpler classifiers to produce a result and use stochastic gradient descent or some similar algorithm to learn to fit the training data.  



## Multi-class Classification

### One-vs-all 

Before we discuss  neural networks, let's first discuss how we might tackle classification tasks for more than two classes.  All of the classifiers we've discussed until now have been strictly binary, but most problems cannot be framed in this way.  There are several heuristic methods for dealing with this. One such method, called one-vs-all or one-against-all classification, trains multiple classifiers to predict from $k$ classes.  More specifically, the one-vs-all method trains $k-1$ classifiers.  For each classifier, the positive class is one of the $k$ classes, while the negative class is *everything else*; hence the name. At test time, we use each of the classifiers to make a prediction for its positive class.  We rank each classifier's prediction for the class is represents and ultimately predict the class with the highest probability.  One disadvantage to this approach is that it does produce a probability distribution over all classes, since each classifier is trained independently, but it often works well in practice.  There are other methods, such as all-vs-all (also called one-vs-one) classification and error-correcting codes.



### Maximum Entropy Classification

One-vs-one is conceptually similar to one-vs-all.  we train $k(k-1)/2$ classifiers, training a binary classifier to compare each class to every other class.  The maximum entropy  (**MaxEnt**) or **softmax** classification technique uses a similar idea.  MaxEnt is another  name for **multinomial logistic regression**, i.e., logistic regression that can handle more than two classes by use of a sigmoid-like activation function that handles more than two classes.   MaxEnt learns a set of weights $\mathbf{w_i}$ for each of the $k$ classes.
$$
\text{softmax}(z_i)=\frac{e^{z_i}}{\sum_{i=j}^k e^{z_i}},
$$


whose input $z_i=\mathbf{w_i}\cdot \mathbf{x}+b_i$, where $\mathbf{w_i}$ is the vector of weights learned for a particular class.   This computes $P(y=i|\mathbf{w_i}, b_i; \mathbf{x})$, the probability that the correct class is $y$ given this classes weights and the current feature values.   The denominator is a normalizing term that makes our probabilities sum to 1.  We can learn the independent weights by minimizing the loss of the independent weights jointly with stochastic gradient descent.  Note that if the number of classes $k=2$, the softmax function reduces to the logistic function.



### Feed-forward Neural Networks



We can think of logistic regression and linear perceptron both as extremely simple neural networks if we simply change the terminology.  Let's consider  logistic regression.  We have an three layers: an input layer, a hidden layer, and an output layer.  The **input layer**, typically represented by $\mathbf{x}$, refers to the feature values.  Recall that these inputs are then fed into the classifier, which assigns a weight to each feature, takes the dot product of the weights and features, and sends them through the sigmoid function.  The classifier consists of the weights $$\mathbf{w} + b$$ and a function, and a means of converting them into a prediction, i.e., by taking $\sigma({\mathbf{w\cdot x + b})}$ .  We'll call this classifier (logistic regression) a **neuron** (or node, or unit), which, in this case, comprise our **hidden layer**.   It produces an prediction, $\hat{y}$, which we hope is closer to the actual answer $y$ than not.  We'll now call this the **output layer**.   Based on the error, or difference, between the correct answer $y$ and the predicted answer $\hat{y}$,  we learn the weights via stochastic gradient descent, which we will now call **backpropagation**.

We generally reserve neural networks and the associated terms above for multi-layered networks, not for single perceptrons and logistic regressions, but it's helpful to introduce these concepts in relation to concepts with which you're already familiar. 

The most fundamental kind of neural network, of which the MLP is an example, is the **feed-forward neural network**, in which one layer feeds in to another layer, with no cycles, until a prediction is made by the final layer.  The feed-forward neural network consists of an input layer, one or more hidden layers, and an output layer. 

Let's consider an example of a classifier we might actually call a neural network: a network with one **hidden layer** consisting of four perceptron neurons, making this an MLP.  Our input layer $\mathbf{x}$ contains our feature values.  These feature values are sent to *all* of the hidden neurons, which independently produce activations, which we'll call $a_1,\ldots, a_4$.  Each of these hidden units send their output to the output layer, which we'll make a sigmoid function (implying this is a binary classifier).  The fact that unit sends its output to every unit on the next layer makes this network **fully-connected**, which is standard for feed-forward neural networks.  We take the dot product of the hidden layer features and weights -- all of them -- and send them through the sigmoid function to produce our final prediction.  A logistic regression or perceptron is just a function.  A neural network is a composite function, e.g., $y=f(g(x))$.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Neural_network_example.svg/220px-Neural_network_example.svg.png)

Once a prediction $\hat{y}$ is made, just as in logistic regression or a single perceptron, we calculate the $y-\hat{y}$ and perform stochastic gradient descent. In logistic regression, we use the update rule for the weights
$$
w:=w+\eta\nabla\mathscr{L}\\
w:=w+\eta(y-\hat{y})\mathbf{x}.
$$
But in a neural network, we need to *propagate* the error from the output layer through the hidden layer.  We still use the gradient, but we have to apply the chain rule for derivatives so that we update the weights for all of the nodes in the network.  The intuition is the same as that of logistic regression or perceptron.  The primary difference is that we have to update the weights for multiple neurons instead of only one.  We're still trying to find the optimum point on a surface.  However, unlike logistic regression, the loss function for neural networks is not convex; so, there is no guarantee that we will find the optimum with gradient descent.  Even so, given the complexity of the classifier function, the representational power of neural networks is significantly greater than that of linear models; so, they still often find superior solutions.

In order to learn interesting functions, neural networks must be initialized.  They can be initialized randomly or with various other clever initializations.  This is to force **symmetry breaking** of the network.  If the nodes all have the same initial values, they will all have the same gradient and learn the same weights, which would be pointless.