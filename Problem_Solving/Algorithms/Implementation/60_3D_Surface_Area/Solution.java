import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int surfaceArea(int[][] A) {
        int height=A.length;
        int width=A[0].length;
        int upDown=height*width;
        int leftRight=0;
        int frontBack=0;

        for(int y=0;y<width;y++){
            for(int x=0;x<height;x++){
                if(x==0)
                    frontBack=frontBack+A[x][y];
                else{
                    if(A[x][y]>A[x-1][y])
                        frontBack=frontBack+(A[x][y]-A[x-1][y]);
                }
                if(y==0)
                    leftRight=leftRight+A[x][y];
                else{
                    if(A[x][y]>A[x][y-1])
                        leftRight=leftRight+(A[x][y]-A[x][y-1]);
                }
            }
        }

        return 2*(upDown+leftRight+frontBack);        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}