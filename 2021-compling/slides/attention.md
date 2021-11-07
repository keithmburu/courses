---
marp: true
theme: default
class:
- invert
paginate: true
footer: Computational Linguistics, Fall 2021\nAlvin Grissom II, Haverford College

---
# Attention Mechanism
2021-11-3

---
# Attention Mechanism
- LSTM/GRU networks attmempt to summarize entire input in final hidden state $\mathbf{h}_n.$
![](images/attention/jm_bottleneck.png)
- What if we could focus preserve the information from all of the intermittent hidden states and focus on some time steps more than others?

---
# Attention Mechanism
- Consider encoder-decoder model.
- Recall that hidden states are a **context vector**, just as in vanilla RNN.
    $$
    \mathbf{c}=f(\mathbf{h^e_1\cdots \mathbf{h}^e_n}).
    $$
    - Here $e$ is for "encoder."
- $\mathbf{c}_i$ is recomputed at decoding step $i$.
$$
\mathbf{h}^d_i=g(\hat{y}_{i-1},\mathbf{h}^d_{i-1},\mathbf{c_i})
$$
- conditioned on current hidden state, previous decder output
---
# Attention mechanism
- With attention mechanism, we have a *weighted* context vector in the decoder $\mathbf{c}$.
- The weights give more or less "attention" to the hidden states at different time steps.
- Attention mechanism computes a relevance score of each encoder state to decoder state $\mathbf{h}^d_{i-1}$.
$$
\text{score}(\mathbf{h}^d_{i-1},\mathbf{h}^e_{j}) 
$$
for each encoder state $j$.

---
# Attention Mechanism
- Simplest version: dot product attention.
    - Relevance as similarity: how similar encoder state is to decoder state, as measured by dot product
![](images/attention/jm_dot_attention.png)

---
# Attention Mechanism
- Simplest version: dot product attention.
    - Relevance as similarity: how similar encoder state is to decoder state, as measured by dot product
$$
\text{score}(\mathbf{h}^d_{i-1},\mathbf{h}^e_{j})=\mathbf{h}^d_{i-1}\cdot \mathbf{h}^e_{j}
$$
- Then normalize with softmax to create vector of weights $\alpha_{ij}\mathbf{h}^e_j$,
where $\alpha_{ij}=\text{softmax}(\text{score}(\mathbf{h}^d_{i-1},\mathbf{h}^e_{j})),  \forall j\in e$.
- We end up with a weighted context vector according to the needs of the decoder, 
$$
\mathbf{c}_i=\sum_j \alpha_{ij}\mathbf{h}^e_j.
$$

---
![](images/attention/jm_encoder_decoder_attention.png)


---
# Attention Mechanism
- It's possible to create more complex attention mechanisms.
- One example: computer relevance of each encoder hidden state to each decoder hidden state by sets of weights to individual *scores* themselves.
$$
\text{score}(\mathbf{h}^d_{i-1},\mathbf{h}^e_{j})=\mathbf{h}^d_{i-1}\mathbf{W}_s \mathbf{h}^e_{j}
$$
- New set of weights $\mathbf{W}_s$ parameterize hidden states.
    - Determines which *parts* of each hidden state vector are relevant at the moment.
    - Trained along with everything else.
---

# Attention Mechanism
- Attention mechanism also commonly used in computer vision, as well.
- Can visualize attention weights.
- Some debate over whether NLP attention is "interpretable."
- Attention almost always helps.
![](images/attention/li_japanese_attention.png)