
class Solution {
    public long countPairs(int[] nums, int k) {
        Map<Integer, Long> freq = new HashMap<>();
        long ans = 0;

        for (int num : nums) {
            int g = gcd(num, k);

            for (Map.Entry<Integer, Long> entry : freq.entrySet()) {
                int previousGcd = entry.getKey();

                if ((long) g * previousGcd % k == 0) {
                    ans += entry.getValue();
                }
            }

            freq.put(g, freq.getOrDefault(g, 0L) + 1);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}