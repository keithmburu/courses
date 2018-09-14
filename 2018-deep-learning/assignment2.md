Deep Computer Vision and Language, Fall 2018

Alvin Grissom II, Ursinus College


# Assignment 2: Adding Layers


In the previous assignment, we created a single-layer neural network to perfrom 10-way classification using a one hot vector representation.  In this assignment, we will add multiple layers.

## Part 0: Install Packages

First, we must ensure that we have all of the necessary packages: pydot and GraphViz. Assuming, you setup your system as described in Assignment 0, the following should work.  GraphViz is an open source visualization package, and pydot is a Python interface for it.

### Typesetting
If you have not installed LaTeX, you should do so (perhaps while working through the rest of this assignment).  It is a large package, which will take a significant amount of time to download and install.  While you may use Markdown for this assignment, some future reports must be submitted as a PDF generated in LaTeX with the NIPS template.

If you are using Markdown (an easy typesetting language), you may find a specialized editor helpful, such as [Typora](http://typora.io).  You can learn about Markdown syntax here: https://support.typora.io/Markdown-Reference

### macOS

    brew install graphviz
    pip3 install graphviz
   	pip3 install pydot
   	pip3 install ann_visualizer

 LaTeX:
 Install the package from the [MacTeX web site](http://www.tug.org/mactex/)
   	
### Windows

    choco install graphviz
    pip3 install graphviz
    pip3 install pydot
    pip3 install ann_visualizer

LaTeX:

    choco install miktex

### Ubuntu
    sudo apt-get install graphviz
    pip3 install graphviz
    pip3 install pydot
    pip3 install ann_visualizer

LaTeX:  

    sudo apt-get install texlive-full


    
### OpenSuSE
    sudo zypper install graphviz
    pip3 install graphviz
    pip3 install pydot
    pip3 install ann_visualizer

LaTeX:

    sudo zypper install texlive-latex


## Part 1: Tweaking a Single-Layer Perceptron

A single perceptron (neuron) is capable of performing classification on its own.  In this case, the input vector, **x**, consists of input features, and we directly learn a weight vector to push the final prediction as either the positive or negative class.

As we learned in class, a multi-layer perceptron sends the output of a perceptron to another *layer* of perceptrons.  You can, therefore, think of the output of the first layer as the input features of the next layer.

The *input layer* consists of our features.  For MNIST, these would be the 784 values for every pixel in the image.  The final layer is called the *output layer*, and it makes predictions.  All of the layers in between are *hidden layers*. 

In general, we want the simplest model that will give us good results on accuracy or some other performance metric.  There is nothing to be gained by adding unnecessary complexity, but if you have a theoretical or practical motivation for doing so, creating more complex neural networks may be justified.

Ultimately, what we're doing is trying to learn a set of weights in each layer, all at once, that will optimize some loss function, giving us better accuracy.  

We can make some basic modifications to our code from Assignment 0 to transform it into a multi-layer neural network.  Recall that previously we defined our model in the following way:

    model.add(Dense(10, activation='linear', input_shape=(784,)))
    model.compile(loss = "mse",
    optimizer = SGD(lr = 0.01),
    metrics=['accuracy'])

   


This gives ia a single-layer model with 10 categorical classes and 784 input features and a linear **activation**, which determines the output of a neuron.  Keras includes support for many [activation functions](https://keras.io/activations/).  Here, we are optimizing (minimizing) [mean squared error](https://en.wikipedia.org/wiki/Mean_squared_error), which is just the square of the difference between the true answer and the predicted answer.  This loss function is often used for linear regression. 

  As is, our model yields about 10% accuracy, which is random, but you likely found that using different activations and/or loss functions raised the accuracy significantly. 

The model as is attempts to use **wx** + *b* predict the correct class and fails miserably.  There are a number of problems with the initial architecture for this particular problem.  We can do significantly better adding a soft activation function in place of the linear one.

In class, we discussed the **sigmoid** activation function, which we could use with some modifications.  It's also common to use the **softmax** activation function, which assigns each class a probability between 0 and 1, where all of the probabilities sum to 1.

      model.compile(activation = 'softmax', ...

While we're changing code, we can also disambiguate our accuracy metric, by replacing 'accuracy' with 'cateogircal_accuracy'.  If we use the underspecified 'accuracy', Keras will guess whether we should use binary accuracy or multi-class accuracy, and it doesn't always guess correctly; so, it is best to avoid the ambiguity.  Using 'binary_accuracy' will give us an inflated accuracy score.      

    metrics=['categorical_accuracy'])

Try running this again while substantially increasing the number of epochs. You can easily quit with Ctrl+C if it takes too long. How does it do?

This simple change -- adding a softmax activation -- allows a linear combination of our weights and vectors to perform much better.  Try running it a few times to see if the accuracy is stable.  

### Normalization

Let's go back to our original model, with a simple 'linear' (non-)activation and simply normalize our inputs from the beginning.  (Recall that each pixel value can vary from 0 to 255.)
    
    x_train /= 255
    x_test /= 255

Re-run the model, and you should find that these results are impressive.  Ponder why it is that either the normalization or the *softmax* seems to help the model. Try *softmax* with normalization.

### Swapping Activations

Recall that the **sigmoid** assigns a probability to two possible classes, not three or more.  For this reason, the softmax activation is often used for multiclass problems. The **softmax** function is a generalization of the sigmoid for multiple classes.  For 2 classes, the softmax function reduces to the sigmoid function.

We can use the sigmoid activation for multiple classes if we prefer, but we must be careful.  Our loss function needs to be compatible with our activation in the output layer.  The sigmoid activation in the output layer requires that we use 'binary_crossentropy' for our loss.  For an explanation of why this is, see the following:

[https://www.depends-on-the-definition.com/guide-to-multi-label-classification-with-neural-networks/]()


Our loss functions are all just different ways of measuring how wrong the model's guess is. In this case, binary cross-entropy measures the difference in (log) probability for each class, given the input.



# Part 2: Adding Layers

### Visualize

It's encouraging that a simple linear combination of pixel values yields such accurate results for a handwriting recognition task.  But if we want more expressivity -- if we would like to divide our data by something other than a flat hyperplane -- we need to add more layers to our network.

The trick is that, by stacking linear perceptrons, we can learn non-linear separating functions.  First, let's visualize our model as it is.  Follow the instructions here: [https://keras.io/visualization/]()



If we open the PNG image file, we see a simple visual representation of the architecture of our model.  In this case, we have a single input layer which maps directly to the output layer: *y'*  = **wx** + *b*, where *y'* is the label guess.  Our layer is *Dense*, meaning that every input maps to the next layer: in the case, every input feature (pixel value) is used in predicting the label *y*.

### 2-Layer Network
Let's add a layer to out network, changing the structure to look like this.
We can add a layer:

    model.add(Dense(5, activation='linear', input_shape=(784,)))
    model.add(Dense(10, activation='linear')

We can use another package to get an alternate visualization of our model. (Note that it may take a while to generate this image).
    
    from ann_visualizer.visualize import ann_viz
    ann_viz(model)

The middle layers are **hidden layers**.  In this network, we compress the representation to five hidden layers before making a prediction.  Try increasing or decreasing the number hidden layers to see how it affects the accuracy.  Also try different activations, such as ReLU.  You can add as many layers as you like, but for a simple problem like this, you'll get diminishing returns and run into problems of **overfitting**, which occurs when the model learns to predict the training data very well but doesn't generalize well to unseen data: it just memorizes how to recapitulate the training data.

# Part 3: Report

Write a report in LaTeX (preferred), according to the guidelines in the syllabus, or Markdown, which addresses the following questions.  Provide theoretical and/or experimental justification for your answers.  


0.  What is the link to your GitHub account?  If you do not yet have one, include a copy of the e-mail confirmation sent to you after registration.
1.  Is there a relationship between using or not using an activation function and normalizing the data as it pertains to accuracy? 
2.  For the single layer perceptron, which activation makes the most sense to use in the output layer?
3. What kind of hidden layer architecture worked best for you? A graph or a table, along with an explanation, would likely be the best way to justify your answer.
4. (Harder) Suppose we would like to predict more than one class at once (multi-label classification)--for example, "cat" and "sleeping", but we want the classes to be independent of each other, i.e., a high probability of one class should not lower the probability of the other classes.  How might one approach this?  Would a softmax activation at the prediction layer be appropriate?  If so, explain why.  If not, what would be more appropriate?  

