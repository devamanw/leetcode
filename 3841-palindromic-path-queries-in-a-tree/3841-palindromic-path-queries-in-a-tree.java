import java.util.*;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        char[] chars = s.toCharArray();

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] prefix = new int[n];


        int[] stack = new int[n];
        int[] iter = new int[n];

        int top = 0;
        int timer = 0;

        stack[0] = 0;
        parent[0] = 0;
        prefix[0] = 1 << (chars[0] - 'a');
        tin[0] = timer++;

        while (top >= 0) {
            int u = stack[top];

            if (iter[top] < adj[u].size()) {
                int v = adj[u].get(iter[top]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                prefix[v] = prefix[u] ^ (1 << (chars[v] - 'a'));
                tin[v] = timer++;

                stack[++top] = v;
                iter[top] = 0;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }


        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        Fenwick bit = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {

                    bit.update(tin[u], delta);
                    bit.update(tout[u] + 1, delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v, depth, up, LOG);

                int maskU = prefix[u] ^ bit.query(tin[u]);
                int maskV = prefix[v] ^ bit.query(tin[v]);

                int pathMask = maskU ^ maskV
                        ^ (1 << (chars[ancestor] - 'a'));

              
                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(
            int u, int v, int[] depth, int[][] up, int LOG) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = LOG - 1; j >= 0; j--) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }


        void update(int index, int value) {
            for (int i = index + 1; i <= n + 1; i += i & -i) {
                tree[i] ^= value;
            }
        }

        
        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}