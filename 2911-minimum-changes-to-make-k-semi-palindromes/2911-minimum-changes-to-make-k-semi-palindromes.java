class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1_000_000_000;

   
        int[][] cost = new int[n][n];

   
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minCost = INF;

              
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

             
                    for (int offset = 0; offset < d; offset++) {

                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {

            for (int parts = 1; parts <= k; parts++) {

         
                for (int j = i + 1; j < n; j++) {

                    if (dp[j + 1][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j] + dp[j + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
