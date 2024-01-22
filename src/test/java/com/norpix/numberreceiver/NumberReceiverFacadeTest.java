package com.norpix.numberreceiver;

import com.norpix.drawdate.DrawDateFacade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    NumbersValidator numbersValidator = new NumbersValidator();
    DrawDateFacade drawDateFacade = new DrawDateFacade();
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numbersValidator, drawDateFacade);
    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        String drawDate = drawDateFacade.nextDrawDate(LocalDate.now());
        assertThat(message).isEqualTo("Numbers received. Next draw date is on: " + drawDate);
    }
    @Test
    public void should_return_failure_when_user_gave_less_than_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        assertThat(message).isEqualTo("ERROR - Less than six numbers or input number out of range 1-99");
    }
    @Test
    public void should_return_failure_when_user_gave_more_than_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6, 7));
        assertThat(message).isEqualTo("ERROR - More than six numbers");
    }
    @Test
    public void should_return_failure_when_user_gave_numbers_out_of_range_from_1_to_99(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 400, 5, 6));
        assertThat(message).isEqualTo("ERROR - Less than six numbers or input number out of range 1-99");
    }
}