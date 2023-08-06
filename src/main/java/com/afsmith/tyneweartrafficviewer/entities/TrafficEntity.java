package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;

/**
 * An interface that should be implemented by all traffic data entities. Provides
 * a collection of generic methods that can be called on all traffic entities.
 */
public interface TrafficEntity {

    /**
     * Get the system code number of the entity. This is an identifier that uniquely
     * identifies the incident, in case of traffic incident data, camera or stretch
     * of road.
     * @return The entity's system code number.
     */
    String getSystemCodeNumber();

    /**
     * Should the entity be included in the returned set of data based on the criteria
     * specified by the filter service?
     * @param filterService A service providing a set of configurable filters to determine
     *                      which entities should be included in the server response.
     * @return Whether the entity should be included.
     */
    boolean isIncluded(FilterService filterService);
}
