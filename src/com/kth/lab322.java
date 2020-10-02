package com.kth;

import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Problem 2.2
 *
 * Använder sig av ett Binary Search Tree från boken.
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i ST. Sedan används en egen variant på
 * frequencyCountern för att hitta ordet som förekommer oftast samtidigt som tiden tas för att kunna jämföra.
 *
 * För graf av BST/ST klicka nedanför, samt kort diskussion:
 * https://docs.google.com/spreadsheets/d/1m3qVGifkF5xmCZFUp0K7L6tDOU1Y3ewiaoxJueOcUXM/edit?usp=sharing
 */
public class lab322<Key extends Comparable<Key>, Value>
{
    public static void main(String[] args) throws FileNotFoundException {
        File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\book.txt");
        for(int k = 10;k<=80;k+=10) {
            long averageTime = 0;
            for(int q =0; q<=1000;q++) {
                Scanner in = new Scanner(book);
                lab322<String, Integer> ST = new lab322<String, Integer>();
                int n = 0;
                //Fyller tabellen
                long startTime = System.nanoTime();

                while (in.hasNext() && n++ != k * 10) {
                    String word = in.next();
                    if (ST.get(word) == null && (word.length() > 0)) {
                        ST.put(word, 1);
                    } else if (word.length() > 0) {
                        ST.put(word, ST.get(word) + 1);
                    }
                }
                averageTime+= (System.nanoTime()-startTime);
            }
            System.out.println("Time for ST is " + averageTime/1000 + " ns for " + k * 10 + " amount of words");

        }
        Scanner in = new Scanner(book);
        lab322<String, Integer> ST = new lab322<String, Integer>();
        int n = 0;
        long startTime = System.nanoTime();
        while (in.hasNext() && n++ != 80 * 100) {
            String word = in.next();
            if (ST.get(word) == null && (word.length() > 0)) {
                ST.put(word, 1);
            } else if (word.length() > 0) {
                ST.put(word, ST.get(word) + 1);
            }
        }
        String topFreq = "";
        ST.put(topFreq, 0);
        for(String words: ST.keys()){
            if(ST.get(topFreq)<ST.get(words))
                topFreq = words;

        }
        System.out.println("-"+topFreq + "- is the top word with " + ST.get(topFreq) + " occurances");
    }

    private Node root; // Roten
    private class Node
    {
        private Key key; // nyckeln
        private Value val; // värdet
        private Node left, right;
        private int N;
        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; }
    }
    public int size()
    { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        else return x.N;
    }
    public Value get(Key key)
    { return get(root, key); }
    private Value get(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    public void put(Key key, Value val)
    { // Söker efter nod, skapar ny nod annars
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val)
    {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Iterable<Key> keys()
    { return keys(min(), max()); }
    //Går till botten av högerdelen av trädet för att hitta största. Rekursivt.
    private Key max() {
        return max(root).key;
    }

    private Node max(Node n){
        if(n.right == null) return n;
        return max(n.right);
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}

