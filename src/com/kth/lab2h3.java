package com.kth;

public class lab2h3 {
    public static void main(String[] args) {

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
    static int median(int[] array){
        int[] arr = {array[0], array[array.length/2], array[array.length-1]};
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


}
