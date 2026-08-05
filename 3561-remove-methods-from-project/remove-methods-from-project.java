
import java.util.*;

class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Visited array
        boolean[] visited = new boolean[n];

        // DFS from suspicious method
        dfs(k, graph, visited);

        // Check if any safe method calls a suspicious method
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!visited[from] && visited[to]) {
                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        // Store remaining methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    public void dfs(int node, ArrayList<Integer>[] graph, boolean[] visited) {

        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }
}