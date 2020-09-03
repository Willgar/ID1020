package com.kth;
/**
 * ID1020
 * Lösning för labb 1 - Problem 5
 * En linked circular list som kan lägga in och ta bort saker från utvald indexplats, där noderna i listan har endast koll på noden framför och
 * första och sista noden är sammankopplade vilket gör den cirkulär.
 *
 * Funktionerna är utformade så att k-värdet måste finnas bland dem index som existerar. De som är utanför kommer returnera
 * ett error No such node och när de är slut på noder så returneras bara en tom lista.
 */
public class lab15 {
    private static node first;
    private static node last;
    /**
     * Mainfunktion för att testa listan.
     *
     */
    public static void main(String[] args) {
        addNode();
        addNode();
        addNode();
        addNode();
        removeKthNode(3);
        removeKthNode(4);
        removeKthNode(-2);
        removeKthNode(2);
        removeKthNode(1);
        removeKthNode(1);
        removeKthNode(1);
    }

    /**
     * Nod med next, samt data för att särskilja noderna.
     */
    public static class node{
        int num;
        node next;
    }

    /**
     * Skapar listans första och sista nod som är de 2 första noderna i listan, ifall listan är tom. Annars lägger den
     * till en ny nod i början av listan genom att skapa den och sedan placera sig mellan den äldre första och den sista noden.
     */
    private static void addNode(){
        if(first == null) {
            first = new node();
            last = first;
            first.next = last;
            last.next = first;
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
     * Hittar noden på Kth platsen för att sedan ta bort den och koppla ihop listan igen.
     * @param k Platsen på noden.
     */

    private static void removeKthNode(int k){
        node tempNode = first;
        //tom lista sker inget
        if(tempNode == null){ printNodes(); }
        //Kollar första talet
        else if(tempNode.num == k){
            //Kollar ifall listan har fler än ett element
            if(tempNode.next == tempNode){
                first = null;
                last = null;
            } else{
                tempNode = tempNode.next;
                last.next = tempNode;
                first = tempNode;
            }
            printNodes();
        }
        else if(last.num >= k && first.num <= k) {
            while (tempNode.next.num != k) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            printNodes();
        }
        else {System.out.println("No such node");}
    }
    /*
    private static void removeKthNode(int k){
        node tempNode = first;
        if(first == last && k == first.num){ first = null; last = null; }
        else if(last.num >= k && first.num <= k) {
            if(tempNode.num == k){
                tempNode = tempNode.next;
                if(tempNode.next == last){last = tempNode;}
            } else{
            while (tempNode.next.num != k) {
                tempNode = tempNode.next;
            }}
            tempNode.next = tempNode.next.next;
            if(tempNode.next == last){last = tempNode;}
            printNodes();
        } else{System.out.println("No such node");}
    }*/


    /**
     * Printar ut samtliga noder i listan.
     */
    private static void printNodes(){
        if(first == null) System.out.println("[ ]");
        else {
            changeNodeIndex();
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
    private static void changeNodeIndex(){
        node tempNode = first;
        int n = 1;
        first.num = n++;
        tempNode = tempNode.next;
        while(tempNode != first){
            tempNode.num = n++;
            tempNode = tempNode.next;
        }
    }
}
