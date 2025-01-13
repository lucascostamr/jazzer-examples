package com.fuzzytest.app;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;

public class CalculatorFuzzTest {

    // The method to be fuzzed
    public static void processExpression(String expression) {
        try {
            // Simulated processing of the expression
            System.out.println("Processing expression: " + expression);
            // Add actual processing logic here
        } catch (Exception e) {
            System.err.println("Error processing expression: " + e.getMessage());
        }
    }

    // Fuzz test method
    @FuzzTest
    public void fuzzProcessExpression(FuzzedDataProvider data) {
        String expression = data.consumeRemainingAsString();
        processExpression(expression);
    }
}