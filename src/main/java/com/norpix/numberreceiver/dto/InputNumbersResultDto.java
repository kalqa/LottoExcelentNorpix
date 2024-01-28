package com.norpix.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record InputNumbersResultDto(String message, LocalDateTime numbersInputDate, String TicketId, Set<Integer> numbers) {
}