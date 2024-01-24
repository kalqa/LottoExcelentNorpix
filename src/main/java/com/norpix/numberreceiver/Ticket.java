package com.norpix.numberreceiver;

import com.norpix.drawdate.dto.NextDrawDateDto;

import java.util.Set;

record Ticket(String clientTickedId, NextDrawDateDto drawDate, Set<Integer> numbers) {
}