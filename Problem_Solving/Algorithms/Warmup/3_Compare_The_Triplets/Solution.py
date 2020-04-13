import math
import os
import random
import re
import sys

def compareTriplets(a, b):
    categories=len(a)
    aliceScore=0
    bobScore=0

    for i in range(categories):
        aRating=a[i]
        bRating=b[i]
        if aRating>bRating:
            aliceScore+=1
        elif aRating<bRating:
            bobScore+=1

    return [aliceScore,bobScore];

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    a = list(map(int, input().rstrip().split()))

    b = list(map(int, input().rstrip().split()))

    result = compareTriplets(a, b)

    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
