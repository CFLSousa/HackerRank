import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static int getTotalX(int[] a, int[] b) {
        ArrayList<Integer> factors_of_a = new ArrayList<Integer>();
        int counterA = 0;
        ArrayList<Integer> factors_of_b = new ArrayList<Integer>();
        int counterB = 0;
        int minimumValueB = minB(b);
        for(int i = a[a.length-1]; i <= minimumValueB; i++){
            for(int j = 0; j < a.length; j++){
                if((i%a[j])==0)
                    counterA++;
                else {
                    counterA = 0;
                    break;
                }  
            }
            if(counterA == a.length){
                factors_of_a.add(i);
                counterA = 0;
            }
        }
        if(factors_of_a.size() == 0)
            return 0;
        else {
            for(int i = 0; i < factors_of_a.size(); i++){
                for(int j = 0; j < b.length; j++){
                    if(b[j]%factors_of_a.get(i)==0)
                        counterB++;
                    else {
                        counterB = 0;
                        break;
                    }
                }
                if(counterB == b.length){
                    factors_of_b.add(factors_of_a.get(i));
                    counterB = 0;
                }
            }
            return factors_of_b.size();
        }
    }

    static int minB(int[] b){
        int minimumValue = 100;
        for(int bValue:b){
            if(minimumValue > bValue)
                minimumValue = bValue;
        }
        return minimumValue;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scan.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[] a = new int[n];

        String[] aItems = scan.nextLine().split(" ");

        for (int aItr = 0; aItr < n; aItr++) {
            int aItem = Integer.parseInt(aItems[aItr].trim());
            a[aItr] = aItem;
        }

        int[] b = new int[m];

        String[] bItems = scan.nextLine().split(" ");

        for (int bItr = 0; bItr < m; bItr++) {
            int bItem = Integer.parseInt(bItems[bItr].trim());
            b[bItr] = bItem;
        }

        int total = getTotalX(a, b);

        bw.write(String.valueOf(total));
        bw.newLine();

        bw.close();
    }
}
