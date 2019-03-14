import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String happyLadybugs(String b) {
        String yes="YES";
        String no="NO";
        Map<Character,Integer> counterMap=createColorsCounters(b);
        Set<Character> colors=counterMap.keySet();
        int colorsSize=colors.size();
        //Just one type of character on String b
        //(just characters of one color 
        //or just underscores)
        if(colorsSize==1){
            //just underscores
            if(colors.contains('_'))
                return yes;
            //just characters of one color
            else{
                //more than one ladybug of this color
                if(counterMap.get(colors.iterator().next())>1)
                    return yes;
                //one ladybug of this color
                else
                    return no;
            }
        }
        else{
            if(containsAloneInBetween(colors.iterator(),counterMap,colorsSize))
                return no;
            //colors not sorted:
            //verify if ladybugs of same color are side by side,
            //if no, that's necessary at least one '_' to 
            //sort the colors
            if(!isSorted(b)){
                if(!colors.contains('_')){
                    if(allAdjacent(b,counterMap))
                        return yes;
                    else
                        return no;
                }
                else
                    return yes;
            }
            //colors are sorted and with more than one 
            //ladybug of each color then
            //they are happy
            else
                return yes;
        }
    }

    /**
    * Count number of ladybug colors and underscores
    */
    static Map<Character,Integer> createColorsCounters(String b){
        Map<Character,Integer> counterMap=new LinkedHashMap<Character,Integer>();
        int bLen=b.length();
        for(int i=0;i<bLen;i++){
            char color=b.charAt(i);
            if(!counterMap.containsKey(color))
                counterMap.put(color,1);
            else
                counterMap.replace(color,
                                counterMap.get(color),
                                counterMap.get(color)+1);
        }
        return counterMap;
    }

    /**
    * Contains an alone ladybug of a color in between
    * other colors with more than one ladybug 
    * and/or underscores?
    */
    static boolean containsAloneInBetween(Iterator<Character> it,
            Map<Character,Integer> counterMap, int colorsSize){
        for(int i=0;i<colorsSize;i++){
            char color=it.next();
            if(counterMap.get(color)==1 && color!='_')
                return true;
        }
        return false;
    }

    /**
    * All ladybug colors side by side?
    */
    static boolean allAdjacent(String str,Map<Character,Integer> counters){
        int strLen=str.length();
        char ch=str.charAt(0);
        int chCounter=1;
        for(int i=1;i<strLen;i++){
            if(str.charAt(i)==ch && ch!='_' && str.charAt(i)!='_')
                chCounter++;
            else{
                if(chCounter==counters.get(ch)){
                    ch=str.charAt(i);
                    chCounter=1;
                    continue;
                }
                else
                    return false;
            }
        }
        return true;
    }

    /**
    * Is String sorted?
    */
    static boolean isSorted(String b){
        int sLen=b.length();
        for(int i=0;i<sLen-1;i++){
            if(b.charAt(i)<=b.charAt(i+1))
                return false;
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String b = scanner.nextLine();

            String result = happyLadybugs(b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
