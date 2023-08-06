package com.afsmith.tyneweartrafficviewer.business.data;

/**
 * An interface that should be implemented by all traffic data transfer objects,
 * expressing the shared methods that can be called on all traffic DTOs.
 */
public interface TrafficDTO {

    /**
     * Get the system code number of the DTO.
     * @return The DTO's system code number.
     */
    String getSystemCodeNumber();
}
