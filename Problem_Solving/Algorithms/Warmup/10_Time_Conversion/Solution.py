import os
import sys
import re

def timeConversion(s):
    regExpr='^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$'
    sb24=""

    if re.match(regExpr,s):
        """
        character in index 8 (is A or P) and identifies
        12 or 24 hour mode
        """
        mode=s[8]
        hour=s[0:2]

        if mode=='P' and hour<"12":
            sb24+=str(int(hour)+12)
        elif mode=='A' and hour=="12":
            sb24+="00"
        else:
            sb24+=hour

        sb24+=s[2:8]
        return sb24
    else:
        return ""

if __name__ == '__main__':
    f = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = timeConversion(s)

    f.write(result + '\n')

    f.close()
