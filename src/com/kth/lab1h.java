package com.kth;
import edu.princeton.cs.algs4.StdIn;
/**
 * ID1020
 * Lösning för labb 1 - Higher assigment problem
 * Med hjälp av StdIn metoden från princeton från andra problemet så läser algoritmen in varje character för sig och
 * ser ifall de är en parantes(eller liknande) med en switch case. Eftersom det enbart är en vanlig while loop med
 * "vanliga" operationer inuti så har den tidskomplexiteten O(n).
 *
 * För varje vänsterparantes så läggs indexet för typen av parantes +1, men motsvarande högerparantes ger -1 för att
 * "balansera" ut paranteserna. I slutet kollar den alla parantesindex och ifall de är 0 så är de balanserat annars är
 * det obalanserat.
 */
public class lab1h {
    public static void main(String[] args) {
        checkParenthesis();
    }
    private static void checkParenthesis(){
        char c = StdIn.readChar();
        int parenthesis = 0;
        int brackets = 0;
        int braces = 0;
        while(c != '\n'){
            switch(c){
                case '[':
                    brackets++;
                    break;
                case '{':
                    braces++;
                    break;
                case '(':
                    parenthesis++;
                    break;
                case ']':
                    brackets--;
                    break;
                case '}':
                    braces--;
                    break;
                case ')':
                    parenthesis--;
                    break;
            }
            c = StdIn.readChar();
        }

        if(parenthesis!= 0) System.out.println("The amount of parenthesis are unbalanced");
        if(brackets!= 0) System.out.println("The amount of brackets are unbalanced");
        if(braces!= 0) System.out.println("The amount of braces are unbalanced");
    }
}
