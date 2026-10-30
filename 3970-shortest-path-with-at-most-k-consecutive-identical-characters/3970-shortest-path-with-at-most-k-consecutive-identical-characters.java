import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[2], b[2])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 1, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int u = (int) cur[0];
            int run = (int) cur[1];
            long cost = cur[2];

            if (cost != dist[u][run]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextRun = labels.charAt(u) == labels.charAt(v)
                    ? run + 1
                    : 1;

                if (nextRun > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[v][nextRun]) {
                    dist[v][nextRun] = newCost;
                    pq.offer(new long[]{v, nextRun, newCost});
                }
            }
        }

        return -1;
    }
}