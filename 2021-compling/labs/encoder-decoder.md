# Encoder-Decoder Lab

For this lab, we will again use some Keras’s example starter code for LSTM-based encoder-decoder models here: https://keras.io/examples/nlp/lstm_seq2seq/.  The purpose of this lab if to give students some intuition some experience training a simple neural machine trnaslation model

Read the tutorial and run the code, either on a Haverford GPU machine or Google Collab.  Recall that the encoder LSTM (or GRU) creates a contextualized representation of the input tokens.  The following line returns the hidden state and a cell state, returned by the following line:

```python
encoder_outputs, state_h, state_c = encoder(encoder_inputs)
encoder_states = [state_h, state_c]
```

The variable `state_h` holds the final hidden state, after all characters have been read.  The initial hidden state of the decoder is then set to the final hidden state of the encoder.

```python
decoder_outputs, _, _ = decoder_lstm(decoder_inputs, initial_state=encoder_states)
```



Then the model chooses the next character to predict with a softmax over all possible characters. 

```
decoder_dense = keras.layers.Dense(num_decoder_tokens, activation="softmax")
```

The task for this lab is simple:  Collaborate with other students to run the translation system for various numbers of epochs and make note of the kinds of translations produced.  Submit to Moodle what you found.