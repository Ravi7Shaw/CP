import java.util.*;

class Solution {
    public int maximumWidth(int[] planks) {

        HashMap<Integer, Integer> cnt = new HashMap<>();

        // Count frequency of each height
        for (int h : planks) {
            cnt.put(h, cnt.getOrDefault(h, 0) + 1);
        }

        HashMap<Integer, Integer> res = new HashMap<>();

        List<Integer> heights = new ArrayList<>(cnt.keySet());

        // Pair different heights
        for (int i = 0; i < heights.size(); i++) {
            for (int j = i + 1; j < heights.size(); j++) {

                int a = heights.get(i);
                int b = heights.get(j);

                int sum = a + b;
                int pairs = Math.min(cnt.get(a), cnt.get(b));

                res.put(sum, res.getOrDefault(sum, 0) + pairs);
            }
        }

        // Pair same heights
        for (int a : heights) {
            int sum = a * 2;
            int pairs = cnt.get(a) / 2;

            res.put(sum, res.getOrDefault(sum, 0) + pairs);
        }

        // Existing planks themselves
        for (int a : heights) {
            res.put(a, res.getOrDefault(a, 0) + cnt.get(a));
        }

        int ans = 1;

        for (int val : res.values()) {
            ans = Math.max(ans, val);
        }

        return ans;
    }
}