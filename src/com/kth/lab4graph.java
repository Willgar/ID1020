package com.kth;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class lab4graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public lab4graph(int V){ //Skapar en ny graf.
        this.V = V; this.E = 0;
        adj = new Bag[V];
        for(int v = 0; v<V; v++){
            adj[v] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w){ //Skapar en koppling mellan två hörn
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
