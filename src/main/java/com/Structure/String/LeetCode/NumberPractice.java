package com.Structure.String.LeetCode;

public class NumberPractice {
    public static void main(String[] args) {

        System.out.println("是否是回文数：" + isPalindrome(-121));

        System.out.println("字符串反转：" + reverseWords("Let's take LeetCode contest"));
    }
    public static boolean isPalindrome(int x) {
        if(x < 0 || (x != 0 && x % 10 == 0)){
            return false;
        } else if (x < 10){
            return true;
        }
        int tmp = 0;
        while(x > tmp){
            tmp = tmp * 10 + x % 10;
            x = x / 10;
        }
        // 奇数位时候，tmp/10 去除中位数。
        return tmp == x || x == tmp / 10;
    }


    /**
     * 反转字符串
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * */
    public static String reverseWords(String s) {

        char[] result = s.toCharArray();
        System.out.println(result+"|"+result.length);
        int tab = 0;
        int head = 0;
        int tail = 0;
        while (tab < result.length){
            while(tab < result.length && result[tab] != ' '){
                tab += 1;
            }

            // 连续空字符怎么处理？
            if(tab == result.length - 1){
                tail = tab;
            } else {
                tail = tab - 1;
            }
            while (head < tail){
                char tmp = result[head];
                result[head] = result[tail];
                result[tail] = tmp;
                head += 1;
                tail -= 1;
            }

            tab += 1;
            head = tab;
        }
        return new String(result);
    }
}
