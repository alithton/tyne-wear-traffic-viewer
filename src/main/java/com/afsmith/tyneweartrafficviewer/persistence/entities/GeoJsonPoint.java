package com.afsmith.tyneweartrafficviewer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.util.Objects;

//@JsonDeserialize(using = GeoJsonPointDeserialiser.class)
@Embeddable
@NoArgsConstructor
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public final class GeoJsonPoint {
    private double latitude;
    private double longitude;

    public GeoJsonPoint(
            double latitude,
            double longitude
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoJsonPoint(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @JsonIgnore
    public String getLatitudeString() {
        return Double.toString(latitude);
    }

    @JsonIgnore
    public String getLongitudeString() {
        return Double.toString(longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GeoJsonPoint) obj;
        return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(that.latitude) &&
                Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "GeoJsonPoint[" +
                "latitude=" + latitude + ", " +
                "longitude=" + longitude + ']';
    }

}
