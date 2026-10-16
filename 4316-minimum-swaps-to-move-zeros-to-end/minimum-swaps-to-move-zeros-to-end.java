
class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int zeroCount = 0;

      
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
        }


        int swaps = 0;

        for (int i = 0; i < n - zeroCount; i++) {
            if (nums[i] == 0) {
                swaps++;
            }
        }

        return swaps;
    }
}
