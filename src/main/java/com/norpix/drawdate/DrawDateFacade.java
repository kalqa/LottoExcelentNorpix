package com.norpix.drawdate;

import com.norpix.drawdate.dto.NextDrawDateDto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;


public class DrawDateFacade {
    private static final String DRAW_TIME = "12:00";
    public NextDrawDateDto nextDrawDate(LocalDateTime date){
        LocalDateTime closestDrawDate = (date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))).withHour(12).withMinute(0).withSecond(0);
        String showDayOfWeek = String.valueOf(closestDrawDate.getDayOfWeek());
        closestDrawDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        return NextDrawDateDto.builder()
                .closestDrawDate(closestDrawDate)
                .dayOfWeek(showDayOfWeek)
                .drawTime(DRAW_TIME)
                .build();
    }
}