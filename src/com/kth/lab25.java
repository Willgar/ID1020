package com.kth;
/**
 * ID1020
 * William Axbrink 2020-09-14
 * Lösning för labb 2 - Problem 5
 * En jämförelse av InsertionSort och MergeSort. Jämför slumpmässigt fyllda arrays i olika storlekar.
 * Varje storlek på array jämförs med 10 000 varianter på den storleken för att få fram en snittid.
 * Graf av resultaten från ett av testerna finnes via länken nedanför till Google SpreadSheet.
 * https://docs.google.com/spreadsheets/d/1rMBhqunVCJ8SZVE-dcaptNfpR1GaC2nhHkqimTzExiM/edit?usp=sharing
 *
 * Från testerna och grafen kan man se att för listor med upp till 100 element så vinner InsertionSort oftare men efter 100 så vinner MergeSort
 */

import java.util.Random;
public class lab25 {
    public static void main(String[] args) {
        /*Uppgift 2.5*/
        for(int i = 1; i < 8193; i=i*2){
            System.out.println(i + " elements");
            long mergeTime = 0;
            long insertionTime = 0;
            for(int j = 0; j<10000; j++){
                int[] arrMerge = createArray(i);
                int[] arrInsertion = arrMerge.clone();
                mergeTime += measureMerge(arrMerge);
                insertionTime += measureInsertion(arrInsertion);
            }
            System.out.println("MergeSort: " + (mergeTime/10000) + " ns");
            System.out.println("InsertionSort: " + (insertionTime/10000) + " ns");
        }

    }

    private static long measureMerge(int arr[]){
        long startTime = System.nanoTime();
        sort(arr, 0, arr.length-1);
        long endTime = System.nanoTime();
        //System.out.println("MergeSort: " + (timeElapsed) + " ns");
        return endTime - startTime;
    }
    private static long measureInsertion(int arr[]){
        long startTime2 = System.nanoTime();
        arr = insertionSort(arr);
        long endTime2 = System.nanoTime();
        //System.out.println("InsertionSort: " + (timeElapsed2) + " ns");
        return endTime2 - startTime2;
    }
    private static int[] createArray(int n){
        Random rand = new Random();
        int[] array = new int[n];
        for(int i = 0; i<n;i++){
            array[i] = rand.nextInt(100000);
        }
        return array;
    }

    static void printArray(int array[]){
        for (int value : array) {
            System.out.print(value + ", ");
        }
        System.out.print("\n");
    }

    private static void merge(int arr[], int left, int mid, int right)
    {
        //Storlek på subarray
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //Temporära arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        //Fyll i temp
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];



        int i = 0, j = 0;

        //Jämför höger och vänster subarrays värden för att fylla på array med.
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //Fyller på array med eventuella överblivna delar av subarrays
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    static void sort(int arr[], int left, int right)
    {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;
            // Sort first and second halves
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }


    static int[] insertionSort(int array[]){
        //For loop för att loopa igenom hela arrayen
        for(int i = 1; i<array.length;i++){
            /**Jämför två element för att se ifall den framför är mindre än den framför. Om de är True så byter dem plats
             och då jämförs det elementet med resten av listan bakifrån.*/
            if(array[i]<array[i-1]){
                array = swap(array, i, i-1);
                /**Jämför värdet med resten av listan med ny variabel j så huvudloopen förblir på samma index*/
                for(int j = i-1; j>0;j--){
                    if(array[j]<array[j-1]){
                        array = swap(array, j, j-1);
                    }
                }
            }
        }
        return array;
    }

    //Byter plats på punkterna i i och j. Skriver ut högt för demonstreringssyfte vilka två element som bytes ut.
    //Printar även ut hela array i samms syfte.
    static int[] swap(int array[], int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }
}