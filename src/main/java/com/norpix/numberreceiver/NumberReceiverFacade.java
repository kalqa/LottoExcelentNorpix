package com.norpix.numberreceiver;

import java.util.List;
import java.util.Set;

public class NumberReceiverFacade {
    final int CORRECT_SIZE = 6;
    public String inputNumbers(final Set<Integer> numbers) {
        List<Integer> filteredNumbers = numbers.stream()
                .filter(number -> number >= 1)
                .filter(number -> number <= 99)
                .toList();
        int howManyNumbers = filteredNumbers.size();
        if (howManyNumbers < CORRECT_SIZE){
            return "failure - less than six numbers or input number out of range 1-99";
        } else if (howManyNumbers > CORRECT_SIZE) {
            return "failure - more than six numbers";
        }
        return "success";
    }


}
