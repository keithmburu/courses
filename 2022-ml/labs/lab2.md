CSCI H360 Machine Learning, Spring 2020

Prof. Alvin Grissom II

# Lab 2: Comparing  Classifier Performance

This provided code 

lab will use some material and [code](https://github.com/ageron/handson-ml2/blob/master/03_classification.ipynb) from Chapter 3.  For this lab, you will use several Naive Bayes classifiers and logistic regression on MNIST.  We will use scikit-learn's [Bernoulli Naive Bayes](https://scikit-learn.org/stable/modules/generated/sklearn.naive_bayes.BernoulliNB.html), [Gaussian Naive Bayes](https://scikit-learn.org/stable/modules/generated/sklearn.naive_bayes.GaussianNB.html), [logistic regression](https://scikit-learn.org/stable/modules/generated/sklearn.linear_model.LogisticRegression.html), and [ultinomial Naive Bayes](https://scikit-learn.org/stable/modules/generated/sklearn.naive_bayes.MultinomialNB.html) classifiers and compare their performance.

Based on the descriptions below, hypothesize which classifiers will work best.

## Lab Assignment

Download the [provided code](code/lab2.py).

For this lab, train and test these classifiers on the official train and test splits.   You've been provided with code to get you started.

Test the accuracy of each of the classifiers on increasing amounts of training data and plot the accurcy with the x-axis showing the amount of training data and the y-axes showing the accuracy.  Run the experiments on enough variations in training data to make a convincing argument about the effect of training data on classifier performance.  If you find that the plots are hard to read because the differences in accuracy are huge, you may want to drop the outlier classifier from the combined plot.

#### What to turn in

In your report, write address the following:

1. Your original hypothesis regarding the relative performance of the classifiers and why you held it.

2. Whether your hypothesis was correct.

3. Your analysis of the performance of each  of the classifiers as a function of training data, including a table or a plot of the accuracy as a function of the training data size.

4. Using the confusion matrices, any interesting observations regarding the effect of training data and algorithm choice on classifying specific digits.  Include images of confusion matrices that support your argument(s).

   



## Classifier Descriptions

You can find scikit-learn's descriptions of these classifiers here: https://scikit-learn.org/stable/modules/naive_bayes.html.

### Bernoulli Naive Bayes

Bernoulli Naive Bayes assumes that the data follow a Bernoulli distribution, i.e., features take only binary values. 

### Multinomial Naive Bayes

In multinomial Naive Bayes, the feature vector can contain integers -- for example, word counts or pixel values -- and the probability $P(X_i=x_j)$ is the probability that feature $i$ takes value $x_j$. The probability calculations are done with simple division (maximum likelihood).  

### Gaussian Naive Bayes

Gaussian Naive Bayes assumes that continuous features follow a Gaussian normal distribution and fits the model based on this assumption.

### Logistic regression

Logistic is a linear classifier which learns similarly to the linear perceptron but outputs probablities.  It is the subject of todays' lecture.

