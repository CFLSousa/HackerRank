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

    static int birthday(List<int> s, int d, int m) {
        int solutions=0;
        int sLen=s.Count();

        if(m<=sLen){
            //Sum progression in each list position
            for(int i=1;i<sLen;i++)
                s[i]=s[i-1]+s[i];
                
            /*
            If month m is equal to list s size
            and the sum of the m elements in s is equal to day d,
            there is only one solution.
            
            If the sum of the m elements in list s is different from day d,
            there is no solution
            */
            if(m==sLen && s[m-1]==d)
                solutions++;
            else if(sLen>m){
                for(int i=m;i<=sLen;i++){
                    /*
                    Each possible combination of m squares is verified,
                    Each combination equalizing day d is a solution
                    */
                    if((i>m && s[i-1]-s[i-1-m]==d) || 
                            (i==m && s[i-1]==d))
                        solutions++;
                }
            }
        }
        return solutions;
    }

    static void Main(string[] args) {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        int n = Convert.ToInt32(Console.ReadLine().Trim());

        List<int> s = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(sTemp => Convert.ToInt32(sTemp)).ToList();

        string[] dm = Console.ReadLine().TrimEnd().Split(' ');

        int d = Convert.ToInt32(dm[0]);

        int m = Convert.ToInt32(dm[1]);

        int result = birthday(s, d, m);

        textWriter.WriteLine(result);

        textWriter.Flush();
        textWriter.Close();
    }
}
