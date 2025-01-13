package com.fuzzytest.app;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;

public class CalculatorFuzzTest {
    // The method to process a basic arithmetic expression
    public static void processExpression(String expression) {
        try {
            System.out.println("Processing expression: " + expression);
            double result = evalSimpleExpression(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Error processing expression: " + e.getMessage());
        }
    }

    // A simple evaluator for basic expressions with +, -, *, /
    public static double evalSimpleExpression(String expression) {
        String[] tokens = expression.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid expression format. Expected format: <num1> <operator> <num2>");
        }

        double num1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double num2 = Double.parseDouble(tokens[2]);

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    // Fuzz test method
    @FuzzTest(maxDuration = "30s")
    public void fuzzProcessExpression(FuzzedDataProvider data) {
        String expression = data.consumeRemainingAsString();
        processExpression(expression);
    }
}