package com.norpix.drawdate;

import com.norpix.AdjustingClock;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
@Log
class DrawDateFacadeTest {
    AdjustingClock fixedClock = new AdjustingClock(LocalDateTime.of(2023, 12, 24, 12, 0, 0).toInstant(ZoneOffset.ofHours(1)), ZoneId.of("Europe/Warsaw"));
    DrawDateFacade drawDateFacade = new DrawDateFacade(fixedClock);
    @Test
    public void should_return_next_draw_date_on_saturday_12_00() {
        String correctNextDrawDate = drawDateFacade.nextDrawDate(LocalDateTime.now(fixedClock));
        String result = LocalTime.of(12, 0) + " " + (DayOfWeek.SATURDAY);
        assertThat(correctNextDrawDate).contains(result);
    }
}