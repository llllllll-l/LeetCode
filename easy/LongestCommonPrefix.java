package easy;
// 14
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};


        System.out.println(longestCommonPrefix(strs));
    }

    private static String longestCommonPrefix(String[] strs) {
        String result = strs[0];

        for (int i = 0; i < strs.length; i++) {
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length()-1);


                if  (result.isEmpty()) {
                    return "";
                }
            }
        }

        return result;
    }
}
