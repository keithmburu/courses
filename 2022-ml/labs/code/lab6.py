import matplotlib.pyplot as plt

from sklearn.datasets import make_gaussian_quantiles
from sklearn.ensemble import AdaBoostClassifier
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeClassifier
from sklearn.linear_model import LogisticRegression
from collections import defaultdict


"""
# code for generating artificial data
X, y = make_gaussian_quantiles(
    n_samples=13000, n_features=10, n_classes=3, random_state=1
)

n_split = 3000

X_train, X_test = X[:n_split], X[n_split:]
y_train, y_test = y[:n_split], y[n_split:]
"""
from sklearn import datasets
from sklearn.model_selection import train_test_split
digits = datasets.load_digits()
n_samples = len(digits.images)
data = digits.images.reshape((n_samples, -1)) #flatten
X_train, X_test, y_train, y_test = train_test_split(
    data, digits.target, test_size=0.1, shuffle=True
)

depths = [1]
estimators = [x for x in range(1,11) if x % 10 == 0]
accuracies = []
classifiers = defaultdict(dict)
scores = defaultdict(dict)
for depth in depths:
    for max_estimators in estimators:
        classifier = AdaBoostClassifier(
            DecisionTreeClassifier(max_depth=depth),
            n_estimators=max_estimators,
            learning_rate=1.0,
            algorithm="SAMME")
        classifier.fit(X_train, y_train)
        classifiers[depth][max_estimators] = classifier
        scores[depth][max_estimators] = classifier.score(X_test, y_test)

    


print("Num estimators", str(len(classifiers[1][10].estimators_)))
# Algorithm will stop adding new estimators if training data is fit perfectly,
# So, max_estimators may not be the actual number of estimators.
print(scores[1][10])



