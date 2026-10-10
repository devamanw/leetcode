
import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {

        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        long total = 0;

        // Step 1: Calculate absolute differences
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
        }

        // All differences can become zero
        if (k >= total) {
            return 0;
        }

        // Step 2: Sort differences in descending order
        Arrays.sort(diff);

        for (int i = 0; i < n / 2; i++) {
            int temp = diff[i];
            diff[i] = diff[n - 1 - i];
            diff[n - 1 - i] = temp;
        }

        // Step 3: Greedy level reduction
        for (int i = 0; i < n; i++) {

            int next = (i == n - 1) ? 0 : diff[i + 1];

            long cost = (long) (i + 1) * (diff[i] - next);

            if (k >= cost) {
                k -= cost;
            } else {
                long reduce = k / (i + 1);
                int remainder = (int) (k % (i + 1));

                int level = (int) (diff[i] - reduce);

                for (int j = 0; j <= i; j++) {
                    diff[j] = level;
                    if (j < remainder) {
                        diff[j]--;
                    }
                }

                k = 0;
                break;
            }
        }

        // Step 4: Calculate squared sum
        long answer = 0;

        for (int d : diff) {
            answer += (long) d * d;
        }

        return answer;
    }
}
