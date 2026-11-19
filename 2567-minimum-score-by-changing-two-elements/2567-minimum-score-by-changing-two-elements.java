class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int changeTwoSmallest = nums[n - 1] - nums[2];
        int changeOneEach    = nums[n - 2] - nums[1];
        int changeTwoLargest = nums[n - 3] - nums[0];

        return Math.min(changeTwoSmallest,
               Math.min(changeOneEach, changeTwoLargest));
    }
}