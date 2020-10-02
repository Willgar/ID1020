package com.kth;

import edu.princeton.cs.algs4.Bag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lab43 {
    private boolean[] marked;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\projects\\ID1020\\src\\com\\kth\\lab4data.dat");
        Scanner fi = new Scanner(file);
        lab32 ST = new lab32<>(10000);
        int index = 0;
        int k = 0;
        Bag<Integer> sources = new Bag<Integer>();
        while (fi.hasNext()) {        //Sätter in alla statförkortningar i en ST och ger dem värden baserat på deras index
            String word = fi.next();
            if (ST.get(word) == null) {
                ST.put(word, index);
                System.out.println(word + " has " + index);
                index++;
            }
            sources.add((Integer)ST.get(word));
        }

        lab4digraph Graph = new lab4digraph(index);
        fi = new Scanner(file);
        while (fi.hasNext()) {        //Sätter in alla statförkortningar i en ST och ger dem värden baserat på deras index
            String first = fi.next();
            String next = fi.next();
            Graph.addEdge((Integer) ST.get(first), (Integer) ST.get(next));
        }

        lab43 DFS = new lab43(Graph, sources);
        for (int v = 0; v < Graph.V(); v++)
            if (DFS.marked(v)) System.out.print(ST.getKey(v) + " ");
        System.out.println();


        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("From which station?");
            String word = in.next();
            int start = (Integer) ST.get(word);
            System.out.println("To which station?");
            word = in.next();
            int end = (Integer) ST.get(word);

            lab43 DFS2 = new lab43(Graph, start);
            if (DFS2.marked(end))
                System.out.println(ST.getKey(end) + " reached");
            else
                System.out.println(ST.getKey(end) + " not reachable");
        }
    }
    public lab43(lab4digraph G, int src){
        marked = new boolean[G.V()];
        dfs(G,src);
    }
    public lab43(lab4digraph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for(int src : sources)
            if(!marked[src]) dfs(G,src);
    }

     private void dfs(lab4digraph G, int v){
        marked[v] = true;
        for(int w: G.adj(v))
            if(!marked[w]) dfs(G,w);
     }
    private void dfs(lab4digraph G, int x, int y){
        marked[x] = true;
        for(int w: G.adj(x))
            if(!marked[w]) dfs(G,w, y);
    }
     public boolean marked(int v){return marked[v];}

}
