package com.kth;

import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Problem 4
 *
 * Använder sig av ett BST från boken för att sortera upp allting samt arraylistor i trädet som lägger in värdet efter
 * att den har placerats ut. Använder sig sedan av MergeSort för att sortera i descending order för att snabbt
 * kunna få fram värdet på k:th platsen. Tid för detta: ca 30-40 sekunder
 * .
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i BST.
 *
 */
public class lab3h2<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;
    private int index;
    public static void main(String[] args) throws FileNotFoundException {
        lab3h2<String, Integer> ST = new lab3h2<String, Integer>(500000000);
        File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\leipzig1m.txt");
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(book);
        long startTime = System.nanoTime();
        long wordCheck = 0;
        while(in.hasNext())
        { // Build symbol table and count frequencies.
            wordCheck++;
            String word = in.next();
            word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if(ST.get(word) == null ) {
                ST.put(word, 1);
            }
            else {
                ST.put(word, ST.get(word)+1);
            }
        }
        System.out.println(wordCheck);
        ST.sort();
        long endTime = System.nanoTime();
        System.out.println("Time to read and sort the leipzig1M text: " + (endTime-startTime)/1000000000 + " seconds" );
        while(true){
            try {
                System.out.println("Which k:th word do you want to see?");

                int kth = sc.nextInt();
                ST.kthWord(kth - 1);

                System.out.println("Enter k:th index");
                System.out.println("Enter n:th index");
                kth = sc.nextInt();
                int nth = sc.nextInt();
                ST.kthWord(kth-1, nth-1);
            }
            catch(Exception e){
                System.out.println("Something went wrong...");
            }
        }

    }
    public lab3h2(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    private Node root; // roten
    private class Node
    {
        private Key key; // nyckeln
        private Value val; // värdet
        private int keyIndex;
        private Node left, right; // koppling till andra noder
        private int N; // antalet noder kopplade till denna nod nedåt
        public Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; keys[index] = key; vals[index] = val; this.keyIndex = index; index++;}
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
    {
        // returnerar värdet som är kopplat till nyckeln i trädet rotad till x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val)
    {
        // Om nyckeln redan finns så ökar value samt arrayen vid nodens index. Annars skapas en ny med värdet o nyckeln.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else {
            x.val = val;
            vals[x.keyIndex] = val;
        }
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

    void sort(){
        sort(vals,keys, 0, index-1);
    }
    void sort(Value[] arr, Key[] arr2, int left, int right)
    {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;
            // Sort first and second halves
            sort(arr, arr2, left, mid);
            sort(arr,arr2, mid + 1, right);
            // Merge the sorted halves
            merge(arr,arr2, left, mid, right);
        }
    }
    private void merge(Value arr[], Key[] arr2, int left, int mid, int right)
    {
        //Storlek på subarray
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //Temporära arrays
        Value L[] = (Value[]) new Object[n1];
        Value R[] = (Value[]) new Object[n2];
        Key L2[] = (Key[]) new Comparable[n1];
        Key R2[] = (Key[]) new Comparable[n2];

        //Fyll i temp
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
            L2[i] = arr2[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
            R2[j] = arr2[mid + 1 + j];
        }



        int i = 0, j = 0;

        //Jämför höger och vänster subarrays värden för att fylla på array med.
        int k = left;
        while (i < n1 && j < n2) {
            if ((Integer)L[i] >= (Integer)R[j]) {
                arr[k] = L[i];
                arr2[k] = L2[i];
                i++;
            }
            else {
                arr[k] = R[j];
                arr2[k] = R2[j];
                j++;
            }
            k++;
        }

        //Fyller på array med eventuella överblivna delar av subarrays
        while (i < n1) {
            arr[k] = L[i];
            arr2[k] = L2[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            arr2[k] = R2[j];
            j++;
            k++;
        }
    }
    public void kthWord(int index){
        System.out.println ((String)keys[index]);
    }
    public void kthWord(int start, int stop){
        for(int i = start; i<=stop; i++){
            System.out.println ((String)keys[i]);
        }
    }
}
