package com.kth;

import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Problem 4
 *
 * Använder sig av ett BST från boken.
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i BST. Sedan så söker den igenom BST för alla
 * ord som matchar in och returnerar dess index i listan. Search i BST sker med O(log n).
 *
 * För graf av BST/ST klicka nedanför, samt kort diskussion:
 * https://docs.google.com/spreadsheets/d/1m3qVGifkF5xmCZFUp0K7L6tDOU1Y3ewiaoxJueOcUXM/edit?usp=sharing
 *
 * För teorifråga 2:
 * https://docs.google.com/document/d/16G-05qpwWHv3APoWiVR37_pZDyDDyznpOtCocqtDM-s/edit?usp=sharing
 */
public class lab34<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) throws FileNotFoundException {
        lab34<String, Integer> ST = new lab34<>();
        File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\book.txt");
        Scanner in = new Scanner(book);
        Scanner sc = new Scanner(System.in);
        int k = 10000;
        int n = 0;
        //Fills the symbol table
        while(in.hasNext() && n++ != k){
            String word = in.next();
            if(ST.get(word) == null && (word.length()>0)) {
                ST.put(word, 1, n);
            }
            else if(word.length()>0){
                ST.put(word, ST.get(word)+1, n);
            }
        }
        while(true){
            System.out.println("Enter word to search for: ");
            String word = sc.next();
            ST.findWord(word);
        }
    }


    private Node root; // root of BST
    private class Node
    {
        private Key key; // key
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here
        private List<Integer> index = new ArrayList<>(); //Lagra ordets index
        public Node(Key key, Value val, int N, int n)
        { this.key = key; this.val = val; this.N = N; index.add(n); }
    }
    public int size()
    { return size(root); }
    private int size(Node x)
    {
        if (x == null) return 0;
        else return x.N;
    }
    public Value get(Key key)
    { return get(root, key); }    // See page 399.
    private Value get(Node x, Key key)
    { // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    private List getIndex(Node x, Key key){
        if(x==null) {System.out.println("no such word in text");
        return null;}
        int cmp = key.compareTo(x.key);
        if(cmp<0) return getIndex(x.left, key);
        else if(cmp>0) return getIndex(x.right, key);
        else return x.index;
    }
    public void put(Key key, Value val, int n)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val, n);
    }
    private Node put(Node x, Key key, Value val, int n)
    {
        // Change key’s value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1, n);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val, n);
        else if (cmp > 0) x.right = put(x.right, key, val, n);
        else {
            x.val = val;
            x.index.add(n);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }    // See page 399.

    // See page 407 for min(), max(), floor(), and ceiling().
    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }
    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
    // See page 409 for select() and rank().
    public Key select(int k)
    {
        return select(root, k).key;
    }
    private Node select(Node x, int k)
    { // Return Node containing key of rank k.
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }
    public int rank(Key key)
    { return rank(key, root); }
    private int rank(Key key, Node x)
    { // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
    // See page 411 for delete(), deleteMin(), and deleteMax().
    public void deleteMin()
    {
        root = deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key)
    { root = delete(root, key); }
    private Node delete(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right); // See page 407.
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    // See page 413 for keys().
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

    private void findWord(Key key){
        List<Integer> list = getIndex(root, key);
        if(list!=null)
            for(Integer index : list){
                System.out.println(index);
            }
    }
}
