import math
import os
import random
import re
import sys

def gradingStudents(grades):
    minimumGrade=38
    roundedGrades=[]

    for k,v in enumerate(grades):
        #round if distance<=2 from ith grade to next multiple of 5
        if v>=minimumGrade and v%5>=3:
            roundedGrades.append(v-(v%5)+5)
        else:
            roundedGrades.append(v)

    return roundedGrades

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    grades_count = int(input().strip())

    grades = []

    for _ in range(grades_count):
        grades_item = int(input().strip())
        grades.append(grades_item)

    result = gradingStudents(grades)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
