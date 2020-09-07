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
     * Loopar tills att man hittat motsvarig slutparantes, ifall fel slutparantes hittas så
     * @param k
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

    }
}
