package com.norpix.drawdate;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
class DrawDateFacadeTest {
    DrawDateFacade drawDateFacade = new DrawDateFacade();
    @Test
    public void should_return_next_draw_date() {
        String drawDate = drawDateFacade.nextDrawDate(LocalDate.of(2024, 1, 17));
        assertThat(drawDate).isEqualTo("20.01.2024 12:00");
    }
}