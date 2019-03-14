import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static String findDay(int[] yearDays, int dayToFind, int year){
        StringBuilder sb=new StringBuilder();
        int auxDaysCounter=0;
        int dayOfYear=0;
        int monthOfYear=0;
        for(int i=0;i<yearDays.length || auxDaysCounter<dayToFind;i++){
            if(auxDaysCounter+yearDays[i]<dayToFind){
                auxDaysCounter=auxDaysCounter+yearDays[i];
            }
            else {
                dayOfYear=dayToFind-auxDaysCounter;
                monthOfYear=i+1;
                sb.append(dayOfYear+".");
                if(monthOfYear<10)
                    sb.append("0"+monthOfYear+".");
                else
                    sb.append(monthOfYear+".");
                sb.append(year+"\n");
                return sb.toString();
            }
        }
        return "";
    }

    static String dayOfProgrammer(int year) {

        //365 days
        int[] currentYear=
            new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        //366 days
        int[] leapYear=
            new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        //February 1918 started on day 14th,
        //was a current year (finished in 28th February)
        //February 1918 had 15 days
        //
        //352 days
        int[] transitionYear=
            new int[]{31,15,31,30,31,30,31,31,30,31,30,31};
        //256th - day of programmer
        int dayToSearch=256;

        //julian calendar
        if(year<1918){
            //julian leap year
            if(year%4==0){
                return findDay(leapYear,dayToSearch,year);
            }
            //julian current year
            else if(!(year%4==0)){
                return findDay(currentYear,dayToSearch,year);
            }
        }
        //gregorian calendar
        else if(year>1918){
            //gregorian leap year
            if((year%400==0) || ((year%4==0) && (year%100!=0))){
                return findDay(leapYear,dayToSearch,year);
            }
            //gregorian current year
            else if(!((year%400==0) || ((year%4==0) && (year%100!=0)))){
                return findDay(currentYear,dayToSearch,year);
            }
        }
        //1918 transition year
        //Julian->Gregorian
        //31Jan->14Feb
        else if(year==1918){
            return findDay(transitionYear,dayToSearch,year);
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
