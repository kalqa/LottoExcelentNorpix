package com.norpix.numberreceiver;

import com.norpix.drawdate.DrawDateFacade;
import com.norpix.numberreceiver.dto.InputNumbersResultDto;
import com.norpix.numberreceiver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {
    private final DrawDateFacade drawDateFacade;
    private final NumbersValidator validator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumbersResultDto inputNumbers(final Set<Integer> numbers) {
        LocalDateTime numbersInputDate = drawDateFacade.enteredNumbersDate(LocalDateTime.now(clock));
        ValidationMsg validationOutput = validator.validate(numbers);

        if (validationOutput == ValidationMsg.VALIDATION_PASSED) {
            String clientTickedId = UUID.randomUUID().toString();
            Ticket save = repository.save(new Ticket(clientTickedId, numbersInputDate, numbers));
            return InputNumbersResultDto.builder()
                    .numbersInputDate(save.drawDate())
                    .TicketId(save.clientTickedId())
                    .numbers(save.numbers())
                    .message(String.valueOf(validationOutput))
                    .build();
        }
        return InputNumbersResultDto.builder()
                .message(String.valueOf(validationOutput))
                .build();
    }
    public List<TicketDto> userNumbers(LocalDateTime date){
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }
    public String showNextDrawDate(){
        return drawDateFacade.nextDrawDate(LocalDateTime.now());
    }
}