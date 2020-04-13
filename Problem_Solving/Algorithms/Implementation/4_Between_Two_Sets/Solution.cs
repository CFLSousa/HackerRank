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

class Result {

    public static int getTotalX(List<int> a, List<int> b){
        //Calculate greatest common divisor of b
        int bgcd=0;
        foreach(int v in b)
            bgcd=gcd(bgcd,v);
        Console.WriteLine("Multiple: " + bgcd);

        //Calculate least common multiple of a
        int alcm=1;
        foreach(int v in a){
            alcm=lcm(alcm,v);

            //If factor becomes greater than multiple, 
            //stop calculating getTotalX function,
            //because there is not a solution.
            //Multiple will never be divisible by factor
            if(alcm>bgcd)
                return 0;
        }

        //If multiple is not divisible by factor, 
        //there is not an integer between the lists
        if(bgcd%alcm != 0)
            return 0;

        Console.WriteLine("Factor: " + alcm);

        int total=0;

        for(int i=alcm;i<=bgcd;i++){
            if(bgcd%i==0 && i%alcm==0)
                total++;
        }

        return total;
    }

    /**
    * Euclid's algorithm
    */
    public static int gcd(int n1,int n2){
        if(n1==0 && n2>0)
            return n2;
        else if(n1>0 && n2==0)
            return n1;
        else if(n1>=n2)
            return gcd(n2,n1%n2);
        else
            return gcd(n1,n2%n1);
    }

    /**
    * lcm(a,b)=(Math.Abs(a)/(gcd(a,b)))*Math.Abs(b)
    * or
    * lcm(a,b)=(Math.Abs(b)/(gcd(a,b)))*Math.Abs(a)
    */
    public static int lcm(int n1,int n2){
        if(n1==0 && n2==0)
            return 0;
        else
            return (Math.Abs(n1)/(gcd(n1,n2)))*(Math.Abs(n2));
    }
}

class Solution {
    public static void Main(string[] args){
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        string[] firstMultipleInput = Console.ReadLine().TrimEnd().Split(' ');

        int n = Convert.ToInt32(firstMultipleInput[0]);

        int m = Convert.ToInt32(firstMultipleInput[1]);

        List<int> arr = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(arrTemp => Convert.ToInt32(arrTemp)).ToList();

        List<int> brr = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(brrTemp => Convert.ToInt32(brrTemp)).ToList();

        int total = Result.getTotalX(arr, brr);

        textWriter.WriteLine(total);

        textWriter.Flush();
        textWriter.Close();
    }
}
