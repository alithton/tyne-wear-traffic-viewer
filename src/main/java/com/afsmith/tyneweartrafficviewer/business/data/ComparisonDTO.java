package com.afsmith.tyneweartrafficviewer.business.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A data object that represents a comparison between current and average journey times
 * (or traffic speeds) for the same stretch of road.
 */
@ToString
@Getter
@NoArgsConstructor
public class ComparisonDTO extends JourneyTimeDTO {
    double typicalSpeed;
    double speedComparison;
    ComparisonResult comparison;

    // Comparison threshold to be deemed much faster / slower than normal.
    @JsonIgnore
    private final double OUTER_THRESHOLD = 0.2;

    // Comparison threshold to be deemed faster / slower than normal.
    @JsonIgnore
    private final double INNER_THRESHOLD = 0.1;

    /**
     * An all-arguments constructor for traffic speed comparison data transfer objects.
     */
    @Builder(builderMethodName = "comparisonBuilder")
    public ComparisonDTO(String systemCodeNumber,
                         String shortDescription,
                         String longDescription,
                         SimpleRouteDTO route,
                         double speed,
                         double typicalSpeed) {
        super(systemCodeNumber, shortDescription, longDescription, route, speed);
        this.typicalSpeed = typicalSpeed;
        this.speedComparison = compareSpeeds(typicalSpeed, speed);
        this.comparison = getComparisonResult(this.speedComparison, typicalSpeed);
    }

    /*
     * Compute a comparison between the current and average speeds. This is just a simple
     * difference between the two, although in principle an alternative comparison function
     * could be used.
     */
    private double compareSpeeds(double average, double current) {
        return current - average;
    }

    /*
     * Determine the result of the speed comparison, which determines what is displayed
     * to users. The result is dependent on the ratio of speed difference (as calculated
     * by compareSpeeds) to the average speeds. Negative values for this ratio indicate
     * slower speeds than normal, while positive values indicate faster. The thresholds
     * at which different results are returned are determined by the outer and inner
     * threshold attributes.
     */
    private ComparisonResult getComparisonResult(double speedComparison, double average) {
        ComparisonResult result = ComparisonResult.SIMILAR;
        double ratio = speedComparison / average;

        if (ratio < -1 * OUTER_THRESHOLD) result = ComparisonResult.MUCH_SLOWER;
        else if (ratio < -1 * INNER_THRESHOLD) result = ComparisonResult.SLOWER;
        else if (ratio > OUTER_THRESHOLD) result = ComparisonResult.MUCH_FASTER;
        else if (ratio > INNER_THRESHOLD) result = ComparisonResult.FASTER;

        return result;
    }
}
