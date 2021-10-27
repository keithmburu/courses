# Lab 6: Playing with Neural Text Generation

**Warning: This lab may be time consuming if you don't use Keras with GPU acceleration enabled.**

You've used $n$-gram language models; now, we'll look at a simple neural language model for text generation.

Recently, there have been news stories about, for example, computers [writing Harry Potter chapters](http://www.bbc.co.uk/newsbeat/article/42348846/harry-potter-gets-a-weird-new-chapter-from-a-computer).  This can be accomplished with deep neural language models.  Recall that LSTMs are much better able to capture long-range dependencies than $n$-gram models, which can only use a history of $n-1$ words.

LSTMs predict the next word by repeatedly sampling from the next-word distribution, conditioned on the previous text context $c$, $P(w | c)$. In this code, there's a `sample()` function for sampling, which has a `temperature` parameter.  A version of the softmax function can use a temperature variable $\tau$, becoming:
$$
P_t(a) = \frac{\exp(q_t(a)/\tau)}{\sum_{i=1}^n\exp(q_t(i)/\tau)} \text{,}
$$


As $\tau \rightarrow \infty$, the probabilities become more uniform, yielding a greater average diversity of samples.  The `diversity` variable is passed into `sample`  and becomes the temperature.  A higher temperature means we're more likely to sample different possible next words; a lower temperature means we're more likely  to predict the argmax, giving us less diversity in language generation.

For this lab, we'll start with some Keras example code for generating text using a character-based neural language model.  See the code and an explanation here: https://keras.io/examples/generative/lstm_character_level_text_generation/  If you've cloned the Keras repository already, it's in the `examples` subdirectory.

If you haven't cloned the Keras repository yet, follow the following instructions:

1.  Clone the Github repository.

```bash
git clone https://github.com/keras-team/keras-io/
```

2. Change to the `generative` subdirectory inside of  `examples` .

```bash
cd examples/generative
```

3. Copy `lstm_character_level_text_generation.py `to someplace where you can easily access it.  For example:

```bash
cp lstm_character_level_text_generation.py ~/chargen.py
```

4.  Now you can edit the file.

```bash
cd ~/
emacs chargen.py
```

Note that when running this code, it is **highly recommended** that you ssh into a GPU machine; otherwise, it will be very slow.

```bash
ssh your_username@computername.cs.haverford.edu
```



## Assignment

Replace the default text file with one of more files of your choice -- you can just replace the URL with a corresponding one from Project Gutenberg -- and answer answer the following questions:

1.  What do you observe about the intermediate output during learning?
2. Try at least three different seeds at different temperatures.  What do you notice?
3. Explain why it prints out nonsense words.
4.  Explain why a character-level model would be able to print out sentences.
5.  Explain line 94 in the original code.
6.  Impressionistically, what do you notice about this model?



