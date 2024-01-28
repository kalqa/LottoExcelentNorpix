package com.norpix.numberreceiver;

import com.norpix.AdjustingClock;
import com.norpix.drawdate.DrawDateFacade;
import com.norpix.numberreceiver.dto.InputNumbersResultDto;
import com.norpix.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    AdjustingClock fixedClock = new AdjustingClock(LocalDateTime.of(2023, 12, 24, 12, 0, 0).toInstant(ZoneOffset.ofHours(1)), ZoneId.of("Europe/Warsaw"));
    DrawDateFacade drawDateFacade = new DrawDateFacade(fixedClock);
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            drawDateFacade,
            new NumbersValidator(),
            new InMemoryNumberReceiverRepositoryTestImplementation(),
            fixedClock);

    @Test
    public void should_return_success_when_user_gave_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
//        LocalDateTime numbersInputDate = drawDateFacade.enteredNumbersDate(LocalDateTime.now(fixedClock));
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
//        String nextDrawDate = drawDateFacade.nextDrawDate(LocalDateTime.now(fixedClock));
        assertThat(result.message()).isEqualTo(String.valueOf(ValidationMsg.VALIDATION_PASSED));
    }
    @Test
    public void should_return_failure_when_user_gave_less_than_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo(String.valueOf(ValidationMsg.ERROR_NOT_ENOUGH_NUMBERS_OR_OUT_OF_RANGE_FROM_1_TO_99));
    }
    @Test
    public void should_return_failure_when_user_gave_more_than_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo(String.valueOf(ValidationMsg.ERROR_TOO_MUCH_NUMBERS));
    }
    @Test
    public void should_return_failure_when_user_gave_numbers_out_of_range_from_1_to_99(){
        Set<Integer> numbers = Set.of(1, 2, 3, 400, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        assertThat(result.message()).isEqualTo(String.valueOf(ValidationMsg.ERROR_NOT_ENOUGH_NUMBERS_OR_OUT_OF_RANGE_FROM_1_TO_99));
    }
    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers(){
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(LocalDateTime.now(fixedClock));
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.TicketId())
                        .drawDate(result.numbersInputDate())
                        .numbers(result.numbers())
                        .build()
        );
    }
    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers1() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                drawDateFacade,
                new NumbersValidator(),
                new InMemoryNumberReceiverRepositoryTestImplementation(),
                fixedClock);
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDto result = numberReceiverFacade.inputNumbers(numbers);

        //month later
        fixedClock.advanceInTimeBy(Duration.ofDays(10));
        fixedClock.advanceInTimeBy(Duration.ofMinutes(25));
        InputNumbersResultDto result1 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 35, 4, 5, 6));
        InputNumbersResultDto result2 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 47, 5, 6));

        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(LocalDateTime.of(2023, 12, 24, 12, 0, 0));
        List<TicketDto> ticketDtos1 = numberReceiverFacade.userNumbers(LocalDateTime.of(2024, 1, 3, 12, 25, 0));
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.TicketId())
                        .drawDate(result.numbersInputDate())
                        .numbers(Set.of(1, 2, 3, 4, 5, 6))
                        .build()
        );
        assertThat(ticketDtos1).containsExactlyInAnyOrder(
                TicketDto.builder()
                        .ticketId(result1.TicketId())
                        .drawDate(result1.numbersInputDate())
                        .numbers(Set.of(1, 2, 35, 4, 5, 6))
                        .build(),
                TicketDto.builder()
                        .ticketId(result2.TicketId())
                        .drawDate(result2.numbersInputDate())
                        .numbers(Set.of(1, 2, 3, 47, 5, 6))
                        .build()
        );
    }
}