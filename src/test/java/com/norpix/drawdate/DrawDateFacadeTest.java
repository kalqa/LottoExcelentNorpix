package com.norpix.drawdate;

import com.norpix.drawdate.dto.NextDrawDateDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
class DrawDateFacadeTest {
    DrawDateFacade drawDateFacade;
    @Test
    public void should_return_next_draw_date_day() {
        NextDrawDateDto drawDate = drawDateFacade.nextDrawDate(LocalDateTime.now());
        NextDrawDateDto expectedDrawDateDto = NextDrawDateDto.builder()
                .closestDrawDate(drawDate.closestDrawDate())
                .dayOfWeek(drawDate.dayOfWeek())
                .drawTime(drawDate.drawTime())
                .build();
        assertThat(expectedDrawDateDto).isEqualTo(drawDate);
    }
}