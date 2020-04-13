import math
import os
import random
import re
import sys

def migratoryBirds(arr):
    countersLen=5
    maxCounter=0
    maxCounterIndex=0
    counters=[0 for x in range(countersLen)]

    for k,birdType in enumerate(arr):
        counters[birdType-1]+=1

    for index,counterVal in enumerate(counters):
        if counterVal>maxCounter:
            maxCounter=counterVal
            maxCounterIndex=index

    return maxCounterIndex+1

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr_count = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    result = migratoryBirds(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
