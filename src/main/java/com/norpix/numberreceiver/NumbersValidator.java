package com.norpix.numberreceiver;

import java.util.List;
import java.util.Set;
class NumbersValidator {
    private static final int CORRECT_SIZE = 6;
    private static final int MINIMAL_NUMBER_VALUE_FROM_USER = 1;
    private static final int MAXIMAL_NUMBER_VALUE_FROM_USER = 99;
    ValidationMsg validate(Set<Integer> numbers) {
        List<Integer> filteredNumbers = numbers.stream()
                .filter(number -> number >= MINIMAL_NUMBER_VALUE_FROM_USER)
                .filter(number -> number <= MAXIMAL_NUMBER_VALUE_FROM_USER)
                .toList();
        int numbersAmount = filteredNumbers.size();
        boolean notEnoughNumbers = numbersAmount < CORRECT_SIZE;
        boolean tooMuchNumbers = numbersAmount > CORRECT_SIZE;

        if (notEnoughNumbers) {
            return ValidationMsg.ERROR_NOT_ENOUGH_NUMBERS_OR_OUT_OF_RANGE_FROM_1_TO_99;
        }
        else if (tooMuchNumbers) {
            return ValidationMsg.ERROR_TOO_MUCH_NUMBERS;
        }
        return ValidationMsg.VALIDATION_PASSED;
    }
}