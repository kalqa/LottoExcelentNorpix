package com.norpix.numberreceiver;

import com.norpix.drawdate.DrawDateFacade;
import com.norpix.drawdate.dto.NextDrawDateDto;
import com.norpix.numberreceiver.dto.InputNumbersResultDto;
import com.norpix.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumbersValidator(),
            new InMemoryNumberReceiverRepositoryTestImplementation());
    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        NextDrawDateDto drawDate = DrawDateFacade.nextDrawDate(LocalDate.now());
        assertThat(result.message()).isEqualTo("Numbers received. Next draw date is on: " + drawDate);
    }
    @Test
    public void should_return_failure_when_user_gave_less_than_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo("ERROR - Less than six numbers or input number out of range 1-99");
    }
    @Test
    public void should_return_failure_when_user_gave_more_than_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo("ERROR - More than six numbers");
    }
    @Test
    public void should_return_failure_when_user_gave_numbers_out_of_range_from_1_to_99(){
        Set<Integer> numbers = Set.of(1, 2, 3, 400, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo("ERROR - Less than six numbers or input number out of range 1-99");
    }
    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(LocalDate.now());
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId("1")
                        .drawDate(result.drawDate())
                        .numbers(Set.of(1,2,3,4,5,6))
                        .build()
        );
    }
}