import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String[] bomberMan(int n, String[] grid) {
        Map<Integer,String> hmOdd=new HashMap<Integer,String>();
        int rows=grid.length;
        int columns=grid[0].length();
        if(n==1)
            return grid;
        String[] resultOdd=new String[rows];
        String[] resultEven=new String[rows];
        //String just with bombs for even time
        String even=getEvenRow(columns);
        //Fill's HashMap with matrix rows for odd time
        //Fill's result matrix for even time
        for(int i=0;i<rows;i++){
            hmOdd.put(i,grid[i]);
            resultEven[i]=even;
        }
        if(n%2==0)
            return resultEven;
        //Odd time (3,5,7,9,11,...) calculating rows transformations
        //Two situations:
        //If (n-3)%4==0 calculate one time
        //If (n-5)%4==0 calculate two times
        int times=calculateTimes(n);
        while(times>0){
            for(int r=0;r<rows;r++){
                StringBuilder oddProc=new StringBuilder(hmOdd.get(r));
                //For previous and next rows of a row with at least one O
                //except top first row or bottom last row
                StringBuilder up=null;
                StringBuilder down=null;
                if(r>=1)
                    up=new StringBuilder(hmOdd.get(r-1));
                if(r<rows-1)
                    down=new StringBuilder(hmOdd.get(r+1));
                //Substitute O's by X's in all rows 
                for(int c=0;c<columns;c++){
                    if(hmOdd.get(r).charAt(c)=='O'){
                        if(c-1>=0)
                            oddProc.setCharAt(c-1,'X');
                        if(c+1<columns)
                            oddProc.setCharAt(c+1,'X');
                        oddProc.setCharAt(c,'X');
                        if(r>=1)
                            up.replace(0,columns,
                                substituteChars(hmOdd.get(r-1),c,
                                columns,up));
                        if(r<rows-1)
                            down.replace(0,columns,
                                substituteChars(hmOdd.get(r+1),c,
                                columns,down));
                    }
                }
                //Refresh rows in HashMap after substitutions
                if(r>=1 && up!=null)
                    hmOdd.replace(r-1,hmOdd.get(r-1),up.toString());
                if(r<rows-1 && down!= null)
                    hmOdd.replace(r+1,hmOdd.get(r+1),down.toString());
                hmOdd.replace(r,hmOdd.get(r),oddProc.toString());
            }
            //Seen all rows? Replace .'s by O's and X's by .'s
            for(int r=0;r<rows;r++)
                hmOdd.replace(r,hmOdd.get(r),
                    hmOdd.get(r).replace(".","O").replace("X","."));
            times--;
        }
        for(int r=0;r<rows;r++)
            resultOdd[r]=hmOdd.get(r);
        return resultOdd;
    }

    static String getEvenRow(int numColumns){
        StringBuilder sb=new StringBuilder();
        String even="";
        for(int i=0;i<numColumns;i++)
            sb.append("O");
        return sb.toString();
    }

    static int calculateTimes(int seconds){
        int times=0;
        if((seconds-3)%4==0)
            times=1;
        else
            times=2;
        return times;
    }

    static String substituteChars(String line, int c, int columns, 
            StringBuilder sb){
        if(line.charAt(c)=='O'){
            if(c-1>=0 && line.charAt(c-1)!='O')
                sb.setCharAt(c-1,'X');
            if(c+1<columns && line.charAt(c+1)!='O')
                sb.setCharAt(c+1,'X');
        }
        else
            sb.setCharAt(c,'X');
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] rcn = scanner.nextLine().split(" ");

        int r = Integer.parseInt(rcn[0]);

        int c = Integer.parseInt(rcn[1]);

        int n = Integer.parseInt(rcn[2]);

        String[] grid = new String[r];

        for (int i = 0; i < r; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = bomberMan(n, grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
