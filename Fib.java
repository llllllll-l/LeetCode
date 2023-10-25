import java.util.HashMap;
import java.util.Map;

public class Fib {

    private static Map<Integer, Long> memo = new HashMap<>();
    
    public static void main(String[] args) {
        int s = 50;

        long sTime = System.currentTimeMillis();
        System.out.println(recFib(s));
        long eTime = System.currentTimeMillis() - sTime;
        System.out.println(eTime);

        long startTime = System.currentTimeMillis();
        System.out.println(iterFib(s));
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime);
    }


    public static long recFib(int n) { // using memorization
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long res = recFib(n-2) + recFib(n-1);
        memo.put(n, res);
        return res; 
    }

    public static int iterFib(int n) {
        int[] fibArr = new int[n+1];
        if (n <= 1) {
            return n;
        }
        fibArr[0] = 0; fibArr[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArr[i] = fibArr[i-2] + fibArr[i-1];
        }

        return fibArr[n];
    }
}
