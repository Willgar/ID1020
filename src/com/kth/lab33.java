package com.kth;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Problem 3
 *
 * Använder sig av en Hashing with separate chaining ST från boken 3.5
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i ST baserat på deras hashcode.
 * Sedan så printas antalet values för varje använd key ut vilket ger resultatet att alla har ca 100 element var i sig
 * av de 10000 orden som lästes in från texten.
 *
 */
public class lab33<Key, Value>  {
    public static void main(String[] args) throws FileNotFoundException {
        lab33<String, Integer> ST = new lab33<String, Integer>();
        File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\book.txt");
        Scanner in = new Scanner(book);
        int k = 10000;
        int n = 0;
        //Fills the symbol table
        while(in.hasNext() && n++ != k){
            String word = in.next();
            if(ST.get(word) == null && (word.length()>0)) {
                ST.put(word, 1);
            }
            else if(word.length()>0){
                ST.put(word, ST.get(word)+1);
            }
        }
        ST.printST();
    }
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public lab33(){
        this(100);
    }
    public lab33(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i <M; i++){
            st[i] = new SequentialSearchST();
        }
    }
    private int hash(Key key){
        return(key.hashCode() & 0x7fffffff % M);
    }
    public Value get(Key key)
    { return (Value) st[hash(key)].get(key); }

    public void put(Key key, Value val)
    { st[hash(key)].put(key, val); }

    public void printST(){
        for(int i = 0; i<M; i++){
            if(!st[i].isEmpty()){
                System.out.print(st[i].size()+ ", ");
            }

        }
    }


}
