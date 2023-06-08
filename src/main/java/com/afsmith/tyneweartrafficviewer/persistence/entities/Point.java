package com.afsmith.tyneweartrafficviewer.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Point {
    private Long easting;
    private Long northing;
    private double latitude;
    private double longitude;
}
