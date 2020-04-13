import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void separateNumbers(String s) {
        int sLen=s.length();
        String no="NO";
        String yes="YES ";
        if(sLen==1 || s.charAt(0)=='0')
            System.out.println(no);
        else{
            Long result=checkValidSequence(s,sLen);
            if(result!=-1)
                System.out.println(yes+result);
            else
                System.out.println(no);
        }
    }

    /**
    * Checks if numeric sequence s is beautiful 
    * and returns first element in positive case.
    */
    static Long checkValidSequence(String s,int sLen){
        Long firstSeqElem=new Long(-1);
        int firstSeqElemLen=1;
        int maxLenFirstElem=sLen/2;
        while(firstSeqElemLen<=maxLenFirstElem){
            int indexSameSize=checkElemsSameLen(firstSeqElemLen,s,sLen);
            if(indexSameSize==sLen-firstSeqElemLen){
                firstSeqElem=Long.valueOf(s.substring(0,firstSeqElemLen));
                break;
            }
            else{
                if(firstSeqElemLen+firstSeqElemLen+1<=sLen){
                    int indexDiffSize=
                        checkElemsDiffLen(firstSeqElemLen,s,sLen,indexSameSize);
                    if(indexDiffSize==sLen-(firstSeqElemLen+1)){
                        firstSeqElem=Long.valueOf(s.substring(0,firstSeqElemLen));
                        break;
                    }
                }
            }
            firstSeqElemLen++;
        }
        return firstSeqElem;
    }

    /**
    * Returns the start index of the last element with the same length 
    * as the first sequence element plus one, complying with the rules.
    * indexSameSize is used to know where was the last element with the same length 
    * as the first sequence element, complying with the rules.
    * 
    */
    static int checkElemsDiffLen(int firstSeqElemLen,String s,
            int sLen,int indexSameSize){
        int i=indexSameSize;
        Long elem=new Long(0);
        Long nextElem=new Long(0);
        while(i<=sLen-(firstSeqElemLen+firstSeqElemLen+1)){
            if(i==indexSameSize)
                elem=Long.valueOf(s.substring(i,i+firstSeqElemLen));
            else
                elem=Long.valueOf(s.substring(i,i+firstSeqElemLen+1));
            String sNextElem="";
            if(i==indexSameSize)
                sNextElem=s.substring(i+firstSeqElemLen,
                    i+firstSeqElemLen+firstSeqElemLen+1);
            else
                sNextElem=s.substring(i+firstSeqElemLen+1,
                    i+firstSeqElemLen+1+firstSeqElemLen+1);
            if(sNextElem.charAt(0)=='0')
                break;
            else
                nextElem=Long.valueOf(sNextElem);
            if(nextElem!=elem+1)
                break;
            if(i==indexSameSize)
                i=i+firstSeqElemLen;
            else
                i=i+firstSeqElemLen+1;
        }
        return i;
    }

    /**
    * Returns the start index of the last element with the same length 
    * as the first sequence element, complying with the rules
    */
    static int checkElemsSameLen(int firstSeqElemLen,String s,int sLen){
        int i=0;
        Long elem=new Long(0);
        Long nextElem=new Long(0);
        while(i<=sLen-(firstSeqElemLen+firstSeqElemLen)){
            elem=Long.valueOf(s.substring(i,i+firstSeqElemLen));
            String sNextElem=
                s.substring(i+firstSeqElemLen,i+firstSeqElemLen+firstSeqElemLen);
            if(sNextElem.charAt(0)=='0')
                break;
            else
                nextElem=Long.valueOf(sNextElem);
            if(nextElem!=elem+1)
                break;
            i=i+firstSeqElemLen;
        }
        return i;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            separateNumbers(s);
        }

        scanner.close();
    }
}
