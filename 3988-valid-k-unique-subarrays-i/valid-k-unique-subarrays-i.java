class Solution {
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int blockSize = Math.max(1, (int) Math.sqrt(n));

        Integer[] order = new Integer[q];

        for (int i = 0; i < q; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {
            int blockA = queries[a][0] / blockSize;
            int blockB = queries[b][0] / blockSize;

            if (blockA != blockB) {
                return Integer.compare(blockA, blockB);
            }

            if ((blockA & 1) == 0) {
                return Integer.compare(queries[a][1], queries[b][1]);
            }

            return Integer.compare(queries[b][1], queries[a][1]);
        });

        int[] freq = new int[100001];
        boolean[] ans = new boolean[q];

        int left = 0;
        int right = -1;
        int distinct = 0;
        int odd = 0;

        for (int idx : order) {
            int l = queries[idx][0];
            int r = queries[idx][1];

            while (left > l) {
                int value = nums[--left];
                if (freq[value] == 0) distinct++;
                if (freq[value] % 2 == 0) odd++;
                else odd--;
                freq[value]++;
            }

            while (right < r) {
                int value = nums[++right];
                if (freq[value] == 0) distinct++;
                if (freq[value] % 2 == 0) odd++;
                else odd--;
                freq[value]++;
            }

            while (left < l) {
                int value = nums[left++];
                if (freq[value] % 2 == 1) odd--;
                else odd++;
                freq[value]--;
                if (freq[value] == 0) distinct--;
            }

            while (right > r) {
                int value = nums[right--];
                if (freq[value] % 2 == 1) odd--;
                else odd++;
                freq[value]--;
                if (freq[value] == 0) distinct--;
            }

            ans[idx] = (distinct == k && odd == 0);
        }

        return ans;
    }
}