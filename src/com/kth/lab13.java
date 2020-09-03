package com.kth;

/**
 * ID1020
 * Lösning för labb 1 - Problem 3
 * En double linked circular list som använder sig av FIFO, där noderna i listan har koll på noden framför och bakom och
 * första och sista noden är sammankopplade vilket gör den cirkulär.
 *
 * Genom att låta programmet själv välja nummervärde till noderna så tillåts inte användaren göra misstag att skriva in
 * felaktig information som skulle kunna förstöra. Det finns ingen riktig övre gräns till mängden noder men försöker man
 * ta bort fler noder när det är tomt så händer inget nytt, det printar bara ut en tom parantes.
 */
public class lab13 {
    private static node first;
    private static node last;
    private static int n = 1;

    /**
     * Mainfunktion för att testa listan.
     *
     */
    public static void main(String[] args) {
    addFirstNodes();
    addNode();
    addNode();
    removeNode();
    addNode();
    addNode();
    removeNode();
    addNode();
    removeNode();
    removeNode();
    removeNode();
    removeNode();
    removeNode();
    }

    /**
     * Nod med next och previous, samt data för att särskilja noderna.
     */
    public static class node{
        int num = n++;
        node next;
        node prev;
    }

    /**
     * Lägger till en nod i slutet av listan genom att först skapa en ny nod, sätta den bakom den första noden och efter
     * den tidigare sista noden. Därefter justeras den nya nodens framför och bakom och slutligen så ändras den nya noden
     * till den nya sista
     */
    private static void addNode(){
        if(first == null){
            addFirstNodes();
        } else {
            node newNode = new node();
            first.prev = newNode;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = first;
            last = newNode;
            printNodes();
        }
    }

    /**
     * Skapar listan med en nod som är både första och sista.
     */
    private static void addFirstNodes(){
        first = new node();
        last = first;
        first.next = last;
        first.prev = last;
        printNodes();
    }

    /**
     * Tar bort första noden. Den börjar med att låta noden efter första ta den förstas plats med dess relation till sista.
     * Därefter så ändras den till den nya första och därmed är den gamla borta.
     */
    private static void removeNode(){
        if(first == null || first.next == first){ first = null; }
        else{
                first.next.prev = last;
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

