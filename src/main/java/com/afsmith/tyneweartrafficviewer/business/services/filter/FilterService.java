package com.afsmith.tyneweartrafficviewer.business.services.filter;

import com.afsmith.tyneweartrafficviewer.business.data.TrafficDataTypes;
import com.afsmith.tyneweartrafficviewer.entities.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterService {
    private final FilterOptions filterOptions;

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

    public boolean noDataRequested() {
        return filterOptions.getDataTypes() == null || filterOptions.getDataTypes().isEmpty();
    }

    public boolean isCurrentSpeed() {
        return filterOptions.getSpeedType() == SpeedType.CURRENT;
    }

    public List<TrafficDataTypes> getDataTypes() {
        return filterOptions.getDataTypes();
    }

    public <T extends TrafficEntity> List<T> filter(List<T> entities) {
        return entities.stream()
                       .filter(e -> e.isIncluded(this))
                       .toList();
    }

    public boolean filterSeverity(String severity) {
        return filterOptions.getSeverity().contains(severity);
    }

    public boolean filterCustom(TrafficEntity entity) {
        if (!filterOptions.getIncludeCustomIncidents()) {
            return !isCustom(entity);
        }
        return true;
    }

    public boolean filterDate(ZonedDateTime start, ZonedDateTime end) {
        if (start == null) return false;
        // If an end date is unavailable, arbitrarily use 1 day after the start time.
        if (end == null) end = start.plusDays(1L);
        return filterOptions.getCurrentOnly()
                ? isCurrent(start, end)
                : overlapsDateRange(start, end);
    }

    public boolean isCurrent(ZonedDateTime start, ZonedDateTime end) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }

    public boolean overlapsDateRange(ZonedDateTime incidentStart, ZonedDateTime incidentEnd) {
        // Do not filter if both the start and end date of the filter range are unspecified.
        if (filterOptions.getStartDate() == null && filterOptions.getEndDate() == null) return true;
        // If no start date is specified, retrieve all incidents that start before the end date.
        if (filterOptions.getStartDate() == null) return incidentStart.isBefore(filterOptions.getEndDate());
        // if no end date is specified, retrieve all incidents that end after the start date.
        if (filterOptions.getEndDate() == null) return incidentEnd.isAfter(filterOptions.getStartDate());
        return !isOutwithDateRange(incidentStart, incidentEnd);
    }

    public boolean isCustom(TrafficEntity entity) {
        if (entity instanceof TrafficPointData) {
            return "User".equals(((TrafficPointData) entity).getDataSourceTypeRef());
        }
        // Only subclasses of TrafficPointData can be custom. Do not apply filter to other data types.
        return false;
    }

    private boolean isOutwithDateRange(ZonedDateTime incidentStart, ZonedDateTime incidentEnd) {
        return incidentEnd.isBefore(filterOptions.getStartDate())
                || incidentStart.isAfter(filterOptions.getEndDate());
    }
}
