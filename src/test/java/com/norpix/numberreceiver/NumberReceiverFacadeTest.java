package com.norpix.numberreceiver;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(message).isEqualTo("success");
    }

    @Test
    public void should_return_failure_when_user_gave_less_than_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        assertThat(message).isEqualTo("failure - less than six numbers or input number out of range 1-99");
    }
    @Test
    public void should_return_failure_when_user_gave_more_than_six_numbers(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6, 7));
        assertThat(message).isEqualTo("failure - more than six numbers");
    }
    @Test
    public void should_return_failure_when_user_gave_numbers_out_of_range_from_1_to_99(){
        String message = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 400, 5, 6));
        assertThat(message).isEqualTo("failure - less than six numbers or input number out of range 1-99");
    }

}
