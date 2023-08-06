package com.afsmith.tyneweartrafficviewer.entities;

import com.afsmith.tyneweartrafficviewer.business.services.filter.FilterService;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * The base traffic data class that expresses the shared relationship between
 * all traffic data entities.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class TrafficData implements TrafficEntity {
    @Id
    private String systemCodeNumber;

    /**
     * Do not filter traffic data by default. Any entity subclasses that should be
     * subject to filtering must override this method.
     * @param filterService A service providing a set of configurable filters to determine
     *                      which entities should be included in the server response.
     * @return Should the entity be included in the response?
     */
    public boolean isIncluded(FilterService filterService) {
        return true;
    }
}
