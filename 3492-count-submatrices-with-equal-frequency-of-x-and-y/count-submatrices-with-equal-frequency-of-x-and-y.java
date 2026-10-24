class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] x = new int[n];
        int[] y = new int[n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            int rowX = 0;
            int rowY = 0;

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') {
                    rowX++;
                } else if (grid[i][j] == 'Y') {
                    rowY++;
                }

                x[j] += rowX;
                y[j] += rowY;

                if (x[j] > 0 && x[j] == y[j]) {
                    ans++;
                }
            }
        }

        return ans;
    }
}