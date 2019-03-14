import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int workbook(int n, int k, int[] arr) {
        int actualPage=0;
        int specialProb=0;
        int startChapter=0;
        int endChapter=0;
        HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++){
            hm.put(i+1,arr[i]);
        }
        for(int i=1;i<=hm.size();i++){
            if(hm.get(i)<=k){
                actualPage++;
                if(hm.get(i)>=actualPage){
                    specialProb++;
                }
            }
            else{
                startChapter=actualPage+1;
                if(hm.get(i)%k!=0){
                    actualPage=actualPage+((hm.get(i)+(k-(hm.get(i)%k)))/k);
                }
                else{
                    actualPage=actualPage+(hm.get(i)/k);
                }
                endChapter=actualPage;
                if(hm.get(i)>=startChapter){
                    for(int j=1, z=startChapter;j<=hm.get(i);j++){
                        if(j==z){
                            specialProb++;
                        }
                        if(j%k==0){
                            z++;
                        }
                    }
                }    
            }
        }
        return specialProb;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = workbook(n, k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
