import java.util.HashMap;
import java.util.Map;

public class Fib2 {

    private static Map<Integer, Long> memo = new HashMap<>();
    public static void main(String[] args) {
        int fib = 11;
        System.out.println("Rec");
        fib(fib);
        System.out.println("Rec memo");
        fibMemo(fib);
        System.out.println("Iterative");
        fibIter(fib);
    }

    private static long fibIter(int n) {
        int[] res = new int[n+1];

        if (n <= 1) {
            return n;
        }

        res[0] = 0; res[1] = 1;

        for (int i = 2; i < n+1; i++) {
            res[i] = res[i-1] + res[i-2];
            System.out.println("Fib: "+ i+ " value: "+ res[i]);
        }

        return res[n];
    }

    private static long fibMemo(int n) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long res = fibMemo(n-1) + fibMemo(n-2);
        memo.put(n, res);
        System.out.println("Fib: "+n+" value: "+ res);
        return res;
    }

    private static long fib(int n) {
        if (n <= 1) {
            return n;
        }

        long res = fib(n-1) + fib(n-2);
        System.out.println("Fib: " + n + " value: "+res);
        return res;
    }

    
}
