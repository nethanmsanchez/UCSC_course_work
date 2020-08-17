"""
Nethaniel Sanchez
Ams129 Fall 2018
Negmsanc@ucsc.edu
"""

def newtons(f, df, initial_guess, threshold):
    diff = initial_guess
    xn = initial_guess
    x = initial_guess
    step = 0
    while diff > threshold:
        print("step: %d, x = %f, dx = %f" % (step, xn, diff))
        x = xn - (f(xn) / df(xn))
        diff = xn - x
        xn = xn - diff
        step = step + 1
        if (diff < 0):
            diff = diff * -1
