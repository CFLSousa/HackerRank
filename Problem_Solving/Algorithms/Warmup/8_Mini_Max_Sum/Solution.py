import math
import os
import random
import re
import sys

def miniMaxSum(arr):
    tot=0
    minVal=1000000001
    maxVal=0

    for key,val in enumerate(arr):
        tot+=val
        if val>maxVal:
            maxVal=val
        if val<minVal:
            minVal=val

    print(tot-maxVal,tot-minVal)

if __name__ == '__main__':
    arr = list(map(int, input().rstrip().split()))

    miniMaxSum(arr)
