import math
import os
import random
import re
import sys

def breakingRecords(scores):
    sLen=len(scores)
    maxPoints=scores[0]
    minPoints=maxPoints
    recordTimes=[0,0]

    for i in range(1,sLen,1):
        if scores[i]>maxPoints:
            maxPoints=scores[i]
            recordTimes[0]+=1
        elif scores[i]<minPoints:
            minPoints=scores[i]
            recordTimes[1]+=1
            
    return recordTimes

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    scores = list(map(int, input().rstrip().split()))

    result = breakingRecords(scores)

    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
