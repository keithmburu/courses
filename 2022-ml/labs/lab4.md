# Lab 4: Exploring Kernel Support Vector Machines

For this lab, you will use support vector machines with to classify MNIST and examine the relationship between the hyperparameters, support vectors, and training data.  You will use [sklearn.svm.SVC](https://scikit-learn.org/stable/modules/generated/sklearn.svm.SVC.html).  This is a multiclass problem. `SVC` defaults to one-vs-one, but `LogisticRegression` does not.  Ensure that you specify which multi-class classification strategy you're using, and think carefully about which combinations you should use for meaningful comparisons Logistic regression can use either softmax (`multinomial`) or one-vs-rest (`ovr`) without a wrapper but changing the `multi_class` variable in the constructor.  Wrap the `LogisticRegression` classifier in the `OneVsOneClassifier` constructor if you prefer to use one-vs-one.

For example:

```python
from sklearn.multiclass import OneVsOneClassifier
lr = LogisticRegression(solver="saga", penalty='l2')
lr = OneVsOneClassifier(lr)
```

or

```python
lr = LogisticRegression(solver="saga", penalty='l2', multi_class='ovr')
```



See the [sklearn.linear_model.LogisticRegression](https://scikit-learn.org/stable/modules/generated/sklearn.linear_model.LogisticRegression.html) documentation for more details.

For `SVC`, you can change the `decision_function_shape` parameter to `'ovo'` or `'ovr'` for one-vs-all or one-vs-rest, respectively. No wrappers are required.  The accuracy is about the same for either option as the training data increases.  You're not required to try different multi-class classification schemes, but it might be interesting for lower numbers of training examples.)

Compare the accuracy of the SVM models to your best logistic regression classifier, with at least four kernels for SVMs: RBF, polynomial (degree 2 and at least one other degree), linear (none), and sigmoid.   Show the effect as training data size increases.  Chose a small enough training data size (perhaps 25 examples) to start.  (Hint: in the beginning, use small increases in training examples, and then space them out at larger intervals).  You may want to tweak the regularization parameter `C`.

In your analysis document, describe the results.  Which classifier works best for different amounts of training data?  Why do you think this is? Are there any theoretical reasons to support this?  Are there empirical reasons to support this?
