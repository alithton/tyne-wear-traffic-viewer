package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import com.opencsv.bean.CsvNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

/**
 * An entity representing the typical time it takes traffic to traverse a link
 * for which traffic data is collected. The average is calculated over 5 minute
 * intervals for both weekdays and the weekend.
 */
@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypicalJourneyTime implements TrafficEntity {

    @GeneratedValue
    @Id
    private long id;

    // An identifying code for the link.
    @CsvBindByName(column = "Site Name", profiles = "weekday")
    @CsvBindByName(column = "Link Name", profiles = "weekend")
    private String systemCodeNumber;

    // Does the data represent a weekend?
    @CsvIgnore
    private boolean isWeekend;

    // The average time taken to traverse the link.
    @CsvBindByName(column = "Average", profiles = "weekday")
    @CsvBindByName(column = "Average Travel Time (s)", profiles = "weekend")
    @CsvNumber("#,##0.00")
    private double travelTime;

    // The standard deviation of the travel time data.
    @CsvBindByName(column = "StDev", profiles = "weekday")
    @CsvBindByName(column = "Standard Dev", profiles = "weekend")
    private double standardDeviation;

    // The time for which the average is calculated (5 minute intervals).
    @CsvBindByName(column = "Time")
    @CsvDate("HH:mm")
    private LocalTime timeOfDay;

    @Override
    public boolean isIncluded(FilterService filter) {
        return true;
    }
}
