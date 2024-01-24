package com.norpix.drawdate.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NextDrawDateDto(LocalDate closestDrawDate, String dayOfWeek, String drawTime) {
}