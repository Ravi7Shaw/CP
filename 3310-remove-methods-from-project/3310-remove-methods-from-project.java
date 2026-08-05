class Solution {

    List<Integer>[] graph;
    boolean[] suspicious;

    void dfs(int u) {
        suspicious[u] = true;

        for (int v : graph[u]) {
            if (!suspicious[v]) {
                dfs(v);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : invocations)
            graph[e[0]].add(e[1]);

        suspicious = new boolean[n];
        dfs(k);

        for (int[] e : invocations) {
            int u = e[0], v = e[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    ans.add(i);
                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.add(i);
        }

        return ans;
    }
}
