package com.norpix.drawdate;

import com.norpix.drawdate.dto.NextDrawDateDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;


public class DrawDateFacade {
    private static final String DRAW_TIME = "12:00";
    public static NextDrawDateDto nextDrawDate(LocalDate date){
        LocalDate closestDrawDate = (date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
        String showDayOfWeek = String.valueOf(closestDrawDate.getDayOfWeek());
        closestDrawDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        return NextDrawDateDto.builder()
                .closestDrawDate(closestDrawDate)
                .dayOfWeek(showDayOfWeek)
                .drawTime(DRAW_TIME)
                .build();
    }
}