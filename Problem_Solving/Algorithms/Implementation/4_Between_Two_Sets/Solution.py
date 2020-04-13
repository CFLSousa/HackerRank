import math
import os
import random
import re
import sys

def getTotalX(a, b):
    #Calculate greatest common divisor of b
    bgcd=0
    for k,v in enumerate(b):
        bgcd=gcd(bgcd,v)
    print("Multiple: ",bgcd)

    #Calculate least common multiple of a
    alcm=1
    for k,v in enumerate(a):
        alcm=lcm(alcm,v)

        """
        If factor becomes greater than multiple, 
        stop calculating getTotalX function,
        because there is not a solution.
        Multiple will never be divisible by factor
        """
        if (alcm>bgcd):
            return 0

    """
    If multiple is not divisible by factor, 
    there is not an integer between the lists
    """
    if (bgcd%alcm!=0):
        return 0

    print("Factor: ",alcm)

    total=0

    for i in range(alcm,bgcd+1,1):
        if (bgcd%i==0 and i%alcm==0):
            total+=1

    return total

"""
Euclid's algorithm
"""
def gcd(n1,n2):
    if (n1==0 and n2>0):
        return n2
    elif (n1>0 and n2==0):
        return n1
    elif (n1>=n2):
        return gcd(n2,n1%n2)
    else:
        return gcd(n1,n2%n1)

"""
lcm(a,b)=(abs(a)/(gcd(a,b)))*abs(b)
or
lcm(a,b)=(abs(b)/(gcd(a,b)))*abs(a)
"""
def lcm(n1,n2):
    if (n1==0 and n2==0):
        return 0
    else:
        return int((abs(n1)/(gcd(n1,n2)))*(abs(n2)))

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    m = int(first_multiple_input[1])

    arr = list(map(int, input().rstrip().split()))

    brr = list(map(int, input().rstrip().split()))

    total = getTotalX(arr, brr)

    fptr.write(str(total) + '\n')

    fptr.close()
