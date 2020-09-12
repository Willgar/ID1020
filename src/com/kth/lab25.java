package com.kth;

public class lab25 {
    public static void main(String[] args) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        printArray(arr);
        sort(arr, 0, arr.length-1);
        printArray(arr);
    }
   /* static void merge(int array[], int left, int middle, int right){
        //Storlek på subarray
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Temporära arrays
        int L[] = new int[n1];
        int R[] = new int [n2];

        //Fyll i temp
        for(int i = 0; i<n1;++i){
            L[i] = array[left+1];
        }
        for(int i = 0; i<n2;++i){
            R[i] = array[middle+i+1];
        }

        int i = 0, j = 0;
        int k = left;
        //Jämför höger och vänster subarrays värden för att fylla på array med.
        while(i<n1 && j<n2){
            if(L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            }
            else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        //Fyller på array med eventuella överblivna delar av subarrays
        while(i<n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while(j<n2) {
            array[k] = R[j];
            k++;
            j++;
        }
    }
    static void sort(int array[], int left, int right){
        if(left<right){
            int middle = (left+right)/2;

            sort(array, left, middle);
            sort(array, middle+1, right);

            merge(array, left, middle, right);
        }
    }
    */
    static void printArray(int array[]){
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.print("\n");
    }
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
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

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}