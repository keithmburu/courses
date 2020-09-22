import nltk.data
import nltk
from nltk.probability import *
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer 
import codecs
import sys
# from urllib import request
# url = 'https://www.gutenberg.org/cache/epub/2707/pg2707.txt'
# raw = response.read().decode('utf8')
f = codecs.open('text.txt', encoding='utf8')
lines = f.readlines()
all_text = ' '.join(lines).lower()
tokenizer = nltk.RegexpTokenizer('\w+')
tokens = tokenizer.tokenize(all_text)
sys.stderr.write('Finding bigrams...' + '\n')
bigrams = nltk.bigrams(tokens)
#tokens = nltk.word_tokenize(all_text)

english_stopwords = stopwords.words('english')
stopwords_set = set(english_stopwords)
filtered_tokens = [w for w in tokens if w not in stopwords_set]
lemmatizer = WordNetLemmatizer()
lemmatized = [lemmatizer.lemmatize(w) for w in filtered_tokens]
#print(len(tokens))
fd = nltk.FreqDist(filtered_tokens)
print(fd.most_common(10))
print(len(tokens))
print(len(lemmatized))

# cfd = nltk.ConditionalFreqDist()
# prev_word = None
# for bigram in bigrams:
#     print(bigram)
#     for token in tokens:
#         cfd[bigram][token] += 1


# prev_word = 'it is'
# i = 0
# sys.stdout.write(prev_word + ' ')
# while i < 100:
#     word = cfd[prev_word].max()
#     sys.stdout.write(word + ' ')
#     sys.stdout.write(prev_word.split()[1] + ' ' + word)
#     i += 1


    
