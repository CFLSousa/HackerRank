import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int palindromeIndex(String s) {
        int result=-1;
        int sLen=s.length();
        Map<Character,Integer> counters=countChars(s,sLen);
        if(counters.size()>1){
            List<Map.Entry<Character,Integer>> oddChars=getOddChars(counters);
            if(oddChars.size()==2 || oddChars.size()==1)
                result=getPosition(s,sLen,oddChars);
        }
        return result;
    }

    static int getPosition(String s,int sLen,
            List<Map.Entry<Character,Integer>> oddChars){
        int index=0;
        int numOddChars=oddChars.size();
        int limit=((sLen%2==0)?sLen/2:sLen/2+1);

        Character ch1=oddChars.get(0).getKey();
        Character ch2=new Character(' ');
        Integer chCounter1=new Integer(0);
        Integer chCounter2=new Integer(0);
        if(numOddChars==2){
            ch2=oddChars.get(1).getKey();
            chCounter1=oddChars.get(0).getValue();
            chCounter2=oddChars.get(1).getValue();
        }

        for(int i=0;i<limit;i++){
            if(i<sLen/2+1){
                char head=s.charAt(i);
                char tail=s.charAt(sLen-1-i);
                if(head!=tail){
                    if(numOddChars==2)
                        index=twoPossibilities(head,tail,chCounter1,chCounter2,
                            ch1,ch2,i,sLen);
                    if(numOddChars==1)
                        index=onePossibility(head,tail,ch1,i,sLen);
                    break;
                }
            }
            if(i==sLen/2+1){
                char head=s.charAt(i);
                if((numOddChars==2 && (head==ch1||head==ch2)) || 
                        (numOddChars==1 && head==ch1))
                    index=i;
            }
        }
        return index;
    }

    static int twoPossibilities(char head,char tail,Integer chCounter1,
            Integer chCounter2,Character ch1,Character ch2,int i,int sLen){
        int index=0;
        if((head==ch1 && tail==ch2)||(head==ch2 && tail==ch1)){
            if(chCounter1>chCounter2)
                index=sLen-1-i;
            else
                index=i;
        }
        else if((tail==ch1 || tail==ch2)&&(head!=ch1 && head!=ch2))
            index=sLen-1-i;
        else if((head==ch1 || head==ch2)&&(tail!=ch1 && tail!=ch2))
            index=i;
        return index;
    }

    static int onePossibility(char head,char tail,Character ch1,int i,int sLen){
        int index=0;
        if(tail==ch1 && head!=ch1)
            index=sLen-1-i;
        else if(tail!=ch1 && head==ch1)
            index=i;
        return index;
    }
    
    static List<Map.Entry<Character,Integer>> getOddChars(
            Map<Character,Integer> counters){
        List<Map.Entry<Character,Integer>> oddChars=
            new ArrayList<Map.Entry<Character,Integer>>();
        int oddCounters=0;
        for(Map.Entry<Character,Integer> counter:counters.entrySet()){
            if(counter.getValue()%2!=0){
                oddCounters++;
                oddChars.add(counter);
                if(oddCounters==3)
                    break;
            }
        }
        return oddChars;
    }

    static Map<Character,Integer> countChars(String s,int sLen) {
        Map<Character,Integer> counters=new TreeMap<Character,Integer>();
        for(int i=0;i<sLen;i++){
            Character ch=new Character(s.charAt(i));
            if(!counters.containsKey(ch))
                counters.put(ch,1);
            else{
                Integer val=counters.get(ch)+1;
                counters.replace(ch,val);
            }
        }
        return counters;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = palindromeIndex(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
