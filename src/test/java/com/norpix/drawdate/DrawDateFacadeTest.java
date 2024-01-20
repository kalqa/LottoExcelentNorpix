package com.norpix.drawdate;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
class DrawDateFacadeTest {
    DrawDateFacade drawDateFacade = new DrawDateFacade();
    @Test
    public void should_return_next_draw_date_day() {
        String drawDate = drawDateFacade.nextDrawDate(LocalDate.now());
        assertThat(drawDate).contains(" SATURDAY 12:00");
    }
}