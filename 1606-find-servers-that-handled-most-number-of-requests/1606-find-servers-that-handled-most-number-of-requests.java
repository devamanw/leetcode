
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        int[] handled = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                available.add(busy.poll()[1]);
            }

            if (available.isEmpty()) continue;

            int preferred = i % k;

            Integer server = available.ceiling(preferred);
            if (server == null) {
                server = available.first();
            }

            available.remove(server);
            handled[server]++;

            busy.offer(new int[]{time + load[i], server});
        }

        int max = Arrays.stream(handled).max().getAsInt();

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (handled[i] == max) {
                answer.add(i);
            }
        }

        return answer;
    }
}
