package com.kth;

import edu.princeton.cs.algs4.Bag;

public class lab4EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<lab4DirectedEdge>[] adj;

    public lab4EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v = 0; v<V; v++){
            adj[v] = new Bag<lab4DirectedEdge>();
        }
    }
    public int V(){return V;}
    public int E(){return E;}
    public Bag<lab4DirectedEdge> adj(int v)
    { return adj[v]; }

    public void addEdge(lab4DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }
    public Iterable<lab4DirectedEdge> edges(){
        Bag<lab4DirectedEdge> bag = new Bag<lab4DirectedEdge>();
        for(int v = 0; v<V; v++)
            for(lab4DirectedEdge e : adj[v])
                bag.add(e);
        return bag;
    }

}
