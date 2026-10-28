class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int limit = 1024;

        boolean[][] prev = new boolean[n][limit];
        boolean[][] curr = new boolean[n][limit];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < limit; x++) {
                    curr[j][x] = false;
                }

                int val = grid[i][j];

                if (i == 0 && j == 0) {
                    curr[j][val] = true;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < limit; x++) {
                        if (prev[j][x]) {
                            curr[j][x ^ val] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < limit; x++) {
                        if (curr[j - 1][x]) {
                            curr[j][x ^ val] = true;
                        }
                    }
                }
            }

            boolean[][] temp = prev;
            prev = curr;
            curr = temp;
        }

        for (int x = 0; x < limit; x++) {
            if (prev[n - 1][x]) {
                return x;
            }
        }

        return 0;
    }
}