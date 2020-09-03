package com.kth;
/**
 * ID1020
 * Lösning för labb 1 - Problem 6
 * En linked list som kan lägga in noder med värden och med MergeSort sortera listan varje gång något nytt läggs in.
 *
 * Testerna sker i main och eftersom den bara tar emot integers så går de ej att kompilera ifall något annat skrivs in.
 * Sedan så körs mergesort efter varje inlägg/borttagning så maintesterna duger för deras syfte.
 *
 * Jag valde mergesort för de anses vara en av de snabbare sorteringsalgoritmerna med tidskomplexiteten N log N och för
 * att ge mig själv lite erfarenhet att arbeta med nya algoritmer.
 */
public class lab16 {
    private static node first;
    private static node last;
    /**
     * Mainfunktion för att testa listan.
     *
     */
    public static void main(String[] args) {
        addNode(32);
        addNode(51);
        addNode(12);
        addNode(72);
        addNode(61);
        addNode(23);
        addNode(47);
        addNode(01);
        addNode(83);
        addNode(-83);
        addNode(4125167);
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
        removeNode();
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
            first.num = n;
            last = first;
        }
        else {
            node newNode = new node();
            newNode.num = n;
            newNode.next = first;
            first = newNode;
        }
        printNodes();
    }
    private static void removeNode(){
        if(first == null || first.next == first){ first = null; }
        else{
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
            mergeSort(first);
            System.out.print("[");
            node tempNode = first;
            System.out.print(tempNode.num);
            tempNode = tempNode.next;
            while (tempNode != null) {
                System.out.print(", ");
                System.out.print(tempNode.num);
                tempNode = tempNode.next;
            }
            System.out.println("]\n");
        }
    }

    /**
     * Jämför noderna i vänster med höger för att sortera upp dem. Sker rekursivt och mergas senare.
     * @param a Vänster lista
     * @param b Höger lista
     * @return Färdig kombinerad lista
     */
    private static node sortedMerge(node a, node b){
        node result = new node();
        if(a == null) return b;
        if(b == null) return a;

        if(a.num <= b.num){
            result = a;
            result.next = sortedMerge(a.next, b);
        } else{
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        first = result;
        return result;
    }

    /**
     * Om first/head ej är tom och det är mer än ett element i listan så appliceras mergesort.
     */
    private static node mergeSort(node head){
        if(head == null || head.next == null){
            return head;
        }
        //Hitta mitten av linked list
        node middle = getMiddle(head);

        //Mergesort på vänsta listan
        node left = head;

        //Mergesort på högra listan
        node right = middle.next;
        middle.next = null;

        //rekursivt delar upp listorna höger o vänster till mindre listor och sedan sorterar varje lista för sig

        return sortedMerge(mergeSort(left), mergeSort(right));
    }

    /**
     * Hittar den mittersta noden i listan.
     */
    private static node getMiddle(node head){
        if (head == null) return head;

        node slow = head;
        node fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
