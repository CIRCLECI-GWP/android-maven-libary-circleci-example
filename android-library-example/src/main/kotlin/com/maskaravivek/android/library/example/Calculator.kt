package com.maskaravivek.android.library.example

/**
 * A simple calculator class that provides basic mathematical operations.
 * This class is designed for tutorial purposes and demonstrates
 * the core functionality of an Android library.
 *
 * @author Vivek Maskara
 */
class Calculator {

    /**
     * Adds two numbers and returns the result.
     *
     * @param a The first number
     * @param b The second number
     * @return The sum of a and b
     */
    fun add(a: Double, b: Double): Double {
        return a + b
    }

    /**
     * Subtracts the second number from the first number and returns the result.
     *
     * @param a The first number (minuend)
     * @param b The second number (subtrahend)
     * @return The difference of a and b
     */
    fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    /**
     * Multiplies two numbers and returns the result.
     *
     * @param a The first number
     * @param b The second number
     * @return The product of a and b
     */
    fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    /**
     * Divides the first number by the second number and returns the result.
     *
     * @param a The dividend
     * @param b The divisor
     * @return The quotient of a and b
     * @throws ArithmeticException if b is zero
     */
    fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return a / b
    }

    /**
     * Utility method to check if a number is positive.
     *
     * @param number The number to check
     * @return true if the number is positive, false otherwise
     */
    fun isPositive(number: Double): Boolean {
        return number > 0
    }

    /**
     * Utility method to check if a number is negative.
     *
     * @param number The number to check
     * @return true if the number is negative, false otherwise
     */
    fun isNegative(number: Double): Boolean {
        return number < 0
    }

    /**
     * Utility method to get the absolute value of a number.
     *
     * @param number The number to get absolute value for
     * @return The absolute value of the number
     */
    fun absolute(number: Double): Double {
        return if (number < 0) -number else number
    }
}
