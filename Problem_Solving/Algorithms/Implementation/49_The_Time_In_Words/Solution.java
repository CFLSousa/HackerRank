import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String timeInWords(int h, int m) {
        StringBuilder sb=new StringBuilder();
        HashMap<Integer,String> hours=new HashMap<Integer,String>();
        HashMap<Integer,String> minutes=new HashMap<Integer,String>();
        for(int i=1;i<=12;i++){
            switch(i){
                case 1:
                    hours.put(i,"one");
                    break;
                case 2:
                    hours.put(i,"two");
                    break;
                case 3:
                    hours.put(i,"three");
                    break;
                case 4:
                    hours.put(i,"four");
                    break;
                case 5:
                    hours.put(i,"five");
                    break;
                case 6:
                    hours.put(i,"six");
                    break;
                case 7:
                    hours.put(i,"seven");
                    break;
                case 8:
                    hours.put(i,"eight");
                    break;
                case 9:
                    hours.put(i,"nine");
                    break;
                case 10:
                    hours.put(i,"ten");
                    break;
                case 11:
                    hours.put(i,"eleven");
                    break;
                case 12:
                    hours.put(i,"twelve");
                    break;
            }
        }
        for(int i=0;i<60;i++){
            switch(i){
                case 0:
                    minutes.put(i," o' clock");
                    break;
                case 1:
                    minutes.put(i,"one minute");
                    break;
                case 2:
                    minutes.put(i,"two minutes");
                    break;
                case 3:
                    minutes.put(i,"three minutes");
                    break;
                case 4:
                    minutes.put(i,"four minutes");
                    break;
                case 5:
                    minutes.put(i,"five minutes");
                    break;
                case 6:
                    minutes.put(i,"six minutes");
                    break;
                case 7:
                    minutes.put(i,"seven minutes");
                    break;
                case 8:
                    minutes.put(i,"eight minutes");
                    break;
                case 9:
                    minutes.put(i,"nine minutes");
                    break;
                case 10:
                    minutes.put(i,"ten minutes");
                    break;
                case 11:
                    minutes.put(i,"eleven minutes");
                    break;
                case 12:
                    minutes.put(i,"twelve minutes");
                    break;
                case 13:
                    minutes.put(i,"thirteen minutes");
                    break;
                case 14:
                    minutes.put(i,"fourteen minutes");
                    break;
                case 15:
                    minutes.put(i,"quarter");
                    break;
                case 16:
                    minutes.put(i,"sixteen minutes");
                    break;
                case 17:
                    minutes.put(i,"seventeen minutes");
                    break;
                case 18:
                    minutes.put(i,"eighteen minutes");
                    break;
                case 19:
                    minutes.put(i,"nineteen minutes");
                    break;
                case 20:
                    minutes.put(i,"twenty minutes");
                    break;
                case 21:
                    minutes.put(i,"twenty one minutes");
                    break;
                case 22:
                    minutes.put(i,"twenty two minutes");
                    break;
                case 23:
                    minutes.put(i,"twenty three minutes");
                    break;
                case 24:
                    minutes.put(i,"twenty four minutes");
                    break;
                case 25:
                    minutes.put(i,"twenty five minutes");
                    break;
                case 26:
                    minutes.put(i,"twenty six minutes");
                    break;
                case 27:
                    minutes.put(i,"twenty seven minutes");
                    break;
                case 28:
                    minutes.put(i,"twenty eight minutes");
                    break;
                case 29:
                    minutes.put(i,"twenty nine minutes");
                    break;
                case 30:
                    minutes.put(i,"half");
                    break;
            }
        }
        if(m==0){
            sb.append(hours.get(h));
            sb.append(minutes.get(m));
        }
        else if(m>=1 && m<=30){
            sb.append(minutes.get(m));
            sb.append(" past ");
            sb.append(hours.get(h));
        }
        else{
            sb.append(minutes.get(60-m));
            sb.append(" to ");
            sb.append(hours.get((h+1)%12));
        }
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
