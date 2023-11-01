import java.util.HashMap;
import java.util.Map;

public class Fib3 {

    private static Map<Integer, Long> memo = new HashMap<>();
    public static void main(String[] args) {
        fib(11);
        
    }

    private static long fib(int n) {
        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long res = fib(n-1) + fib(n-2);
        memo.put(n, res);
        System.out.println("Fib: "+n+" value: "+res);
        return res;
    }
}
