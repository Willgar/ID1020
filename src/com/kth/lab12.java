package com.kth;

import java.util.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * ID1020
 * Lösning för labb 1 - Problem 2
 * Använder en rekursiv och iterativ metod med hjälp av Stacks från 1.3
 *
 * All input som man skriver in i terminalen är en typ av Characters så några utförliga tester kändes ej nödvändiga.
 */
public class lab12 {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        RecursiveJava(stack);
        System.out.println("\n");
        IterativeJava(stack);
    }

    /**
     * Rekursivt hämtar samtliga characters från terminalen och pushar in dem i stacken tills den når EOF/(\n) och sedan
     * skriver ut dem och poppar ut dem.
     */
    static void RecursiveJava(Stack<Character> stack){
        char c = StdIn.readChar();
        if(c != '\n'){
            stack.push(c);
            RecursiveJava(stack);
        }
        else {
            for(int i = 0; i<stack.size()+i; i++){
                StdOut.print(stack.pop());
            }
        }
    }

    /**
     * Iterativt hämtar samtliga characters från texten och pushar dem i en stack. När den når EOF/(\n) printar den ut samtliga characters från
     * arrayen startandes bakifrån med hjälp av poppande.
     */
    static void IterativeJava(Stack<Character> stack){
        char c = StdIn.readChar();
        int n = 0;
        while(c != '\n'){
            stack.push(c);
            c = StdIn.readChar();
        }
        for(int i = 0; i<stack.size()+i; i++){
            StdOut.print(stack.pop());
        }
    }
}
