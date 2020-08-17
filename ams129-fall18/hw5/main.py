"""
Nethaniel Sanchez
Ams129 Fall 2018
Negmsanc@ucsc.edu
"""

from newton import newtons

def f(x):
    return ((2 * x**7) + (4 * x**5) - (2 * x**3) + (3 * x) + 1)

def df(x):
    return ((14 * x**6) + (20 * x**4) - (6 * x**2) + 3)

def main():
    initial_guess = 10
    threshold = 1.E-8
    newtons(f, df, initial_guess, threshold)


if __name__ == "__main__": main()