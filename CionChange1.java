public class CionChange1 {
    public static void main(String[] args)  {
        int[] coins = {1,5,8,10};
        int payment = 16;

        System.out.println(minChange(payment, coins));
    }

    private static int minChange(int M, int[] coins) {
        int[] res = new int[M+1];
        res[0] = 0; // base case

        for (int i = 1; i <= M; i++) {
            res[i] = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (i - coin >= 0) {
                    res[i] = Math.min(res[i], res[i-coin]+1);
                }
            }
        }

        return res[M];
    }
}
