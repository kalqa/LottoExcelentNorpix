package com.norpix.numberreceiver;

import java.util.List;
import java.util.Set;

class NumbersValidator {
    final int CORRECT_SIZE = 6;
    final String NOT_ENOUGH_NUMBERS = "ERROR - Less than six numbers or input number out of range 1-99";
    final String TOO_MUCH_NUMBERS = "ERROR - More than six numbers";
    final String VALIDATION_PASSED = "Numbers received. Next draw date is on: ";
    String validate(Set<Integer> numbers) {
        List<Integer> filteredNumbers = numbers.stream()
                .filter(number -> number >= 1)
                .filter(number -> number <= 99)
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