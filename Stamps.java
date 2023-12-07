public class Stamps {
    public static void main(String[] args) {
        double payment = 17.0;
        double[] coins = {0.01,0.05,0.25,0.5,1.2};

        System.out.println(stamps(payment, coins));
    }

    private static double stamps(double payment, double[] coins) {
        double[] arr = new double[(int) (payment*100) +1];
        arr[0] = 0;


        for (int i = 1; i <= (payment*100); i++) {
            arr[i] = Integer.MAX_VALUE;

            for (double coin : coins) {
                if (i - (coin*100) >= 0) {
                    arr[i] = Math.min(arr[i], arr[(int) (i-(coin*100))]+1);
                }
            }
        }
        return arr[(int) payment];
    }
}
