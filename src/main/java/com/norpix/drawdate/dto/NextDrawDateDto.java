package com.norpix.drawdate.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NextDrawDateDto(LocalDateTime closestDrawDate, String dayOfWeek, String drawTime) {
}