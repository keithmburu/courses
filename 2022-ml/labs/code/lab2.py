# Some of this code is taken from Ch. 3 of Hands-on Machine Learning by Geron.
import sys
import pandas as pd
assert sys.version_info >= (3, 5)


# Scikit-Learn ≥0.20 is required
import sklearn
assert sklearn.__version__ >= "0.20"

# Common imports
import numpy as np
import os
from sklearn.model_selection import cross_val_score
from sklearn.metrics import confusion_matrix
from sklearn.linear_model import LogisticRegression
from sklearn.linear_model import SGDClassifier
from sklearn.datasets import fetch_openml
from sklearn.naive_bayes import BernoulliNB
from sklearn.naive_bayes import MultinomialNB
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
import matplotlib as mpl
import matplotlib.pyplot as plt
mnist = fetch_openml('mnist_784', version=1, as_frame=False)
X, y = mnist["data"], mnist["target"]


# To plot pretty figures



mpl.rc('axes', labelsize=14)
mpl.rc('xtick', labelsize=12)
mpl.rc('ytick', labelsize=12)

# Where to save the figures
PROJECT_ROOT_DIR = "."
CHAPTER_ID = "classification"
IMAGES_PATH = os.path.join(PROJECT_ROOT_DIR, "images", CHAPTER_ID)
os.makedirs(IMAGES_PATH, exist_ok=True)

"""
This function runs multiple experiments on multiple classifiers with
the training data amounts determined by the list num_training_examples.
The results are then plotted.
"""
def run_experiments(X : list, y : list, classifiers : list, num_training_examples):
    X_train, X_test, y_train, y_test = X[:60000], X[60000:], y[:60000], y[60000:]
    print(num_training_examples)
    all_accuracies = pd.DataFrame()
    for clf in classifiers:
        classifier_name = type(clf).__name__
        clf_accuracies = []
        for num_training in num_training_examples:
            sys.stdout.write("Training " + classifier_name + " on " + str(num_training) + " examples\n")
            clf.fit(X_train[:num_training], y_train[:num_training])
            y_pred = clf.predict(X_test)
            accuracy = accuracy_score(y_pred, y_test)
            sys.stdout.write("Accuracy " + str(accuracy) + "\n")
            clf_accuracies.append(accuracy) # for this classifier
        print(clf_accuracies)
        all_accuracies = all_accuracies.append(pd.Series(clf_accuracies,
                                                         index=num_training_examples,
                                                         name=classifier_name)) # for all classifiers
        all_accuracies.transpose().plot(style='.-')
        #plot = sns.scatterplot(data=all_accuracies.transpose())
        print(all_accuracies.head())
    plt.show()
    plot_confusion_matrix(confusion_matrix(y_test, y_pred))


def plot_confusion_matrix(matrix):
    """If you prefer color and a colorbar"""
    fig = plt.figure(figsize=(8,8))
    ax = fig.add_subplot(111)
    cax = ax.matshow(matrix)
    fig.colorbar(cax)


def save_fig(fig_id, tight_layout=True, fig_extension="png", resolution=300):
    path = os.path.join(IMAGES_PATH, fig_id + "." + fig_extension)
    print("Saving figure", fig_id)
    if tight_layout:
        plt.tight_layout()
    plt.savefig(path, format=fig_extension, dpi=resolution)


if __name__ == "__main__":
    num_training_examples = [60000]
    classifiers = []
    lr = LogisticRegression(solver="saga", tol=0.1)
    gnb = GaussianNB()
    classifiers.append(lr)
    classifiers.append(gnb)

    run_experiments(X, y, classifiers, num_training_examples)
    plt.show()
    print("Done.")



#ovr_clf.predict([some_digit])
