# -*- coding: utf-8 -*-
input_file = 'myfile.txt'
import gensim
from  gensim.models.word2vec import LineSentence
train_file = LineSentence(input_file)
model = gensim.models.Word2Vec(train_file, size=100, window=7, min_count=3, workers=6)
model.save('model.w2v')
