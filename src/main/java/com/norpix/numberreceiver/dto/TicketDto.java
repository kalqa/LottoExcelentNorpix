package com.norpix.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketDto(LocalDateTime drawDate, String ticketId, Set<Integer> numbers) {
}