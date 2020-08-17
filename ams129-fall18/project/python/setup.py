

import sys
import numpy as np
import matplotlib.pyplot as plt
import os

def main():
    #dir = os.listdir(os.curdir)
    os.chdir("../fortran")
    os.system('make')
    os.chdir("../fortran")
    os.system('main')
    f=open("output_8.txt", 'r')
    g = open("output_16.txt", 'r')
    h = open("output_32.txt", 'r')
    i = open("output_64.txt", 'r')
    tarray8 = np.arange(9.0)
    yarray8 = np.arange(9.0)
    solarray8 = np.arange(9.0)
    errarray8 = np.arange(9.0)
    tarray16 = np.arange(17.0)
    yarray16 = np.arange(17.0)
    solarray16 = np.arange(17.0)
    errarray16 = np.arange(17.0)
    tarray32 = np.arange(33.0)
    yarray32 = np.arange(33.0)
    solarray32 = np.arange(33.0)
    errarray32 = np.arange(33.0)
    tarray64 = np.arange(65.0)
    yarray64 = np.arange(65.0)
    solarray64 = np.arange(65.0)
    errarray64 = np.arange(65.0)
    x = 0.0000000000000000000
    y = 0.0000000000000000000
    count = 0
    error = 0.0
    error2 = 0
    error3 = 0
    error4 = 0
    for line in f:
        x, y = line.split()
        tarray8[count] = float(x)
        yarray8[count] = float(y)
        #print(yarray8[count])
        solarray8[count] = realsol(tarray8[count])
        errarray8[count] = solarray8[count] - yarray8[count]
        error = error + errarray8[count]
        count = count + 1
    count = 0
    for line in g:
        x, y = line.split()
        tarray16[count] = float(x)
        yarray16[count] = float(y)
        solarray16[count] = realsol(tarray16[count])
        errarray16[count] = solarray16[count] - yarray16[count]
        error2 = error2 + errarray16[count]
        count = count + 1
    count = 0
    for line in h:
        x, y = line.split()
        tarray32[count] = float(x)
        yarray32[count] = float(y)
        solarray32[count] = realsol(tarray32[count])
        errarray32[count] = solarray32[count] - yarray32[count]
        error3 = error3 + errarray32[count]
        count = count + 1
    count = 0
    for line in i:
        x, y = line.split()
        tarray64[count] = float(x)
        yarray64[count] = float(y)
        solarray64[count] = realsol(tarray64[count])
        errarray64[count] = solarray64[count] - yarray64[count]
        error4 = error4 + errarray64[count]
        count = count + 1

    #plt.bar(tarray8, yarray8)
    os.chdir("../python")
    plt.close()
    plt.plot(tarray8, yarray8, 'ro-')
    plt.plot(tarray8, solarray8, 'bo-')
    plt.xlabel("T axis")
    plt.ylabel("Y axis")
    plt.grid(True)
    title = "Error = " + str(error)
    plt.title(title)
    open('result_8.png', 'w')
    plt.savefig('result_8.png')
    plt.close()
    plt.plot(tarray16, yarray16, 'ro--')
    plt.plot(tarray16, solarray16, 'bo-')
    plt.xlabel("T axis")
    plt.ylabel("Y axis")
    plt.grid(True)
    title = "Error = " + str(error2)
    plt.title(title)
    f=open('result_16.png', 'w')
    plt.savefig('result_16.png')
    plt.close()
    plt.plot(tarray32, yarray32, 'ro--')
    plt.plot(tarray32, solarray32, 'bo-')
    plt.xlabel("T axis")
    plt.ylabel("Y axis")
    plt.grid(True)
    title = "Error = " + str(error3)
    plt.title(title)
    f=open('result_32.png', 'w')
    plt.savefig('result_32.png')
    plt.close()
    plt.plot(tarray64, yarray64, 'ro--')
    plt.plot(tarray64, solarray64, 'bo-')
    plt.xlabel("T axis")
    plt.ylabel("Y axis")
    plt.grid(True)
    title = "Error = " + str(error4)
    plt.title(title)
    f=open('result_64.png', 'w')
    plt.savefig('result_64.png')

def realsol(t):
    return -1*np.sqrt((2*np.log((t*t)+1))+4)


if __name__ == "__main__": main()