package com.kth;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.io.*;
import java.util.Scanner;

public class lab41 {
    private boolean[] marked;
    private int[] edgeTo;
    private final int src;

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\projects\\ID1020\\src\\com\\kth\\lab4data.dat");
        Scanner fi = new Scanner(file);
        lab32 ST = new lab32<>(10000);      //Lagra stationerna och använda deras int-värde för graferna.
        int index = 0;                              //Hålla räkning på antalet olika stationer
        while (fi.hasNext()) {        //Sätter in alla statförkortningar i en ST och ger dem värden baserat på deras index
            String word = fi.next();
            if (ST.get(word) == null) {   //Vi bryr oss ej om hur många av varje station det finns. Bara hur många olika det finns
                ST.put(word, index);
                index++;
            }
        }

        lab4graph Graph = new lab4graph(index);
        fi = new Scanner(file);
        while (fi.hasNext()) {        //Lägger in alla edges till grafen
            String first = fi.next();
            String next = fi.next();
            Graph.addEdge((Integer) ST.get(first), (Integer) ST.get(next));
        }

        lab41 DFS = new lab41(Graph, 0);
        DFS.dfs(Graph, Graph.V() - 1);
        for (int i = 0; i < Graph.V(); i++)
            System.out.println(ST.getKey(0) + " has a path to " + ST.getKey(i) + ": " + DFS.hasPathTo(i));

        Iterable<Integer> path = DFS.pathTo((Integer)ST.get("CT")); //Kollar ett exempel på väg till CT
        int steps = 0;
        for(Integer vertex : path) {
            System.out.print(ST.getKey(vertex) + ", ");
            steps++;
        }
        System.out.println(steps + " stations");

        Scanner in = new Scanner(System.in);        //För att användaren ska själv välja mellan stationer att åka till
        System.out.println("From which station?");
        String word = in.next();
        int start = (Integer)ST.get(word);
        System.out.println("To which station?");
        word = in.next();
        int end = (Integer)ST.get(word);

        lab41 DFS2 = new lab41(Graph, start);         //Skapar en ny DFS där src är den starten man angav
        DFS2.dfs(Graph, Graph.V() - 1);
        Iterable<Integer> path2 = DFS2.pathTo(end);   //Kollar stigen mellan src/start och end
        steps = 0;
        for(Integer vertex : path2) {
            System.out.print(ST.getKey(vertex) + ", ");
            steps++;
        }
        System.out.println(steps + " stations");
    }
    public lab41(lab4graph G, int src){
        marked = new boolean[G.V()]; //Ny lista för alla markerade
        edgeTo = new int[G.V()]; //Ny lista för alla vägar
        this.src = src;
        dfs(G, src);    //Börjar utforska
    }
    private void dfs(lab4graph G, int v){
        marked[v] = true; //Markerar nuvarande hörnet som markerad
        for(int w: G.adj(v))    //Går igenom alla adjunkta hörn
            if(!marked[w]){     //Går enbart till de adjunkta hörn som ej redan blivit besökta
                edgeTo[w] = v;
                dfs(G,w);       //Utforskar hörnets andra vägar
            }
    }
    private boolean hasPathTo(int v){
        return marked[v];                   //temp tas bort ifall oanvänd
    }
    public Iterable<Integer> pathTo(int v){ //Skapar en väg av hörn i stack path
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int i = v; i!= src; i = edgeTo[i])
            path.push(i);
        path.push(src);
        return path;
    }
}

