from nltk import CFG
import nltk
import os
from nltk.draw.tree import TreeView
from nltk.parse.generate import generate, demo_grammar
from nltk.draw.util import CanvasFrame
#based on https://stackoverflow.com/questions/23429117/saving-nltk-drawn-parse-tree-to-image-file
def save_tree(tree):
    cf = CanvasFrame()
    tc = TreeWidget(cf.canvas(),tree)
    cf.add_widget(tc,10,10) # (10,10) offsets
    cf.print_to_file('tree.ps')
    cf.destroy()
    os.system('convert tree.ps output.png')


s1 = """
S -> NP VP
S -> Aux NP VP 
S -> VP
NP -> Pronoun
NP -> Proper-Noun
NP -> Det Nominal
Aux -> 'does'
Nominal -> Noun 
Nominal -> Nominal PP 
VP -> Verb
VP -> Verb NP 
VP -> Verb NP PP
VP -> Verb PP
VP -> VP PP
PP -> Preposition Noun
Preposition -> 'from' | 'to' | 'on' | 'near' | 'through'
Det -> 'that' | 'this' | 'the' | 'a'
Proper-Noun -> 'Houston' | 'NWA'
Noun -> 'book' | 'flight' | 'meal' | 'money'
Verb -> 'book' | 'include' | 'prefer'
Pronoun -> 'I' | 'she' | 'me'
"""

#TODO: modify this grammar to include your modifications.
s2 = """
S -> NP VP
S -> Aux NP VP 
S -> VP
NP -> NP Conj NP
NP -> Pronoun
NP -> Proper-Noun
NP -> Det Nominal
Aux -> 'does'
Nominal -> Noun 
Nominal -> Nominal PP 
VP -> Verb
VP -> Verb NP 
VP -> Verb NP PP
VP -> Verb PP
VP -> VP PP
PP -> Preposition N
Preposition -> 'from' | 'to' | 'on' | 'near' | 'through'
Det -> 'that' | 'this' | 'the' | 'a'
Proper-Noun -> 'Houston' | 'NWA'
Noun -> 'book' | 'flight' | 'meal' | 'money'
Verb -> 'book' | 'include' | 'prefer'
Pronoun -> 'I' | 'she' | 'me'
"""


g1 = CFG.fromstring(s1)
parser = nltk.ChartParser(g1)
#parser = nltk.RecursiveDescentParser(g1)
#print(g1)

#generate valid sentences from grammar
for sentence in generate(g1, n=200, depth = 4):
    print(' '.join(sentence))

sent = 'I book the flight'.split()
#print all possible parses of sentence
for tree in parser.parse(sent):
    print(tree)
    tree.draw()
    #TreeView(tree)._cframe.print_to_file('output.ps')
    #save_tree(tree)

