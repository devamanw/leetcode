
import java.util.*;

class Solution {
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);

        int n = nums.length;
        long[] prefix = new long[n + 1];

        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        List<Long> ans = new ArrayList<>();

        for (int q : queries) {
           
            int left = lowerBound(nums, q);

            long leftCost = (long) q * left - prefix[left];

            int right = upperBound(nums, q);

            long rightCost =
                (prefix[n] - prefix[right])
                - (long) (n - right) * q;

            ans.add(leftCost + rightCost);
        }

        return ans;
    }

    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private int upperBound(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
