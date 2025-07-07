package com.maskaravivek.android.library.example

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for the Calculator class.
 * Tests all basic mathematical operations and utility methods.
 */
class CalculatorTest {
    
    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun addition_isCorrect() {
        val result = calculator.add(2.0, 2.0)
        assertEquals(4.0, result, 0.001)
    }

    @Test
    fun addition_withNegativeNumbers() {
        val result = calculator.add(-5.0, 3.0)
        assertEquals(-2.0, result, 0.001)
    }

    @Test
    fun subtraction_isCorrect() {
        val result = calculator.subtract(10.0, 3.0)
        assertEquals(7.0, result, 0.001)
    }

    @Test
    fun subtraction_withNegativeResult() {
        val result = calculator.subtract(3.0, 10.0)
        assertEquals(-7.0, result, 0.001)
    }

    @Test
    fun multiplication_isCorrect() {
        val result = calculator.multiply(4.0, 5.0)
        assertEquals(20.0, result, 0.001)
    }

    @Test
    fun multiplication_withZero() {
        val result = calculator.multiply(10.0, 0.0)
        assertEquals(0.0, result, 0.001)
    }

    @Test
    fun division_isCorrect() {
        val result = calculator.divide(10.0, 2.0)
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun division_withDecimalResult() {
        val result = calculator.divide(10.0, 3.0)
        assertEquals(3.333, result, 0.001)
    }

    @Test(expected = ArithmeticException::class)
    fun division_byZero_throwsException() {
        calculator.divide(10.0, 0.0)
    }

    @Test
    fun isPositive_withPositiveNumber() {
        assertTrue(calculator.isPositive(5.0))
    }

    @Test
    fun isPositive_withNegativeNumber() {
        assertFalse(calculator.isPositive(-5.0))
    }

    @Test
    fun isPositive_withZero() {
        assertFalse(calculator.isPositive(0.0))
    }

    @Test
    fun isNegative_withNegativeNumber() {
        assertTrue(calculator.isNegative(-5.0))
    }

    @Test
    fun isNegative_withPositiveNumber() {
        assertFalse(calculator.isNegative(5.0))
    }

    @Test
    fun isNegative_withZero() {
        assertFalse(calculator.isNegative(0.0))
    }

    @Test
    fun absolute_withPositiveNumber() {
        val result = calculator.absolute(5.0)
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun absolute_withNegativeNumber() {
        val result = calculator.absolute(-5.0)
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun absolute_withZero() {
        val result = calculator.absolute(0.0)
        assertEquals(0.0, result, 0.001)
    }
}