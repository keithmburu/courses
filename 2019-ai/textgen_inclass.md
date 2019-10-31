# In-class Exercise: Text Generation

You may have seen in the news examples of fictional books being generated automatically.  As we've discussed, language models can be used for generated, by modeling the probability of the next word given the previous context words.

For this  (fun) exercise, you will use the Keras example code, [lstm_text_generation.py](https://github.com/keras-team/keras/blob/master/examples/lstm_text_generation.py).  As we discussed in class, this code uses an LSTM-based neural network to generate the next character given its context. As is, it will download some uplifting text from Nietzsche and iteratively build a model from it.  There's a parameter called `temperature`, which determines how "creative" the model is with its guesses.

For this exercise, head to [Project Gutenberg](http://gutenberg.org) (or use the text from your previous exercise) to generate your text.  Modify that temperature and observe how it changes what's generated.  In addition, modify the `seed` text, which is the text used to generate the successive characters.  

This model is playing a deep learning version of the Shannon game of predicting the next character/word in a sequence, based on the previous text.