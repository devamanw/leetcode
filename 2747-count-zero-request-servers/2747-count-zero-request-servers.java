class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int q = queries.length;

        int[][] sortedQueries = new int[q][2];

        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries,
            (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[n + 1];
        int[] ans = new int[q];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int[] query : sortedQueries) {

            int time = query[0];
            int index = query[1];

            int start = time - x;

            while (right < logs.length &&
                   logs[right][1] <= time) {

                int server = logs[right][0];

                if (freq[server] == 0) {
                    active++;
                }

                freq[server]++;
                right++;
            }

            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    active--;
                }

                left++;
            }

            ans[index] = n - active;
        }

        return ans;
    }
}
