package ua.kvelinskyi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import ua.kvelinskyi.ContextCalculator;
import ua.kvelinskyi.Operation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Kvelinskyi (igorkvjava@gmail.com)
 *
 */
public class ButtonController {

    @FXML
    private Text displayHistory;

    @FXML
    private Text displayOperand;

    @FXML
    private Text displayInfo;
    private StringBuilder operand = new StringBuilder(10);
    private StringBuilder history = new StringBuilder();
    private ContextCalculator contextCalculatorX;
    private ContextCalculator contextCalculatorY;
    private double operandDigit;
    private String resultEquals;
    private List<OperationStrategy> operationStrategyList = new ArrayList<>();
    private List<String> resultList = new ArrayList<>();

    @FXML
    private void toDisplayDigit(ActionEvent event){
        //method (inputKey) -> operand.append
        operandDigit = Double.parseDouble(inputKey(event));
        displayOperand.setText(String.valueOf(operandDigit));
    }

    @FXML
    private void operatorHandleEquals(ActionEvent event){
        try {
            contextCalculatorY = new ContextCalculator(operandDigit, operationStrategyList.get(operationStrategyList.size()-1));
            contextCalculatorY.calculateTo(contextCalculatorX);
            resultEquals = String.valueOf(contextCalculatorY.getArgument());
            clearExpression();
            displayOperand.setText(resultEquals);
        }catch (NullPointerException e){
            displayOperand.setText("0");
        }
    }

    @FXML
    private void operatorHandlerMultiply(ActionEvent event){
        operatorHandler(new OpMultiply(), "*");
    }

    @FXML
    private void operatorHandlerDivision(ActionEvent event){
        operatorHandler(new OpDivision(), "/");
    }

    @FXML
    private void operatorHandlerPlus(ActionEvent event){
        operatorHandler(new OpPlus(), "+");
    }

    @FXML
    private void operatorHandlerMinus(ActionEvent event){
        operatorHandler(new OpMinus(), "-");
    }
    @FXML
    private void clearExpression(){
        clearStringBuilder(history);
        clearStringBuilder(operand);
        displayOperand.setText("0");
        displayHistory.setText("");
        displayInfo.setText("");
        contextCalculatorX = contextCalculatorY = null;
        resultList.clear();
        operationStrategyList.clear();
        operandDigit=0;
    }

    private String inputKey(ActionEvent event){
        Button clickedButton = (Button) event.getTarget();
        operand.append(clickedButton.getText());
        return operand.toString();
    }

    private void clearStringBuilder(StringBuilder text){
        int len = text.length();
        text.delete(0, len);
    }

    private void operatorHandler(OperationStrategy operationStrategy, String sing){
        operationStrategyList.add(operationStrategy);
        history.append(operandDigit + sing);
        displayHistory.setText(history.toString());
        try{
        contextCalculatorY = new ContextCalculator(operandDigit,
                operationStrategyList.get(operationStrategyList.size()-2));
        }catch (ArrayIndexOutOfBoundsException e){
            contextCalculatorY = new ContextCalculator(operandDigit,
                    operationStrategyList.get(operationStrategyList.size()-1));
        }
        contextCalculatorY.calculateTo(contextCalculatorX);
        contextCalculatorX = contextCalculatorY;
        // resultList for test
        resultList.add(contextCalculatorY.getArgument() + ":");
        displayInfo.setText(String.valueOf(resultList));
        //---- test
        clearStringBuilder(operand);
        displayOperand.setText(String.valueOf(contextCalculatorY.getArgument()));
    }

    @FXML
    private void keyboardInput(KeyEvent event){
        displayInfo.setText("keyboardInput");
    }
}

