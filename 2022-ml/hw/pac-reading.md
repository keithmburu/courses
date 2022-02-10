# Comprehension  Questions (ERM, PAC Learning)

5 points.  Due 2/14 before class.

1.  Describe the role $\epsilon$ and $\delta$ in the "probably" and "approximately" parts of the definition of PAC learning.
2.  Suppose we have a learner with high sample complexity.
3. T/F $\mathcal{D}$ is observable.  If false, explain.
4. What is the relationship between $\mathcal{H}$ and $\mathcal{C}$?
5. Suppose we have a non-linearly separable data set and a linear classifier.  The classifier does not find a separating hyperplane on the training data.  Does this imply that the learner is not a consistent learner?  Why or why not?
6.  Describe the difference between empirical risk and generalization error and how they relate to (any version of) formulas for each.
7. Why is ERM prone to overfitting?
8. Give an example of inductive bias.
9.  Suppose that we're using a dataset to predict the kind of flower based on petal size features, $\mathbf{x}$.  For simplicity, assume that we have only two possible flower types. The training examples are $S\sim\mathcal{D}$.    Suppose, though, that 2% of the time, flowers of one class have features that are more likely to belong to the other class, according to $\mathcal{D}$.  What is the name of this phenomenon?
10. For question (9), what is the Bayes error?