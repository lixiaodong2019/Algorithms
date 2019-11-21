package graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    //顶点数目
    private int V;
    private int E;
    private Set<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public void addE(int v, int w) {
        if (v == w) {
            return;
        }
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addE(1, 2);
        graph.addE(2, 3);
        System.out.println(graph.toString());
    }
}
