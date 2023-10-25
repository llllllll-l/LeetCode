import java.util.HashMap;
import java.util.Map;

public class Fib {

    private static Map<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        int s = 11;

        System.out.println("Recursive");
        recFib(s);
        System.out.println("Iterative");
        iterFib(s);
    }

    private static long iterFib(int n) {
        int[] fibArr = new int[n+1];
        if (n <= 1) {
            return n;
        }

        fibArr[0] = 0; fibArr[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArr[i] = fibArr[i-1] + fibArr[i-2];
            System.out.println("Fib: "+i+" vaue: "+fibArr[i]);
        }

        return fibArr[n];

    }

    private static long recFib(int n) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long res = recFib(n-1) + recFib(n-2);
        memo.put(n, res);
        System.out.println("Fib: "+n+" value: "+ res);
        return res;
        
    }
}