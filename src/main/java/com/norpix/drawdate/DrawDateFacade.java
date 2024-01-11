package com.norpix.drawdate;

import java.time.LocalDateTime;

public class DrawDateFacade {

    public LocalDateTime nextDrawDate(LocalDateTime date){
        LocalDateTime now = LocalDateTime.now();
        // wzgledem date (11-01-2024)
        // oddaje kolejna sobote
        // return date (13-01-2024)
        return now;
    }
}
