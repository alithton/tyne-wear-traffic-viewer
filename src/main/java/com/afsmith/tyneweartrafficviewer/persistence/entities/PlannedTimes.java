package com.afsmith.tyneweartrafficviewer.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PlannedTimes {
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
