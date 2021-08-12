# Lab 5: Playing with Neural Text Generation

**Warning: This lab may be time consuming if you don't use Keras with GPU acceleration enabled.**

You've used $n$-gram language models; now, we'll look at a simple neural language model for text generation.

Recently, there have been news stories about, for example, computers [writing Harry Potter chapters](http://www.bbc.co.uk/newsbeat/article/42348846/harry-potter-gets-a-weird-new-chapter-from-a-computer).  This can be accomplished with deep neural language models.  Recall that LSTMs are much better able to capture long-range dependencies than $n$-gram models, which can only use a history of $n-1$ words.

Recall that this predicts the next word by repeatedly sampling from the next-word distribution, conditioned on the previous text context $c$, $P(w | c)$. In this code, there's a `sample()` function for sampling, which has a `temperature` parameter.  A version of the softmax function can use a temperature variable $\tau$, becoming:
$$
P_t(a) = \frac{\exp(q_t(a)/\tau)}{\sum_{i=1}^n\exp(q_t(i)/\tau)} \text{,}
$$


As $\tau \rightarrow \infty$, the probabilities become more uniform, yielding a greater average diversity of samples.  The `diversity` variable is passed into `sample`  and becomes the temperature.  A higher temperature means we're more likely to sample different possible next words; a lower temperature means we're almost certain to predict the argmax.

For this lab, we'll start with some Keras example code for generating text using a character-based neural language model.  See the code and an explanation here: https://keras.io/examples/generative/lstm_character_level_text_generation/  If you've cloned the Keras repository already, it's in the `examples` subdirectory.

## Assignment

Replace the default text file with one of more files of your choice -- you can just replace the URL with a corresponding one from Project Gutenberg -- and answer answer the following questions:

1.  What do you observe about the intermediate output during learning?
2. Try at least three different seeds at different temperatures.  What do you notice?
3. Write a short, impressionistic description of what you think the model is learning.





## 