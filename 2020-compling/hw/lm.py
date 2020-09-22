from sys import stdin
from collections import defaultdict

def count_ngrams(n=2):
    counts = defaultdict(int)
    for line in stdin:
        for word in line.split():
            counts[word.lower()] += 1


def main():
    count_ngrams()
    
if __name__ == "__main__":
    main()
