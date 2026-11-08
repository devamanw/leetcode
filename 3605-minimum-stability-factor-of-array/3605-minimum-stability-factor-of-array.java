class Solution {
    int[][] st;
    int[] log;
    int n;

    public int minStable(int[] nums, int maxC) {
        int[] bantorvixo = nums;
        n = bantorvixo.length;

        log = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int K = log[n] + 1;
        st = new int[K][n];

        for (int i = 0; i < n; i++) {
            st[0][i] = bantorvixo[i];
        }

        for (int k = 1; k < K; k++) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                st[k][i] = gcd(
                    st[k - 1][i],
                    st[k - 1][i + (1 << (k - 1))]
                );
            }
        }

        int low = 0, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (check(mid, maxC)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean check(int limit, int maxC) {
        int used = 0;
        int lastChosen = -1;

        int len = limit + 1;

        for (int i = 0; i + len <= n; i++) {

            int j = i + len - 1;


            if (query(i, j) >= 2) {


                if (lastChosen < i) {
                    used++;


                    lastChosen = j;

                    if (used > maxC) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

 
    private int query(int l, int r) {
        int len = r - l + 1;
        int k = log[len];

        return gcd(
            st[k][l],
            st[k][r - (1 << k) + 1]
        );
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
