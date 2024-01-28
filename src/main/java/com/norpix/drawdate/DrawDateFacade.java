package com.norpix.drawdate;

import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

@AllArgsConstructor
public class DrawDateFacade {
    private final Clock clock;
    public LocalDateTime enteredNumbersDate(LocalDateTime date){
        return LocalDateTime.now(clock);
    }
    public String nextDrawDate(LocalDateTime date){
        LocalDateTime closestDrawDate = (date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))).withHour(12).withMinute(0).withSecond(0);
        String showDayOfWeek = String.valueOf(closestDrawDate.getDayOfWeek());
        closestDrawDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        return closestDrawDate + " " + showDayOfWeek;
    }
}