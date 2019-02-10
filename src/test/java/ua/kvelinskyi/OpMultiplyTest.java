package ua.kvelinskyi;

import org.junit.Test;
import ua.kvelinskyi.Operation.OpDivision;
import ua.kvelinskyi.Operation.OpMultiply;
import ua.kvelinskyi.Operation.OperationStrategy;

import static org.junit.Assert.*;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 */
public class OpMultiplyTest {

    @Test
    public void calculate() {
        OperationStrategy operationStrategy = new OpMultiply();
        ContextCalculator calculatorX = new ContextCalculator(2.5, new OpMultiply());
        ContextCalculator calculatorY = new ContextCalculator(5, new OpDivision());
        calculatorX.calculateTo(calculatorY);
        System.out.println(calculatorX.getArgument() + "  " + calculatorY.getArgument());
        assertEquals("" + calculatorX.getArgument(), 12.5002, calculatorX.getArgument(), 0.001);

    }
}