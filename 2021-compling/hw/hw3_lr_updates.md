# Homework 3: Logistic Regression Updates (50 points)

Due:  October 8, 4:00pm.  (Will accept with no penalty until 11:59pm.)

As stated in the syllabus, all written work must be submitted in Markdown (with LaTeX for formulas) or LaTeX.  Show all of your work.



1. (15 points) Suppose you have a logistic regression model with the following feature weights, where the learning rate $\eta=0.01$ and the classes $Y=\{1,0\}$, where 1 represents $\texttt{fiction}$ and 0 is $\texttt{nonfiction}$, and the current state of the model is:

   $\mathbf{w}=[0, -3.5, 5.0, -3.1, 1.0]$, and $b=0$ for features $[\texttt{quick},\texttt{jumped},\texttt{lazy},\texttt{dog},\texttt{fox}]$.

   Continue training the model on the following examples, in this order.  Show all work, including the calculations for the prediction step and updating the weights. You do not need to use log probabilities for this exercise. The following training examples have the class to the left of the pipe and the text to the right.

   i. `fiction| the quick brown fox jumped`

   ii. `nonfiction | the computer is quick`

   iii. `nonfiction | cheating is unacceptable`

The following tables are provided to make providing the answers easier, but you must still show your work.

| weights      | $w_1$:quick | $w_2$:jump | $w_3$:lazy | $w_4$:dog | $w_5$:fox | $b$  |
| ------------ | ----------- | ---------- | ---------- | --------- | --------- | ---- |
| original     | 0           | -3.5       | 5.0        | -3.1      | 1.0       | 0    |
| update (i)   |             |            |            |           |           |      |
| update (ii)  |             |            |            |           |           |      |
| update (iii) |             |            |            |           |           |      |

| Features      | $x_1$:quick | $x_2$:jump | $x_3$:lazy | $x_4$:dog | $x_5$:fox |
| ------------- | ----------- | ---------- | ---------- | --------- | --------- |
| Eample (i)    |             |            |            | -         |           |
| Example (ii)  |             |            |            |           |           |
| Example (iii) |             |            |            |           |           |

2. (5 points) Starting with the same weights defined in Problem 1, perform update (i) with L2 regularization, with the regularizaton parameter $\lambda=0.001$.   (Different sources may use different variable names, such as $\alpha$ or $\mu$.). The regularized objective function adds $\lambda\sum_i w_i^2$ to the original objective function, pushing the weights closer to 0 and counteracting overfitting.  Taking its derivative, its update rule merely adds $2\lambda w_i$ to the original update rule.

3. (30 points) For the following, answer `true` or `false`.  If `false`, explain why.  Choose 5.

   i. Stochastic gradient descent achieves lower accuracy than gradient descent because it only modifies the weights based on one example at a time.

   ii. Word2Vec word embeddings are learned by training a logistic regression to predict nearby words with the context words in a window of text.

   iii. Minibatching updates the weights of a model changing the weights based on the median loss of the training examples in the batch.

   iv. A positive bias term in a perceptron or logistic regression ensures that if none of the example's features are represented by weights in the model, the model will predict the positive class.  

   v. Regularization helps to prevent overfitting by ensuring that only a handful of features become very large.

   vi. Discriminative classifiers model how the data are generated, while generative clssifiers calculate the probability of a class given the data.

4. (5 points).  Suppose we have two linear perceptrons, with  weights $\mathbf{w_1}=[0.25, 0.1, -2.5]$ and $\mathbf{w_2}=[0.2, -1, 3.0]$, and we send our features $\mathbf{x}=[1,0,1]$ into both of them simultaneously. The output $a_i$ of perceptron $i$ is then used as the input of a logistic regression with weights $\mathbf{k}=[0.25, -1$], one for each $a_i$ to be used as a feature.  Calculate the output $\hat{y}$ of this logistic regression.

