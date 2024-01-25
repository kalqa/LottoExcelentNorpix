package com.norpix.numberreceiver;

import com.norpix.AdjustingClock;
import com.norpix.drawdate.DrawDateFacade;
import com.norpix.drawdate.dto.NextDrawDateDto;
import com.norpix.numberreceiver.dto.InputNumbersResultDto;
import com.norpix.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    DrawDateFacade drawDateFacade = new DrawDateFacade();
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            drawDateFacade,
            new NumbersValidator(),
            new InMemoryNumberReceiverRepositoryTestImplementation(),
            Clock.systemUTC());
    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        NextDrawDateDto drawDate = drawDateFacade.nextDrawDate(LocalDateTime.now());
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
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
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(LocalDateTime.now());
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId("1")
                        .drawDate(result.drawDate())
                        .numbers(Set.of(1,2,3,4,5,6))
                        .build()
        );
    }
    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers1() {
        AdjustingClock fixedClock = new AdjustingClock(LocalDateTime.of(2023, 12, 24, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/Warsaw"));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                drawDateFacade,
                new NumbersValidator(),
                new InMemoryNumberReceiverRepositoryTestImplementation(),
                fixedClock);
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        //minął miesiąc
        fixedClock.advanceInTimeBy(Duration.ofDays(10));
        fixedClock.advanceInTimeBy(Duration.ofMinutes(25));
        InputNumbersResultDto result1 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 35, 4, 5, 6));
        InputNumbersResultDto result2 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 47, 5, 6));

        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(LocalDateTime.of(2023, 12, 24, 14, 0, 0));
        List<TicketDto> ticketDtos1 = numberReceiverFacade.userNumbers(LocalDateTime.of(2024, 1, 3, 14, 25, 0));
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId("1")
                        .drawDate(result.drawDate())
                        .numbers(Set.of(1, 2, 3, 4, 5, 6))
                        .build()
        );
        assertThat(ticketDtos1).containsExactlyInAnyOrder(
                TicketDto.builder()
                        .ticketId("2")
                        .drawDate(result1.drawDate())
                        .numbers(Set.of(1, 2, 35, 4, 5, 6))
                        .build(),
                TicketDto.builder()
                        .ticketId("3")
                        .drawDate(result2.drawDate())
                        .numbers(Set.of(1, 2, 3, 47, 5, 6))
                        .build()
        );
    }
}