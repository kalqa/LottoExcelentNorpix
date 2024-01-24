package com.norpix.numberreceiver.dto;

import com.norpix.drawdate.dto.NextDrawDateDto;
import lombok.Builder;

@Builder
public record InputNumbersResultDto(String message, NextDrawDateDto drawDate, String TicketId) {
}