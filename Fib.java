public class Fib {

    private static int[] cach = new int[100];
    public static void main(String[] args) {
        int input = 11;
        fib(input);
    }

    public static int fib(int n) {
        if (cach[n] != 0) {
            return cach[n];
        }
        if (n <= 1) { // base case
            return n;
        }

        int res = fib(n-1) + fib(n-2);
        System.out.println("Fib("+n+") is: " + res);
        cach[n] = res;

        return res;
    }
}
