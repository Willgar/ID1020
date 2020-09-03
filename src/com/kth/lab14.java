package com.kth;
/**
 * ID1020
 * Lösning för labb 1 - Problem 4
 * En linked circular list som kan lägga in och ta bort saker från början och slut, där noderna i listan har endast koll på noden framför och
 * första och sista noden är sammankopplade vilket gör den cirkulär.
 */
public class lab14 {
    private static node first;
    private static node last;
    private static int n = 1;
    /**
     * Mainfunktion för att testa listan.
     *
     */
    public static void main(String[] args) {
        addFirst();
        addFirst();
        removeLast();
        addLast();
        addLast();
        removeFirst();
        addFirst();
        removeLast();
        removeLast();
        removeLast();
        removeFirst();

    }

    /**
     * Nod med next, samt data för att särskilja noderna.
     */
    public static class node{
        int num = n++;
        node next;
    }

    /**
     * Skapar listans första och sista nod som är de 2 första noderna i listan, ifall listan är tom. Annars lägger den
     * till en ny nod i början av listan genom att skapa den och sedan placera sig mellan den äldre första och den sista noden.
     */
    private static void addFirst(){
        if(first == null) {
            first = new node();
            last = first;
            first.next = last;
        }
        else {
            node newNode = new node();
            newNode.next = first;
            last.next = newNode;
            first = newNode;
        }
        printNodes();
    }

    /**
     * Skapar en ny nod som placerar sig efter den tidigare sista och tar över dess relation till första och sedan dess
     * titel som sista.
     */
    private static void addLast(){
        if(first == null){
            addFirst();
        } else{
            node newNode = new node();
            last.next = newNode;
            newNode.next = first;
            last = newNode;
        }
        printNodes();
    }

    /**
     * Tar bort den sista noden. Börjar med att hitta den näst sista noden och låter den ta den sista nodens plats.
     */
    private static void removeLast(){
        if(first == null || first.next == first){ first = null; }
        else {
            node tempNode = first;
            while (tempNode.next != last) {
                tempNode = tempNode.next;
            }
            tempNode.next = first;
            last = tempNode;
        }
        printNodes();
    }

    /**
     * Tar bort den första noden
     */
    private static void removeFirst(){
        if(first == null || first.next == first){ first = null; }
        else {
            last.next = first.next;
            first = first.next;
        }
        printNodes();
    }

    /**
     * Printar ut samtliga noder i listan.
     */
    private static void printNodes(){
        if(first == null) System.out.println("[ ]");
        else {
            System.out.print("[");
            node tempNode = first;
            System.out.print(tempNode.num);
            tempNode = tempNode.next;
            while (tempNode != first) {
                System.out.print(", ");
                System.out.print(tempNode.num);
                tempNode = tempNode.next;
            }
            System.out.println("]\n");
        }
    }
}
