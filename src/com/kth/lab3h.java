package com.kth;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ID1020
 * William Axbrink 2020-09-24
 * Lösning för labb 3 - Higher Assignment
 *
 * Använder sig av ett ordered array symbol table från boken eftersom dess användning av en Value arrays gör den lätt
 * att sortera för att hitta k:th ordet. Sorterar med MergeSort för att den är snabb men ändå stabil.
 * MergeSort är anpassad så att både Value arrayen och Key arrayen sorteras tillsammans baserat på värdena på Value.
 *
 * Main läser först in k antal ord eller tills EOF, och sorterar in  dem i ST. Sedan används en egen variant på
 * frequencyCountern för att hitta ordet som förekommer oftast samtidigt som tiden tas för att kunna jämföra.
 *
 */
public class lab3h<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;
    public static void main(String[] args) throws FileNotFoundException {
        lab3h<String, Integer> ST = new lab3h<String, Integer>(1000000);
        File book = new File("D:\\projects\\ID1020\\src\\com\\kth\\leipzig1m.txt");
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(book);
        int k = 10000;
        int n = 0;
        //Fyller tabellen
        while(in.hasNext()/*&& n++ != k*/)
        { // Build symbol table and count frequencies.
            String word = in.next();
            word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            word.replace("\"", "");
            if(ST.get(word) == null && (word.length()>2)) {
                ST.put(word, 1);
            }
            else if(word.length()>2){
                ST.put(word, ST.get(word)+1);
            }
        }

        ST.sort();

        while(true){
            System.out.println("Which k:th word do you want to see?");

            int kth = sc.nextInt();
            ST.kthWord(kth);

            System.out.println("Enter k:th index");
            System.out.println("Enter n:th index");
            kth = sc.nextInt();
            int nth = sc.nextInt();
            ST.kthWord(kth, nth);

        }

    }

    public lab3h(int capacity)
    { // See Algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size()
    { return N; }
    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }
    public void kthWord(int index){
        System.out.println ((String)keys[index]);
    }
    public void kthWord(int start, int stop){
        for(int i = start; i<=stop; i++){
            System.out.println ((String)keys[i]);
        }
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
    // See page 381.
    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        { vals[i] = val; return; }
        for (int j = N; j > i; j--)
        { keys[j] = keys[j-1]; vals[j] = vals[j-1]; }
        keys[i] = key; vals[i] = val;
        N++;
    }
    public Key min()
    { return keys[0]; }
    public Key max()
    { return keys[N-1]; }
    public Key select(int k)
    { return keys[k]; }
    public Key ceiling(Key key)
    {
        int i = rank(key);
        return keys[i];
    }
    //public Key floor(Key key)
    // See Exercise 3.1.17.
    // public Key delete(Key key)
    // See Exercise 3.1.16.
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


    int partition(Value[] arr, Key[] arr2, int low, int high){
        Value pivot = arr[high];
        int i = (low-1);

        for(int j = low; j<= high; j++){
            //Om något element är mindre än pivot
            if((Integer)arr[j] < (Integer)pivot){
                i++;
                Value temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                Key temp2 = arr2[i];
                arr2[i] = arr2[j];
                arr2[j] = temp2;
            }
        }
        Value temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        Key temp2 = arr2[i+1];
        arr2[i+1] = arr2[high];
        arr2[high] = temp2;

        return i+1;
    }
    void quickSort(Value[] arr, Key[] arr2, int low, int high){
        if(low<high){
            int pi = partition(arr, arr2, low, high);

            quickSort(arr, arr2, low, pi-1);
            quickSort(arr,arr2, pi+1, high);
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

    void sort(){
        sort(vals,keys, 0, N-1);
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

}

