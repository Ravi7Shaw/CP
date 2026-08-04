
   


import java.util.*;

class Solution {

    static class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int n) {
            size = 1;
            while (size < n) size <<= 1;
            tree = new int[size * 2];
        }

        void update(int pos) {
            pos += size;
            tree[pos]++;

            while (pos > 1) {
                pos >>= 1;
                tree[pos] = tree[pos * 2] + tree[pos * 2 + 1];
            }
        }

        int query(int l, int r) {
            if (l > r) return 0;

            l += size;
            r += size;

            int ans = 0;

            while (l <= r) {
                if ((l & 1) == 1) ans += tree[l++];
                if ((r & 1) == 0) ans += tree[r--];

                l >>= 1;
                r >>= 1;
            }

            return ans;
        }
    }

    public int countRatioSubarrays(int[] nums, int a, int b) {

        int n = nums.length;

        int[] even = new int[n + 1];
        int[] odd = new int[n + 1];
        long[] value = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            even[i] = even[i - 1];
            odd[i] = odd[i - 1];

            if ((nums[i - 1] & 1) == 0)
                even[i]++;
            else
                odd[i]++;

            value[i] = 1L * b * even[i] - 1L * a * odd[i];
        }

        long[] temp = value.clone();
        Arrays.sort(temp);

        ArrayList<Long> vals = new ArrayList<>();
        for (long x : temp) {
            if (vals.isEmpty() || vals.get(vals.size() - 1) != x)
                vals.add(x);
        }

        int m = vals.size();

        SegmentTree[] trees = new SegmentTree[n + 1];
        for (int i = 0; i <= n; i++)
            trees[i] = new SegmentTree(m);

        int ans = 0;

        for (int i = 0; i <= n; i++) {

            int idx = Collections.binarySearch(vals, value[i]);

            for (int o = 0; o < odd[i]; o++) {
                ans += trees[o].query(idx, m - 1);
            }

            trees[odd[i]].update(idx);
        }

        return ans;
    }
}