package com.kth;

public class lab16 {
    private static node first;
    private static node last;
    /**
     * Mainfunktion för att testa listan.
     *
     */
    public static void main(String[] args) {
        /*addNode();
        addNode();
        addNode();
        addNode();
        removeKthNode(3);
        */

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
    private static void addNode(int n){
        if(first == null) {
            first = new node();
            last = first;
            first.next = last;
        }
        else {
            node newNode = new node();
            newNode.num = n;
            newNode.next = first;
            last.next = newNode;
            first = newNode;
        }
        printNodes();
    }

    private static void removeKthNode(int k){
        node tempNode = first;
        if(last.num >= k) {
            while (tempNode.next.num != k) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            if(tempNode.next == last){last = tempNode;}

        } else{System.out.println("No such node");}
        printNodes();
    }


    /**
     * Printar ut samtliga noder i listan.
     */
    private static void printNodes(){
        changeNodeOrder();
        node tempNode = first;
        System.out.println(tempNode.num);
        tempNode = tempNode.next;
        while(tempNode != first){
            System.out.println(tempNode.num);
            tempNode = tempNode.next;
        }
        System.out.println("\n");
    }
    private static void changeNodeOrder(){
        node tempNode = first;
        int n = 1;
        first.num = n++;
        tempNode = tempNode.next;
        while(tempNode != first){
            tempNode.num = n++;
            tempNode = tempNode.next;
        }
    }

    private static void insertionSort(){

    }
    private static void swap(node firstNode, node secondNode){
        node tempNode = firstNode;
        firstNode = secondNode;
        secondNode = tempNode;
    }
}
