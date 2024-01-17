package com.norpix.drawdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

public class DrawDateFacade {
    public String nextDrawDate(LocalDate date){
        String closestDrawDate = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        return closestDrawDate + " 12:00";
    }
}
