package com.norpix.numberreceiver;

import com.norpix.numberreceiver.dto.TicketDto;

public class TicketMapper {
    public static TicketDto mapFromTicket(Ticket ticket){
        return TicketDto.builder()
                .numbers(ticket.numbers())
                .ticketId(ticket.clientTickedId())
                .drawDate(ticket.drawDate())
                .build();
    }
}
