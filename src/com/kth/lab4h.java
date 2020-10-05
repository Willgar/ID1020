package com.kth;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lab4h {
    private lab4DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    private static lab4h[] all;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\projects\\ID1020\\src\\com\\kth\\NYC.txt");
        Scanner fi = new Scanner(file);
        lab4EdgeWeightedDigraph NYCGraph = new lab4EdgeWeightedDigraph(264346);
        fi.nextLine();fi.nextLine(); //Skippar de 2 första raderna bestående av info av datan
        for(int i = 0; i< 733846; i++) {        //Lägger in alla edges till grafen
            NYCGraph.addEdge(new lab4DirectedEdge(fi.nextInt(), fi.nextInt(), fi.nextInt()));
        }

        lab4DirectedEdge test1 = new lab4DirectedEdge(0,2,0.26);
        lab4DirectedEdge test2 = new lab4DirectedEdge(0,4,0.38);
        lab4DirectedEdge test3 = new lab4DirectedEdge(1,3,0.29);
        lab4DirectedEdge test4 = new lab4DirectedEdge(2,7,0.34);
        lab4DirectedEdge test5 = new lab4DirectedEdge(3,6,0.52);
        lab4DirectedEdge test6 = new lab4DirectedEdge(4,7,0.37);
        lab4DirectedEdge test7 = new lab4DirectedEdge(4,5,0.35);
        lab4DirectedEdge test8 = new lab4DirectedEdge(5,1,0.32);
        lab4DirectedEdge test9 = new lab4DirectedEdge(5,7,0.28);
        lab4DirectedEdge test10 = new lab4DirectedEdge(5,4,0.35);
        lab4DirectedEdge test11 = new lab4DirectedEdge(6,4,0.93);
        lab4DirectedEdge test12 = new lab4DirectedEdge(6,0,0.58);
        lab4DirectedEdge test13 = new lab4DirectedEdge(6,2,0.40);
        lab4DirectedEdge test14 = new lab4DirectedEdge(7,3,0.39);
        lab4DirectedEdge test15 = new lab4DirectedEdge(7,5,0.28);

        lab4EdgeWeightedDigraph testGraph = new lab4EdgeWeightedDigraph(8);
        testGraph.addEdge(test1);
        testGraph.addEdge(test2);
        testGraph.addEdge(test3);
        testGraph.addEdge(test4);
        testGraph.addEdge(test5);
        testGraph.addEdge(test6);
        testGraph.addEdge(test7);
        testGraph.addEdge(test8);
        testGraph.addEdge(test9);
        testGraph.addEdge(test10);
        testGraph.addEdge(test11);
        testGraph.addEdge(test12);
        testGraph.addEdge(test13);
        testGraph.addEdge(test14);
        testGraph.addEdge(test15);

        lab4h testSP2 = new lab4h(testGraph, 0);
        for(int i = 1; i<= 7;i++) {
            System.out.println(testSP2.distTo(i));
            System.out.println(testSP2.pathTo(i));
        }

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("From which station?");
            int start = in.nextInt();
            System.out.println("Which middle station?");
            int middle = in.nextInt();
            System.out.println("To which station?");
            int end = in.nextInt();
            lab4h SPAB = new lab4h(NYCGraph, start);
            lab4h SPBC = new lab4h(NYCGraph, middle);
            System.out.println("Shortest distance from A to C with a middle stop at B is: " + (SPAB.distTo(middle)+SPBC.distTo(end)) + " meter");
        }
    }

    private lab4h(lab4EdgeWeightedDigraph G, int src){
        edgeTo = new lab4DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for(int v = 0; v<G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;       //Sätter samtliga distans från src till andra hörn till oändlighet
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;        //Distansen till src(sig själv) är 0.
        pq.insert(0,0.0);
        while(!pq.isEmpty())    //Relaxar grafen tills PQ är tom
            relax(G, pq.delMin());
    }
    private void relax(lab4EdgeWeightedDigraph G, int v){
        for(lab4DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w]>distTo[v] + e.weight()){   //Kollar ifall distansen till hörnet är kortare än föregående väg
                distTo[w] = distTo[v]+e.weight();   //Om hörnet ej har besökts än så är distansen INF och då garanterat kortare
                edgeTo[w] = e;
                if(pq.contains(w))
                    pq.change(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
    private double distTo(int v){
        return distTo[v];
    }
    private boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    private Iterable<lab4DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<lab4DirectedEdge> path = new Stack<lab4DirectedEdge>();
        for (lab4DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

}
