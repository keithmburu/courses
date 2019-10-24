# -*- coding: utf-8 -*-
import gensim
import sys

model_name='model.w2v'
from gensim.models import Word2Vec
model = Word2Vec.load(model_name)

#print([x[0] for x in result])
#print([x[0] + ":" + str(x[1]) for x in result])
print()
print()
print("Type a word, followed by [ENTER] to retrieve its nearest neighboards. Press Ctrl-C to exit.")
print()
print("You may search for multiple words separated by space.")
while True:
    query = input(">")
    result = model.most_similar(positive=query.strip().split())
    for x, y in result:
        print(x,y)
