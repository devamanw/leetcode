
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long maximumScore(int[] nums, String s) {
        PriorityQueue<Integer> pq =
            new PriorityQueue<>(Collections.reverseOrder());

        long score = 0;

        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);

            if (s.charAt(i) == '1') {
                score += pq.poll();
            }
        }

        return score;
    }
}
