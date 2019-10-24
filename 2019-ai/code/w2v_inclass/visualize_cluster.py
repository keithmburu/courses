# -*- coding: utf-8 -*-
#!/usr/bin/local/python3.6
#Code based on: https://habr.com/en/company/mailru/blog/449984/
import gensim
from gensim.models import Word2Vec
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import pandas as pd
import numpy as np
import matplotlib
import matplotlib.pyplot as plt
import matplotlib.cm as cm
import matplotlib.patheffects as PathEffects
import tempfile
import imageio
import shutil


font = {'family' : 'normal',
        'size'   : 15}

matplotlib.rc('font', **font)
def tsne_plot_similar_words(title, labels, embedding_clusters, word_clusters, a, filename=None):
    plt.figure(figsize=(21, 9))
    colors = cm.rainbow(np.linspace(0, 1, len(labels)))
    markers = ['o', 'v', 's', '^', '*']
    for label, embeddings, words, color, marker in zip(labels, embedding_clusters, word_clusters, colors, markers):
        x = embeddings[:, 0]
        y = embeddings[:, 1]
        plt.scatter(x, y, c=color, alpha=a, label=label, marker=marker)
        for i, word in enumerate(words):
            plt.annotate(word, alpha=0.8, xy=(x[i], y[i]), xytext=(5, 2),
                         textcoords='offset points', ha='right', va='bottom', size=15)
    plt.legend(loc=4)
    plt.title(title)
    plt.grid(True)
    if filename:
        plt.savefig(filename, format='png', dpi=150, bbox_inches='tight')
    plt.show()


def tsne_plots_similar_words(title, labels, embedding_clusters, word_clusters, a, filename=None):
    plt.figure(figsize=(16,9))
    colors = cm.rainbow(np.linspace(0, 1, len(labels)))
    markers = ['o', 'v', 's', '^', '*']
    for label, embeddings, words, color, marker in zip(labels, embedding_clusters, word_clusters, colors, markers):
        x = embeddings[:, 0]
        y = embeddings[:, 1]
        plt.scatter(x, y, c=color, alpha=a, label=label, marker=marker)
        for i, word in enumerate(words):
            plt.annotate(word, alpha=0.5, xy=(x[i], y[i]), xytext=(5, 2),
                         textcoords='offset points', ha='right', va='bottom', size=12)
    plt.legend(loc=4)
    plt.title(title)
    plt.grid(True)
    if filename:
        plt.savefig(filename, format='png', dpi=150, bbox_inches='tight')
    plt.show()
    

    


def tsne_plot_2d(label, embeddings, words=[], a=1):
    plt.figure(figsize=(16, 9))
    colors = cm.rainbow(np.linspace(0, 1, 1))
    x = embeddings[:,0]
    y = embeddings[:,1]
    plt.scatter(x, y, c=colors, alpha=a, label=label)
    for i, word in enumerate(words):
        plt.annotate(word, alpha=1, xy=(x[i], y[i]), xytext=(5, 2), 
                     textcoords='offset points', ha='right', va='bottom', size=15)
    plt.legend(loc=4)
    plt.grid(True)
    plt.savefig("hhh.png", format='png', dpi=150, bbox_inches='tight')
    plt.show()





#for word in 
#print([x[0] for x in result])
#print([x[0] + ":" + str(x[1]) for x in result])
#for x, y in result:
#    print(x,y)

def tsne_plot_3d(title, label, embeddings, a=1):
    fig = plt.figure()
    ax = Axes3D(fig)
    colors = cm.rainbow(np.linspace(0, 1, 1))
    plt.scatter(embeddings[:, 0], embeddings[:, 1], embeddings[:, 2], c=colors, alpha=a, label=label)
    plt.legend(loc=4)
    plt.title(title)
    plt.show()



model = Word2Vec.load("gower-all.w2v")
#result = model.most_similar(positive=[u'love'])
vocab = list(model.wv.vocab)
X = model[vocab]

keys = ['love','lust', 'bok', 'rage','vice']

embedding_clusters = []
word_clusters = []
for word in keys:
    embeddings = []
    words = []
    for similar_word, _ in model.most_similar(word, topn=10):
        words.append(similar_word)
        embeddings.append(model[similar_word])
    embedding_clusters.append(embeddings)
    word_clusters.append(words)

tsne_model_en_2d = TSNE(perplexity=0.9, n_components=2, init='pca', n_iter=200000, random_state=5, learning_rate=5)
embedding_clusters = np.array(embedding_clusters)
n, m, k = embedding_clusters.shape
embeddings_en_2d = np.array(tsne_model_en_2d.fit_transform(embedding_clusters.reshape(n * m, k))).reshape(n, m, 2)

tsne_plot_similar_words('Visualization of Most Similar Words in Embedding Space', keys, embeddings_en_2d, word_clusters, 0.5,
                        'similar_words.png')



# words = []
# embeddings = []
# for word in list(model.wv.vocab):
# #for similar_word, _ in model.most_similar(word, topn=30):
#     embeddings.append(model.wv[word])
#     words.append(word)
    
# tsne_2d = TSNE(perplexity=15, n_components=2, init='pca', n_iter=3500, random_state=32)
# embeddings_2d = tsne_2d.fit_transform(embeddings)
# tsne_plot_2d('2D Embedding Space Visualization', embeddings_2d, a=1)




