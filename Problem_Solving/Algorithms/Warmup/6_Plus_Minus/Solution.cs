using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Diagnostics.CodeAnalysis;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.Serialization;
using System.Text.RegularExpressions;
using System.Text;
using System;

class Solution {

    static void plusMinus(int[] arr) {
        double posNums=0;
        double negNums=0;
        double zeroNums=0;
        double posFraction=0.0;
        double negFraction=0.0;
        double zeroFraction=0.0;
        int arrLen=arr.Length;

        for(int i=0;i<arrLen;i++){
            if(arr[i]==0)
                zeroNums++;
            else if(arr[i]>0)
                posNums++;
            else
                negNums++;
        }

        posFraction=posNums/arrLen;
        negFraction=negNums/arrLen;
        zeroFraction=zeroNums/arrLen;

        Console.WriteLine(posFraction.ToString("0.000000") + "\n" + 
            negFraction.ToString("0.000000") + "\n" + 
            zeroFraction.ToString("0.000000"));
    }

    static void Main(string[] args) {
        int n = Convert.ToInt32(Console.ReadLine());

        int[] arr = Array.ConvertAll(Console.ReadLine().Split(' '), arrTemp => Convert.ToInt32(arrTemp));
        plusMinus(arr);
    }
}
