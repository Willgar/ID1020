package com.kth;
import edu.princeton.cs.algs4.StdIn;

import java.util.Scanner;

/**
 * ID1020
 * William Axbrink 2020-09-10
 * Lösning för labb 2 - Problem 1
 * En insertionSort återskapad baserat på hur principen av den ska fungera.
 *
 * Testerna sker i main och i swap-funktionen så skrivs vilka element som byts ut och sedan printas hela arrayen i dess
 * utseende.
 *
 */
public class lab21 {
    public static int n = 0;
    public static void main(String[] args) {
        int[] array = {2,1,16,71,32,82,33, -10,12,14};
        printArray(array);
        array = insertionSort(array);
        System.out.println(array.length);
        n = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("How large array do you want?");
        int arraySize = sc.nextInt();
        System.out.println("Fill the list");
        int[] customArray = new int[arraySize];
        for(int i = 0; i<arraySize; i++){
            customArray[i] = sc.nextInt();
        }
        customArray = insertionSort(customArray);
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
        System.out.println("Swapping " + array[j] + " with " + array[i]);
        printArray(array);
        n++;
        System.out.println("Swaps: " + n);
        return array;
    }
    //Loopar igenom hela array och printar ut innehållet.
    static void printArray(int[] array){
        for(int i = 0; i<array.length;i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
}
