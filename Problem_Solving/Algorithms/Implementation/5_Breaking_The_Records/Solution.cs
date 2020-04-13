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

    static int[] breakingRecords(int[] scores) {
        int sLen=scores.Length;
        int maxPoints=scores[0];
        int minPoints=maxPoints;
        int[] recordTimes=new int[2];

        for(int i=1;i<sLen;i++){
            if(scores[i]>maxPoints){
                maxPoints=scores[i];
                recordTimes[0]++;
            }
            else if(scores[i]<minPoints){
                minPoints=scores[i];
                recordTimes[1]++;
            }
        }
        return recordTimes;
    }

    static void Main(string[] args) {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        int n = Convert.ToInt32(Console.ReadLine());

        int[] scores = Array.ConvertAll(Console.ReadLine().Split(' '), scoresTemp => Convert.ToInt32(scoresTemp));
        int[] result = breakingRecords(scores);

        textWriter.WriteLine(string.Join(" ", result));

        textWriter.Flush();
        textWriter.Close();
    }
}
