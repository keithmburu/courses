# Lab 7: Feature Engineering Lite (10 points)

Note: if you want to try to use scikit-learn for this lab, you may.

In this lab, we will use Vowpal Wabbit, a highly optimized suite of machine learning tools.  **It is already installed on lab machines.**  The purpose of this lab is to give students practice transforming data and trying out features for the classifier, both of which are what machine learning engineers spend much of their time doing.

To install it on Debian-based Linux distributions, run:

```bash
sudo apt install vowpal-wabbit
```

On macOS:

```
brew install vowpal-wabbit
```



## Data Munging

You can run VW by typing 

```bash
vw
```

To see the help menu, type:

```bash
vw --help	
```

We'll do an experiment with a sentiment analysis dataset.  We'll predict whether the review is positive or negative from the text. Download the data here: https://ai.stanford.edu/~amaas/data/sentiment/

You can unzip it by using the `tar` command.

```bash
tar xvf [filename]
```

Look at the data; there are a lot of small files, one per example.  Let's combine them into something more manageable.  We can create a file called `combine.sh` to do this

```bash
for f in  train/pos/*.txt; do cat $f; echo; done > ./train_pos.txt
for f in train/neg/*.txt; do cat $f; echo; done > ./train_neg.txt

for f in test/pos/*.txt; do cat $f; echo; done > ./test_pos.txt
for f in test/neg/*.txt; do cat $f; echo; done  > ./test_neg.txt

```



We'll need to convert this data into a format that Vowpal Wabbit can read.

See https://github.com/VowpalWabbit/vowpal_wabbit/wiki/Input-format

We have two classes: 1 and -1. 

A single line in a simple VW formatted text file looks like this:

```
1 | these are my features
```

They can be more complex, but this is all we'll need for now.  The `1` is the class.  We then have a space, the pipe character, and space-separated text features.  Features with no explicit value given are assumed to have a value of 1.  Any feature not mentioned has a value of 0.  You do not need to prespecify features with VW.

A negative example would look like this:

```
-1 | now we have negative features
```

You need to convert the text training and test data you've downloaded into this format. I'm providing some code to help you combine the files.

Once you have your data in the correct format, you can send it to VW with input redirection.

```bash
vw < train.vw                                                                      
```

Since we're not using much data, this should take almost no time.  We can add some more options.

```bash
vw --final_regressor sentiment.model --kill_cache --loss_function logistic --passes 1 < train.vw                                                                                     
```

Now we're saving the model in a file called sentiment.model, we're deleting the cache between runs,and we're using logistic loss (for logistic regression).  Note: we should shuffle our data when training!  We can do this with `shuf` in Linux and `gshuf` (which you can install with Homebrew) on OS X.  

We can test on our test data with the following command:

```bash
vw --testonly -i sentiment.model --predictions predictions.txt --binary  < test.vw
```

This will feed our test data into our model and generate predictions.  We have to write our own program to calculate accuracy.  This loads our model `sentiment.model` from the previous step and outputs the predictions to `predictions.txt`.  

The option `link=logistic` tells us to use a [logistic link function](https://en.wikipedia.org/wiki/Generalized_linear_model#Logit_link_function) to generate probabilities for our linear classifier.  Otherwise, we just get the dot product.  To get just the classes {1, -1}, we can add `--binary`.

## Feature Engineering

1. Your baseline model should use binary features for single words (unigrams).  Note that for binary features, you can just use the word strings as features.   Be sure to lower case before extracting the features.

2. Try both binary features and word count features. For features with numerical values, you can separate the feature with a colon.  For example, if the unigram "kite" appears twice, you can use `kyte:2.0`.

3. Try adding bigrams to your features.  You can use NLTK to extract bigrams with with [nltk.util.ngrams](https://www.nltk.org/api/nltk.util.html) with n = 2 or nltk.util.bigrams.

   

Hints:

- Any feature that does not appear on a line is assumed to be 0 (not present).

- You may find the VW validator helpful: http://hunch.net/~vw/validate.html

- If you do multiple passes, be sure to kill your cache with 

  ```
  --kill_cache
  ```

   between runs. 

  

  

  - Also delete your model file between runs.

- Vowpal Wabbit does not report accuracy, but you can output the guesses for each line with `--predictions ./predictions.txt` and write a short script to calculate this by comparing it to the test file.

- Use `--oaa n` for more than two classes during training, where *n* is the number of classes.  You don't need that for this exercise.

- VW classes (for more than two classes) must be integers (starting at 1).

- You will have to convert the string names to integers.

- Be sure to use `-t` or `--testonly` during testing.  Otherwise, you will update the model while testing!

- Include the commands you used for your VW runs in a script called run.sh

- You can use the Unix command `shuf` (`gshuf` on macOS) to shuffle your files.

  - On macOS, type `brew install coreutils` to install `gshuf`.

  
