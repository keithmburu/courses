---
marp: true
theme: default
class:
paginate: true
footer: Machine Learning, Spring 2022\nAlvin Grissom II, Haverford College
style: |
  .columns {
    display: grid;
    grid-template-columns: repeat(2, minmax(0, 1fr));
    gap: 1rem;
  }
---
# k-Nearest Neighbors


---
# k-Nearest Neighbors
- Recall vector space feature model.
    - Every dimension in a feature vector $\mathbf{x}$ represents some **feature**.
    -  This means that we can represent every example geometrically in a vector space.
    - We can calculate the distance from any given example $x_i$ to any other training exampe $x_j$.


---
# k-Nearest Neighbors
- Recall vector space feature model.
    - Every dimension in a feature vector $\mathbf{x}$ represents some **feature**.
    -  This means that we can represent every example geometrically in a vector space.
    - We can calculate the distance from any given example $x_i$ to any other training exampe $x_j$.
    - kNN is a **voting** algorithm.  
        -  On the test data, find the $k$ nearest training examples.  The most common label among them is the predicted label.
        - No actual training involved.

---
![bg 50%](images/knn/knn.png)

---
![bg left 99%](images/knn/data.png)
1NN
![](images/knn/1nn.png)

---

![bg left 75%](images/knn/1nn.png)

1-NN vs. 5-NN
![](images/knn/5nn.png)

---
# k-Nearest Neighbords
- Choice of distance metric may vary depending on data or task.
- Most common is Euclidean distance, especially for continuous values.
$$
d(p,q) = \sqrt{(p_1- q_1)^2 + (p_2 - q_2)^2+\cdots+(p_i - q_i)^2+\cdots+(p_n - q_n)^2}
$$
- Hamming distance may be used for categorial variables.
    Given two vectors, how many bits are different?

- Any metric is fair game.

---
# k-Nearest Neighbors

Basic Algorithm

```python
for each x in test_data:
    ranked = sort_by_distance(x, training_data)
    kNearest = topK(ranked)
    predicted_class = most_common_class(kNearest)
```
- No training, but pretty slow at test time.