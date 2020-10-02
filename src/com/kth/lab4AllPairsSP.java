package com.kth;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;

public class lab4AllPairsSP {
    private lab4h[] all;

    lab4AllPairsSP(lab4EdgeWeightedDigraph G){
        all = new lab4h[G.V()];
        for(int v = 0; v<G.V();v++)
            all[v] = new lab4h(G,v);
    }
    Iterable<lab4DirectedEdge> path(int s, int t){
        return all[s].pathTo(t);
    }
    double distTo(int s, int t){
        return all[s].distTo(t);
    }
}
