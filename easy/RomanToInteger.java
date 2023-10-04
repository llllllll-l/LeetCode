package easy;
// 13

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        String s = "III";
        String s1 = "LVIII";

        System.out.println(romanToInt(s1));
    }

    private static int romanToInt(String s) {
       /* HashMap<Character, Integer> m = new HashMap<>();

        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);


        int ans = 0; // initiate retuen 

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && m.get(s.charAt(i)) < m.get(s.charAt(i+1))) {
                ans -= m.get(s.charAt(i));
            } else {
                ans += m.get(s.charAt(i));
            }
        }


        return ans;*/

        int ans = 0, prev = 0, cur = 0;

        for (int i = s.length() -1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I' -> cur = 1;
                case 'V' -> cur = 5;
                case 'X' -> cur = 10;
                case 'L' -> cur = 50;
                case 'C' -> cur = 100;
                case 'D' -> cur = 500;
                case 'M' -> cur = 1000;
            }

            if (cur < prev) {
                ans -= cur;
            } else {
                ans += cur;
            }

            prev = cur;
        }



        return ans;
    }
}
