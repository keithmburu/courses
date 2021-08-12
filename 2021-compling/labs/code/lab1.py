import sys
from random import choices

def main():
        print(roll_die())

        """
Flips a possibly-biased coin. 
The first argument of choices is the choices;
the second is the probability distribution of the choices
"""
def roll_die(probs=[0.5, 0.5]):
    return choices(range(len(probs)), probs)


if __name__ == "__main__":
    main()
