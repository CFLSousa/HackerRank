import math
import os
import random
import re
import sys

def birthday(s, d, m):
    solutions=0
    sLen=len(s)

    if m<=sLen:
        #Sum progression in each list position
        for i in range(1,sLen):
            s[i]=s[i-1]+s[i]
            
        """
        If month m is equal to list s size
        and the sum of the m elements in s is equal to day d,
        there is only one solution.
        
        If the sum of the m elements in list s is different from day d,
        there is no solution
        """
        if (m==sLen and s[m-1]==d):
            solutions+=1
        elif sLen>m:
            for i in range(m,sLen+1):
                """
                Each possible combination of m squares is verified,
                Each combination equalizing day d is a solution
                """
                if((i>m and s[i-1]-s[i-1-m]==d) or (i==m and s[i-1]==d)):
                    solutions+=1

    return solutions

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    s = list(map(int, input().rstrip().split()))

    dm = input().rstrip().split()

    d = int(dm[0])

    m = int(dm[1])

    result = birthday(s, d, m)

    fptr.write(str(result) + '\n')

    fptr.close()
