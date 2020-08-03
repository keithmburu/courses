# Training and Prediction

Fundamental in modern machine learning approaches to computational linguistics is **prediction**.  Given some data---say, a sentence---we would like to predict something about them.  As a simple example, suppose that we have a collection of product reviews and we would like to predict based on the text of the reviews whether the review is positive or negative.  This is a **classification** task, a fundamental task in machine learning.

For simplicity, let's assume that we have a corpus of reviews in which each review is labeled as belonging to one of two **classes** or **labels**: positive (P) or negative (N).  This is a **binary classification problem** because we only have two labels.  Given only the text of an arbitrary review, with the label hidden or unknown, we want to be able to classify it as P or N based purely on the text of the review.  We need a set of **features** that indicate whether the review is positive or negative.  For now, we'll use the words in the text as our bag-of-words features.  We want to **train** a model that can predict on its own.

## Splitting the Data

Let's suppose we have a modest 1,000  labeled reviews.  We'll set aside 10% of them as our **test set** to see how well we can can predict and another 10% as our **development set** (dev set), which we'll use for testing and tweaking our model while we're working on it.  The remaining 80% will be our **training set**, which we'll use for algorithmically creating our model.  Before spliting these data into subsets, we'll shuffle the examples to increase the likelihood that the three sets are sufficiently representative of the entire dataset.  Otherwise, we might train a more biased model, if the unrandomized data exhibit a pattern.  For example, if the positive reviews are in the first half of the dataset file and the negative reviews are in the second half, but we use the last 20% for testing, we will only test on negative reviews and we will train on significantly more positive reviews than negative reviews.  Usually, we want to have an equal nubmer of each class in at least the test and development sets if at all possible, though there may be situations when it's preferable for the data to reflect an assumed distribution of the data in reality.  

In general, we should **not** look at our test set.  Our goal is **generalization** from our training data to unseen data, and evaluation on the test set should show evidence of this generalization.  If we look at the test data, it's cheating, because the data are no longer unseen.  It's possible, even if unconsciously, to explicitly engineer a model that will exploit features in the test set, artificially driving up accuracy and creating the illusion of a generalizable model.  

There is another evaluation method called **k-fold cross-validation**, whereby we split the data into $k$ random subsets several times, training on $k-1$ of them and testing on the remaining one.  This shows that the model generalizes over all of the data.  A related alternative is known as **leave one out**  (LOO) evaluation, whereby the model goes through many rounds of training on all of the data except one example, on which it is tested.  This is a special case of cross-validation where the $k$ is the number of examples in the data.  In both k-fold cross-validation and LOO, the objective is the same: to show that the trained model generalizes beyond the examples on which it is trained. LOO is particularly useful when we have a paucity of data and creating dedicated data splits results in an unacceptable loss of perfectly good training data.

## Training

Training, as described in the previous section, is the process of making iterative changes to a model's parameters to increase the efficacy of a model. (What "efficacy" means exactly may vary by task and situation.) We'll assume for the moment that our goal is **accuracy**, the percentage of the predictions that are correct:
$$
\text{accuracy} = \frac{\text{correct predictions}}{\text{all predictions}}.
$$


For now, we'll consider the **supervised** approach.  **Supervised learning** uses labeled examples for training and testing.  We assume that the labels, which may have been provided by experts or mechanically, are the correct ones. The learning is "supervised" by these labels, known as the **gold standard** labels.  By looking at these examples, we hope to iteratively improve the model during the training phase. If there are discernable patterns in the data, and our learning algorithm is robust enough to discern them -- more on this in later chapters -- the model will improve in its ability to predict the labels in the training data.  This is also called **fitting** the data, for reasons that will become clear shortly. 

## The Perceptron

To concretize the concepts of training and testing, we'll use the perceptron.  The **perceptron** is one of the earliest machine learning algorithms, and it is fundamental to understanding modern machine learning.  It also happens to be elegant and simple, requiring only basic algebra and some geometric intuition to understand.  The perceptron consists of two algorithms: one for learning and another for prediction based on what has been learned.

### Geometric Intuition

#### Geometric Interpretation of Hyperplanes

We know that $y=mx+b$ represents a line in two-dimensional real number space -- what mathematicians call $\mathbb{R}^2$ ("R two" ) -- and the $m$ represents the slope of the line, while $b$ is the $y$-intercept -- how much the line is shifted up or down.  Notationally, we'll use $w$ instead of $m$, which is only used in high school algebra textbooks, giving us $y=wx+b$.  In linear algebra and statistics parlance, the $w$ coefficient is known as a **weight**.  It "weights" the $x$ term: the higher it is, the more weight $x$ has when determining the final value of $y$.  If $w$ is negative, then $x$ will push the push $y$ down; if it's $very$ negative, $x$ will push $y$ down quite a lot.

If we don't mind going beyond two dimensions -- and we don't -- we can have more $x$ terms and corresponding $w$ terms.  For example,
$$
y=w_1x_1+w_2x_2+b.
$$
This equation now represents a 2D plane in $\mathbb{R}^3$.  How much influence $x_1$ will have on the value of $y$ depends on the *magnitude* of $w_1$, and the influence that $x_2$ has on $y$ entirely depends on $w_2$. The direction of the influence of a particular weight depends on it sign.  A weight of 0 has no influence on a classification.  We can in principle have as many $x$ terms and corresponding $w$ terms as we like.  Our formula above is  a dot product plus an extra term $b$.  We can therefore rewrite this as
$$
\begin{align}
y = h_\mathbf{w}(\mathbf{x}) &= w_1x_1+w_2x_2+\cdots+ w_nx_n + b \\
 & = \sum_{i=1}^n w_i x_i + b \\
 &= \mathbf{w}\cdot \mathbf{x} + b
\label{eq:percptron_dot}
\end{align}
$$
In some texts, the dot may be omitted, in which case we should write $\mathbb{w}^T\mathbb{x}$ to be notationally consistent, as only row and column vectors can be multiplied with each other. The transpose operation converts the row vector to a column vector, making multiplication possible.  It's also common in machine learning to use $h(x)$ to describe the function used to make predictions. The $h$ here is for "hypothesis."

This equation represents an $n-1$-dimensional plane in $\mathbb{R}^n$. If we're using a bag of words model, each $x$ term represents a word, and each $w$ term represents for the weight of the word.  The perceptron algorithm $learns$ these weights (and the $b$ term, known as the **bias**) by looking at examples.  Each example is a point in $\mathbb{R}^n$. The data which fall above the line (or plane, known as a **hyperplane**) are classified as the positive class; the examples that fall below it are the negative class.  The learning algorithm sets the weights to determine the optimal hyperplane for classifying the data -- that is, for determining which examples should be below or above the line.  The original perceptron algorithm only supports two labels.

#### Geometric Intuition of Data

Suppose that we use a bag-of-words representation of our data. That is, for each example, we reduce the text to a list of words that appear in the example.  We can then assign a vector to each $x_i$.  If our entire dataset -- the aggregation of all of our examples -- has 10 words in total, we'll need a 10-dimensional vector to represent all of the data in a bag-of-words model.  If word $i$ appears in the example, then $x_i$ is 1; otherwise $x_i$ is $0$.  Given a vocabulary size of $|V$|, we always need at least $|V|$ $x$ terms to represent them.  Every example is reduced to its **feature vector**. Though some examples have more words than others, every example is the same length. For example, if in example 1 we have words 3, 5, and 9, the feature vector is $[0,0,1,0,1,0,0,0,1,0]$ , where elements 3, 5, and 9 are 1 and the rest 0. If we stack all of our feature vectors on top of each other, we have a matrix, $W$ with a height equal to the number of examples in the training data. In our $\mathbb{R}^n$ space, each of these feature vectors represents a point in the space, and the hyperplane for which the algorithm is searching will divide the positive and negative examples on different sides of the plane, if possible. (Some data are inherently inseparable by a hyperplane, but if the data are **linearly separable**, the perceptron *will* find a separating hyperplane).   

Those familiar with physics may balk at the notion that vectors and points are the same.  It is true that we can also interpret these real numbered tuples as directional vectors emanating from the origin of the space.  By this interpretation, the dot product measures the cosine similarity between $\mathbf{w}$ and $\mathbf{x}$. There is a correspondence between the two interpretations; they are equivalent. But we will use the abstraction of points and planes.

### Prediction Algorithm

Prediction in the perceptron algorithm is based solely on the dot product.
$$
a =\sum_{i=1}^n w_i x_i + b
$$
This is the same formula from before, but we've  replaced the $y$ with an $a$.  This is called the **activation**, by analogy to a physical neuron in the brain.  By convention, $\mathbf{x}$ is used for the example's feature vector and $y\in\{-1,1\}$ is used for the *correct* label.  A single example is an ordered pair $(\mathbf{x},y)\in D$, where $D$ is set of all examples in the training data.   Since we don't *know* beforehand that this is the correct prediction, it's the activation.  Given an example, if the model works as intended, the *sign* ($+$ or $-$) of the activation $a$ will be the same sign as $y$.    When making the prediction, we don't care about the magnitude of $a$, as long as its sign is correct.  If $a < 0$, we'll predict the negative class; otherwise, we'll predict the positive class.

```pseudocode
function predict(x)
    return sign(dot(w,x) + b)
```



### Perceptron Learning Algorithm

The learning algorithm sets the weights $\mathbf{w}$ in Equation $\ref{perceptron_dot}$.  If $y\geq0$, we predict the positive class; otherwise, we predict the negative class.  The learning algorithm uses error-based learning to set the weights.  On the each example in the training data, the algorithm makes a prediction based on the current state of the model (the current values of the weights).  If the prediction is wrong, *all* of the weights are adjusted so that the features used to make the incorrect prediction will push the prediction in the direction of the other label on the next training example, including the bias term $b$, which still serves as the intercept, shifting the hyperplane up or down.  The bias will reflect the distribution of labels in the training data. Sometimes, the bias term is written as $w_0$ with a corresponding "fake" $x_0$ value that is always 1, since it is updated on every iteration with the same rule as the other weights. Fundamentally, most machine learning algorithms "learn" by making these incremental adjustments over many examples and/or iterations.

```pseudocode
1.  Initialize weights vector w to random numbers in [0, 1].
2.  for each example (x, y) in D:
        prediction = predict(x)
        if not sign(y) == sign(prediction):
            for each w in weights:
                w = w + y * x
                b = b + y * x
```

If the prediction was correct, we do nothing.  We only modify weights when the algorithm makes a mistake, at which point we modify the weights corresponding to the features/words that appear in the example so that they're either more positive or negative, whichever is closer to the true answer.  This means that if, in the next example, we see any of the words again that appeared in the example that the model classified incorrectly, these weights will be closer to the correct class $y$.  As the model repeats this **update rule** over all of the training data, perhaps for multiple iterations, we eventually quiesce at weights that, on average, give us the best fit we can hope for with a linear hyperplane and this feature representation. (Some data cannot be separated by a hyperplane.)  

The update rule is defined as
$$
w_i := w_i + yx_i.
$$
If word $x$ doesn't appear, its value is 0, leaving its corresponding weight unchanged.

There is no absolute stopping criteria. We want the algorithm to stop when it is no longer learning. This may manifest itself as a lack of improvement in training data accuracy or by the weights vasillating between the same values for an extended number of iterations.

Recall that we're using **binary features** in this model that can only be $1$ or $0$.  If a word appears, the corresponding feature $x_i$ is 1; otherwise, it is 0.  The algorithm, as written, can therefore only modify weights by 1 unit at a time.  This limits the kinds of hyperplanes that the model can find, since all of the elements in the weight vector $\mathbf{w}$ will be integers.  We say that this model has a **learning rate** or **step size** of 1 because each weight value changes in increments of 1.  We can change this by adding a **hyperparameter** -- a parameter that isn't learned but set in advance -- which we'll call $\eta$ (eta). We'll replace $w := w+yx$ with and an upate rule that allows us to scale the learning rate:
$$
w_i := w_i + \eta yx_i.
$$
he same for the bias term. Clearly, $\eta$ must be positive. The smaller $\eta$, the tighter the potential fit to the training data, since the hyperplane corresponding to the weights will be adjusted at increments less than 1 across each dimension.  Training will take longer, however, since the steps will be smaller, and we risk **overfitting.**  This occurs when the model fits the training data precisely but is unable to generalize to unseen data.  This algorithm is searching for a function to fit the data, but if it fits the training data too exactly, it has, in a sense, memorized that data rather than discovered general patterns that work for all examples.  Using too little data also leads to overfitting, since there isn't enough data to learn general patterns.  This is why, in general, more data leads to better-performing models, though there still exists the danger of learning idiosyncrasies of a particular dataset.  This is a fundamental problem in machine learning not easily overcome.

Perceptron is the simplest **linear model**.  It's a linear model because the model consists of a **linear combination** $w_1 x_1 + w_2 x_2 + \cdots + w_n x_n$. 

#### Learning Error 

There are several ways of thinking about "error" in machine learning.  Perhaps the most obvious way is in terms of accuracy.  That is, we can think of the "error rate" as the complement of the accuracy, $1-\text{accuracy} = \frac{\text{incorrect guesses}}{\text{all guesses}}.$

But the online learning algorithm is not privy to the accuracy of the prediction; it considers one example at a time.  In machine learning, when we talk about **error**, we usually mean learning error, which is just the difference between the correct answer and the prediction.  To get a sense of learning error, consider a single example.  The error on a single example is the difference between the correct answer and the predicted answer, but usually when we refer to error, we're interested in the mean of all such errors across all of the data. Let's consider the error for the perceptron. For perceptron, if the correct answer is the positive class $y=1$ and the activation $a=-3$, then the error on this example is $1-(-3) = 4$.  

Usually, in applied machine learning, as opposed to statistics, we're more concerned with the related notion of **loss** than with error, and belaboring the distinction may seem a bit pedantic.  Loss is crucial to error based learning.  The **loss** determines how much the weights will change based on the predicted answer during training, as determined by some function, called a **loss function**.  Naturally, there is typically a straightforward relationship between the loss function and the error. To illustrate, let's briefly consider the loss function for the perceptron. 

In English, the perceptron loss is 0 if the answer is correct and the $y-p$ otherwise.
$$
\label{perceptron_loss}
\begin{align}
\mathscr{L}(h_\mathbf{w}(\mathbf{x}),y) = 
\mathscr{L}(p,y) &= 
\begin{cases}
y-p & \text{if }  yp > 0 \\
0 & \text{otherwise} 
\end{cases}\\
  &= \text{max}(0, 1-yp)
  \end{align}
$$


Note that $yp$ is positive if and only if both the correct class $y$ and the predicted value $p$ have same sign. This corresponds to the fact that the perceptron incurs no loss unless its guess is incorrect (though there is technically error).  The loss function is known as **hinge loss**, because its graph takes the shape of as hinge.	

We'll further discuss losses in the context of logistic regression, when we'll see that It turns out that these changes based on the weights are the derivative of the loss function.

