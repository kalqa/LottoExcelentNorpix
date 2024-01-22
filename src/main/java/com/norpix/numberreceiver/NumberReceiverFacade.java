package com.norpix.numberreceiver;

import com.norpix.drawdate.DrawDateFacade;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
public class NumberReceiverFacade {
    private NumbersValidator validate;
    private DrawDateFacade drawDateFacade;
    public String inputNumbers(final Set<Integer> numbers) {
        String drawDate = drawDateFacade.nextDrawDate(LocalDate.now());
        String validationOutput = validate.validate(numbers);
        boolean passedValidation = validationOutput.contains("received");

        if (passedValidation)
            return validationOutput + drawDate;
        return validationOutput;
    }
}