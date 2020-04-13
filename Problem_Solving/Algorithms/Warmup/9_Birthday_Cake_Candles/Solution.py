import math
import os
import random
import re
import sys

def birthdayCakeCandles(ar):
    maxVal=0
    candlesQty=0
    for key,val in enumerate(ar):
        if val>maxVal:
            maxVal=val
            candlesQty=1
        elif val==maxVal:
            candlesQty+=1
    return candlesQty

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    ar_count = int(input())

    ar = list(map(int, input().rstrip().split()))

    result = birthdayCakeCandles(ar)

    fptr.write(str(result) + '\n')

    fptr.close()
