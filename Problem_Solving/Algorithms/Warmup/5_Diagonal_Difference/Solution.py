import math
import os
import random
import re
import sys

def diagonalDifference(arr):
    squareMatrixLen=len(arr)
    total=0

    for i in range(squareMatrixLen):
        total+=arr[i][i]
        total-=arr[i][squareMatrixLen-i-1]

    return abs(total)

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    arr = []

    for _ in range(n):
        arr.append(list(map(int, input().rstrip().split())))

    result = diagonalDifference(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
