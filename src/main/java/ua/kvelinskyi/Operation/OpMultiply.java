package ua.kvelinskyi.Operation;

import ua.kvelinskyi.ContextCalculator;

public class OpMultiply implements OperationStrategy {

    @Override
    public void calculate(ContextCalculator argumentY, ContextCalculator argumentX) {
        try {
            argumentY.setArgument(argumentX.argument * argumentY.argument);
        } catch (NullPointerException e){

        }
    }
}
