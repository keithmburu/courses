# -*- coding: utf-8 -*-
import gensim
from gensim.models import Word2Vec
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt
import pandas as pd
model = 'model.w2v'
model = Word2Vec.load("model.w2v")
#result = model.most_similar(positive=[u'love'])
vocab = list(model.wv.vocab)
X = model[vocab]
tsne = TSNE(n_components=2)
X_tsne = tsne.fit_transform(X)
df = pd.DataFrame(X_tsne, index=vocab, columns=['x', 'y'])
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)

max = 25
ax.scatter(df['x'], df['y'])
i = 0
for word, pos in df.iterrows():
    i += 1
    if i == max:
        break
    ax.annotate(word, pos)
fig.savefig('all-plot.jpg')
#print([x[0] for x in result])
#print([x[0] + ":" + str(x[1]) for x in result])
#for x, y in result:
#    print(x,y)
