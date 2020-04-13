import math
import os
import random
import re
import sys

def plusMinus(arr):
    posNums=0.0
    negNums=0.0
    zeroNums=0.0
    posFraction=0.0
    negFraction=0.0
    zeroFraction=0.0
    arrLen=len(arr)

    for i in range(arrLen):
        if arr[i]==0:
            zeroNums+=1
        elif arr[i]>0:
            posNums+=1
        else:
            negNums+=1

    posFraction=posNums/arrLen
    negFraction=negNums/arrLen
    zeroFraction=zeroNums/arrLen
    
    print(f'{posFraction:.6f}\n{negFraction:.6f}\n{zeroFraction:.6f}') 

if __name__ == '__main__':
    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    plusMinus(arr)
