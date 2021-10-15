package com.Leetcode;

import java.util.*;

public class MTCode {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine().trim();
        String s2 = sc.nextLine().trim();
        char[] s1_c = s1.toCharArray();
        char[] s2_c = s2.toCharArray();
        int sum = 0;
        HashMap<String ,Integer> map = new HashMap<String,Integer>();
        for(int i = 0; i <= s1_c.length - s2_c.length; i++){
            sum = sum + getDist(s1_c,i,s2_c,map);
        }
        System.out.println(sum);
        sc.close();
    }

    private static int getDist(char[] s1_c,int begin,char[] s2_c,HashMap<String ,Integer> map ){
        String tmp = String.valueOf(Arrays.copyOfRange(s1_c,begin,begin + s2_c.length));
        if(map.containsKey(tmp)){
            return map.get(tmp);
        }
        int dist = 0;
        for(int i = 0; i < s2_c.length;i ++){
            if(s1_c[begin + i] != s2_c[i]){
                dist += 1;
            }
        }
        map.put(tmp,dist);
        return dist;
    }

    // Git Demo Test

    
}
