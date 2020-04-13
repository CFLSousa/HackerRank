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

    public static List<int> gradingStudents(List<int> grades){
        int MINIMUM_GRADE=38;
        List<int> roundedGrades=new List<int>(grades.Count);

        foreach(int grade in grades){
            //round if distance<=2 from ith grade to next multiple of 5
            if(grade>=MINIMUM_GRADE && grade%5>=3)
                roundedGrades.Add(grade-(grade%5)+5);
            else
                roundedGrades.Add(grade);
        }

        return roundedGrades;
    }

}

class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        int gradesCount = Convert.ToInt32(Console.ReadLine().Trim());

        List<int> grades = new List<int>();

        for (int i = 0; i < gradesCount; i++)
        {
            int gradesItem = Convert.ToInt32(Console.ReadLine().Trim());
            grades.Add(gradesItem);
        }

        List<int> result = Result.gradingStudents(grades);

        textWriter.WriteLine(String.Join("\n", result));

        textWriter.Flush();
        textWriter.Close();
    }
}
