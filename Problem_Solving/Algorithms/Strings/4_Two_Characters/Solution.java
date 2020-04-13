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

public class Solution {

    /**
    * Class to represent each character pair.
    */
    static class CharPair implements Comparable<CharPair> {
        private final char ch1;
        private final char ch2;

        CharPair(char ch1,char ch2){
            this.ch1=ch1;
            this.ch2=ch2;
        }

        int getFirstChar(){
            return this.ch1;
        }

        int getSecondChar(){
            return this.ch2;
        }

        @Override public boolean equals(Object other) {
            if (!(other instanceof CharPair)) {
                return false;
            }
            CharPair otherCharPair = (CharPair) other;
            return ch1 == otherCharPair.ch1 && ch2 == otherCharPair.ch2;
        }

        @Override public int hashCode() {
            return Character.getNumericValue(ch1) * 37 + Character.getNumericValue(ch2) * 13;
        }

        @Override public int compareTo(CharPair other) {
            return ch1 != other.ch1 
                ? Integer.compare(ch1, other.ch1) 
                : Integer.compare(ch2, other.ch2);
        }

        public String toString(){
            return ch1 + ":" + ch2;
        }
    }

    static int alternate(String s) {
        int sLen=s.length();
        int tLen=0;
        int aux=0;
        //Strings just with one character length (p.e. q)
        if(sLen>1){
            Set<Character> chars=getChars(s,sLen);
            int charsSize=chars.size();
            //just checks cases with more than one character type in String s 
            //(p.e. pdgfgdba - abdfgp rather than zzz - z)
            if(charsSize>1){
                List<CharPair> charPairs=getCharPairs(chars,charsSize);
                for(CharPair cp:charPairs){
                    aux=checksCharPair(s,sLen,cp);
                    if(aux>tLen)
                        tLen=aux;
                }
            }
        }
        return tLen;
    }

    /**
    * Checks each CharPair and gets the length for a String composed
    * with the two characters of this CharPair
    */
    static int checksCharPair(String s,int sLen,CharPair cp){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<sLen;i++){
            char thisChar=s.charAt(i);
            if(thisChar==cp.getFirstChar() || thisChar==cp.getSecondChar())
                sb.append(thisChar);
        }
        return getAlternationLen(sb,sb.length());
    }

    /**
    * Returns the length of the biggest alternated String.
    */
    static int getAlternationLen(StringBuilder sb,int sbLen){
        int len=0;
        int i=1;
        boolean breaked=false;
        while(i<sbLen){
            if(sb.charAt(i)==sb.charAt(i-1)){
                breaked=true;
                break;
            }
            i++;
        }
        if(breaked==false)
            len=sbLen;
        return len;
    }

    /**
    * Get all combinations of character pairs in String.
    */
    static List<CharPair> getCharPairs(Set<Character> chars,int charsSize){
        List<Character> charsList=new ArrayList<Character>();
        charsList.addAll(chars);
        List<CharPair> charPairs=new ArrayList<CharPair>();
        for(int i=0;i<charsSize-1;i++)
            for(int j=i+1;j<charsSize;j++)
                charPairs.add(new CharPair(charsList.get(i),charsList.get(j)));
        return charPairs;
    }

    /**
    * Checks and returns the different characters present on String s
    */
    static Set<Character> getChars(String s,int sLen){
        Set<Character> chars=new TreeSet<Character>();
        for(int i=0;i<sLen;i++)
            chars.add(s.charAt(i));
        return chars;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
