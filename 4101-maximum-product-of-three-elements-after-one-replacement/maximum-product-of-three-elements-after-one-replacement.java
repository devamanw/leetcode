
import java.util.Arrays;

class Solution {
    public long maxProduct(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        long a = nums[0];      
        long b = nums[1];      
        long c = nums[n - 2];   
        long d = nums[n - 1];  

        long x = 100000L;

        long case1 = a * b * x;
        long case2 = c * d * x;
        long case3 = -a * d * x;

        return Math.max(case1, Math.max(case2, case3));
    }
}
