package medium;
// 12
public class IntegerToRoman {
    public static void main(String[] args) {

        int num = 1949;
        System.out.println(intToRoman(num));
    }

    private static String intToRoman(int num) {
        
        StringBuilder roman = new StringBuilder();
        
        int [] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            
            while (num >= value) {
                roman.append(symbol);
                num -= value;
            }
        }

        return roman.toString();


    }
}
