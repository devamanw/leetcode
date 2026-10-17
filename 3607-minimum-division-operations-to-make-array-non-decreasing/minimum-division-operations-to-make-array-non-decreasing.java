
class Solution {
    public int minOperations(int[] nums) {
        int operations = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i] = smallestPrimeFactor(nums[i]);

                if (nums[i] > nums[i + 1]) {
                    return -1;
                }

                operations++;
            }
        }

        return operations;
    }

    private int smallestPrimeFactor(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return i;
            }
        }

        return n;
    }
}
