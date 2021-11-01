---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2021\nAlvin Grissom II, Haverford College

---
# Encoder-Decoder/Sequence-to-Sequence Models
2020-11-1

---
# Review: Neural Language Models

* Feed-forward networks
    * Calculate $P(w_{n+1}|c)$
    * Condition next word $w_{n+1}$ on a fixed window of $n$ previous context words, $c$.
    * Pass through softmax to get a confidence score "probability."
    * No notion of order.

---
# Review: Recurrent Neural Language Models
* Vanilla recurrent neural networks
    * Allows unlimited context, with order information
    * Context influence decrases with previous word distance from current position.
    * Vanishing gradient problem

---
# Review: Recurrent Neural Language Models
* Vanilla recurrent neural networks
    * Allows unlimited context, with order information
    * Context influence decrases with previous word distance from current position.
    * Vanishing gradient problem
* LSTM language models
    * All of the benefits of vinalla RNNs
    * Gates allow models to "forget."
    * Gates mitigate vanishing gradient problem.
* GRU
    * Like LSTM with fewer gates, only hidden state

---
# Machine Translation
- Research area since 1950's
- Originally rule-based systems
- Eventually moved to statistical MT (1990)

---
# Statistical Machine Translation
- Application of Bayes's Rule between source and target.
$$										
P(S|T)=\frac{P(S)P(T|S)}{P(T)}.
$$
- Maximize probability of source sentence given target sentence.
- This might seem backwards.
- $S$ and $T$ mean opposite of what you might expect.

---
# Statistical Machine Translation
Noisy Channel Model (Shannon, 1948)
$$										
P(S|T)=\frac{P(S)P(T|S)}{P(T)}.
$$
- Source message $S$ sent across imperfect wire (noisy channel).
- Noisy channel corrupts $S$.
- Receipient receives $T$, corrupted/noisy version of $S$.
- Recipient wants to reconstruct $S$ message given corrupted $T$ 
    - Decoding: $P(S|T$.)  
    - Like encryption.

---
# Statistical Machine Translation
$$										
P(S|T)=\frac{P(S)P(T|S)}{P(T)}.
$$
- Suppose we're translating from French to English.
- In NCM, we **assume** that French is really corrupted English.
- Out job is to decode the French back into English.
- $P(S)$ is **language model**.  How likely is a given sequence of English.
- $P(T|S)$ is the **translation model**.  How likely are French words/phrases to map to English words/phrases.
- USe **word alignments** to estiamte plausible translations for each word.

---
# Neural Machine Translation with RNNs
- Encoder-Decoder/Sequence-to-sequence model MT.
- We have an **encoder** and **decoder** RNNs
    - Typically LSTM or GRU (lately, Transformers).
    - Only difference is GRU only has hidden state $\mathbf{h}
_t$ without $C_t$ of LSTM.

---
# Neural Machine Translation with RNNs
Training (We'll assume GRUs)
- Encoder and decoder trained jointly, not separately.
- Input to encoder GRU is source text concatenated with target text.
- Encoder generates final hidden state $\mathbf{h}$_t, as in language model.
- Use source text + hidden state to generate next word in target language.
- Beginning hidden state of decoder GRU $\mathbf{h}_0$ is final hidden state of encoder GRU.
- Might use **teacher forcing**.  Predict next word based on true, not predicted, history.

---
# Neural Machine Translation with RNNs
Testing
- Encoder works exactly the same as in training
    - Inputs summarized in final hidden state and passed to decoder.
    - Next token conditioned on predicted, not true input (since true input is unavailable).

---
# Attention Mchanism Basics
- Using fixed length vector $\mathbf{h}_t$ is limiting.
    - Similar to vanishing gradient problem.
-  Attention mechanism is *another* set of weights that "attend to" (focus on) certain parts of input.
    - Also used in computer vision.
- Instead of relying on a summary $\mathbf{h}_t$, weight each $\mathbf{h}_t$ at every time step.
    - Some time steps (tokens) will have more improtance.
- Many kinds of attention.  Dot product attention is simplest