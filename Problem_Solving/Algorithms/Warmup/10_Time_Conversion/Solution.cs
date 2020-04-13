using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

class Solution {

    static string timeConversion(string s) {

        string regExpr12=@"^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$";
        Match match=Regex.Match(s,regExpr12);
        StringBuilder sb24=new StringBuilder();

        if(match.Success){
            //character in index 8 (is A or P) and identifies
            //12 or 24 hour mode
            char mode=s[8];
            string hour=s.Substring(0,2);

            if(mode=='P' && (hour.CompareTo("12")<0)){
                if(!String.IsNullOrEmpty(hour)){
                    if(int.TryParse(hour, out int val)){
                        sb24.Append((val+12)+"");
                    }
                }
            }
            else if(mode=='A' && hour.Equals("12"))
                sb24.Append("00");
            else
                sb24.Append(hour);

            sb24.Append(s.Substring(2,6));
            return sb24.ToString();
        }
        else
            return "";
    }

    static void Main(string[] args) {
        TextWriter tw = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        string s = Console.ReadLine();

        string result = timeConversion(s);

        tw.WriteLine(result);

        tw.Flush();
        tw.Close();
    }
}
