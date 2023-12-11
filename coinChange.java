public class coinChange {
    public static void main(String[] args) {
        int payment = 17;
        int[] coins = {1, 5, 8, 10};

        System.out.println("Minimum #coins needed: "+minCoins(payment, coins));
    }

    private static int minCoins(int payment, int[] coins) {
        int[] arr = new int[payment +1];
        arr[0] = 0; // base case

        for (int amount = 1; amount <= payment; amount++) {
            arr[amount] = Integer.MAX_VALUE; // Initialize wth a large value

            for (int coin : coins) {
                if (amount - coin >= 0) {
                    arr[amount] = Math.min(arr[amount], arr[amount-coin]+1);
                }
            }
        }
        return arr[payment];
    }
}
