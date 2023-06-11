package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int value = 1;
        for(int i = 0; i <= operandTwo.getValue(); i++){
            value *= operandOne.getValue();
        }
        Operand result = new Operand (value);

        return result;
    }
}
