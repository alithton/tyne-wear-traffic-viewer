package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Represents a time period. This is used to represent the planned or actual start
 * and end times of some traffic incidents.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlannedTimes {
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
