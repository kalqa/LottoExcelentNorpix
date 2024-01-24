package com.norpix.numberreceiver;

import com.norpix.drawdate.DrawDateFacade;
import com.norpix.drawdate.dto.NextDrawDateDto;
import com.norpix.numberreceiver.dto.InputNumbersResultDto;
import com.norpix.numberreceiver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {
    private NumbersValidator validate;
    private NumberReceiverRepository repository;
    public InputNumbersResultDto inputNumbers(final Set<Integer> numbers) {
        NextDrawDateDto drawDate = DrawDateFacade.nextDrawDate(LocalDate.now());
        String validationOutput = validate.validate(numbers);
        boolean passedValidation = validationOutput.contains("received");

        if (passedValidation) {
            String clientTickedId = UUID.randomUUID().toString();
            Ticket save = repository.save(new Ticket(clientTickedId, drawDate, numbers));
            return InputNumbersResultDto.builder()
                    .drawDate(save.drawDate())
                    .TicketId(save.clientTickedId())
                    .message(validationOutput + drawDate)
                    .build();
        }
        return InputNumbersResultDto.builder()
                .message(validationOutput)
                .build();
    }
    public List<TicketDto> userNumbers(LocalDate date){
        return List.of(
                TicketDto.builder()
                        .ticketId("1")
                        .drawDate(DrawDateFacade.nextDrawDate(LocalDate.now()))
                        .numbers(Set.of(1,2,3,4,5,6))
                        .build()
        );
    }
}