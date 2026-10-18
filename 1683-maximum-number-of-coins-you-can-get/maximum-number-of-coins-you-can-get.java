class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int coins = 0;
        int n = piles.length;

        for (int i = n / 3; i < n; i += 2) {
            coins += piles[i];
        }

        return coins;
    }
}
