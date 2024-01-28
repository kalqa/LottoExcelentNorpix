package com.norpix.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

record Ticket(String clientTickedId, LocalDateTime drawDate, Set<Integer> numbers) {
}