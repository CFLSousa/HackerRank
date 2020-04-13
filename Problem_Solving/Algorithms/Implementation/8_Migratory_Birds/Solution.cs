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

    static int migratoryBirds(List<int> arr) {
        int countersLen=5;
        int maxCounter=0;
        int maxCounterIndex=0;
        int[] counters=new int[countersLen];

        foreach(int birdType in arr)
            counters[(birdType-1)]++;

        for(int i=0;i<countersLen;i++){
            if(counters[i]>maxCounter){
                maxCounter=counters[i];
                maxCounterIndex=i;
            }
        }
        return maxCounterIndex+1;
    }

    static void Main(string[] args) {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        int arrCount = Convert.ToInt32(Console.ReadLine().Trim());

        List<int> arr = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(arrTemp => Convert.ToInt32(arrTemp)).ToList();

        int result = migratoryBirds(arr);

        textWriter.WriteLine(result);

        textWriter.Flush();
        textWriter.Close();
    }
}
