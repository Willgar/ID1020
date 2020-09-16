package com.kth;

import java.util.Arrays;
import java.util.Random;
/**
 * ID1020
 * William Axbrink 2020-09-14
 * Lösning för labb 2 - Problem 6
 * En jämförelse av MergeSort kombinerat med Insertionsort och vanliga MergeSort. Jämför slumpmässigt fyllda arrays i
 * storleken n=50
 * Varje cutoff på sorteringsalgoritmen jämförs med 100 000 varianter på den storleken för att få fram en snittid.
 * Graf av resultaten från ett av testerna finnes via länken nedanför till Google SpreadSheet.
 * https://docs.google.com/spreadsheets/d/1rMBhqunVCJ8SZVE-dcaptNfpR1GaC2nhHkqimTzExiM/edit?usp=sharing
 *
 *
 */
public class lab26 {
    public static void main(String[] args) {
        for(int i = 1; i <= 30; i++){
            System.out.println(i + " cutoff");
            long mergeInsertTime = 0;
            long mergeTime = 0;
            for(int j = 0; j<100000; j++){
                int[] arrMergeInsert = createArray(50);
                int[] arrMerge = arrMergeInsert.clone();
                mergeInsertTime += measureMergeInsert(arrMergeInsert,i);
                mergeTime += measureMerge(arrMerge);
                if(!Arrays.equals(arrMerge, arrMergeInsert))
                    System.out.print("ERROR");
            }
            System.out.println("MergeSortInsert: " + (mergeInsertTime/100000) + " ns");
            System.out.println("MergeSort: " + (mergeTime/100000) + " ns");
        }
    }

    private static long measureMergeInsert(int arr[], int CUTOFF){
        long startTime = System.nanoTime();
        sortInsertionMerge(arr, 0, arr.length-1, CUTOFF);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureMerge(int arr[]){
        long startTime = System.nanoTime();
        sort(arr, 0, arr.length-1);
        long endTime = System.nanoTime();
        return endTime - startTime;
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
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.print("\n");
    }


    //MergeSort som använder sig av InsertionSort vid subarrays som är under en viss storlek
    static void mergeInsertionMerge(int arr[], int left, int mid, int right)
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

        //När rekursiviteten delat upp listorna till storlekar där n < gränsen så sorteras listorna med InsertionSort
    //Istället för att ytterliggare mergeSorta dem till mindre listor.
    private static void sortInsertionMerge(int[] arr, int left, int right, int CUTOFF)
    {
        if (left < right) {
            if(right-left < CUTOFF){
                arr = insertionSort(arr);
            } else{
                // Find the middle point
                int mid = (left + right) / 2;
                // Sort first and second halves
                sortInsertionMerge(arr, left, mid, CUTOFF);
                sortInsertionMerge(arr, mid + 1, right, CUTOFF);
                // Merge the sorted halves
                mergeInsertionMerge(arr, left, mid, right);
            }

        }
    }


    private static int[] insertionSort(int[] array){
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
    private static int[] swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    //Normala mergeSort funktionen
    private static void merge(int arr[], int left, int mid, int right)
    {
        //Storlek på subarray
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //Temporära arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

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
}
