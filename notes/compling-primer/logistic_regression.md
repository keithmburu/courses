Computational Linguistics Primer (Draft)

Alvin Grissom II

# Logistic Regression

Note: All images are sourced from Wikipedia.

For all of its elegance, one weakness of the perceptron algorithm is its tendency to **overfit** the training data.  This is due in part to the fact that the activation $a$ is the pure result of a linear combination, meaning that $a$ is in principle unbounded.  While we can force hard constraints on either the weights or the activations manually, we might hope that there were a more principled way of constraining the activations.  

Logistic regression uses a very similar learning and prediction algorithm to that of the perceptron, but its predictions are probabilities instead of unbounded scalars.  Despite its anachronistic name, logistic regression is a *classification* algorithm.  One way of mitigating this is to send the output of the dot product into an **activation function** that constrains the output.  For example:
$$
f(t)=
\begin{cases}
1 & \text{if }  t > 0\\
0 & \text{otherwise}
\end{cases}
$$
<img src="../../2020-compling/slides/images/Activation_binary_step.svg" alt="Step function graph. Source: wikipedia" style="zoom:150%;" />

This is a binary step activation function that forces all predictions to be either 1 or 0.   This achieves our goal of constraining our outputs (which we could also accomplish by normalizing the inputs), but we lose all information about the confidence in our prediction.  Concretely, the input of the function will be $\mathbf{w}\cdot\mathbf{x} + b$.  If this value is positive, the output is 1; otherwise it is 0.  Usually, we would like, however, to have a probability of our prediction, not just 1 or 0.

If the probability of the positive class is $p$, the probability of the negative class must be $1-p$, since probabilities must sum to 1. Logistic regression uses the same linear combination as the perceptron (though sometimes, instead of $\mathbf{w}$, we use $\mathbf{\beta}$ or $\mathbf{\theta}$ for the weights, with no change in meaning, as different communities have different aesthetic preferences).  During prediction, the final value is sent into the **logistic function**, which has asymptotes at 0 and 1, This function, denoted by $\sigma (t)$, "squishes" its input into [0,1].  This is a monotonic function, so the absolute ordering of its inputs are maintained in its output; only the magnitudes differ.  The **logistic function** is one example of a **sigmoid function** (S-shaped function).  The logistic function is a smooth or "soft" version of the binary step function,  defined as:
$$
\sigma(t) = \frac{e^t}{1 + e^t} =\frac{1}{1+e^{-t}}.
$$
Often, $e^x$ is written as $\text{exp}[x]$ for readability.  

![Logistic function graph. Source: Wikipedia](../../2020-compling/slides/images/logistic_curve.svg)

Now, when we make a prediction, we simply send our dot product through $\sigma(t)$ to compute our soft activation.
$$
\begin{align}
p &= \sigma(\mathbf{w}\cdot\mathbf{x}+b) \\
&= \frac{1}{1+\text{exp}[-(\mathbf{w}\cdot\mathbf{x}+b)]}\\
&= \frac{1}{1+\text{exp}[-(\sum_{i=1}^n w_i x_i + b)]}\\
&= P(y=1|\mathbf{w};\mathbf{x})
\end{align}
$$

This function has several convenient properties, such as differentiability, which we will explore later. Euler's number $e$ frequently appears in statistics, and it may seem a bit too convenient for it to appear here.  (In fact, we could use any integer $a>1$ in place of $e$ and the functions would still have asymptotes at $y=0$ and $y=1$, but since the derivative of $e^x$ is itself, using $e$ simplifies certain derivations.) Our activation $p$ is now the probability of $y=1$, the positive class, given the current parameters $\textbf{w}$ and example features $\textbf{x}$.

The logistic regression prediction algorithm is very similar to that of the perceptron.  For mathematical convenience, we'll use 0 and 1, respectively, for the negative and positive classes, instead of -1 and 1, as in the perceptron.

```pseudocode
function classify(x):
    p = predict(x)
    if p < 0.5:
        return 0
    else:
        return 1
        
function predict(x):
    return logistic(dot(w, x) + b))    
```

The value $p$ is the probability of the positive class; by extension, $1-p$ is the probability of the negative class.  If $p$ < 0.5, then the model predicts that the negative class is more likely.  Sometimes $\pi$ (not 3.14) is used instead of $p$ with no change in meaning.  For many reasons, we often use **log probabilities** in machine learning.

The perceptron algorithm implicitly used a very simple hinge **loss function** to calculate the penalty of predictions.  Our error is still $y-p$, the difference between the actual answer and the prediction, but now $p$ is the result of sending $\mathbf{w}\cdot \mathbf{x} + b$ through the logistic sigmoid function.  As we'll see shortly, this is no arbitrary decision.  Our update rule is defined for a given example as
$$
w_i := w_i + \eta(y-p)x_i.
$$
where $\eta$ is the learning rate, $x$ is the feature value, $y$ is the true answer (1 or 0), and $w$ is the weight for feature $x$ in a given example.

```pseudocode
function train(x, y, learning_rate):
1.  Initialize weights vector w to 0 in [0, 1].
2.  for each example (x, y) in D:
        p = predict(x)
        for each w in weights:
            w = w + learning_rate * (y - p) * x
        b = b + learning_rate * (y - p) * x
```

This looks very similar to the perceptron learning algorithm.  This update rule expresses the fact that if feature $x$ is 0, it goes unchanged.  Otherwise, we update the weights proportional to the error $y-p$, scaled by the learning rate $\eta$.  While this is fortunately intuitive, it is also mathematically sound given the loss function of logistic regression.

## Logistic Regression Loss Function

The loss function of logistic regression has several names, including **logistic loss**, **cross-entropy loss** and **negative log likelihood**.  Cross-entropy is a term  from information theory and discussed in the chapter on that topic.  Logistic regression is an algorithm of **cross-entropy minimization**. Ultimately, our goal is to, on average, maximize the probability of the correct class $y$ given a set of features for a sentence $\textbf{x}$.  Logistic regression is a **discriminative classifier**, meaning that it doesn't have a prior like a generative classifier.  It directly maximizes $P(y|x)$, unlike Naive Bayes, which maximizes $\frac{P(\mathbf{x;\mathbf{w}}|y)P(y)}{P(\mathbf{x};\mathbf{w})} \propto P(\mathbf{x;\mathbf{w}}|y)P(y)$.   Logistic regression learns weights that predict
$$
\underset{y}{\text{argmax}}\text{ }P(y|\textbf{x};\textbf{w}).
$$
Recall that the argmax here returns the $y$ (the class) that maximizes $P(y|x)$.

There are only two possible classes: 0 and 1.  Thus, $P(c) = 1-P(\neg{c})$.  At this point, it's useful to use the concept of an odds ratio. The **odds** that an event $e$ will occur is the probability that it will occur divided by the probability that it will not occur.  So, we express the odds of picking class $y$ as
$$
\frac{P(y=1)}{1-P(y=1)} = \frac{P(y = 1)}{P(y = 0)}
$$
In a binary situation,  $P(y =1) = 0.75$, then the odds of the class 1 is $\frac{0.75}{1-.75}=\frac{.75}{.25} = 3$.  So there is a 3 to 1 chance of class 1. This is a monotonic transformation that preserves the ordering of the probabilities but maps them from $[0,1]$ to $[0,\infty]$, the reverse of what the logistic function does. Assuming that $P(y=1)=p=\sigma(\mathbf{w}\cdot\mathbf{x}+b)$. Then $P(y=0)=1-p=1-\sigma(\mathbf{w}\cdot\mathbf{x}+b)$.  We can write this more compactly as 
$$
p^y(1-p)^{1-y}=y\sigma(\mathbf{w}\cdot\mathbf{x}+b)^y(1-\sigma(\mathbf{w}\cdot\mathbf{x}+b))^{1-y}.
$$

Taking the logarithm yields

$$
y\log p + (1-y)\log(1-p)= y\log\sigma(\mathbf{w}\cdot\mathbf{x}+b) + (1-y)(1- \log\sigma(\mathbf{w}\cdot\mathbf{x}+b)),
$$
which is, in fact, our **log likelihood** of a correct answer.   We can sum this up over all of the data to measure ,
$$
\begin{align}
\mathscr{L}(\mathbf{x})=&\sum_{(\mathbf{x},y)\in D} [y\log p + (1-y)\log(1-p)]\\
=& \sum_{(\mathbf{x},y)\in D} [y\log\sigma(\mathbf{w}\cdot\mathbf{x}) + (1-y)(1- \log\sigma(\mathbf{w}\cdot\mathbf{x}+b))],
\end{align}
$$
which is our objective function, the function we're trying to optimize.  The **loss function** for logistic regression is just the negative of this value, the **negative log likelihood**. 



There's another way of approaching this. If we take the log of the odds ratio, we get the **log likelihood** or **log odds**
$$
\log\frac{p}{1-p}=\log p-\log (1-p),
$$


which has a number of useful informatic theoretic interpretations, which is of the same form of our logistic loss function.  Incidentally, this is the logit function, another monotonic transformation.   Assume that the logistic regression is attempting to maximize the log odds ratio by searching for weights $\textbf{w}$. Then we can use algebra.
$$
\begin{align}
\ln\frac{p}{1-p}&=\mathbf{w}\cdot\mathbf{x}+b.\\
 \frac{p}{1-p} &= e^{\mathbf{w}\cdot\mathbf{x}+b} \\
 p &= (1-p)e^{\mathbf{w}\cdot\mathbf{x}+b} \\
 p &= e^{\mathbf{w}\cdot\mathbf{x}+b} -
pe^{\mathbf{w}\cdot\mathbf{x}+b}\\
 p + pe^{\mathbf{w}\cdot\mathbf{x}+b} &= e^{\mathbf{w}\cdot\mathbf{x}+b} \\
p(1+e^{\mathbf{w}\cdot\mathbf{x}+b}) &= e^{\mathbf{w}\cdot\mathbf{x}+b}\\
 p&=\frac{e^{\mathbf{w}\cdot\mathbf{x}+b}}{1+e^{\mathbf{w}\cdot\mathbf{x}+b}}\\ 
p&= \frac{1}{1+e^{-(\mathbf{w}\cdot\mathbf{x}+b)}}

 \end{align}
$$


From this perspective, the logistic function may not seem so arbitrary.

## Optimization

Machine learning is largely about optimizing some loss function or **objective function** according to some metric.  For logistic regression, it's the logistic loss.  If we could derive an analytical solution through algebraic tricks for optimizing this function, we wouldn't need machine learning.  But with both logistic regression and perceptron, we're trying to approach the minimum of a loss function.  The logistic loss function is convex, meaning that, if graphed, there are no "hills."  As long as you are walking down the function, you'll keep walking down, and as long as you're walking up the function, you'll keep walking up.  You needn't worry about walking down the function for a while and then suddenly find yourself walking up.  Naturally, then, we just need to keep walking down, and fortunately, there's a mathematical method to ensure that tells us how to do this: the derivative, or, more specifically, the **gradient**.

### Review: The Derivative

Recall that a derivative is just a slope, or rate of change.  Given a line $y=mx+b$, if $m=\frac{\Delta y}{\Delta x}=3/4$, then this is the slope, and we know that we're doing up at a rate of three $y$ units for every four $x$ units.  If all we have is a graph, we can simply measure the line to calculate the slope.

![Logistic function graph. Source: Wikipedia Slope article](../../2020-compling/slides/images/Wiki_slope_in_2d.svg)

  This doesn't work for smooth curves, because the slope is constantly changing.  For these, we want to measure $\frac{\Delta y}{\Delta{x}}$ at a single point.  We can think of these as two points arbitrarily close together -- taking the slope after zooming in to a single point, or as taking the slope of a line tangent to this point.  We call this $\frac{dy}{dx}$ or $f'(x)$.  For simple functions, there's a simple rule for calculating derivatives.  For example, if $f(x)=x^2$, its derivative $f'(x)=2x$, which is a function that gives us the slope at $x$.  For functions of more than two dimensions, e.g., $f(x,y)$, we can differentiate along one dimension at a time, which gives of the rate of change along one particular dimension.  This is accomplished by keeping all of the variables constant except for the variable we're differentiating.  This is a partial derivative.  In a 3D plot, this corresponds to keeping, say, the values of $x$ and $y$ constant but varying $z$.  The numerical value of the slope for smooth curves is only valid at a specific point.  So, we can think of a partial derivative equation as giving us the slope across one dimension that can be anything (say, $x$) when we know the other coordinates (say, $y$ and $z$).  To estimate the slope across an arbitrary portion of the graph, we can chose two coordinates across the non-varying dimensions and average their slopes.

For a partial derivative of the function $f$ with respect to $x$, where $x$ varies, we can write $\frac{\partial f}{\partial{x}}$.  For the derivative with respect to $y$, we can write $\frac{\partial f}{\partial{y}}$.  Sometimes the $f$ is omitted, and we're left with $\frac{\partial}{\partial y}$.  For non-partial derivatives, we typically use $\frac{dy}{dx}$, and this cursive "d" for the differential (referring to $\frac{\Delta y}{\Delta x}$ ) is analogous, variously pronounced "dee," "del," "partial," etc.    The formal reading is "the partial derivative of $f$ with respect to $x$," but "dee $f$ over dee $x$" will do.

### The Gradient

The **gradient** is a vector of partial derivatives.  For a function $f$ , it is denoted by the nabla symbol $\nabla f$ and read "del $f$."  Given a function of $n$ variables and dimensions, its gradient is the vector of its partial derivatives with respect to each of its independent variables.  For example, if $f$ is a function of three variables, $x, y, z$, its gradient is
$$
\nabla f = 
\begin{bmatrix}
\frac{\partial f}{\partial x}, \frac{\partial f}{\partial y}, \frac{\partial f}{\partial z}
\end{bmatrix}.
$$

The gradient describes a direction within an $n$-dimensional space; the elements are just numbers -- slopes.  Each element in the vector describes one dimension of this direction. This direction is the **direction of steepest ascent**.  In other words, if you move in the direction of the gradient, you will climb the hill of the function fastest.  It is the optimal way to climb the curve.  It may not be obvious why this is.  One way of thinking about it is this: each partial derivative is the slope of the curve along a single dimension.  If we were only looking at function of one variable, say $f(x) = x^2$, what would be the fastest rate at which could ascend the curve? It would be the slope at that point. We couldn't possibly traverse this curve faster than its slope.  In a function of multiple variables, this is also true: we can't possibly ascend a function along one dimension faster than the slope of that dimension. 

Now, consider two dimensions $x$ and $y$. We can't ascend along $x$ faster than the slope of $x$; neither can we ascend along $y$ at a rate greater than the slope of $y$.  What is the maximum possible rate of ascent on the curve with dimensions $x$ and $y$? It would have to be bound by some combination of the slopes along both $x$ and $y$ dimensions.  But what combination specifically?  We want to take a step in the direction that's a result of moving in each dimension *proportional to its steepness*.  We want a kind of *weighted* merging of each composite direction based on how helpful the direction is for climbing this hill, where the movement in each dimension is scaled according to how helpful it us in helping us accomplish this.  The gradient can be thought of, conceptually, as a vector of "weights" for each dimension, which, when combined, give us a direction. Since these "weights" are exactly the steepness of the corresponding dimension (the maximum ascent in this direction), taken together, we have the direction of steepest ascent.   

![Gradient. Source: Wikipedia](../../2020-compling/slides/images/Gradient2.svg)

We can also conceptualize this in terms of an alternate definition of the gradient.


$$
\begin{align}
\nabla f &= \frac{\partial f}{\partial x}\mathbf{i}+\frac{\partial f}{\partial y}\mathbf{j}+\frac{\partial f}{\partial z}\mathbf{k}\\
&= \frac{\partial f}{\partial x}
\begin{bmatrix}           
1\\0\\0
\end{bmatrix}
+\frac{\partial f}{\partial y}\begin{bmatrix}           
0\\1\\0
\end{bmatrix}+\frac{\partial f}{\partial z}
\begin{bmatrix}           
0\\0\\1
\end{bmatrix}\\
&= 
\begin{bmatrix}           
\frac{\partial f}{\partial x}\\0\\0
\end{bmatrix}
+
\begin{bmatrix}           
0\\\frac{\partial f}{\partial y}\\0
\end{bmatrix}+
\begin{bmatrix}           
0\\0\\\frac{\partial f}{\partial z}
\end{bmatrix}\\
&= \begin{bmatrix}
\frac{\partial f}{\partial x}\\
\frac{\partial f}{\partial y}\\
\frac{\partial f}{\partial x}
\end{bmatrix}
\end{align}
$$
where $\mathbf{i},\mathbf{j},\mathbf{k}$ are the unit vectors -- vectors of length 1 -- for the $x,y,z$ axes, respectively.  Remember that when adding directional vectors, the resulting direction is a combination of the individual directions and magnitudes.  In this case, the unit vectors $\mathbf{i,j,k}$ each point along only a single dimension, the $x$, $y$, and $z$ axes. The partial derivatives are just scalar values -- numbers -- and indeed they are scaling the unit vectors (what I somewhat abusively called "weighting" earlier).  Through simple vector arithmetic, we recover the original gradient, this time represented as a column vector instead of a row vector.  

The magnitude or **Euclidean norm** (also called the Euclidean distance, 2-norm,  or $\ell ^2$ norm) of this gradient $|\nabla f|$ is the rate of change of this direction on the surface represented by the function $f$.  The gradient is just a vector, and like any other vector, it points in some direction.  Since the length of each element in the gradient is just the distance from the origin to its terminal point, we can just use a multi-variable generalization of the the distance formula for line segments (itself just an application of the Pythagorean theorem, $(\Delta x)^2 + (\Delta y)^2 = c^2$), as with any other vector, to find its distance.  It's easy to visualize this in two dimensions, since the line segment described by any two points is the hypotenuse of a triangle with its opposite and adjacent sides being the line segments of moving along only the $x$ and $y$ dimensions, respectively. 



Likewise, in three dimensions, we have three line segments (or vectors emanating from the origin, in this case) to consider, so we just add another parameter.  For two parameters, for example, the magnitude of the gradient is just the Euclidean distance formula we learned in grade school,
$$
|\nabla f(x,y)|= \sqrt{\left(\frac{\partial f}{\partial x}\right)^2+\left(\frac{\partial f}{\partial y}\right)^2}.
$$
For three dimensions, it is
$$
|\nabla f(x,y,z)|= \sqrt{\left(\frac{\partial f}{\partial x}\right)^2+\left(\frac{\partial f}{\partial y}\right)^2+\left(\frac{\partial f}{\partial z}\right)^2},
$$
and so on.

In general, a **norm** is a function that returns the size of a vector according to some definition.  The $\ell^2$-norm is a specific instance of an $\ell^p$ norm or $p$-norm.  Given a vector  $\mathbf{x} = [x_1, x_2, \cdots, x_n]$, its $\ell^p$ norm is defined as
$$
\|\mathbf{x}\|_p=\sqrt[p]{|x_1|^p+|x_2|^p+\cdots+|x_n|^p}.
$$

In this case, $p=2$, so we have a square root, or the Euclidean distance.

![L2 norm graph. Source: Wikipedia Euclidean distance article](../../2020-compling/slides/images/Euclidean_distance_3d.png)

### Gradient Descent

We want to minimize the loss function over the training data, which is equivalent to maximizing the probability of a correct answer on average.  Since we can't do this analytically, we need a numerical solution that approaches the optimum over time by iterating over the training data. That is, we want to find the parameters $\mathbf{w}$ that minimize the loss.  Every time we modify a parameter, we change our position along one dimension on the multi-dimensional surface corresponding to this loss function.  To minimize it, we must find its global minimum, or at least get close.  As long as we take a step in the direction that decreases the loss, we can be sure, since the loss function is convex, that we are making progress toward the global minimum and not just some local minimum, because local minima do not exist in convex functions.  Just as taking steps in the direction of the gradient will take us toward the local maximum, taking the negative gradient $-\nabla f$ will guarantee that we move in the direction of the global minimum.  We just need iteratively find the negative gradient of the loss function and take steps in the direction of the minimum by modifying the weights $\mathbf{w}$ according to it.

Fortunately, there is a closed-form equation for the gradient of the logistic loss function, and we have already seen it.  Recall that the logistic loss  for a single example is given by $-[y\log p + (1-y)\log(1-p)]=-[ y\log\sigma(\mathbf{w}\cdot\mathbf{x}+b) + (1-y)(1- \log\sigma(\mathbf{w}\cdot\mathbf{x}+b)]$, and our loss function is the *sum* of all of these losses.  Our update rule, which we've already discussed intuitively, is derived from the derivative of this function.  The derivation, which we will skip, is a straightforward application of the chain rule for derivatives and the fact that $\sigma\prime(x)=\sigma(x)(1-\sigma(x))$. Ultimately, we find that
$$
\begin{align}
\mathscr{L}\prime(\mathbf{x})&=\sum_{(x,y)\in D}(y-p)x_i\\
&=\sum_{(\mathbf{x},y)\in D}(y-\sigma(\mathbf{w}\cdot\mathbf{x+b}))x_i
\end{align}
$$
This should look familiar.  It is our update rule without the learning rate.  For a single example, we remove the summation, yielding
$$
(y-p)x_i
$$
This tells us  how we should change our weights.  We can scale the step size by adding a learning rate.  Since we change *all* of the weights according to this rule after making a prediction $p$, this learning rate $\eta$ must be the same for all weights, which brings us back to our update rule for a single example:
$$
\begin{align}
w_i &:= w_i +\eta(y-p)x_i\\
w_i &:= w_i + \eta \nabla f
\end{align}
$$
Therefore, our "intuitive" update rule is exactly the gradient, scaled by a learning rate. This is why this logistic regression optimization algorithm guarantees that we will converge toward the optimal weights if the step size is sufficiently small.  

### Stochastic Gradient Descent

Note that in the full version of gradient descent (from the perspective of minimizing the loss) or gradient ascent (from the perspective of maximizing the likelihood), we modify the weights only after calculating the loss over all of the data.  This means that, $N$ steps of gradient descent requires $N$ passes over *all* of the data.  This is very slow for large data, so no one does this.  Instead, we use **stochastic gradient descent** (SGD).  The word "stochastic" indicates that there is some randomness in the process.  

In stochastic gradient descent, instead of going over all of the data, we look at one example at a time and modify all of the weights based on this single example.  These examples are theoretically sampled at random, so, over time, we still converge at the minimum.  This is the algorithm I've described here. 

It's also possible to use **mini-batching**, where instead of updating based on the loss on one example, we update based on the average loss of $n$ examples.

As we've seen from the simple pseudocode, it's possible to write a fully-functional version of logistic regression with SGD without understanding any of this, but grasping these fundamental concepts is important for understanding neural networks.

