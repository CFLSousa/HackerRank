import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String gridSearch(String[] G, String[] P) {
        String yes="YES";
        String no="NO";
        Map<Integer,String> grid=new HashMap<Integer,String>();
        Map<Integer,String> pattern=new HashMap<Integer,String>();
        int gLen=G.length;
        int pLen=P.length;

        for(int x=0;x<gLen;x++)
            grid.put(x,G[x]);
        for(int x=0;x<pLen;x++)
            pattern.put(x,P[x]);

        for(int xp=0;xp<pLen;xp++){
            String patternLine=pattern.get(xp);
            for(int xg=0;xg<=gLen-pLen;xg++){
                String gridLine=grid.get(xg);
                int start=0;
                int end=0;

                while(gridLine.indexOf(patternLine,start)!=-1){
                    start=gridLine.indexOf(patternLine,start);
                    end=start+patternLine.length();

                    for(int xgdown=xg+1,xpdown=xp+1;xpdown<xp+pLen;xgdown++,xpdown++){
                        if(!grid.get(xgdown).substring(start,end).equals(pattern.get(xpdown))){
                            start++;
                            break;
                        }
                        if(xpdown==xp+pLen-1)
                            return yes;
                    }
                    
                }

            }
        }
        return no;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] RC = scanner.nextLine().split(" ");

            int R = Integer.parseInt(RC[0]);

            int C = Integer.parseInt(RC[1]);

            String[] G = new String[R];

            for (int i = 0; i < R; i++) {
                String GItem = scanner.nextLine();
                G[i] = GItem;
            }

            String[] rc = scanner.nextLine().split(" ");

            int r = Integer.parseInt(rc[0]);

            int c = Integer.parseInt(rc[1]);

            String[] P = new String[r];

            for (int i = 0; i < r; i++) {
                String PItem = scanner.nextLine();
                P[i] = PItem;
            }

            String result = gridSearch(G, P);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
