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

    static void staircase(int n) {
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.Append(' ',n-i);
            sb.Append('#',i);
            if(i<n)
                sb.Append("\n");
        }
        Console.WriteLine(sb.ToString());
    }

    static void Main(string[] args) {
        int n = Convert.ToInt32(Console.ReadLine());

        staircase(n);
    }
}
