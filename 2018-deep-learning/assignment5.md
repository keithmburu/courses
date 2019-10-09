CS Seminar in Deep Computer Vision and Language

Ursinus College

Alvin Grissom II

# Asignment 5: Machine Translation



Our assignments up until now have used Keras as an introductory framework, but there are several other popular frameworks available, including TensorFlow, PyTorch, DyNet, Deeplearning4j, and Chainer.  For this exercise, we'll use a neural machine translation called Sockeye.  If you like, you may use another package, such as NMT-Keras, OpenNMT, or lower-level code in some deep learning library.  

We've spent a bit of time discussing sequence-to-sequence models and various extensions to them.  In this assignment, you'll get to play with them.  Due to our limited number of GPU machines, you may work with a partner or two on this project, but if you do, you must clearly describe what each person did in your report.  You will likely not finish in time if you attempt to use a CPU.   It is, however, reasonable to use a CPU for rapid prototyping on small datasets.



## Part 1: Setup

### Install Sockeye

Note that if you're using one of our GPU-equipped machines, Sockeye is already installed and set up to use the GPU.

If your system is arleady set up to use CUDA with your GPU, you'll need to install sockeye and the MXNet backend.  This guide assumes you're using CUDA 9.0.  If you're using 8.0, 9.1 or 9.2, you'll have to change the first command accordingly.  If you're installing the CPU version, you can skip the CUDA version of mxnet.  If you don't remember which version of CUDA you're using, you can check with `nvcc --version`

```shell
pip install mxnet-cu90mkl
pip install sockeye --no-deps
pip install mxboard
pip install sacrebleu

```

### Download and Preprocess Data

For this guide, we'll be using Shakespeare data.  Our bitext will consist of the original Shakespeare and a version in modern English.  We'll train a model to "translate" from modern English to Shakespearean English.  If you'd like to use some other languages, you may do so, as long as you clear it with me.  

If using a word-based model, you'll want to ensure that punctuation is not considered part of the word, and you may want to lower case everything.

Fortunately, the data are already available for us to use here: https://github.com/tokestermw/tensorflow-shakespeare, in the `data` subdirectory.

You'll need to combine these files, preferably shuffle them, and split them into train, test, and dev sets.  You might find the Unix utilities `shuf` (or `gshuf`), `head`, `cat`, and `tail` useful for this purpose, but you can also use scikit-learn (**recommended**) or write your own script following some [basic guidelines](https://cs230-stanford.github.io/train-dev-test-split.html): 

Each pair of files has the same sentence in two different languages (in this case, two different versions of English) on the same line.  



## Part 2: Training a Translation Model

Training takes a long time.  I do not recommend attempting to complete this assignment without using a GPU.

Sockeye provides implementations of several popular translation model architectures, including RNN, CNN, and Transformer-based architectures.  It can be run from the command line.

***

**For those logging in remotely:**

When you log out of a remote machine, everything you're running will stop, unless you take measures to prevent this.  One way is to use `nohup`, but I recommend using **tmux**, a terminal multiplexer. tmux will continue running your programs even if you lose your connection or log out.  It allows you to pick up exactly where you left off.  To run it, when you log in to a remote machine, simply type `tmux`.

If you lose your connection, simply log back in and type `tmux attach`.  You will return to exactly where you left off.  tmux also supports a number of other features, such as multiple terminals.

See this article for a basic introduction: [https://medium.com/actualize-network/a-minimalist-guide-to-tmux-13675fb160fa](https://medium.com/actualize-network/a-minimalist-guide-to-tmux-13675fb160fa)

***

I'm providing a basic script for training a model here:

```shell
DATA_DIR=data
MODEL_DIR=models
rm -Rf $MODEL_DIR
mkdir -p $MODEL_DIR
python3 -m sockeye.train\
                       --source $DATA_DIR/shakespeare/sparknotes/merged/antony-and-cleopatra_modern.snt.aligned \
                       --target $DATA_DIR/shakespeare/sparknotes/merged/antony-and-cleopatra_original.snt.aligned \
                       --encoder cnn \
                       --decoder cnn \
                       --rnn-num-hidden 2 \
                       --validation-source $DATA_DIR/shakespeare/sparknotes/merged/antony-and-cleopatra_modern.snt.aligned \
                       --validation-target $DATA_DIR/shakespeare/sparknotes/merged/antony-and-cleopatra_original.snt.aligned \
                       --output $MODEL_DIR \
                       --decode-and-evaluate 500


```

You can save it in a file called `train.sh` (or whatever you like) and run:
```shell
sh train.sh
```
Unfortunately, using a Transformer for both the encoder and the decoder causes our 6GB GTX 1060 to run out of memory.  If you have GPU with more RAM, however, you can use two Transformers.  If you like you can monitor GPU memory usage with:

```shell
watch -n 0.5 nvidia-smi
```

This is also useful for checking if someone else is using the GPU.  The `top` command will monitor CPU usage.

In the script above, we use two-layer RNNs for both the encoder and the decoder,  but you can mix and match various kinds of models and see how it does.  You can set the attention type, dropout, the embedding dimension, and so on, by adding options.

 In a real experiment, you should to train on a much larger set of data, but here I'm engaging in the terrible practice of using different books for training and validation.  (Don't do this.)

After each epoch, the model reports the [perplexity](https://en.wikipedia.org/wiki/Perplexity) on the validation data.  Perplexity is often used to measure the quality of monolingual language generation tasks,  but [BLEU](https://en.wikipedia.org/wiki/BLEU) is more common for machine translation.

## Part 3: Translation and Evaluation

Once you have trained the model, you want to translate (**decode**) some new sentences on your test data.

Sockeye provides an easy command for translating.

```shell
python3 -m sockeye.translate -m models
```

Here, `models` is assumed to be the directory containing our trained model.

Once the model has finished loading, you can try typing some text and pressing [ENTER], such as "Hello ."

We can redirect stdout to a file in the normal way:

```shell
python3 -m sockeye.translate -m models > my_translations.txt
```



Likewise, we can generate a number of sentences on the fly by redirecting both stdin and stdout.

```shell
python3 -m sockeye.translate -m models < my_test_data.txt > my_translations.txt
```
Of course, you must change `my_test_data.txt` to whatever source sentences you're using. 

You can use [sacrebleu](https://github.com/awslabs/sockeye/tree/master/sockeye_contrib/sacrebleu) (or any other BLEU script) to evaluate the quality of the translations.  You can also visualize the attention weights in the model. See https://aws.amazon.com/blogs/machine-learning/train-neural-machine-translation-models-with-sockeye/.

Because Sockeye saves the best parameters on every epoch, you don't have to wait for it to finish training to start testing it.



## Assignment

Write a report using the NIPS template which addresses the following:

1. Describe what you're doing, the dataset, your motivation. (Introduction)
2. Describe any preprocessing on the data you used and why you did it.
3. Train and test your model with at least one architecture that performs reasonably.  Report all of the relevant details of the model and the perplexity and BLEU scores on the models that you used.
4. Translate some other text (e.g., news or novel text) with your model and include some interesting examples of what the model does in your analysis.  You might consider using [Project Gutenburg](https://www.gutenberg.org) as a source.
5. Plot at least one attention graph (preferably more) that shows something interesting.  Why does the model seem to focus on certain parts?  Does it make sense?
6. What is the takeaway from these experiments? (Conclusion)
7. Bonus: Talk to your classmates and compare notes.  Include their results in your analysis.
