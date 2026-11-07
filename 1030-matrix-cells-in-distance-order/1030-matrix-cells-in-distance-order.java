class Solution {
    public int[][] allCellsDistOrder(
            int rows, int cols, int rCenter, int cCenter) {

        int[][] ans = new int[rows * cols][2];
        boolean[][] visited = new boolean[rows][cols];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{rCenter, cCenter});
        visited[rCenter][cCenter] = true;

        int index = 0;

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();

            int r = cell[0];
            int c = cell[1];

            ans[index][0] = r;
            ans[index][1] = c;
            index++;

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return ans;
    }
}
