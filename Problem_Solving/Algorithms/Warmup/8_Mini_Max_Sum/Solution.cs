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

    static void miniMaxSum(int[] arr) {
        long sum=0;
        long minVal=long.MaxValue;
        long maxVal=long.MinValue;

        foreach(int num in arr){
            sum+=num;
            if(num>maxVal)
                maxVal=num;
            if(num<minVal)
                minVal=num;
        }
        
        Console.Write((sum-maxVal) + " " + (sum-minVal));
    }

    static void Main(string[] args) {
        int[] arr = Array.ConvertAll(Console.ReadLine().Split(' '), arrTemp => Convert.ToInt32(arrTemp));
        miniMaxSum(arr);
    }
}
