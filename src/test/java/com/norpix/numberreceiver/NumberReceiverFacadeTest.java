package com.norpix.numberreceiver;

import org.junit.jupiter.api.Test;

import java.util.Set;


class NumberReceiverFacadeTest {

    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1,2,3,4,5,6));
        assert message.equals("success");
    }

    @Test
    public void should_return_failure_when_user_gave_less_than_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1,2,3,4,5));
        assert message.equals("failure");
    }

}
