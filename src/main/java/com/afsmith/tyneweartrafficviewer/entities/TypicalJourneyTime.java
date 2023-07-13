package com.afsmith.tyneweartrafficviewer.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

/**
 * An entity representing the typical time it takes traffic to traverse a link
 * for which traffic data is collected. The average is calculated over 5 minute
 * intervals for both weekdays and the weekend.
 */
@ToString
@Setter
@Getter
@Entity
public class TypicalJourneyTime implements TrafficEntity {

    // An identifying code for the link.
    @CsvBindByName(column = "Site Name", profiles = "weekday")
    @CsvBindByName(column = "Link Name", profiles = "weekend")
    @Id
    private String systemCodeNumber;

    // Does the data represent a weekend?
    @CsvIgnore
    private boolean isWeekend;

    // The average time taken to traverse the link.
    @CsvBindByName(column = "Average", profiles = "weekday")
    @CsvBindByName(column = "Average Travel Time (s)", profiles = "weekend")
    private double travelTime;

    // The standard deviation of the travel time data.
    @CsvBindByName(column = "StDev", profiles = "weekday")
    @CsvBindByName(column = "Standard Dev", profiles = "weekend")
    private double standardDeviation;

    // The time for which the average is calculated (5 minute intervals).
    @CsvBindByName(column = "Time")
    @CsvDate("HH:mm")
    private LocalTime timeOfDay;
}
