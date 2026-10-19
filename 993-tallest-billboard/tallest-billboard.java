import java.util.*;

class Solution {
    public int tallestBillboard(int[] rods) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);

        for (int rod : rods) {
            Map<Integer, Integer> next = new HashMap<>(dp);

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int diff = entry.getKey();
                int shorter = entry.getValue();


                int newDiff = diff + rod;
                next.put(newDiff,
                    Math.max(next.getOrDefault(newDiff, 0), shorter));

                int newDiff2 = Math.abs(diff - rod);
                int newShorter = shorter + Math.min(diff, rod);

                next.put(newDiff2,
                    Math.max(next.getOrDefault(newDiff2, 0), newShorter));
            }

            dp = next;
        }

        return dp.get(0);
    }
}
