# Machine Translation 

Machine translation (MT) is one of the original “artificial intelligence” problems.  The initial attempts, starting in the 1950’s, were **rule-based**: they relied on hand-crafted transformations between the source and target languages.  Much later corpus-based approaches, including example-based MT and finally **statistical machine translation**, with Brown et al. (1990).  First, we’ll briefly review statistical MT.

## Bayesian Machine Translation

The most common approach to statistical MT takes a Bayesian approach.  The initial system is known as IBM Model 1.  Subsequent iterations – Model 2, Model 3, etc.  – made incremental improvements to the core idea of Model 1, which is an application of Bayes’s Rule using parallel corpora for MT.  A parallel corpus is a corpus in two or more different languages.  Usually, the parallel corpora used in MT are translated at the sentence level.  From these parallel corpora, a statistical translation model can be learned.  Given a source sentence $S$ and some observed target sentence $T$, the baisc models is
$$
P(S|T)=\frac{P(S)P(T|S)}{P(T)}.
$$
This is Bayes’s Rule, and, as usual, we can ignore the denominator.  We want to maximize the probability of the source sentence given the target sentence, $P(S|T$).

This may seem backwards, as it seems more natural to maximize the probability of the *target* given the *source*, but these terms exactly the opposite meaning from what one might expect.  This particular formulation is an application of the **noisy channel model**, introduced by Shannon (1948), which is an important concept in information theory.  In the noisy channel model, there is a source message $S$ sent across an imperfect telephone wire to a recipient.  Sending across this imperfect medium (noisy channel) introduces noise into the message by the time is has been received.  This corrupted version of $S$ is $T$.  It’s the recipient’s job to reconstruct $S$ from the corrupted message received, $T$.  We can do this probabilistically if we have a probabilistic model to calculate the probability of a given source sequence $P(S)$ and a model for the probability that said source sequence gave rise to the target sequence received, $P(T|S)$.  This gives us the right side of the equation.  So, how likely is $S$ by itself, and how likely is this $S$ to give rise to what we saw, $T$? 

In statistical MT, at translation time, we are searching for the best $S$, which is what the model will actually produce: the “translation.”  If our goal is to translate from English to French, we *assume* that French ($T$) is just corrupted English, ($S)$.  Given some French text, we need to **decode** it into the original English.  We do this by finding that translation that maximized the above fromula.  $P(S)$ is an $n$-gram **language model** which provides a probability for a given English sentence.  $P(T|S)$ is a **translation model** which gives a probability for how likely it is for this candidate English sentence to give rise to this French we’ve seen.  For a word-based model, the translation model tells us how likely it is that we see a certain set of words, and the language model tells us the best order to put them in.  Together, they provide a translation probability.

Our translation model needs **word alignments**, which estimates plausible translations for each word.  In a phrase-based translation system, these words would be “phrases,” which are just $n$-grams.  The parameters for statistical MT models must be estimated with, for example, the expectation maximization algorithm. 



## Neural Machine Translation with Sequence-to-Sequence Models

Neural MT usually outperforms statistical MT when there is sufficient data.  In **sequence-to-sequence MT**, we use two recurrent neural networks – LSTMs or GRUs, typically – to learn and execute the translations: an **encoder** and a **decoder**.  This is known as the **encoder-decoder** model, the most prevalent MT model today.  

The core idea is simple: the encoder encodes a source sentence into a fixed-length vector; the decoder takes this vector and produces a sentence in the target language.  For simplicity, I’ll assume we’re using GRUs, but the only practical difference between using LSTMs and GRUs is that GRUs have only a hidden state $\mathbf{h}_t$ to summarize its information whereas LSTMs have the hidden state and a cell state $C_t$.

### Training

The encoder and decoder are trained jointly, not separately.  The input to the network is the concatenation of the source text and its translation, separated by a special token.  This input is then used to predict the next word, as with any other recurrent neural network, except in this case the predicted next word is in the target language.

During training, the encoder GRU receives the source sentence input one at a time.  As in any other GRU network, at the end of the input sequence, the final hidden state $\mathbf{h_t}$ holds a numeric summary of all of the information of the sequence.  These individual inputs may be represented as one hot vectors, or, better, embeddings.  The final hidden state is what we save.

The decoder generates its output, its translation, one token at a time.  The beginning hidden state, $\mathbf{h}_0$ of the decoder is the final hidden state of the encoder. At each time step $t$, it is shown the output up to step $t-1$. (If it is shown the correct output instead of the generated output, this is known as **teacher forcing**, as opposed to showing the GRU its own output at previous time steps.  This ensures that the GRU is conditioning its future outputs on *correct* inputs.) At each time step, the output is generated from a softmax layer over the vocabulary, which may be characters or words.  In practice, reversing the inputs on the source side seems to aid training.  This is a trick without a satisfying explanation at present.  Loss is calculated only based on the outputs of the decoder, the nature of the encoder’s hidden state is learned during training through backpropagation through time.



### Testing

During testing, the encoder works exactly the same as during training: its inputs are summarized in it sfinal hidden state, and this final hidden state is passed to the decoder.  The decoder then takes this hidden state and produces output, but during testing, there is no correct answer, so the subsequence tokens are conditioned on the previously genereated output, rather than the correct output.  For simplicity, we have used GRUs, but often BiLSTMs or BiGRUs are used instead.



### The Attention Mechanism

As in the vanishing gradient problem with RNNs, forcing the representation of a source sentence into a fixed-length final hidden state $\mathbf{h_t}$ is limiting.  This is a lot of information to cram into a vector.  LSTMs introduced gates to address this, allowing the LSTM to forget information and preserve other information, but, in the end, both LSTMs and GRUs are still attempting to store a long history into limited space.  The **attention** mechanism has been shown to greatly improve neural lanuage modeling and machine translation.  It allows a model to give more weight to certain parts of the input.  It is popular in both NLP and computer vision.

The attention mechanism is *another* set of weights that accomplishes this.  It provides a set of weights to each hidden state $h_t$ in the recurrent unit. Thus, instead of relying on a summary $\mathbf{h}_T$ in the final state, we instead have a *weighted* summary.  There are many kinds of attention.  The simplest is dot product attention.  In encoder-ecoder models, it measures the similarity between the encoder and decoder’s respective hidden states, at every time step, by computing the dot product between them.  This dot product is then sent through a softmax to give a probability distribution, but we can just think of them as weights for each step $t$ in the input, correponding to each word (for example).  We can then take the weighted average of all of the hidden states – instead of the unweighted summary we would have without attention – to produce a weighted summary.  These attention weights are learned with backpropagation, as with everything else.  Attention has been shown to *greatly* increase the performance of not only MT models but most deep learning models, but, as with weights in neural networks more generally, there is some debate about whether and to what extent the attention weights themselves are consistently interpretable. .

### Transformers for Machine Translation

Attention works well.  It works so well, in fact, that it may obviate the need for recurrence, as Transformers have shown.  Transformers lack recurrence, are faster to train on GPUs than RNNs, and usually have superior performance.