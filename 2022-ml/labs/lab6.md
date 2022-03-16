# Lab 6: AdaBoost (10 points)

Conventional wisdom is that AdaBoost needs weak learners to perform well, but work by [Wyner et al. (2017)](https://www.jmlr.org/papers/volume18/15-240/15-240.pdf) argues that this isn't true. 

In this lab, you'll use AdaBoost on MNIST.  You'll use the scikit-learn implementation to examine the effect of decision tree depth and the number of decision trees on accuracy on MNIST.  I've provided [code](https://github.com/acgrissom/courses/blob/master/2022-ml/labs/code/lab6.py) to get you started.  Can your make it onto the [MNIST leaderboard](https://paperswithcode.com/sota/image-classification-on-mnist)?  

In your analysis, describe the trends you found and how they do or do not comport with known analyses of AdaBoost.  Use citations where necessary (preferably with BibTeX).

Hints:

1. You should, at a minimum try weak learners at least up to the number of features, but there is no need to stop there.
2. Be careful about confusing the maximum number of base learners with the actual number of base learners. 
3. Put your results in a LaTeX table or, better, in plots.  
4. While not required for this lab, the number of training examples may make a difference.  You can use cross-validation.

