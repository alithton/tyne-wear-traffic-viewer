package com.afsmith.tyneweartrafficviewer.persistence.entities;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Entity
public class JourneyTimeDynamic extends TrafficData {
    int linkTravelTime;
    int platesIn;
    int platesOut;
    int plateMatches;
    ZonedDateTime lastUpdated;

    @Builder
    public JourneyTimeDynamic(String systemCodeNumber,
                              int linkTravelTime,
                              int platesIn,
                              int platesOut,
                              int plateMatches,
                              ZonedDateTime lastUpdated) {
        super(systemCodeNumber);
        this.linkTravelTime = linkTravelTime;
        this.platesIn = platesIn;
        this.platesOut = platesOut;
        this.plateMatches = plateMatches;
        this.lastUpdated = lastUpdated;
    }
}
