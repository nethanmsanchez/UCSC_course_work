import sys
import numpy as np
import matplotlib.pyplot as plt
import os



def char_count(file):
    f=open(file, 'r')
    mydict = {}
    for line in f:
        lower = line.lower()
        for x in lower:
            if x in mydict:
                mydict[x] = mydict[x] + 1
            else:
                if x.isalpha():
                    mydict[x] = 1
    return(mydict)



def main():
    dir = os.listdir(os.curdir)
    for x in dir:
        if x != sys.argv[0]:
            str = "result.png"
            if x != str:
                arg1 = x
    hist = char_count(arg1)
    sortedhist = sorted(hist)
    mylist = []
    myarr = np.arange(len(hist.keys()))
    count = 0
    for x in sortedhist:
        mylist.append(x)
        myarr[count] = hist[x]
        count = count + 1
    x = np.arange( len(hist.keys()) )
    graph = plt.bar(x, myarr)
    plt.plot(x, myarr, 'ro-')
    plt.xticks(x, sortedhist)
    plt.xlabel("Frequency")
    plt.ylabel("Character")
    plt.grid(True)
    f = open('result.png', 'w')
    plt.savefig('result.png')



if __name__ == "__main__": main()