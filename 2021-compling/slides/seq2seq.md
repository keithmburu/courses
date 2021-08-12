---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2020\nAlvin Grissom II, Haverford College

---
# Encoder-Decoder/Sequence-to-Sequence Models
2020-10-27

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
    * Like LSTM with fewer gates
