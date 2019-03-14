import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int minimumDistances(int[] a) {
        int minimumDistance=Integer.MAX_VALUE;
        LinkedHashMap<Integer,LinkedList<Integer>> lhm=
            new LinkedHashMap<Integer,LinkedList<Integer>>();
        for(int i=0;i<a.length;i++){
            if(lhm.get(a[i])==null){
                LinkedList<Integer> auxIndexes=new LinkedList<Integer>();
                auxIndexes.add(i);
                lhm.put(a[i],auxIndexes);
            }
            else {
                lhm.get(a[i]).add(i);
            }
        }
        int[] setKeys=lhm.keySet().stream().mapToInt(i->i).toArray();
        for(int z=0;z<lhm.size();z++){
            //just in case of duplicates existence
            if(lhm.get(setKeys[z]).size()>1){
                int auxMin=auxMinDist(lhm.get(setKeys[z]));
                if(auxMin<minimumDistance)
                    minimumDistance=auxMin;            
            }
        }
        if(minimumDistance==Integer.MAX_VALUE)
            minimumDistance=-1;
        return minimumDistance;
    }

    static int auxMinDist(LinkedList<Integer> elemIndexes){
        int auxMin=Integer.MAX_VALUE;
        for(int i=0;i<elemIndexes.size()-1;i++){
            for(int j=i+1;j<elemIndexes.size();j++){
                int mathAbs=Math.abs(elemIndexes.get(j)-elemIndexes.get(i));
                if(auxMin>mathAbs)
                    auxMin=mathAbs;
            }
        }
        return auxMin;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minimumDistances(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
