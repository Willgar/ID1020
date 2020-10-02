package com.kth;

public class lab4DirectedEdge {
    private final int v; //Start
    private final int w; //Mål
    private final double weight; //Distans

    public lab4DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight()
    { return weight; }
    public int from()
    { return v; }
    public int to()
    { return w; }
    public String toString()
    { return String.format("%d->%d %.2f", v, w, weight); }
}
