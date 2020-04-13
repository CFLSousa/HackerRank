import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int changesMade=0;
    static String leftPart="";
    static String rightPart="";
    static String rightPartReversed="";
    static boolean impossible=false;
    static List<Integer> markChanges;

    static String highestValuePalindrome(String s, int n, int k) {
        int limit=n/2;
        leftPart=s.substring(0,limit);
        rightPart=s.substring(((n%2==0)?limit:limit+1),n);
        rightPartReversed=new StringBuilder(rightPart).reverse().toString();
        markChanges=new ArrayList<Integer>();

        firstRound(k,limit);

        if(!impossible)
            secondRound(k,limit);

        return (!impossible)
            ?getFinalResult(n,s,limit,k)
            :"-1";
    }

    /**
    * Checks the pairs in indexes 'index' and 'n-index-1' that have different numbers
    * from the beginning and end of string s to the middle of string s.
    * Marks the change in this index to have that information in the second round.
    * If string s has more different pairs than k maximum possible changes,
    * then is impossible to return a palindrome.
    */
    static void firstRound(int k,int limit){
        for(int i=0;i<limit;i++){
            char chLeft=leftPart.charAt(i);
            char chRight=rightPartReversed.charAt(i);
            if(chLeft!=chRight){
                markChanges.add(1);
                checkDiffs(chLeft,chRight,i);
            }
            else
                markChanges.add(0);
            if(changesMade>k && i<limit){
                impossible=true;
                break;
            }
        }
    }

    /**
    * Checks the pairs in indexes 'index' and 'k-index-1' that have equal numbers
    * (different then '9')
    * from the beginning and end of string s to the middle of string s.
    * Checks if any change in this 'index' was made in first round to control
    * changesMade.
    */
    static void secondRound(int k,int limit){
        boolean changedBefore=false;

        for(int j=0;j<limit;j++){
            char chLeft=leftPart.charAt(j);
            if(chLeft!='9'){ 
                if(markChanges.get(j)==1)
                    changedBefore=true;
                else
                    changedBefore=false;
                checkEquals(k,j,changedBefore);
            }
        }
    }

    /**
    * Does the modification of pairs with equals numbers, 
    * different than '9', in second round.
    */
    static void checkEquals(int k,int pos,boolean changedBefore){
        if(changesMade<k-1 && !changedBefore){
            leftPart=changeLeftPart(pos,"9");
            rightPartReversed=changeRightPartReversed(pos,"9");
            changesMade+=2;
        }
        else if(changesMade<k && changedBefore){
            leftPart=changeLeftPart(pos,"9");
            rightPartReversed=changeRightPartReversed(pos,"9");
            changesMade++;
        }
    }

    /**
    * Does the modification of pairs with different numbers,
    * in first round. The maximum between chLeft and chRight is used.
    */
    static void checkDiffs(char chLeft,char chRight,int pos){
        if(chLeft>chRight)
            rightPartReversed=changeRightPartReversed(pos,(chLeft+""));
        else
            leftPart=changeLeftPart(pos,(chRight+""));
        changesMade++;
    }

    /**
    * Does the modification of the first half of string s.
    */
    static String changeLeftPart(int pos,String chLeft){
        return new StringBuilder(leftPart)
            .replace(pos,pos+1,chLeft).toString();
    }

    /**
    * Does the modification of the second half (reversed) of string s.
    */
    static String changeRightPartReversed(int pos,String chRight){
        return new StringBuilder(rightPartReversed)
            .replace(pos,pos+1,chRight).toString();
    }

    /**
    * Joins the first half of string s modified with the middle in case of
    * odd length of string s, with the second half of string s modified.
    */
    static String getFinalResult(int n,String s,int limit,int k){
        StringBuilder join=new StringBuilder();
        join.append(leftPart);
        if(n%2!=0){
            if((s.charAt(limit)!='9' && changesMade<k) || s.charAt(limit)=='9'){
                join.append('9');
            }
            else if(s.charAt(limit)!='9' && changesMade>=k){
                join.append(s.charAt(limit));
            }
        }
        join.append(new StringBuilder(rightPartReversed).reverse().toString());
        return join.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

