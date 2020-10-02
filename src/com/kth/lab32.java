package com.kth;


import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Problem 2.1
 *
 * Använder sig av ett ordered array sybmol table från boken.
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i ST. Sedan används en egen variant på
 * frequencyCountern för att hitta ordet som förekommer oftast samtidigt som tiden tas för att kunna jämföra.
 *
 * För graf av BST/ST klicka nedanför, samt kort diskussion:
 * https://docs.google.com/spreadsheets/d/1m3qVGifkF5xmCZFUp0K7L6tDOU1Y3ewiaoxJueOcUXM/edit?usp=sharing
 */
public class lab32<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;
        public static void main(String[] args) throws FileNotFoundException {
            File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\book.txt");
            for(int k = 10;k<=80;k+=10) {
                long averageTime = 0;
                for (int q = 0; q <= 1000; q++) {
                    Scanner in = new Scanner(book);
                    lab32<String, Integer> ST = new lab32<String, Integer>(10000);
                    int n = 0;
                    //Fyller tabellen
                    long startTime = System.nanoTime();
                    while (in.hasNext() && n++ != k) {
                        String word = in.next();
                        if (ST.get(word) == null && (word.length() > 0)) {
                            ST.put(word, 1);
                        } else if (word.length() > 0) {
                            ST.put(word, ST.get(word) + 1);
                        }
                    }
                    averageTime += (System.nanoTime() - startTime);
                }
                System.out.println("Time for ST is " + averageTime / 1000 + " ns for " + k * 10 + " amount of words");
            }

            Scanner in = new Scanner(book);
            lab32<String, Integer> ST = new lab32<String, Integer>(100000);
            int n = 0;
            long startTime = System.nanoTime();
            while (in.hasNext() && n++ != 800) {
                String word = in.next();
                if (ST.get(word) == null && (word.length() > 0)) {
                    ST.put(word, 1);
                } else if (word.length() > 0) {
                    ST.put(word, ST.get(word) + 1);
                }
            }
            String topFreq = "";
            ST.put(topFreq, 0);
            ST.highestFrequency(topFreq);
        }

    public lab32(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public int size()
    { return N; }
    public Value get(Key key) //Hämtar värdet på nyckel
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }


    public Key getKey(Value val){
            for (Key key : keys)
                if(get(key) == val)
                    return key;
            return null;
    }

    private boolean isEmpty() {
            return N==0;
    }

    public int rank(Key key)
    {
        int lo = 0, hi = N-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val)
    { // Letar efter nyckel, annars skapar ny
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        { vals[i] = val; return; }
        for (int j = N; j > i; j--)
        { keys[j] = keys[j-1]; vals[j] = vals[j-1]; }
        keys[i] = key; vals[i] = val;
        N++;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }
    public boolean contains(Key key){
            return (get(key)!=null);
    }
        public void highestFrequency(Key topFreq){
            for(int i = 0; i<N-1; i++)
                if((Integer)vals[i]>((Integer)get(topFreq)))
                    topFreq = keys[i];

            System.out.println("-"+topFreq + "- is the top word with " + get(topFreq) + " occurances");
        }
    }
