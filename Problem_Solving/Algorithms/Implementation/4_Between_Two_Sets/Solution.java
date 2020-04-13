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

class Result {

    public static int getTotalX(List<Integer> a, List<Integer> b) {

        //Calculate greatest common divisor of b
        int bgcd=0;
        for(Integer v:b)
            bgcd=gcd(bgcd,v);
        System.err.println("Multiple: " + bgcd);

        //Calculate least common multiple of a
        int alcm=1;
        for(Integer v:a){
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

        System.err.println("Factor: " + alcm);

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
    * lcm(a,b)=(Math.abs(a)/(gcd(a,b)))*Math.abs(b)
    * or
    * lcm(a,b)=(Math.abs(b)/(gcd(a,b)))*Math.abs(a)
    */
    public static int lcm(int n1,int n2){
        if(n1==0 && n2==0)
            return 0;
        else
            return (Math.abs(n1)/(gcd(n1,n2)))*(Math.abs(n2));
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
