package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

    int value;

    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        if(!check(token)){
            new InvalidTokenException();
        }

        this.value = Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.value = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        return token.matches("[0-9]+");
    }

    public static void main(String[] args) {
        Operand x = new Operand("14");
    }
}
