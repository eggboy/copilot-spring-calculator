package com.copilot.calculator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.copilot.calculator.api.ArithmeticController;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CalculatorApplicationTests {

	@Autowired
    private ArithmeticController arithmeticController;
	@Test
	void contextLoads() {
	}

	// Validation Test
	@Test
	public void testInvalidOperation() throws Exception {
		//rejects missing operation, expects IllegalArgumentException("Invalid operation: "
		var exception = assertThrows(IllegalArgumentException.class, () -> {
        	arithmeticController.calculate("", "1", "2");
		});
		assertThat(exception.getMessage()).isEqualTo("Invalid operation: ");

		//rejects invalid operation
		exception = assertThrows(IllegalArgumentException.class, () -> {
			arithmeticController.calculate("invalid", "1", "2");
		});
		assertThat(exception.getMessage()).isEqualTo("Invalid operation: invalid");

		//rejects missing operand1
		exception = assertThrows(IllegalArgumentException.class, () -> {
			arithmeticController.calculate("add", "", "2");
		});
		assertThat(exception.getMessage()).isEqualTo("Operand cannot be null or empty");

		//rejects missing operand2
		exception = assertThrows(IllegalArgumentException.class, () -> {
			arithmeticController.calculate("add", "1", "");
		});
		assertThat(exception.getMessage()).isEqualTo("Operand cannot be null or empty");
		
	}

	// Test Addition
	@Test
	public void testAdd() throws Exception {
		assertThat(arithmeticController.calculate("add", "1", "2")).isEqualTo("{\"result\": 3.00}");
		assertThat(arithmeticController.calculate("add", "1.1", "2.2")).isEqualTo("{\"result\": 3.30}");
		assertThat(arithmeticController.calculate("add", "-1", "-2")).isEqualTo("{\"result\": -3.00}");
	}

	// Test Subtraction
	@Test
	public void testSubtract() throws Exception {
		assertThat(arithmeticController.calculate("subtract", "1", "2")).isEqualTo("{\"result\": -1.00}");
		assertThat(arithmeticController.calculate("subtract", "1.1", "2.2")).isEqualTo("{\"result\": -1.10}");
		assertThat(arithmeticController.calculate("subtract", "-1", "-2")).isEqualTo("{\"result\": 1.00}");
	}

	// Test Multiplication
	@Test
	public void testMultiply() throws Exception {
		assertThat(arithmeticController.calculate("multiply", "1", "2")).isEqualTo("{\"result\": 2.00}");
		assertThat(arithmeticController.calculate("multiply", "1.1", "2.2")).isEqualTo("{\"result\": 2.42}");
		assertThat(arithmeticController.calculate("multiply", "-1", "-2")).isEqualTo("{\"result\": 2.00}");
	}

	// Test Division
	@Test
	public void testDivide() throws Exception {
		assertThat(arithmeticController.calculate("divide", "1", "2")).isEqualTo("{\"result\": 0.50}");
		assertThat(arithmeticController.calculate("divide", "1.1", "2.2")).isEqualTo("{\"result\": 0.50}");
		assertThat(arithmeticController.calculate("divide", "-1", "-2")).isEqualTo("{\"result\": 0.50}");
	}

	//TODO: Challenge #1
	
}
