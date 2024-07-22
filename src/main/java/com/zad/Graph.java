package com.zad;

import java.util.*;

public class Graph {
    private List<Edge>[] adjList;
    private int n;
    private Map<String, Integer> memo;

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        memo = new HashMap<>();
    }

    public void addEdge(int a, int b, int weight) {
        adjList[a].add(new Edge(b, weight));
        adjList[b].add(new Edge(a, weight));
    }

    public List<Integer> findOptimalPath() {
        boolean[] visited = new boolean[n];
        List<Integer> currentPath = new ArrayList<>();
        List<Integer> bestPath = new ArrayList<>();
        currentPath.add(0);
        visited[0] = true;
        int minDifficultEdges = dfs(0, visited, currentPath, 0, bestPath);
        return bestPath.isEmpty() ? null : bestPath;
    }

    private int dfs(int current, boolean[] visited, List<Integer> currentPath, int difficultEdges, List<Integer> bestPath) {
        if (currentPath.size() == n) {
            if (bestPath.isEmpty() || difficultEdges < memo.getOrDefault(currentPath.toString(), Integer.MAX_VALUE)) {
                bestPath.clear();
                bestPath.addAll(new ArrayList<>(currentPath));
                memo.put(currentPath.toString(), difficultEdges);
            }
            return difficultEdges;
        }

        int bestEdges = Integer.MAX_VALUE;
        for (Edge edge : adjList[current]) {
            if (!visited[edge.to]) {
                visited[edge.to] = true;
                currentPath.add(edge.to);
                String state = currentPath.toString() + ":" + edge.to;
                int newDifficultEdges = difficultEdges + edge.weight;

                if (!memo.containsKey(state) || newDifficultEdges < memo.get(state)) {
                    memo.put(state, newDifficultEdges);
                    bestEdges = Math.min(bestEdges, dfs(edge.to, visited, currentPath, newDifficultEdges, bestPath));
                }

                currentPath.remove(currentPath.size() - 1);
                visited[edge.to] = false;
            }
        }

        return bestEdges;
    }
}
