package com.afsmith.tyneweartrafficviewer.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a geographic location specified by its latitude and longitude. It is
 * formatted as an array when serialised to JSON format.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Point {
    private double latitude;
    private double longitude;
}
