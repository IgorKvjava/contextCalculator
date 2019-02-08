package ua.kvelinskyi;

import ua.kvelinskyi.Operation.OperationStrategy;

public class ContextCalculator {
    public double argument;
    public OperationStrategy operationStrategy;

    public ContextCalculator(double argument, OperationStrategy operationStrategy) {
        this.argument = argument;
        this.operationStrategy = operationStrategy;
    }

    public void calculateTo(ContextCalculator contextCalculator, OperationStrategy operationStrategy){
        operationStrategy.calculate(this, contextCalculator);
    }

    public void calculateTo(ContextCalculator contextCalculator){
        calculateTo(contextCalculator, operationStrategy);
    }

    public double getArgument() {
        return argument;
    }

    public void setArgument(double argument) {
        this.argument = argument;
    }

}
