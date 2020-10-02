package com.kth;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lab42 {
    private boolean[] marked;
    private int[] edgeTo;
    private final int src;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\projects\\ID1020\\src\\com\\kth\\lab4data.dat");
        Scanner fi = new Scanner(file);
        lab32 ST = new lab32<>(10000);
        int index = 0;
        int k = 0;
        while (fi.hasNext()) {        //Sätter in alla statförkortningar i en ST och ger dem värden baserat på deras index
            String word = fi.next();
            if (ST.get(word) == null) {
                ST.put(word, index);
                System.out.println(word + " has " + index);
                index++;
            }
        }

        lab4graph Graph = new lab4graph(index);
        fi = new Scanner(file);
        while (fi.hasNext()) {        //Sätter in alla statförkortningar i en ST och ger dem värden baserat på deras index
            String first = fi.next();
            String next = fi.next();
            Graph.addEdge((Integer) ST.get(first), (Integer) ST.get(next));
        }

        lab42 BFS = new lab42(Graph, 0);
        BFS.bfs(Graph, Graph.V() - 1);
        for (int i = 0; i < Graph.V(); i++)
            System.out.println(ST.getKey(0) + " has a path to " + ST.getKey(i) + ": " + BFS.hasPathTo(i));
        Iterable<Integer> path = BFS.pathTo(20);
        int steps = 0;
        for(Integer vertex : path) {
            System.out.print(ST.getKey(vertex) + ", ");
            steps++;
        }
        System.out.println(steps + " stations");

        Scanner in = new Scanner(System.in);
        System.out.println("From which station?");
        String word = in.next();
        int end = (Integer)ST.get(word);
        System.out.println("To which station?");
        word = in.next();
        int start = (Integer)ST.get(word);

        lab42 BFS2 = new lab42(Graph, end);
        BFS2.bfs(Graph, Graph.V() - 1);
        Iterable<Integer> path2 = BFS2.pathFromTo(start,end);
        steps = 0;
        for(Integer vertex : path) {
            System.out.print(ST.getKey(vertex) + ", ");
            steps++;
        }
        System.out.println(steps + " stations");
    }
    public lab42(lab4graph G, int src){
        marked = new boolean[G.V()]; //Ny lista för alla markerade
        edgeTo = new int[G.V()]; //Ny lista för alla vägar
        this.src = src;
        bfs(G, src);    //Börjar utforska
    }

    private void bfs(lab4graph G, int src){
        Queue<Integer> queue = new Queue<Integer>();
        marked[src] = true;
        queue.enqueue(src);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w: G.adj(v))
                if(!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){ //Skapar en väg av hörn i stack path
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int i = v; i!= src; i = edgeTo[i])
            path.push(i);
        path.push(src);
        return path;
    }
    public Iterable<Integer> pathFromTo(int X, int Y){ //Skapar en väg av hörn i stack path
        if(!hasPathTo(X)) return null;
        Stack<Integer> path = new Stack<>();
        for(int i = X; i!= Y; i = edgeTo[i])
            path.push(i);
        path.push(Y);
        return path;
    }
}
