package com.copilot.calculator.api;

import java.util.Map;
import java.util.function.BinaryOperator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArithmeticController {

    @GetMapping("/arithmetic")
    public String calculate(@RequestParam String operation, @RequestParam String operand1, @RequestParam String operand2) {

        // TODO: Add operator
        var operations = Map.<String, BinaryOperator<Double>>of(
            "add",      (Double a, Double b) -> a + b,
            "subtract", (Double a, Double b) -> a - b,
            "multiply", (Double a, Double b) -> a * b,
            "divide",   (Double a, Double b) -> a / b
        );

        if (!operations.containsKey(operation)) {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        Double op1 = parseOperand(operand1);
        Double op2 = parseOperand(operand2);

        Double result = operations.get(operation).apply(op1, op2);

        return String.format("{\"result\": %.2f}", result);
    }

    private Double parseOperand(String operand) {
        if (operand == null || operand.isEmpty()) {
            throw new IllegalArgumentException("Operand cannot be null or empty");
        }

        try {
            return Double.parseDouble(operand);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid operand: " + operand);
        }
    }
}