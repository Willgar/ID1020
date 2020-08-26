package com.kth;

public class lab13 {


    public static void main(String[] args) {
        node first = new node();
        node last = new node();
        int listSize;

        first.next = last;
        first.prev = last;
        last.next = first;
        last.prev = first;


    }
    public static class node{
        int num;
        static node next;
        static node prev;
    }
    public void addNode(node newNode){

        while(node.next != null){
            node = node.next;

        }

    }
}
