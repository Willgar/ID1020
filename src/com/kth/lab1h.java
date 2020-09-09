package com.kth;
import edu.princeton.cs.algs4.StdIn;
/**
 * ID1020
 * Lösning för labb 1 - Higher assigment problem
 * Med hjälp av StdIn metoden från princeton från andra problemet så läser algoritmen in varje character för sig och
 * ser ifall de är en parantes(eller liknande) med en switch case.
 * Algoritmen loopar endast igenom inputen en gång och har därmed tidskomplexiteten N.
 * Minneskomplexiteten är O(1). Ingenting av värde sparas. 
 */
public class lab1h {
    public static void main(String[] args) {
        checkParenthesis();
    }
    private static void checkParenthesis(){
        char c = StdIn.readChar();
        while(c != '\n'){
            switch(c){
                case '[':
                case '{':
                case '(':
                    balanceParanthesis(c);
                    break;
                case ']':
                case ')':
                case '}':
                    System.out.println("The amount of parenthesis are unbalanced");
                    break;
            }
            c = StdIn.readChar();
        }
    }

    /**
     * Loopar tills att man hittat motsvarig slutparantes, ifall fel slutparantes hittas så skrivs error ut att
     * paranteserna är obalanserade. Ifall ny startparantes hittats så börjar den med rekursiv funktion leta efter den
     * nya startparantesens slutparantes innan den fortsätter med tidigare parantes.
     * @param k Typen av startparantes
     */
    static void balanceParanthesis(char k){
        boolean paranthes = false;
        boolean bracket = false;
        boolean brace = false;
        switch(k){
            case'(':
                paranthes = true;
                break;
            case '[':
                bracket = true;
                break;
            case '{':
                brace = true;
                break;
            }
        char c = StdIn.readChar();
        while(c != '\n'){
            switch(c){
                case ')':
                    if(paranthes){return;}
                    else{System.out.println("The amount of parenthesis are unbalanced");}
                    break;
                case ']':
                    if(bracket){return;}
                    else{System.out.println("The amount of brackets are unbalanced");}
                    break;
                case '}':
                    if(brace){return;}
                    else{System.out.println("The amount of braces are unbalanced");}
                    break;
                case '[':
                case '{':
                case '(':
                    balanceParanthesis(c);
                    break;
            }
            c = StdIn.readChar();
        }
        System.out.println("No end paranthesis found");

    }
}
