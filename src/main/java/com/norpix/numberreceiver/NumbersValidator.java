package com.norpix.numberreceiver;

import java.util.List;
import java.util.Set;

class NumbersValidator {
    private static final int CORRECT_SIZE = 6;
    private static final int MINIMAL_NUMBER_VALUE_FROM_USER = 1;
    private static final int MAXIMAL_NUMBER_VALUE_FROM_USER = 99;
    private static final String NOT_ENOUGH_NUMBERS = "ERROR - Less than six numbers or input number out of range 1-99";
    private static final String TOO_MUCH_NUMBERS = "ERROR - More than six numbers";
    private static final String VALIDATION_PASSED = "Numbers received. Next draw date is on: ";
    String validate(Set<Integer> numbers) {
        List<Integer> filteredNumbers = numbers.stream()
                .filter(number -> number >= MINIMAL_NUMBER_VALUE_FROM_USER)
                .filter(number -> number <= MAXIMAL_NUMBER_VALUE_FROM_USER)
                .toList();
        int numbersAmount = filteredNumbers.size();
        boolean notEnoughNumbers = numbersAmount < CORRECT_SIZE;
        boolean tooMuchNumbers = numbersAmount > CORRECT_SIZE;

        if (notEnoughNumbers)
            return NOT_ENOUGH_NUMBERS;
        else if (tooMuchNumbers)
            return TOO_MUCH_NUMBERS;
        return VALIDATION_PASSED;
    }
}