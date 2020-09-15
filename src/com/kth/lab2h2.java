package com.kth;

import java.util.Random;
/**
 * ID1020
 * William Axbrink 2020-09-15
 * Lösning för labb 2 - Higher Assigment 2
 * En jämförelse av MergeSort  och QuickSort. Jämför slumpmässigt fyllda arrays i olika storlekar.
 * Varje storlek på arrayerna jämförs med 1000 varianter på den storleken för att få fram en snittid.
 * Graf av resultaten från ett av testerna finnes via länken nedanför till Google SpreadSheet.
 * https://docs.google.com/spreadsheets/d/1rMBhqunVCJ8SZVE-dcaptNfpR1GaC2nhHkqimTzExiM/edit?usp=sharing
 *
 * Allmänt så är QuickSort snabbare än MergeSort. Men med minneskomplexiteten och att quicksort har in-place memory
 * så är MergeSort bättre för länkade listor medan quicksort är bättre för arrays.
 *
 */
public class lab2h2 {
    public static void main(String[] args) {
        for(int i = 10000; i < 1000000; i+=10000){
            System.out.println(i + " elements");
            long mergeTime = 0;
            long quickTime = 0;
            for(int j = 0; j<1000; j++){
                int[] arrMerge = createArray(i);
                int[] arrQuick = arrMerge.clone();
                mergeTime += measureMerge(arrMerge);
                quickTime += measureQuick(arrQuick);
            }
            System.out.println("MergeSort: " + (mergeTime/1000) + " ns");
            System.out.println("Quicksort: " + (quickTime/1000) + " ns");
        }
        int arr[] = {10, 7, 8, 9, 1, 5,5,-1,4,12};
        quickSort(arr, 0, arr.length-1);
    }

    private static long measureMerge(int arr[]){
        long startTime = System.nanoTime();
        sort(arr, 0, arr.length-1);
        long endTime = System.nanoTime();
        //System.out.println("MergeSort: " + (timeElapsed) + " ns");
        return endTime - startTime;
    }
    private static long measureQuick(int arr[]){
        long startTime2 = System.nanoTime();
        quickSort(arr, 0, arr.length-1);
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
    static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = (low-1);

        for(int j = low; j<= high; j++){
            //Om något element är mindre än pivot
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
    static void quickSort(int[] arr, int low, int high){
        if(low<high){
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
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

}
