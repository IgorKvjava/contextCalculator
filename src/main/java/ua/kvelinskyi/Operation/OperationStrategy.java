package ua.kvelinskyi.Operation;

import ua.kvelinskyi.ContextCalculator;

public interface OperationStrategy {

    void calculate(ContextCalculator argumentX, ContextCalculator argumentY);
}
