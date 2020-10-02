package com.kth;

import edu.princeton.cs.algs4.Bag;

public class lab4digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public lab4digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v = 0; v<V; v++){
            adj[v] = new Bag<Integer>();
        }
    }
    public int V(){return V;}
    public int E(){return E;}

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public lab4digraph reverse(){
        lab4digraph R = new lab4digraph(V);
        for(int v = 0; v<V; v++)
            for(int w: adj(v))
                R.addEdge(w,v);
        return R;
    }
}
