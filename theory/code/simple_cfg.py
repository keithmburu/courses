from nltk import CFG
import nltk
from nltk.draw.tree import TreeView
from nltk.parse.generate import generate, demo_grammar


s1 = """
S -> A C | A B
C -> S B
A -> 'a'
B -> 'b'
"""
    

g1 = CFG.fromstring(s1)
parser = nltk.ChartParser(g1)
#print(g1)

for sentence in generate(g1, n=10, depth = 20):
    print(' '.join(sentence))

sent = 'a a a a a b b b b b'.split()
for tree in parser.parse(sent):
    print(tree.chomsky_normal_form())
    tree.draw()
    #TreeView(tree)._cframe.print_to_file('output.ps')
