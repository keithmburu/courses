"""
This file uses ftfy to fix broken unicode in Python files, strips punctuation, removes numbers.
"""
import ftfy
import sys
import codecs
for line in ftfy.fix_file(codecs.open('new-latin1.txt',errors='ignore', encoding='latin1')):
    if line.strip().startswith('['):
        continue
    line = line.replace(",","").replace("?","").replace(".","").replace("!","").replace(";","").replace("-","").replace("'"," ").lower()
    line = ''.join([i for i in line if not i.isdigit()]) #remove numbers
    line = line.strip()
    if line == '':
        continue
    print(line.replace("\n",""))




