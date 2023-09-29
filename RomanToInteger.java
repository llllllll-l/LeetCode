// 13

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        String s = "III";
        String s1 = "LVIII";

        

        romanToInt(s);
    }

    private static int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();

        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        char[] charArr = s.toCharArray();
        
        for (char c : charArr) {
            System.out.print(c+" ");
        }

        for (int i = 0; i < charArr.length; i++) {
            if (i < charArr.
        }


        return 0;
    }
}
