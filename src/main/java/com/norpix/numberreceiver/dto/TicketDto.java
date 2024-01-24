package com.norpix.numberreceiver.dto;

import com.norpix.drawdate.dto.NextDrawDateDto;
import lombok.Builder;

import java.util.Set;

@Builder
public record TicketDto(NextDrawDateDto drawDate, String ticketId, Set<Integer> numbers) {
}