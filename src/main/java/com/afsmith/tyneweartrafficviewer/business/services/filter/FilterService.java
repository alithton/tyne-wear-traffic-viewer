package com.afsmith.tyneweartrafficviewer.business.services.filter;

import com.afsmith.tyneweartrafficviewer.entities.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a collection of methods for filtering traffic data. The parameters determining
 * what data is filtered out are configured by supplying a FilterOptions object.
 */
public class FilterService {

    // The configuration used for filtered.
    private final FilterOptions filterOptions;

    /*
     * Private constructor for the filter service.
     */
    private FilterService(FilterOptions filterOptions) {
        this.filterOptions = filterOptions;
    }

    /**
     * Construct a FilterService instance using the supplied filter options. This factory
     * method ensures that filter options that are missing from supplied FilterOptions object
     * are correctly initialised.
     * @param filterOptions The options to be used for filtering.
     * @return An instance of FilterService with a correctly initialised set of filter options.
     */
    public static FilterService fromOptions(FilterOptions filterOptions) {
        if (filterOptions.getSpeedType() == null) {
            filterOptions.setSpeedType(SpeedType.CURRENT);
        }
        if (filterOptions.getSeverity() == null) {
            filterOptions.setSeverity(new ArrayList<>());
        }
        if (filterOptions.getCurrentOnly() == null) {
            filterOptions.setCurrentOnly(false);
        }
        if (filterOptions.getIncludeCustomIncidents() == null) {
            filterOptions.setIncludeCustomIncidents(false);
        }
        return new FilterService(filterOptions);
    }

    /**
     * Is any data being requested? If no traffic data types are specified, then
     * this is interpreted as no data being requested.
     * @return Whether no data is requested.
     */
    public boolean noDataRequested() {
        return filterOptions.getDataTypes() == null || filterOptions.getDataTypes().isEmpty();
    }

    /**
     * Is current speed data being requested?
     * @return Whether current speed data is requested.
     */
    public boolean isCurrentSpeed() {
        return filterOptions.getSpeedType() == SpeedType.CURRENT;
    }

    /**
     * Get a list of data types that were requested.
     * @return A list of traffic data types.
     */
    public List<TrafficDataTypes> getDataTypes() {
        return filterOptions.getDataTypes();
    }

    /**
     * Filter a provided list of entities by applying the configured filter options
     * to them.
     * @param entities The list of entities to be filtered.
     * @return The filtered list of entities.
     * @param <T> The type of entity to be filtered.
     */
    public <T extends TrafficEntity> List<T> filter(List<T> entities) {
        return entities.stream()
                       .filter(e -> e.isIncluded(this))
                       .toList();
    }

    /**
     * Is the provided severity value among the filter options?
     * @param severity The severity value.
     * @return Whether the severity value is in the filter options.
     */
    public boolean filterSeverity(String severity) {
        return filterOptions.getSeverity().contains(severity);
    }

    /**
     * Does the provided traffic entity conform to the custom incident filter settings?
     * If the provided entity is not a user-created one, it will always pass this
     * filter.
     * @param entity The entity to be checked.
     * @return Whether the entity conforms to the custom incident filter settings.
     */
    public boolean filterCustom(TrafficEntity entity) {
        if (!filterOptions.getIncludeCustomIncidents()) {
            return !isCustom(entity);
        }
        return true;
    }

    /**
     * Do the provided start and end times fall within the range specified by the
     * filter options?
     * <p>
     *     The behaviour of this filter check is dependent on the filter options.
     *     If current data only is selected, this method will whether the provided
     *     start and end times overlap with the current time.
     * </p>
     * <p>
     *     Otherwise, the start and end date filter options are used. If values
     *     for both are provided, then a check is made to determine whether that
     *     range overlaps with the range of the provided start and end times. If
     *     both filter option start and end times are missing, then this always
     *     returns true. If only filter option start time is provided, a check is
     *     made whether any part of the provided time range occurs after that start
     *     time. If only the filter option end time is provided, a check is made
     *     to determine whether any part of the provided time range occurs before
     *     that end time.
     * </p>
     * @param start The query start time.
     * @param end The query end time.
     * @return Whether the start and end time fall with the included range.
     */
    public boolean filterDate(ZonedDateTime start, ZonedDateTime end) {
        if (start == null) return false;
        // If an end date is unavailable, arbitrarily use 1 day after the start time.
        if (end == null) end = start.plusDays(1L);
        return filterOptions.getCurrentOnly()
                ? isCurrent(start, end)
                : overlapsDateRange(start, end);
    }

    /**
     * Do the provided start and end times include the current time?
     * @param start The query start time.
     * @param end The query end time.
     * @return Whether the specified time range includes the current time.
     */
    public boolean isCurrent(ZonedDateTime start, ZonedDateTime end) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }

    /**
     * Do the provided start and end times overlap with the included range specified
     * in the filter options?
     * @param incidentStart The query start time.
     * @param incidentEnd The query end time.
     * @return Whether the start and end time overlap with the included range.
     */
    public boolean overlapsDateRange(ZonedDateTime incidentStart, ZonedDateTime incidentEnd) {
        // Do not filter if both the start and end date of the filter range are unspecified.
        if (filterOptions.getStartDate() == null && filterOptions.getEndDate() == null) return true;

        // If no start date is specified, retrieve all incidents that start before the end date.
        if (filterOptions.getStartDate() == null) return incidentStart.isBefore(filterOptions.getEndDate());

        // if no end date is specified, retrieve all incidents that end after the start date.
        if (filterOptions.getEndDate() == null) return incidentEnd.isAfter(filterOptions.getStartDate());
        return !isOutwithDateRange(incidentStart, incidentEnd);
    }

    /**
     * Is the provided traffic entity a user-created one?
     * @param entity The entity.
     * @return Whether the traffic entity was user-created.
     */
    public boolean isCustom(TrafficEntity entity) {
        if (entity instanceof TrafficPointData) {
            return "User".equals(((TrafficPointData) entity).getDataSourceTypeRef());
        }
        // Only subclasses of TrafficPointData can be custom. Do not apply filter to other data types.
        return false;
    }

    // Are the provided start and end times outwith the included range as specified by the filter options?
    private boolean isOutwithDateRange(ZonedDateTime incidentStart, ZonedDateTime incidentEnd) {
        return incidentEnd.isBefore(filterOptions.getStartDate())
                || incidentStart.isAfter(filterOptions.getEndDate());
    }
}
