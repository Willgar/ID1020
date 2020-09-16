package com.kth;

import java.util.Arrays;
import java.util.Random;
/**
 * ID1020
 * William Axbrink 2020-09-16
 * Lösning för labb 2 - Higher Assigment 3
 * En jämförelse av QuickSort där pivotelementet väljs från medianen av de element från listan  och QuickSort med
 * pivotelement från början och arbetar "bakifrån". Jämför slumpmässigt fyllda arrays i olika storlekar.
 * Varje storlek på arrayerna jämförs med 10 000 varianter på den storleken för att få fram en snittid.
 * Graf av resultaten från ett av testerna finnes via länken nedanför till Google SpreadSheet.
 * https://docs.google.com/spreadsheets/d/1rMBhqunVCJ8SZVE-dcaptNfpR1GaC2nhHkqimTzExiM/edit?usp=sharing
 *
 * Det var ingen större skillnad på resultatet men QuickSort med pivot från low var lite snabbare.
 */
public class lab2h3 {
    public static void main(String[] args) {
        int[] array = {5,8,2,6,4,-2,-4,10};
        int[] array2 = array.clone();
        printArray(array);
        quickSortMedian(array, 0, array.length-1);
        quickSort(array2, 0, array2.length-1);
        printArray(array);
        printArray(array2);

        for(int i = 1; i < 32761; i=i*2){
            System.out.println(i + " elements");
            long medianTime = 0;
            long quickTime = 0;
            for(int j = 0; j<10000; j++){
                int[] arrMerge = createArray(i);
                int[] arrQuick = arrMerge.clone();
                medianTime += measureQuickMedian(arrMerge);
                quickTime += measureQuick(arrQuick);
                if(!Arrays.equals(array, array2))
                     System.out.println("ERROR");
            }
            System.out.println("QuickMedianSort: " + (medianTime/10000) + " ns");
            System.out.println("Quicksort: " + (quickTime/10000) + " ns");
        }
    }

    private static long measureQuickMedian(int arr[]){
        long startTime = System.nanoTime();
        quickSortMedian(arr, 0, arr.length-1);
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
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.print("\n");
    }
    static int partitionMedian(int[] arr, int low, int high){
        int pivotMid = median(arr, low, high);
        int i = (low-1);

        for(int j = low; j<= high; j++){
            //Om något element är mindre än pivot
            if(arr[j] < pivotMid){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                //System.out.println("switching " + arr[j] + " with " + arr[i]);
            }
        }
        //printArray(arr);
        if(arr[i + 1]>arr[high]) {
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
        }
        return i+1;
    }
    static void quickSortMedian(int[] arr, int low, int high){
        if(low<high){
            int pi = partitionMedian(arr, low, high);

            quickSortMedian(arr, low, pi-1);
            quickSortMedian(arr, pi+1, high);
        }
    }
    static int median(int[] array, int low, int high){
        int mid = high/2;
        int[] arr = {array[low], array[mid], array[high]};

        if (arr[0] > arr[1]) arr = swap(arr, 0,1 );
        if (arr[1] > arr[2])  arr = swap(arr, 1,2 );
        if (arr[0] > arr[1])  arr = swap(arr, 0,1 );


        return arr[1];
    }
    static int[] swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }


    static void quickSort(int[] arr, int low, int high){
        if(low<high){
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    static int partition(int[] arr, int low, int high){
        int pivotMid = arr[low];
        int i = (high+1);

        for(int j = high; j>= low; j--){
            //Om något element är mindre än pivot
            if(arr[j] > pivotMid){
                i--;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i-1];
        arr[i-1] = arr[low];
        arr[low] = temp;

        return i-1;
    }
}
